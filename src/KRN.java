import processing.core.PApplet;
import processing.data.FloatList;

import static processing.core.PApplet.cos;
import static processing.core.PConstants.TWO_PI;

/**
 * Created by Briareus on 02.11.2016.
 */
public class KRN extends PApplet
{
    public static void main(String[] args)
    {
        PApplet.main("KRN");//damit eigenständig läuft: Klasse.main(was?)
    }

        int numPoints = 6;//insgesamt 6 Mittelpunkte für Ellipsen
        float radius;
        float textSize = 25;
        float zweiDrittel = (float) 2/3;
        float einDrittel = (float) 1/3;
        float dynamicAreaWidth;
        float staticAreaWidth;
        float angle = TWO_PI/(float) 4;//für 90 Grad
        float xBasePoint;
        float yBasePoint;
        float[][] basePoints;
        float xOffset;
        float yOffset;
        float boarderWidth;
        float boarderHeight;
        float distance;

        public void setup()
        {
            size (1000, 600);
            dynamicAreaWidth = width*zweiDrittel;
            boarderWidth = (float) (0.05*width);
            boarderHeight =(float) (0.05*height);
            distance = dist(dynamicAreaWidth/2, height/2, dynamicAreaWidth/2, boarderHeight);
            radius = distance;//Höhe ist limitierender Faktor für Radius
            basePoints = new float[numPoints][2];
            xOffset = dynamicAreaWidth/2;
            yOffset = height/2;
            //erstelle 4 Basispunkte für Kreis
            //Koordinatensystem verschoben mit Offsets => neuer Nullpunkt ab diesem Punkt
            pushMatrix();//speichert Koordinatensystem mit verschobenem Nullpunkt
            translate(dynamicAreaWidth/2, height/2);
            //erstellt Kreis und speicher die Koordinaten (x,y) der 4 Punkte in Liste mittels Polarkoordinaten :o)
            for (int j = 0; j < 2; j++)
            {
                for (int i = 0; i < 4; i++)//für die ersten 4 mit Winkel von 90 Grad
                {
                    if (j == 0)
                    {
                        xBasePoint = radius*sin(angle*i);
                        basePoints[i][0] = xBasePoint + xOffset;//Offset hinzurechnen, weil Koordinatensystem verschoben
                    }
                    else
                    {
                        yBasePoint = radius*cos(angle*i);
                        basePoints[i][j] = yBasePoint + yOffset;//Offset hinzurechnen, weil Koordinatensystem verschoben
                    }
                }
            }
            //Koordinaten für Kreis oben mit 20 Grad vom Mittelpunkt aus :o), noch nicht flexibel
            angle = (PI/180)*(90+20);
            basePoints[4][0] = radius*sin(angle) + xOffset;
            basePoints[4][1] = radius*cos(angle) + yOffset;
            //Koordinaten für Kreis unten mit -20 Grad vom Mittelpunkt aus, noch nicht flexibel
            angle = (PI/180)*(90-20);
            basePoints[5][0] = radius*sin(angle) + xOffset;
            basePoints[5][1] = radius*cos(angle) + yOffset;
            //Koordinaten für Kreis mitte rechts ist x = Mittelpunkt + radius/2 u. y = height/2
            popMatrix();//stellt wieder vorheriges Koordinatensystem her deswegen Offset hinzurechnen
        }

        public void draw()
        {
            background(51);
            text("x: "+mouseX+" y: "+mouseY, 10, 15);
            noStroke();
            fill(51, 51, 255);
            ellipse(basePoints[0][0], basePoints[0][1], 25, 25);//Kreis unten
            fill(0, 102, 0);
            ellipse(((dynamicAreaWidth/2)+(radius/2)), height/2, 25, 25);//Kreis rechts: Anfrage Arzt
            fill(255, 128, 0);
            ellipse(basePoints[2][0], basePoints[2][1], 25, 25);//Kreis oben: Planung
            fill(51, 51, 255);
            ellipse(basePoints[3][0], basePoints[3][1], 25, 25);//Kreis links
            fill(255, 0, 0);
            ellipse(basePoints[4][0], basePoints[4][1], 25, 25);//Kreis rechts oben: im RIS erfasst
            fill(192, 192, 192);
            ellipse(basePoints[5][0], basePoints[5][1], 25, 25);//Kreis rechts unten: Befundfreigabe
            //R = Text oben
            textSize(textSize);
            fill(255, 0, 0);
            text("ANFRAGE", dynamicAreaWidth-25, basePoints[4][1]+textSize);
            //R = Text mitte
            fill(255, 128, 0);
            text("PLANUNG", dynamicAreaWidth-25, (height/2)+(textSize/2));
            //R = Text unten
            pushMatrix();
            translate((dynamicAreaWidth), basePoints[5][1]);//wieder Nullpunkt verschieben
            rotate((float) 0.75*TWO_PI);//Koordinatensystem wird um 270 Grad gedreht mit neuem Nullpunkt
            fill(192, 192, 192);
            text("BEFUNDFREIGABE", -30, 115);
            popMatrix();
            //N =
            //Filler Text
            textSize(20);
            fill(255);
            textAlign(2);
            text("Klinik für Radiologie", dynamicAreaWidth-25, height-boarderHeight);
            text("und Nuklearmedizin", dynamicAreaWidth-25, (height-boarderHeight)+textSize);


        }

    public float getxBasePoint()
    {
        return xBasePoint;
    }

    public float getyBasePoint()
    {
        return yBasePoint;
    }
}

