import java.io.*;
import java.text.ParseException;

/**
 * Created by Andreas on 15.06.2016.
 */

public class Node
{
    public static void main(String[] args) throws IOException, ParseException
    {
        MainFrame mainFrame = new MainFrame();
        mainFrame.createMainFrame();
        mainFrame.waitingForUser(); //Barriere => warten bis File ausgesucht wurde, dann gehts weiter mit Anweisungen unten
        Data csvFile = new Data(mainFrame.getCsvFile());
        mainFrame.setList(csvFile.storeContent());
        //System.out.println(csvFile.storeContent()[1][1].toString());
        Logic test = new Logic(csvFile.storeContent());
        test.useLogic();
    }
}