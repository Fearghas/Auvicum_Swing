import java.io.*;
import java.util.ArrayList;

/**
 * Created by Briareus on 28.08.2016.
 */
public class Data
{
    private Patient patient;
    private String file;
    int count;
    String[] header;

    public Data(String file)
    {
        this.file = file;
    }

    //Methode zum Anzahl Columns berechnen
    public void storeContent() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String headerLine = bufferedReader.readLine();
        String csvSplitBy = ";";
        String[] header = headerLine.split(csvSplitBy);
        count = header.length;
        String[][] multiDimensional = new String[1][18];
        //ArrayList<String> patientList = new ArrayList<>(count-1);
        for (int i = 0; i < count; i++)
        {
            multiDimensional[0][i] = header[i];
            //patientList.add(header[i]);
            //System.out.println(patientList.get(i));
        }
        System.out.println(multiDimensional[0][17]);
        /*String lineAfterHeader;
        while ((lineAfterHeader = bufferedReader.readLine()) != null)
        {
            String[] contentOfCorrespondedHeader = lineAfterHeader.split(csvSplitBy, -1);
            for (int i = 0; i < count; i++)
            {

                header[i].contentOfCorrespondedHeader[i];
            }
        }*/
        bufferedReader.close();
        //return patientList;

    }

    //Methode Anzahl Variablen in Konstruktor für Patient einbauen
    public String createConstructorForPatient()
    {
        String possible = " ";
        for (int i = 0; i < count; i++)
        {
            possible = header[i];
        }
        return possible;
    }
}
