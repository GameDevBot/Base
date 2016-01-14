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
    private double gemX;
    private double gemY;
    private boolean onLedge = true;
    private Rectangle rectangleLedge;
    private Rectangle rectangleHero;
    private Rectangle rectangleGem;
    private Game game;
    public int gravity = 5;
    public boolean gravityOn = true;

    public Hero(double x, double y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
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

        if(y + heroDY < 0)
            y = 0;

    }

    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int)x, (int)y, (int)w, (int)h);
    }
    
    public void gemDetection(ArrayList entities, Hero hero) 
    {
        for (int i = 0; i < entities.size(); i++)
        {
            entity e = (entity) entities.get(i);

            if (e instanceof Hero)
                continue;
            if (e instanceof Ledge)
                continue;
            if (e instanceof Gem) 
            {
                gemX = e.getX();
                gemY = e.getY();
            
            
                rectangleHero = new Rectangle((int) x+heroDX, (int)y+heroDY, 8, 30);
                rectangleGem = new Rectangle((int)gemX, (int)gemY, 15, 15);

                if (gemCollision())
                {
                    entities.remove(e);
                    GemGeneration gemGen = new GemGeneration();
                    
                    Gem gem = gemGen.generateGem((Gem)e, hero);
                    gem.setX(1800);
                    entities.add(gem);
                }
            }
            
        }
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
                {
                    heroDY = 0;
                    heroScroll();
                }

                if (KeyBoard.isPressed(KeyBoard.S))
                    heroDY = 5;
            }

        }
    }

    public void heroScroll()
    {
        if(!KeyBoard.isPressed(KeyBoard.A)&&!KeyBoard.isPressed(KeyBoard.D))
        heroDX = -(int)game.scrollRate;
        onLedge = true;
    }

    public boolean collision() {return rectangleHero.intersects(rectangleLedge);}
    
    public boolean gemCollision() { return rectangleHero.intersects(rectangleGem);}

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
            heroDY = gravity;
    }
}
