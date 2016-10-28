import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Random;

class Surface extends JPanel implements ActionListener
{

    private final int DELAY = 500;//milliseconds
    private Timer timer;
    private int radius1 = 30;//Magic number 10 für das erste Mal zeichnen, dann abhängig von Logic
    private int radius2 = 30;
    private int radius3 = 30;
    private int radius4 = 30;
    private int radius5 = 30;
    private int radius6 = 30;
    private Logic logic;
    private Integer[] frequency;
    String date;

    public Surface(Logic logic)
    {
        this.logic = logic;
        initTimer();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();// heisst das =>start/delay-actionPerformed wird ausgeführt-delay-actionPerformed wird ausgeführt
    }

    public Timer getTimer() {

        return timer;
    }

    public void stopTimer(){
        timer.stop();
    }

    public void startTimer(){
        timer.restart();
    }

    // Zeichne Ausgangslage, dann Timer, dann neu zeichnen
    private void doDrawing(Graphics g)
    {
        //draw one circle
        Graphics2D circle = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        Random r = new Random();
        //int x = Math.abs(r.nextInt()) % w;
        //int y = Math.abs(r.nextInt()) % h;
        int x = 50; //Stao im Koordinatensystem für Kreiszentrum x-Achse
        int y = 50;//Stao im Koordinatensystem für Kreiszentrum y-Achse
        System.out.println("Radius after Timer: " + radius1);
        circle.setPaint(Color.BLUE);
        circle.fillOval(x, y, radius1, radius1); //1
        circle.setPaint(Color.CYAN);
        circle.fillOval(100, 100, radius2, radius2);//2
        circle.setPaint(Color.magenta);
        circle.fillOval(150, 50, radius3, radius3);//3
        circle.setPaint(Color.YELLOW);
        circle.fillOval(200, 100, radius4, radius4);//4
        circle.setPaint(Color.red);
        circle.fillOval(250, 50, radius5, radius5);//5
        circle.setPaint(Color.PINK);
        circle.fillOval(300, 100, radius6, radius6);//6

        //draw "Date-label"
        Graphics2D text = (Graphics2D) g;
        text.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Arial", Font.PLAIN, 20);
        text.setColor(Color.black);
        text.setFont(font);
        Date date = logic.getStartDate();
        String stringDate = String.valueOf(date);
        text.drawString(stringDate, 200, 450);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        frequency = logic.countFrequency();
        int addToRadius1 = frequency[0];
        int addToRadius2 = frequency[1];
        int addToRadius3 = frequency[2];
        int addToRadius4 = frequency[3];
        int addToRadius5 = frequency[4];
        int addToRadius6 = frequency[5];
        if (addToRadius1 != 0 || addToRadius2 != 0 || addToRadius3 != 0 || addToRadius4 != 0 || addToRadius5 != 0
            || addToRadius6 != 0)
        {
            radius1 = radius1 + addToRadius1;
            radius2 = radius2 + addToRadius2;
            radius3 = radius3 + addToRadius3;
            radius4 = radius4 + addToRadius4;
            radius5 = radius5 + addToRadius5;
            radius6 = radius6 + addToRadius6;
        }
        else
        {
            radius1 = 30;
            radius2 = 30;
            radius3 = 30;
            radius4 = 30;
            radius5 = 30;
            radius6 = 30;
        }
        //System.out.println("Radius to draw: " + radius1);
        logic.addDay();
        repaint();//zeichne alles neu
    }
}

class PointsEx extends JFrame
{
    private Logic logic;
    private Surface surface;
    private Timer timer;


    //Konstruktor
    public PointsEx(Logic logic)
    {
        this.logic = logic;
        initUI();
        timer = surface.getTimer();
    }

    private void initUI()
    {

        surface = new Surface(logic);
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer;
                timer = surface.getTimer();
                timer.stop();
            }
        });

        JLabel date = new JLabel("Date comes here");

        setTitle("Circles");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void stopTimer()
    {

        timer.stop();
    }

    public void startTimer()
    {

        timer.restart();
    }

    /*public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {

                PointsEx ex = new PointsEx();
                ex.setVisible(true);
            }
        });
    }*/
}

