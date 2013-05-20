(ns project-tofu.core 
  (:use compojure.core)
  (:require
    [clojure.contrib [duck-streams :as ds]]
    [ring.middleware [multipart-params :as mp]]
    [compojure.route :as route]
    [compojure.handler :as handler]
    [ring.util.response :as resp] 
    [ring.adapter.jetty :as jetty])) 

(defn upload-file
  [file]
  (ds/copy (file :tempfile) (ds/file-str "file.out"))
  (str "uploaded! called 'file.out' not much you can do with it right now..."))

; Routing
(defroutes main-routes
  (GET "/" []
    (resp/file-response "index.html" {:root "public"})) 

  (GET "/search" {params :params}
    {:status 200
     :body (str "Search with these params: " params)})

  (GET "/user/add/:name" {{name :name} :params}
    {:status 200
     :body (str "Create user named " name)})

  (GET "/user/:name/:name2/delete" {{name :name name2 :name2} :params}
    {:status 200
     :body (str "Deleted user named " name " " name2)})

  (POST "/searchpost" {params :params}
    {:status 200
     :body (str "Search with these params: " params)})

  (mp/wrap-multipart-params 
    (POST "/file" {params :params} (upload-file (get params "file"))))


  (route/resources "/")
  (route/not-found "404 Not Found") )


(def main-handler (-> main-routes handler/api))
; Server
(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty main-handler {:port port})))
