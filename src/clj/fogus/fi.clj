(ns fogus.fi
  (:import java.util.function.Supplier))

(defn do-supplier []
  (let [tl (ThreadLocal/withInitial
            (reify Supplier
              (get [_] 42)))]
    (.get tl)))
