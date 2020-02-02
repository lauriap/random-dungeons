
package ruins;

import java.util.Random;

/**
 * This class creates a random ruins-type map with rectangular, broken structures.
 * @author lauri
 */
public class Ruins {
    
    Random rand;
    int[][] ruins;
    int ruinsWidth;
    int ruinsHeight;
    int wallProbability;
    int testSeed;
    
    /**
     * Constructor for a new Ruins.
     * @param n number of rows
     * @param m number of columns
     * @param p probability of a tile being a wall in the initialization phase
     * @param seed used for testing. 0 = random ruins (normal use), 
     * > 0 = reconstructable ruins (testing use). Standard test seed = 1337.
     */
    public Ruins(int n, int m, int p, int seed) {
        this.ruinsHeight = n;
        this.ruinsWidth = m;
        this.wallProbability = p;
        this.ruins = new int[this.ruinsHeight][this.ruinsWidth];
        
        if(seed != 0) { //seed is 0 for normal use, above 0 for testing.
            this.rand = new Random(seed);
        }
        else {
            this.rand = new Random();
        }
    }
    
    /**
     * Returns the ruins map as a two-dimensional int array.
     * @return two-dimensional ruins array, 1 = wall, 0 = free space
     */
    public int[][] getRuinsArray() {
        return this.ruins;
    }
    
        /**
     * Getter for ruinsWidth.
     * @return int value for ruins width (10-1000).
     */
    public int getRuinsWidth() {
        return this.ruinsWidth;
    }
    
    /**
     * Getter for ruinsHeight.
     * @return int value for ruins height (10-1000).
     */
    public int getRuinsHeight() {
        return this.ruinsHeight;
    }
    
    /**
     * Creates map borders for the ruins.
     */
    public void initializeRuins() {
        
        for(int column = 0, row = 0; row < this.ruinsHeight; row++) {
            for(column = 0; column < ruinsWidth; column++) {
                
                if(column == 0) { //create borders when column or row is 0 or max height/width
                    this.ruins[column][row] = 1;
                }
                else if(row == 0) {
                    this.ruins[column][row] = 1;
                }
                else if(column == this.ruinsWidth-1) {
                    this.ruins[column][row] = 1;
                }
                else if(row == this.ruinsHeight-1) {
                    this.ruins[column][row] = 1;
                }
            }
        }
    }
    
    
    
    
    /**
     * Creates a house in ruins with a free tile around each wall piece.
     * @param x x coordinate of the upper right corner of the house
     * @param y y coordinate of the upper right corner of the house
     * @param width house width
     * @param height house length
     */
    public void createHouse(int x, int y, int width, int height) {
        // LAITA HUONEILLE MINIMIKOKO ETTEI TULE TYPERIÄ MUOTOJA! JATKA SEINIEN REI'ITTÄMISELLÄ
        for(int i = x; i <= x+height; i++) {
            // create free space around the house
            this.placeSpace(i, y);
            //this.ruins[i][y] = 2;
            this.placeSpace(i, y+width);
            //this.ruins[i][y+width] = 2;

        for(int j = y; j <= y+width; j++) {
            this.placeSpace(x, j);
            this.placeSpace(x+height, j);
            //this.ruins[x][j] = 2;
            //this.ruins[x+height][j] = 2;
        }
            // create left and right walls
            this.placeWall(i, y+1);
            //this.ruins[i][y+1] = 1;
            this.placeWall(i, y+width-1);
            //this.ruins[i][y+width-1] = 1;
        }
        
        for(int z = y+1; z <= y+width-1; z++) {
            // create upper and lower walls
            this.placeWall(x+1, z);
            //this.ruins[x+1][z] = 1;
            this.placeWall(x+height-1, z);
            //this.ruins[x+height-1][z] = 1;
        }
        
        // place house space inside the house ruins
        for(int k = x+2; k < x+height-1; k++) {
            for(int l = y + 2; l < y+width-1; l++) {
                this.placeHouseSpace(k, l);
            }
        }
        
        
    }
    
     /**
     * Places a wall on a map if fits.
     * Rules:
     * Tiles next to walls or borders are always free spaces.
     * Otherwise set tile to wall.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void placeWall(int x, int y) { 
        if(this.isOutOfBounds(x, y)) {
            return;
        }
        else if(this.ruins[x][y] == 1 || this.ruins[x][y] == 2) {
            return;
        }
        else if(x == 1 || x == (this.getRuinsHeight()-2) || y == 1 || y == (this.getRuinsWidth()-2)) {
            this.ruins[x][y] = 2;
        }
        else {
            this.ruins[x][y] = 1;
        }
    }
    
     /**
     * Places a free space on a around a house if fits.
     * Rules:
     * Tiles next to walls or borders are always free spaces. Controlled with method isOutOfBounds.
     * Otherwise place a free space.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void placeSpace(int x, int y) {
        if(this.isOutOfBounds(x, y)) {
            return;
        }
        else if(this.ruins[x][y] == 1 || this.ruins[x][y] == 2) {
            return;
        }
        else {
            this.ruins[x][y] = 2;
        }
    }
    
         /**
     * Places a house space inside a house if it fits.
     * Rules:
     * Tiles next to walls or borders are always free spaces. Controlled with method isOutOfBounds.
     * Otherwise place a free space.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void placeHouseSpace(int x, int y) {
        if(this.isOutOfBounds(x, y)) {
            return;
        }
        else if(this.ruins[x][y] == 1 || this.ruins[x][y] == 3) {
            return;
        }
        else {
            this.ruins[x][y] = 3;
        }
    }
    
    
        /**
     * Determines whether a tile is set to be a wall or explorable space based on probability. 
     * If the wall probability (user input from Ruins constructor) is higher 
     * than a random number between 0-100, returns 1 ( = wall).
     * Otherwise returns 0.
     * @return 1 = wall, 0 = explorable space 
     */
    public int wallOrSpace() {
        if(this.wallProbability >= this.rand.nextInt(100)) {
            return 1;
        }
        return 0;
    }
    
        /**
     * Checks whether the parameter tile is out of bounds.
     * @param x x coordinate
     * @param y y coordinate
     * @return true = out of bounds, false = not out of bounds
     */
    public boolean isOutOfBounds(int x, int y) {
        if( x < 0 || y < 0) {
            return true;
            }
            else if(x > this.ruinsWidth-2 || y > this.ruinsHeight-2 ) {
                return true;
            }
        return false;
    }
    
    
    
    /**
     * Checks if the tile (x,y) is adjacent to map borders.
     * @param x x coordinate
     * @param y y coordinate
     * @return true = is adjacent to a border, false = is not
     */
    /*
    public boolean isAdjacentToBorder(int x, int y) {
        if(x == 1 || x == (this.getRuinsWidth() - 2)) {
            return true;
        }
        else if(y == 1 || y == (this.getRuinsHeight() - 2)) {
            return true;
        }
        
        return false;
    }
    */
    
    
    /**
     * This method is used solely for testing. Prints the ruins layout to the command line.
     */
    public void printRuins() {
        char w = '#';
        char p = '.';
        String s = "";
        for(int column = 0, row = 0; row <= this.ruinsHeight-1; row++) {
            for(column = 0; column <= this.ruinsHeight-1; column++) {
                if(this.ruins[row][column] == 1) {
                    String otherString = s + '#';
                    s = otherString;
                }
                else if(this.ruins[row][column] == 2) {
                    String otherString = s + 'o';
                    s = otherString;
                }
                else if(this.ruins[row][column] == 3) {
                    String otherString = s + '_';
                    s = otherString;
                }
                else {
                    String otherString = s + '.';
                    s = otherString;
                }
            }
        String endOfRowString = s + "\n";
        s = endOfRowString;
        }
        System.out.println(s);
    }
    
    public static void main(String[] args) {
        Ruins rauniot = new Ruins(15, 15, 40, 0);
        rauniot.initializeRuins();
        rauniot.createHouse(7, 2, 6, 5);
        rauniot.createHouse(0, 0, 4, 5);
        //rauniot.createHouse(5, 2, 4, 9);
        rauniot.printRuins();
    }
    
}
