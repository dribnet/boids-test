(defproject boids-test "0.1.0-SNAPSHOT"
  :plugins [[lein-cljsbuild "0.3.3"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1913"]
                 [net.drib/strokes "0.5.1"]]
  :cljsbuild {:builds [{:source-paths ["src"]
                        :compiler {:output-to "public/boids.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})
