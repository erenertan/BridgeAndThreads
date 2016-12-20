import java.awt.*;
import java.util.Random;

/**
 * Created by halil on 13.12.2016.
 */
public class Circle extends Thread {

    Random random = new Random();

    // X coordinate of circle.
    private int x = 30;

    // Radius of circle.
    private int radius = 40;

    // Y coordinate of circle.
    private int y;

    // Animation speed of circles.
    private double speed;

    // Boolean paramator to control  does thread has semaphore or not.
    private boolean permission = false;

    //Random generated color of circle.
    private Color color = new Color(random.nextFloat(), random.nextFloat(),random.nextFloat());

    //Constructor of circle.
    Circle() {
        this.y = random.nextInt(600);
        this.speed = random.nextFloat() * 8;
    }

    @Override
    public void run() {
        while (true) {
            try {
                x += speed; //To move circles.

                //Controls if circle in bridge's borders.
                if (x + radius >= Management.bridge.getX() && x < Management.bridge.getX() + Management.bridge.getLength()) {

                    /**
                     * Checks if circle has semaphore. If has then can not take anothor one. Else take semaphore.
                     */
                    if (!permission) {
                        Management.semaphore.acquire();
                        permission = true;
                    }

                }
                //Controls circles outside of the bridge. If yes, then release semaphore.
                if ((x >= (Management.bridge.getX() + Management.bridge.getLength())) && permission){
                    Management.semaphore.release();
                    permission = false;
                }

                Thread.sleep(60);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    int getX() {
        return x;
    }

    int getRadius() {
        return radius;
    }

    int getY() {
        return y;
    }

    Color getColor() {
        return color;
    }

}
