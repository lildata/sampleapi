(ns sampleapi.db.task
  "@ctdean"
  (:require
   [sampleapi.db.config :as db]
   [clojure.java.jdbc :as jdbc]))

(defn fetch [id]
  (first (db/task-fetch db/spec id)))

(defn fetch-all []
  (db/task-fetch-all db/spec))

(defn create [title]
  (let [t (db/now)]
    (db/task-create<! db/spec title false t t)))

(defn update [task]
  (let [id (:id task)
        new (-> task
                (dissoc :id)
                (assoc :updated_at (db/now)))]
    (jdbc/update! db/spec :task new ["id = ?" id])))

(defn delete [id]
  (db/task-delete! db/spec id))
