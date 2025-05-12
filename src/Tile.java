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


    public void setHit(boolean h){
        hit = h;
    }


    public boolean equals(Tile t){
        return(x == t.getX()) && (y == t.getY());
    }

    
}
