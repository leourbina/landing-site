(ns customer-ui.views
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [customer-ui.layout :refer [page-layout]]))

(defn home-panel []
  [page-layout
   [rc/v-box
    :children
    [[:h1 {:class "f1"} "Nice meeting you,"]
     [:p "I'm Leo Urbina. I love building products that have an impact in peoples lives. Whether at a startup or a large company, I love helping teams grow and achieve their goals."]
     [:p "Previously I was a tech lead at Drift, HubSpot, and the first engineer at BitSight."]
     [:p "Currently I'm starting my own consulting agency, helping companies navigate the technical landscape, and building world class products."]
     ]]])

(defn project-panel []
  [page-layout "These are my projects"])

;; main
(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :projects-panel [project-panel]
    [:div "Page not found"])) ;; TODO: add real 404

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (rf/subscribe [:active-panel])]
    (fn []
      [rc/v-box
       :height "100%"
       :align :center
       :children [[panels @active-panel]]])))

