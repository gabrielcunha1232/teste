package view;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {

    private JPanel painelPrincipal;
    private CardLayout cardLayout;

    public JanelaPrincipal(String titulo){

        setTitle(titulo);
        setSize(400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.cardLayout = new CardLayout(10, 10);
        this.painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(cardLayout);

        this.add(painelPrincipal);
    }


    public void mostrarTela(String nome) {
        this.cardLayout.show(painelPrincipal, nome);
    }

    public void adicionarTela(JPanel tela, String nome){
        painelPrincipal.add(tela, nome);
    }
}
