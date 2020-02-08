
package testdungeon;

import ruins.Ruins;
import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This is the test class for Ruins.
 * The Ruins parameters used to test its functionality are as follows:
 * Ruins ruins = new Ruins(10, 10, 15, 1005); and 
 * it results in the following ruins map:
 * 
 * Legend integer keys in testing:
 * 
 *      . = 0 = space
 *      # = 1 = wall
 *      o = 2 = space perimeter outside the house
 *      _ = 3 = space inside a house structure
 *      0 = 4 = hole in the house wall to permit access
 * 
 * after initializeRuins():
 * 
 *      ##########
 *      #........#
 *      #........#
 *      #........#
 *      #........#
 *      #........#
 *      #........#
 *      #........#
 *      #........#
 *      ##########
 * 
 * after createRuins():
 * 
 *      ##########
 *      #oooooooo#
 *      #o######o#
 *      #o#____#o#
 *      #o#____#o#
 *      #o#____#o#
 *      #o#____0o#
 *      #o#____#o#
 *      #oo____oo#
 *      ##########
 * 
 * 
 * 
 * 
 * @author lauri
 */
public class TestRuins {
    
    /**
     * Tests that initializeRuins() forms an empty map with walls
     */
    @Test
    public void testInitializeRuins() {
        Ruins testRuins = new Ruins(10, 10, 15, 1005);
        testRuins.initializeRuins();
        int ruinsArray[][] = testRuins.getRuinsArray();
        int[][] testArray = new int[10][10];
        
        //form int[10][10] test array with hand-calculated values of initializeDungeon() using 1005 as seed.
        for (int i = 0; i < 10; i++) {
            testArray[0][i] = 1;
            testArray[i][0] = 1;
            testArray[9][i] = 1;
            testArray[i][9] = 1;
        }
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(testArray[i][j], ruinsArray[i][j]);
            }
        }
    }
    
    /**
     * Checks that getRuinsArray() correctly returns an empty array.
     */
    @Test
    public void testGetRuinsArray() {
        Ruins testRuins = new Ruins(10, 10, 15, 1005);
        int[][] ruinsArray = testRuins.getRuinsArray();
        int[][] testArray = new int[10][10];
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(testArray[i][j], ruinsArray[i][j]);
            }
        }
    }
    
    /**
     * Checks that getRuinsWidth() returns the correct width.
     */
    @Test
    public void testGetRuinsWidth() {
        Ruins testRuins = new Ruins(10, 10, 15, 1005);
        assertEquals(10, testRuins.getRuinsWidth());
    }
    
    /**
     * Checks that getRuinsHeight() returns the correct height.
     */
    @Test
    public void testGetRuinsHeight() {
        Ruins testRuins = new Ruins(10, 10, 15, 1005);
        assertEquals(10, testRuins.getRuinsHeight());
    }
    
    /**
     * Tests that getRandomStartPoint() correctly returns 
     * hand-calculated values for three coordinates. The expected value for 
     * coordinate x is the nth value in the Random(10) nextInt sequence and
     * the value for y is the n+3th value, where n >= 1. 
     * The first values in the sequence are 5, 7, 1, 1, 3, 0, so the first
     * coordinate needs to be (5, 1).
     */
    @Test
    public void testGetRandomStartPoint() {
        Ruins testRuins = new Ruins(10, 10, 15, 1005);
        int[] t = new int[]{5, 1};
        assertEquals(t[0], testRuins.getRandomStartPoint()[0]);
        assertEquals(t[1], testRuins.getRandomStartPoint()[1]);
        
        int[] a = new int[]{3, 7};
        assertEquals(a[0], testRuins.getRandomStartPoint()[0]);
        assertEquals(a[1], testRuins.getRandomStartPoint()[1]);
        
        int[] b = new int[]{7, 5};
        assertEquals(b[0], testRuins.getRandomStartPoint()[0]);
        assertEquals(b[1], testRuins.getRandomStartPoint()[1]);
    }

    /**
     * Tests that isOutOfBounds() calculates the boundaries of 
     * the dungeon correctly.
     */
    @Test
    public void testIsOutOfBounds() {
        Ruins testRuins = new Ruins(10, 10, 15, 1005);
        
        assertEquals(true, testRuins.isOutOfBounds(-1, 0));
        assertEquals(true, testRuins.isOutOfBounds(0, -1));
        assertEquals(true, testRuins.isOutOfBounds(-1, -1));
        assertEquals(true, testRuins.isOutOfBounds(9, 0));
        assertEquals(true, testRuins.isOutOfBounds(0, 9));
        assertEquals(true, testRuins.isOutOfBounds(9, 9));
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                assertEquals(false, testRuins.isOutOfBounds(i, j));
            }
        }
    }
    
    /**
     * Tests that createHole() method places the hole in the hand-calculated
     * location.
     * The test is continued until all possible random values 0-3 
     * have been tested.
     */
    @Test
    public void testCreateHole() {
        Ruins testRuins = new Ruins(10, 10, 15, 1005);

        // creates a hole to all possible four locations.
        for(int i = 0; i < 15; i++) {
            testRuins.createHole(2, 2, 5, 5);
        }
        
        int[][] ruinsArray = testRuins.getRuinsArray();
        
        int count = 0; //counter for the number of holes

        // counts the number of holes on the map. 
        // Should be 4 (north-east-south-west)
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if (ruinsArray[i][j] == 4) {
                    count++;
                }
            }
        }
        assertEquals(4, count);
    }
    
    
    
    /**
     * Test to check that the hand-calculated array matches the ruins map array.
     */
    @Test
    public void testCreateRuins() {
        Ruins testRuins = new Ruins(10, 10, 15, 1005);
        testRuins.initializeRuins();
        testRuins.createRuins();
        int[][] ruinsArray = testRuins.getRuinsArray();
        int[][] testArray = new int[10][10];
        
        
        // create the expected array based on hand calculations
        // # outer walls
        for (int i = 0; i < 10; i++) {
            testArray[0][i] = 1;
            testArray[i][0] = 1;
            testArray[9][i] = 1;
            testArray[i][9] = 1;
        }
        
        // o
        for (int i = 1; i < 9; i++) {
            testArray[1][i] = 2;
            testArray[i][1] = 2;
            testArray[i][8] = 2;
        }
        
        // #
        for (int i = 2; i < 8; i++) {
            testArray[2][i] = 1;
            testArray[i][2] = 1;
            testArray[i][7] = 1;
        }
        
        // _
        for (int i = 3; i < 9; i++) {
            for (int j = 3; j < 7; j++) {
                testArray[i][j] = 3;
            }
        }

        testArray[8][2] = 2; // o
        testArray[8][7] = 2; // o
        testArray[6][2] = 4; // 0
        
        
        // test that each element in the arrays are equal.
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(testArray[i][j], ruinsArray[i][j]);
            }
        }
        
        
    }
    
}
