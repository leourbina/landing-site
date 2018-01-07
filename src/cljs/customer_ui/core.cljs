(ns customer-ui.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [customer-ui.events]
            [customer-ui.subs]
            [customer-ui.routes :as routes]
            [customer-ui.views :as views]
            [customer-ui.config :as config]))

(enable-console-print!)

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
