import java.io.*;

/**
 * Created by Andreas on 15.06.2016.
 */

public class Node
{
    public static void main(String[] args) throws IOException
    {
        MainFrame mainFrame = new MainFrame();
        mainFrame.createMainFrame();
        mainFrame.waitingForUser(); //Barriere => warten bis File ausgesucht wurde, dann gehts weiter mit Anweisungen unten
        Data csvFile = new Data(mainFrame.getCsvFile());
        mainFrame.setData(csvFile.storeContent());

    }
}