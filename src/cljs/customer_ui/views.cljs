(ns customer-ui.views
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [customer-ui.layout :refer [page-layout]]))

(defn home-panel []
  [page-layout
   [rc/v-box
    :children
    [[:h1 {:class "f1"} "Hey, nice to meet you"]
     [:p {:class "f4"} "I'm Leo Urbina. I'm a full-stack engineer, with a nack for building products that have an impact on people's lives. Whether at a startup or a large company, I love helping teams grow and achieve their goals."]
     [:p {:class "f4"} "huh"]
     [:p "In the past I've been at Drift, HubSpot and BitSight."]]]])

(defn project-panel []
  [page-layout
   [rc/v-box
    [:children
     [:h1 {:class "f1"} "Projects"]
     [:p "Here are some of my projects"]]]])

(defn not-found-panel []
  [page-layout
   [:div
    [:h1 {:class "f1"} "Hmm, this isn't right"]
    [:p "We couldn't find the page you're looking for. No fret! Just click one of the links above ☝"]]])

;; main
(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :projects-panel [project-panel]
    
    [not-found-panel])) ;; TODO: add real 404

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (rf/subscribe [:active-panel])]
    (fn []
      [rc/v-box
       :height "100%"
       :align :center
       :children [[panels @active-panel]]])))

