/**
 * Created by Andreas on 15.06.2016.
 */

public class Auvicum
{
    public static void main(String[] args) throws Exception {
        MainFrame mainFrame = new MainFrame();
        mainFrame.createMainFrame();
        mainFrame.waitingForUser(); //Barriere => warten bis File ausgesucht wurde, dann gehts weiter mit Anweisungen unten
        Data csvFile = new Data(mainFrame.getCsvFile());
        Logic test = new Logic(csvFile.storeContent());
        mainFrame.setLogic(test);
    }
}