(ns boids
  (:require [strokes :refer [d3 timer category10]]))

(strokes/bootstrap)

(def width 960)
(def height 500)

(def boids (atom [
  {:id "a" :x 200 :y 200}
  {:id "b" :x 200 :y 300}
  {:id "c" :x 300 :y 200}
  {:id "d" :x 400 :y 300}
  {:id "f" :x 500 :y 300}]))

(def colorfn (category10))

(def svg (-> d3 (.select "body") (.append "svg")
      (.attr {:width width :height height})))

(defn draw-boids []
  (let [circles (-> svg (.selectAll "circle") (.data @boids))]
    (-> circles (.enter)
      (.append "circle")
      (.style "fill" #(colorfn %2)))

    (-> circles
      (.attr {:transform #(str "translate(" (:x %) "," (:y %) ")")
              :r 10}))))

(defn update-one-boid [b]
  (let [brownianize #(+ % -3 (rand-int 7))
        newx (brownianize (:x b))
        newy (brownianize (:y b))]
    (merge b {:x newx :y newy})))

(defn update-boids []
  (swap! boids #(mapv update-one-boid %)))

(timer (fn []
  (update-boids)
  (draw-boids)
  false))
