import java.io.IOException;
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
            System.out.println(result);
        }
    }
}

