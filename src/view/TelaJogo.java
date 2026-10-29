package view;

import controller.JogoController;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class TelaJogo extends JPanel {

    private JanelaPrincipal janelaPrincipal;
    private JogoController jogoController;

    private JLabel tema;
    private JLabel display;
    private JLabel letrasUsadas;
    private JTextField letraDigitada;
    private JButton confirmarPalpite;

    public TelaJogo(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20,20));

        // tema
        this.tema = new JLabel();
        this.tema.setFont(new Font("Arial", Font.BOLD, 16));
        this.tema.setAlignmentX(Component.CENTER_ALIGNMENT);

        // display
        this.display = new JLabel();
        this.display.setFont(new Font("Monospaced", Font.BOLD, 32));
        this.display.setAlignmentX(Component.CENTER_ALIGNMENT);

        // letras em uso
        this.letrasUsadas = new JLabel();
        this.letrasUsadas.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.letrasUsadas.setAlignmentX(Component.CENTER_ALIGNMENT);

        // painel de entrada do palpite
        JPanel painelDeEntrada = new JPanel(new FlowLayout());
        JLabel digite = new JLabel("Digite uma letra: ");
        digite.setFont(new Font("Arial", Font.PLAIN, 14));

        this.letraDigitada = new JTextField(3);
        this.letraDigitada.setFont(new Font("Arial", Font.BOLD, 14));
        this.letraDigitada.setHorizontalAlignment(JTextField.CENTER);

        this.confirmarPalpite = new JButton("ENVIAR PALPITE");
        this.confirmarPalpite.addActionListener(e -> {
            String palpite = this.letraDigitada.getText();

            if (!palpite.trim().isEmpty()) {
                enviarPalpite(palpite);
                this.letraDigitada.setText("");
            }

            this.letraDigitada.requestFocus();
        });

        painelDeEntrada.add(digite);
        painelDeEntrada.add(letraDigitada);
        painelDeEntrada.add(confirmarPalpite);

        this.add(this.tema);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(this.display);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(this.letrasUsadas);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(painelDeEntrada);
    }

    public void setJogoController(JogoController jogoController) {
        this.jogoController = jogoController;
    }

    public void setTema(String tema) {
        this.tema.setText(tema);
    }

    public void setDisplayText(String display) {
        this.display.setText(display);
    }

    public void setLetrasUsadas(Set<Character> letrasUsadas) {
        this.letrasUsadas.setText(letrasUsadas.toString());
    }

    public void enviarPalpite(String letra) {
        jogoController.enviarPalpite(letra.toUpperCase());
    }

    public void perdeuVoltaMenu() {
        this.setDisplayText("PERDEU");
        this.setTema("");
        this.setLetrasUsadas(new HashSet<>());

        this.janelaPrincipal.mostrarTela("MENU");
    }

}
