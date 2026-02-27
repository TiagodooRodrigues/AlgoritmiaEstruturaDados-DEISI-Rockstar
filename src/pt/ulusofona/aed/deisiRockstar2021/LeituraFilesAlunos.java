package pt.ulusofona.aed.deisiRockstar2021;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class LeituraFilesAlunos { // main principal dos nossos ficheiros
    public static HashSet<Song> listadeSons;
    public static ArrayList<Artista> listadeArtistas;
    public static HashSet<Song> listadetalhes;
    public static  ArrayList<Song> listadeSons2;
    public static  ArrayList<Song> listaDetalhes2;
    public static ParseInfo infoSongs = new ParseInfo(0,0);
    public static ParseInfo infoSongs1 = new ParseInfo(0,0);
    public static ParseInfo infoSongs2 = new ParseInfo(0,0);

    public static void loadFiles() throws IOException {
        String ficheiro_songs = "test-files/musicas.txt";
        FunctionsLoadfilesSongsAluno.leituraSons(ficheiro_songs);
        ///////// FIM de Leitura de Songs/////////////////////////
        String ficheiro_song_details = "test-files/detalhes.txt";
        FunctionsLoadfilesDetailsAluno.leituraDetalhes(ficheiro_song_details);
        //////// fim do ficheiro detalhes.txt///////////////////////
        String ficheiro_song_artits = "test-files/cantores.txt";
        FunctionsLoadfilesArtistAluno.loadfilesArtistas(ficheiro_song_artits);
    }
    public static String execute(String command) {
        String[] input = command.split(" ",2); // divide a string em espacos mas com limite dois
        return SwitchExecuteAluno.switch1(input);
    }
    public static void main(String[] args) throws IOException {
        loadFiles();
        System.out.println(execute("GET_MOST_DANCEABLE 2017 2020 3"));
    }
}
