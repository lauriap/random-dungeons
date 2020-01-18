package dungeon;

import java.util.Random;

/**
 * Contains the methods for creating and generating a random dungeon.
 * @author lauri
 */
public class Dungeon {
    
    Random rand = new Random();
    char[][] dungeon;
    
    /**
     * Constructor for a new Dungeon.
     * @param n number of rows
     * @param m number of columns
     * @param wallcount how many tiles of walls are used in the initialization phase
     */
    public Dungeon(int n, int m, int wallcount) {
        this.dungeon = new char[n][m];
    }
    
    // jatka tästä: http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels
    // https://tiralabra.github.io/2020_p3/fi/aikataulu/
    // https://tiralabra.github.io/2020_p3/
    // https://github.com/lauriap/random-dungeons
    
}
