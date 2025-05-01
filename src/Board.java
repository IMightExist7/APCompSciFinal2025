public class Board {
    private Tile[][] board;

    public Board(int side){
        board = new Tile[side][side];
    }


    public Tile getTile(int x, int y){
        return board[y][x];
    }
    
}
