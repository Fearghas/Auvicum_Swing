import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andreas on 15.06.2016.
 */

public class main {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Briareus\\IdeaProjects\\AuvicumTest2\\src\\UTF8_1.txt";
        BufferedReader bufferedReader = null;
        String line;
        String csvSplitBy = ";";
        ArrayList<Patient> patientList = new ArrayList<>();
        //Patient patient;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
            //bufferedReader = new BufferedReader(new FileReader(csvFile)); //Datei schon als UTF-8 gespeichert
            String headerLine = bufferedReader.readLine(); //liest erste Zeile, übernimmt Werte aber nicht, anschliessend zweite Zeile mit Index[0] übernimmt Werte
            while ((line = bufferedReader.readLine()) != null) {

                // use comma as separator
                String[] lineSplitted = line.split(csvSplitBy);
                //int idParsed = Integer.parseInt(lineSplitted[0]);
                //System.out.println(lineSplitted[0]);
                Patient patient = new Patient(lineSplitted[0], lineSplitted[1], lineSplitted[2], lineSplitted[3],
                        lineSplitted[4], lineSplitted[5], lineSplitted[6], lineSplitted[7], lineSplitted[8],
                        lineSplitted[9], lineSplitted[10], lineSplitted[11], lineSplitted[12], lineSplitted[13],
                        lineSplitted[14], lineSplitted[15], lineSplitted[16], lineSplitted[17], lineSplitted[18]);    //patient wird immer überschrieben in while loop
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
            System.out.println(patientList.get(0).getANF_DETAILS());
        }

    }
}