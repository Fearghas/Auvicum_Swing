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
    private Data data;
    private Integer[] frequencyArray;
    private ArrayList<Date> columnAnfrageDatum;
    private ArrayList<Date> columnTerminvergabe;
    private ArrayList<Date> columnAnmeldungAnkunft;
    private ArrayList<Date> columnUntersuchungsbeginn;
    private ArrayList<Date> columnBefund;
    private ArrayList<Date> columnBefundfreigabe;
    private SimpleDateFormat newFormat;
    private Date startDate;

    public Logic(Data data) throws ParseException
    {
        this.data = data;
        columnAnfrageDatum = data.createArrayListDates(1);
        columnTerminvergabe = data.createArrayListDates(9);
        columnAnmeldungAnkunft = data.createArrayListDates(8);
        columnUntersuchungsbeginn = data.createArrayListDates(10);
        columnBefund = data.createArrayListDates(13);
        columnBefundfreigabe = data.createArrayListDates(17);
        newFormat = new SimpleDateFormat("dd.MM.yyyy");
        startDate = newFormat.parse(new String("01.12.2015"));
        frequencyArray = new Integer[6];
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
        int frequencyTermin = Collections.frequency(columnTerminvergabe, startDate);
        frequencyArray[1] = frequencyTermin;
        int frequencyAnmeldung = Collections.frequency(columnAnmeldungAnkunft, startDate);
        frequencyArray[2] = frequencyAnmeldung;
        int frequencyUntersuchung = Collections.frequency(columnUntersuchungsbeginn, startDate);
        frequencyArray[3] = frequencyUntersuchung;
        int frequencyBefund = Collections.frequency(columnBefund, startDate);
        frequencyArray[4] = frequencyBefund;
        int frequencyFreigabe = Collections.frequency(columnBefundfreigabe, startDate);
        frequencyArray[5] = frequencyBefund;
        System.out.println("Date: " + startDate + " " + "Frequency: " + frequencyAnfrage + " " + "Termin: " + frequencyTermin
        + " " + "Anmeldung: " + frequencyAnmeldung + " " + "Untersuchung: " + frequencyUntersuchung + " " +
                "Befund: " + frequencyBefund + " " + "Freigabe: " + frequencyFreigabe);
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
