(ns customer-ui.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [customer-ui.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
