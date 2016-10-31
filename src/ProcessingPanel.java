import processing.core.PApplet;

/**
 * Created by Briareus on 31.10.2016.
 */
public class ProcessingPanel extends PApplet
{
    private ProcessingFrame proccessingFrame;
    int radius = getRadius();
    int x = getX();
    int y = getY();

    public void setup()
   {
       background(255);
   }



   public void draw()
   {
       stroke(0);
       fill(255, 0, 255);
       ellipse(50, 50, radius, radius);

   }

    public int getRadius()
    {
        return radius;
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

