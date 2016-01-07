import java.awt.*;

/**
 * Created by Damian Suski on 1/6/2016.
 */
public class Hero implements entity{

    private double x;
    private double y;
    private int w = 8;
    private int h = 30;
    private double velocity;
    int heroX = 0;
    int heroDX = 1;
    public Hero(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getH() {
        return 0;
    }

    @Override
    public double getW() {
        return 0;
    }

    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(heroX, 545, w, h);

    }

    public void move()
    {
        heroX = heroDX + heroX;
    }
}
