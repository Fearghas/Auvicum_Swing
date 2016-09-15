import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Andreas on 25.07.2016.
 */


public class Patient
{
    private String[][] data;
    private Data test;

    public Patient(String[][] data)
    {
        this.data = data;
    }

    public void getPatientID(Data test)
    {

        for (int i = 0; i < test.getLineCounter(); i++)
        {
            String result = data[i][0];
            //System.out.println(result);
        }
    }

    public int sortByYear(Data test)
    {
        String[] result = new String[2];
        int count2015 = 0;
        int count2016 = 0;
        for (int i = 0; i < test.getLineCounter(); i++)
        {
            String content = data[i][1];
            if (content.contains("2015"))
            {
                int count = count2015++;


            }
            else if(content.contains("2016"))
            {
                count2016++;
            }

        }
        return count2015;
    }
}

