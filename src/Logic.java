import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Briareus on 11.10.2016.
 */
public class Logic
{
    private String[][] list;


    public Logic(String[][] list) throws ParseException
    {
        this.list = list;
    }

    public void useLogic() throws ParseException
    {
        SimpleDateFormat newFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        //String parsen zu Date mit definiertem Format
        Date startDate = newFormat.parse(String.valueOf(list[1][1]));
        System.out.println("Startdatum: " + startDate);
        String testDate = new String("01.12.2016 12:00");
        Date endDate = newFormat.parse(testDate);
        //Date endDate = newFormat.parse(String.valueOf(list[4][1]));
        System.out.println("Enddatum: " + endDate);

        //Start date und enddate im Kalender

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        Calendar cdate = Calendar.getInstance();
        //getTime wandelt um in Zeit...
        //System.out.println(start.getTime());
        //System.out.println(end.getTime());

        //Algorithmus, Rohdaten existieren versteckte Zeilen z.B. bei 30!!!!

        int counter;
        while (start.before(end)) {
            counter = 0;
            //System.out.println(start.getTime());
            for (int i = 1; i < list.length; i++)
            {
                try
                {
                    Date date = newFormat.parse(String.valueOf(list[i][1]));
                    cdate.setTime(date);
                    //System.out.println(cdate.getTime());
                    //System.out.println("Cdate: " + cdate.get(Calendar.DATE));
                    // System.out.println("Compare: " + start.get(Calendar.DATE));
                    if (cdate.get(Calendar.DAY_OF_YEAR) == start.get(Calendar.DAY_OF_YEAR))
                    {
                        //System.out.println("Date from array: " + cdate.get(Calendar.DATE) + " " + "Date advanced: " + start.get(Calendar.DATE));
                        counter++;
                    }
                }
                catch (ParseException pe)
                {
                    cdate.setTime(startDate);
                }
            }
            System.out.println("Überprüfendes Datum: " + start.getTime() + " " + "Anzahl gleiches Datum: " + counter);
            start.add(Calendar.DATE, 1);
        }
    }
}
