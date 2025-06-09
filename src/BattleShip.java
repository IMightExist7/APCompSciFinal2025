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

    private JPanel p0;

    private JPanel p1;

    private JPanel p2;

    private JPanel p3;

    private JPanel p4;
    /** Length of Tiles*/
    private int tileLength;
    /** Height of Tiles*/
    private int tileHeight;
    /**Title ath the top. */
    private JLabel title;

    private boolean[][] selectedB1;
    private boolean placeMode;

    private JButton fire;

    private JButton shipButton;

    private int turn = 0;

    private JLabel overlay;

    private JButton next;

    private JButton directionButton;

    private String[] directions;

    private int direction;
    
    private Ship[] ships1;
    private Ship[] ships2;
    private Ship curShip;

    private JButton pop;

    private ImageIcon[][][] shipIcons = {
                                    {
                                        {new ImageIcon("img/s3-y/N/0.png"), new ImageIcon("img/s3-y/N/1.png"), new ImageIcon("img/s3-y/N/2.png")}, 
                                        {new ImageIcon("img/s3-y/E/0.png"), new ImageIcon("img/s3-y/E/1.png"), new ImageIcon("img/s3-y/E/2.png")}, 
                                        {new ImageIcon("img/s3-y/S/0.png"), new ImageIcon("img/s3-y/S/1.png"), new ImageIcon("img/s3-y/S/2.png")}, 
                                        {new ImageIcon("img/s3-y/W/0.png"), new ImageIcon("img/s3-y/W/1.png"), new ImageIcon("img/s3-y/W/2.png")}
                                    },
                                    {
                                        {new ImageIcon("img/s2/N/0.png"), new ImageIcon("img/s2/N/1.png")}, 
                                        {new ImageIcon("img/s2/E/0.png"), new ImageIcon("img/s2/E/1.png")}, 
                                        {new ImageIcon("img/s2/S/0.png"), new ImageIcon("img/s2/S/1.png")}, 
                                        {new ImageIcon("img/s2/W/0.png"), new ImageIcon("img/s2/W/1.png")}
                                    },
                                    {
                                        {new ImageIcon("img/s3-n/N/0.png"), new ImageIcon("img/s3-n/N/1.png"), new ImageIcon("img/s3-n/N/2.png")},
                                        {new ImageIcon("img/s3-n/E/0.png"), new ImageIcon("img/s3-n/E/1.png"), new ImageIcon("img/s3-n/E/2.png")},
                                        {new ImageIcon("img/s3-n/S/0.png"), new ImageIcon("img/s3-n/S/1.png"), new ImageIcon("img/s3-n/S/2.png")},
                                        {new ImageIcon("img/s3-n/W/0.png"), new ImageIcon("img/s3-n/W/1.png"), new ImageIcon("img/s3-n/W/2.png")}
                                    },
                                    {
                                        {new ImageIcon("img/s4/N/0.png"), new ImageIcon("img/s4/N/1.png"), new ImageIcon("img/s4/N/2.png"), new ImageIcon("img/s4/N/3.png")}, 
                                        {new ImageIcon("img/s4/E/0.png"), new ImageIcon("img/s4/E/1.png"), new ImageIcon("img/s4/E/2.png"), new ImageIcon("img/s4/E/3.png")}, 
                                        {new ImageIcon("img/s4/S/0.png"), new ImageIcon("img/s4/S/1.png"), new ImageIcon("img/s4/S/2.png"), new ImageIcon("img/s4/S/3.png")}, 
                                        {new ImageIcon("img/s4/W/0.png"), new ImageIcon("img/s4/W/1.png"), new ImageIcon("img/s4/W/2.png"), new ImageIcon("img/s4/W/3.png")}
                                    },
                                    {
                                        {new ImageIcon("img/s5/N/0.png"), new ImageIcon("img/s5/N/1.png"), new ImageIcon("img/s5/N/2.png"), new ImageIcon("img/s5/N/3.png"), new ImageIcon("img/s5/N/4.png")}, 
                                        {new ImageIcon("img/s5/E/0.png"), new ImageIcon("img/s5/E/1.png"), new ImageIcon("img/s5/E/2.png"), new ImageIcon("img/s5/E/3.png"), new ImageIcon("img/s5/E/4.png")}, 
                                        {new ImageIcon("img/s5/S/0.png"), new ImageIcon("img/s5/S/1.png"), new ImageIcon("img/s5/S/2.png"), new ImageIcon("img/s5/S/3.png"), new ImageIcon("img/s5/S/4.png")}, 
                                        {new ImageIcon("img/s5/W/0.png"), new ImageIcon("img/s5/W/1.png"), new ImageIcon("img/s5/W/2.png"), new ImageIcon("img/s5/W/3.png"), new ImageIcon("img/s5/W/4.png")}
                                    }};

    /**
     * Constuctor for the BattleShip class.
     * @param b1 Player 1's board.
     * @param b2 Player 2's board.
     */
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
        p0 = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
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



    /**
     * Initialize the game display.
     */
    public void initDisplay(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel.add(p0, 0);
        panel.add(p1, 1);
        panel.add(p2, 2);
        panel.add(p3, 3);
        panel.add(p4, 4);

        tileHeight = 50;
        tileLength = 50;
        this.setSize(1920, 1080);

        p0.setSize(1920, 1080);
        p1.setSize(1920, 1080);
        p2.setSize(1920, 1080);
        p3.setSize(1920, 1080);
        p4.setSize(1920, 1080);
        
        p0.setLayout(null);
        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        p4.setLayout(null);

        p0.setOpaque(false);
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);

        ImageIcon bkg = new ImageIcon("img/bkg.png");
        JLabel back = new JLabel(bkg);
        p0.add(back);
        back.setBounds(0, 0, 1920, 1080);

        Background b = new Background(panel, p0, p1, p2, p3, p4);
        new Thread(b).start();

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

        shipButton = new JButton();
        shipButton.setText("New Ship");
        p1.add(shipButton);
        shipButton.setBounds(0,0, 100, 150);
        shipButton.setOpaque(true);
        shipButton.addActionListener(e -> shipAdd());

        fire = new JButton();
        fire.setText("FIRE");
        p1.add(fire);
        fire.setBounds(tileLength+625,675, 150, 100);
        fire.setOpaque(true);
        fire.setBackground(Color.red);
        fire.addActionListener(e -> fire());       
        fire.setVisible(false);

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



    /**
     * Change the direction of the ship about to be placed.
     */
    public void directionChange(){
        if(direction+1!=4)
            direction++;
        else
            direction=0;
        directionButton.setText(directions[direction].toUpperCase());

    }



    /**
     * Set curShip to the next ship to be placed.
     */
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



    /**
     * Does 2 things, which one depends on the stage of the game, and where the source is.
     * 1. Selects tiles on the enemy's board.
     *  -Happens after ship placement, source is enemy's board.
     * 2. Places ships on the current player's board.
     *  -Happens during ship placement, source is current player's board.
     */
    public void actionPerformed(ActionEvent e) {
        ImageIcon s = new ImageIcon("img/t2/t2-selected.png");
        ImageIcon t2 = new ImageIcon("img/t2/t2.png");
        for (int r=0; r < board1.getSide(); r++) {
            for(int c=0; c < board1.getSide();c++) {
                if (e.getSource().equals(gridRight[r][c])) {
                    if(turn%2 == 0){
                        if(!board2.getTile(r, c).getHit()){
                            selectedB1[r][c] = !selectedB1[r][c];
                            if(selectedB1[r][c]){
                                gridRight[r][c].setIcon(s);
                            }else{
                                gridRight[r][c].setIcon(t2);
                            }
                        }else{
                            signalError();
                            return;
                        }
                    }else{
                        if(!board1.getTile(r, c).getHit()){
                            selectedB1[r][c] = !selectedB1[r][c];
                            if(selectedB1[r][c]){
                                gridRight[r][c].setIcon(s);
                            }else{
                                gridRight[r][c].setIcon(t2);
                            }
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
                    Board temp;
                    if(turn == 0){
                        temp = board1;
                    }else{
                        temp = board2;
                    }
                    if(placeMode==true && curShip!=null && !checkOverlap(temp, curShip, r, c)) {
                        index = curShip.getSize() - 1;
                        if(curShip.isSub()){
                            index = 0;
                        }
                        if(directions[direction].toUpperCase().equals("N") && r-curShip.getSize()+1>=0){
                            curShip.setLocation(temp, temp.getTile(r,c), directions[direction]);
                            for(int i = 0; i < curShip.getSize(); i++){
                                Point loc = gridLeft[r-i][c].getLocation();
                                JLabel ship = new JLabel(shipIcons[index][0][i]);
                                p2.add(ship);
                                ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                            }
                            placeMode=!placeMode;
                            temp.placeShip(curShip);
                        }else if(directions[direction].toUpperCase().equals("E") && c+curShip.getSize()-1<board1.getSide()){
                            curShip.setLocation(temp, temp.getTile(r,c), directions[direction]);
                            for(int i = 0; i < curShip.getSize(); i++){
                                Point loc = gridLeft[r][c+i].getLocation();
                                JLabel ship = new JLabel(shipIcons[index][1][i]);
                                p2.add(ship);
                                ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                            }
                            placeMode=!placeMode;
                            temp.placeShip(curShip);
                        }else if(directions[direction].toUpperCase().equals("S") && r+curShip.getSize()-1<board1.getSide()){
                            curShip.setLocation(temp, temp.getTile(r,c), directions[direction]);
                            for(int i = 0; i < curShip.getSize(); i++){
                                Point loc = gridLeft[r+i][c].getLocation();
                                JLabel ship = new JLabel(shipIcons[index][2][i]);
                                p2.add(ship);
                                ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);                                
                            }
                            placeMode=!placeMode;
                            temp.placeShip(curShip);
                        }else if(directions[direction].toUpperCase().equals("W") && c-curShip.getSize()+1>=0){
                            curShip.setLocation(temp, temp.getTile(r,c), directions[direction]);
                            for(int i = 0; i < curShip.getSize(); i++){
                                Point loc = gridLeft[r][c-i].getLocation();
                                JLabel ship = new JLabel(shipIcons[index][3][i]);
                                p2.add(ship);
                                ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);                              
                            }
                            placeMode=!placeMode;
                            temp.placeShip(curShip);
                        }
                    }
                    directionButton.setVisible(placeMode);
                    if(index == 1){
                        nextPlace();
                    }
                    
                }
            } 
        }
        panel.setLayer(p1, 1);
        panel.setLayer(p2, 2);
    }



    /**
     * Transitions game out of ship placement mode.
     */
    private void nextPlace(){
        if(turn == 1){
            fire.setVisible(true);
            shipButton.setVisible(false);
        }
        nextTurn();
    }



    /**
     * Determines whether or not a Tile at coordinates (x, y)
     * on Board b is a ship or not.
     * @param b the Board being looked at.
     * @param x the x-coordinate of the Tile.
     * @param y the y-coordiate of the Tile.
     * @return  true if the Tile is a ship, false otherwise.
     */
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



    /**
     * Sets the selected Tile to hit.
     * Creates a popup that reads if the Tile hit was a ship or not.
     * Calls result()
     */
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
            fire.setEnabled(false);
            pop = new JButton();
            p4.add(pop);
            pop.setOpaque(true);
            pop.setBounds(600, 400, 300, 200);
            pop.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            pop.setVisible(true);
            Board curBoard;
            if(turn%2 == 0){
                curBoard = board2;
            }else{
                curBoard = board1;
            }
            pop.addActionListener(e -> result(curBoard));
            if(turn%2 == 0){
                curBoard.getTile(y, x).setHit(true);
                if(isShip(board2, x, y)){
                    pop.setText("HIT");
                    pop.setBackground(Color.RED);
                }else{
                    pop.setText("MISS");
                    pop.setBackground(Color.WHITE);
                }
            }else{
                curBoard.getTile(y, x).setHit(true);
                if(isShip(board1, x, y)){
                    pop.setText("HIT");
                    pop.setBackground(Color.RED);
                }else{
                    pop.setText("MISS");
                    pop.setBackground(Color.WHITE);
                }
            }
            

            
        }
    }



    /**
     * Checks whether a Ship to be placed would overlap with another Ship.
     * @param board the board the Ship is being placed on.
     * @param s the ship being placed.
     * @param r the y-coordinate of the 'source' Tile of the Ship.
     * @param c the x-coordinate of the 'source' Tile of the Ship.
     * @return false if the Ship being placed does not overlap with any
     *          other Ships, true otherwise.
     */
    private boolean checkOverlap(Board board, Ship s, int r, int c) {
        Ship[] checkedShips = board.getShips(); 
        Tile[] checkedLocation;
        for(int j=0;j<checkedShips.length;j++){
            if(checkedShips[j]!=null){
                checkedLocation = checkedShips[j].getLocation();
                if(directions[direction].toUpperCase().equals("N")){
                    for(int i = 0; i < checkedShips[j].getSize(); i++){
                        System.out.println(i);
                        if(checkedLocation[i].getY()>r-s.getSize() && checkedLocation[i].getY()<=r && checkedLocation[i].getX()==c)
                            return true;
                    }
                }else if(directions[direction].toUpperCase().equals("E")){
                    for(int i = 0; i < checkedShips[j].getSize(); i++){
                        System.out.println(i);
                        if(checkedLocation[i].getY()==r && checkedLocation[i].getX()<c+s.getSize() && checkedLocation[i].getX()>=c)
                            return true;
                    }
                }else if(directions[direction].toUpperCase().equals("S")){
                    for(int i = 0; i < checkedShips[j].getSize(); i++){
                        if(checkedLocation[i].getY()<r+s.getSize() && checkedLocation[i].getY()>=r && checkedLocation[i].getX()==c)
                            return true;
                    }
                }else{
                    for(int i = 0; i < checkedShips[j].getSize(); i++){
                        if(checkedLocation[i].getY()==r && checkedLocation[i].getX()>c-s.getSize() && checkedLocation[i].getX()<=c)
                            return true;
                   }
                }
            }
        }
        return false;
    }



    /*
     * Moves to the next turn of the game.
     * Switches which Tile grid represents which board.
     * Resets Ships shown to the other player's.
     * Resets hit markers to those of the other player.
     * Creates an overlay blocking the game from sight until a button is pressed.
     */
    private void nextTurn(){
        if(turn > 1){
            p4.remove(pop);
            fire.setEnabled(true);
        }
        turn++;
        Board curBoard;
        Board enemy;
        if(turn%2 == 0){
            curBoard = board1;
            enemy = board2;
        }else{
            curBoard = board2;
            enemy = board1;
        }
        overlay = new JLabel();
        ImageIcon hit = new ImageIcon("img/hit.png");
        ImageIcon miss = new ImageIcon("img/miss.png");
        ImageIcon t2 = new ImageIcon("img/t2/t2.png");
        p4.add(overlay);
        p4.setOpaque(true);
        overlay.setBackground(Color.WHITE);
        overlay.setOpaque(true);
        next = new JButton();
        p4.add(next);
        next.addActionListener(e -> cont());
        next.setBounds(600, 400, 300, 200);
        next.setText("Next Turn");
        p2.removeAll();
        p3.removeAll();
        if(turn > 1){
            for(Ship s: curBoard.getShips()){
                int index = s.getSize()-1;
                if(s.isSub()){
                    index = 0;
                }
                if(s.getDirection().toUpperCase().equals("N")){
                    for(int l = 0; l < s.getSize(); l++){
                        Point loc = gridLeft[s.getLocation()[l].getY()][s.getLocation()[l].getX()].getLocation();
                        JLabel ship = new JLabel(shipIcons[index][0][l]);
                        p2.add(ship);
                        ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                    }
                }else if(s.getDirection().toUpperCase().equals("E")){
                    for(int l = 0; l < s.getSize(); l++){
                        Point loc = gridLeft[s.getLocation()[l].getY()][s.getLocation()[l].getX()].getLocation();
                        JLabel ship = new JLabel(shipIcons[index][1][l]);
                        p2.add(ship);
                        ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                    }
                }else if(s.getDirection().toUpperCase().equals("S")){
                    for(int l = 0; l < s.getSize(); l++){
                        Point loc = gridLeft[s.getLocation()[l].getY()][s.getLocation()[l].getX()].getLocation();
                        JLabel ship = new JLabel(shipIcons[index][2][l]);
                        p2.add(ship);
                        ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                    }
                }else{
                    for(int l = 0; l < s.getSize(); l++){
                        Point loc = gridLeft[s.getLocation()[l].getY()][s.getLocation()[l].getX()].getLocation();
                        JLabel ship = new JLabel(shipIcons[index][3][l]);
                        p2.add(ship);
                        ship.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                    }
                }
            }
        }
        for(int r = 0; r < board1.getSide(); r++){
            for(int c = 0; c < board1.getSide(); c++){
                Tile curTile;
                curTile = enemy.getTile(r, c);
                gridRight[r][c].setIcon(t2);
                if(curTile.getHit()){
                    Point loc = gridRight[r][c].getLocation();
                    JLabel mark = new JLabel();
                    p3.add(mark);
                    mark.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                    if(isShip(enemy, c, r)){
                        mark.setIcon(hit);
                    }else{
                        mark.setIcon(miss);
                    }
                }
            }
        }

        for(int r = 0; r < board1.getSide(); r++){
            for(int c = 0; c < board1.getSide(); c++){
                Tile curTile;
                curTile = curBoard.getTile(r, c);
                if(curTile.getHit()){
                    Point loc = gridLeft[r][c].getLocation();
                    JLabel mark = new JLabel();
                    p3.add(mark);
                    mark.setBounds((int)loc.getX(), (int)loc.getY(), 50, 50);
                    if(isShip(curBoard, c, r)){
                        mark.setIcon(hit);
                    }else{
                        mark.setIcon(miss);
                    }
                }
            }
        }

    }



    /**
     * Checks wheter or not the game has been won.
     * @param enemy the enemy's board.
     * @return true if the current player has won the game, 
     *          false otherwise.
     */
    public boolean checkWin(Board enemy){
        for(Ship s: enemy.getShips()){
            for(Tile t: s.getLocation()){
                if(!t.getHit()){
                    return false;
                }
            }
        }
        return true;
    }



    /**
     * If the game has not been won, calls nextTurn(),
     * otherwise, calls winScreen()
     */
    public void result(Board enemy){
        boolean win = checkWin(enemy);
        if(!win){
            nextTurn();
        }else{
            winScreen();
        }
    }



    /**
     * Covers the game in an overlay proclaiming "YOU WIN!!"
     */
    public void winScreen(){
        p4.remove(pop);
        JLabel end = new JLabel();
        p4.setOpaque(true);
        p4.add(end);
        end.setOpaque(true);
        end.setBounds(0, 0, 1920, 1080);
        end.setBackground(Color.CYAN);
        end.setText("YOU WIN!!");
    }




    /**
     * Gets rid of the overlap in between turns.
     */
    private void cont(){
        p4.setOpaque(false);
        p4.remove(overlay);
        p4.remove(next);
    }



    /**
     * Plays a beep sound.
     */
    private void signalError() {
		Toolkit t = panel.getToolkit();
		t.beep();
	}

}
