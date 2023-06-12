package Renderer;

import javax.swing.*;
import java.awt.*;

public class RoundColorPanel extends JPanel {

    private Color color;
    private Color bordu;

    public RoundColorPanel(Color color, Color colorBordur) {
        this.color = color;
        this.bordu = colorBordur;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = Math.min(getWidth(), getHeight());
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(bordu);
        g2d.fillRect(x, y, size, size);
        g2d.setColor(color);
        g2d.fillOval(x, y, size, size);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50); // Taille préférée du composant
    }

    public void setColor(Color couleur) {
        this.color = couleur;
    }

    public void setBorderColor(Color couleurBordu) {
        this.bordu = couleurBordu;
    }
}
