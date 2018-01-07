(ns customer-ui.layout
  (:require [re-com.core :as rc]
            [customer-ui.routes :as routes]
            [customer-ui.utils :as utils]))

(defn nav-button
  [title url target]
  [rc/box
   :class (utils/classnames "nav-button"
            {:active (= url (.-pathname (.-location js/window)))})
   :align :center
   :child
   [rc/hyperlink-href
    :label title
    :href url
    :target target]])


(defn profile []
  [rc/h-box
   :class "profile"
   :children
   [[:img
     {:src "/images/leo-profile-small.png"
      :class "profile-image"}]
    [rc/v-box
     :children
     [[:h2
       {:class "f2 name"}
       "Leo Urbina"]
      [rc/title
       :label "I like to build things"
       :class "f6"]
      [rc/h-box
       :class "social-links"
       :width "5rem"
       :justify :between
       :children
       [[rc/hyperlink-href
         :href "https://twitter.com/leo_urbina"
         :target "_blank"
         :label
         [:i {:class "fa fa-twitter"}]]

        [rc/hyperlink-href
         :href "https://www.linkedin.com/in/leourbina/"
         :target "_blank"
         :label
         [:i {:class "fa fa-linkedin-square"}]]

        [rc/hyperlink-href
         :href "mailto:me@leourbina.com"
         :target "_blank"
         :style {:margin "-1px 0 0 2px"}
         :label
         [:i {:class "fa fa-envelope"}]]]]]]]])

(defn header []
  [rc/h-box
   :class "main-header"
   :children
   [[profile]
    [rc/gap :size "3"]
    [rc/h-box
     :class "nav"
     :children
     [[nav-button "About Me" (routes/home)]
      #_[nav-button "Projects" (routes/projects)]
      [nav-button "Blog" "https://blog.leourbina.com" "_blank"]]]]])

(defn page-layout [contents]
  [rc/v-box
   :class "main-content"
   :size "100vh"
   :children
   [[header]
    [rc/box :child contents :class "content-body"]]])
