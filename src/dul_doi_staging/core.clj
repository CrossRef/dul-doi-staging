(ns dul-doi-staging.core
 (:require  [config.core :refer [env]]
            [crossref.util.doi :as cr-doi]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [org.httpkit.server :as server]
            [org.httpkit.client :as client]
            [compojure.core :refer [defroutes GET ANY context]]
            [liberator.core :refer [defresource]]
            [ring.util.response :refer [response]])
  (:import [java.net URL URI URLEncoder])
  (:gen-class))

(def mapping (-> "mapping.json" clojure.java.io/resource slurp json/read-str))

(defn resolve-doi
  [doi]
  (let [response @(client/get (str "http://doi.org/" (URLEncoder/encode doi "UTF-8")) {:follow-redirects false})
        status (some-> response :status)
        endpoint (some-> doi cr-doi/get-prefix mapping)
        formatted-endpoint (str "<" endpoint ">; rel=\"dul\"")
        headers (-> response
                    :headers
                    ; Explicitly get rid of the production header field.
                    (dissoc :link)
                    ; This is added automatically.
                    (dissoc :date)
                    (assoc :link formatted-endpoint))

        ; Rewrite header fields from keywords to strings.
        headers (into {} (map (fn [[k v]] [(name k) v]) headers))]
      {:status status :headers headers :body (:body response)}))


(defroutes app-routes
  (ANY "/:doi{.*}"  [doi] (resolve-doi doi)))

(defn run
  []
  (let [port (Integer/parseInt (:doi-dul-staging-port env))]
    (log/info "Start server on " port)
    (server/run-server app-routes {:port port})))

(defn -main
  [& args]
  (run))
