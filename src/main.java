import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Andreas on 15.06.2016.
 */

public class main
{
    public static void main(String[] args) throws IOException
    {
        //create file chooser
        final JFileChooser chooser = new JFileChooser("C:\\Users\\Briareus\\IdeaProjects\\AuvicumTest2\\src\\");
        //filechooser shows file with *.csv ending
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
        chooser.setFileFilter(filter);
        int returnValue = chooser.showOpenDialog(null);
        //action to perform with approve
        String csvFile = null;
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            // use file
             csvFile = chooser.getSelectedFile().getPath();
            //Methode für einlesen und weitere Verarbeitung einbauen
        }
        Data FileToHandle = new Data(csvFile);
        Patient patient = new Patient(FileToHandle.storeContent());
        patient.getPatientID(FileToHandle);


    }
}