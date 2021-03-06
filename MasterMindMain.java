import javax.swing.*;
import java.awt.*;

/**
 * Created by Damian Suski on 1/6/2016.
 */
public class MasterMindMain extends JFrame {

    public MasterMindMain(int height, int width)
    {
        setSize(height, width);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        CommandCard card = null;
        Game game = new Game(card);
        card = new CommandCard(game);
        add(card);
        add(game);
        setVisible(true);
    }

    public static void main(String args[])
    {
        //Run with different testing sizes from commandline
        if(args.length==3)
            new MasterMindMain(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        else
            new MasterMindMain(900,600);
    }
}
