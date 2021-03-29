(ns fogus.fi-java-test
  (:require [clojure.test :refer :all]
            [fogus.fi :as fi])
  (:import fogus.FI))

(deftest test-functions
  (testing "Java"
    (is (= ["FOO" "BAR" "BAZ"] (FI/func))))

  (testing "Clojure"
    (is (= ["FOO" "BAR" "BAZ"] (fi/do-func)))))

(deftest test-to-prim-functions
  (testing "Java"
    (is (= [-42 0 42] (FI/toLongFunc))))

  (testing "Clojure"
    (is (= [-42 0 42] (fi/do-toLongFunc)))))

(deftest test-prim-functions
  (testing "Java"
    (is (= ["1" "2" "3"] (FI/longfunc))))

  (testing "Clojure"
    (is (= ["1" "2" "3"] (fi/do-longfunc)))))

(deftest test-to-prim-bifunctions
  (testing "Java"
    (is (= 15 (FI/toLongBiFunc))))

  (testing "Clojure"
    (is (= 15 (fi/do-toLongBiFunc)))))

(deftest test-bifunctions
  (testing "Java"
    (is (= 9 (FI/bifunc))))

  (testing "Clojure"
    (is (= 9 (fi/do-bifunc)))))

(deftest test-predicates
  (testing "Java"
    (is (= ["foo" "bar" "baz"] (FI/pred))))

  (testing "Clojure"
    (is (= ["foo" "bar" "baz"] (fi/do-pred)))))

(deftest test-bipredicates
  (testing "Java"
    (is (= 1 (count (FI/bipred)))))

  (testing "Clojure"
    (is (= 1 (count (fi/do-bipred))))))

(deftest test-suppliers
  (testing "Java"
    (is (= [42 42 42 42 42] (FI/supplier 5))))

  (testing "Clojure"
    (is (= [42 42 42 42 42] (fi/do-supplier 5)))))

(deftest test-consumers
  (testing "Java"
    (is (= 5 (FI/consumer 5))))

  (testing "Clojure"
    (is (= 5 (fi/do-consumer 5)))))

(deftest test-biconsumers
  (testing "Java"
    (is (= 15 (FI/biconsumer))))

  (testing "Clojure"
    (is (= 15 (fi/do-biconsumer)))))
