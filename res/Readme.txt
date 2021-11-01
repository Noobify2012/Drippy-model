About/Overview: This is my implementation of the Dungeon Model. 1 player enters the dungeon with nothing but their empty treasure sack.


List of features. List all features that are present in your program.
- In its current implemenation, the player can be initialized, assigned attributes, assigned weapons, and assigned gear 
with all of their effects applied with 3 line calls after the player is created and the rest done in private methods. This
takes any of the constructing or building out of the users hands and removes some of the associated error. The only reason
this isn't all done automatcially when a player is created because of the requirements for printing out the information in the proper order. 

- Uses a weapon factory and gear factory that automatically generates and the associtated equipment or weapon when called and returns it to
 the player with no input required.

- All of the gear(belts, headgear, footwear and potions) have unique names that are generated based on their effect on the player. 

- The simplicity of how the player just loses damage and no other attribute makes it very easy to reset the battle with only one call

- If the user, in the future, wants to expand the gear and/or the weapons it is very easy to add them in and include their production in the respective factories. 

How To Run. Instructions to run the program should include the following:

How to run the jar file
There are no command lind arguements that are required to run the jar file. 

How to Use the Program. Instructions on how to use functionality in your program. if interactive, how to interact with your program? Pay particular attention to the parts that are not part of the example runs that you provided.
- The only thing the user needs to do is start the jar file and when prompted either enter restart to restart the battle or quit to end the program. 

Description of Examples. 

Run 1 -- Run 1.txt:
1. Welcome Message printed to user
2. Introduces and prints the players and their attributes as well as the weapons they issued.
3. It then prints out the results of the battle and asks if the user wants to restart.
4. In this run, I entered quit and ended the program, the program then prints a farewell message. 

Run 2 -- Run2.txt:
1. Welcome Message printed to user
2. Introduces and prints the players and their attributes as well as the weapons they issued.
3. It then prints out the results of the battle and asks if the user wants to restart.
4. In this run, I entered restart and restarted the program, the program then runs.


Design/Model Changes. It is important to document what changes that you have made from earlier designs. Why were those changes required? You can write these changes in terms of version if you wish.

- the weapon and gear abstract classes and sub classes ended up being exercised with factory methods that made the 
production and packaging of those items easier and more user friendly because all the constructors and arguements 
were handled in private methods and handled in the background. 

- lots of private methods were added to handle all of the object passing and maintinence. 



Assumptions. List what assumptions you made during program development and implementation. Be sure that these do not conflict with the requirements of the project.
- Potions and all gear will remain in effect for the entire length of the battle and rematches. All gear will remain the same through out the battle and rematches until the program is finished and restarted. 

- a player will have the same weapon through out a battle and will only be able to draw a new weapon if there is a rematch.



Limitations. Limitations of your program if any. This should include any requirements that were not implemented or were not working correctly (including something that might work some of the time).

- While the scanner will parse the user input when asking the user if they want to restart the battle or quit, it has a bug where if the words reset and quit are passed with a space in between, 
it will do both or it will quit if it quit comes first. 

-I didn't understand the random number generator constructors and their structure until it was a little late to implement. I just have a bunch of little random number generators. 

- should have improved some of the structure using enums for things like weapon name, it would have made some of the testing. Also the way I did random number generation made it difficult to test certain things like getting gear and checking for its type. 

-Sometimes the first run hangs and won't complete. I'm not sure if this is an IDE issue or something with my machine but I can't tell if its a bug or just machine issue. Either way if the program is restarted it immeediatly works again. 

Citations. 
I found an example of how to simulate the dice on stackoverflow and how to sort the values so that we can do the selection for the attribute implementation:  //https://stackoverflow.com/questions/20518078/how-to-sort-listinteger
There are other citations in line including in differnt gear classes that used names of mythical or biblical items and where i found the references. 