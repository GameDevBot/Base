import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
