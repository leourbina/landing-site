(ns customer-ui.utils)

(defmulti create-class type)
(defmethod create-class js/String [class]
  class)
(defmethod create-class PersistentArrayMap [class-map]
  (let [classes (for [[key value] class-map :when (boolean value)] (name key))]
    classes))

(defn classnames [& args]
  (->> args
      (map create-class)
      flatten
      (clojure.string/join " ")))

