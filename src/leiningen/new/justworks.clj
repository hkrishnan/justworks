(ns leiningen.new.justworks
  (:require [leiningen.new.templates :refer [renderer
                                             name-to-path
                                             ->files
                                             raw-resourcer]]
            [leiningen.core.main :as main]))

(def render (renderer "justworks"))

(def raw (raw-resourcer "justworks"))

(defn justworks
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' justworks project.")
    (->files data
             ["README.md" (render "README.md" data)]
             ["package.json" (render "package.json" data)]
             ["project.clj" (render "project.clj" data)]
             ["resources/graphql.schema" (render "resources/graphql.schema" data)]
             ["src/App.css" (render "src/App.css" data)]
             ["src/App.js" (render "src/App.js" data)]
             ["src/App.test.js" (render "src/App.test.js" data)]
             ["src/graphiql.css" (render "src/graphiql.css" data)]
             ["src/index.css" (render "src/index.css" data)]
             ["src/index.js" (render "src/index.js" data)]
             ["src/logo.svg" (raw "src/logo.svg")]
             ["src/{{sanitized}}/core.clj" (render "src/project/core.clj" data)]
             ["src/{{sanitized}}/handler.clj" (render "src/project/handler.clj" data)]
             ["src/{{sanitized}}/graphql.clj" (render "src/project/graphql.clj" data)]
             ["public/index.html" (render "public/index.html" data)]
             ["public/favicon.ico" (raw "public/favicon.ico")])))
