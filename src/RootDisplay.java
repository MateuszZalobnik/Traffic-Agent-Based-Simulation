import javax.swing.*;
import java.awt.*;

public class RootDisplay {
    public static int boardScale = 20;
    private static final Color BLUE = new Color(0, 50, 100);
    private static final Color LIGHT_BLUE = new Color(0, 100, 180);
    private static final Color BLACK = new Color(0, 0, 0);
    private static final Color WHITE = new Color(255, 255, 255);

    public static void display() {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Traffic Agent Based Model");
            frame.setSize(CrossRoadModel.boardWidth * boardScale + 400, CrossRoadModel.boardWidth * boardScale);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel displayBoard = new JPanel(null);
            displayBoard.setBackground(BLUE);
            displayBoard.setBounds(400, 0, CrossRoadModel.boardWidth * boardScale,
                    CrossRoadModel.boardWidth * boardScale);
            frame.add(displayBoard);

            // icon
            Image icon = Toolkit.getDefaultToolkit().getImage("TrafficLights.png");
            frame.setIconImage(icon);

            Label label = new Label(displayBoard, "Liczba samochodów", 850, 10);
            Label label2 = new Label(displayBoard, "Max czas reakcji", 850, 90);
            Label label3 = new Label(displayBoard, "Min czas reakcji", 850, 190);
            Label label4 = new Label(displayBoard, "Liczba fal samochodów", 850, 290);
            Label label5 = new Label(displayBoard, "Czas fal", 850, 390);
            Label label6 = new Label(displayBoard, "Czas swiatel", 850, 490);
            Label label7 = new Label(displayBoard, "Czas symulacji", 850, 590);

            Slider slider_number_of_cars = new Slider(850, 50, 20, 140, 40, 10, 20);
            Slider slider_max_time_reaction = new Slider(850, 130, 0, 5, 4, 2, 1);
            Slider slider_min_time_reaction = new Slider(850, 230, 0, 5, 1, 2, 1); // poprawic
            Slider slider_number_of_waves = new Slider(850, 330, 1, 5, 3, 2, 1);
            Slider slider_waves_time = new Slider(850, 430, 20, 140, 80, 10, 20);
            Slider slider_lights_time = new Slider(850, 530, 1, 13, 7, 2, 1);
            Slider slider_time_simulation = new Slider(850, 630, 50, 1000, 100, 50, 100);

            // print sliders
            slider_number_of_cars.addPanel(displayBoard);
            slider_max_time_reaction.addPanel(displayBoard);
            slider_min_time_reaction.addPanel(displayBoard);
            slider_number_of_waves.addPanel(displayBoard);
            slider_waves_time.addPanel(displayBoard);
            slider_lights_time.addPanel(displayBoard);
            slider_time_simulation.addPanel(displayBoard);

            Font Calibri = new Font("Calibri", Font.CENTER_BASELINE, 35);
            JButton restartButton = new JButton("Start/Restart");
            restartButton.setBounds(820, 700, 300, 50);
            restartButton.setBackground(LIGHT_BLUE);
            restartButton.setFont(Calibri);
            restartButton.setForeground(WHITE);

            displayBoard.add(restartButton);

            restartButton.addActionListener(e -> {
                int slider_number_of_carsValue = slider_number_of_cars.getValue();
                int slider_max_time_reactionValue = slider_max_time_reaction.getValue();
                int slider_min_time_reactionValue = slider_min_time_reaction.getValue();
                int slider_number_of_wavesValue = slider_number_of_waves.getValue();
                int slider_waves_timeValue = slider_waves_time.getValue();
                int slider_lights_timeValue = slider_lights_time.getValue();
                int slider_time_simulationValue = slider_time_simulation.getValue();
                displayBoard.removeAll();
                Road roadToRender = new Road(slider_number_of_carsValue, slider_max_time_reactionValue,
                        slider_min_time_reactionValue, slider_number_of_wavesValue, slider_waves_timeValue,
                        slider_lights_timeValue, slider_time_simulationValue);
                roadToRender.setBounds(0, 0, CrossRoadModel.boardWidth * boardScale,
                        CrossRoadModel.boardWidth * boardScale);
                displayBoard.add(roadToRender);
                displayBoard.add(restartButton);

                slider_number_of_cars.addPanel(displayBoard);
                slider_max_time_reaction.addPanel(displayBoard);
                slider_min_time_reaction.addPanel(displayBoard);
                slider_number_of_waves.addPanel(displayBoard);
                slider_waves_time.addPanel(displayBoard);
                slider_lights_time.addPanel(displayBoard);
                slider_time_simulation.addPanel(displayBoard);
                // frame.repaint();
            });

            displayBoard.setSize(1000, 1000);

            // frame.setLayout(null);
            frame.setVisible(true);

        });
    }
}