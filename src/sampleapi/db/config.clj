(ns sampleapi.db.config
  "@ctdean"
  (:require
   [clams.conf :as conf]
   [clj-time.coerce :as coerce]
   [clj-time.core :as time]
   [jdbc.pool.c3p0 :as pool]
   [yesql.core :refer [defqueries]]))

(defqueries "sql/api.sql")

(def spec (pool/make-datasource-spec
           (conf/get :database-jdbc-url)))

(defn now []
  (coerce/to-sql-time (time/now)))
