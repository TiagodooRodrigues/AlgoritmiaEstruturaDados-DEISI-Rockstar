package pt.ulusofona.aed.deisiRockstar2021;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class FunctionsLoadfilesDetailsAluno {
    public static void leituraDetalhes(String ficheiroDetails) { // leitura dos nosso ficheiro detalhes
        try {
            LeituraFilesAlunos.listadetalhes = new HashSet<Song>();
            LeituraFilesAlunos.listaDetalhes2 = new ArrayList<Song>();
            LeituraFilesAlunos.infoSongs1 = new ParseInfo(0,0);
            File ficheiro = new File(ficheiroDetails);
            FileInputStream fis1 = new FileInputStream(ficheiro);
            Scanner leitorFicheiro = new Scanner(fis1);
            HashSet<String> guardaIDs2 = new HashSet<String>();
            while (leitorFicheiro.hasNextLine()) { // enquanto tiver linhas...
                String linha = leitorFicheiro.nextLine();// Ler a linha do ficheiro
                String dados[] = linha.split("@");
                if (dados.length == 7) {
                    int EncontrouDuplicadoId2 = 0;
                    dados[0] = dados[0].trim();
                    String ID = dados[0]; // ID tema musical
                    if (ID.equals("")) { // id inexistente
                        LeituraFilesAlunos.infoSongs1.numLinhasIgnored++;
                    } else {
                        if (!guardaIDs2.add(ID)) {
                            LeituraFilesAlunos.infoSongs1.numLinhasIgnored++;
                            EncontrouDuplicadoId2 = 1; // uma musica ja tem esse id
                        }
                        if (EncontrouDuplicadoId2 == 0) {
                            dados[1] = dados[1].trim();
                            int duracao = Integer.parseInt(dados[1]); // duracao
                            dados[2] = dados[2].trim();
                            int letra = Integer.parseInt(dados[2]);  // letra
                            dados[3] = dados[3].trim();
                            double popularidade = Double.parseDouble(dados[3]); // popularidade
                            dados[4] = dados[4].trim();
                            double dancabilidade = Double.parseDouble(dados[4]); // dancabilidade
                            dados[5] = dados[5].trim();
                            double vivacidade = Double.parseDouble(dados[5]);  // dancabilidade
                            dados[6] = dados[6].trim();
                            double volumeMedio = Double.parseDouble(dados[6]); // volume medio
                            // Criar objeto artistas
                            Song detalhes = new Song(ID, duracao, letra, popularidade, dancabilidade, vivacidade, volumeMedio);
                            LeituraFilesAlunos.listadetalhes.add(detalhes);
                            LeituraFilesAlunos.listaDetalhes2.add(detalhes);
                            guardaIDs2.add(ID);
                            LeituraFilesAlunos.infoSongs1.numLinhasOk++;
                        }
                    }
                } else {
                    LeituraFilesAlunos.infoSongs1.numLinhasIgnored++;
                }
            }
            leitorFicheiro.close();
        } catch (FileNotFoundException exception) {
            String mensagem = "Erro: o ficheiro " + ficheiroDetails + " não foi encontrado. ";
            System.out.println(mensagem);
        }
    }
}
