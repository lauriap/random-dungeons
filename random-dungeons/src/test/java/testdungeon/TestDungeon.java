package testdungeon;

import dungeon.Dungeon;
import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This is the test class for Dungeon.
 * 
 * The most commonly used test dungeon is as follows:
 * Dungeon testDungeon = new Dungeon(5, 5, 15, 1337) where 1337 = random seed.
 *      
 *      *** AFTER initializeDungeon() ***
 *              #####
 *              #...#
 *              #..##
 *              #...#
 *              #####
 * 
 *       *** AFTER makeDungeon() ***
 *              #####
 *              #####
 *              #..##
 *              #####
 *              #####
 * 
 * The above dungeons were calculated by hand and are compared to the algorithms
 * used in the Dungeon class.
 * 
 * In the tests, # = 1 and . = 0 as the final conversion to characters is only done
 * in the printDungeon() method.
 * 
 * @author lauri
 */
public class TestDungeon {

    /**
     * Tests that the dungeon is initialized correctly with seed 1337. Comparison is made against hand-calculated values
     *      *** AFTER initializeDungeon() ***
     *              #####
     *              #...#
     *              #..##
     *              #...#
     *              #####
     */
    @Test
    public void testInitializeDungeon() {
        Dungeon testDungeon = new Dungeon(5, 5, 15, 1337);
        testDungeon.initializeDungeon();
        int dungeonArray[][] = testDungeon.getDungeonArray();
        int[][] testArray = new int[5][5];
        //form int[5][5] test array with hand-calculated values of initializeDungeon() using 1337 as seed.
        for(int i = 0; i < 5; i++) {
            testArray[0][i] = 1;
            testArray[i][0] = 1;
            testArray[4][i] = 1;
            testArray[i][4] = 1;
        }
        testArray[1][1] = 0;
        testArray[1][2] = 0;
        testArray[1][3] = 0;
        testArray[2][1] = 0;
        testArray[2][2] = 0;
        testArray[2][3] = 1;
        testArray[3][1] = 0;
        testArray[3][2] = 0;
        testArray[3][3] = 0;
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                assertEquals(testArray[i][j], dungeonArray[i][j]);
            }
        }
    }
    
    /**
     * Test for a random wall/explorable space generator.
     */
    @Test
    public void testWallOrSpace() {
        int testValue = 0;
        int testProbability = 45;
        Random rand = new Random(1337);
        Dungeon testDungeon = new Dungeon(50, 50, 45, 1337); //wall probability 45
        for(int i = 0; i < 100; i++) {
            if(testProbability >= rand.nextInt(100)) {
                testValue = 1;
            }
            else {
                testValue = 0;
            }
            assertEquals(testValue, testDungeon.wallOrSpace());
        }
    }
    
    /**
     * Tests that isOutOfBounds() calculates the boundaries of the dungeon correctly.
     */ 
    @Test
    public void testIsOutOfBounds() {
        Dungeon testDungeon = new Dungeon(5, 5, 15, 1337);
        
        assertEquals(true, testDungeon.isOutOfBounds(-1, 0));
        assertEquals(true, testDungeon.isOutOfBounds(0, -1));
        assertEquals(true, testDungeon.isOutOfBounds(-1, -1));
        assertEquals(true, testDungeon.isOutOfBounds(5, 0));
        assertEquals(true, testDungeon.isOutOfBounds(0, 5));
        assertEquals(true, testDungeon.isOutOfBounds(5, 5));
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                assertEquals(false, testDungeon.isOutOfBounds(i, j));
            }
        }
    }
    
    /**
     * Tests whether the values of the two-dimensional array created using 
     * MakeDungeon() matches the correct hand-calculated values.
     *       *** AFTER makeDungeon() ***
     *              #####
     *              #####
     *              #..##
     *              #####
     *              #####
     */
    @Test
    public void testMakeDungeon() {
        Dungeon testDungeon = new Dungeon(5, 5, 15, 1337);
        testDungeon.initializeDungeon();
        testDungeon.makeDungeon();
        int[][] dungeonArray = testDungeon.getDungeonArray();
        
        int[][] testArray = new int[5][5];
        //form int[5][5] test array with hand-calculated values of makeDungeon() using 1337 as seed.
        for(int i = 0; i < 5; i++) {
            testArray[0][i] = 1;
            testArray[1][i] = 1;
            testArray[2][i] = 1;
            testArray[3][i] = 1;
            testArray[4][i] = 1;
        }
        testArray[2][1] = 0;
        testArray[2][2] = 0;
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                assertEquals(testArray[i][j], dungeonArray[i][j]);
            }
        }
    }
    
    /**
     * Tests that the isWall() function returns true if the given tile (x,y) is a wall and false otherwise.
     */
    @Test
    public void testIsWall() {
        Dungeon testDungeon = new Dungeon(5, 5, 15, 1337);
        testDungeon.initializeDungeon();
        
        assertEquals(true, testDungeon.isWall(0, 0));
        assertEquals(false, testDungeon.isWall(1, 2));
        assertEquals(true, testDungeon.isWall(2, 3));
        assertEquals(false, testDungeon.isWall(3, 1));
        assertEquals(true, testDungeon.isWall(4, 4));
    }
    
    /**
     * Tests that getAdjacentWalls calculates the amount of walls correctly.
     * @param x x coordinate of the tile for which the number of adjacent walls is calculated.
     * @param y y coordinate of the tile for which the number of adjacent walls is calculated.
     */
    @Test
    public void testGetAdjacentWalls() {
        Dungeon testDungeon = new Dungeon(5, 5, 15, 1337); //test Dungeon with seed 1337.
        testDungeon.initializeDungeon();
        
        assertEquals(7, testDungeon.getAdjacentWalls(0, 0));
        assertEquals(6, testDungeon.getAdjacentWalls(0, 1));
        assertEquals(4, testDungeon.getAdjacentWalls(1, 2));
        assertEquals(3, testDungeon.getAdjacentWalls(2, 1));
        assertEquals(5, testDungeon.getAdjacentWalls(3, 1));
        assertEquals(1, testDungeon.getAdjacentWalls(2, 2));
        assertEquals(6, testDungeon.getAdjacentWalls(3, 3));
    }
    
    /**
     * Tests that the rules according to which walls and explorable spaces are placed into the dungeon are correct. 
     * PlaceWallLogic takes a tile (x,y) and returns 1 if a wall is to be placed there, 0 otherwise.
     * This test only tests the resulting tiles based on the starting tiles. 
     * The iterative algorithm is tested with testMakeDungeon().
     * 
     *       *** AFTER initializeDungeon() ***
     *                #####
     *                #...#
     *                #..##
     *                #...#
     *                #####
     */
    @Test
    public void testPlaceWallLogic() {
        Dungeon testDungeon = new Dungeon(5, 5, 15, 1337);
        testDungeon.initializeDungeon();
        assertEquals(1, testDungeon.placeWall(1, 1));
        assertEquals(0, testDungeon.placeWall(2, 1));
        assertEquals(0, testDungeon.placeWall(2, 2));
        assertEquals(0, testDungeon.placeWall(2, 3));
        assertEquals(1, testDungeon.placeWall(3, 3));
        assertEquals(1, testDungeon.placeWall(4, 4));
    }
    
    /**
     * This test will be added later as the final version will not contain a command line print but a UI.
     */
    @Test
    public void testPrintDungeon() {
        assertEquals(true, true);
    }
    
    
}
