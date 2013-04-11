(defproject project-tofu "0.1.0-SNAPSHOT"
  :description "TOFU!"
  :dependencies [[org.clojure/clojure "1.5.1"]
                                  [compojure "1.1.5"]
                                  [hiccup "1.0.3"]]
  :plugins [[lein-ring "0.8.3"]]
  :ring {:handler project-tofu.core/app})
