import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Damian Suski on 1/6/2016.
 */
//REALLY MADE BY FARZA HAHAHA
public class Game extends JPanel implements MouseListener,MouseMotionListener,KeyListener{

    //Main thread variables
    public boolean running = true;
    final int targetFPS = 60;
    Thread thread;

    //Game variables
    public ArrayList <entity> entities;
    private SideScroller scroller;

    public Game()
    {
        initialize();
        startThread();
    }

    private void initialize()
    {
        setFocusable(true);
        setBackground(Color.BLACK);
        requestFocus();
        entities = new ArrayList();
    }

    public void paint(Graphics g)
    {
        super.paint(g);

    }

    private void update(double delta)
    {
        scroll();
    }

    private void scroll()
    {

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
                Thread.sleep((lastLoopTime-System.nanoTime()+OPTIMAL_TIME)/1000000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
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
