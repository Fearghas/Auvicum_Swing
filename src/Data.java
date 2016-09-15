import java.io.*;
import java.util.ArrayList;

/**
 * Created by Briareus on 28.08.2016.
 */
public class Data
{

    private String file;
    private int lineCounter;


    public Data(String file)
    {
        this.file = file;
    }

    //Methode zum Anzahl Columns berechnen und liefert String[][] zurück
    public String[][] storeContent() throws IOException
    {
        FileInputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String csvSplitBy = ";";
        String headerLine = bufferedReader.readLine();
        String[] header = headerLine.split(csvSplitBy);
        int count = header.length;
        lineCounter = 1; // Headerlinie noch dazuzählen
        int indexOfHeaderElements = 0;
        while (bufferedReader.readLine() != null)
        {
            lineCounter++;
        }
        String[][] multiDimensional = new String[lineCounter][count];
        for (int i = 0; i < count; i++)
        {
            multiDimensional[indexOfHeaderElements][i] = header[i];
        }
        inputStream.getChannel().position(0); //BufferedReader springt zurück zum Textanfang
        //System.out.println("x-Achse: " + count + " " + "y-Achse: " + lineCounter);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //wieder neu initialisieren
        headerLine = bufferedReader.readLine();
        String lineAfterHeader;
        int y = 1;
        while ((lineAfterHeader = bufferedReader.readLine()) != null)
        {
            String[] content = lineAfterHeader.split(csvSplitBy, -1); //String mit 17 Elementen
            for (int x = 0; x < count; x++)
            {
                multiDimensional[y][x] = content[x];
            }
            y++;
        }
        //System.out.println(multiDimensional[7532][1]);
        bufferedReader.close();
        return multiDimensional;
    }

    public int getLineCounter()
    {
        return lineCounter;
    }
}
