(ns sampleapi.main
  "@ctdean"
  (:require
    [clams.app :as app]
    [clams.conf :as conf]
    [clojure.tools.logging :as log]
    [ring.middleware.reload :as reload])
  (:gen-class))

(def middleware
  [
   reload/wrap-reload
   ])

(defn -main [& args]
  (app/start-server 'sampleapi {:middleware middleware})
  (log/infof "Starting server on port %s" (conf/get :port)))
