# Testing document

## Unit tests

The program has full unit tests for the dungeon generator classes, i.e. Dungeon.java and Ruins.java. Jacoco is used for test coverage reports.

The following aspects were covered with the unit tests:
* Generation of random dungeons, reproducibility done by using a seed for the RandomNumGen class
* Output matching against hand-calculated values for dungeon arrays
* Correct indexing, specifically for IsOutOfBounds() method where risk for IndexOutOfBoundsException is the highest

What was not covered by the tests:
* Errors in user input as the risk has been minimized in the UI by not allowing free input
* GUI unit testing (not part of this course)

Test coverage at the end of week 6:

[Dungeon](https://github.com/lauriap/random-dungeons/blob/master/documentation/test-coverage/test-coverage-dungeon-w6.png)


[Ruins](https://github.com/lauriap/random-dungeons/blob/master/documentation/test-coverage/test-coverage-ruins-w6.png)


[All](https://github.com/lauriap/random-dungeons/blob/master/documentation/test-coverage/test-coverage-all-w6.png)


## Style check

Checkstyle is used to ensure that the code is well written. All checkstyle errors for Ruins and Dungeon have been reviewed and either fixed or accepted.


## Assessment of map "goodness"

To assess the "goodness", i.e. how good a map looks, how "interesting it is", I ran the random dungeon generator 100 times for both Cavern and Ruins type dungeon. The distribution of settings for both types was the following:
* 33 rounds with low, 33 with medium and 34 with high density
* For each set of 33/34 runs based on density, I did 11 runs with small, 11 runs with medium and 11 runs with large map size
* The reason for this choice was that I felt that density was the key factor in affecting how interesting the map turned out to be

### Key takeaways from map "goodness" testing

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



## Performance testing

The program includes a class called DungeonSpeedTest, which was used to test the time required for creating different sized dungeons. The results are as follows:


| Dungeon type        | Size          | Time (s)  |
| :-----------------: |:-------------:| :--------:|
| Cavern              | 50x50         | 0.0063    |
| Cavern              | 100x100       | 0.0093    |
| Cavern              | 150x150       | 0.0145    |
| Cavern              | 200x200       | 0.0191    |
| Cavern              | 500x500       | 0.0387    |
| Ruins               | 50x50         | 0.0022    |
| Ruins               | 100x100       | 0.0024    |
| Ruins               | 150x150       | 0.0061    |
| Ruins               | 200x200       | 0.0060    |
| Ruins               | 500x500       | 0.0170    |


![alt text](https://raw.githubusercontent.com/lauriap/random-dungeons/master/documentation/performance-testing.png)


We can see from the results that the performance starts to decrease when the size increases to 500x500. The reason why the test was not continued to higher magnitudes is the recursive floodFill algorithm employed in the Dungeon.java class. A higher map size results in a stack overflow. This issue could potentially be alleviated by using breadth-first instead of depth-first as the search algorithm, but its implementation would have required the use of a queue. As all data structures are to be self-made, I chose to accept this limitation in testing instead. For the purpose of these types of maps, a map size of 500x500 is already large enough for all purposes.



