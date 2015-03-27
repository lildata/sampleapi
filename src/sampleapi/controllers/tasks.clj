(ns sampleapi.controllers.tasks
  "@ctdean"
  (:refer-clojure :exclude [find])
  (:require
   [clams.controller :refer [defcontroller]]
   [clams.params :as p]
   [clams.response :refer [ok not-found]]
   [sampleapi.db.config :as db]
   [sampleapi.db.task :as task]))

(defn str->int
  "Convert a string to an integral number."
  [s]
  (when s
    (if (number? s)
        s
        (Long/parseLong s))))

(defcontroller fetchall []
  (ok {:tasks (task/fetch-all)}))

(defcontroller fetch [id :- p/Str]
  (let [task (task/fetch (str->int id))]
    (if task
        (ok {:task task})
        (not-found {:id (str->int id)
                    :message "Not Found"}))))

(defn create [req]
  (let [{{title :title} :task} (:params req)]
    (let [task (task/create title)]
      (ok {:task task}))))

(defn update [req]
  (let [{id :id task :task} (:params req)
        xid (str->int id)
        new (-> task
                (select-keys [:title :completed])
                (assoc :id xid))]
    (task/update new)
    (ok {:task (task/fetch xid)})))

(defcontroller delete [id :- p/Str]
  (let [xid (str->int id)
        ndeleted (task/delete xid)]
    (if (pos? ndeleted)
        (ok {:id xid :deleted true})
        (not-found {:id xid :message "Not Found"}))))
