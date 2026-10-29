import controller.JogoController;
import model.BancoDePalavras;
import model.JogoForca;
import model.Palavra;
import service.LeitorDeArquivoPalavras;
import view.JanelaPrincipal;
import view.PainelForca;
import view.TelaJogo;
import view.TelaMenu;

import javax.swing.*;
import java.io.IOException;
import java.util.List;


public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Palavra> listaBanco = null;
            try {
                listaBanco = LeitorDeArquivoPalavras.ler("src/files/jogoforca.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            BancoDePalavras bancoDePalavras = new BancoDePalavras(listaBanco);
            JanelaPrincipal janelaPrincipal = getJanelaPrincipal(bancoDePalavras);
            janelaPrincipal.setVisible(true);
        });
    }

    private static JanelaPrincipal getJanelaPrincipal(BancoDePalavras bancoDePalavras) {
        JogoForca jogoForca = new JogoForca(bancoDePalavras);
        PainelForca painelForca = new PainelForca();

        JanelaPrincipal janelaPrincipal = new JanelaPrincipal("Jogo da Forca");

        TelaJogo telaJogo = new TelaJogo(janelaPrincipal, painelForca);
        JogoController jogoController = new JogoController(jogoForca,  telaJogo);
        telaJogo.setJogoController(jogoController);

        TelaMenu telaMenu = new TelaMenu(janelaPrincipal, jogoController);

        janelaPrincipal.adicionarTela(telaMenu, "MENU");
        janelaPrincipal.adicionarTela(telaJogo, "JOGO");

        janelaPrincipal.mostrarTela("MENU");
        return janelaPrincipal;
    }
}
