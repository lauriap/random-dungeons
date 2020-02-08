# Testing document

## Unit tests

The program has full unit tests for the dungeon generator classes, i.e. Dungeon.java and Ruins.java. GUI testing will not be included in the unit tests. Jacoco is used for test coverage reports.

The following aspects were covered with the unit tests:
* Generation of random dungeons, reproducibility done by using a seed for the Random class
* Output matching against hand-calculated values for dungeon arrays
* Correct indexing, specifically for IsOutOfBounds() method where risk for IndexOutOfBoundsException is the highest

Test coverage at the end of week 4:

[Dungeon](https://github.com/lauriap/random-dungeons/blob/master/documentation/test-coverage/test-coverage-dungeon-w4.png)


[Ruins](https://github.com/lauriap/random-dungeons/blob/master/documentation/test-coverage/test-coverage-ruins-w4.png)

Tests for Ruins will be added during week 5.


## Style check

Checkstyle is used to ensure that the code is well written.





