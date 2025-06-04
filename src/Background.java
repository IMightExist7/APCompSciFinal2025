import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Background implements Runnable{
    private JLayeredPane panel;
    private JPanel p0;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;


    public Background(JLayeredPane p, JPanel z, JPanel a, JPanel b, JPanel c, JPanel d){
        panel = p;
        p0 = z;
        p1 = a;
        p2 = b;
        p3 = c;
        p4 = d;
    }

    public void run(){
        while(true){
            panel.setLayer(p0, 0);
            panel.setLayer(p1, 1);
            panel.setLayer(p2, 2);
            panel.setLayer(p3, 3);
            panel.setLayer(p4, 4);
        }
    }

}