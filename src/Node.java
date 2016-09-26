import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.awt.Graphics;

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
        Data FileToHandle = new Data(mainFrame.getCsvFile());
        Patient patient = new Patient(FileToHandle.storeContent());
        patient.getPatientID(FileToHandle);
        System.out.println(patient.sortByYear(FileToHandle));
    }
}