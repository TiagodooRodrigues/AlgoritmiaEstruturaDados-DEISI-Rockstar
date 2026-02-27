package pt.ulusofona.aed.deisiRockstar2021;
import java.security.cert.CertificateParsingException;
import java.util.*;
public class FunctionsSongs {
    public static int binarySearch(ArrayList<Song> a, String key) { // pesquisa binaria para ids entre musicas e artistas
        int lo = 0;
        int hi = a.size() - 1;
        while (hi >= lo) {
            int mid = (lo + hi) / 2;
            if (a.get(mid).iD.compareTo(key) < 0) {
                lo = mid + 1;
            } else if (a.get(mid).iD.compareTo(key) > 0) {
                hi = mid - 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }
    public static int minutos(int duracao) {
        return (duracao / 1000) / 60;
    }

    public static int segundos(int duracao) {
        return (duracao / 1000) % 60;
    }

   public static String getMostDurationThatYear(HashSet<Song> sons, HashSet<Song> detalhes, int ano, int duracao) { // nossa query

       ArrayList<Song> listaSom = new ArrayList<>();
       ArrayList<Song> listaDetalhe = new ArrayList<>();
       listaDetalhe.addAll(detalhes); //copia detalhes
       StringBuilder listafinal = new StringBuilder();

       if (sons == null) { // caso seja null
           return null;
       }

       for (Song musicaAno : sons){ // filtrar para musicas que tenham esses anos
           if (musicaAno.anoLancamento == ano) {
               listaSom.add(musicaAno);
           }
       }

       Collections.sort(listaDetalhe,Comparator.comparing((Song som1) -> som1.iD)); //ordernado por IDs os detalhes

       for (int i = 0 ; i < listaSom.size() ; i ++) {
           int index = binarySearch(listaDetalhe,listaSom.get(i).iD); // pesquisa binaria de IDs
           if (index != -1) { // caso encontre
               listaSom.get(i).duracaoDoTema = listaDetalhe.get(index).duracaoDoTema; // adiciono a duracao associada a musica
           }
       }

       Collections.sort(listaSom,Comparator.comparingDouble((Song som2) -> som2.duracaoDoTema).reversed()); //ordenar sons pela duracao

        for (int i = 0; i < listaSom.size(); i++) {
            if (listaSom.get(i).duracaoDoTema >= duracao) {
                listafinal.append(listaSom.get(i).titulo);
                listafinal.append(" : ");
                listafinal.append(listaSom.get(i).anoLancamento);
                listafinal.append(" : ");
                listafinal.append(minutos(listaSom.get(i).duracaoDoTema));
                listafinal.append(":");
                listafinal.append(segundos(listaSom.get(i).duracaoDoTema));
                listafinal.append("\n");
            }
        }
        if (!listafinal.toString().equals("")) {
            int last = listafinal.lastIndexOf("\n"); // encontra a posicao do \n
            if (last == listafinal.length() - 1) { // caso a ultima linha seja \n
                listafinal.delete(last, listafinal.length());  // remover \n
            }
        }
        return listafinal.toString();
    }
    public static int countSongsYear(ArrayList<Song> sons, int ano ) {
        int contador = 0;
        if (sons == null || sons.size() == 0) {
            return contador;
        }
        for (int i = 0; i < sons.size(); i++) {
            if (sons.get(i).anoLancamento == ano) {
                contador++;
            }
        }
        return contador;
    }
    public static int countDuplicateSongsYear(HashSet<Song> sons, int ano) { // retorna musicas duplicadas desse ano
        HashSet<String> verificadorRepetido = new HashSet<String>(); // guardar os duplicados
        int count = 0;
        for (Song som : sons) { // percorrer as musicas
            if ((som.anoLancamento == ano) && verificadorRepetido.contains(som.titulo)){ // tem o mesmo ano e e repetido
                count++; // adiciona o titulo repetido e ignora mais que um repetido
            } else {
                if (som.anoLancamento == ano) {
                    verificadorRepetido.add(som.titulo); // nao e repetido
                }
            }
        }
        return count;
    }
    public static String getMostDanceable(HashSet<Song> sons, HashSet<Song> detalhes, int anoInicio, int anoFim, int nMusicas) { // musicas mais dancaveis
        ArrayList<Song> listaSom = new ArrayList<>(); // copia de musicas
        ArrayList<Song> listaDetalhe = new ArrayList<>(); // copia de detalhes
        listaDetalhe.addAll(detalhes); //copia detalhes
        StringBuilder listafinal = new StringBuilder(); // string final

        if (sons == null) {
            return null;
        }

        for (Song musicaAno : sons){ // as musicas que estao nesses anos
            if (musicaAno.anoLancamento >= anoInicio && musicaAno.anoLancamento <= anoFim) {
                listaSom.add(musicaAno);
            }
        }
        Collections.sort(listaDetalhe,Comparator.comparing((Song som1) -> som1.iD)); //ordernado por IDs os detalhes

        for (int i = 0 ; i < listaSom.size() ; i ++) {
            int index = binarySearch(listaDetalhe,listaSom.get(i).iD); // pesquisa binaria de ID da musica nos detalhes
            if (index != -1) { // caso encontre
                listaSom.get(i).grauDancabilidade = listaDetalhe.get(index).grauDancabilidade; // adiciona o grau de dancabilidade a musica
            }
        }
        Collections.sort(listaSom,Comparator.comparingDouble((Song som2) -> som2.grauDancabilidade).reversed()); //ordenar sons por grau dancabilidade em descrescente

        for (int i = 0; i < nMusicas; i++) { // para cada musica
                listafinal.append(listaSom.get(i).titulo); // adiciono o nome
                listafinal.append(" : ");
                listafinal.append(listaSom.get(i).anoLancamento); // adiciono o ano de lancamento
                listafinal.append(" : ");
                listafinal.append(listaSom.get(i).grauDancabilidade); // adiciono o grau de dancabilidade
                if (i != nMusicas - 1) {
                    listafinal.append("\n");
                }
        }
        return listafinal.toString();
    }
}