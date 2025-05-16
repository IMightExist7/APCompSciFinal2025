import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.Statement;


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

    private ImageIcon shipImageIcon;

    private Icon prevIcon;

    private boolean[][] selectedB1;
    private boolean placeMode;

    private JButton fire;

    private int turn = 0;

    private JLabel overlay;

    private JButton next;

    private int[] indexOfSelection;

    private Ship[] ships;
    private Ship[] shipsInPlay;
    private Ship curShip;

    
    public BattleShip(Board p1, Board p2){
        board1 = p1;
        board2 = p2;

        ships = new Ship[]
                {new Ship(5, false), 
                new Ship(4, false), 
                new Ship(3, true),
                new Ship(3, false),
                new Ship(2, false)};

        shipImageIcon = new ImageIcon("img/t2-selected.png");
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
        this.setSize(1800, 800);
        
        panel = new JPanel();
        panel.setLayout(null);

        panel.setBackground(Color.WHITE);

        

        ImageIcon t1 = new ImageIcon("img/t1.png");
        //board1
        gridLeft = new JButton[board2.getSide()][board2.getSide()];
        for(int r = 0; r < board1.getSide(); r++){
            for(int c = 0; c < board1.getSide(); c++){
                gridLeft[r][c] = new JButton(t1);
                panel.add(gridLeft[r][c]);
                gridLeft[r][c].setBounds(tileLength*c+200, tileHeight*r+100, tileLength, tileHeight);
                gridLeft[r][c].setOpaque(true);
                gridLeft[r][c].setHorizontalAlignment(gridLeft[r][c].CENTER);
                gridLeft[r][c].setVerticalAlignment(gridLeft[r][c].CENTER);
                gridLeft[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                gridLeft[r][c].addActionListener(this);
            }
        }

        ImageIcon t2 = new ImageIcon("img/t2/t2.png");
        //board2
        gridRight = new JButton[board2.getSide()][board2.getSide()];
        for(int r = 0; r < board1.getSide(); r++){
            for(int c = 0; c < board1.getSide(); c++){
                gridRight[r][c] = new JButton(t2);
                panel.add(gridRight[r][c]);
                gridRight[r][c].setBounds(tileLength*c+800, tileHeight*r+100, tileLength, tileHeight);
                gridRight[r][c].setOpaque(true);
                gridRight[r][c].setHorizontalAlignment(gridRight[r][c].CENTER);
                gridRight[r][c].setVerticalAlignment(gridRight[r][c].CENTER);
                gridRight[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                gridRight[r][c].addActionListener(this);
            }
        }

        JButton shipButton = new JButton();
        shipButton.setText("New Ship");
        panel.add(shipButton);
        shipButton.setBounds(0,0, 100, 150);
        shipButton.setOpaque(true);
        shipButton.addActionListener(e -> shipAdd());

        JButton fireButton = new JButton();
        fireButton.setText("FIRE");
        panel.add(fireButton);
        fireButton.setBounds(tileLength+625, 650, 150, 100);
        fireButton.setOpaque(true);
        fireButton.setBackground(Color.red);
        fireButton.addActionListener(e -> fire());       

        this.add(panel);
        this.setVisible(true);
        this.isFocusableWindow();


    }


    public void shipAdd()
    {
        if(!placeMode){
                int i=0;
            while(i<ships.length&&ships[i]==null) {
                i++;
            }
            if(i!=ships.length) {
                curShip=ships[i];
                ships[i]=null;
            }
            placeMode=!placeMode;
            System.out.println(placeMode);
        }
    }



    public void actionPerformed(ActionEvent e) {
        ImageIcon s = new ImageIcon("img/t2/t2-selected.png");
        ImageIcon t2 = new ImageIcon("img/t2/t2.png");
        for (int r=0; r < board1.getSide(); r++) {
            for(int c=0; c < board1.getSide();c++) {
                if (e.getSource().equals(gridRight[r][c])) {
                    if(turn%2 == 0){
                        if(!board1.getTile(c, r).getHit()){
                            selectedB1[r][c] = !selectedB1[r][c];
                            if(selectedB1[r][c]){
                                gridRight[r][c].setIcon(s);
                            }else{
                                gridRight[r][c].setIcon(t2);
                            }
                            System.out.print(selectedB1[r][c]);
                        }else{
                            signalError();
                            return;
                        }
                    }else{
                        if(board1.getTile(c, r).getHit()){
                            selectedB1[r][c] = !selectedB1[r][c];
                            if(selectedB1[r][c]){
                                gridRight[r][c].setIcon(s);
                            }else{
                                gridRight[r][c].setIcon(t2);
                            }
                            System.out.print(selectedB1[r][c]);
                        }else{
                            signalError();
                            return;
                        }
                    }
                    
                }else if(selectedB1[r][c]){
                    selectedB1[r][c] = false;
                    gridRight[r][c].setIcon(t2);
                }
                else if(e.getSource().equals(gridLeft[r][c])){
                    if(placeMode==true && curShip!=null) {
                        String direction="N";
                        
                        if(direction.toUpperCase().equals("N") && r-curShip.getSize()>=0){
                            for(int i = 0; i < curShip.getSize(); i++){
                                gridLeft[r-i][c].setIcon(shipImageIcon);

                                curShip.setLocation(board2, board2.getTile(r,c), direction);
                                board1.placeShip(curShip);

                                for(int j=0;j<curShip.getSize();j++)
                                    System.out.println(curShip.getLocation()[j].getX() + " " + curShip.getLocation()[j].getY());    
                            }
                            placeMode=!placeMode;
                        }else if(direction.toUpperCase().equals("E") && c+curShip.getSize()<board1.getSide()){
                            for(int i = 0; i < curShip.getSize(); i++){
                                gridLeft[r][c+i].setIcon(shipImageIcon);

                                curShip.setLocation(board2, board2.getTile(r,c), direction);
                                board1.placeShip(curShip);

                                for(int j=0;j<curShip.getSize();j++)
                                    System.out.println(curShip.getLocation()[j].getX() + " " + curShip.getLocation()[j].getY()); 
                            }
                            placeMode=!placeMode;
                        }else if(direction.toUpperCase().equals("S") && c-curShip.getSize()>=0){
                            for(int i = 0; i < curShip.getSize(); i++){
                                gridLeft[r][c-1].setIcon(shipImageIcon);

                                curShip.setLocation(board2, board2.getTile(r,c), direction);
                                board1.placeShip(curShip);

                                for(int j=0;j<curShip.getSize();j++)
                                    System.out.println(curShip.getLocation()[j].getX() + " " + curShip.getLocation()[j].getY()); 
                            }
                            placeMode=!placeMode;
                        }else if(direction.toUpperCase().equals("W") && r+curShip.getSize()<board1.getSide()){
                            for(int i = 0; i < curShip.getSize(); i++){
                                gridLeft[r+i][c].setIcon(shipImageIcon);

                                curShip.setLocation(board2, board2.getTile(r,c), direction);
                                board1.placeShip(curShip);

                                for(int j=0;j<curShip.getSize();j++)
                                    System.out.println(curShip.getLocation()[j].getX() + " " + curShip.getLocation()[0].getY()); 
                            }
                            placeMode=!placeMode;
                        }
                        curShip=null;
                    }
                }
            } 
        }
    }


    private boolean isShip(Board b, int x, int y){
        for(Ship s: b.getShips()){
            for(Tile t: s.getLocation()){
                if(t.getX() == x && t.getY() == y){
                    return true;
                }
            }
        }
        return false;
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
            JLabel pop = new JLabel();
            panel.add(pop);
            if(turn%2 == 0){
                board2.getTile(x, y).setHit(true);
                if(isShip(board2, x, y)){
                    pop.setText("HIT");
                }else{
                    pop.setText("MISS");
                }
            }else{
                board1.getTile(x, y).setHit(true);
                if(isShip(board1, x, y)){
                    pop.setText("HIT");
                }else{
                    pop.setText("MISS");
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.remove(pop);
            nextTurn();
        }
    }


    private void nextTurn(){
        overlay = new JLabel();
        panel.add(overlay);
        overlay.setBackground(Color.WHITE);
        next = new JButton();
        panel.add(next);
        next.addActionListener(e -> cont());
        next.setBounds(600, 400, 300, 200);
        next.setText("Next Turn");
        Board temp = board1;
        board1 = board2;
        board2 = temp;
    }


    private void cont(){
        panel.remove(overlay);
        panel.remove(next);
    }


    private void signalError() {
		Toolkit t = panel.getToolkit();
		t.beep();
	}

}
