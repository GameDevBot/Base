import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
    public ArrayList <entity> entities;
    private SideScroller scroller;
    private double scrollRate = 2;

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
        hero = new Hero(0,545);
        entities = new ArrayList();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        scroller.paintBackground(g);
        hero.draw(g);
    }

    private void update(double delta)
    {
        scroll();
        hero.update();
        hero.keyboardMovement();
    }

    private void scroll()
    {
        scroller.scroll(scrollRate);
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
