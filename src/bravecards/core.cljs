(ns bravecards.core
  (:require
   #_[om.core :as om :include-macros true]
   [reagent.core :as reagent]
   [clojure.string :as string]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defn time-str
  "Return just the time as a String from a js/Date"
  [jsDate]
  (-> jsDate .toTimeString (string/split " ") first))

(defonce ratom-time
  (let [a (reagent/atom (time-str (js/Date.)))]
    (js/setInterval #(reset! ratom-time (time-str (js/Date.))) 10000)
    a))

(defonce atom-time
  (let [a (atom (time-str (js/Date.)))]
    (js/setInterval #(reset! atom-time (time-str (js/Date.))) 1000)
    a))

#_(defcard time-atom-edn-debug
  "### defcard edn debug display of cljs atom-time - updates every sec"
  atom-time
  {}
  {:history false})

(defn time-element
  "Return a react element vector with most recent value of time-atom (deref)"
  [time-atom]
  [:div "Time: " @time-atom])

(defcard cljs-atom-time
  "### cljs atom used to display time - updates every sec"
  (fn [atom-time _]
    (sab/html (time-element atom-time)))
  atom-time
  {:inspect-data false
   :history false})

#_(defcard reagent-atom-time
  "### reagent atom used to display time - updates every 10 secs"
  (fn [ratom-time _]
    (reagent/as-element [time-element ratom-time]))
  ratom-time
  {:history false})

(defcard first-card
  (sab/html
   [:div [:h1 "Jon, This is your first devcard!"]]))

(defcard second-card "
# Markdown example

   Some serious md...

   * one
   * two
   * three
   * four
 ")

(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (.render js/ReactDOM (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html

