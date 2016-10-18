import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andreas on 15.10.2016.
 */
public class ButtonPanelPlayStop extends JPanel{

    private JButton play;
    private JButton stop;
    private JButton pause;
    private JLabel buttonLabel;
    private Animation animation;
    private Timer timer;


    public ButtonPanelPlayStop(){
        play=new JButton("Play");
        pause=new JButton("Pause");
        stop=new JButton("Stop");

        play.setText("Play");
        pause.setText("Pause");
        stop.setText("Stop");

        buttonLabel=new JLabel();

        setLayout(new FlowLayout(1));//(this, BoxLayout.Y_AXIS));
        JToolBar toolBar = new JToolBar("Still draggable");
        add(toolBar, BorderLayout.PAGE_START);
        toolBar.add(play);

        add(buttonLabel);
        add(play);
        add(pause);
        add(stop);



        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Animation.play;
                System.out.println("Play");
            }
        });

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Timer timer = circles.getTimer();
                //System.out.println(timer);
            }
        });
        setVisible(true);

    }
}
