import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Damian Suski on 1/6/2016.
 */
public class Hero implements entity {

    private double x;
    private double y;
    private double w = 8;
    private double h = 30;
    private int heroDX = 0;
    private int heroDY = 0;
    private double ledgeY;
    private double ledgeX;
    private boolean onLedge = true;
    private Rectangle rectangleLedge;
    private Rectangle rectangleHero;
    public Hero(double x, double y) {
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
    public void setX(double d) {
        this.x=d;
    }

    @Override
    public void setY(double d) {
        this.y=d;
    }

    @Override
    public void update()
    {
        if(x + heroDX < 0)
        {
            x = 0;
        }

        else if(x + heroDX > 800 - w)
        {
            x = 792-w;
        }

        else
            x += heroDX;

        if (y + heroDY < 550)
            y += heroDY;

    }

    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int)x, (int)y, (int)w, (int)h);
    }

    //Checks if hero collides with top of ledge using rectangle collision.
    public void ledgeDetection(ArrayList entities)
    {
        for (int i = 0; i < entities.size(); i++)
        {
            entity e = (entity) entities.get(i);

            if (e instanceof Hero)
                continue;

            if (e instanceof Ledge)
            {
                ledgeX = e.getX();
                ledgeY = e.getY();
            }

            rectangleLedge = new Rectangle((int)ledgeX, (int)ledgeY, 50, 10);
            rectangleHero = new Rectangle((int) x+heroDX, (int)y+heroDY, 8, 30);

            if (collision()) {
                if (ledgeY - 30 == y)
                    heroDY = 0;
                if (KeyBoard.isPressed(KeyBoard.S))
                    heroDY = 5;
            }

        }
    }

    public boolean collision() {return rectangleHero.intersects(rectangleLedge);}

    public void keyboardMovement()
    {
        if (KeyBoard.isPressed(KeyBoard.A))
            heroDX = -5;
        if (!(KeyBoard.isPressed(KeyBoard.A))&&!(KeyBoard.isPressed(KeyBoard.D)))
            heroDX = 0;
        if (KeyBoard.isPressed(KeyBoard.D))
            heroDX = 5;
        if (KeyBoard.isPressed(KeyBoard.W))
            heroDY = -5;
        if (!(KeyBoard.isPressed(KeyBoard.W)))
            heroDY = 5;
    }
}
