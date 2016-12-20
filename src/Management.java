import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by halil on 13.12.2016.
 */
public class Management extends JPanel {

    static Bridge bridge;
    static Semaphore semaphore; //Thread mechanism to proceed many thread.
    private ArrayList<Circle> arrayList = new ArrayList<>();

    /**
     * Constructor of class. Creates frame, panel, bridge.
     * @param numberOfThreads input of threads which will be created.
     * @param permissions number of semaphores. Different than circles permission.
     */
    private Management(int numberOfThreads, int permissions) {

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setBounds(100, 100,1000, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        this.setVisible(true);
        frame.add(this);
        this.setSize(1000,600);

        //Bridge created.
        bridge = new Bridge();

        //Method called to create threads.
        createThreads(numberOfThreads);

        //Creates semaphore and gives the number of semaphores.
        semaphore = new Semaphore(permissions);
        repaint();
    }

    /**
     * Method to create threads from circle class and run them.
     * Add them arraylist.
     * @param numberOfThreads number of how many thread will be created.
     */
    private void createThreads(int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++){
            Circle circle = new Circle();
            circle.start();

            arrayList.add(circle);
        }
    }

    /**
     * First creates bridge and fill it.
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(bridge.getX(), bridge.getY(), bridge.getLength(), bridge.getHeight());
        g.setColor(bridge.getColor());

        for (Circle circle:arrayList) {
            g.fillOval(circle.getX(), circle.getY(), circle.getRadius(),circle.getRadius());
            g.setColor(circle.getColor());
        }
    }

    public void controlBridgeTraffic() {
        for (;;){
            repaint();
        }
    }


    public static void main(String[] args) {
        int inputOne; //Numbers of threads.
        int inputTwo; //Numbers of semaphores.

        Management management = new Management(10, 2);
        management.controlBridgeTraffic();

        if (args.length > 0) {
            inputOne = Integer.parseInt(args[0]);
            inputTwo = Integer.parseInt(args[1]);

            //Management management = new Management(inputOne, inputTwo);
        }
    }
}
