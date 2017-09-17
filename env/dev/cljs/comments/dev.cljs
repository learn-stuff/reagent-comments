(ns ^:figwheel-no-load comments.dev
  (:require
    [comments.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
