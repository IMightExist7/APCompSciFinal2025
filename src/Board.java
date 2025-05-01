public class Board {
    private Tile[][] board;


    /**
     * Constructor for the Board Object.
     * @param side side length of the square Board.
     */
    public Board(int side){
        board = new Tile[side][side];
        for(int row = 0; row < side; row++){
            for(int col = 0; col < side; col++){
                board[row][col] = new Tile(row, col);
            }
        }
    }


    /**
     * Return a specific Tile in the Board.
     * @param x coordinate of the Tile.
     * @param y coordinate of the Tile.
     * @return Tile at given coordinates.
     */
    public Tile getTile(int x, int y){
        return board[y][x];
    }


    
}
