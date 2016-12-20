import java.awt.*;

/**
 * Created by halil on 13.12.2016.
 */
public class Bridge {

    //X coordinate of bridge.
    private int x = 400;

    //Y coordinate of bridge.
    private int y = 0;

    //Height of bridge.
    private int height = 600;

    //Length of bridge.
    private int length = 200;

    //Color of bridge.
    private Color color = new Color(0x000096);

    //Constructor for bridge.
    Bridge() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public Color getColor() {
        return color;
    }
}
