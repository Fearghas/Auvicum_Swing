import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Briareus on 13.09.2016.
 */
public class MainFrame extends JPanel
{
    private String csvFile = null;
    private JLabel label;
    private JLabel labelTwo;
    private boolean fileChosen = false;
    private String [][] list;
    private Animation circles;
    private MainFrame mainFrame;
    private JButton stopButton;
    private JButton buttonDraw;
    private JButton button;
    private Panel firstPanel;
    private JButton playButton;

    public MainFrame(){}

    public void createMainFrame()
    {
        //Hauptframe erstellen
        JFrame mainFrame = new JFrame("Auvicum");
        //Panel für Hauptframe erstellen
        firstPanel = new Panel();
        //setLayout mit BoxLayout/BorderLayout/GridLayout für Standort der Panels zu organisiere
        firstPanel.setLayout(new GridLayout(0,1));


        //Eigenschaften für Panel
        label = new JLabel("Choose a *.csv file");
        labelTwo = new JLabel("Draw Image - creates a new frame");
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
        buttonDraw.setText("Draw");

        buttonDraw.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame frame = new JFrame("TimerBasedAnimation");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                MainFrame.this.circles = new Animation(getList());
                frame.add(circles);
                frame.setSize(450, 350);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        stopButton = new JButton();
        stopButton.setText("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circles.stopTimer();
            }
        });

        playButton=new JButton();
        playButton.setText("Play");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circles.startTimer();
            }
        });
        firstPanel.add(label);
        firstPanel.add(button);
        firstPanel.add(labelTwo);
        firstPanel.add(buttonDraw);
        firstPanel.add(stopButton);
        firstPanel.add(playButton);



        mainFrame.getContentPane().add(firstPanel);
        mainFrame.setSize(500, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

    }

    //alle Methoden dieser Klasse
    private void getPath() {
        //JFileChooser erstellen in dem nur *.csv angezeigt wird
        JFileChooser chooser = new JFileChooser("C:\\Users\\Andreas\\IdeaProjects\\Auvicum_Swing\\src");
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

    public String[][] getList()
    {
        return list;
    }

    public void setList(String[][] list)
    {
        this.list = list;
    }

}
