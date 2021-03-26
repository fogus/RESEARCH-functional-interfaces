(ns fogus.fi-java-test
  (:require [clojure.test :refer :all]
            [fogus.fi :as fi])
  (:import fogus.FI))

(deftest test-functions
  (testing "Java"
    (is (= ["FOO" "BAR" "BAZ"] (FI/func))))

  (testing "Clojure"
    (is (= ["FOO" "BAR" "BAZ"] (fi/do-func)))))

(deftest test-suppliers
  (testing "Java"
    (is (= [42 42 42 42 42] (FI/supplier 5))))

  (testing "Clojure"
    (is (= [42 42 42 42 42] (fi/do-supplier 5)))))
