package app;

import logic.Logic;
import persist.FileLoader;
import present.info.MainPanel;

/**
 * Created by Andreas on 15.06.2016.
 */

public class Auvicum
{
    public static void main(String[] args) throws Exception
    {
        MainPanel mainPanel = new MainPanel();
        mainPanel.createMainFrame();

        mainPanel.waitingForUser(); //Barriere => warten bis File ausgesucht wurde, dann gehts weiter mit Anweisungen unten
        FileLoader csvFile = new FileLoader(mainPanel.getCsvFile());
        Logic test = new Logic(csvFile.storeContent());
        mainPanel.setLogic(test);
    }
}