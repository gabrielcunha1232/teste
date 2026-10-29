package view;

import javax.swing.*;
import java.awt.*;

public class PainelForca extends JPanel {

    private int erros = 0;

    public PainelForca() {
        this.setPreferredSize(new Dimension(200,250));
        this.setBackground(Color.WHITE);
    }

    public void setErros(int erros){
        this.erros = erros;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(4f));
        g2d.setColor(new Color(101, 67, 33));

        g2d.drawLine(20, 220, 110, 220);
        g2d.drawLine(50, 220, 50, 30);
        g2d.drawLine(50,30,130,30);
        g2d.setStroke(new BasicStroke(2f));
        g2d.setColor(Color.black);
        g2d.drawLine(130, 30, 160, 60);

        if(erros >= 1) {
            g2d.drawOval(115, 60, 30, 30);
        }

        if (erros >= 2) {
            g2d.drawLine(130, 90, 130, 140);
        }

        if (erros >= 3) {
            g2d.drawLine(130, 105, 110, 125);
        }

        if (erros >= 4) {
            g2d.drawLine(130, 105, 150, 125);
        }

        if (erros >= 5) {
            g2d.drawLine(130, 140, 115, 180);
        }

        if (erros >= 6) {
            g2d.drawLine(130, 140, 145, 180);
        }
    }
}
