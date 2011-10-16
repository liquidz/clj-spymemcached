(ns clj-spymemcached.core
  (:import
    [net.spy.memcached MemcachedClient]
    [java.net InetSocketAddress]))

(def mcd (atom nil))

(defn- key->str [key]
  (if (keyword? key) (name key) key))

(defn memcached! [& {:keys [host port] :or {host "localhost" port 11211}}]
  (reset! mcd (MemcachedClient. (list (InetSocketAddress. host port)))))

(defn cache-set
  ([key value]
   (cache-set key value 3600))
  ([key value expiration]
   (.set @mcd (key->str key) expiration value)))

(defn cache-get
  ([key] (cache-get key nil))
  ([key default-value]
   (if-let [res (.get @mcd (key->str key))] res default-value)))

