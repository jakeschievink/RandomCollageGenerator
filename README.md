#Random Collage Generator

This project was concieved of and created during a fall 2013 independent at Hampshire College.

###Log of the process I went through during the independent study: 

Created ruby web crawling script which randomly chooses an image from google images and saves it to an images folder

started with a collage function in clojure which would work by loading the randomly chosen google images and peices them together

used stackoverflow figure out how to extract the image data stored in quil

used stackoverflow to create recursion in the ruby script so that whenever it tries to get an image from a website which doesn't exist it just retries

created a random sentance generator which parses text files containing lists of nouns and verbs

Met with Tomas Helmuth to talk about handling execptions in clojure and creating a function which executes a random list of functions. 

Created a recursive function which would collage images in a way which didn't fit what I had imagined

Posted on stackoverflow regarding an error I recieve when I run the ruby code as a normal user

started writing a function which loads svg graphics into the canvas

fixed the error I got with my ruby code, now I can run my webcrawler within my core.clj file.

Finished the drawprep function which randomly generates the list of functions. 

Added a line which will randomly grab an image from the webcrawler whenever the program is run.

started logging some of the interesting results 

Started adding a colorschemes hashmap so that the colors which are randomly picked complement eachother

Working with other ways to make the randomly generated peices more visually appealing, enclosing the randomly generated sentance within a semi tranparent rectangle for instance. Making it so only one randomly generated sentance happens

        Work more on expression of genes
        Stay away from predesigning

Met with alec during Ta hours and figured out how to create a workable genome which could be mutated 

Added two hashmaps, one with a list of functions and one linking those functions to their arguments, then in the drawprep function a list of keys is generated which are then associated to the functions and their randomly generated arguments. 

Added figure function, and colors to other functions so that a colorscheme can be selected and the color of things don't clash too harshly

Fixed the webcrawler which was having a few other issues

Used try and catch to make it so the code still runs when an image is loaded which has bad image data
