(ns hyperbolic.tiling
  (:require
    [hyperbolic.mat :as m]))

(def transform-matrix (m/mat-* (m/rotz (/ m/PI 3)) (m/hrotx (/ m/PI 5.6))))

(print (m/rotz (/ m/PI 2)))
(print transform-matrix)

(defn heptagon-points
  ([k cur result]
   (if (> k 0)
      (let [ncur (m/vec-* transform-matrix cur)]
        (heptagon-points (dec k) ncur (conj result cur)))
      result))
  ([] (heptagon-points 8 [0 0 1] [])))

(print (heptagon-points))
