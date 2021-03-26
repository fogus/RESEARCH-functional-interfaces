(ns fogus.fi-java-test
  (:require [clojure.test :refer :all]
            [fogus.fi :as fi])
  (:import fogus.FI))

(deftest test-suppliers
  (testing "Java"
    (is (= 42 (FI/supplier))))

  (testing "Java"
    (is (= 42 (fi/do-supplier))))1)
