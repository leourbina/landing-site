(ns customer-ui.views
  (:require [re-frame.core :as rf]
            [re-com.core :as rc]
            [customer-ui.layout :refer [page-layout]]))

(defn home-panel []
  [page-layout
   [rc/v-box
    :children
    [[:h1 {:class "f1"} "Hey nice to meet you! 👋🏽"]
     [:p {:class "f4"} "I'm Leo Urbina. I’m a " [:a {:href "https://www.linkedin.com/in/leourbina"} "full-stack engineer "] "and enjoy math so much I got a " [:a {:href "https://math.mit.edu/"} "degree "] "in it. I love building things that have an impact on people’s lives, enabling companies and teams achieve their goals, and helping others achieve their full potential. I care about education, transportation and health care, and finding ways to use technology to improve these areas."]
     [:p {:class "f4"} "Currently I’m running my own consulting company, helping other companies achieve business success with the help of technology."]
     [:p {:class "f4"} "If any of this sounds good to you, " [:a {:href "mailto:me@leourbina.com"} "let’s talk"] "."]]]])

(defn project-panel []
  [page-layout
   [rc/v-box
    :children
    [[:h1 {:class "f1"} "Projects"]
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
