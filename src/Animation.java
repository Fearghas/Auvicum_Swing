import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation extends JPanel implements ActionListener {

    private Ellipse2D.Float ellipse = new Ellipse2D.Float();
    private Ellipse2D.Float ellipse1 = new Ellipse2D.Float();
    private Ellipse2D.Float ellipse2 = new Ellipse2D.Float();
    private Ellipse2D.Float ellipse3 = new Ellipse2D.Float();
    private Ellipse2D.Float ellipse4 = new Ellipse2D.Float();
    private Ellipse2D.Float ellipse5 = new Ellipse2D.Float();
    private double ellipseSize;
    private double maxSize = 0;
    private boolean initialize = true;
    private Timer timer;
    private String[][] list;


    public Animation(String[][] list)
    {
        this.list = list;
        System.out.println(list.length);
        setXY( Math.random(), 200, 200);//Math.random = size => ersetzen und Zugriff auf Data erstellen!
        timer = new Timer(40, this); //"50" setzt Geschwindigkeit der Animation, wie schnell wird neugezeichnet
        timer.setInitialDelay(190);
        timer.start();
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setXY(double size, int w, int h) //Ellipsen Grösse und Anfangsposition bestimmen
    {
        ellipseSize = size;
        ellipse.setFrame(Math.random()*150, Math.random()*250, 250, size); //Position der Animation 50=x, 50=y, Grösse der elipse height = size, width = size
        ellipse1.setFrame(25,250,size,size);
        ellipse2.setFrame(10,10,size,size);
        ellipse3.setFrame(160,60,size,size);
        ellipse4.setFrame(155,55,size,size);
        ellipse5.setFrame(200,30,size,size);


    }

    public void reset(int w, int h) { //methode reset wird im drawing genutzt
        maxSize = w / 4;
        setXY(maxSize * Math.random(), w, h);
    }
    public void step(int w, int h) { //methode step wird im drawing genutzt
        ellipseSize++;
        if (ellipseSize > maxSize) {
            setXY(1, w, h);
        } else {
            ellipse.setFrame(ellipse.getX(), ellipse.getY(), ellipseSize, ellipseSize);//Sets the location and size of the framing rectangle of this Shape to the specified rectangular values.
            ellipse1.setFrame(ellipse1.getX(), ellipse1.getY(), ellipseSize, ellipseSize);
            ellipse2.setFrame(ellipse2.getX(), ellipse2.getY(), ellipseSize, ellipseSize);
            ellipse3.setFrame(ellipse3.getX(), ellipse3.getY(), ellipseSize, ellipseSize);
            ellipse4.setFrame(ellipse4.getX(), ellipse4.getY(), ellipseSize, ellipseSize);
            ellipse5.setFrame(ellipse5.getX(), ellipse5.getY(), ellipseSize, ellipseSize);

        }
    }

    public void render(int w, int h, Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.draw(ellipse);
        g2.draw(ellipse1);
        g2.draw(ellipse2);
        g2.draw(ellipse3);
        g2.draw(ellipse4);
        g2.draw(ellipse5);
        g2.fill(ellipse);
        g2.fill(ellipse1);

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

    public void stopTimer(){
        timer.stop();
    }

    public void startTimer(){
        timer.restart();
    }


}