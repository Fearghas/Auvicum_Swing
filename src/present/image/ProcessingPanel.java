package present.image;

import logic.Logic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * Created by Briareus on 13.11.2016.
 */
public class ProcessingPanel extends JFrame implements ActionListener
{
    private ProcessingView processingViewPanel;
    private Logic logic;
    private final int DELAY = 500;//milliseconds
    private Timer timer;
    private Integer[] frequency;
    private int addToAnfrage = 0;
    private int addToAnfrageEreignis = 0;
    private int addToTermin = 0;
    private int addToAnkunft = 0;
    private int addToUntersuchung = 0;
    private int addToBefund = 0;
    private int addToBefundFreigabe = 0;
    private Date date;

    public ProcessingPanel(Logic logic)
    {
        this.logic = logic;
        processingViewPanel = new ProcessingView();
        processingViewPanel.init();//aus Processing-Klasse in "Java Component" umwandeln...
        add(processingViewPanel);
        setTitle("Processing");
        setSize(1006, 629);//nicht flexibel angepasst auf size von : 1000x600
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        initTimer();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
            }
        });
    }

    private void initTimer()
    {

        timer = new Timer(DELAY, this);
        timer.start();// heisst das =>start/delay-actionPerformed wird ausgeführt-delay-actionPerformed wird ausgeführt
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        date = logic.getStartDate();
        String stringDate = String.valueOf(date);
        processingViewPanel.setDate(stringDate);
        frequency = logic.countFrequency();
        addToAnfrage = frequency[0];
        addToAnfrageEreignis = frequency[1];
        addToTermin = frequency[2];
        addToAnkunft = frequency[3];
        addToUntersuchung = frequency[4];
        addToBefund = frequency[5];
        addToBefundFreigabe = frequency[6];
        if (addToAnfrage != 0 || addToAnfrageEreignis != 0 || addToTermin != 0 || addToAnkunft != 0 || addToUntersuchung != 0
                || addToBefund != 0 || addToBefundFreigabe != 0)
        {
            if (addToAnfrage > 100 || addToAnfrageEreignis > 100 || addToTermin > 100|| addToAnkunft > 100 ||
                    addToUntersuchung > 100 || addToBefund > 100 || addToBefundFreigabe > 100)
            {
                addToAnfrage = 100;
                addToAnfrageEreignis = 100;
                addToTermin = 100;
                addToAnkunft = 100;
                addToUntersuchung = 100;
                //addToBefund = 100;
                addToBefundFreigabe = 100;
            }
            processingViewPanel.setStrokeAnfrage(1 + addToAnfrage);
            processingViewPanel.setStrokeAnfrageEreignis(1 + addToAnfrageEreignis);
            processingViewPanel.setStrokeTermin(1 + addToTermin);
            processingViewPanel.setStrokeAnkunft(1 + addToAnkunft);
            processingViewPanel.setStrokeUntersuchung(1 + addToUntersuchung);
            //processingViewPanel.setStrokeBefund(1 + addToBefund);
            processingViewPanel.setGetStrokeBefundFreigabe(1 + addToBefundFreigabe);
        }
        else
        {
            processingViewPanel.setStrokeAnfrage(0);
            processingViewPanel.setStrokeAnfrageEreignis(0);
            processingViewPanel.setStrokeTermin(0);
            processingViewPanel.setStrokeAnkunft(0);
            processingViewPanel.setStrokeUntersuchung(0);
            //processingViewPanel.setStrokeBefund(1);
            processingViewPanel.setGetStrokeBefundFreigabe(0);
        }
        processingViewPanel.redraw();
        logic.addDay();
    }
}
