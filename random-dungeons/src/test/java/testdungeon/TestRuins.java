
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
 * . = 0 = space
 * # = 1 = wall
 * o = 2 = space perimeter outside the house
 * _ = 3 = space inside a house structure
 * 0 = 4 = hole in the house wall to permit access
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
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(testArray[i][j], ruinsArray[i][j]);
            }
        }
    }
    
}
