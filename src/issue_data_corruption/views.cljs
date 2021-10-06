(ns issue-data-corruption.views
  (:require
    [re-frame.core :as rf]
    [fork.re-frame :as fork])
  (:require
    [reagent-material-ui.core.box :refer [box]]
    [reagent-material-ui.core.container :refer [container]]
    [reagent-material-ui.core.css-baseline :refer [css-baseline]]
    [reagent-material-ui.styles :refer [with-styles, create-mui-theme, theme-provider]])
  (:require
    [issue-data-corruption.theme :refer [my-theme]]
    [issue-data-corruption.certificate.views.certificate :refer [certification]]))


(defn custom-styles [{:keys [spacing]}]
  {:root       {:display "flex"}
   :main       {:flex-grow 1
                :height    "100vh"
                :overflow  "auto"}
   :container  {:padding-top    (spacing 12)
                :padding-bottom (spacing 4)}
   :main-panel {:padding        (spacing 2)
                :display        "flex"
                :overflow       "hidden"
                :flex-direction "column"
                :box-shadow     "0px 0px 25px -6px rgba(0,0,0,0.3)"
                :border-radius  20
                :width          "100%"
                :min-height     500}})

(def with-custom-styles (with-styles custom-styles))

(defn panel [{:keys [classes]}]
  [:div {:class-name (:root classes)}
   [:main {:class-name (:main classes)}
    [container {:max-width  false
                :class-name (:container classes)}

     [box {:class-name (:main-panel classes)}
      [fork/form {:path              [:form :list-products]
                  :keywordize-keys   true
                  :prevent-default?  true
                  :clean-on-unmount? true
                  :initial-values    {:certifications {:cdf53254-ed48-4e91-b3cf-4fd03dc7d6e0 {:description "Initial Value"}}}
                  :on-submit         #(rf/dispatch [:issue-data-corruption.events/submit %])}
       (fn [{:keys [form-id
                    values
                    dirty
                    handle-submit] :as form-props}]
         [:form
          {:id        form-id
           :on-submit handle-submit}

          (println "VALUES (as received from Fork)")
          (cljs.pprint/pprint values)

          (str "dirty: " (with-out-str (cljs.pprint/pprint dirty)))

          (for [certificate-id @(rf/subscribe [:issue-data-corruption.subs/certificates])]
            ^{:key certificate-id}
            [certification {:get-props (fn []
                                         {:form-props     form-props
                                          :certificate-id certificate-id

                                          ;; Explicit structure, similar to that which will be used in the form values. Also exhibits
                                          ;; the issue (see print output at the as the first line of this component)
                                          ;;
                                          ;; The output should match the following - note the corruption of the UUID formatting:
                                          ;; TEST-PROPS
                                          ;; {:certificates
                                          ;;  {:cdf-53254-ed-484e-91-b-3cf-4fd-03dc-7d-6e-0 {:description "d"}}}
                                          :test-props     {:certificates {:cdf53254-ed48-4e91-b3cf-4fd03dc7d6e0 {:description "d"}}}})}]
            )])]]]]])

(defn main-panel []
  [theme-provider (create-mui-theme my-theme)
   [css-baseline]
   [(with-custom-styles panel)]])
