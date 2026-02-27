package pt.ulusofona.aed.deisiRockstar2021;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class FunctionsLoadfilesSongsAluno { // leitura dos nosso ficheiro songs
    public static void leituraSons(String ficheiroSons) {
        try {
            LeituraFilesAlunos.listadeSons = new HashSet<Song>();
            LeituraFilesAlunos.listadeSons2 = new ArrayList<Song>();
            LeituraFilesAlunos.infoSongs2 = new ParseInfo(0,0);
            File ficheiro = new File(ficheiroSons);
            FileInputStream fis1 = new FileInputStream(ficheiro);
            Scanner leitorFicheiro = new Scanner(fis1);
            HashSet<String> guardaIDs = new HashSet<String>();
            while (leitorFicheiro.hasNextLine()) { // enquanto tiver linhas...
                String linha = leitorFicheiro.nextLine();
                String dados[] = linha.split("@");
                if (dados.length == 3) {
                    int EncontrouDuplicadoId = 0;
                    dados[0] = dados[0].trim();
                    String ID = dados[0];
                    if (!guardaIDs.add(ID)) {
                        LeituraFilesAlunos.infoSongs2.numLinhasIgnored++;
                        EncontrouDuplicadoId = 1; // uma musica ja tem esse id
                    }
                    if (EncontrouDuplicadoId == 0){
                        dados[1] = dados[1].trim();
                        String nome = dados[1];
                        dados[2] = dados[2].trim();
                        int ano = Integer.parseInt(dados[2]);
                        // Criar objetos artistas
                        Song sons = new Song(ID, nome, ano);
                        LeituraFilesAlunos.listadeSons.add(sons);
                        LeituraFilesAlunos.listadeSons2.add(sons);
                        LeituraFilesAlunos.infoSongs2.numLinhasOk++;
                        guardaIDs.add(ID);
                    }
                } else {
                    LeituraFilesAlunos.infoSongs2.numLinhasIgnored++;
                }
            }
            leitorFicheiro.close();
        } catch (FileNotFoundException exception) {
            String mensagem = "Erro: o ficheiro " + ficheiroSons + " não foi encontrado. ";
            System.out.println(mensagem);
        }
    }
}
