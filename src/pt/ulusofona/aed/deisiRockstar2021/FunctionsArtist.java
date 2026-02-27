package pt.ulusofona.aed.deisiRockstar2021;

import jdk.swing.interop.SwingInterOpUtils;

import java.net.PortUnreachableException;
import java.net.http.HttpHeaders;
import java.util.*;

public class FunctionsArtist {
    public static int binarySearch(ArrayList<Artista> a, String key) { // pesquisa binaria do nome do artista
        int lo = 0;
        int hi = a.size() - 1;
        while (hi >= lo) {
            int mid = (lo + hi) / 2;
            if (a.get(mid).nome.compareTo(key) < 0) {
                lo = mid + 1;
            } else if (a.get(mid).nome.compareTo(key) > 0) {
                hi = mid - 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }
    public static String get_ArtistForTag(ArrayList<Artista> artistas , String tag) { // obter os artistas desse Tag
        StringBuilder resultado = new StringBuilder(); // string final
        HashSet<String> repetidos = new HashSet<>(); // hashset que guarda repetidos
        ArrayList<Artista> artistasTag = new ArrayList<Artista>();

        if (artistas == null || tag == null) { // caso artistas sejam null
            return "No results";
        }
        for (Artista artista : artistas) { // entre os artistas
            if (repetidos.add(artista.nome)) { // verificar se ja li esse artista
                if (artista.tags.contains(tag)) { // se o artista tiver essa tag
                    artistasTag.add(artista); // adiciono a uma lista de artistas que tenham essa tag
                }
                repetidos.add(artista.nome); // adiciono sempre o artista aos repetidos
            }
        }
        if (artistasTag.isEmpty()) { // caso nao encontre uma artista com essa tag
            return "No results";
        }
        for (int i  = 0 ; i < artistasTag.size() ; i++) { // faco um for entre a lista de artistas que tenham essa tag
            resultado.append(artistasTag.get(i).nome);  // adiciono primeiro o nome
            if ( i != artistasTag.size() - 1) {  // caso haja mais que um artista com essa tag . divido por ;
                resultado.append(";");
            }
        }
        return resultado.toString(); // retorno a string final
    }
    public static String removeTaggs(ArrayList<Artista> artistas , String artistaPedido, ArrayList<String> tags) { // remover tags de um artista
        StringBuilder resultado = new StringBuilder();  // string final
        Artista artista = new Artista(null, null, null); // criacao do artista pedido
        if (artistas == null || artistaPedido == null) { // caso for null
            return "Inexistent artist";
        }
        boolean temArtista = false; // valor booleano para guardar caso nao encontre o artista
        for (Artista nomeDoArtista : artistas) {
            if (nomeDoArtista.nome.equals(artistaPedido)) {
                temArtista = true;  // guardar se encontramos o nome
                artista = nomeDoArtista;// guardar o artista encontrado
                for (String tag : tags) { // para cada artista remove as tags respetivas
                    if (nomeDoArtista.tags.contains(tag)) { // remove apenas a tags pedidas
                        nomeDoArtista.tags.remove(tag); // adiciono a tagg
                    }
                }
            }
        }
        if (!temArtista) { // o artista nao existe
            return "Inexistent artist";
        }
        resultado.append(artista.nome); // adiciono o nome
        resultado.append(" | ");
        if (artista.tags.size() == 0) { // caso o artistas fique sem tags
            resultado.append("No tags");
        } else {
            for (int i = 0; i < artista.tags.size(); i++) { // ainda tem tags
                resultado.append(artista.tags.get(i));
                if (i != artista.tags.size() - 1) {
                    resultado.append(",");
                }
            }
        }
        return resultado.toString();
    }
    public static String tagsAdd(ArrayList<Artista> artistas, String artistaPedido , ArrayList<String> tags) { // adicionar tags
        StringBuilder resultado = new StringBuilder(); // string final
        Artista artista = new Artista(null,null,null);
        if (artistas == null || artistaPedido == null) { // caso for null
            return "Inexistent artist";
        }
        boolean temArtista = false;
        for (Artista nomeDoArtista : artistas) {
            if (nomeDoArtista.nome.equals(artistaPedido)) {
                temArtista = true;  // guardar se encontramos o nome
                artista = nomeDoArtista; // guardar o artista encontrado
                for (String tag : tags) {
                    if (!(nomeDoArtista.tags.contains(tag))){ // se nao tiver essa tag
                        nomeDoArtista.tags.add(tag); // adiciono
                    }
                }
            }
        }
        if (!temArtista) { // o artista nao existe
            return "Inexistent artist";
        }
            resultado.append(artista.nome); // adiciono o nome da musica
            resultado.append(" | "); // divide o artista com as suas tags
            for (int i = 0; i < artista.tags.size(); i++) {
                resultado.append(artista.tags.get(i)); // adiciona as tags
                if ( i != artista.tags.size() - 1) { // caso nao for a ultima tag , usa a virgula
                    resultado.append(",");
                }
            }
        return resultado.toString();
    }

    public static String getArtistsOneSong(int anoInicio ,int anoFim, ArrayList<Song> listadeSons, ArrayList<Artista> listadeArtistas) {
        StringBuilder resultado = new StringBuilder();
        if (anoInicio >= anoFim) {  //ano inicio superior ao fim da erro
            return "Invalid period";
        }
        ArrayList<Song> musicasAnos = new ArrayList<>(); // lista de musicas que estejam nesses anos

        for (Song som : listadeSons) { // reduzir a lista para musicas desses anos
            if (som.anoLancamento >= anoInicio && som.anoLancamento <= anoFim) { // filtrar para musicas
                musicasAnos.add(som);
            }
        }
        ArrayList<Song> musicasOrdenadas = new ArrayList<Song>();
        musicasOrdenadas.addAll(musicasAnos);
        musicasOrdenadas.sort(Comparator.comparing((Song som1) -> som1.iD)); // musicas ordenadas pelo nesse periodo de anos

        ArrayList<Artista> artistasOrdenado = new ArrayList<>();
        artistasOrdenado.addAll(listadeArtistas);
        artistasOrdenado.sort(Comparator.comparing((Artista artista) -> artista.nome)); // artistas ordenados por ordem algabetica

        artistasOrdenado.removeIf(artista -> FunctionsSongs.binarySearch(musicasOrdenadas, artista.iD) == -1);// remover artistas sem musicas desses anos

        HashSet<String> duplicados = new HashSet<>(); // verificar repetidos
        ArrayList<Artista> artistasCom1Som = new ArrayList<>(); // lista de artistas com 1 som

        for (Artista artista : artistasOrdenado) { // entre os artistas com musicas desses anos
           if (duplicados.add(artista.nome)) { // se nao for duplicado
                artistasCom1Som.add(artista); // adiciona o artista a lista de artisras com 1 som apenas
            } else {
               duplicados.add(artista.nome); // se for duplicado
               int index = binarySearch(artistasCom1Som,artista.nome);// procurar pelo duplicado
               if (index != -1) {
                   artistasCom1Som.remove(index); // remover o artista que continha duplicacao
               }
            }
        }
       artistasCom1Som.sort(Comparator.comparing((Artista artista) -> artista.nome)); // ordernar os artistas com 1 som pelo nome

        for (Artista artista : artistasCom1Som ){
            int index = FunctionsSongs.binarySearch(musicasOrdenadas,artista.iD); // obter o posicao a partir id da musica
            // encontrou um artista com uma musica desses anos
                resultado.append(artista.nome); // escreve nome arista
                resultado.append(" | ");
                resultado.append(musicasOrdenadas.get(index).titulo); // escreve a musica
                resultado.append(" | ");
                resultado.append(musicasOrdenadas.get(index).anoLancamento); // escreve ano lancamento da musica
                resultado.append("\n");
        }
        if (!resultado.toString().equals("")) { // if para retirar a linha vazia do final
            int last = resultado.lastIndexOf("\n"); // encontra a posicao do \n
            if (last == resultado.length() - 1) { // caso a ultima linha seja \n
                resultado.delete(last, resultado.length());  // remover \n
            }
        }
        return resultado.toString(); // retorna uma string
    }
    public static String getUniqueTags(ArrayList<Artista> artistas) { // funcao que indica as tags em artistas
        HashMap<String,Integer> uniqueTags = new HashMap<>(); // mapa com nome das tags e numero de ocorrencias da tags
        HashSet<String> repetidos = new HashSet<>();
        StringBuilder resultado = new StringBuilder(); // string final
        for (Artista artista : artistas) {
            if (repetidos.add(artista.nome)) { // caso nao tenha lido esse artista ainda
                for (int i = 0; i < artista.tags.size(); i++) { // leio a lista de tags
                    String tag = artista.tags.get(i);
                    if (uniqueTags.containsKey(tag)) { // existe ja a tag
                        uniqueTags.put(tag, uniqueTags.get(tag) + 1); // aumento 1 no numero de ocorrencias
                    } else {
                        uniqueTags.put(tag, 1); // adiciono a tag apenas com 1 ocorrencia apenas
                    }
                }
                repetidos.add(artista.nome); // adiciono o nome para evitar duplicados
            }
        }
        ArrayList<String> listaTags = new ArrayList<>();
        listaTags.addAll(uniqueTags.keySet()); // copio todas as tags numa lista
        Collections.sort(listaTags,Comparator.comparingInt(uniqueTags::get)); // ordernar as tags pelo numero de ocorrencias crescente

        for (String key : listaTags){ // para cada tag
            resultado.append(key); // adiciono o nome
            resultado.append(" ");
            resultado.append(uniqueTags.get(key)); // adiciono o seu valore de ocorrencias
            resultado.append("\n");
        }
        if (!resultado.toString().equals("")) { // apagar a linha vazia
            int last = resultado.lastIndexOf("\n"); // encontra a posicao do \n
            if (last == resultado.length() - 1) { // caso a ultima linha seja \n
                resultado.delete(last, resultado.length());  // remover \n
            }
        }
        if (resultado.toString().equals("")) { // caso nao haja tags
            return "No results";
        }
        return resultado.toString();
    }
    public static String getUniqueTagsBetween(int ano1 , int ano2 , ArrayList<Artista> artistas, HashSet<Song> sons) { // retorna as tags desses anos
        HashMap<String,Integer> uniqueTags = new HashMap<>(); // mapa como a funcao anterior
        HashSet<String> repetidos = new HashSet<>();
        StringBuilder resultado = new StringBuilder(); // string final
        ArrayList<Song> copiaMusicas = new ArrayList<>(); // copia das musicas

        for (Song som : sons) {
            if (som.anoLancamento >= ano1 && som.anoLancamento <= ano2) { // se estiver entre esses anos
                copiaMusicas.add(som);
            }
        }
        Collections.sort(copiaMusicas,Comparator.comparing((Song som1) -> som1.iD)); //ordernado por IDs as musicas
        adicionarTagsHash(artistas,copiaMusicas,repetidos,uniqueTags); // funcao que adiciona as tags desses anos
        ArrayList<String> listaTags = new ArrayList<>();
        listaTags.addAll(uniqueTags.keySet());
        Collections.sort(listaTags,Comparator.comparingInt(uniqueTags::get).reversed()); // ordernar as tags pelo numero de ocorrencias em decrescente

        for (String key : listaTags){ // para cada tag
            resultado.append(key);
            resultado.append(" ");
            resultado.append(uniqueTags.get(key));
            resultado.append("\n");
        }
        if (!resultado.toString().equals("")) { // limpa a ultima linha
            int last = resultado.lastIndexOf("\n"); // encontra a posicao do \n
            if (last == resultado.length() - 1) { // caso a ultima linha seja \n
                resultado.delete(last, resultado.length());  // remover \n
            }
        }
        if (resultado.toString().equals("")) { // nao encontrou nenhuma tag desses anos
            return "No results";
        }
        return resultado.toString();
    }
    public static void adicionarTagsHash(ArrayList<Artista> artistas, ArrayList<Song> copiaMusicas,HashSet<String> repetidos, HashMap<String,Integer> uniqueTags) {
        for (Artista artista : artistas) {
            int index = FunctionsSongs.binarySearch(copiaMusicas, artista.iD); // procura pelo id do artista na musicas ,ou seja, artistas que tenha musicas nesses anos
            if (index != -1) { // caso encontre faz o mesmo que uniquetags
                if (repetidos.add(artista.nome)) {
                    for (int i = 0; i < artista.tags.size(); i++) {
                        String tag = artista.tags.get(i);
                        if (uniqueTags.containsKey(tag)) { // existe ja a tag
                            uniqueTags.put(tag, uniqueTags.get(tag) + 1);
                        } else {
                            uniqueTags.put(tag, 1);
                        }
                    }
                    repetidos.add(artista.nome);
                }
            }
        }
    }
}
