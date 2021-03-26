(ns fogus.fi-java-test
  (:require [clojure.test :refer :all]
            [fogus.fi :as fi])
  (:import fogus.FI))

(deftest test-suppliers
  (testing "Java"
    (is (= [42 42 42 42 42] (FI/supplier 5))))

  (testing "Java"
    (is (= [42 42 42 42 42] (fi/do-supplier 5)))))
