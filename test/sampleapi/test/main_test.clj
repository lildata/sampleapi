(ns sampleapi.test.main-test
  "@ctdean"
  (:require [clojure.test :refer :all])
  (:require [sampleapi.main :as main])
  (:require [sampleapi.test.fixtures :as fixtures]))

(use-fixtures :once fixtures/wrap-fixture-data)

(deftest app-exists-test
  (is main/-main))
