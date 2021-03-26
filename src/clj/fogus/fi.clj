(ns fogus.fi
  (:import java.util.function.Supplier
           java.util.stream.Stream
           java.util.stream.Collectors))

(defn do-supplier [n]
  (-> (Stream/generate (reify Supplier (get [_] 42)))
      (.limit n)
      (.collect (Collectors/toList))))
