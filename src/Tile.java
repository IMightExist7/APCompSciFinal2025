public class Tile {
    private int x;
    private int y;
    private boolean hit;


    /**
     * Contructor for Tile Object.
     * @param ns y coordinate.
     * @param ew x coordinate.
     */
    public Tile(int ns, int ew){
        x = ew;
        y = ns;
        hit = false;
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


    /**
     * @return if the Tile has been hit.
     */
    public boolean getHit(){
        return hit;
    }

    /**
     * Sets whether or not the Tile is hit.
     * @param h whether the Tile is hit or not.
     */
    public void setHit(boolean h){
        hit = h;
    }

    /**
     * Determines if a Tile is equal to another Tile.
     * @param t the Tile it is being compared to.
     * @return  tue if they are equal, false otherwise.
     */
    public boolean equals(Tile t){
        return(x == t.getX()) && (y == t.getY());
    }

    
}
