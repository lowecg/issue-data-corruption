(ns issue-data-corruption.certificate.views.certificate
  (:require
    [reagent-material-ui.core.grid :refer [grid]]
    [reagent-material-ui.core.button :refer [button]]
    [reagent-material-ui.core.text-field :refer [text-field]])
  (:require
    [reagent-material-ui.styles :as styles]))

(defn event-value
  [^js/Event e]
  (.. e -target -value))

(defn custom-styles [_]
  {:container {:width "100%"}
   :field     {:width 300}})

(defn panel [{:keys [classes, get-props]}]
  (let [{:keys [certificate-id, form-props, test-props]} (get-props)]
    (println "TEST-PROPS")
    (cljs.pprint/pprint test-props)

    (let [{:keys [values
                  set-handle-change]} form-props
          k-certificate-id (keyword certificate-id)]

      (println "VALUES (as received in component)")
      (cljs.pprint/pprint values)

      (println "keyword certificate-id" k-certificate-id)
      (println "certificate-id" certificate-id)
      (println "DESCRIPTION" (get-in values [:certifications k-certificate-id :description]))

      [grid {:container   true
             :class-name  (:container classes)
             :justify     "space-between"
             :align-items "center"
             :direction   "column"
             :spacing     2}
       [grid {:item true}
        [text-field {:id         (str "certificate-description-" certificate-id)
                     :label      "Description"
                     :size       "small"
                     :required   true
                     :class-name (:field classes)
                     :value      (get-in values [:certifications k-certificate-id :description])
                     :on-change  (fn [e]
                                   (let [params {:value (event-value e)
                                                 :path  [:certifications k-certificate-id :description]}
                                         result (set-handle-change params)]
                                     (println "SET-HANDLE-CHANGE RESULT:")
                                     (cljs.pprint/pprint (get-in result [:values :certifications]))
                                     result))}]]

       [grid {:item true}
        [button {:type "submit"} "Save"]]])))


(def ^:private with-custom-styles (styles/with-styles custom-styles))

(def certification (with-custom-styles panel))

