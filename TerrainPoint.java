/**
 * Created by Damian Suski on 1/6/2016.
 */
public class TerrainPoint {

    public double x;
    public double y;
    public boolean rising;

    public TerrainPoint(double x, double y, boolean rising)
    {
        this.rising=rising;
        this.x=x;
        this.y=y;
    }

    public double getX(){return x;}
    public double getY(){return y;}
}
