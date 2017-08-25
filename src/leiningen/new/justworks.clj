(ns leiningen.new.justworks
  (:require [clojure.string :as str]
            [leiningen.new.templates :refer [renderer
                                             name-to-path
                                             ->files
                                             raw-resourcer]]
            [leiningen.core.main :as main]))

(def render (renderer "justworks"))

(def raw (raw-resourcer "justworks"))

(def templates ["README.md"
                "CHANGELOG.md"
                ".gitignore"
                "project.clj"
                "dev/user.clj"
                "resources/schemas/hello.umlaut"
                "src/{{sanitized}}/application.clj"
                "src/{{sanitized}}/config.clj"
                "src/{{sanitized}}/components/lacinia.clj"
                "src/{{sanitized}}/components/pedestal.clj"
                "src/{{sanitized}}/components/routes.clj"
                "src/{{sanitized}}/components/schema.clj"
                "src/{{sanitized}}/resolvers/core.clj"
                "src/{{sanitized}}/resolvers/hello.clj"])

(def raw-files [])

(defn render-data
  "Replaces {{sanitized}} with the string project and renders data into it."
  [template-path data]
  (render (-> template-path
              (str/replace #"\{\{sanitized\}\}" "project"))
          data))

(defn justworks
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' justworks project.")
    (let [rendered-set (map (fn [x] [x (render-data x data)]) templates)
          raw-set (map (fn [x] [x (raw x)]) raw-files)]
      (apply (partial ->files data) (concat rendered-set raw-set)))))
