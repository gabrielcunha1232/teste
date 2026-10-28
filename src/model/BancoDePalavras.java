package model;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BancoDePalavras {
    private List<Palavra> banco;
    private Set<String> palavrasUsadas;
    private Random random;

    public BancoDePalavras(List<Palavra> banco) {
        if (banco == null || banco.isEmpty()) {
            throw new IllegalArgumentException("o banco de palavras nao pode estar vazio");
        }

        this.banco = List.copyOf(banco);
        this.palavrasUsadas = new HashSet<>();
        this.random = new Random();
    }

    public Palavra sortearPalavra() {
        if (palavrasUsadas.size() == banco.size()) {
            throw new IllegalStateException("Todas as palavras do banco já foram jogadas");
        }

        List<Palavra> palavrasDisponiveis = banco.stream()
                        .filter(p -> !palavrasUsadas.contains(p.getPalavra()))
                        .toList();

        Palavra palavraSorteada = palavrasDisponiveis.get(random.nextInt(palavrasDisponiveis.size()));

        palavrasUsadas.add(palavraSorteada.getPalavra());
        return palavraSorteada;
    }

    public void resetar() {
        palavrasUsadas.clear();
    }

    public boolean aindaTemPalavra() {
        return palavrasUsadas.size() < banco.size();
    }
}
