(ns issue-data-corruption.events
  (:require
    [re-frame.core :as rf]
    [fork.re-frame :as fork]
    [issue-data-corruption.db :as db]))

(rf/reg-event-db
  ::initialize-db
  (fn [_ _]
    db/default-db))

(rf/reg-event-fx
  ::submit
  (fn [{:keys [db]} [_ {:keys [values, path]}]]

    (println "Submit values" values)

    {:db (-> db
             (fork/set-submitting path true))}))