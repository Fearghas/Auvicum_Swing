package logic;

import persist.FileLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Briareus on 18.10.2016.
 */
public class Logic
{
    private FileLoader fileLoader;
    private Integer[] frequencyArray;
    private ArrayList<Date> columnAnfrageDatum;
    private ArrayList<Date> columnAnfrageEreignisDatum;
    private ArrayList<Date> columnTerminvergabe;
    private ArrayList<Date> columnAnmeldungAnkunft;
    private ArrayList<Date> columnUntersuchungsbeginn;
    private ArrayList<Date> columnBefund;
    private ArrayList<Date> columnBefundfreigabe;
    private SimpleDateFormat newFormat;
    private Date startDate;

    public Logic(FileLoader fileLoader) throws ParseException
    {
        this.fileLoader = fileLoader;
        columnAnfrageDatum = fileLoader.createArrayListDates(1);
        columnAnfrageEreignisDatum = fileLoader.createArrayListDates(2);
        columnTerminvergabe = fileLoader.createArrayListDates(9);
        columnAnmeldungAnkunft = fileLoader.createArrayListDates(8);
        columnUntersuchungsbeginn = fileLoader.createArrayListDates(10);
        columnBefund = fileLoader.createArrayListDates(13);
        columnBefundfreigabe = fileLoader.createArrayListDates(17);
        newFormat = new SimpleDateFormat("dd.MM.yyyy");
        startDate = newFormat.parse(new String("01.10.2015"));
        frequencyArray = new Integer[7];
        //Date startDate = newFormat.parse(String.valueOf(list[1][1]));
        //String testDate = new String("31.12.2016");
        //Date endDate = newFormat.parse(testDate);
        //Start date und enddate im Kalender
    }

    public Integer[] countFrequency()
    {
        //Calendar start = Calendar.getInstance();
        //start.setTime(startDate);
        //Calendar end = Calendar.getInstance();
        //end.setTime(endDate);
        int frequencyAnfrage = Collections.frequency(columnAnfrageDatum, startDate);
        frequencyArray[0] = frequencyAnfrage;
        int frequencyAnfrageEreignisDatum = Collections.frequency(columnAnfrageEreignisDatum, startDate);
        frequencyArray[1] = frequencyAnfrageEreignisDatum;
        int frequencyTermin = Collections.frequency(columnTerminvergabe, startDate);
        frequencyArray[2] = frequencyTermin;
        int frequencyAnmeldung = Collections.frequency(columnAnmeldungAnkunft, startDate);
        frequencyArray[3] = frequencyAnmeldung;
        int frequencyUntersuchung = Collections.frequency(columnUntersuchungsbeginn, startDate);
        frequencyArray[4] = frequencyUntersuchung;
        int frequencyBefund = Collections.frequency(columnBefund, startDate);
        frequencyArray[5] = frequencyBefund;
        int frequencyBefundFreigabe = Collections.frequency(columnBefundfreigabe, startDate);
        frequencyArray[6] = frequencyBefund;
        System.out.println(
                "Date: " + startDate + " " + "Anfrage KIS " + frequencyAnfrage + " " + "AnfrageEreignis: "
                        + frequencyAnfrageEreignisDatum + " " + "Termin: " + frequencyTermin + " "
                        + "Anmeldung: " + frequencyAnmeldung + " " + "Untersuchung: " + frequencyUntersuchung + " " +
                "Befund: " + frequencyBefund + " " + "Freigabe: " + frequencyBefundFreigabe);
        return frequencyArray;
    }


    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date addDay()
    {
        Calendar c = Calendar.getInstance();
        c.setTime(getStartDate());
        c.add(Calendar.DATE, 1);
        startDate = c.getTime();
        return startDate;
    }
}
