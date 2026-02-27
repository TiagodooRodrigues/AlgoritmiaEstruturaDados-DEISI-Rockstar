package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;

public class Song {
    String iD;
    String titulo;
    ArrayList<Artista> artistas = Main.listadeArtistas;
    int anoLancamento;
    int duracaoDoTema;
    int letraExplicita;
    double popularidade;
    double grauDancabilidade;
    double grauVivacidade;
    double volumeMedio;

    public Song(String titulo) {
        this.titulo = titulo;
    }

    public Song(String ID, String titulo, int anoLancamento) { // Songs.txt
        this.iD = ID;
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
    }
    public Song (String ID, int duracaoDoTema, int letraExplicita, double popularidade, double grauDancabilidade, double grauVivacidade,double volumeMedio) {
        this.iD = ID;
        this.duracaoDoTema = duracaoDoTema;
        this.letraExplicita = letraExplicita;
        this.popularidade = popularidade;
        this.grauDancabilidade = grauDancabilidade;
        this.grauVivacidade = grauVivacidade;
        this.volumeMedio = volumeMedio;
    }
    public Song(String ID, String titulo, ArrayList<Artista> artistas, int anoLancamento, int duracaoDoTema, int letraExplicita, double popularidade, double grauDancabilidade, double grauVivacidade, double volumeMedio) {
        this.iD = ID;
        this.titulo = titulo;
        this.artistas = artistas;
        this.anoLancamento = anoLancamento;
        this.duracaoDoTema = duracaoDoTema;
        this.letraExplicita = letraExplicita;
        this.popularidade = popularidade;
        this.grauDancabilidade = grauDancabilidade;
        this.grauVivacidade = grauVivacidade;
        this.volumeMedio = volumeMedio;
    }
    public ArrayList<Integer> numeroDeTemas() { // Calcula o numero de temas de cada artista do ID pedido
        ArrayList<Integer> numerotemas = new ArrayList<Integer>();
        int contador = 0;
        for (Artista artista : artistasdesseID()) { // entre os artistas do id da musica
            for (Artista artista2 : Main.listadeArtistas) {
                if (artista.nome.equals(artista2.nome)) { // se tiver outra musica com o mesmo artista
                    contador++; // conta um
                }
            }
            numerotemas.add(contador); // adiciona o contador
            contador = 0;
        }
        return numerotemas;
    }
    public ArrayList<Artista> artistasdesseID() { // Devolve uma lista com artistas que tenham essa musica
        ArrayList<Artista> lista = new ArrayList<Artista>();
        if ( Main.listadeArtistas != null ) { // diferente de null
            for (Artista artista : Main.listadeArtistas) { // procura os artistas dentro da lista de artistas
                if (artista.iD.equals(iD)) { // se o artista tiver essa musica
                    lista.add(artista); // adiciona o artista a lista
                }
            }
        }
        return lista;
    }
    public String toString() {
        double popularidadeCalculada = 0.0;
        double volumeMedio = 0.0;
        int minutos = 0;
        int segundos = 0;
        StringBuilder todosOsArtistas = new StringBuilder(); // string final
        if ( artistasdesseID() != null ) {
            for (int i = 0 ; i < artistasdesseID().size() ; i ++) { // escrever os artistas
                todosOsArtistas.append(artistasdesseID().get(i).nome);
                if (i != artistasdesseID().size() - 1){ // caso seja mais que artista que tenha essa musica deve ser separado por uma /
                    todosOsArtistas.append(" / ");
                }
            }
            todosOsArtistas.append(" | ");
            todosOsArtistas.append("(");
            for (int i = 0 ; i < numeroDeTemas().size() ; i ++) { // adicionar o numero de temas desse mesmo artista
                todosOsArtistas.append(numeroDeTemas().get(i));
                if (i != artistasdesseID().size() - 1) { // se houver mais que um artista separa se por /
                    todosOsArtistas.append(" / ");
                }
            }
            todosOsArtistas.append(")");
        }
        for (Song musica : Main.listaDetalhes2) {
            if (musica.iD.equals(iD)) { // encontro a uma musica pedida
                popularidadeCalculada = musica.popularidade; // adiciona a sua popularidade
                volumeMedio = musica.volumeMedio;
                minutos = (musica.duracaoDoTema / 1000) / 60;  // meto em minutos
                segundos = (musica.duracaoDoTema / 1000) % 60; // meto em segundos
            }
        }
        return iD + " | " + titulo + " | " + anoLancamento + " | " + minutos + ":"+ segundos + " | "+ popularidadeCalculada + " | " + todosOsArtistas;
    }
}
