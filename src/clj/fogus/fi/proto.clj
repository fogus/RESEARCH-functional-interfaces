(ns fogus.fi.proto
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]
            [clojure.repl :as repl])
  (:import java.util.function.Supplier))



(defn supplier
  "(void -> x) -> Supplier

  Takes a function of zero-arguments and returns an instance of the interface
  java.util.function.Supplier that calls the function on invocations of its
  #get method."
  [f]
  (reify java.util.function.Supplier
    (get [_] (f))))

(s/fdef supplier
  :args (s/cat :f ifn?))

(comment

  (s/fspec :args (s/cat)
           :ret any?
           :fn any?)
  
  (stest/instrument `supplier)

  (repl/doc supplier)

  (supplier #(:a))

  (stest/unstrument `supplier)
)


(defn adder [x] #(+ x %))

(s/fdef adder
  :args (s/cat :x number?)
  :ret (s/fspec :args (s/cat :y number?)
                :ret number?)
  :fn #(= (-> % :args :x) ((:ret %) 0)))

(comment
  (stest/instrument `adder)

  (adder :a)
)
