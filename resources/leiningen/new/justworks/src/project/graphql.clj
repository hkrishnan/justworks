(ns {{name}}.graphql
  (:require [graphql-clj.parser :as parser]
            [graphql-clj.type :as type]
            [graphql-clj.resolver :as resolver]
            [graphql-clj.executor :as executor]
            [graphql-clj.validator :as validator]
            [graphql-clj.introspection :as introspection]
            [clojure.java.io :as io]
            [clojure.core.match :as match]
            [clojure.string :as string]
            [com.netflix.hystrix.core :refer [defcommand]]))

(def schema (slurp (io/resource "graphql.schema")))

(def parsed-schema (parser/parse schema))

(def people [{:code "1"
              :firstName "John"
              :lastName "Jones"}
             {:code "2"
              :firstName "Mary"
              :lastName "Monroe"}])

(defcommand get-people
  {:hystrix/cache-key-fn (fn [] "get-people")
   :hystrix/fallback-fn (fn [] [])}
  []
  people)

(defcommand get-person
  {:hystrix/cache-key-fn (fn [code] code)
   :hystrix/fallback-fn (fn [code] {})}
  [code]
  (first (filter #(= code (:code %)) people)))

(defn resolver-fn [type-name field-name]
  (match/match
   [type-name field-name]
   ["Query" "people"] (fn [context parent args]
                        (get-people))
   ["Query" "person"] (fn [context parent args]
                        (get-person (get args "code")))
   :else nil))

(defn execute
  [query variables]
  (let [type-schema (validator/validate-schema parsed-schema)
        context nil]
    (executor/execute context type-schema resolver-fn query variables)))
