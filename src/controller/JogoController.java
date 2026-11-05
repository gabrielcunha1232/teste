package controller;

import model.JogoForca;
import model.ResultadoPalpite;
import view.TelaJogo;

import java.util.Set;

public class JogoController {

    private TelaJogo telaJogo;
    private JogoForca jogoForca;

    public JogoController(JogoForca jogoForca, TelaJogo telaJogo) {
        this.jogoForca = jogoForca;
        this.telaJogo = telaJogo;
    }

    public void iniciarJogo() {
        jogoForca.iniciarJogo();
        jogoForca.setarDadosDeAjuda();
        setFase();
        atualizarDadosJogo();
    }

    public void enviarAjuda() {
        ResultadoPalpite resultadoPalpite = jogoForca.ajudaRevelandoLetra();
        atualizarDadosJogo();
        switchQueVerificaCondicaoDoJogo(resultadoPalpite);
    }

    public void enviarPalpite(String letra) {
        ResultadoPalpite resultadoPalpite = this.jogoForca.enviarPalpite(letra.charAt(0));

        this.switchQueVerificaCondicaoDoJogo(resultadoPalpite);
    }

    public void setFase() {
        this.telaJogo.setFaseDisplay(this.jogoForca.getFase());
    }

    private void perdeuDerrota() {
        this.telaJogo.venceuOuPerdeuMenu("PERDEU", this.jogoForca.getPalavraAtual().getPalavra());
        setFase();
        jogoForca.resetar();
    }


    public void atualizarDadosJogo() {
        this.telaJogo.setDisplayText(this.jogoForca.getPalavraAtual().getDisplay());
        this.telaJogo.setTema(this.jogoForca.getPalavraAtual().getTema());
        this.telaJogo.setErros(this.jogoForca.getErros());
        setFase();

        Set<Character> letras = this.jogoForca.getLetras();

        this.telaJogo.setLetrasUsadas(letras);
    }

    private void switchQueVerificaCondicaoDoJogo(ResultadoPalpite resultadoPalpite) {
        switch (resultadoPalpite) {
            case VENCEU_O_JOGO -> {this.telaJogo.venceuOuPerdeuMenu("GANHOU", this.jogoForca.getPalavraAtual().getPalavra());}
            case VENCEU_A_RODADA -> {jogoForca.proximaRodada(); atualizarDadosJogo();}
            case PERDEU -> {perdeuDerrota();}
            default -> {
                atualizarDadosJogo(); setFase();
            }
        }
    }
}