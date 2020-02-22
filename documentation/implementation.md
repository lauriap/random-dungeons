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

Test classes have been created for all the main classes. Please see the testing document for more information.

# Time and space complexity

Consider a 100x100 dungeon matrix, i.e. a two-dimensional array t[100][100]. Both algorithms will have to go through each element of the array, resulting in a time complexity of O(nm) where n is the number of rows and m the number of columns. 

The space complexity of the algorithms is O(nm).

# Assessment of map "goodness"

To assess the "goodness", i.e. how good a map looks, how "interesting it is", I ran the random dungeon generator 100 times for both Cavern and Ruins type dungeon. The distribution of settings for both types was the following:
* 33 rounds with low, 33 with medium and 34 with high density
* For each set of 33/34 runs based on density, I did 11 runs with small, 11 runs with medium and 11 runs with large map size
* The reason for this choice was that I felt that density was the key factor in affecting how interesting the map turned out to be

## Key takeaways from map "goodness" testing

In general, the maps turned out how I was expecting them to turn out. However, there were some things that stood out during performance testing:
* Low density maps tended to have a lot of open space and especially with Cavern-type dungeons that center area of the map turned out to be relatively empty irrespective of map size
* Medium density maps worked well for both Ruins and Cavern-type dungeons for all map sizes
* High density maps worked best for Ruins-type dungeons. For Caverns, high density and the underlying flood fill algorithm sometimes caused the explorable space to become smaller than on medium density maps. This is partially due to the way the flood fill algorithm is built: It starts from the middle of the map and looks for the first open space either below, to the right, to the left or above it. Also, the fact that the algorithm looks for an open space below the center of the map meant that high density maps tended to lie in the lower part of the map. This could be altered with a different approach for the getFillStartPoint() method in the Dungeon class.
* As the Ruins algorithm always leaves a space between map walls and the structures, small maps ended up having seemingly more empty space than larger ones. Therefore medium and large maps worked best.

Overall, the "best" outcomes were given by the following settings:

**Ruins**
* High density
* Medium or large map size

**Cavern**
* Medium density
* Any map size

# Suggestions for further development

* The Ruins map could become more interesting if more varied shapes were introduced (e.g. cross-shaped structures, circles etc.)
* Still considering whether to allow rectangular maps instead of squares.
* A prettier UI based on something else than Swing as it limits the possibilities somewhat (e.g. line-height not supported in Swing HTML/CSS)

# Sources

RogueBasin: [_Cellular Automata Method for Generating Random Cave-Like Levels_](http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels), visited 18.1.2020.







