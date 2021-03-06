import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by farza on 1/13/2016.
 */
public class MinionGeneration {

    private double ledgeY;
    private double ledgeX;
    Random random = new Random(500);
    Rectangle rectangleLedge;


    public void spawnTroopers(ArrayList entities) {

        for (int i = 0; i < 5; i++)
        {
            int d = random.nextInt(500);
            int f = random.nextInt(500);
            entities.add(new Minion(500 + d, -100 - f, 10, 10));
        }
    }

    public boolean minionLedgeDetection(ArrayList entities, Minion minion) {

        for (int i = 0; i < entities.size(); i++)
        {
            entity e = (entity) entities.get(i);

            if (e instanceof Ledge)
            {
                rectangleLedge = new Rectangle((int) e.getX(), (int) e.getY() -2 , 50, 10);
                ledgeY =  e.getY();
                ledgeX = e.getX();
            }

            Rectangle rectangleMinion = new Rectangle((int) minion.getX(), (int) minion.getY(), 10, 10);

            if (collision(rectangleMinion, rectangleLedge))
            {
                if (ledgeY  == minion.getY() && minion.getX() > ledgeX);
                    return true;
            }
          
        }

        return false;
    }
    
    public void minionDrop(Minion minion)
    {
        minion.setX(minion.getX() + 1);
        minion.setY(minion.getY() + 2);
    }

    public boolean collision(Rectangle rectangleMinion, Rectangle rectangleLedge)
    {
        return rectangleMinion.intersects(rectangleLedge);
    }
}


