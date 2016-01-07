import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Damian Suski on 1/6/2016.
 */
public class KeyBoard {
    private static final boolean keyState[] = new boolean[5];
    public static final int W = 0;
    public static final int A = 1;
    public static final int S = 2;
    public static final int D = 3;
    public static final int Escape = 4;
    private static final int numKeys = 5;

    public static void KeyPressed(int i, boolean b){
        if(i == KeyEvent.VK_W) keyState[W] = b;
        else if(i == KeyEvent.VK_A) keyState[A] = b;
        else if(i == KeyEvent.VK_S) keyState[S] = b;
        else if(i == KeyEvent.VK_D) keyState[D] = b;
        else if(i == KeyEvent.VK_ESCAPE) keyState[Escape] = b;
    }

    public static boolean isPressed(int key){
        return keyState[key];
    }

}
