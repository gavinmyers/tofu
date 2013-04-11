(ns project-tofu.core 
  (:use compojure.core
    [hiccup.middleware :only (wrap-base-url)]
    [hiccup core page])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defn version [] 
  (print "0.0.2"))

(defn index-page []
  (html5
    [:head [:title "Hello World"]] 
    [:body [:h1 "Hello World"]]))

(defroutes main-routes
    (GET "/" [] (index-page))
    (route/resources "/")
    (route/not-found "Page not found"))

(def app
    (-> (handler/site main-routes)
              (wrap-base-url)))
