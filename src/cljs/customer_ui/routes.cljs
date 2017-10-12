(ns customer-ui.routes
  (:require-macros [secretary.core :refer [defroute]]
                   [re-frame.core :as rf])
  (:import goog.history.Html5History
           goog.Uri)
  (:require [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [re-frame.core :as rf]))

(defn hook-browser-navigation! []
  (let [history (doto (Html5History.)
                  (events/listen
                    EventType/NAVIGATE
                    (fn [event]
                      (secretary/dispatch! (.-token event))))
                  (.setUseFragment false)
                  (.setPathPrefix "")
                  (.setEnabled true))]
    
    (events/listen js/document "click"
                   (fn [e]
                     (. e preventDefault)
                     (let [path (.getPath (.parse Uri (.-href (.-target e))))
                           title (.-title (.-target e))]
                       (when path
                         (. history (setToken path title))))))))

(defn app-routes []
  ;;(secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute home "/" []
    (rf/dispatch [:set-active-panel :home-panel]))

  (defroute about "/about" []
    (rf/dispatch [:set-active-panel :about-panel]))

  ;; --------------------
  (hook-browser-navigation!))
