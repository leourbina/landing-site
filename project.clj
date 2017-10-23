(defproject customer-ui "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.1"]
                 [org.clojure/core.async "0.2.391"]
                 [venantius/accountant "0.2.0"]
                 [re-com "2.1.0"]
                 [secretary "1.2.3"]
                 [garden "1.3.2"]
                 [ns-tracker "0.3.0"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-scss  "0.3.0" ]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"
                                    "resources/public/css"]

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler customer-ui.server/handler}

  :hooks [leiningen.scss
          leiningen.cljsbuild]
  
  :scss {:builds
         {:dev        {:source-dir "resources/scss/"
                       :dest-dir   "resources/public/css/"
                       :executable "sassc"
                       :args       ["-m" "-I" "scss/" "-t" "nested"]}
          :prod {:source-dir "resources/scss/"
                 :dest-dir   "resources/public/css/"
                 :executable "sassc"
                 :args       ["-I" "scss/" "-t" "compressed"]
                 :jar        true}
          :test       {:source-dir "tests/scss/"
                       :dest-dir   "/tmp/test/css/"
                       :executable "sassc"
                       :args       ["-m" "-I" "scss/" "-t" "nested"]}}}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.4"]
                   [figwheel-sidecar "0.5.13"]
                   [com.cemerick/piggieback "0.2.2"]
                   [re-frisk "0.5.0"]
                   [doo "0.1.8"]]

    :plugins      [[lein-figwheel "0.5.13"]
                   [lein-doo "0.1.8"]]}}

  :cljsbuild
  {:builds
   {:dev
    {:source-paths ["src/cljs"]
     :figwheel     {:on-jsload "customer-ui.core/mount-root"}
     :compiler     {:main                 customer-ui.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload
                                           re-frisk.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    :prod
    {:source-paths ["src/cljs"]
     :compiler     {:main            customer-ui.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    :test
    {:source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          customer-ui.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"
                    :optimizations :none}}
    }})
