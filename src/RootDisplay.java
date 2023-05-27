import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.event.ActionListener;

public class RootDisplay {
    public static int boardScale = 20;
    private static final Color BLUE = new Color(0, 50, 100);
    private static final Color LIGHT_BLUE = new Color(0, 100, 180);
    private static final Color BLACK = new Color(0, 0, 0);
    private static final Color WHITE = new Color(255, 255, 255);

    public static void display() {

        SwingUtilities.invokeLater(() -> {

            // Label label = new Label(greenBoard, "Liczba samochodów", 10, 10);
            // Label label2 = new Label(greenBoard, "Max czas reakcji", 310, 10);
            // Label label3 = new Label(greenBoard, "Min czas reakcji", 610, 10);
            // Label label4 = new Label(greenBoard, "Liczba fal samochodów", 10, 110);
            // Label label5 = new Label(greenBoard, "Czas fal", 310, 110);
            // Label label6 = new Label(greenBoard, "Czas swiatel", 610, 110);

            // Slider slider_number_of_cars = new Slider(10, 50, 0, 10, 9,2,1);
            // Slider slider_max_time_reaction = new Slider(310, 50, 1, 5, 1,2,1);
            // Slider slider_min_time_reaction = new Slider(610, 50, 1, 5, 2,2 ,1);
            // Slider slider_number_of_waves = new Slider(10, 150, 1, 5, 5,2,1);
            // Slider slider_waves_time = new Slider(310, 150, 20, 140, 40,10,20);
            // Slider slider_lights_time = new Slider(610, 150, 5,12, 12,2,1);

            // // print sliders
            // slider_number_of_cars.addPanel(greenBoard);
            // slider_max_time_reaction.addPanel(greenBoard);
            // slider_min_time_reaction.addPanel(greenBoard);
            // slider_number_of_waves.addPanel(greenBoard);
            // slider_waves_time.addPanel(greenBoard);
            // slider_lights_time.addPanel(greenBoard);

            // JButton button = new JButton("Restart");
            // button.setBounds(10, 250, 95, 30);
            // greenBoard.add(button);

            // button.addActionListener(e ->
            // {
            // int slider_number_of_carsValue = slider_number_of_cars.getValue();
            // int slider_max_time_reactionValue = slider_max_time_reaction.getValue();
            // int slider_min_time_reactionValue = slider_min_time_reaction.getValue();
            // int slider_number_of_wavesValue = slider_number_of_waves.getValue();
            // int slider_waves_timeValue = slider_waves_time.getValue();
            // int slider_lights_timeValue = slider_lights_time.getValue();
            // greenBoard.removeAll();
            // Road roadToRender = new Road(slider_number_of_carsValue,
            // slider_max_time_reactionValue, slider_min_time_reactionValue,
            // slider_number_of_wavesValue, slider_waves_timeValue,
            // slider_lights_timeValue);
            // roadToRender.setBounds(80, (MoveModel.boardWidth*boardScale/2)-25,
            // MoveModel.boardWidth*boardScale, 50);
            // greenBoard.add(roadToRender);
            // greenBoard.add(button);
            // slider_number_of_cars.addPanel(greenBoard);
            // slider_max_time_reaction.addPanel(greenBoard);
            // slider_min_time_reaction.addPanel(greenBoard);
            // slider_number_of_waves.addPanel(greenBoard);
            // slider_waves_time.addPanel(greenBoard);
            // slider_lights_time.addPanel(greenBoard);
            // // frame.repaint();
            // });

            JFrame frame = new JFrame("Traffic Agent Based Model");
            frame.setSize(CrossRoadModel.boardWidth * boardScale + 400, CrossRoadModel.boardWidth * boardScale);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel greenBoard = new JPanel(null);
            greenBoard.setBackground(BLUE);
            greenBoard.setBounds(400, 0, CrossRoadModel.boardWidth * boardScale,
                    CrossRoadModel.boardWidth * boardScale);

            Road road = new Road(5, 3, 3, 3, 80, 7);
            road.setBounds(0, 0, CrossRoadModel.boardWidth * boardScale, CrossRoadModel.boardWidth * boardScale);
            greenBoard.add(road);
            frame.add(greenBoard);

            // icon
            Image icon = Toolkit.getDefaultToolkit().getImage("TrafficLights.png");
            frame.setIconImage(icon);

            Label label = new Label(greenBoard, "Liczba samochodów", 850, 10);
            Label label2 = new Label(greenBoard, "Max czas reakcji", 850, 110);
            Label label3 = new Label(greenBoard, "Min czas reakcji", 850, 210);
            Label label4 = new Label(greenBoard, "Liczba fal samochodów", 850, 310);
            Label label5 = new Label(greenBoard, "Czas fal", 850, 410);
            Label label6 = new Label(greenBoard, "Czas swiatel", 850, 510);

            Slider slider_number_of_cars = new Slider(850, 50, 0, 10, 5, 2, 1);
            Slider slider_max_time_reaction = new Slider(850, 150, 1, 5, 3, 2, 1);
            Slider slider_min_time_reaction = new Slider(850, 250, 1, 5, 3, 2, 1);
            Slider slider_number_of_waves = new Slider(850, 350, 1, 5, 3, 2, 1);
            Slider slider_waves_time = new Slider(850, 450, 20, 140, 80, 10, 20);
            Slider slider_lights_time = new Slider(850, 550, 1, 13, 7, 2, 1);

            // print sliders
            slider_number_of_cars.addPanel(greenBoard);
            slider_max_time_reaction.addPanel(greenBoard);
            slider_min_time_reaction.addPanel(greenBoard);
            slider_number_of_waves.addPanel(greenBoard);
            slider_waves_time.addPanel(greenBoard);
            slider_lights_time.addPanel(greenBoard);

            JButton button = new JButton("Restart");
            button.setBounds(876, 645, 200, 70);
            button.setBackground(LIGHT_BLUE);
            Font Calibri = new Font("Calibri", Font.CENTER_BASELINE, 35);
            button.setFont(Calibri);
            button.setForeground(WHITE);
            greenBoard.add(button);

            button.addActionListener(e -> {
                int slider_number_of_carsValue = slider_number_of_cars.getValue();
                int slider_max_time_reactionValue = slider_max_time_reaction.getValue();
                int slider_min_time_reactionValue = slider_min_time_reaction.getValue();
                int slider_number_of_wavesValue = slider_number_of_waves.getValue();
                int slider_waves_timeValue = slider_waves_time.getValue();
                int slider_lights_timeValue = slider_lights_time.getValue();
                greenBoard.removeAll();
                Road roadToRender = new Road(slider_number_of_carsValue, slider_max_time_reactionValue,
                        slider_min_time_reactionValue, slider_number_of_wavesValue, slider_waves_timeValue,
                        slider_lights_timeValue);
                roadToRender.setBounds(0, 0, CrossRoadModel.boardWidth * boardScale,
                        CrossRoadModel.boardWidth * boardScale);
                greenBoard.add(roadToRender);
                greenBoard.add(button);

                slider_number_of_cars.addPanel(greenBoard);
                slider_max_time_reaction.addPanel(greenBoard);
                slider_min_time_reaction.addPanel(greenBoard);
                slider_number_of_waves.addPanel(greenBoard);
                slider_waves_time.addPanel(greenBoard);
                slider_lights_time.addPanel(greenBoard);
                // frame.repaint();
            });

            greenBoard.setSize(1000, 1000);

            // frame.setLayout(null);

            frame.setVisible(true);

        });
    }
}