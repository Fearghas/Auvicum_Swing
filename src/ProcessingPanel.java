import processing.core.PApplet;

/**
 * Created by Briareus on 31.10.2016.
 */
public class ProcessingPanel extends PApplet
{
    int radius = getRadius();

    public void setup()
   {

   }

   public void draw() //Default sind 60 Hz wenn ohne "noLoop()"
   {
       background(51);
       stroke(0);
       fill(255, 0, 255);
       //System.out.println(radius);
       ellipse(getWidth()/2, getHeight()/2, radius, radius);
       noLoop();
   }

    public int getRadius()
    {
        return radius;
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
    }
}



