(ns clj-spymemcached.core
  (:import
    [net.spy.memcached MemcachedClient]
    [java.net InetSocketAddress]))

(def mcd (atom nil))

(defn- key->str [key]
  (if (keyword? key) (name key) key))

(defn memcached! [& {:keys [host port] :or {host "localhost" port 11211}}]
  (reset! mcd (MemcachedClient. (list (InetSocketAddress. host port)))))

(defn cache-set [key value & {:keys [expiration] :or {expiration 3600}}]
  (.set @mcd (key->str key) expiration value))

(defn cache-get [key & {:keys [default] :or {default nil}}]
  (if-let [res (.get @mcd (key->str key))] res default))

