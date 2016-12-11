package present.info;

import logic.Logic;
import present.image.ProcessingPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Briareus on 13.09.2016.
 */
public class MainPanel extends JPanel
{
    private String csvFile = null;
    private JLabel label;
    private JLabel labelTwo;
    private boolean fileChosen = false;
    private Logic logic;
    private Timer timer;
    private JButton stopButton;
    private JButton buttonDraw;
    private JButton button;
    private Panel firstPanel;
    private JButton playButton;

    public MainPanel(){}

    public void createMainFrame()
    {
        //Hauptframe erstellen
        JFrame mainFrame = new JFrame("app.Auvicum");
        //Panel für Hauptframe erstellen
        firstPanel = new Panel();
        //setLayout mit BoxLayout/BorderLayout/GridLayout für Standort der Panels zu organisiere
        firstPanel.setLayout(new GridLayout(0,1));

        //Eigenschaften für Panel
        label = new JLabel("Choose CSV File: ", JLabel.CENTER);
        //labelTwo = new JLabel("Nothing to draw", JLabel.CENTER);
        button = new JButton();
        button.setText("Browse");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                getPath();
            }
        });
        buttonDraw = new JButton();
        buttonDraw.setForeground(Color.RED);
        buttonDraw.setText("Nothing To Draw");
        buttonDraw.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Weiter mit present.image.ProcessingView
                ProcessingPanel processingPanel = new ProcessingPanel(getLogic());
                timer = processingPanel.getTimer();

            }
        });

        stopButton = new JButton();
        stopButton.setText("Pause");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //ex.stopTimer();
                timer.stop();
            }
        });

        playButton=new JButton();
        playButton.setText("Play");
        playButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               //ex.startTimer();
                timer.start();
            }
        });
        firstPanel.add(label);
        firstPanel.add(button);
        //firstPanel.add(labelTwo);
        firstPanel.add(buttonDraw);
        firstPanel.add(stopButton);
        firstPanel.add(playButton);
        firstPanel.setBackground(Color.WHITE);

        mainFrame.getContentPane().add(firstPanel);
        mainFrame.setSize(500, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

    }

    //alle Methoden dieser Klasse
    private void getPath() {
        //JFileChooser erstellen in dem nur *.csv angezeigt wird
        JFileChooser chooser = new JFileChooser("c://auvicum");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
        chooser.setFileFilter(filter);
        int returnValue = chooser.showOpenDialog(null);
        //auszuführende Aktion, wenn "open" ausgesucht wird oder "cancel"
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            // use file
            csvFile = chooser.getSelectedFile().getPath();
            inputFromUser(); //Main mitteilen, dass Input gekommen ist und weitermachen kann mit Anweisung
            label.setForeground(Color.BLACK);
            label.setText("Valid File Chosen!");
            buttonDraw.setForeground(Color.GREEN);
            buttonDraw.setText("Draw Content");

        }
        /*
        else
        {
            label.setText("Choose CSV File");
        }
        */
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
            while (!fileChosen) //!fileChosen negiert, wird hier zu "true"
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

    public void setLogic(Logic logic)
    {
        this.logic = logic;
    }

    public Logic getLogic()
    {

        return logic;
    }

}
