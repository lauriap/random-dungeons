# Program implementation document

## General structure

This program has been written using Java and it was built using gradle. Checkstyle and jacoco were used for code style management and test coverage reports.

The program consists of five classes: Dungeon.java, Ruins.java, DungeonGUI.java, RandomNumGen.java and SpeedTest.java.

## Dungeon.java

This class is used to create a random dungeon based on cellular automata. The resulting dungeon is reminiscient of a cavern. The class first initializes a cavern by randomly filling it with wall tiles. Once this has been done, all tiles' neighbours are checked and morphed into wall or space tiles depending on the amount of wall tile neighbours for that specific tile.

## Ruins.java

This class creates a random dungeon with a ruins-type setting. It creates house-like structures where some of the walls have fallen down while some are still intact. All houses are made accessible. The algorithm selects random points on the map and forms houses according to specific criteria (house "height" and width) taking into account the existing structures on the map. No structures are based on one another.

## DungeonGUI.java

This is the graphical user interface class of the program. DungeonGUI enables the user to select the kind of dungeon s/he wants to create using dungeon type, size, wall density and random seed as parameters. The user's choices have been limited to just a few (e.g. small/medium/large) to avoid confusion and to avoid input-based errors. 

## RandomNumGen.java

This is a random number generator data structure based on the Java's Random class.

## SpeedTest.java

This class is used solely for speed testing purposes. Speed test results can be seen in the testing document.

## Test classes

Test classes have been created for the dungeon classes. The dungeon classes also include testing of the random number generator. Please see the testing document for more information.

## Time and space complexity

Consider a 100x100 dungeon matrix, i.e. a two-dimensional array t[100][100]. Both algorithms will have to go through each element of the array, resulting in a time complexity of O(nm) where n is the number of rows and m the number of columns. 

The space complexity of the algorithms is O(nm).

## Suggestions for further development

* Breadth-first search (BFS) algorithm for Dungeon.java floodFill() method.
* The Ruins map could become more interesting if more varied shapes were introduced (e.g. cross-shaped structures, circles etc.)
* Allowing rectangular maps instead of squares.
* A prettier UI based on something else than Swing as it limits the possibilities somewhat (e.g. line-height not supported in Swing HTML/CSS)

# Sources

RogueBasin: [_Cellular Automata Method for Generating Random Cave-Like Levels_](http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels), visited 18.1.2020.







