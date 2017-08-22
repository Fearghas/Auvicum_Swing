package persist;

import model.PatientParticle;
import model.SimplePatientParticle;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CsvFileLoader {

    public CsvFileLoader() {
    }

    public static ArrayList<PatientParticle> createWorkflowData(String file) throws IOException {
        List<PatientParticle> particles = new ArrayList<>();
        Scanner scanner = new Scanner(new File(file));
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        // Number of columns in muster.txt/excel file
        int tokenLength = 18;

        // Number of column anmeldung_ankunft
        int indexAnkunft = 8;

        // Number of column untersuchungs_beginn
        int indexBeginn = 10;

        // Number of column untersuchungs_ende
        int indexEnde = 11;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\t");
            if (tokens.length == tokenLength) {
                try {
                    Date anmeldungAnkunft = dateFormat.parse(tokens[indexAnkunft]); //dateFormat.parse macht Datum aus anmeldeAnkunft
                    Date untersuchungsBeginn = dateFormat.parse(tokens[indexBeginn]);
                    Date untersuchungsEnde = dateFormat.parse(tokens[indexEnde]);

                    // Date to milliseconds with  method getTime() since 01.01.1970
                    PatientParticle particle = new SimplePatientParticle(anmeldungAnkunft.getTime(),
                            untersuchungsBeginn.getTime(), untersuchungsEnde.getTime());
                    particles.add(particle);
                } catch (ParseException e) {
                    //System.err.println(tokens[8] + ", " + tokens[17]);
                    System.err.println("Error formating date, ignoring line...");
                }
            } else {
                //System.err.println("Not enough tokens found, ignoring line...");
            }
        }
        return (ArrayList<PatientParticle>) particles;
    }
}
