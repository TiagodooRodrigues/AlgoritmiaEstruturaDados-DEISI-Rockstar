package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;

public class FunctionsRecurso {
    public static String getArtistsLouderThan(ArrayList<Artista> artistas, ArrayList<Song> musicas, ArrayList<Song> detalhes, int ano , double ruido) {
        ArrayList<Artista> artistasOrdenado = new ArrayList<>();
        artistasOrdenado.addAll(artistas);
        ArrayList<Song> musicasOrdenandas = new ArrayList<>();
        musicasOrdenandas.addAll(musicas);
        ArrayList<Song> detalhesDasMusicas = new ArrayList<>();
        detalhesDasMusicas.addAll(detalhes);
        HashSet<String> artistasFinais = new HashSet<>();

        musicasOrdenandas.sort(Comparator.comparing((Song som) -> som.iD));
        artistasOrdenado.sort(Comparator.comparing((Artista artista) -> artista.iD)); // artistas ordenados por ordem algabetica
        detalhesDasMusicas.sort(Comparator.comparing((Song som) -> som.iD));

        for (Artista artista : artistasOrdenado) {
            int index = FunctionsSongs.binarySearch(musicasOrdenandas,artista.iD);
            int index2 = FunctionsSongs.binarySearch(detalhesDasMusicas,artista.iD);
            if (index != -1 && index2 != -1) {
                if (musicasOrdenandas.get(index).anoLancamento == ano && (detalhesDasMusicas.get(index2).volumeMedio > ruido && detalhesDasMusicas.get(index2).volumeMedio < 0.0)) {
                    artistasFinais.add(artista.nome);
                }
            }
        }
        StringBuilder listaFinalArtistas = new StringBuilder();
        for(String nome : artistasFinais) {
            listaFinalArtistas.append(nome);
            listaFinalArtistas.append("\n");
        }
        if (!listaFinalArtistas.toString().equals("")) { // limpa a ultima linha
            int last = listaFinalArtistas.lastIndexOf("\n"); // encontra a posicao do \n
            if (last == listaFinalArtistas.length() - 1) { // caso a ultima linha seja \n
                listaFinalArtistas.delete(last, listaFinalArtistas.length());  // remover \n
            }
        }
        if (listaFinalArtistas.toString().equals("")) { // nao encontrou nenhum artista
            return "No results";
        }
        return listaFinalArtistas.toString();
    }

    public static String getArtistMostTags(ArrayList<Artista> artistas , int n) {
        ArrayList<Etiquetas> tabelaTags = new ArrayList<Etiquetas>();
        int contadorTagsConsideradas = 0;
        HashSet<String> repetidos = new HashSet<>();
        StringBuilder resultado = new StringBuilder(); // string final
        for (Artista artista : artistas) {
            contadorTagsConsideradas = 0;
            if (repetidos.add(artista.nome)) { // caso nao tenha lido esse artista ainda
                for (int i = 0; i < artista.tags.size(); i++) { // leio a lista de tags
                    String tag = artista.tags.get(i);
                    if (tag.length() >= n) {
                        contadorTagsConsideradas++;
                    }
                }
                if (artista.tags.size() != 0) { // pelo menos uma tag
                    tabelaTags.add(new Etiquetas(artista.nome,contadorTagsConsideradas,artista.tags.size()));
                }
                repetidos.add(artista.nome); // adiciono o nome para evitar duplicados
            }
        }
        sortTabela(tabelaTags);
        for (Etiquetas tag : tabelaTags){ // para cada artista
            resultado.append(tag.nome); // adiciono o nome
            resultado.append(" : ");
            resultado.append(tag.totais); // adiciono o seu valore de ocorrencias
            resultado.append(" : ");
            resultado.append(tag.consideraveis);
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
    public static ArrayList<Etiquetas> sortTabela(ArrayList<Etiquetas> tabelaTags) {
        Collections.sort(tabelaTags, (tag1,tag2) -> {
            if (tag1.consideraveis < tag2.consideraveis) {
                return 1;
            } else if ( tag1.consideraveis > tag2.consideraveis ) {
                return -1;
            }
            else { // desempate
                if (tag1.totais < tag2.totais) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return tabelaTags;
    }
}
