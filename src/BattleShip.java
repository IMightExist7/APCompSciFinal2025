import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class BattleShip extends JFrame{
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

    
    public BattleShip(Board p1, Board p2){
        board1 = p1;
        board2 = p2;

        Ship[] left1, left2 = 
                {new Ship(5, false), 
                new Ship(4, false), 
                new Ship(3, false),
                new Ship(3, true),
                new Ship(2, false)};
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

        ImageIcon t2 = new ImageIcon("C:/Users/pig04/APCompSciFinal2025/img/t2.png");

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
            }
        }
        

        this.add(panel);
        this.setVisible(true);
        this.isFocusableWindow();





    }

}
