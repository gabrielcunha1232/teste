package view;

import javax.swing.*;

public class TelaJogo extends JPanel {

    private JanelaPrincipal janelaPrincipal;

    public TelaJogo(JanelaPrincipal janelaPrincipal) {
        JLabel jLabel = new JLabel();

        jLabel.setText("FAAAAAAAAAAAAAAAALLLLLLLLLLLLLAAAAAAAA");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(jLabel);

        this.add(Box.createHorizontalGlue());
        this.add(jLabel);
    }

}
