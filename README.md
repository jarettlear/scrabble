# Scrabble Helper

## Build

You will need Java 17+ to build and run this application which is not packaged with this project.

To kick off the build execute the build.sh script.

`$ ./build.sh`

Once the build has completed successfully then you can move on to running the app.

## Run

To start the app you will execute the run.sh script.

`$ ./run.sh`

## Usage

Once the server has started you will be able to hit the endpoint with your browser.

`GET http://localhost:8080/autocomplete/{partial-scrabble-word}`

For example:
[http://localhost:8080/autocomplete/get](http://localhost:8080/autocomplete/get)

---

## Questions

1. How does your system work? (if not addressed in comments in source)
> When the application starts, the file with the list of words is loaded into an array of Strings.
> The array is looped through and each word is broken down and added by character to 
> a trie node. The trie is then wired up as a bean in order for it to be available to 
> any service that wants to use it. When the GET /autocomplete/{prefix} endpoint is hit the 
> controller takes the request and passes it onto the service. The service has a dependency on the 
> trie and that allows us to run our helper methods to pull 10 words.
---
2. How will your system perform with a 10 MB file? a 100 MB file? a 1 GB file?
> The system performance would probably depend on the amount of memory available for storing the initial
> list. At 10MB or 100MB the only difference might be a few milliseconds during start up.
> A 1GB file is more prohibitive in that, it would be a big jar so moving it around would be time-consuming.
> Hope you didn't mess up. Also loading that much data into memory on startup will slow it down, if you have the
> resources available then it can handle it, it just might take a longer to start up initially. Once you get to 
> that size you should probably be letting a database do the work, it will be more suited to those amounts of data.
---
3. How will your system perform with 100 users? 10000 users? 1000000 users?
> This will also depend on resources some. Once the trie is loaded and ready it is very fast to search. With 100 users
> we wouldn't hardly see a blip. 10000 users might be a small blip, but it would still perform very well even on a single
> machine. 1000000 users would still be stable, though at that point, or before, it sounds like you would want to 
> scale a bit for performance and uptime since a million people playing scrabble would be crying out in desperation
> if your app did come crashing down. 
---
4. What documentation, websites, papers, etc did you consult in doing this assignment?
> I used stackoverflow.com because I honestly couldn't remember how to retrieve a file in a fat jar. I knew it was different but I always forget.
> I looked around a bit for the trie solution. It was what I wanted to use from the beginning, but I did use a site 
> help guide me.
---
5. What third-party libraries or other tools does the system use? How did you choose each library or framework you used?
> Mostly just Spring Boot with Web and Lombok. Spring because I know Spring and so that part was easy.
> Lombok because it makes annotation processing and makes my classes cleaner. It's not for everyone
> but I like it. I did decide to use maven, I have mostly used that or gradle, and I was just more comfortable
> with maven. 
---
6. What data structures did you use and why?
> The Trie was chosen because it's whole thing is searching in a way that allows for efficient
> generation of completion lists. Wikipedia told me that.
---
7. How long did you spend on this exercise? If you had unlimited more time to spend on this, how would you spend it and how would you prioritize each item?
> I spent a good part of an afternoon on the whole thing. I can't say for sure but I would guess my total time
> in code was just a few hours. I took some more time to tweak and test and hack around some. 
---
8. If you were to critique your code, what would you have to say about it?
> I would probably ask myself why I didn't use a database. No but seriously, I feel like I would look at the TrieConfig a little closer and 
> try to see if there was a way to clean it up more. I don't hate it, but I also think it feels kind of like there might be a better way to 
> organize it and clean it up.

