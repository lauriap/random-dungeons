
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
        testArray[6][7] = 4; // 0
        
        
        // test that each element in the arrays are equal.
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(testArray[i][j], ruinsArray[i][j]);
            }
        }
        
        
    }
    
}
