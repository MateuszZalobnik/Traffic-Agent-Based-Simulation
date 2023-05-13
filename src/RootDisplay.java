import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class RootDisplay {
    public static int boardScale = 20;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Traffic Agent Based Model");
            frame.setSize(MoveModel.boardWidth*boardScale+400, MoveModel.boardWidth*boardScale);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel greenBoard = new JPanel(null);
            greenBoard.setBackground(Color.GREEN);
            greenBoard.setBounds(400, 0, MoveModel.boardWidth*boardScale, MoveModel.boardWidth*boardScale);
            Road road = new Road();
            road.setBounds(0, (MoveModel.boardWidth*boardScale/2)-25, MoveModel.boardWidth*boardScale, 50);
            greenBoard.add(road);
            frame.add(greenBoard);

            frame.setLayout(null);
            frame.setVisible(true);
        });
    }
}
