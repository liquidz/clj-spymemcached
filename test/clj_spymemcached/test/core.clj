(ns clj-spymemcached.test.core
  (:use [clj-spymemcached.core])
  (:use [clojure.test]))

(memcached!)

(deftest set-get-test
  (cache-set "a" "b" 1)
  (is (= "b" (cache-get "a")))

  (Thread/sleep 1000)

  (is (nil? (cache-get "a")))
  (is (= "default" (cache-get "a" "default"))))


(deftest set-get-keyword-test
  (cache-set :a "b" 1)
  (is (= "b" (cache-get :a)))

  (Thread/sleep 1000)

  (is (nil? (cache-get :a)))
  (is (= "default" (cache-get :a "default"))))


(deftest set-get-object-test
  (cache-set :ls '(1 2 3 4))
  (is (= '(1 2 3 4) (cache-get :ls)))

  (cache-set :map {:a 1 :b 2})
  (is (= {:a 1 :b 2} (cache-get :map))))
