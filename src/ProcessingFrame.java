import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Briareus on 31.10.2016.
 */
public class ProcessingFrame extends JFrame implements ActionListener
{
    private ProcessingPanel processingPanel;
    private final int DELAY = 2000;//milliseconds
    private Timer timer;
    private int radius1 = 30;//Magic number 10 für das erste Mal zeichnen, dann abhängig von Logic
    private int radius2 = 30;
    private int radius3 = 30;
    private int radius4 = 30;
    private int radius5 = 30;
    private int radius6 = 30;
    private Logic logic;
    private Integer[] frequency;
    private int addToRadius1= 0;
    //String date;

    public ProcessingFrame(Logic logic)
    {
        this.logic = logic;
        setTitle("Processing");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        processingPanel = new ProcessingPanel();
        processingPanel.init();//aus Processing-Klasse in "Java Component" umwandeln...
        add(processingPanel);
        initTimer();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();// heisst das =>start/delay-actionPerformed wird ausgeführt-delay-actionPerformed wird ausgeführt
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        frequency = logic.countFrequency();
        addToRadius1 = frequency[0];
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
        processingPanel.setRadius(radius1+addToRadius1);

        System.out.println(radius1);
        logic.addDay();
        //repaint();

    }

}
