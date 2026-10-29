package view;

import javax.swing.*;
import java.awt.*;

public class TelaMenu extends JPanel {

    private JButton jogar;
    private JButton historico;
    private JButton config;

    public TelaMenu() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.jogar = new JButton();
        jogar.setText("JOGAR");
        jogar.setAlignmentX(CENTER_ALIGNMENT);
        jogar.setMaximumSize(new Dimension(100, 40));

        this.historico = new JButton();
        historico.setText("HISTORICO");
        historico.setAlignmentX(CENTER_ALIGNMENT);
        historico.setMaximumSize(new Dimension(100, 40));

        this.config = new JButton();
        config.setText("CONFIG");
        config.setAlignmentX(CENTER_ALIGNMENT);
        config.setMaximumSize(new Dimension(100, 40));

        this.add(Box.createVerticalGlue());
        this.add(jogar);
        this.add(Box.createVerticalStrut(20));

        this.add(historico);
        this.add(Box.createVerticalStrut(20));

        this.add(config);
        this.add(Box.createVerticalGlue());
    }
}
