(ns hyperbolic.mat)

(defn sin [a] (.sin js/Math a))
(defn cos [a] (.cos js/Math a))
(defn sinh [a] (.sinh js/Math a))
(defn cosh [a] (.cosh js/Math a))
(def PI (.-PI js/Math))

(defn pos2ind [r c] (+ c (* 3 r)))

(defn mat-+ [m1 m2] (for [r [0 1 2] c [0 1 2]]
                  (+
                    (nth m1 (pos2ind r c))
                    (nth m2 (pos2ind r c)))))

(defn mat-- [m1 m2] (for [r [0 1 2] c [0 1 2]]
                  (-
                    (nth m1 (pos2ind r c))
                    (nth m2 (pos2ind r c)))))

(defn mat-* [m1 m2] (for [r [0 1 2] c [0 1 2]]
                  (apply + (map #(*
                              (nth m1 (pos2ind r %1))
                              (nth m2 (pos2ind %1 c)))
                            [0 1 2]))))

(defn vec-* [m1 v] (vec (for [r [0 1 2]]
                  (apply + (map #(*
                              (nth m1 (pos2ind r %1))
                              (nth v %1))
                            [0 1 2])))))

(def eye [1 0 0 0 1 0 0 0 1])

(defn rotz [alpha] [(cos alpha) (sin alpha) 0 (- (sin alpha)) (cos alpha) 0 0 0 1])
(defn hrotx [alpha] [1 0 0 0 (cosh alpha) (sinh alpha) 0 (sinh alpha) (cosh alpha)])
(defn hroty [alpha] [(cosh alpha) 0 (sinh alpha) 0 1 0 (sinh alpha) 0 (cosh alpha)])
