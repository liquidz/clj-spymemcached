(defproject clj-spymemcached "1.0.1"
  :description "simple spymemcached wrapper"
  :repositories {"spy" "http://files.couchbase.com/maven2/"}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [spy/spymemcached "2.7.3"]]
  :main clj-spymemcached.core)
