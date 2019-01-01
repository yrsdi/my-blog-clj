(ns user
  (:require [my-blog-clj.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [my-blog-clj.core :refer [start-app]]
            [my-blog-clj.db.core]
            [conman.core :as conman]
            [luminus-migrations.core :as migrations]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'my-blog-clj.core/repl-server))

(defn stop []
  (mount/stop-except #'my-blog-clj.core/repl-server))

(defn restart []
  (stop)
  (start))

(defn restart-db []
  (mount/stop #'my-blog-clj.db.core/*db*)
  (mount/start #'my-blog-clj.db.core/*db*)
  (binding [*ns* 'my-blog-clj.db.core]
    (conman/bind-connection my-blog-clj.db.core/*db* "sql/queries.sql")))

(defn reset-db []
  (migrations/migrate ["reset"] (select-keys env [:database-url])))

(defn migrate []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration [name]
  (migrations/create name (select-keys env [:database-url])))


