(ns fogus.fi
  (:require [clojure.string :as string])
  (:import java.util.function.Function
           java.util.function.BiFunction
           java.util.function.BinaryOperator
           java.util.function.Predicate
           java.util.function.BiPredicate
           java.util.function.Supplier
           java.util.stream.Stream
           java.util.stream.Collectors
           java.util.Arrays
           java.nio.file.Files
           java.nio.file.Path
           java.nio.file.Paths
           java.net.URI))

(defn do-supplier [n]
  (-> (Stream/generate (reify Supplier (get [_] 42)))
      (.limit n)
      (.collect (Collectors/toList))))

(defn do-func []
  (-> (Arrays/asList (to-array ["foo" "bar" "baz"]))
      .stream
      (.map (reify Function (apply [_ s] (string/upper-case s))))
      (.collect (Collectors/toList))))

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



