import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Damian Suski on 1/6/2016.
 */
//REALLY MADE BY FARZA HAHA

    /*Ideas for final game:
    Increase / decrease scroll speed
    Reverse gravity
    Bombs
    Non lethal traps
    Make hero invisible except for certain actions (or vice versa)
    Invulnerability (except from falling)
    Removing a ledge
    Adding a ledge
    */
public class Game extends JPanel implements MouseListener,MouseMotionListener,KeyListener{

    //Main thread variables
    Hero hero;
    public boolean running = true;
    final int targetFPS = 60;
    Thread thread;
    private long tick = 0;

    //Game variables
    private int ledgeNumber = 20;
    public ArrayList <entity> entities;
    private SideScroller scroller;
    public double scrollRate = 2;
    Random random = new Random(1);

    public Game()
    {
        initialize();
        startThread();
    }


    private void initialize()
    {
        addKeyListener(this);
        scroller = new SideScroller();
        setFocusable(true);
        setBackground(Color.BLACK);
        requestFocus();
        hero = new Hero(50,545,this);
        entities = new ArrayList();
        generateInitialLedges();
        entities.add(hero);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        scroller.paintBackground(g);
        for(entity e : entities)
            e.draw(g);
    }

    private void generateInitialLedges()
    {
        Ledge previous = null;
        for(int i=0;i<ledgeNumber;i++)
        {
            Ledge ledge = generateLedge(previous);
            previous = ledge;
            entities.add(ledge);
        }
    }

    private Ledge generateLedge(Ledge previous)
    {
        if(previous == null)
        {
            return new Ledge(50,300+hero.getH(),10,50);
        }

        double ledgeX = previous .getX() + 100;
        double ledgeY = 300;

        if(previous.getY() == 300)
        {
            int determine = random.nextInt(2);

            if(determine == 1)
                ledgeY = 200;
            else
                ledgeY = 400;
        }

        else if(previous.getY() == 200)
            ledgeY = 300;

        else if(previous.getY() == 400)
        {
            int determine = random.nextInt(2);

            if(determine == 1)
                ledgeY = 300;
            else
                ledgeY = 500;
        }

        else if(previous.getY() == 500)
            ledgeY = 400;

        return new Ledge(ledgeX,ledgeY,10,50);
    }

    private void update(double delta)
    {
        scroll();
        entities.forEach(entity::update);
        hero.keyboardMovement();
        hero.ledgeDetection(entities);
    }

    private void scroll()
    {
        scroller.scroll(scrollRate);
        for(int i = 0; i< entities.size(); i++)
        {
            entity e = entities.get(i);
            if(e instanceof Hero)
                continue;


            e.setX(e.getX() - scrollRate);

            if(e.getX()<-50)
            {
                if(e instanceof Ledge)
                {
                    Ledge ledge = generateLedge((Ledge)e);
                    ledge.setX(1000);
                    entities.add(ledge);
                }

                entities.remove(e);
            }
        }
    }

    private void startThread()
    {
        thread = new Thread()
        {
            public void run() {
                gameLoop();
            }
        };

        thread.start();
    }

    //Main loop that keeps time constant across calculation
    private void gameLoop()
    {
        long lastLoopTime = System.nanoTime();
        final long OPTIMAL_TIME = 1000000000 / targetFPS;

        while(running)
        {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            //Delta variable keeps track of discrepancies in time
            double delta = updateLength / ((double)OPTIMAL_TIME);

            update(delta);
            repaint();

            try{
                if(lastLoopTime-System.nanoTime()+OPTIMAL_TIME > 0)
                    Thread.sleep((lastLoopTime-System.nanoTime()+OPTIMAL_TIME)/1000000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            tick++;
            //if(tick>100)
            //    running=!running;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyBoard.KeyPressed(e.getKeyCode(),true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyBoard.KeyPressed(e.getKeyCode(),false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    //Unused
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
