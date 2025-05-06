import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BattleShip extends JFrame implements ActionListener{
    /** Height of the game frame. */
	private static final int DEFAULT_HEIGHT = 1080;
	/** Width of the game frame. */
	private static final int DEFAULT_WIDTH = 1920;
    /**Player 1's Board */
    private Board board1;
    /**Player 2's Board */
    private Board board2;
    /**Top Grid */
    private JButton[][] gridLeft;
    /**Bottom Grid */
    private JButton[][] gridRight;
    /** The main panel containing the game components. */
	private JPanel panel;
    /** Length of Tiles*/
    private int tileLength;
    /** Height of Tiles*/
    private int tileHeight;
    /**Title ath the top. */
    private JLabel title;

    private boolean[][] selectedB1;

    private JButton fire;

    private int turn = 0;

    
    public BattleShip(Board p1, Board p2){
        board1 = p1;
        board2 = p2;

        Ship[] left1, left2 = 
                {new Ship(5, false), 
                new Ship(4, false), 
                new Ship(3, false),
                new Ship(3, true),
                new Ship(2, false)};
        selectedB1 = new boolean[p1.getSide()][p1.getSide()];
        initDisplay();
    }


    /**
     * Run the game.
     */
    public void displayGame() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }  


    public void initDisplay(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tileHeight = 50;
        tileLength = 50;
        this.setSize(1920, 1080);
        
        panel = new JPanel();
        panel.setLayout(null);

        panel.setBackground(Color.WHITE);

        

        ImageIcon t1 = new ImageIcon("C:/Users/pig04/APCompSciFinal2025/img/t1.png");
        //board1
        gridLeft = new JButton[board2.getSide()][board2.getSide()];
        for(int r = 0; r < board1.getSide(); r++){
            for(int c = 0; c < board1.getSide(); c++){
                gridLeft[r][c] = new JButton(t1);
                panel.add(gridLeft[r][c]);
                gridLeft[r][c].setBounds(tileLength*c+200, tileHeight*r+160, tileLength, tileHeight);
                gridLeft[r][c].setOpaque(true);
                gridLeft[r][c].setHorizontalAlignment(gridLeft[r][c].CENTER);
                gridLeft[r][c].setVerticalAlignment(gridLeft[r][c].CENTER);
                gridLeft[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
        }

        ImageIcon t2 = new ImageIcon("img/t2.png");
        //board2
        gridRight = new JButton[board2.getSide()][board2.getSide()];
        for(int r = 0; r < board1.getSide(); r++){
            for(int c = 0; c < board1.getSide(); c++){
                gridRight[r][c] = new JButton(t2);
                panel.add(gridRight[r][c]);
                gridRight[r][c].setBounds(tileLength*c+800, tileHeight*r+160, tileLength, tileHeight);
                gridRight[r][c].setOpaque(true);
                gridRight[r][c].setHorizontalAlignment(gridRight[r][c].CENTER);
                gridRight[r][c].setVerticalAlignment(gridRight[r][c].CENTER);
                gridRight[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                gridRight[r][c].addActionListener(this);
            }
        }

        fire = new JButton();
        panel.add(fire);
        fire.addActionListener(e -> fire());
        

        this.add(panel);
        this.setVisible(true);
        this.isFocusableWindow();





    }



    public void actionPerformed(ActionEvent e) { 
        for (int r=0; r < board1.getSide(); r++) {
            for(int c=0; c < board1.getSide();c++) {
                if (e.getSource().equals(gridRight[r][c])) {
                    selectedB1[r][c] = !selectedB1[r][c];
                    System.out.print("test");
                    System.out.print(selectedB1[r][c]);
                    return;
                }
                
            } 
        }
    }


    private void fire(){
        int x = -1;
        int y = -1;
        for(int r = 0; r < board1.getSide(); r++){
            for(int c = 0; c < board1.getSide(); c++){
                if(selectedB1[r][c]){
                    x = c;
                    y = r;
                }
            }
        }
        if(x == -1){
            signalError();
        }else{
            if(turn%2 == 0){
                board2.getTile(x, y).setHit(true);
            }else{
                board1.getTile(x, y).setHit(true);
            }
            nextTurn();
        }
    }


    private void nextTurn(){
        JLabel overlay = new JLabel();
        panel.add(overlay);
        overlay.setBackground(Color.WHITE);
        JButton next = new JButton();
        panel.add(next);
        next.addActionListener(e -> cont());
        next.setBounds(600, 400, 300, 200);
        next.setText("Next Turn");
        Board temp = board1;
        board1 = board2;
        board2 = temp;
    }


    private void cont(){

    }


    private void signalError() {
		Toolkit t = panel.getToolkit();
		t.beep();
	}


    

}
