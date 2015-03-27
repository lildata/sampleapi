(ns sampleapi.routes
  "@ctdean"
  (:require [clams.route :refer :all]))

(def routes
  [
   [GET "/api/v1/tasks" :tasks-fetchall]
   [GET "/api/v1/tasks/:id" :tasks-fetch]
   [POST "/api/v1/tasks" :tasks-create]
   [PUT "/api/v1/tasks/:id" :tasks-update]
   [DELETE "/api/v1/tasks/:id" :tasks-delete]
   ])
