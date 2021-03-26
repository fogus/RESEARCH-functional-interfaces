(ns fogus.fi
  (:require [clojure.string :as string])
  (:import java.util.function.Function
           java.util.function.Supplier
           java.util.stream.Stream
           java.util.stream.Collectors
           java.util.Arrays))

(defn do-supplier [n]
  (-> (Stream/generate (reify Supplier (get [_] 42)))
      (.limit n)
      (.collect (Collectors/toList))))

(defn do-func []
  (-> (Arrays/asList (to-array ["foo" "bar" "baz"]))
      .stream
      (.map (reify Function (apply [_ s] (string/upper-case s))))
      (.collect (Collectors/toList))))
