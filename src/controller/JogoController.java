package controller;

import model.JogoForca;
import view.TelaJogo;

public class JogoController {

    private TelaJogo telaJogo;
    private JogoForca jogoForca;

    public JogoController(JogoForca jogoForca, TelaJogo telaJogo) {
        this.jogoForca = jogoForca;
        this.telaJogo = telaJogo;
    }

    public void iniciarJogo() {
        jogoForca.iniciarJogo();
    }
}
