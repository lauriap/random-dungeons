
package speedtest;

import dungeon.Dungeon;
import ruins.Ruins;

/**
 * Tests how long it takes to create dungeons of different sizes and prints
 * the results into the terminal. Only tests the speed needed for map creation,
 * not for printing.
 * @author lauri
 */
public class DungeonSpeedTest {
    
    long startTime = 0;
    long endTime = 0;
    
    /**
     * Resets timer between tests.
     */
    public void resetTimer() {
        this.startTime = 0;
        this.endTime = 0;
    }
    
    /**
     * Tests speed for Dungeon type map.
     * @param height
     * @param width
     * @param probability
     * @param seed 
     */
    public void speedTestDungeon(int height, int width, 
            int probability, int seed) {
        System.out.println("***** BEGIN TEST *****");
        System.out.println("Dungeon, size " + height + "x" + width);
        this.startTime = System.nanoTime();
        Dungeon dung = new Dungeon(height, width, probability, seed);
        dung.initializeDungeon();
        dung.makeDungeon();
        dung.makeFloodedMap();
        this.endTime = System.nanoTime();
        System.out.println("Time elapsed: " + (this.endTime - this.startTime) + " ns");
        System.out.println("Time elapsed: " + ((this.endTime - this.startTime) / 1e9) + " s");
        this.resetTimer();
        System.out.println("***** END TEST");
        System.out.println();
    }
    
    /**
     * Tests speed for Ruins type map.
     * @param height
     * @param width
     * @param probability
     * @param seed 
     */
    public void speedTestRuins(int height, int width, 
            int probability, int seed) {
        System.out.println("***** BEGIN TEST *****");
        System.out.println("Ruins, size " + height + "x" + width);
        this.startTime = System.nanoTime();
        Ruins ruins = new Ruins(height, width, probability, seed);
        ruins.initializeRuins();
        ruins.createRuins();
        this.endTime = System.nanoTime();
        System.out.println("Time elapsed: " + (this.endTime - this.startTime) + " ns");
        System.out.println("Time elapsed: " + ((this.endTime - this.startTime) / 1e9) + " s");
        this.resetTimer();
        System.out.println("***** END TEST *****");
        System.out.println();
    }
    
    /**
     * Main class for testing purposes only.
     */
    /*
    public static void main(String[] args) {
        
        DungeonSpeedTest speedTest = new DungeonSpeedTest();
        
        //speedTest.speedTestDungeon(100, 100, 40, 0);
        speedTest.speedTestRuins(5000, 5000, 15, 0);
        
        
    }
    */
    
    
}
