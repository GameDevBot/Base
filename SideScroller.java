import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Damian Suski on 1/6/2016.
 */
public class SideScroller {

    //Points for background drawing
    private int[] pointSize = {30,30,30};

    private LinkedList<TerrainPoint> nearBG;
    private LinkedList <TerrainPoint> midBG;
    private LinkedList <TerrainPoint> farBG;
    private ArrayList <LinkedList> allPoints;

    public SideScroller()
    {
        allPoints = new <LinkedList> ArrayList(3);

        nearBG = new <TerrainPoint> LinkedList();
        allPoints.add(nearBG);

        midBG = new <TerrainPoint> LinkedList();
        allPoints.add(midBG);

        farBG = new <TerrainPoint> LinkedList();
        allPoints.add(farBG);

        for(int i = 0; i < allPoints.size(); i++)
        {
            TerrainPoint previous = null;

            for(int j = 0; j < pointSize[i]; j++)
            {
                TerrainPoint point = generatePoint(i, previous);
                allPoints.get(i).addLast(point);
                previous = point;
            }
        }
    }

    public TerrainPoint generatePoint(int scale, TerrainPoint previous)
    {
        Random random = new Random();

        double x;
        double y;

        if(previous == null)
        {
            y = random.nextInt(100)+scale*100;
            x = 0;
            return new TerrainPoint(x,y,true);
        }

        if(previous.y > scale * 300 + 100)
            y = previous.y - random.nextInt(scale * 5 +1) - random.nextInt(5);

        else if(previous.y < scale * 100 + 50)
            y = previous.y + random.nextInt(scale * 5 +1) + random.nextInt(5);

        else
        {
            if(previous.rising)
                y = previous.y + random.nextInt(scale * 5 +1) - random.nextInt(scale * 2 +1) + random.nextInt(5);
            else
                y = previous.y - random.nextInt(scale * 5 +1) + random.nextInt(scale * 2 +1) - random.nextInt(5);
        }

        x = previous.x + 800/pointSize[scale]+5;

        if(previous.y - y < 0)
            return new TerrainPoint(x,y,true);

        return new TerrainPoint(x,y,false);
    }

    public void scroll(double rate)
    {
        for(int i = 0; i < allPoints.size();i++)
        {
            for(int j=0;j< allPoints.get(i).size();j++)
            {
                TerrainPoint p = (TerrainPoint) allPoints.get(i).get(j);
                p.x-=rate * (i+1) - 1;
                if(p.x < -50)
                {
                    allPoints.get(i).addLast(generatePoint(i,(TerrainPoint)allPoints.get(i).getLast()));
                    allPoints.get(i).remove(p);
                }
            }
        }
    }

    public void paintBackground(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        for(int i = 0; i < allPoints.size(); i++) {

            int n = pointSize[i]+2;
            int[] allX = new int[pointSize[i]+2];
            int[] allY = new int[pointSize[i]+2];

            allX[0] = 0;
            allY[0] = 600;

            int j;
            for(j = 0; j < pointSize[i]; j++)
            {
                TerrainPoint p = (TerrainPoint) allPoints.get(i).get(j);
                allX[j+1] = (int)p.x;
                allY[j+1] = (int)p.y;
            }

            allX[j+1] = 1000;
            allY[j+1] = 600;

            GradientPaint paint;

            switch(i) {
                case (0):
                    paint = new GradientPaint(0,i*200+100, new Color(38,38,50), 0, i*200+300, Color.lightGray);
                    break;

                case (1):
                    paint = new GradientPaint(0, i*200, new Color(64,64,70), 0, i*200+300, Color.gray);
                    break;

                case (2):
                    paint = new GradientPaint(0, i*200, new Color(70,75,70), 0, i*200+300, new Color(140,170,150));
                    break;

                default:
                    paint = new GradientPaint(0, i*200+200, Color.darkGray, 0, i*200, Color.gray);
                    break;
            }
            Polygon p = new Polygon(allX,allY,pointSize[i]+2);
            g2d.setPaint(paint);
            g2d.fill(p);
        }
    }
}
