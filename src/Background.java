import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Background implements Runnable{
    private JLayeredPane panel;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;


    public Background(JLayeredPane p, JPanel a, JPanel b, JPanel c){
        panel = p;
        p1 = a;
        p2 = b;
        p3 = c;
    }

    public void run(){
        while(true){
            panel.setLayer(p1, 1);
            panel.setLayer(p2, 2);
            panel.setLayer(p3, 3);
        }
    }

}