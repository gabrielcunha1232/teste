package model;


import java.util.HashSet;
import java.util.Set;

public class Palavra {

    private String palavra;
    private String tema;
    private Set<Integer> indicesRevelados;

    public Palavra(String palavra, String tema) {
        if (palavra == null || tema == null || palavra.trim().isEmpty() || tema.trim().isEmpty()) {
            throw new IllegalArgumentException("O jogo da Forca não inicia enquanto a palavra ou o tema forem vazios");
        }

        this.palavra = palavra.toUpperCase();
        this.tema = tema;
        this.indicesRevelados = new HashSet<>();

        for (int i = 0; i < this.palavra.length(); i++) {
            if (Character.isWhitespace(this.palavra.charAt(i))) {
                indicesRevelados.add(i);
            }
        }
    }

    public String getPalavra() {
        return palavra;
    }

    public String getTema() {
        return tema;
    }

    private Set<Integer> getIndicesRevelados() {
        return indicesRevelados;
    }

    public boolean letraExiste(String letra) {
        if(letra == null || letra.length() != 1) {
            return false;
        }

        return this.getPalavra().contains(letra.toUpperCase());
    }

    public boolean venceu() {
        return this.getIndicesRevelados().size() == this.palavra.length();
    }

    public void revelar(String letra) {
        if (letra == null || letra.length() != 1) {
            return;
        }

        String palavraAtual = this.getPalavra();
        String letraDigitada = letra.toUpperCase();

        for(int i = 0; i < palavraAtual.length(); i++) {
            if (String.valueOf(palavraAtual.charAt(i)).equals(letraDigitada)) {
                this.indicesRevelados.add(i);
            }
        }
    }

    public String getDisplay() {
        StringBuilder sb = new StringBuilder();
        String palavraAtual = getPalavra();

        for(int i = 0; i < getPalavra().length(); i++) {
            if (this.indicesRevelados.contains() ){
                sb.append(palavraAtual.charAt(i))
            }
        }
    }
}
