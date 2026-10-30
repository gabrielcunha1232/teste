package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class JogoForca {

    private BancoDePalavras banco;
    private Palavra palavraAtual;
    private int maxVida;
    private int vidaAtual;
    private int fase;
    private int qtdAjudaNaFase;
    private int maxQtdAjuda;
    private Set<Character> letras;
    private EstadoJogo estadoJogo;
    private Random random = new Random();

    public JogoForca(BancoDePalavras banco) {
        this.banco = banco;
        this.maxVida = 6;
        this.fase = 1;
        this.letras = new HashSet<>();
        this.vidaAtual = maxVida;
        this.estadoJogo = EstadoJogo.MENU;
    }

    public int getMaxVida() {
        return this.maxVida;
    }

    public int getVidaAtual() {
        return this.vidaAtual;
    }

    public int getFase() {
        return this.fase;
    }

    public int getErros() {
        return getMaxVida() - getVidaAtual();
    }

    private void setPalavraAtual(Palavra palavraAtual) {
        this.palavraAtual = palavraAtual;
    }

    public EstadoJogo getEstadoJogo() {
        return this.estadoJogo;
    }

    public Palavra getPalavraAtual() {
        return this.palavraAtual;
    }

    public Set<Character> getLetras() {
        return Collections.unmodifiableSet(this.letras);
    }

    public void iniciarJogo() {
        this.estadoJogo = EstadoJogo.JOGANDO;
        Palavra palavraSorteada = banco.sortearPalavra();
        setPalavraAtual(palavraSorteada);
    }

    public ResultadoPalpite enviarPalpite(char letra) {
        char letraEmMaisculo = Character.toUpperCase(letra);

        if (letras.contains(letraEmMaisculo)) {
            return ResultadoPalpite.LETRA_JA_USADA;
        }

        this.letras.add(letraEmMaisculo);
        String letraDigitada = Character.toString(letraEmMaisculo);

        boolean letraExiste = this.palavraAtual.letraExiste(letraDigitada);

        if (!letraExiste) {
            return errouChecaVida();
        }

        return acertouChecaCondicao(letraDigitada);
    }

    public void proximaRodada() {
        this.fase += 1;
        this.qtdAjudaNaFase = 0;
        if (this.fase == 10) {
            this.vidaAtual = Math.min(getVidaAtual() + 2, getMaxVida());
        }
        this.letras.clear();
        this.estadoJogo = EstadoJogo.JOGANDO;

        Palavra proximaPalavra = this.banco.sortearPalavra();
        setPalavraAtual(proximaPalavra);
        setarDadosDeAjuda();
    }

    public void resetar() {
        this.fase = 1;
        this.qtdAjudaNaFase = 0;
        this.letras.clear();
        this.palavraAtual = null;
        this.banco.resetar();
        this.vidaAtual = getMaxVida();
        this.estadoJogo = EstadoJogo.MENU;
    }

    public void setarDadosDeAjuda() {
        int tamanhoPalavraAtual = this.palavraAtual.getPalavra().length();

        if (tamanhoPalavraAtual > 7) {
            this.maxQtdAjuda = 3;
        } else {
            this.maxQtdAjuda = 1;
        }
    }

    public void ajudaRevelandoLetra() {
        if (this.qtdAjudaNaFase >= maxQtdAjuda) {
            return;
        }

        String palavra = this.palavraAtual.getPalavra();
        int totalLetras = palavra.length();


        int idxToReveal;
        char letraSorteada;

        do {
            idxToReveal = random.nextInt(totalLetras);
            letraSorteada = palavra.charAt(idxToReveal);
        } while (this.letras.contains(letraSorteada));

        this.palavraAtual.revelar(Character.toString(letraSorteada));
        this.letras.add(letraSorteada);
        this.qtdAjudaNaFase++;
    }

    private ResultadoPalpite acertouChecaCondicao(String letra) {
        this.palavraAtual.revelar(letra);

        boolean venceu = this.palavraAtual.venceu();
        boolean temMaisLetras = this.banco.aindaTemPalavra();

        if (venceu && temMaisLetras) {
            this.estadoJogo = EstadoJogo.VENCEU_RODADA;
            return ResultadoPalpite.VENCEU_A_RODADA;
        }

        if (venceu) {
            this.estadoJogo = EstadoJogo.VENCEU_JOGO;
            return ResultadoPalpite.VENCEU_O_JOGO;
        }

        return ResultadoPalpite.ACERTOU_UMA_LETRA;
    }

    private ResultadoPalpite errouChecaVida() {
        this.vidaAtual -= 1;

        if (getErros() >= getMaxVida()) {
            this.estadoJogo = EstadoJogo.PERDEU;

            return ResultadoPalpite.PERDEU;
        }

        return ResultadoPalpite.ERROU_UMA_LETRA;
    }
}
