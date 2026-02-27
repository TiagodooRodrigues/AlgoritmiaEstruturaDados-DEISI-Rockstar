package pt.ulusofona.aed.deisiRockstar2021;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class FunctionsLoadfilesArtist {
    public static HashSet<String> guardaIDs; // guarda os ids
    public static HashSet<String> nomesRepetidos; // guarda os repetidos
    public static void loadfilesArtistas(String ficheiroArtistas) {
        try {
            Main.listadeArtistas = new ArrayList<Artista>();
            Main.infoSongs = new ParseInfo(0, 0);
            ArrayList<Song> OrdenadoPB = new ArrayList<>(); //copiar songs de forma ordenanda
            OrdenadoPB.addAll(Main.listadeSons2);
            OrdenadoPB.sort(Comparator.comparing((Song som1) -> som1.iD)); //ordernado por IDs as musicas
            File ficheiro = new File(ficheiroArtistas);
            FileInputStream fis1 = new FileInputStream(ficheiro);
            Scanner leitorFicheiro = new Scanner(fis1);
            nomesRepetidos = new HashSet<>();
            guardaIDs = new HashSet<>();
            while (leitorFicheiro.hasNextLine()) { // enquanto tiver linhas...
                leituraFicheiroArtistas(leitorFicheiro, OrdenadoPB); // funcao que le as linhas dos artistas.txt
            }
            leitorFicheiro.close(); // fecho a leitura
        } catch (FileNotFoundException exception) {
            String mensagem = "Erro: o ficheiro " + ficheiroArtistas + " não foi encontrado. ";
            System.out.println(mensagem);
        }
    }

    public static void leituraFicheiroArtistas(Scanner leitorFicheiro, ArrayList<Song> OrdenadoPB) {
        String linha = leitorFicheiro.nextLine(); // Ler a linha do ficheiro
        String dados[] = linha.split("@");
        int temArtistaVazio = 0;
        if (dados.length == 2) { // tem de ter apenas 2 @
            String ID = dados[0].trim(); // tiro os espacos em branco do ID
            if (ID.equals("") || !Main.verificarIDvalido(OrdenadoPB, ID)) { // se nao tiver ID , ou ID nao tiver nas musicas
                Main.infoSongs.numLinhasIgnored++; // linha ignorada
            } else {
                adicionarArtista(dados,ID,temArtistaVazio); // funcao que adiciona o artista a lista de artistas principal
            }
        } else {
            Main.infoSongs.numLinhasIgnored++; // nao correspondeu ao tamanho certo da linha
        }
    }
    public static void adicionarArtista(String[] dados , String ID , int temArtistaVazio) {
        if (guardaIDs.add(ID)) { // verificar se ID do artista sera repetido
            if (dados[1].contains(",")) { // caso seja mais que um artista
                String[] nomes = dados[1].split(","); //dividir por ,
                for (String nome : nomes) {
                    nome = nome.replace("[", "").replace("]", "").replace("'", "").replace("\"", "").trim(); //retirar peliculas e '
                    if (nome.equals("")) { // verifico se for nome do artista for vazio
                        temArtistaVazio = 1;
                    }
                }
                if (temArtistaVazio == 1) {
                    Main.infoSongs.numLinhasIgnored++; // encontrou artista vazio por isso ignora
                } else {
                    for (String nome : nomes) {
                        nome = replaceArtist(nome); // chamo a funcoa que retira as [ , '  dos nomes
                        if (nomesRepetidos.add(nome)) { // caso seja um nome de uma artista repetido com o mesmo ID
                            Artista artista = new Artista(ID, nome);
                            Main.listadeArtistas.add(artista);
                            nomesRepetidos.add(nome);
                        }
                    }
                    nomesRepetidos.clear(); // apago tudo e comeca tudo de novo ( apenas verifico se tem artista com o mesmo nome na mesma linha)
                    Main.infoSongs.numLinhasOk++; // leu a linha toda
                }
            } else { // tem apenas uma artista
                String nome = dados[1];
                nome = replaceArtist(nome); // chamo a funcoa que retira as [ , '  dos nomes
                if (nome.equals("")) {
                    Main.infoSongs.numLinhasIgnored++; // encontrou artista vazio por isso ignora
                } else {
                    // Criar objeto artistas
                    Artista artista = new Artista(ID, nome);
                    Main.listadeArtistas.add(artista);
                    Main.infoSongs.numLinhasOk++;
                }
            }
            guardaIDs.add(ID);
        } else {
            Main.infoSongs.numLinhasOk++;
        }
    }
    public static String replaceArtist(String nome) { // retira a porcaria toda dos nomes
        nome = nome.trim();
        nome = nome.replace("\"['","");
        nome = nome.replace("']\"","");
        nome = nome.replace("'[\"","");
        nome = nome.replace("\"]'","");
        nome = nome.replace("['","");
        nome = nome.replace("']","");
        nome = nome.replace("'[","[");
        nome = nome.replace("]'","]");
        nome = nome.replace("\"[","");
        nome = nome.replace("]\"","");
        nome = nome.replace("\"\"'","");
        nome = nome.replace("\"'","");
        nome = nome.replace("'\"\"","");
        nome = nome.replace("'\"","");
        nome = nome.replace("\"\"\"","\"");
        nome = nome.replace("\"\"","");
        if (nome.startsWith("\'")) {
            nome = nome.substring(1);
        }
        if (nome.endsWith("\'")) {
            nome = nome.substring(0,nome.length() -1);
        }
        return nome;
    }
}
