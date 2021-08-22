(ns issue-data-corruption.events
  (:require
   [re-frame.core :as re-frame]
   [issue-data-corruption.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
