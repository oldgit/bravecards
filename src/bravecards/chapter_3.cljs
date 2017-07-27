(ns bravecards.chapter-3
  (:require
   #_[om.core :as om :include-macros true]
   [reagent.core :as reagent]
   [clojure.string :as string]
   [sablono.core :as sab :include-macros true]
   [cljs.test :as t :include-macros true :refer-macros [testing is]])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

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

(defn error-message
  "Return error-message string based on severity-keyword"
  [severity-keyword]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity-keyword :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(deftest chapter-3-control-flow "
  ### Chapter 3: Control Flow

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

  (defn error-message
   \"Return error-message string based on severity-keyword\"
   [severity-keyword]
   (str \"OH GOD! IT'S A DISASTER! WE'RE \"
        (if (= severity-keyword :mild)
          \"MILDLY INCONVENIENCED!\"
          \"DOOOOOOOMED!\")))
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
  (testing "testing error-message"
    (is (= (error-message :mild) "OH GOD! IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"))
    (is (= (error-message :bad) "OH GOD! IT'S A DISASTER! WE'RE DOOOOOOOMED!"))))

(defonce churchill {:name {:first "Winston" :middle "Leonard" :last "Spencer-Churchill"}
                    :born {:day 30 :month 11 :year 1874}})

(deftest chapter-3-data-structures "
  ### Chapter 3: Data Structures

  #### Numbers

  Clojurescript supports integers and floats but does not have a ratio type, e.g. 1/3, like Clojure.

  #### Strings

  Concat strings with the `str` function.

  #### Maps

  ```
  {:x 1 :y 2 z: 3}
  (defonce churchill {:name {:first \"Winston\" :middle \"Leonard\" :last \"Spencer-Churchill\"}
                      :born {:day 30 :month 11 :year 1874}})
  ```

  #### Vectors, Lists and Sets

  ```
  [3 2 1]
  '(1 2 3 4)
  #{:x :y :z}
  ```
  "
  (testing "testing cljs-has-no-ratios-like-clojure"
    (is (= (/ 1 2) 0.5) "1 divided by 2 is 0.5")
    (is (= (js/Math.floor (/ 200 3)) 66) "use js/Math.floor")
    (is (= (js/Math.round (/ 200 3)) 67) "use js/Math.round"))
  (testing "testing str-concat"
    (is (= (str "1" "-2-" "3") "1-2-3")))
  (testing "testing map-get"
    (is (= (get {0 0 "a" 0 "b" 1 :c "text"} "b") 1) "any types can be used as key/value's")
    (is (= (get {:a 0 :b 1} :b) 1) "typically use keywords for keys")
    (is (= (get {:a 0 :b 1} :c) nil) "return nil if key not found")
    (is (= (get {:a 0 :b 1} :c 3) 3) "return default value if key not found"))
  (testing "testing map-get-in"
    (is (= (get-in churchill [:born :year]) 1874)))
  (testing "testing map-get-by-key"
    (is (= ({:name "Human Coffeepot"} :name) "Human Coffeepot")))
  (testing "testing keyword-in-map"
    (is (= (:name {:name "Human Coffeepot"}) "Human Coffeepot")))
  (testing "testing keyword-in-map-with default"
    (is (= (:pot {:name "Human Coffeepot"} "defaultPot" ) "defaultPot")))
  (testing "testing vector-get"
    (is (= (get [3 2 1] 0) 3)))
  (testing "testing vector-conj"
    (is (= (conj [1 2 3] 4) [1 2 3 4]) "conj adds to end of vector"))
  (testing "testing list-nth"
    (is (= (nth '(:a :b :c) 0) :a)))
  (testing "testing list-conj"
    (is (= (conj '(:b :c :d) :a) '(:a :b :c :d)) "conj adds to start of list"))
  (testing "testing hash-set-conj"
    (is (= (conj #{:b :c :d} :a) #{:a :b :c :d}))
    (is (= (conj #{:b :c :d} :b) #{:b :c :d})))
  (testing "testing hash-set-contains"
    (is (= (contains? #{:b :c :d} :a) false))
    (is (= (contains? #{:b :c :d} :b) true)))
  (testing "testing hash-set-get"
    (is (= (get #{:b :c :d} :b) :b))
    (is (= (get #{:b :c :d} :a) nil)))
  (testing "testing keyword-in-hash-set"
    (is (= (:b #{:b :c :d}) :b))
    (is (= (:a #{:b :c :d}) nil))))

 (defn too-enthusiastic
    "Return a cheer that might be a bit too enthusiastic"
    [name]
    (str "OH. MY. GOD! " name "! YOU ARE MOST DEFINITELY LIKE THE BEST!"))

 (defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
     (str "I " chop-type " chop " name "! Take that!"))
  ([name]
     (x-chop name "karate")))

(defn favourite-things
  [name & things]
  (str "Hi, " name ", here are my favourite things: "
       (string/join ", " things)))

;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(defn announce-treasure-location-lat
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  lat)

(defn announce-treasure-location-lng
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  lng)

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  {:location treasure-location})

(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(deftest chapter-3-functions "
  ### Chapter 3: Functions

  A function has:

    * a function name
    * an optional docstring describing the function
    * Parameters listed in brackets
    * a function body

  For example:

  ```
  (defn too-enthusiastic
    \"Return a cheer that might be a bit too enthusiastic\"
    [name]
    (str \"OH. MY. GOD! \" name \"! YOU ARE MOST DEFINITELY LIKE THE BEST!\"))
  ```

  #### Parameters, Arity and rest parameter

  Clojure functions can be defined with zero or more parameters.
  The values you pass to functions are called *arguments*, and the arguments
  can be of any type. The number of parameters is the function's *arity*.

  Functions also support *arity overloading*. This means that you can define a
  function so a different function body will run depending on the arity.
  Arity overloading is one way to provide default values for arguments.

  ```
  (defn x-chop
    \"Describe the kind of chop you're inflicting on someone\"
    ([name chop-type]
      (str \"I \" chop-type \" chop \" name \"! Take that!\"))
    ([name]
     (x-chop name \"karate\")))
  ```

  Clojure also allows you to define variable-arity functions by including a rest parameter,
  as in \"put the rest of these arguments in a **list** with the following name.\"
  The rest parameter is indicated by an ampersand, &.
  You can mix rest parameters with normal parameters, but the rest parameter has to come last.

  ```
  (defn favourite-things
    [name & things]
    (str \"Hi, \" name \", here are my favourite things: \"
       (string/join \", \" things)))
  ```

  #### Destructuring

  The basic idea behind destructuring is that it lets you concisely bind names to values within a collection.

  ```
  ;; Return the first element of a collection
  (defn my-first
    [[first-thing]] ; Notice that first-thing is within a vector
    first-thing)
  ```

  Above, the `my-first` function associates the symbol `first-thing` with the first element
  of the vector that was passed in as an argument. You tell `my-first` to do this by placing
  the symbol `first-thing` within a vector. When destructuring a vector or list,
  you can name as many elements as you want and also use rest parameters.

  You can also destructure maps. In the same way that you tell ClojureScript to destructure
  a vector or list by providing a vector as a parameter, you destructure maps by
  providing a map as a parameter.

  ```
  (defn announce-treasure-location-lat
    [{lat :lat lng :lng}]
    (println (str \"Treasure lat: \" lat))
    (println (str \"Treasure lng: \" lng))
    lat)

  (defn announce-treasure-location-lng
    [{:keys [lat lng]}]
    (println (str \"Treasure lat: \" lat))
    (println (str \"Treasure lng: \" lng))
    lng)

  (defn receive-treasure-location
    [{:keys [lat lng] :as treasure-location}]
    (println (str \"Treasure lat: \" lat))
    (println (str \"Treasure lng: \" lng))
    {:location treasure-location})
  ```

  #### Anonymous Functions

  Use `fn`:

  ```
  ((fn [x] (* x 3)) 8)
  ; => 24
  ```

  Use `#`:

  ```
  (#(* % 3)) 8)
  ; => 24
  ```

  The percent sign, `%`, indicates the argument passed to the function.
  If your anonymous function takes multiple arguments, you can distinguish them
  like this: `%1`, `%2`, `%3` etc.

  ```
  (#(str %1 \" and \" %2) \"cornbread\" \"butter beans\")
  ; => \"cornbread and butter beans\"
  ```

  #### Returning Functions

  Functions can return other functions. The returned functions are *closures*,
  which means that they can access all the variables that were in scope when the
  function was created.

  ```
  (defn inc-maker
    \"Create a custom incrementor\"
    [inc-by]
    #(+ % inc-by))

  (def inc3 (inc-maker 3))

  (inc3 7)
  ; => 10
  ```
  "
  (testing "testing too-enthusiastic"
    (is (= (too-enthusiastic "Donald") "OH. MY. GOD! Donald! YOU ARE MOST DEFINITELY LIKE THE BEST!")))
  (testing "testing x-chop"
    (is (= (x-chop "Kanye West" "slap") "I slap chop Kanye West! Take that!"))
    (is (= (x-chop "Kanye East") "I karate chop Kanye East! Take that!")))
  (testing "testing favourite-things"
    (is (= (favourite-things "Doreen" "gum" "shoes" "kara-te")
           "Hi, Doreen, here are my favourite things: gum, shoes, kara-te")))
  (testing "testing my-first"
    (is (= (my-first ["oven", "bike", "war-axe"]) "oven")))
  (testing "testing treasure-location"
    (is (= (announce-treasure-location-lat {:lat 28.22 :lng 81.33}) 28.22))
    (is (= (announce-treasure-location-lng {:lat 28.22 :lng 81.33}) 81.33))
    (is (= (receive-treasure-location {:lat 28.22 :lng 81.33})
           {:location {:lat 28.22 :lng 81.33}})))
  (testing "testing anonymous fn"
    (is (= (#(str %1 " and " %2) "cornbread" "butter beans")
           "cornbread and butter beans")))
  (testing "testing inc-maker"
    (is (= ((inc-maker 3) 7) 10))))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(better-symmetrize-body-parts asym-hobbit-body-parts)
