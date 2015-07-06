(ns ^:figwheel-always hyperbolic.core
  (:require
    [reagent.core :as reagent :refer [atom]]
    [hyperbolic.tiling :as tiling]
    [hyperbolic.widget :as widget]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(reagent/render-component (widget/hyperbolic-plane [(tiling/heptagon-points)])
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

