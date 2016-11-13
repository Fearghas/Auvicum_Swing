import processing.core.PApplet;
import processing.core.PImage;

import java.util.Date;


/**
 * Created by Briareus on 02.11.2016.
 */
public class KRN extends PApplet
{


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
        int x = 1000;//1000
        int y = 600;//600
        PImage image;
        PImage imageKRN;
    int strokeAnfrage = 0;
    int strokeAnfrageEreignis = 0;
    int strokeTermin = 0;
    int strokeAnkunft = 0;
    int strokeUntersuchung = 0;
    int strokeBefund = 0;
    int getStrokeBefundFreigabe = 0;
    String date = " ";

        public void setup()
        {
            size (x, y);
            image = loadImage("Logo.gif");
            imageKRN = loadImage("KRN.gif");
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
            //Radius berechnen für
            //distance2 = dist(dynamicAreaWidth/2, height/2, )
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
            //System.out.println(width + " " + height);

            background(96, 96, 96);
            image(image, 300, 200);
            if (millis() > 2500)
            {
                image(imageKRN, 200, 50);
            }
            if (millis() > 5000){
                background(96, 96, 96);
                //text("x: " + mouseX + " y: " + mouseY, 10, 15); //Um Koordinaten anzuzeigen
                noStroke();
                fill(0);
                ellipse(basePoints[0][0], basePoints[0][1], 25, 25);//Kreis unten: Untersuchung
                fill(0, 102, 0);
                ellipse(((dynamicAreaWidth / 2) + (radius / 2)), height / 2, 25, 25);//Kreis rechts: Anfragen KIS
                fill(255, 128, 0);
                ellipse(basePoints[2][0], basePoints[2][1], 25, 25);//Kreis oben: Planung
                fill(51, 51, 255);
                ellipse(basePoints[3][0], basePoints[3][1], 25, 25);//Kreis links: Ankunt
                fill(255, 0, 0);
                ellipse(basePoints[4][0], basePoints[4][1], 25, 25);//Kreis rechts oben: RIS
                fill(192, 192, 192);
                ellipse(basePoints[5][0], basePoints[5][1], 25, 25);//Kreis rechts unten: Befundfreigabe

            //Kurve: Start RIS, Ende Planung
                noFill();
                stroke(255, 0, 0);
                strokeWeight(strokeAnfrageEreignis);
                arc(dynamicAreaWidth / 2, height / 2, 2 * radius, 2 * radius, PI + HALF_PI, TWO_PI - ((PI / 180) * 20));//Endpunkt minus 20 Grad in radians

            //Kurve: Start Planung, Ende Ankunft
                noFill();
                stroke(255, 128, 0);
                strokeWeight(strokeTermin);
                arc(dynamicAreaWidth / 2, height / 2, 2 * radius, 2 * radius, PI, PI + HALF_PI);

            //Kurve: Start Ankunft, Ende Untersuchung
                noFill();
                stroke(51, 51, 255);
                strokeWeight(strokeAnkunft);
                arc(dynamicAreaWidth / 2, height / 2, 2 * radius, 2 * radius, HALF_PI, PI);

            //Kurve: Start Untersuchung, Ende Befundfreigabe
                noFill();
                stroke(0);
                strokeWeight(strokeUntersuchung);
                arc(dynamicAreaWidth / 2, height / 2, 2 * radius, 2 * radius, (PI / 180) * 20, HALF_PI);

            //Linie: Start Anfrage KIS, Ende RIS
                stroke(0, 102, 0);
                strokeWeight(strokeAnfrage);
                line((dynamicAreaWidth / 2) + (radius / 2), height / 2, basePoints[4][0], basePoints[4][1]);

            //Linie: Start Befundfreigabe, Ende Anfrage KIS
                stroke(192, 192, 192);
                strokeWeight(getStrokeBefundFreigabe);
                line(basePoints[5][0], basePoints[5][1], (dynamicAreaWidth / 2) + (radius / 2), height / 2);

            //R = Text oben
                textSize(textSize);
                fill(255, 0, 0);
                text("ANFRAGE", dynamicAreaWidth - 25, basePoints[4][1] + textSize);

            //R = Text mitte
                fill(255, 128, 0);
                text("PLANUNG", dynamicAreaWidth - 25, (height / 2) + (textSize / 2));

            //R = Text unten
                pushMatrix();
                translate((dynamicAreaWidth), basePoints[5][1]);//wieder Nullpunkt verschieben
                rotate((float) 0.75 * TWO_PI);//Koordinatensystem wird um 270 Grad gedreht mit neuem Nullpunkt
                fill(192, 192, 192);
                text("BEFUNDFREIGABE", -30, 115);
                popMatrix();

            //N = oben
                textSize(textSize);
                fill(51, 51, 255);
                text("ANKUNFT", dynamicAreaWidth + 160, basePoints[4][1] + textSize);

            //N = rechts
                textSize(textSize);
                pushMatrix();
                translate((dynamicAreaWidth), basePoints[5][1]);//wieder Nullpunkt verschieben
                rotate((float) 0.75 * TWO_PI);//Koordinatensystem wird um 270 Grad gedreht mit neuem Nullpunkt
                fill(0);
                text("UNTERSUCHUNG", -30, 295);
                popMatrix();

            //N = links
                textSize(textSize);
                pushMatrix();
                translate((dynamicAreaWidth), basePoints[5][1]);//wieder Nullpunkt verschieben
                rotate((float) 0.75 * TWO_PI);//Koordinatensystem wird um 270 Grad gedreht mit neuem Nullpunkt
                fill(0, 102, 0);
                text("ANFRAGEN  KIS", -30, 180);
                popMatrix();

            //Filler Text
                textSize(15);
                fill(255);
                textAlign(2);
                text("Klinik für Radiologie", width - 150, height - boarderHeight);
                text("und Nuklearmedizin", width - 150, (height - boarderHeight) + textSize);

            //Text für Datum
            textSize(20);
            fill(0);
            textAlign(2);
            text(date, dynamicAreaWidth, boarderHeight);
        }}

    public float getxBasePoint()
    {
        return xBasePoint;
    }

    public float getyBasePoint()
    {
        return yBasePoint;
    }

    public int getStrokeAnfrage()
    {
        return strokeAnfrage;
    }

    public void setStrokeAnfrage(int strokeAnfrage)
    {
        this.strokeAnfrage = strokeAnfrage;
    }

    public int getStrokeAnfrageEreignis() {
        return strokeAnfrageEreignis;
    }

    public void setStrokeAnfrageEreignis(int strokeAnfrageEreignis) {
        this.strokeAnfrageEreignis = strokeAnfrageEreignis;
    }

    public int getStrokeTermin() {
        return strokeTermin;
    }

    public void setStrokeTermin(int strokeTermin) {
        this.strokeTermin = strokeTermin;
    }

    public int getStrokeAnkunft() {
        return strokeAnkunft;
    }

    public void setStrokeAnkunft(int strokeAnkunft) {
        this.strokeAnkunft = strokeAnkunft;
    }

    public int getStrokeUntersuchung() {
        return strokeUntersuchung;
    }

    public void setStrokeUntersuchung(int strokeUntersuchung) {
        this.strokeUntersuchung = strokeUntersuchung;
    }

    public int getStrokeBefund() {
        return strokeBefund;
    }

    public void setStrokeBefund(int strokeBefund) {
        this.strokeBefund = strokeBefund;
    }

    public int getGetStrokeBefundFreigabe() {
        return getStrokeBefundFreigabe;
    }

    public void setGetStrokeBefundFreigabe(int getStrokeBefundFreigabe) {
        this.getStrokeBefundFreigabe = getStrokeBefundFreigabe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

