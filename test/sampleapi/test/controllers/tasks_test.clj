(ns sampleapi.test.controllers.tasks-test
  "Simple test of the task controller interface

   @ctdean"
  (:require [clojure.test :refer :all])
  (:require [sampleapi.test.fixtures :as fixtures])
  (:require [sampleapi.controllers.tasks :as tasks]))

(use-fixtures :once fixtures/wrap-fixture-data)

(defn- keep-keys [task]
  (select-keys task [:id :title]))

(defn- resp-title [resp]
  (-> resp
      :body :task :title))

(defn- resp-completed [resp]
  (-> resp
      :body :task :completed))

(deftest fetchall-test
  (is (= [{:title "Task 1", :id 101}
          {:title "Task 2", :id 102}
          {:title "Task 3", :id 103}]
         (->> (tasks/fetchall)
              :body
              :tasks
              (map keep-keys)))))

(deftest fetch-test
  (is (= {:id 101, :title "Task 1"}
         (->> (tasks/fetch "101")
              :body
              :task
              keep-keys))))

(deftest create-test
  (let [x (rand-int 1000)]
    (is (= (str "test title " x)
           (->> (tasks/create {:params {:task {:title (str "test title " x)}}})
                resp-title)))))

(deftest update-test
  (let [x (rand-int 1000)
        id (->> (tasks/create {:params {:task {:title (str "first title " x)}}})
                :body :task :id)]
    (is (= (str "first title " x)
           (->> (tasks/fetch (str id))
                resp-title)))
    (tasks/update {:params {:id (str id)
                            :task {:title (str "second title " x)}}})
    (is (= (str "second title " x)
           (->> (tasks/fetch (str id))
                resp-title)))
    (is (= false
           (->> (tasks/fetch (str id))
                resp-completed)))
    (tasks/update {:params {:id (str id)
                            :task {:completed true}}})
    (is (= true
           (->> (tasks/fetch (str id))
                resp-completed)))))

(deftest delete-test
  (let [x (rand-int 1000)
        id (->> (tasks/create {:params {:task {:title (str "first title " x)}}})
                :body :task :id)]
    (is (= (str "first title " x)
           (->> (tasks/fetch (str id))
                resp-title)))
    (tasks/delete (str id))
    (is (= 404
           (:status (tasks/fetch (str id)))))))
