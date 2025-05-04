package GUI;
import javax.swing.*;
import java.awt.*;

public class Roundbtn extends JButton {
    private int cornerRadius = 20;

    public Roundbtn(String text) {
        super(text);
        setContentAreaFilled(false); // Không dùng background mặc định
        setFocusPainted(false);
        setForeground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());

        // Bo góc với corner radius
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Không cần border
    }
}
