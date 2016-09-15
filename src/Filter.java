/**
 * Created by Briareus on 05.09.2016.
 */
public class Filter
{
    private Patient list;
    private String[][] data;

    public Filter(Patient list)
    {
        this.list = list;
    }

    public int sortByYear(Data test)
    {
        int count = 0;
        for (int i = 0; i < test.getLineCounter(); i++)
        {
            String content = data[i][0];
            if (content.contains("2016"))
            {
                count++;
            }

        }
        return count;
    }
}
