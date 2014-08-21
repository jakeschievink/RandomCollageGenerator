(ns RandomArtGenerator.functions
    (:use [clojure.string :only (join split)])
    (:use [clojure.java.shell :only [sh]])
    (:use quil.core)
    (:import java.io.File))

(def datadir "./data/images/")
(def displaywidth 1000)
(def displayheight 800)
(def nouns (split (slurp "./data/pos/nouns.txt") #"\r\n"))
(def verbs (split (slurp "./data/pos/verbs.txt") #"\r\n"))
(def adj (split (slurp "./data/pos/adj.txt") #"\r\n"))
(def colorschemes {
                   ;grabbed from colorcombos.com
                   :black [[0, 0, 0]]

                   :darkblues [[68, 50, 102]
                               [195, 195, 229]
                               [241, 240, 255]
                               [140, 72, 159]]

                   :hotcolors [[255, 255, 102]
                               [255, 204, 0]
                               [255, 153, 0]
                               [255, 0, 0]]

                   :lightgreens [[204, 153, 255]
                                 [255, 255, 255]
                                 [153, 204, 0]
                                 [102, 153, 102]]

                   :darkercolors [[0, 51, 51]
                                  [204, 204, 153]
                                  [102, 102, 153]
                                  [0, 51, 102]]

                   :rockcolors [[213, 208, 176]
                                [115, 146, 64]
                                [200, 96, 0]
                                [66, 82, 39]]
                   })

(def activefuncs (atom ()))
(def selcolor (atom (rand-nth (keys colorschemes))))

(defmacro ignore-errors
    "Returns the result of evaluating e, or nil if it throws an exception."
    [e]
    `(try ~e (catch java.lang.Exception _# nil)))

;creates a black circle 
(defn blackhole[xpos ypos size]
  (fill 0)
  (ellipse xpos ypos size size)) 

(defn blur-rand [n] 
  (display-filter :blur n))

;Grabs a new webimg
;only works if user is connected to the internet
(defn newwebimg[]
   (sh "python" "./data/wc.py"))

;sets background color
(defn bgc[color]
  (println color)
  (apply fill-float color)
  (rect 0 0 displaywidth displayheight))

;sets color scheme
(defn setactivecolors[k]
 (reset! selcolor k))

;Random Sentance Generator
(defn sentance[sentance randsize color] 
        (println color)
        (apply fill-float color)
        (text-font (create-font "Nimbus Sans L" randsize))
        (text sentance 50 (rand-nth [100, 300, 500]) displaywidth displayheight))

;Loads a random web image from the ./data/images folder
(defn loadwebimg [xpos ypos imgf]
  (let [
        webimg (load-image (str datadir imgf))
        iheight (.height webimg)
        iwidth (.width webimg)
        ] 
       (ignore-errors (image webimg xpos ypos))))
       
    


;Loads a shadow of a figure
(defn load-figure [xpos ypos]
  (let [
        fig (load-image "./data/Figure.png")
        figwidth (.width fig)
        figheight (.height fig)
        ]
    (image fig xpos ypos (/ figwidth 4) (/ figheight 4))))


