(require '[cljs.nodejs :as nodejs])
(require '[clojure.spec.alpha :as s])

(def fs (nodejs/require "fs"))
(def js-yaml (nodejs/require "js-yaml"))


(s/def ::url (s/nilable (s/and string? #(not= "" %))))
(s/def ::email (fn [x] (or (nil? x) (and (string? x) (.includes x "@")))))
(s/def ::github (s/nilable string?))
(s/def ::image ::url)
(s/def ::name (s/and string? #(not= "" %)))
(s/def ::twitter (s/nilable string?))

(s/def ::layout #(= "post" %))
(s/def ::permalink string?)
(s/def ::country string?)
(s/def ::city string?)
(s/def ::street (s/nilable string?))
(s/def ::latitude (s/nilable (s/double-in :min -90.0 :max 90.0)))
(s/def ::longitude (s/nilable (s/double-in :min -180.0 :max 180.0)))
(s/def ::start-date #(instance? js/Date %))
(s/def ::end-date #(instance? js/Date %))
(s/def ::registration-url (s/nilable ::url))
(s/def ::city-image-url ::url)
(s/def ::gravatar-email ::email)
(s/def ::organizers (s/coll-of (s/keys :req-un [::name ::email ::github ::twitter])))
(s/def ::sponsors (s/coll-of (s/keys :req-un [::name ::url ::image])))

(s/def ::workshop-metadata (s/keys :req-un [::layout
                                            ::permalink
                                            ::country
                                            ::city
                                            ::street
                                            ::latitude
                                            ::longitude
                                            ::start-date
                                            ::end-date
                                            ::registration-url
                                            ::city-image-url
                                            ::gravatar-email
                                            ::organizers
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
