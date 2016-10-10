import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation extends JPanel implements ActionListener {

    private Ellipse2D.Float ellipse = new Ellipse2D.Float();
    private double esize;
    private double maxSize = 0;
    private boolean initialize = true;
    Timer timer;
    private Data data;



    public Animation(Data data)
    {
        this.data = data;
        System.out.println(data.getLineCounter());
        setXY( Math.random(), 200, 200);//Math.random = size => ersetzen und Zugriff auf Data erstellen!
        timer = new Timer(40, this); //"50" setzt Geschwindigkeit der Animation, wie schnell wird neugezeichnet
        timer.setInitialDelay(190);
        timer.start();
    }


    public void setXY(double size, int w, int h) //Ellipsen Grösse und Anfangsposition bestimmen
    {
        esize = size;
        ellipse.setFrame(50, 50, size, size); //Frame anhand der Anfangsgrösse setzen?

    }

    public void reset(int w, int h) { //methode reset wird im drawing genutzt
        maxSize = w / 4;
        setXY(maxSize * Math.random(), w, h);
    }
    public void step(int w, int h) { //methode step wird im drawing genutzt
        esize++;
        if (esize > maxSize) {
            setXY(1, w, h);
        } else {
            ellipse.setFrame(ellipse.getX(), ellipse.getY(), esize, esize);
        }
    }

    public void render(int w, int h, Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.draw(ellipse);
    }
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);
        Dimension size = getSize();
        if (initialize) {
            reset(size.width, size.height);
            initialize = false;
        }
        this.step(size.width, size.height);
        render(size.width, size.height, g2);
    }
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


    /*public static void main(String[] args) {

        JFrame frame = new JFrame("TimerBasedAnimation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Animation());
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }*/
}