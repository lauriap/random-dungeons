package dungeon;

import randomnumgen.RandomNumGen;

/**
 * Contains the methods for creating and generating a random dungeon.
 * @author lauri
 */
public class Dungeon {
    
    RandomNumGen rand;
    int[][] dungeon;
    int[][] fillmap;
    int[][] visited;
    int dungeonWidth;
    int dungeonHeight;
    int wallProbability;
    
    /**
     * Constructor for a new Dungeon.
     * @param n number of rows
     * @param m number of columns
     * @param p probability of a tile being a wall in the initialization phase
     * @param seed used for testing. 0 = random dungeon (normal use), 
     * > 0 = reconstructable dungeon (testing use). Standard test seed = 1337.
     */
    public Dungeon(int n, int m, int p, int seed) {
        this.dungeonHeight = n;
        this.dungeonWidth = m;
        this.wallProbability = p;
        this.visited = new int[this.dungeonHeight][this.dungeonWidth];
        this.dungeon = new int[this.dungeonHeight][this.dungeonWidth];
        
        if (seed != 0) { //seed is 0 for normal use, above 0 for testing.
            this.rand = new RandomNumGen(seed);
        
        } else {
            this.rand = new RandomNumGen();
        }
        
        // set all tiles in fillmap to 1.
        this.fillmap = new int[this.dungeonHeight][this.dungeonWidth];
        for (int i = 0; i < this.dungeonHeight; i++) {
            for (int j = 0; j < this.dungeonWidth; j++) {
                this.fillmap[i][j] = 1;
            }
        }
        
    }
    
    /**
     * Returns the dungeon map as a two-dimensional int array.
     * @return two-dimensional dungeon array, 1 = wall, 0 = free space
     */
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
     * Method for setting a map tile to a desired integer value.
     * Used only for testing.
     * @param x x coordinate
     * @param y y coordinate
     * @param value tile value
     */
    public void setTile(int x, int y, int value) {
        this.dungeon[x][y] = value;
    }
    
    /**
     * Places wall blocks at random locations to a dungeon matrix (map) as
     * the first step towards creating a random dungeon.
     */
    public void initializeDungeon() {
        
        int temp = 0; //temporary variable
        
        for (int column = 0, row = 0; row < this.dungeonHeight; row++) {
            for (column = 0; column < dungeonWidth; column++) {
                
                //create borders when column or row is 0 or max height/width
                if (column == 0) { 
                    this.dungeon[column][row] = 1;
                
                } else if (row == 0) {
                    this.dungeon[column][row] = 1;
                
                } else if (column == this.dungeonWidth - 1) {
                    this.dungeon[column][row] = 1;
                
                } else if (row == this.dungeonHeight - 1) {
                    this.dungeon[column][row] = 1;
                
                //if not next to dungeon border, 
                //place wall blocks at random locations
                } else { 
                    temp = this.dungeonHeight / 2;
                    if (row == temp) {
                        // ensures that the middle of the map is explorable
                        this.dungeon[column][row] = 0; 
                    
                    } else {
                        this.dungeon[column][row] = wallOrSpace();
                    }
                }
            }
        }
    }
    
    /**
     * Determines whether a tile is set to be a wall 
     * or explorable space based on probability. 
     * If the wall probability (user input from Dungeon constructor) is higher 
     * than a random number between 0-100, returns 1 ( = wall).
     * Otherwise returns 0.
     * @return 1 = wall, 0 = explorable space 
     */
    public int wallOrSpace() {
        if (this.wallProbability >= this.rand.nextInt(100)) {
            return 1;
        }
        return 0;
    }
    
    /**
     * Creates a random dungeon from an initialized dungeon 
     * by invoking placeWall on all tiles. 
     */
    public void makeDungeon() {
        for (int column = 0, row = 0; row <= this.dungeonHeight - 1; row++) {
            for (column = 0; column <= this.dungeonWidth - 1; column++) {
                this.dungeon[column][row] = placeWall(column,row);
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
    public int placeWall(int x, int y) {
        int numberOfWalls = getAdjacentWalls(x, y);
        
        if (this.dungeon[x][y] == 1) {
            if (numberOfWalls >= 4) {
                return 1;
            }
            if (numberOfWalls < 2) {
                return 0;
            }
        } else {
            if (numberOfWalls >= 5) {
                return 1;
            }
        }
        return 0;
    }
    
    /**
     * Returns the number of wall tiles adjacent to the original tile. 
     * If a wall tile is out of dungeon bounds, it is counted as a wall ( = 1).
     * @param x starting point x parameter
     * @param y starting point y parameter
     * @return 
     */
    public int getAdjacentWalls(int x, int y) {
        int startX = x - 1;
        int startY = y - 1;
        int endX = x + 1;
        int endY = y + 1;
        
        int wallCounter = 0;
        
        for (int iY = startY; iY <= endY; iY++) {
            for (int iX = startX; iX <= endX; iX++) {
                if (!(iX == x && iY == y)) {
                    if (isWall(iX,iY)) {
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
    public boolean isWall(int x,int y) {
        // Checks if the wall is out of bounds
        if (isOutOfBounds(x,y)) {
            return true;
        }

        if (this.dungeon[x][y] == 1) {
            return true;
        }

        if (this.dungeon[x][y] == 0) {
            return false;
        }
        
        return false;
    }
    
    /**
     * Checks whether the parameter tile is out of bounds.
     * @param x x coordinate
     * @param y y coordinate
     * @return true = out of bounds, false = not out of bounds
     */
    public boolean isOutOfBounds(int x, int y) {
        if (x < 0 || y < 0) {
            return true;
            
        } else if (x > this.dungeonWidth - 1 || y > this.dungeonHeight - 1) {
            return true;
        } 
        
        return false;
    }
    
    /**
     * Returns the coordinates of a free tile in the middle of the map for
     * initiating flood fill. Flood fill is used to remove isolated cave spaces.
     * @return coordinates (x, y) in array format.
     */
    public int[] getFillStartPoint() {
        int height = this.getDungeonHeight();
        int width = this.getDungeonWidth();
        int[][] dungeonArray = this.getDungeonArray();
        
        int x = this.getDungeonHeight() / 2;
        int y = this.getDungeonWidth() / 2;
        
        // checks rows downwars from the middle of the map
        for (int i = x; i < height; i++) {
            if (dungeonArray[i][y] == 0) {
                return new int[]{i, y};
            }
        }
        
        // checks rows upwards
        for (int i = x; i > 0; i--) {
            if (dungeonArray[i][y] == 0) {
                return new int[]{i, y};
            }
        }
        
        // checks columns to the left
        for (int i = y; i > 0; i--) {
            if (dungeonArray[x][i] == 0) {
                return new int[]{x, i};
            }
        }
        
        // checks columns to the right
        for (int i = y; i < width; i++) {
            if (dungeonArray[x][i] == 0) {
                return new int[]{x, i};
            }
        }
        
        // if no tiles free are found, 
        // checks all tiles from the beginning of the map
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (dungeonArray[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{x, y};
    }
    
    /**
     * Flood fill method to turn fillmap into a dungeon array with only
     * one open space.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void floodFill(int x, int y) {
        
        // return if out of bounds
        if (this.isOutOfBounds(x, y)) {
            return;
        }
        
        if (this.visited[x][y] != 1) {
            
            this.visited[x][y] = 1;
            
            // return if the tile is a wall
            if (this.dungeon[x][y] == 1) {
                return;
            // if the tile is free, mark it as free into the fillmap
            } else if (this.dungeon[x][y] == 0) {
                this.fillmap[x][y] = 0;
            }
            
            // recursively check all neighbouring tiles
            this.floodFill(x-1, y-1);
            this.floodFill(x-1, y);
            this.floodFill(x-1, y+1);
            this.floodFill(x, y-1);
            this.floodFill(x, y+1);
            this.floodFill(x+1, y-1);
            this.floodFill(x+1, y);
            this.floodFill(x+1, y+1);
        }
    }
    
    /**
     * Creates a dungeon map that has no unexplorable pockets but a single
     * explorable space using flood fill. This is the map version which
     * will be showed to the user.
     */
    public void makeFloodedMap() {
        int[] coordinates = this.getFillStartPoint();
        this.floodFill(coordinates[0], coordinates[1]);
    }
    
    /**
     * This method is used solely for testing. 
     * Prints the dungeon layout to the command line.
     */
    /*
    public void printDungeon() {
        char w = '#';
        char p = '.';
        String s = "";
        for (int column = 0, row = 0; row <= this.dungeonHeight - 1; row++) {
            for (column = 0; column <= this.dungeonHeight - 1; column++) {
                if (this.fillmap[row][column] == 1) {
                    String otherString = s + '#';
                    s = otherString;
                } else {
                    String otherString = s + '.';
                    s = otherString;
                }
            }
            String endOfRowString = s + "\n";
            s = endOfRowString;
        }
        System.out.println(s);
    }
    */
    
    /**
     * Returns the finished random dungeon in String format (HTML).
     * @return String dungeon with line breaks.
     */
    public String returnDungeonMap() {
        char w = '#';
        char p = '.';
        
        String s = "<html><body>"; // ADDED BUT DIDN'T SOLVE PROBLEM WITH LINE-HEIGHT
        for (int column = 0, row = 0; row <= this.dungeonHeight - 1; row++) {
            for (column = 0; column <= this.dungeonHeight - 1; column++) {
                if (this.fillmap[row][column] == 1) {
                    String otherString = s + '#';
                    s = otherString;
                } else {
                    String otherString = s + '.';
                    s = otherString;
                }
            }
            String endOfRowString = s + "<br>";
            s = endOfRowString;
        }
        String otherS = s + "</body></html>"; // ADDED BUT DIDN'T SOLVE PROBLEM WITH LINE-HEIGHT
        s = otherS;
        return s;
    }
    
    // main used here for testing purposes.
    /*
    public static void main(String[] args) {
        Dungeon luola = new Dungeon(50, 50,45, 1337);
        luola.initializeDungeon();
        System.out.println("After initialization:");
        luola.printDungeon();
        
        System.out.println();
        
        luola.makeDungeon();
        luola.makeFloodedMap();
        System.out.println("After creation:");
        luola.printDungeon();
        
        System.out.println();
        
        System.out.println("***** TOINEN LUOLA*****");
        Dungeon toinenLuola = new Dungeon(5, 5, 15, 1337);
        toinenLuola.initializeDungeon();
        toinenLuola.printDungeon();
        toinenLuola.makeDungeon();
        System.out.println();
        System.out.println("MakeDungeon:");
        toinenLuola.printDungeon();
    }
    */
}
