package pt.ulusofona.aed.deisiRockstar2021;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class FunctionsLoadfilesArtistAluno { // leitura dos nosso ficheiro artist
    public static void loadfilesArtistas(String ficheiroArtistas) {
        try {
            LeituraFilesAlunos.listadeArtistas= new ArrayList<Artista>();
            LeituraFilesAlunos.infoSongs = new ParseInfo(0,0);
            ArrayList<Song> OrdenadoPB = new ArrayList<>(); //copiar songs de forma ordenanda
            OrdenadoPB.addAll(LeituraFilesAlunos.listadeSons2);
            OrdenadoPB.sort(Comparator.comparing((Song som1) -> som1.iD)); //ordernado por IDs os detalhes
            File ficheiro = new File(ficheiroArtistas);
            FileInputStream fis1 = new FileInputStream(ficheiro);
            Scanner leitorFicheiro = new Scanner(fis1);
            while (leitorFicheiro.hasNextLine()) { // enquanto tiver linhas...
                leituraPrincipal(leitorFicheiro,OrdenadoPB);
            }
            leitorFicheiro.close();
        } catch (FileNotFoundException exception) {
            String mensagem = "Erro: o ficheiro " + ficheiroArtistas + " não foi encontrado. ";
            System.out.println(mensagem);
        }
    }
    public static void leituraPrincipal(Scanner leitorFicheiro,ArrayList<Song> OrdenadoPB) {
        String linha = leitorFicheiro.nextLine(); // Ler a linha do ficheiro
        String dados[] = linha.split("@");
        int temArtistaVazio = 0;
        if (dados.length == 2) {
            String ID = dados[0].trim();
            if (ID.equals("") || !Main.verificarIDvalido(OrdenadoPB,ID)) { // se nao tiver id
                LeituraFilesAlunos.infoSongs.numLinhasIgnored++;
            } else {
                if (dados[1].contains(",")) { // caso seja mais que um artista
                    String[] nomes = dados[1].split(","); //dividir por ,
                    for (String nome : nomes) {
                        nome = nome.replace("[", "").replace("]", "").replace("'", "").replace("\"", "").trim(); //retirar peliculas e '
                        if (nome.equals("")) {
                            temArtistaVazio = 1;
                        }
                    }
                    if (temArtistaVazio == 1) {
                        LeituraFilesAlunos.infoSongs.numLinhasIgnored++; // encontrou artista vazio por isso ignora
                    } else {
                        for (String nome : nomes) {
                            nome = nome.replace("[", "").replace("]", "").replace("'", "").replace("\"", "").trim(); //retirar peliculas e '
                            Artista artista = new Artista(ID, nome);
                            LeituraFilesAlunos.listadeArtistas.add(artista);
                        }
                        LeituraFilesAlunos.infoSongs.numLinhasOk++; // leu a linha toda
                    }
                } else { // tem apenas uma artista
                    String nome = dados[1].replace("[", "").replace("]", "").replace("'", "").replace("\"", "").trim(); // retirar peliculas e []
                    if (nome.equals("")) {
                        LeituraFilesAlunos.infoSongs.numLinhasIgnored++; // encontrou artista vazio por isso ignora
                    } else {
                        // Criar objeto artistas
                        Artista artista = new Artista(ID, nome);
                        LeituraFilesAlunos.listadeArtistas.add(artista);
                        LeituraFilesAlunos.infoSongs.numLinhasOk++;
                    }
                }
            }
        } else {
            LeituraFilesAlunos.infoSongs.numLinhasIgnored++;
        }
    }
}
