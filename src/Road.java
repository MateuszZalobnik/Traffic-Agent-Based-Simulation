import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Road extends JPanel {
    private int carWidth = 15;
    private int carHeight = 15;
    private int carAllSpace = RootDisplay.boardScale;
    private static final Color ROAD_COLOR = Color.DARK_GRAY;
    private static final Color STRAIGHT_COLOR = Color.BLUE;
    private static final Color LEFT_COLOR = Color.ORANGE;
    private static final Color RIGHT_COLOR = Color.YELLOW;
    private static final Color MARKING_COLOR = Color.WHITE;
    private static final Color RED_COLOR = Color.RED;
    private static final Color GREEN_COLOR = Color.GREEN;
    private ArrayList<Car> listOfCars = new ArrayList<>(); // cars on board

    private Timer timer;

    private CrossRoadModel firstModel;

    public Road() {
        firstModel = new CrossRoadModel();
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePoints();
                repaint();
                // if(firstModel.isEnd() && secondModel.isEnd()){
                // timer.stop();
                // }
            }
        });
        timer.start();
    }

    private void updatePoints() {
        listOfCars.clear();
        listOfCars = firstModel.move();
        java.util.Iterator<Car> iterator = listOfCars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();

            int x = car.getPositionX();
            int y = car.getPositionY();

            // Wrap around the panel edges
            if (x > getWidth()) {
                iterator.remove(); // Remove the point from the list
            } else {
                car.setPosition(x, y);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the background color of the panel to green
        setBackground(ROAD_COLOR);

        // Draw the marks on the board
        for (int i = 0; i < getWidth(); i += 30) {
            g.setColor(MARKING_COLOR);
            g.fillRect(i, 398, 20, 2);
        }
        for (int i = 0; i < getWidth(); i += 30) {
            g.setColor(MARKING_COLOR);
            g.fillRect(398, i, 2, 20);
        }
        // Draw the squares on the board
        for (Car coordinate : listOfCars) {
            int x = coordinate.getPositionX() * carAllSpace;
            int y = coordinate.getPositionY();

            String dest = coordinate.getDestination();
            switch (dest) {
                case "left":
                    g.setColor(LEFT_COLOR);
                    break;
                case "right":
                    g.setColor(RIGHT_COLOR);
                    break;
                default:
                    g.setColor(STRAIGHT_COLOR);
                    break;
            }
            g.fillRect(x, y, carWidth, carHeight); // Adjust the size of the square as needed
        }
    }

}
