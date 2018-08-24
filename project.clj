(defproject dul-doi-staging "0.1.0"
  :description "Distributed Usage Logging DOI Staging server"
  :url "http://eventdata.crossref.org/"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [liberator "0.14.1"]
                 [http-kit "2.2.0"]
                 [yogthos/config "0.8"]
                 [crossref-util "0.1.15"]
                 [compojure "1.5.1"]
                 [org.clojure/data.json "0.2.6"]
                 [ring "1.6.1"]]
  :main ^:skip-aot dul-doi-staging.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
