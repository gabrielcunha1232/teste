package service;

import model.Palavra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LeitorDeArquivoPalavras {

    public static List<Palavra> ler(String caminho) throws IOException {
        return Files.readAllLines(Path.of(caminho))
                .stream()
                .filter(linha -> !linha.trim().isEmpty())
                .filter(linha -> linha.trim().contains(";"))
                .map(linha -> {
                    String[] partes = linha.split(";");

                    return new Palavra(partes[0].trim(), partes[1].trim());
                }).toList();
    }
}
