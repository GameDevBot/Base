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
    private int[] pointSize = {60,40,20};

    private LinkedList<TerrainPoint> nearBG;
    private LinkedList <TerrainPoint> midBG;
    private LinkedList <TerrainPoint> farBG;
    private ArrayList <LinkedList> allPoints;

    public SideScroller()
    {
        allPoints = new ArrayList(3);

        nearBG = new LinkedList();
        allPoints.add(nearBG);

        midBG = new LinkedList();
        allPoints.add(midBG);

        farBG = new LinkedList();
        allPoints.add(farBG);

        for(int i = 0; i < allPoints.size(); i++)
        {
            TerrainPoint previous = null;

            for(int j = 0; j < pointSize[i]; j++)
            {
                TerrainPoint point = generatePoint(i, previous);
                allPoints.get(i).addLast(point);
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

        if(previous.y > scale * 200 + 100)
            y = previous.y + random.nextInt(scale * 5)-15;

        else if(previous.y < scale * 100 + 25)
            y = previous.y + random.nextInt(scale * 5) + 15;

        else
        {
            if(previous.rising)
                y = previous.y + random.nextInt(scale * 5) - random.nextInt(scale * 4) + random.nextInt(5) - 5;
            else
                y = previous.y - random.nextInt(scale * 5) + random.nextInt(scale * 4) - random.nextInt(5) + 5;
        }

        x = previous.x + 600/pointSize[scale];

        if(previous.y - y < 0)
            return new TerrainPoint(x,y,true);

        return new TerrainPoint(x,y,false);
    }

    public void paintBackground(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        for(int i = allPoints.size(); i >= 0; i--) {

            int n = pointSize[i];
            int[] allX = new int[pointSize[i]];
            int[] allY = new int[pointSize[i]];
            for(int j = 0; j < pointSize[i]; j++)
            {
                allX[j] = allPoints.get(i).get(j).x;
                allY[j] = allPoints.get(i).get(j).y;
            }

            GradientPaint paint = new GradientPaint(300, 50, Color.darkGray, 500, 0, Color.lightGray);
            g2d.setPaint(paint);
            g2d.fillPolygon(allX, allY, n);
        }
    }
}
