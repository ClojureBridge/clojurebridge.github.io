(require '[cljs.nodejs :as nodejs])
(require '[clojure.spec.alpha :as s])

(def fs (nodejs/require "fs"))
(def js-yaml (nodejs/require "js-yaml"))


(s/def ::url (s/and string? #(not= "" %)))
(s/def ::email (s/and string? #(.includes % "@")))
(s/def ::image ::url)
(s/def ::names (s/coll-of string?))
(s/def ::service string?)
(s/def ::username string?)

(s/def ::layout #(= "post" %))
(s/def ::country string?)
(s/def ::city string?)
(s/def ::street string?)
(s/def ::latitude (s/double-in :min -90.0 :max 90.0))
(s/def ::longitude (s/double-in :min -180.0 :max 180.0))
(s/def ::date (s/and string? #(re-matches #"\d\d\d\d-\d\d-\d\d \d\d:\d\d:\d\d [+-]\d\d\d\d" %)))
(s/def ::workshop-dates string?)
(s/def ::city-image-url ::url)
(s/def ::organizers (s/keys :req-un [::email ::names]))
(s/def ::accounts (s/coll-of (s/keys :req-un [::service ::username])))
(s/def ::sponsors (s/coll-of (s/keys :req-un [::name ::url ::image])))

(s/def ::workshop-metadata (s/keys :req-un [::layout
                                            ::country
                                            ::city
                                            ::street
                                            ::latitude
                                            ::longitude
                                            ::date
                                            ::workshop-dates
                                            ::city-image-url
                                            ::organizers
                                            ::accounts
                                            ::sponsors]))

(defn validate [k o]
  (let [valid (s/valid? k o)]
    (when (not valid) (s/explain k o))
    valid))

(def validate-file
     (comp #(validate ::workshop-metadata %)
           #(js->clj % :keywordize-keys true)
           #(.safeLoad js-yaml %)
           second
           #(clojure.string/split % "---\n")
           #(.readFileSync fs % "utf8")))

(defn validate-dir [dir]
  (let [files (js->clj (.readdirSync fs dir "utf8"))
        valid (every? #(validate-file (str dir "/" %)) files)]
    (js/process.exit (if valid 0 1))))

(validate-dir "_posts")
