(ns bravecards.about
  (:require
   #_[om.core :as om :include-macros true]
   [reagent.core :as reagent]
   [clojure.string :as string]
   [sablono.core :as sab :include-macros true]
   ;; add chapter cljs files here:
   [bravecards.chapter-3]
   [cljs.test :as t :include-macros true :refer-macros [testing is]])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defcard bravecards"
  # Bravecards: [devcards](https://github.com/bhauman/devcards) for [Clojure for the Brave and True](http://www.braveclojure.com/introduction)

  Using Bruce Hauman's great [devcards](https://github.com/bhauman/devcards) to work through Daniel Higginbotham's enjoyable book: [Clojure for the Brave and True](http://www.braveclojure.com/introduction).

  **Why?** - well more the question: **Why not?**

  [ClojureScript](https://clojurescript.org/) is Lisp elegance with immutable, persistent data structures for the web...

  'Most Excellent' as [Ted](http://www.imdb.com/title/tt0096928/?ref_=ttpl_pl_tt) would say ;-)

  Amusing [xkcd](https://xkcd.com/)'s take on it:

  ![alt lisp cycles comic](https://imgs.xkcd.com/comics/lisp_cycles.png 'Lisp xkcd')

  For those vimsters out there, take a look at `figwheel-vim.md` and `figwheel-spacemacs.md` to integrate with Bruce's
  invaluable [figwheel](https://github.com/bhauman/lein-figwheel)
  ")

 (defcard chapters"
  ### Chapters 1 & 2

  These chapters cover environment setup and emacs - no code so on to Chapter 3!

  ### Chapter 3

  Control flow and data structures is [here](#!/bravecards.chapter_3).

  ")

(defn time-str
  "Return just the time as a String from a js/Date"
  [jsDate]
  (-> jsDate .toTimeString (string/split " ") first))

;; form to call time-str to test your editor eval works
#_(time-str (js/Date.))

;; a reagent atom to store the time string
;; See: http://reagent-project.github.io/docs/master/reagent.core.html#var-atom
;; 'A reagent atom is like a core atom except it keeps track of derefs.'
(defonce ratom-time
  (let [a (reagent/atom (time-str (js/Date.)))]
    (js/setInterval #(reset! ratom-time (time-str (js/Date.))) 10000)
    a))

;; a standard clojure.core/atom to store the time string
;; See: http://cljs.github.io/api/cljs.core/atom
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
  "Return a Hiccup elements vector with most recent value of time-atom (deref)"
  [time-atom]
  [:div "Time: " @time-atom])

(defcard cljs-atom-time
  "### cljs atom used to display time - updates every sec
  (kicking the tyres of defcard, atom, reagent rendering...)
  "
  (fn [atom-time _]
    (sab/html (time-element atom-time)))
  atom-time
  {:inspect-data false
   :history false})

;; Uncomment to see reagent called directly
#_(defcard reagent-atom-time
  "### reagent atom used to display time - updates every 10 secs"
  (fn [ratom-time _]
    (reagent/as-element [time-element ratom-time]))
  ratom-time
  {:history false})

(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (.render js/ReactDOM (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html

