(ns fogus.fi
  (:require [clojure.string :as string])
  (:import java.util.function.Function
           java.util.function.ToLongFunction
           java.util.function.ToLongBiFunction
           java.util.function.BiFunction
           java.util.function.LongFunction
           java.util.function.LongToDoubleFunction
           java.util.function.BinaryOperator
           java.util.function.LongBinaryOperator
           java.util.stream.LongStream
           java.util.function.Predicate
           java.util.function.BiPredicate
           java.util.function.Supplier
           java.util.function.Consumer
           java.util.function.BiConsumer
           java.util.stream.Stream
           java.util.stream.Collectors
           java.util.Arrays
           java.nio.file.Files
           java.nio.file.Path
           java.nio.file.Paths
           java.net.URI
           java.util.concurrent.ConcurrentHashMap))

(defn do-func []
  (-> (Arrays/asList (to-array ["foo" "bar" "baz"]))
      .stream
      (.map (reify Function (apply [_ s] (string/upper-case s))))
      (.collect (Collectors/toList))))

(defn do-toLongFunc []
  (-> (Arrays/stream (to-array ["-42" "0" "42"]))
      (.mapToLong (reify ToLongFunction (applyAsLong [_ s] (Long/parseLong s 10))))
      .boxed
      (.collect (Collectors/toList))))

(defn do-longfunc []
  (-> (LongStream/of (long-array [(long 1) (long 2) (long 3)]))
      (.mapToObj (reify LongFunction (apply [_ n] (String/valueOf n))))
      (.collect (Collectors/toList))))

(defn do-long2doublefunc []
  (-> (LongStream/of (long-array [(long 1) (long 2) (long 3)]))
      (.mapToDouble (reify LongToDoubleFunction (applyAsDouble [_ n] (+ Math/PI n))))
      .boxed
      (.collect (Collectors/toList))))

(defn do-toLongBiFunc []
  (-> (ConcurrentHashMap. {"a" 1, "b" 2, "c" 3, "d" 4, "e" 5})
      (.reduceToLong 1
                     (reify ToLongBiFunction (applyAsLong [_ k v] v))
                     0
                     (reify LongBinaryOperator (applyAsLong [_ acc v] (+ acc v))))))

(map #(.getCanonicalName ^Class %) (.getInterfaces (class (fn [^long n] n))))

(defn do-bifunc []
  (-> (Arrays/asList (to-array ["foo" "bar" "baz"]))
      .stream
      (.reduce 0 (reify BinaryOperator (apply [_ acc s] (+ acc (count s)))))))

(defn do-pred []
  (-> (Arrays/asList (to-array ["foo" "frob" "bar" "baz" "quux"]))
      .stream
      (.filter (reify Predicate (test [_ s] (odd? (count s)))))
      (.collect (Collectors/toList))))

(defn do-bipred []
  (-> (Files/find (Path/of (.toURI (java.io.File. "."))) 1
                  (reify BiPredicate (test [_ path attr]
                                       (and (.isRegularFile attr)
                                            (-> path str (.endsWith "md")))))
                  (into-array java.nio.file.FileVisitOption []))
      (.collect (Collectors/toList))))

(defn do-supplier [n]
  (-> (Stream/generate (reify Supplier (get [_] 42)))
      (.limit n)
      (.collect (Collectors/toList))))

(defn do-consumer [n]
  (let [accesses (atom 0)]
    (-> (Stream/generate (reify Supplier (get [_] 42)))
        (.limit n)
        (.forEach (reify Consumer (accept [_ x] (println x) (swap! accesses inc)))))
    @accesses))

(defn do-biconsumer []
  (let [sum (atom 0)]
    (-> (java.util.HashMap. {"a" 1, "b" 2, "c" 3, "d" 4, "e" 5})
        (.forEach (reify BiConsumer (accept [_ k v] (println k "->" v) (swap! sum + v)))))
    @sum))
