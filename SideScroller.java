import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Damian Suski on 1/6/2016.
 */
public class SideScroller {

    //Points for background drawing
    private ArrayList <TerrainPoint> nearBG;
    private final int nearPoints = 20;

    private ArrayList <TerrainPoint> midBG;
    private final int midPoints = 20;

    private ArrayList <TerrainPoint> farBG;
    private final int farPoints = 20;

    private ArrayList <ArrayList> allPoints;

    public SideScroller()
    {
        nearBG = new ArrayList(nearPoints);
        allPoints.add(nearBG);

        midBG = new ArrayList(midPoints);
        allPoints.add(nearBG);

        farBG = new ArrayList(farPoints);
        allPoints.add(nearBG);

        for(ArrayList a : allPoints)
        {
            //for(int i = 0; i < )
            {

            }
        }
    }

    public void paintBackground(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

    }
}
