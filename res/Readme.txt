About/Overview: This is my implementation of the Dungeon Model. A player enters the dungeon with
nothing but their empty treasure sack. The player then navigates the dungeon from their start point
to the end. They can either start at a randomly selected point and navigate to a point at least 5
moves away or start at cave 0 and move through all the nodes in the dungeon.


List of features. List all features that are present in your program.
- When the program starts the user is greeted and presented with the criteria for entering proper
command line arguments for starting the dungeon model. The first level of error checking starts
there. The input parser ensures that the user enters at least true or false for the wrapping
requirement and positive integers for rows, columns, interconnectivity, and percentage of caves with
treasure.

-The user has no real interaction with any constructors. The startup parser passes all of those
arguments, once validated at the first level, to the constructor. From there the dungeon and player
are automatically built and run.

-In the dungeon implementation, it can be set to either run automatically as a breadth first path,
meaning from start point to end point as efficiently as possible. Or it can run as a depth first
path starting at node 0 and go to every node. Player navigates the dungeon via the unique index
value of each of the caves or tunnels.

-As the player moves through the dungeon, the program will identify which caves have treasure in
them, display the treasure in the cave and then automatically pick it up before the player moves to
the next cave.

-One unique design feature is the treasure implementation which is an enum and factory pattern all
in one class. This makes the code slightly more compact as a single stop for construction and
validation of treasure. This allows the program to easily identify treasure objects in the players
treasure list and cleanly present them when called.

-Caves and edges have a lot of detail which will help for expanding capability going forward.
Caves and tunnels know who they are neighbors with as well as where they are in the dungeon. Edges
contain the two caves associated with them. They also know which direction to travel to get from
one cave to the other. In the next implementation, this will be used so that the player takes a
direction of travel rather than an index to move through the dungeon.





How To Run. Instructions to run the program should include the following:

How to run the jar file
There are no command lind arguments that are required to run the jar file other than
"java -jar Project3_Dungeon_Model.jar"

How to Use the Program. Instructions on how to use functionality in your program. if interactive, how to interact with your program? Pay particular attention to the parts that are not part of the example runs that you provided.
- The user only needs to start the jar file as described above and when prompted enter the dungeon
construction requirements. A boolean value for wrapping or non(true for wrapping, false for non),
a positive integer for the number of rows, a positive integer for the number of columns, zero or
greater for the level of interconnectivity, and an integer between 0 and 100 for the percentage of
caves with treasure.

- The program will do the rest.

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

-While the program can run through the dungeon as either a breadth first(the shortest path from
start point to end point) or depth first(start at node 0 and run through all the nodes), that has to
be specifically configured in the code. One has to be turned on and the other turned off.

-The player has a move method and there are directions in the enum in the game which are used to
determine direction from one cave to another along an edge object but that is not how the player
currently navigates. In its current implementation, the player moves via a list of indexes instead
of a list of directions. This will be an improved feature that is fixed in the next project
deliverable.




Citations. 
I found an example of how to simulate the dice on stackoverflow and how to sort the values so that we can do the selection for the attribute implementation:  //https://stackoverflow.com/questions/20518078/how-to-sort-listinteger
There are other citations in line including in differnt gear classes that used names of mythical or biblical items and where i found the references. 