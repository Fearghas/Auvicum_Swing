package persist;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Briareus on 28.08.2016.
 */
public class FileLoader
{
    private String file;
    private int lineCounter;
    private ArrayList<Date> dateList;
    private String[][] list;

    //Konstruktor nimmt String als Parameter auf
    public FileLoader(String file)
    {
        this.file = file;
    }

    //Methode zum Anzahl Columns berechnen und liefert String[][] zurück
    public FileLoader storeContent() throws IOException
    {
        FileInputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String csvSplitBy = ";";
        String headerLine = bufferedReader.readLine();

        /*
        * erstellt String Liste
        * header: [0] = ID
        *         [1] = Anfrage Datum
        */
        String[] header = headerLine.split(csvSplitBy);
        int count = header.length; //zählt Elemente aus Liste = 18
        lineCounter = 1; // Headerlinie noch dazuzählen
        int indexOfHeaderElements = 0;

        //zählt wie viele Anzahl Zeilen
        while (bufferedReader.readLine() != null)
        {
            lineCounter++;
        }

        /*
        * erstellt String 2d
        * list [0][i = 0] = header[0] => list       [0] [1] [2] [3]
        * list [0][i = 1] = header[1] =>        [0]ID   Anf etc.
        * list [0][i = 1] = header[2] =>        [1]1
        */
        list = new String[lineCounter][count];
        for (int i = 0; i < count; i++)
        {
            list[indexOfHeaderElements][i] = header[i];
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
                list[y][x] = content[x];
            }
            y++;
        }
        bufferedReader.close();
        return this;
    }

    public int getLineCounter()
    {
        return lineCounter;
    }

    public ArrayList<Date> createArrayListDates(int index)
    {
        dateList = new ArrayList<>();
        SimpleDateFormat newFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (int i = 0; i < list.length; i++)
        {
            try
            {
                Date date = newFormat.parse(list[i][index]);
                dateList.add(date);

            } catch (ParseException e)
            {
                dateList.add(null);
            }
        }
        return dateList;
    }
}
