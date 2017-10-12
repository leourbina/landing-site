(ns customer-ui.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [customer-ui.routes :as routes]))

;; home
(defn home-title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/title
       :label (str "Hello from " @name ". This is the Home Page.")
       :level :level1])))

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href (routes/about)])

(defn home-panel []
  [re-com/v-box
   :height "100%"
   :class "app"
   :children
   [[:div
     "Hello World"
     [home-title]]]
])

;; about
(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "go to Home Page"
   :href (routes/home)])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title] [link-to-home-page]]])

;; main
(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [[panels @active-panel]]])))
