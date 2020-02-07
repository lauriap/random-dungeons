# Program implementation document

## General structure

This program has been written using Java and it was built using gradle. Checkstyle and jacoco were used for code style management and test coverage reports.

The program consists of three classes: Dungeon.java, Ruins.java and DungeonGUI.java.

# Dungeon.java

This class is used to create a random dungeon based on cellular automata. The resulting dungeon is reminiscient of a cavern. The class first initializes a cavern by randomly filling it with wall tiles. Once this has been done, all tiles' neighbours are checked and morphed into wall or space tiles depending on the amount of wall tile neighbours for that specific tile.

# Ruins.java

This class creates a random dungeon with a ruins-type setting. It creates house-like structures where some of the walls have fallen down while some are still intact. All houses are made accessible. The algorithm selects random points on the map and forms houses according to specific criteria (house "height" and width) taking into account the existing structures on the map. No structures are based on one another.

# DungeonGUI.java

This is the graphical user interface class of the program. DungeonGUI enables the user to select the kind of dungeon s/he wants to create using dungeon type, size, wall density and random seed as parameters. The user's choices have been limited to just a few (e.g. small/medium/large) to avoid confusion and to avoid input-based errors. 

# Test classes

A test class has been created for Dungeon.java. A test class will be added for Ruins.java during week 5.

## Time and space complexity

Consider a 100x100 dungeon matrix, i.e. a two-dimensional array t[100][100]. Both algorithms will have to go through each element of the array, resulting in a time complexity of O(nm) where n is the number of rows and m the number of columns. 

The space complexity of the algorithms is O(nm).

## Suggestions for improvement

* Unit testing for Ruins class missing. Will be added during week 5.
* Cavern-type dungeon also forms caverns with inaccessible places. Will be fixed during week 5.
* GUI window showing the generated dungeon is missing a scroll bar. Will be added during week 5.
* Checkstyle corrections still need to be made.
* Random class not implemented with a self-made data structure yet
* Still considering whether to allow rectangular maps instead of squares.

## Sources

RogueBasin: [_Cellular Automata Method for Generating Random Cave-Like Levels_](http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels), visited 18.1.2020.







