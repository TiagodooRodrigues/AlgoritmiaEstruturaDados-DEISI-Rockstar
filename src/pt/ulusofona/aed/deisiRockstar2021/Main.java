package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;
import java.io.*;
public class Main {
    public static HashSet<Song> listadeSons; // estrutura 1 que guarda os sons
    public static  ArrayList<Artista> listadeArtistas; // estrutura que guarda os artistas
    public static HashSet<Song> listadetalhes; // estrutura que guarda os detalhes das musicas
    public static  ArrayList<Song> listadeSons2; // estrutura 2 que guarda sons
    public static  ArrayList<Song> listaDetalhes2; // estrutura 2 que guarda detalhes
    public static ParseInfo infoSongs = new ParseInfo(0,0);
    public static ParseInfo infoSongs1 = new ParseInfo(0,0);
    public static ParseInfo infoSongs2 = new ParseInfo(0,0);

    public static void loadFiles() throws IOException { // funcao para leitura de ficheiros
        String ficheiro_songs = "songs.txt";
        FunctionsLoadfilesSongs.leituraSons(ficheiro_songs); // leitura dos songs

        String ficheiro_song_details = "song_details.txt";
        FunctionsLoadfilesDetails.leituraDetalhes(ficheiro_song_details); // leitura dos detalhes

        String ficheiro_song_artits = "song_artists.txt";
        FunctionsLoadfilesArtist.loadfilesArtistas(ficheiro_song_artits); // leitura dos artistas
    }
    public static ParseInfo getParseInfo(String fileName) { // funcao diz que as linhas que leu ou que ignorou devido aos requisitos pedidos
        if (fileName.equals("song_artists.txt")) {
            return infoSongs;
        }
        if (fileName.equals("songs.txt")) {
            return infoSongs2;
        }
        if (fileName.equals("song_details.txt")) {
            return infoSongs1;
        }
        return null;
    }
    public static ArrayList<Song> getSongs() { // funcao que retorna a lista de musicas em Arraylist
        return listadeSons2;
    }
    public static String getCreativeQuery() { // retorna o nome da query criativa que criamos
        return "GET_MOST_DURATION_YEAR";
    }
    public  static int getTypeOfSecondParameter() { // parametro duracao escolhido
        return 1;
    }
    public static String getVideoUrl(){ // url do video do youtube
        return "https://youtu.be/ghX17j6fC_s";
    }
    public static boolean verificarIDvalido(ArrayList<Song> copia ,String Id) { // funcao ligada a leitura de artitas que verifica se existe uma musica com esse ID
        return FunctionsSongs.binarySearch(copia, Id) != -1; // utiliza  a pequisa binaria para procurar pelo
    }
    public static String execute(String command) {
        String[] input = command.split(" ",2); // divide a string em espacos mas com limite dois
        return SwitchExecute.switchExecuteFuction1(input);
    }
    public static ArrayList<Song> getsonswithoutdetails () {
        /////Copia das listas/////////
        ArrayList<Song> musicas = new ArrayList<>();
        musicas.addAll(listadeSons2);
        ArrayList<Song> detalhes = new ArrayList<>();
        detalhes.addAll(listaDetalhes2);
        //// Ordenar por ID //////////
        musicas.sort(Comparator.comparing((Song som) -> som.iD));
        detalhes.sort(Comparator.comparing((Song som) -> som.iD));
        ///// Arraylist Pedido ///////////
        ArrayList<Song> musicasSemDetahes = new ArrayList<>();

        for ( Song musica : musicas) {
            int index = FunctionsSongs.binarySearch(detalhes,musica.iD);
            if (index == -1) {
                musicasSemDetahes.add(musica);
            }
        }
        return musicasSemDetahes;
    }

   public static ArrayList<Song> getSongsStartingOrEndingWith(char c) {
        ArrayList<Song> musicas = new ArrayList<>();
        ArrayList<Song> musicascomLetra = new ArrayList<>();
        musicas.addAll(listadeSons2);
        for (Song musica : musicas) {
            if ( musica.titulo.charAt(0) == c || musica.titulo.charAt(musica.titulo.length() -1) == c) {
                musicascomLetra.add(musica);
            }
        }
        return musicascomLetra;
    }
    public static ArrayList<Artista> artistasdesseID(String iD) { // Devolve uma lista com artistas que tenham essa musica
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
    public static void main(String[] args) throws IOException {
        loadFiles();
        System.out.println("Welcome to DEISI Rockstar!");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine(); // linha vai ser a primeira palavra
        while (line != null && !line.equals("KTHXBYE")) {
            long start = System.currentTimeMillis(); //
            String result = execute(line);
            long end = System.currentTimeMillis();
            System.out.println(result);
            System.out.println("(took " + (end - start) + " ms)"); // tempo que demorou
            line = in.nextLine();
        }
    }
}