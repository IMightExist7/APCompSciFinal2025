public class Board {
    private Tile[][] board;
    private int side;
    private Ship[] ships;


    /**
     * Constructor for the Board Object.
     * @param side side length of the square Board.
     */
    public Board(int s){
        side = s;
        board = new Tile[side][side];
        for(int row = 0; row < side; row++){
            for(int col = 0; col < side; col++){
                board[row][col] = new Tile(row, col);
            }
        }
        ships = new Ship[5];
    }


    /**
     * Return a specific Tile in the Board.
     * @param x coordinate of the Tile.
     * @param y coordinate of the Tile.
     * @return Tile at given coordinates.
     */
    public Tile getTile(int y, int x){
        return board[y][x];
    }

    public void placeShip(Ship s){
        int i=0;

        while(ships[i]!=null&&i<ships.length-1){
            i++;
        }
        if(ships[i]==null)
            ships[i]=s;
            //System.out.println(ships[i].toString());
        
    }

    
    /**
     * Gets the side length of the Board.
     * @return side.
     */
    public int getSide() {
        return side;
    }

    /**
     * Sets the given Tile to hit.
     * @param r y-coordinate of the Tile.
     * @param c x-coordinate of the tile.
     */
    public void hit(int r, int c){
        getTile(c, r).setHit(true);
    }

    /**
     * Gets the Ships on the Board.
     * @return an Array of the Ships on the Board.
     */
    public Ship[] getShips(){
        return ships;
    }


    
}
