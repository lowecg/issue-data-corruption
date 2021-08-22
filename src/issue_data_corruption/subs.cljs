(ns issue-data-corruption.subs
  (:require
    [re-frame.core :as rf]))

(rf/reg-sub
  ::certificates
  (fn [db]
    (:certificates db)))
