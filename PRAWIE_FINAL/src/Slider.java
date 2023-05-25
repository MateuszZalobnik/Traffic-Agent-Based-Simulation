import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Slider{
    private AtomicInteger value=new AtomicInteger(30);
    private JSlider slider;
    public Slider(int x, int y, int min, int max, int def, int minor, int major) {
        slider = new JSlider(JSlider.HORIZONTAL, min, max, def);
        slider.setBounds(x, y, 250, 50);
        slider.setMinorTickSpacing(minor);
        slider.setMajorTickSpacing(major);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setForeground(Color.BLACK);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                JSlider source = (JSlider) event.getSource();
                if (!source.getValueIsAdjusting()) {
                    int v = source.getValue();
                    value.set(v);
                }
            }
        });
    }
    public int getValue(){
        return value.get();
    }

    public void addPanel(JPanel panel){
        panel.add(slider);
    }
}
