package dungeon;

import java.util.Random;

/**
 * Contains the methods for creating and generating a random dungeon.
 * @author lauri
 */
public class Dungeon {
    
    Random rand = new Random();
    char[][] dungeon;
    int dungeonWidth;
    int dungeonHeight;
    int wallCount;
    
    /**
     * Constructor for a new Dungeon.
     * @param n number of rows
     * @param m number of columns
     * @param wallcount how many tiles of walls are used in the initialization phase
     */
    public Dungeon(int n, int m, int wallcount) {
        this.dungeonHeight = n;
        this.dungeonWidth = m;
        this.wallCount = wallcount;
        
        this.initializeDungeon();
    }
    
    /**
     * Places wall blocks at random locations to a dungeon matrix (map) as the first step towards creating a random dungeon.
     */
    public void initializeDungeon() {
        this.dungeon = new char[this.dungeonHeight][this.dungeonWidth];
        
        
        
    }
    
    // jatka tästä: http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels
    // https://tiralabra.github.io/2020_p3/fi/aikataulu/
    // https://tiralabra.github.io/2020_p3/
    // https://github.com/lauriap/random-dungeons
    
}
