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
	private JLayeredPane panel;

    private JPanel p1;

    private JPanel p2;

    private JPanel p3;
    /** Length of Tiles*/
    private int tileLength;
    /** Height of Tiles*/
    private int tileHeight;
    /**Title ath the top. */
    private JLabel title;

    private boolean[][] selectedB1;
    private boolean placeMode;

    private JButton fire;

    private int turn = 0;

    private JLabel overlay;

    private JButton next;

    private JButton directionButton;

    private String[] directions;

    private int direction;
    
    private Ship[] ships1;
    private Ship[] ships2;
    private Ship curShip;

    private ImageIcon[][][] shipIcons = {
                                    {{new ImageIcon("img/s2/N/0"), new ImageIcon("img/s2/N/1")}, {new ImageIcon("img/s2/E/0"), new ImageIcon("img/s2/E/1")}, {new ImageIcon("img/s2/S/0"), new ImageIcon("img/s2/S/1")}, {new ImageIcon("img/s2/W/0"), new ImageIcon("img/s2/W/1")}},   //s2
                                    {{new ImageIcon("img/s3-n/N/0"), new ImageIcon("img/s3-n/N/1"), new ImageIcon("img/s3-n/N/2")}, {new ImageIcon("img/s3-n/E/0"), new ImageIcon("img/s3-n/E/1"), new ImageIcon("img/s3-n/E/2")}, {new ImageIcon("img/s3-n/S/0"), new ImageIcon("img/s3-n/S/1"), new ImageIcon("img/s3-n/S/2")}, {new ImageIcon("img/s3-n/W/0"), new ImageIcon("img/s3-n/W/1"), new ImageIcon("img/s3-n/W/2")}},   //s3-n
                                    {{new ImageIcon("img/s3-y/N/0"), new ImageIcon("img/s3-y/N/1"), new ImageIcon("img/s3-y/N/2")}, {new ImageIcon("img/s3-y/E/0"), new ImageIcon("img/s3-y/E/1"), new ImageIcon("img/s3-y/E/2")}, {new ImageIcon("img/s3-y/S/0"), new ImageIcon("img/s3-y/S/1"), new ImageIcon("img/s3-y/S/2")}, {new ImageIcon("img/s3-y/W/0"), new ImageIcon("img/s3-y/W/1"), new ImageIcon("img/s3-y/W/2")}},   //s3-y
                                    {{new ImageIcon("img/s4/N/0"), new ImageIcon("img/s4/N/1"), new ImageIcon("img/s4/N/2"), new ImageIcon("img/s4/N/3")}, {new ImageIcon("img/s4/E/0"), new ImageIcon("img/s4/E/1"), new ImageIcon("img/s4/E/2"), new ImageIcon("img/s4/E/3")}, {new ImageIcon("img/s4/S/0"), new ImageIcon("img/s4/S/1"), new ImageIcon("img/s4/S/2"), new ImageIcon("img/s4/S/3")}, {new ImageIcon("img/s4/W/0"), new ImageIcon("img/s4/W/1"), new ImageIcon("img/s4/W/2"), new ImageIcon("img/s4/W/3")}},   //s4
                                    {{new ImageIcon("img/s5/N/0"), new ImageIcon("img/s5/N/1"), new ImageIcon("img/s5/N/2"), new ImageIcon("img/s5/N/3"), new ImageIcon("img/s5/N/4")}, {new ImageIcon("img/s5/E/0"), new ImageIcon("img/s5/E/1"), new ImageIcon("img/s5/E/2"), new ImageIcon("img/s5/E/3"), new ImageIcon("img/s5/E/4")}, {new ImageIcon("img/s5/S/0"), new ImageIcon("img/s5/S/1"), new ImageIcon("img/s5/S/2"), new ImageIcon("img/s5/S/3"), new ImageIcon("img/s5/S/4")}, {new ImageIcon("img/s5/W/0"), new ImageIcon("img/s5/W/1"), new ImageIcon("img/s5/W/2"), new ImageIcon("img/s5/W/3"), new ImageIcon("img/s5/W/4")}}};  //s5

    
    public BattleShip(Board b1, Board b2){
        board1 = b1;
        board2 = b2;

        ships1 = new Ship[]
                {new Ship(5, false), 
                new Ship(4, false), 
                new Ship(3, true),
                new Ship(3, false),
                new Ship(2, false)};
        ships2 = new Ship[]
                {new Ship(5, false), 
                new Ship(4, false), 
                new Ship(3, true),
                new Ship(3, false),
                new Ship(2, false)};
        
        directions = new String[]{"N","E","S","W"};
        direction = 0;
        

        selectedB1 = new boolean[b1.getSide()][b1.getSide()];
        panel = new JLayeredPane();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        this.setContentPane(panel);
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

        panel.add(p1, 1);
        panel.add(p2, 2);
        panel.add(p3, 3);

        tileHeight = 50;
        tileLength = 50;
        this.setSize(1920, 1080);
        
        p1.setSize(1920, 1080);
        p2.setSize(1920, 1080);
        p3.setSize(1920, 1080);
        
        
        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);

        p2.setOpaque(false);
        p3.setOpaque(false);

        ImageIcon t1 = new ImageIcon("img/t1.png");


        //board1
        gridLeft = new JButton[board2.getSide()][board2.getSide()];
        for(int r = 0; r < board1.getSide(); r++){
            for(int c = 0; c < board1.getSide(); c++){
                gridLeft[r][c] = new JButton(t1);
                p1.add(gridLeft[r][c]);
                gridLeft[r][c].setBounds(tileLength*c+200, tileHeight*r+160, tileLength, tileHeight);
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
                p1.add(gridRight[r][c]);
                gridRight[r][c].setBounds(tileLength*c+800, tileHeight*r+160, tileLength, tileHeight);
                gridRight[r][c].setOpaque(true);
                gridRight[r][c].setHorizontalAlignment(gridRight[r][c].CENTER);
                gridRight[r][c].setVerticalAlignment(gridRight[r][c].CENTER);
                gridRight[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                gridRight[r][c].addActionListener(this);
                
            }
        }

        JButton shipButton = new JButton();
        shipButton.setText("New Ship");
        p1.add(shipButton);
        shipButton.setBounds(0,0, 100, 150);
        shipButton.setOpaque(true);
        shipButton.addActionListener(e -> shipAdd());

        JButton fireButton = new JButton();
        fireButton.setText("FIRE");
        p1.add(fireButton);
        fireButton.setBounds(tileLength+625,675, 150, 100);
        fireButton.setOpaque(true);
        fireButton.setBackground(Color.red);
        fireButton.addActionListener(e -> fire());       
        fireButton.setVisible(false);

        //this.add(panel);
        this.setVisible(true);
        this.isFocusableWindow();

        directionButton = new JButton();
        p1.add(directionButton);
        directionButton.setBounds(0,200,100,150);
        directionButton.setText("N");
        directionButton.addActionListener(e -> directionChange());
        directionButton.setVisible(false);  


    }

    public void directionChange(){
        if(direction+1!=4)
            direction++;
        else
            direction=0;
        directionButton.setText(directions[direction].toUpperCase());
        System.out.println(directions[direction]);

    }


    public void shipAdd()
    {
        if(!placeMode){
            if(turn == 0){
                    int i=0;
                while(i<ships1.length&&ships1[i]==null) {
                    i++;
                }
                if(i!=ships1.length) {
                    curShip=ships1[i];
                    ships1[i]=null;
                }
                placeMode=!placeMode;
            }else{
                    int i=0;
                while(i<ships2.length&&ships2[i]==null) {
                    i++;
                }
                if(i!=ships2.length) {
                    curShip=ships2[i];
                    ships2[i]=null;
                }
                placeMode=!placeMode;
            }
        }
        directionButton.setVisible(placeMode);   
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
                    int index = -1;
                    if(placeMode==true && curShip!=null) {
                        System.out.println(c +" " + r);
                        index = curShip.getSize() - 1;
                        if(index == 2 && curShip.isSub()){
                            index++;
                        }
                        System.out.println(r-curShip.getSize()+1>=0);
                        if(directions[direction].toUpperCase().equals("N") && r-curShip.getSize()+1>=0){
                            for(int i = 0; i < curShip.getSize(); i++){
                                Point loc = gridLeft[r-i][c].getLocation();
                                JLabel ship = new JLabel(shipIcons[index][0][i]);
                                p2.add(ship);
                                ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);

                                curShip.setLocation(board2, board2.getTile(r,c), directions[direction]);
                                ship.setOpaque(true);

                                curShip.setLocation(board2, board2.getTile(r,c), directions[direction]);
                                board1.placeShip(curShip);    
                            }
                        }else if(directions[direction].toUpperCase().equals("E") && c+curShip.getSize()-1<board1.getSide()){
                            for(int i = 0; i < curShip.getSize(); i++){
                                Point loc = gridLeft[r][c+i].getLocation();
                                JLabel ship = new JLabel(shipIcons[index][1][i]);
                                p2.add(ship);
                                ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                                ship.setOpaque(true);
                                curShip.setLocation(board2, board2.getTile(r,c), directions[direction]);
                                board1.placeShip(curShip);
                            }
                        }else if(directions[direction].toUpperCase().equals("S") && r+curShip.getSize()-1<board1.getSide()){
                            for(int i = 0; i < curShip.getSize(); i++){
                                Point loc = gridLeft[r+i][c].getLocation();
                                JLabel ship = new JLabel(shipIcons[index][2][i]);
                                p2.add(ship);
                                ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                                ship.setOpaque(true);
                                curShip.setLocation(board2, board2.getTile(r,c), directions[direction]);
                                board1.placeShip(curShip);
                                
                            }
                        }else if(directions[direction].toUpperCase().equals("W") && c-curShip.getSize()+1>=0){
                            for(int i = 0; i < curShip.getSize(); i++){
                                Point loc = gridLeft[r][c-i].getLocation();
                                JLabel ship = new JLabel(shipIcons[index][3][i]);
                                p2.add(ship);
                                ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                                ship.setOpaque(true);
                              
                                curShip.setLocation(board2, board2.getTile(r,c), directions[direction]);
                                board1.placeShip(curShip);
                            }
                        }
                    }
                    placeMode=!placeMode;
                    directionButton.setVisible(placeMode);
                    curShip=null;
                    if(index == 1){
                        nextPlace();
                    }
                    
                }
            } 
        }
        panel.setLayer(p1, 1);
        panel.setLayer(p2, 2);
        Background b = new Background(panel, p1, p2, p3);
        new Thread(b).start();
    }


    private void nextPlace(){
        nextTurn();
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
            p3.add(pop);
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
            p3.remove(pop);
            nextTurn();
        }
    }


    private void nextTurn(){
        turn++;
        overlay = new JLabel();
        p3.add(overlay);
        overlay.setBackground(Color.WHITE);
        next = new JButton();
        p3.add(next);
        next.addActionListener(e -> cont());
        next.setBounds(600, 400, 300, 200);
        next.setText("Next Turn");
        Board temp = board1;
        board1 = board2;
        board2 = temp;
    }


    private void cont(){
        p3.remove(overlay);
        p3.remove(next);
    }


    private void signalError() {
		Toolkit t = panel.getToolkit();
		t.beep();
	}

}
