(ns tutorial.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  )


;; hello clojure
(println "hello world")


;; values
(def rand-var 10)
(println (type rand-var))

(def a-boolean false)
(println (type a-boolean))

(def a-double 12/3)
(println (type a-double))
(println (nil? a-double))

(println (type nil))
(println (type [2 3 4]))
(println (pos? 15))
(println (neg? -1))
(println (number? 23.5))
(println (integer? 24))
(println (float? 13.3))
(println (vector? [12 4 2]))


;; formats
(def a-string "hello")
(def a-long 14)
(format "This is string %s" a-string)
(format "Leading zeros %04d" a-long)

;; REPL (CTRL + ENTER) but bind declaring line first
(println a-string)


;; strings
(def str1 "this is my 2nd string")

;; imports in Clojure
"""
(ns your-namespace
  (:require [clojure.string :as str]))
"""

;; expect to work after import
(str/blank? str1)
(str/includes? str1 "my")
(str/index-of str1 "my")
(str/split str1 #" ")
(str/join " " ["the" "new" "cheese"])
(str/replace "I am jane" #"jane" "dave")
(str/upper-case str1)
(str/lower-case str1)


;; lists
(list 12 4 5)
(println (list "Dog" 1 3.4 true))
(println (first (list 1 2 3)))
(println (list* 1 2 [3 4]))
(println cons 3 (list 1 2))


;; sets
(println (set '(3 2)))
(println (conj (set '(3 2)) 1))
(println (disj (set '(3 2)) 2))
(println (contains? (set '(3 2)) 2))


;; vectors
(println (vector 2 4))
(println (get (vector 3 2) 2))
(println (get ["abc" false 99] 0))
(println (count [1 2 3]))
(println (conj (vector 2 9) 1 1))
(println (pop (vector 1 2 3)))
(println (subvec (vector 1 3 6) 1 3))
;; (println (subvec (vector 1 3 6) 1 6)) <= causes error


;; maps
(println (hash-map "name" "dave" "age" "24"))
(println (sorted-map 3 42 2 "bingo" 23 "name"))
(println (get (hash-map "name" "dave" "employed" true) "name"))
(println (find (hash-map "name" "dave" "employed" true) "name"))
(println (contains? (hash-map "name" "dave" "employed" true) "employed"))
(println (keys (hash-map "name" "dave" "employed" true)))
(println (vals (hash-map "name" "dave" "employed" true)))
(println (merge-with + (hash-map "name" "dave") (hash-map "employed" true)))


;; atoms
(def counter (atom 0))
(swap! counter inc)
(println @counter)

;; watchers
#_{:clj-kondo/ignore [:unused-binding]}
(defn my-watcher [key atom old-state new-state]
  (println "Counter has changed from" old-state "to" new-state))

;; atoms and watchers all together
(println my-watcher)
(add-watch counter :my-key my-watcher)
(swap! counter inc)
(remove-watch counter :my-key)


;; agents
(def my-agent (agent 0))
(send my-agent inc)
(println my-agent)

;; combining agents
(def another-agent (agent 10))
(defn combine-agents [a1 a2]
  (send a1 + @a2))
(combine-agents my-agent another-agent)


;; math
(Math/abs -10)
(Math/sqrt 3)
(Math/floor 1.2)
(Math/ceil -2.5)
(Math/exp 1)
(Math/log 2.4)
(Math/log10 100)
(Math/max 2 3)
(Math/min 1 5)
(Math/pow 2 16)
(Math/PI)


;; functions
(defn say-hello
  [name]
  (println "hello" name))

(defn get-sum
  [x y]
  (+ x y))

(defn hello-all
  [& names]
  (map say-hello names))

(hello-all ["adam" "jacob"])
(get-sum 2 3)


;; operators
(= 3 4)
(<= 2 2)
(not= 2 (+ 1 1))
(and true true)
(or true false)
(not false)


;; conditionals
(defn can-vote
  [age]
  (if (> age 18) println "you can vote")
  (println "you can not vote")
  )

(can-vote 19)

(defn can-do-more
  [age]
  (if (>= age 18)
    (do (println "you can drive")
        (println "you can vote")
        )
    (println "you can not")
    )  
  )

(can-do-more 21)

(defn when-ex
  [tof]
  (when tof
    (println "1st move")
    (println "2nd move")
    )
  )

(when-ex 9)

(defn what-grade
  [n]
  (cond
    (< n 5) "preschool"
    (= n 5) "kindergarten"
    (and (> n 5) (<= n 18)) (format "grade %d" (- n 5))
    :else "college"
    )
  )

(what-grade 12)


;; loops
(defn one-to-x
  [x]
  #_{:clj-kondo/ignore [:inline-def]}
  (def i (atom 1))
  (while (<= @i x)
    (do
      (println @i)
      (swap! i inc)
      )
    )
  )

(one-to-x 4)

(defn dbl-to-x
  [x]
  (dotimes [i x]
    (println (* i 2))
    )
  )

(dbl-to-x 6)

(defn triple-to-x
  [x y]
  (loop [i x]
    (when (< i y)
      (println (* i 3))
      (recur (+ i 1))
      )
    )
  )

(triple-to-x 3 12)

(defn print-list
  [& nums]

  (doseq [x nums]
    (println x)
    )
  )

(print-list 6 7 8)


;; file i/o
;; import from Java
"""
   (use 'clojure.java.io)   
"""

;; expect to work after import
"""
(defn write-to-file
  [file text]
  (with-open [wrtr (writer file)]
    (.write wrtr text)
    )
  )
"""

(defn read-from-file
  [file]
  (try
    (println (slurp file))
    (catch Exception e
      (println "Error:" (.getMessage e))
      )
    )
  )

;; expect to work after import
"""
(defn append-to-file
  [file text]
  (with-open [wrtr (writer file :append :true)]
    (.write wrtr text)
    )
  )

 (defn read-line-from-file
   [file]
   (with-open [rdr (reader file)]
     (doseq [line (line-seq rdr)]
       (println line)
       )
     )
   )
"""

;; destructoring
(defn destruct
  []
  #_{:clj-kondo/ignore [:inline-def]}
  (def vectVals [1 2 3 4])
  (let [[one two & the-rest] vectVals]
    (println one two the-rest)
    )
  )
 
 (destruct)

 
 ;; struct maps
 (defrecord Customer [Name Phone])
 
 (defn struct-map-ex
   []
   (let [cust1 (->Customer "Doug" 4125551212)
         cust2 (map->Customer {:Name "Sally" :Phone 77777777})]
     (println cust1)
     (println (:Name cust2))))
 
 (struct-map-ex)


;; anonymous functions
(map #(* % 3) (range 1 10))
(#(* %1 %2) 2 3)


;; clojures
(defn custom-multiplier
  [mult-by]
  #(* % mult-by)
  )

(def mult-by-3 (custom-multiplier 3))
(mult-by-3 3)


;; filtering lists
(take 2 [1 2 3])
(drop 1 [1 6 2])
(take-while neg? [-1 0 1])
(drop-while odd? [-2 1])
(filter #(> % 2) [1 2 3 4])


;; macros
(defmacro discount
  ([cond dist1 dist2]
   (list `if cond dist1 dist2)
   )
  )

(discount (> 25 65) (println "10% Off")
          (println "Full Price")
          )

(defmacro reg-math
  [calc]
  (list (second calc) (first calc) (nth calc 2))
  )

(reg-math [2 + 5])

(defmacro do-more
  [cond & body]
  (list `if cond (cons 'do body))
  )

(do-more (< 1 2) (println "hello")
         (println "hello again"))

(defmacro do-more-2
  [cond & body]
  `(if ~cond (do ~@body))
  )

(do-more-2 (< 1 2) (println "hello")
           (println "hello again"))