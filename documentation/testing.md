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





