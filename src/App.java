import view.JanelaPrincipal;
import view.TelaJogo;
import view.TelaMenu;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal janelaPrincipal = new JanelaPrincipal("Jogo da Forca");
            TelaMenu telaMenu = new TelaMenu(janelaPrincipal);
            TelaJogo telaJogo = new TelaJogo(janelaPrincipal);

            janelaPrincipal.adicionarTela(telaMenu, "MENU");
            janelaPrincipal.adicionarTela(telaJogo, "JOGO");

            janelaPrincipal.mostrarTela("MENU");

            janelaPrincipal.setVisible(true);
        });
    }
}
