import javax.swing.*;
import java.awt.*;

public class Label {
    private static final Color BLACK = new Color(0, 0, 0);
    private static final Color WHITE = new Color(255, 255, 255);

    public Label(JPanel panel, String text, int x, int y) {
        JLabel name_slider = new JLabel(text);
        name_slider.setBounds(x, y, 200, 50);
        panel.add(name_slider);
        name_slider.setForeground(WHITE);
        Font Calibri = new Font("Calibri", Font.BOLD, 20);
        name_slider.setFont(Calibri);
    }
}
