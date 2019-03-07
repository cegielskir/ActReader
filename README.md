

# Act Reader

Command line application which allows user to find and show specific part of an Act. It can be section, chapter, article, point and letter. It also allows to show table of contents, range of articles, etc. It was developed mainly for two documents: constitution of the Republic of Poland, and Act on competition and consumer protection, so it can not work properly for other documents.    

ese instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.


### Usage

Application can be run via command line.
First and the only necessary argument is a path of a document. After the path application accepts several options(# means part's identification - it can be for example: IIIA, 52, 94a, c, etc.):

```
-s // Shows table of contets
```
```
-sd # // Shows table of contents of a specific section 
```
```
-a # // Shows specific article
```
```
-r # // Shows specific chapter
```
```
-d # // Shows specific section
```
```
-u # // Shows specific paragrapf
```
```
-p # // Shows specific point
```
```
-l # // Shows specific letter
```
```
-za # #  // Shows specific articles from and to requested ones
```

Some of the options can't be used separately - for example, if you want to find specific letter it is necessary to
define some other parts, such as article, paragraph and point. The same applies to chapter if sections exist - they have 
to be defined.

Examples:
```
path -a 18 -p 3 -l c // Shows letter 'c', from point '3', from article '18'
```
```
path -d IIIA -r 2 // Shows chapter '2' from section 'IIIA'
```


