import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * Created by Briareus on 13.11.2016.
 */
public class ProcessingFrameKRN extends JFrame implements ActionListener
{
    private KRN krnPanel;
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

    public ProcessingFrameKRN(Logic logic)
    {
        this.logic = logic;
        krnPanel = new KRN();
        krnPanel.init();//aus Processing-Klasse in "Java Component" umwandeln...
        add(krnPanel);
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
        krnPanel.setDate(stringDate);
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
            krnPanel.setStrokeAnfrage(1 + addToAnfrage);
            krnPanel.setStrokeAnfrageEreignis(1 + addToAnfrageEreignis);
            krnPanel.setStrokeTermin(1 + addToTermin);
            krnPanel.setStrokeAnkunft(1 + addToAnkunft);
            krnPanel.setStrokeUntersuchung(1 + addToUntersuchung);
            //krnPanel.setStrokeBefund(1 + addToBefund);
            krnPanel.setGetStrokeBefundFreigabe(1 + addToBefundFreigabe);
        }
        else
        {
            krnPanel.setStrokeAnfrage(0);
            krnPanel.setStrokeAnfrageEreignis(0);
            krnPanel.setStrokeTermin(0);
            krnPanel.setStrokeAnkunft(0);
            krnPanel.setStrokeUntersuchung(0);
            //krnPanel.setStrokeBefund(1);
            krnPanel.setGetStrokeBefundFreigabe(0);
        }
        krnPanel.redraw();
        logic.addDay();
    }
}
