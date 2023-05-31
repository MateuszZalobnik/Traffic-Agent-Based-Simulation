import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Road extends JPanel {
    private int carWidth = 15;
    private int carHeight = 15;
    private int simulationTime;
    private int carAllSpace = RootDisplay.boardScale;
    private static final Color ROAD_COLOR = Color.DARK_GRAY;
    private static final Color STRAIGHT_COLOR = Color.BLUE;
    private static final Color LEFT_COLOR = Color.PINK;
    private static final Color RIGHT_COLOR = Color.YELLOW;
    private static final Color MARKING_COLOR = Color.WHITE;
    private static final Color RED_COLOR = Color.RED;
    private static final Color GREEN_COLOR = Color.GREEN;

    private ArrayList<Car> listOfCars = new ArrayList<>(); // cars on board

    private Timer timer;

    private CrossRoadModel firstModel;

    public Road(int number_of_cars, int maxTimeReaction, int minTimeReaction, int numberOfWaves, int wavesTime,
            int lightsTime, int simulationTime) {
        firstModel = new CrossRoadModel(number_of_cars, numberOfWaves, wavesTime, minTimeReaction, maxTimeReaction,
                lightsTime);
        this.simulationTime = simulationTime;
        timer = new Timer(this.simulationTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePoints();
                repaint();
                if (firstModel.isEnd()) {
                    timer.stop();
                }
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

    private void DrawTrafficLight(Graphics g, int x, int y) {
        // Draw traffic lights
        int pointSize = 20;
        g.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);

        g.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);
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

        for (TrafficLight trafficLight : firstModel.getTrafficLightV2()) {
            g.setColor(RED_COLOR);
            if (trafficLight.getState()) {
                g.setColor(GREEN_COLOR);
            }
            DrawTrafficLight(g, trafficLight.getPositionX(), trafficLight.getPositionY());

        }

        String letter = "Krok nr:" + firstModel.getTimeStep();
        Font font = new Font("Arial", Font.BOLD, 24);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(letter, 0, 700);
    }

}