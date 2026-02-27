package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;

public class Artista { // Artista
        String iD; // id da musica
        String nome; // nome do artista
        ArrayList<String> tags = new ArrayList<>(); // lista de tipos de musicas do artista

        public Artista(String ID, String nome) { // construtor para o loadfiles (leitura de artistas.txt)
            this.iD = ID;
            this.nome = nome;
        }
    public Artista(String iD, String nome, ArrayList<String> tags) { // construtor para o ADD tags
        this.iD = iD;
        this.nome = nome;
        this.tags = tags;
    }
}
