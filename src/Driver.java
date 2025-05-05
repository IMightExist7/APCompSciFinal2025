public class Driver{

    public static void main(String[] args){
        Board b1 = new Board(10);
        Board b2 = new Board(10);
        BattleShip game = new BattleShip(b1, b2);
        game.displayGame();
    }

}