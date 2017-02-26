(defproject vx-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/core.async "0.2.395"]
                 [clj-http "0.6.0"]
                 [compojure "1.5.2"]
                 [ring/ring-core "1.5.1"]
                 [ring/ring-jetty-adapter "1.5.1"]
                 [ring/ring-devel "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [ring-cors "0.1.8"]
                 [clj-time "0.13.0"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [graphql-clj "0.1.20" :exclusions [org.clojure/clojure]]
                 [com.netflix.hystrix/hystrix-clj "1.5.9"]]
  :main ^:skip-aot {{name}}.core
  :target-path "target/%s"
  :resource-paths ["build" "resources"]
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-ring "0.11.0"]]
  :ring {:handler {{name}}.handler/app
         :auto-reload? true
         :reload-paths ["src" "build" "resources"]
         :port 3002})
