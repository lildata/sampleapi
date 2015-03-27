(ns sampleapi.test.fixtures
  "Fixtures test data

   @ctdean"
  (:require
   [clojure.java.jdbc :as jdbc]
   clojure.tools.logging
   [sampleapi.db.config :as db]))

(def task-data
  [
   {:id 101 :title "Task 1" :completed false :created_at (db/now) :updated_at (db/now)}
   {:id 102 :title "Task 2" :completed true  :created_at (db/now) :updated_at (db/now)}
   {:id 103 :title "Task 3" :completed false :created_at (db/now) :updated_at (db/now)}
   ])

(defn- clean-data
  "Erase all the fixture and new data in the test db"
  []
  (jdbc/execute! db/spec ["delete from task where id > 100"]))

(defn- load-data
  "Load in the fixture data"
  []
  (jdbc/execute! db/spec ["truncate task"])
  (doseq [row task-data]
    (jdbc/insert! db/spec :task row)))

(defn wrap-fixture-data [f]
  (clean-data)
  (load-data)
  (f)
  ;;(clean-data)
  )
