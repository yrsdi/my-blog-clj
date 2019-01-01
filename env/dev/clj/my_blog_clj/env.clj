(ns my-blog-clj.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [my-blog-clj.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[my-blog-clj started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[my-blog-clj has shut down successfully]=-"))
   :middleware wrap-dev})
