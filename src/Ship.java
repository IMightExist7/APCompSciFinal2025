public class Ship {
    private int size;
    private Tile[] location;
    private String direction;
    private boolean submarine;
    

    /**
     * Constructor for a Ship Object.
     * @param s how many Tiles the Ship occupies.
     * @param sub is it a submarine.
     */
    public Ship(int s, boolean sub){
        size = s;
        location = new Tile[size];
        submarine = sub;
    }


    /**
     * Sets the location for a ship object on a gride of Tile objects.
     * @param b The board the Ship is on.
     * @param start The start Tile.
     * @param direction The direction the Ship extends from the start Tile.
     *              (N/E/S/W)
     */
    public void setLocation(Board b, Tile start, String direction){
        this.direction = direction;
        location[0] = start;
        if(direction.toUpperCase().equals("N")){
            for(int i = 0; i < size; i++){
                location[i] = b.getTile(start.getY() - i, start.getX());
            }
        }else if(direction.toUpperCase().equals("E")){
            for(int i = 0; i < size; i++){
                location[i] = b.getTile(start.getY(), start.getX() + i);
            }
        }else if(direction.toUpperCase().equals("S")){
            for(int i = 0; i < size; i++){
                location[i] = b.getTile(start.getY() + i, start.getX());
            }
        }else{
            for(int i = 0; i < size; i++){
                location[i] = b.getTile(start.getY(), start.getX() - i);
            }
        }
    }

    /**
     * Gets the location of the Ship.
     * @return An Array of Tiles.
     */
    public Tile[] getLocation(){
        return location;
    }


    /**
     * Gets the size of the Ship.
     * @return size.
     */
    public int getSize(){
        return size;
    }

    /**
     * Gets the direction of the Ship.
     * @return direction.
     */
    public String getDirection(){
        return direction;
    }

    /**
     * Gets whether the Ship is or is not a submarine.
     * @return submarine.
     */
    public boolean isSub(){
        return submarine;
    }


}
