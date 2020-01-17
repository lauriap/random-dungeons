# Program requirements specification

## Purpose and Scope

Random Dungeons is a program designed to generate random ASCII dungeons (caves) similar to those seen in early rogue-like games such as NetHack. The program is based on the concept of cellular automata. The program comes with a simple graphical user interface and outputs an ASCII map pictured from above (example below). The program is written using Java. 


		############################################################
		#########......###...##........####...####.....#############
		####............................##.....##............#######
		###................###.....##..........................#####
		###...............#####...####..........................####
		###...............#####....####............#............####
		####...###.........####.......##.......................#####
		##########..........###........##.....................######
		##########...........#..........##...................#######
		#########.......................#####..............#########
		######.........................#######...#......############
		#####..........................############....#############
		####...........................###########......######..####
		###..........##.................#########................###
		##.......#######........#..........######...###.........####
		##......########.......##............###...######.....######
		###.....#######...............#####........########..#######
		###......#####...##...........######........################
		###......#####..####...........#####.........###############
		#######..#####..####............###...........#######....###
		########..###...#####......###.................#####......##
		########.......######......####.................###.......##
		########.......######.......##....##..................##..##
		#######.......######....##.......####................####..#
		######.......#######....###......####..........###..#####..#
		#####........######.....######....##..........##########...#
		######........###........######...............########.....#
		##########...............#######.............########.....##
		#############...#######..###########...#####################
		############################################################  
		
		Example dungeon.
  

## Algorithms and Data Structures Used

An approach to generating random dungeons using cellular automata is described in [RogueBasin](http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels), which is also the main source for the algorithms used in this program. According to the RogueBasin article, 

> It is an old and fairly well documented trick to use cellular automata to generate cave-like structures. The basic idea is to fill the first map randomly, then repeatedly create new maps using the 4-5 rule: a tile becomes a wall if it was a wall and 4 or more of its eight neighbors were walls, or if it was not a wall and 5 or more neighbors were. Put more succinctly, a tile is a wall if the 3x3 region centered on it contained at least 5 walls. Each iteration makes each tile more like its neighbors, and the amount of overall "noise" is gradually reduced.

One of the issues with using the cellular automata algorithm is that it also generates isolated pockets that are not accessible by any means. In order to alleviate this issue, a Flood Fill algorithm is used. The Flood Fill takes any open cell in the main dungeon, recursively checks the whole open space around it and finally turns all remaining, isolated open spaces into walls.

As the dungeon is generated using ASCII art, the main functionality can be created using two-dimensional arrays. For randomization, a random number generator will have to be created. For the GUI, Java's Abstract Windowing Toolkit (AWT) will be used. In order to keep track of the necessary wall character transformations each round, a stack structure might be needed.

### Why these algorithms and data structures?

I found cellular automata to be an interesting yet reasonably simple way of generating random dungeons. As I'm not very experienced with coding, I decided to pursue a project that I find manageable but which can also be expanded upon in case the project can be completed faster than previously expected. One possible expansion of the project is to include a start and end point and then look for the shortest path through the dungeon.

Two-dimensional character arrays work well for the purpose of this program because no element of the dungeon array will contain more than one character. The random number generator can be made using the computer's internal clock which, while not random, will create an effect of randomness from the user's point of view.

## Input and Output

Using the GUI, the user can set the parameters for the dungeon generator. The parameters include dungeon size (X/Y integer values between 10-1000), random seed number (optional), number of "wall points" and number of iterations (1-10). The output is an ASCII image of the dungeon in another window.

## Time and Space Complexity

Consider a 100x100 dungeon matrix, i.e. a two-dimensional array t[100][100]. Thus in total, there are 10 000 points in the dungeon. During the randomization phase, the program will select w number (user input) of "wall points" to set up a starting configuration for the cellular automata algorithm. The algorithm will then proceed to loop through the full character matrix with O(nm) time complexity where n is the number of rows and m the number of columns. During the loop, the algorithm checks whether the character is a wall character and, if it is, checks its neighbours. If the 4-5 rule described above is true for any of its neighbours, the neighbour will be turned into a wall character at the end of the round.

The space complexity of the algorithm itself is O(nm).

## Sources

RogueBasin: [_Cellular Automata Method for Generating Random Cave-Like Levels_](http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels), visited 18.1.2020.







