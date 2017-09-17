(ns comments.core
  (:require [reagent.core :as r]))

;; -------------------------
;; Mock data

(def comments (r/atom [{:author "dra1n"
                        :content "This is my first comment"}
                       {:author "dra1n"
                        :content "Oh shi! It works!"}]))


;; -------------------------
;; Actions

(defn handle-comment-submit [c e]
  (.preventDefault e)
  (swap! comments conj @c)
  (reset! c {}))


;; -------------------------
;; Views

(defn comment-form []
  (let [c (r/atom {})]
    (fn []
      [:form.commentForm {:on-submit (partial handle-comment-submit c)}
       [:input {:type "text"
                :value (get @c :author)
                :on-change #(swap! c assoc :author (-> % .-target .-value))}]
       [:input {:type "text"
                :value (get @c :content)
                :on-change #(swap! c assoc :content (-> % .-target .-value))}]
       [:input {:type "submit"
                :value "Post"}]])))

(defn comment-item [c]
  [:div.comment
   [:h3.commentAuthor (get c :author)]
   [:div.commentContent (get c :content)]])

(defn comment-list []
  [:div.commentList
   (for [c @comments]
     ^{:key c} [comment-item c])])

(defn home-page []
  [:div
   [:h1 "Comments"]
   [comment-list]
   [comment-form]])


;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
