package dungeon;

import java.util.Random;

/**
 * Contains the methods for creating and generating a random dungeon.
 * @author lauri
 */
public class Dungeon {
    
    Random rand;
    int[][] dungeon;
    int dungeonWidth;
    int dungeonHeight;
    int wallProbability;
    int testSeed;
    
    /**
     * Constructor for a new Dungeon.
     * @param n number of rows
     * @param m number of columns
     * @param p probability of a tile being a wall in the initialization phase
     * @param seed used for testing. 0 = random dungeon (normal use), > 0 = reconstructable dungeon (testing). Standard test seed = 1337.
     */
    public Dungeon(int n, int m, int p, int seed) {
        this.dungeonHeight = n;
        this.dungeonWidth = m;
        this.wallProbability = p;
        this.dungeon = new int[this.dungeonHeight][this.dungeonWidth];
        
        if(seed != 0) { //seed is 0 for normal use, above 0 for testing.
            this.rand = new Random(seed);
        }
        else {
            this.rand = new Random();
        }
        
    }
    
    
    public int[][] getDungeonArray() {
        return this.dungeon;
    }
    
    /**
     * Getter for dungeonWidth.
     * @return int value for dungeon width (10-1000).
     */
    public int getDungeonWidth() {
        return this.dungeonWidth;
    }
    
    /**
     * Getter for dungeonHeight.
     * @return int value for dungeon height (10-1000).
     */
    public int getDungeonHeight() {
        return this.dungeonHeight;
    }
    
    /**
     * Getter for wallProbability.
     * @return int value for wall probability (0-100).
     */
    public int getWallProbability() {
        return this.wallProbability;
    }
    
    /**
     * Places wall blocks at random locations to a dungeon matrix (map) as the first step towards creating a random dungeon.
     */
    public void initializeDungeon() {
        
        int temp = 0; //temporary variable
        
        for(int column = 0, row = 0; row < this.dungeonHeight; row++) {
            for(column = 0; column < dungeonWidth; column++) {
                
                if(column == 0) { //create borders when column or row is 0 or max height/width
                    this.dungeon[column][row] = 1;
                }
                else if(row == 0) {
                    this.dungeon[column][row] = 1;
                }
                else if(column == this.dungeonWidth-1) {
                    this.dungeon[column][row] = 1;
                }
                else if(row == this.dungeonHeight-1) {
                    this.dungeon[column][row] = 1;
                }
                else { //if not next to dungeon border, place wall blocks at random locations
                    temp = this.dungeonHeight / 2;
                    if(row == temp) {
                        this.dungeon[column][row] = 0; // ensures that the middle of the map is explorable
                    }
                    else {
                        this.dungeon[column][row] = WallOrSpace();
                    }
                }
            }
        }
    }
    
    /**
     * Determines whether a tile is set to be a wall or explorable space based on probability. 
     * If the wall probability (user input from Dungeon constructor) is higher 
     * than a random number between 0-100, returns 1 ( = wall).
     * Otherwise returns 0.
     * @return 1 = wall, 0 = explorable space 
     */
    public int WallOrSpace() {
        if(this.wallProbability >= this.rand.nextInt(100)) {
            return 1;
        }
        return 0;
    }
    
    
    public void MakeDungeon() {
        for(int column=0, row=0; row <= this.dungeonHeight-1; row++) {
            for(column = 0; column <= this.dungeonWidth-1; column++) {
                this.dungeon[column][row] = PlaceWallLogic(column,row);
            }
        }
    }
    
    /**
     * Morphs tiles into spaces or walls based on predetermined rules.
     * Rules:
     * If a wall tile has fewer than 2 adjacent wall tiles, return 0 ( = space).
     * If a wall tile has more than 4 adjacent wall tiles, return 1 ( = wall).
     * If a space tile has more than 5 adjacent wall tiles, return 1 ( = wall).
     * Else, return 0 ( = space).
     * @param x x coordinate
     * @param y y coordinate
     * @return 1 = wall tile, 0 = explorable space tile
     */
    public int PlaceWallLogic(int x, int y) {
        int numWalls = getAdjacentWalls(x, y, 1, 1);
        
        if(this.dungeon[x][y] == 1) {
            if(numWalls >= 4) {
                return 1;
            }
            if(numWalls < 2) {
                return 0;
            }
        }
        else {
            if(numWalls >= 5) {
                return 1;
            }
        }
        return 0;
    }
    
    /**
     * Returns the number of wall tiles adjacent to the original tile. If a wall tile is out of dungeon bounds, it is counted as a wall ( = 1).
     * @param x starting point x parameter
     * @param y starting point y parameter
     * @param scopeX
     * @param scopeY
     * @return 
     */
    public int getAdjacentWalls(int x, int y, int scopeX, int scopeY) {
        int startX = x - scopeX;
        int startY = y - scopeY;
        int endX = x + scopeX;
        int endY = y + scopeY;
        
        int iX = startX;
        int iY = startY;
        
        int wallCounter = 0;
        
        for(iY = startY; iY <= endY; iY++) {
            for(iX = startX; iX <= endX; iX++) {
                if(!(iX==x && iY==y)) {
                    if(IsWall(iX,iY)) {
                        wallCounter += 1;
                    }
                }
            }
        }
        return wallCounter;
    }
    
    /**
     * Checks if the input tile (x,y) is a wall.
     * @param x
     * @param y
     * @return 
     */
    public boolean IsWall(int x,int y) {
        // Checks if the wall is out of bounds
        if( IsOutOfBounds(x,y)) {
            return true;
        }

        if( this.dungeon[x][y] == 1) {
            return true;
        }

        if(this.dungeon[x][y] == 0) {
            return false;
        }
        
        return false;
    }
    
    /**
     * Checks whether the parameter tile is out of bounds.
     * @param x
     * @param y
     * @return 
     */
    public boolean IsOutOfBounds(int x, int y) {
        if( x < 0 || y < 0) {
            return true;
            }
            else if(x > this.dungeonWidth-1 || y > this.dungeonHeight-1 ) {
                return true;
            }
        return false;
    }
 
    
    public void printDungeon() {
        char w = '#';
        char p = '.';
        for(int column = 0, row = 0; row <= this.dungeonHeight-1; row++) {
            String s = "";
            for(column = 0; column <= this.dungeonHeight-1; column++) {
                if(this.dungeon[row][column] == 1) {
                    String otherString = s + '#';
                    s = otherString;
                }
                else {
                    String otherString = s + '.';
                    s = otherString;
                }
            }
        System.out.println(s);
        }
    }
    
    public static void main(String[] args) {
        Dungeon luola = new Dungeon(50, 50,45, 1337);
        luola.initializeDungeon();
        System.out.println("After initialization:");
        luola.printDungeon();
        luola.MakeDungeon();
        System.out.println("After creation:");
        luola.printDungeon();
        
        System.out.println("***** TOINEN LUOLA*****");
        Dungeon toinenLuola = new Dungeon(5, 5, 15, 1337);
        toinenLuola.initializeDungeon();
        toinenLuola.printDungeon();
        toinenLuola.MakeDungeon();
        System.out.println("MakeDungeon:");
        toinenLuola.printDungeon();
    }

    
    // jatka tästä: http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels
    // https://tiralabra.github.io/2020_p3/fi/aikataulu/
    // https://tiralabra.github.io/2020_p3/
    // https://github.com/lauriap/random-dungeons
    
}
