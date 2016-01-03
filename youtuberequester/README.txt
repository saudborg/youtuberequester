README

This README file explains how to use the project develop to NowDiscover.

In this project was used
- JAVA 1.6
- Maven
- Spring

All the dependecies were added by Maven. To run the application you should download this dependencies

In the project you can find 2 ways to test the application

- nowdiscover.youtuberequester.executable.App.java (uncomment main method)
	
In this class you can change the parameter that will search in youtube changing the attribute querySearch. After this, the application will generate a random number > 50 and will search in youtube and is going to show all the words that appear in this search. 

=============================================================
   First 427 videos for search on "duck". (Total words found: 4151)
=============================================================

Word: duck | Value: 467 | Frequencie: 11.0%
Word: donald | Value: 350 | Frequencie: 8.0%
Word: cartoons | Value: 178 | Frequencie: 4.0%
Word: chip | Value: 152 | Frequencie: 3.0%
Word: and | Value: 146 | Frequencie: 3.0%
Word: full | Value: 146 | Frequencie: 3.0%
Word: dale | Value: 144 | Frequencie: 3.0%
Word: episodes | Value: 112 | Frequencie: 2.0%
Word: compilation | Value: 110 | Frequencie: 2.0%
Word: cartoon | Value: 94 | Frequencie: 2.0%
Word: hd | Value: 92 | Frequencie: 2.0%
Word: new | Value: 86 | Frequencie: 2.0%
Word: collection | Value: 51 | Frequencie: 1.0%
Word:  | Value: 51 | Frequencie: 1.0%
Word: disney | Value: 44 | Frequencie: 1.0%

After this the application will get the firsts 10 results (skipping the first one, because is the same that the param) and will search in youtube how many times the word appears in this results and will show the best words to find your param

=============================================================
Results for duck
=============================================================
Word: donald | 136
Word: cartoons | 128
Word: chip | 32
Word: and | null
Word: full | null
Word: dale | 126
Word: episodes | null
Word: compilation | null
Word: cartoon | 120
Word: hd | null
Word: new | null


- nowdiscover.youtuberequester.Application.java

You just have to run, and the application will be deploy in a TomCat and the application will be available in localhost:8080/home.

localhost:8080/home
When you enter in this website the default searching is Duck. The application will search in Youtube and generate a randon number > 50 to the max results. This page shows how many times appear each word when you pass a param, like in App.java but here you can see the frequency.
You can change the query parameter via URL. 
The parameter is 
query: String
maxNumber: Integer

example: http://localhost:8080/home?query=pig&maxNumber=100


localhost:8080/search
This is another url available that you can use to search in Youtube. But in this one the application will just show you the words that the application consider more relevant. That means, if you search in youtube for this words you always will find the word that you wanna in this research
You can change the query parameter via URL. 
The parameter is 
query: String
maxNumber: Integer

example: http://localhost:8080/search?query=duck&maxNumber=500