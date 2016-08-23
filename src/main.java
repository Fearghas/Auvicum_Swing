import java.io.*;
import java.util.ArrayList;

/**
 * Created by Andreas on 15.06.2016.
 */

public class main {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Andreas\\Desktop\\muster.csv";
        BufferedReader bufferedReader = null;
        String line = null;
        String csvSplitBy = ";";
        ArrayList<Patient> patientList = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile)));
            {
                //bufferedReader = new BufferedReader(new FileReader(csvFile)); //Datei schon als UTF-8 gespeichert
                String headerLine = bufferedReader.readLine(); //liest erste Zeile, übernimmt Werte aber nicht, anschliessend zweite Zeile mit Index[0] übernimmt Werte
                int count = headerLine.length() - headerLine.replace(";", "").length();
                System.out.println(headerLine.length()); //Für Verständnis der Colum berechnung
                System.out.println(headerLine.replace(";", "").length()); //Für Verständnis der colum berechnung
                System.out.println(count); //Anzahl der Spalten
            }
            while ((line = bufferedReader.readLine()) != null) {

                // use comma as separator
                String[] lineSplitted = line.split(csvSplitBy, -1);

                //int idParsed = Integer.parseInt(lineSplitted[0]);
                //System.out.println(lineSplitted[0]);
                Patient patient = new Patient(lineSplitted[0], lineSplitted[1], lineSplitted[2], lineSplitted[3],
                        lineSplitted[4], lineSplitted[5], lineSplitted[6], lineSplitted[7], lineSplitted[8],
                        lineSplitted[9], lineSplitted[10], lineSplitted[11], lineSplitted[12], lineSplitted[13],
                        lineSplitted[14], lineSplitted[15], lineSplitted[16], lineSplitted[17]);    //patient wird immer überschrieben in while loop
                patientList.add(patient);

                //System.out.println(lineSplitted[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close(); // close bufferreader
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}