import java.awt.*;

/**
 * Created by Damian Suski on 1/6/2016.
 */
public interface entity {

    double getX();
    double getY();
    double getH();
    double getW();
    void setX(double d);
    void setY(double d);
    void update();
    void draw(Graphics g);
}
