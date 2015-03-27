(defproject sampleapi
  "0.1.0-tag"
  :description "A Simple API server in Clams"
  :url "http://standardtreasury.com"
  :main sampleapi.main
  :dependencies [
                 [cheshire "5.4.0"]
                 [clams "0.1.0" :exclusions [commons-codec]]
                 [clj-time "0.9.0"]
                 [clojure.jdbc/clojure.jdbc-c3p0 "0.3.1"]
                 [foe "0.3.0-1-ge28b"]
                 [htmhell "0.1.2"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]
                 [org.slf4j/slf4j-log4j12 "1.7.7"]
                 [ragtime/ragtime.sql.files "0.3.8"]
                 [ring "1.3.1"]
                 [ring-server "0.4.0"]
                 [yesql "0.4.0"]
                 ]
  :repositories{"internal" {:url "s3://standard-releases/releases/"
                            :username :env/aws_access_key_id
                            :passphrase :env/aws_secret_access_key
                            :sign-releases false}}
  :ragtime {
            :migrations ragtime.sql.files/migrations
            :database "jdbc:postgresql://localhost/sampleapi?user=postgres"}
  :profiles {:test {:ragtime {:database
                              "jdbc:postgresql://localhost/sampleapi_test?user=postgres"}
                    :jvm-opts ["-Dclams.env=test"]}}
  :plugins [
            [lein-maven-s3-wagon "0.2.4"]
            [ragtime/ragtime.lein "0.3.8"]
            ]
  )
