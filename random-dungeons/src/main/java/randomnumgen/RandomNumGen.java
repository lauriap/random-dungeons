
package randomnumgen;

/**
 * This class generates random numbers in a similar fashion to Java's
 * Random class. It can only create integers using nextRandInt() method.
 * @author lauri
 */
public class RandomNumGen {
    
    long seed;
    
    /**
     * Constructor for a random sequence based on computer clock.
     */
    public RandomNumGen() {
        this(System.currentTimeMillis());
    }
    
    /**
     * Constructor for a random sequence based on preset seed.
     * @param seed 
     */
    public RandomNumGen(long seed) {
        this.setSeed(seed);
    }
    
    /**
     * Method for setting a seed.
     * @param seed 
     */
    public void setSeed(long seed) {
        this.seed = (seed ^ 0x5DEECE66DL) & ((1L << 48) - 1);
    }
    
    /**
     * Generates the next pseudorandom number.
     * @param bits
     * @return 
     */
    public int next(int bits) {
        seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
        return (int) (seed >>> (48 - bits));
    }
    
    /**
     * Returns the next pseudorandom, uniformly distributed int 
     * value from this random number generator's sequence.
     * @param n
     * @return 
     */
    public int nextInt(int n) {
        if ((n & -n) == n) { // i.e., n is a power of 2
            return (int) ((n * (long) next(31)) >> 31);
        }
        int bits, val;
        do {
            bits = next(31);
            val = bits % n;
        } while (bits - val + (n - 1) < 0);
            
        return val;
    }

}
