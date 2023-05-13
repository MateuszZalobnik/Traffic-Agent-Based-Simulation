import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class Road extends JPanel {
    private int carWidth = 15;
    private int carHeight = 12;
    private int carAllSpace = RootDisplay.boardScale;
    private static final Color ROAD_COLOR = Color.DARK_GRAY;
    private static final Color SQUARE_COLOR = Color.BLUE;
    private static final Color MARKING_COLOR = Color.WHITE;
    private static final Color RED_COLOR = Color.RED;
    private static final Color GREEN_COLOR = Color.GREEN;

    private List<Point> points;
    private Timer timer;

    private MoveModel firstModel;
    private MoveModel secondModel;

    public Road() {
        points = new ArrayList<>();
        firstModel = new MoveModel(20, 4, 140, false,1,3,9);
        secondModel = new MoveModel(20,4,20, true, 1,3,12);

        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePoints();
                repaint();
                if(firstModel.isEnd() && secondModel.isEnd()){
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private void updatePoints() {
        points.clear();
        for (Car item : firstModel.move()) {
            int x = item.getPositionX();
            int y = item.getPositionY();
            points.add(new Point(x, y));
        }
        for (Car item : secondModel.move()) {
            int x = item.getPositionX();
            int y = item.getPositionY();
            points.add(new Point(x, y));
        }
        java.util.Iterator<Point> iterator = points.iterator();
        while (iterator.hasNext()) {
            Point point = iterator.next();

            int x = point.x;
            int y = point.y;


            // Wrap around the panel edges
            if (x > getWidth()) {
                iterator.remove(); // Remove the point from the list
            } else {
                point.setLocation(x, y);
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
            g.fillRect(i, 24, 20, 2);
        }

        // Draw the squares on the board
        for (Point coordinate : points) {
            int x = coordinate.x*carAllSpace;
            int y = coordinate.y;

            g.setColor(SQUARE_COLOR);
            g.fillRect(x, y, carWidth, carHeight); // Adjust the size of the square as needed
        }

        //Draw traffic lights for first model
        if (firstModel.getLight().getState() == "red") {
            g.setColor(RED_COLOR);
        } else {
            g.setColor(GREEN_COLOR);
        }
        g.fillRect(MoveModel.boardWidth*20/2-50, 32, 15,15);
        
        //Draw traffic lights for second model
        if (secondModel.getLight().getState() == "red") {
            g.setColor(RED_COLOR);
        } else {
            g.setColor(GREEN_COLOR);
        }
        g.fillRect(MoveModel.boardWidth*20/2+25, 5, 15,15);
    }

}
