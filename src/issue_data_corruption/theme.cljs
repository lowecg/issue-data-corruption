(ns issue-data-corruption.theme)

; handy for choosing relative colours:
; https://www.tutorialrepublic.com/html-reference/html-color-picker.php

(defn spacing [units]
  (* units 8))

(def my-theme {:overrides {:MuiPaper        {:root {:background-color "rgba(248, 244, 241, 1)"}}
                           :MuiListItemIcon {:root {:min-width (spacing 5)}}
                           :MuiTypography   {:root {:color "#000"}}
                           :MuiSvgIcon      {:root {:color "#000"}}
                           ;:MuiIconButton   {:root {       :background-color "#fff"
                           ;                         "&:hover"         {:background-color "#d4d2d2" ; tertiary light
                           ;                                            }}}
                           }
               :props     {:MuiTypography {:variant "body2"}
                           :MuiPaper      {:elevation 0}
                           :MuiIconButton {:color   "inherit"
                                           :variant "contained"}
                           :MuiTextField  {:variant "outlined"
                                           :margin  "normal"}}})