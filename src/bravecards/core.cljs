(ns bravecards.core
  (:require
   #_[om.core :as om :include-macros true]
   [reagent.core :as reagent]
   [clojure.string :as string]
   [sablono.core :as sab :include-macros true]
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

 (defcard chapters-1-2"
  ### Chapters 1 & 2

  These chapters cover environment setup and emacs - no code so on to Chapter 3!
  ")

;; =============================================================================
;; CHAPTER-3: CONTROL FLOW
;; =============================================================================

(defn hammer-trident
  "Is it a hammer or trident?"
  [hammer?]
  (if hammer?
    "By Zeus's hammer!"
    "By Aquaman's trident!"))

(defn is-odin
  "Is this odin?"
  [odin?]
  (if odin?
    "By Odin's Elbow!"))

(defn do-hammer-trident
  "Is it a hammer or trident?"
  [hammer?]
  (if hammer?
    (do (println "Successful hammer!")
        "By Zeus's hammer!")
    (do  (println "Failed trident!")
    "By Aquaman's trident!")))

(defn when-odin
  "When this is odin, do it"
  [odin?]
  (when odin?
    (println "Here's Odin!")
    "By Odin's Elbow!"))

(deftest chapter-3
  "### Chapter 3: Control Flow

  #### if-then-else, do and when

  ```
  (defn hammer-trident
    \"Is it a hammer or trident?\"
    [hammer?]
    (if hammer?
      \"By Zeus's hammer!\"
      \"By Aquaman's trident!\"))

  (defn is-odin
    \"Is this odin?\"
    [odin?]
    (if odin?
      \"By Odin's Elbow!\"))

  (defn do-hammer-trident
    \"Is it a hammer or trident?\"
    [hammer?]
    (if hammer?
      (do (println \"Successful hammer!\")
          \"By Zeus's hammer!\")
      (do  (println \"Failed trident!\")
      \"By Aquaman's trident!\")))

  (defn when-odin
    \"When this is odin, do it\"
    [odin?]
    (when odin?
      (println \"Here's Odin!\")
      \"By Odin's Elbow!\"))
  ```
  "
  (testing "testing hammer-trident"
    (is (= (hammer-trident true) "By Zeus's hammer!"))
    (is (= (hammer-trident false) "By Aquaman's trident!")))
  (testing "testing is-odin"
    (is (= (is-odin true) "By Odin's Elbow!"))
    (is (= (is-odin false) nil) "As there is no else form return nil"))
  (testing "testing do-hammer-trident"
    (is (= (do-hammer-trident true) "By Zeus's hammer!"))
    (is (= (do-hammer-trident false) "By Aquaman's trident!")))
  (testing "testing when-odin"
    (is (= (when-odin true) "By Odin's Elbow!")))
  (testing "testing nil?"
    (is (= (nil? 1) false))
    (is (= (nil? nil) true)))
  (testing "testing nil-is-falsey"
    (is (= (if nil
            "never returned"
            "nil is falsey") "nil is falsey")))
  (testing "testing or"
    (is (= (or false nil "first" "second") "first") "return first truthy")
    (is (= (or false nil (= "yes" "no")) false) "return last value"))
  (testing "testing and"
    (is (= (and :correct :ok nil false true) nil) "return first falsey")
    (is (= (and true :correct :ok "A+") "A+") "all true - return last value"))
  )

(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (.render js/ReactDOM (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html

