(ns RandomArtGenerator.core
  (:use quil.core)
  (:import java.io.File)
  (:use RandomArtGenerator.functions)
)
;functions which must be chosen first
(def firstfunctions {
                     :setactivecolors setactivecolors
                     :bgc bgc
                     })
;list of active functions which get into the genetic soup
(def functions {
                :blackhole blackhole
                :loadwebimg loadwebimg
                :sentance sentance
                :load-figure load-figure
                :setactivecolors setactivecolors
                :bgc bgc
;              :blur-rand blur-rand
                })

;Arguments to the functions in the functions map
(def functionargs 
  (let [xpos #(rand-int displaywidth)
        ypos #(rand-int displayheight)
        filelst (map (fn [f] (.getName f)) (.listFiles (File. (str datadir))))
        imgf #(first (shuffle filelst))
        sentance #(str (rand-nth nouns) " " (rand-nth verbs) " " (rand-nth nouns))
        randsize #(+ (rand-int (- 100 30)) 30)
        selcolorkey #(rand-nth (keys colorschemes))
        color #(rand-nth (colorschemes @selcolor))
        bc #(rand-int 30)
        ]
         {
          :blackhole [xpos ypos #(rand-int 200)]
          :loadwebimg [xpos ypos imgf]
          :sentance [sentance randsize color]
          :load-figure [xpos ypos]
          :bgc [color]
          :setactivecolors [selcolorkey]
          :blur-rand [bc]
         }))

;generates a randomlist of functions 
(defn drawprep []
  (let [
        firstfuncs (keys firstfunctions)
        secondfuncs (repeatedly (rand-int 20) #(rand-nth (keys functions))) 
        selectedfunctions (concat firstfuncs secondfuncs)
        getfunctions (map functions selectedfunctions)
        arglist (map functionargs selectedfunctions)
        ;funclist (repeatedly (rand-int 10) #(rand-nth functions))
        ]
    ;uses an anonymous function which takes a function and its arguments and applies to arguments to the function
    (reset! activefuncs (map (fn [f args] (apply f (map #(%) args))) getfunctions arglist))
    (println secondfuncs)
    (println selectedfunctions)
    (println getfunctions arglist) 
    ))

(defn setup[]
  (smooth)
  (no-loop)
  (drawprep))

(defn mouse-pressed[]
  (println "pressed")
  (drawprep)
  )

(defn draw[]
  (doall @activefuncs))

(defsketch RandomArtGenerator
           :title "RandomArtGenerator"
           :setup setup
           :draw draw
           :mouse-pressed mouse-pressed
           :size [1000 800]
           )

(defn -main []
  #_(run-sketch RandomArtGenerator))
