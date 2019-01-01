(ns my-blog-clj.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[my-blog-clj started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[my-blog-clj has shut down successfully]=-"))
   :middleware identity})
