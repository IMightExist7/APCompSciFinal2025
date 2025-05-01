public class Tile {
    private int x;
    private int y;


    /**
     * Contructor for Tile Object.
     * @param ns y coordinate.
     * @param ew x coordinate.
     */
    public Tile(int ns, int ew){
        x = ew;
        y = ns;
    }


    /**
     * @return x coordinate of the Tile.
     */
    public int getX() {
        return x;
    }


    /**
     * @return y coordinate of the Tile.
     */
    public int getY() {
        return y;
    }

    
}
