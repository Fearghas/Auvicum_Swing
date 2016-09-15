import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Briareus on 13.09.2016.
 */
public class MainFrame
{
    private String csvFile = null;
    private JLabel label;
    private boolean fileChosen = false;

    public MainFrame() {} //Konstruktor generisch

    public void createMainFrame()
    {
        //Hauptframe erstellen
        JFrame mainFrame = new JFrame("Auvicum");

        //Panel für Hauptframe
        Panel firstPanel = new Panel();
        firstPanel.setLayout(new FlowLayout());

        //Eigenschaften für Panel
        label = new JLabel("Choose a *.csv file");
        JButton button = new JButton();
        button.setText("Browse");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                getPath();
            }
        });

        firstPanel.add(label);
        firstPanel.add(button);

        mainFrame.add(firstPanel);
        mainFrame.setSize(300, 300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

    }

    //alle Methoden dieser Klasse
    private void getPath() {
        //JFileChooser erstellen in dem nur *.csv angezeigt wird
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
        chooser.setFileFilter(filter);
        int returnValue = chooser.showOpenDialog(null);
        //auszuführende Aktion, wenn "open" ausgesucht wird oder "cancel"
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            // use file
            csvFile = chooser.getSelectedFile().getPath();
            inputFromUser(); //Main mitteilen, dass Input gekommen ist und weitermachen kann mit Anweisung
            label.setText("Valid file chosen");
        }
        else
        {
            label.setText("Choose a *.csv file");
        }
    }

    public String setCsvFile(String csvFile)
    {
        this.csvFile = csvFile;
        return csvFile;
    }

    public String getCsvFile()
    {
        return csvFile;
    }

    //Verständnis sosolala, noch nachlesen und recherchieren für untenstehende Methoden - Stichwort "Warten bis Input"
    public void waitingForUser()
    {
        synchronized (this)
        {
            while (!fileChosen) //!fileChosen negiert wird hier zu "true"
            {
                try
                {
                    this.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void inputFromUser()
    {
        synchronized (this)
        {
            fileChosen = true;
            this.notifyAll();
        }
    }
}

