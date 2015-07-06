(ns hyperbolic.widget
  (:require
    [reagent.core :as r]))

(defn get-hyper [[x y z]]
  (let [alpha-sq (+ (- (* x x)) (- (* y y)) (* z z))
        alpha (.sqrt js/Math alpha-sq)]
    [(/ x alpha) (/ y alpha) (/ z alpha)]))

(defn node2str [node]
  (str (+ 500 (* 100 (nth node 0))) "," (+ 500 (* 100 (nth node 1)))))

(defn line [start end]
  (let [[sx sy sz] start
        [ex ey ez] end
        dx (- ex sx)
        dy (- ey sy)
        len (.sqrt js/Math (+ (* dx dx) (* dy dy)))
        n (.ceil js/Math (/ len 0.01))
        mid (fn [k]
              (let [t (/ k n)
                    rx (+ (* sx (- 1 t)) (* ex t))
                    ry (+ (* sy (- 1 t)) (* ey t))
                    rz (+ (* sz (- 1 t)) (* ez t))]
                (get-hyper [rx ry rz])))
        nodes (map mid (range (inc n)))]
    (apply str (map #(str "L" (node2str %)) nodes))))

(defn hyperbolic-path [nodes]
  (let [f (str "M" (node2str (first nodes)))
        r (map (partial apply line) (partition 2 1 nodes))
        d (apply str (concat [f] r))]
    [:path {:d d :style {"hyperbolic-path" 1} :key "xx"}]))

(defn hyperbolic-plane [figures]
  [:svg {:width 1000 :height 1000}
   [:g (map hyperbolic-path figures)]])
