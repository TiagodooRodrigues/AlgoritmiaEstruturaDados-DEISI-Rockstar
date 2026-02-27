package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;

public class SwitchExecute {
    public static String switchExecuteFuction1(String[] input) { // switch 1
        switch (input[0]) {
            case "COUNT_SONGS_YEAR":
                return Integer.toString(FunctionsSongs.countSongsYear(Main.listadeSons2, Integer.parseInt(input[1])));
            case "COUNT_DUPLICATE_SONGS_YEAR":
                return Integer.toString(FunctionsSongs.countDuplicateSongsYear(Main.listadeSons, Integer.parseInt(input[1])));
            case "GET_ARTISTS_FOR_TAG":
                return FunctionsArtist.get_ArtistForTag(Main.listadeArtistas, input[1].trim().toUpperCase());
            case "GET_MOST_DANCEABLE":
                String[] dados = input[1].split(" ");
                return FunctionsSongs.getMostDanceable(Main.listadeSons, Main.listadetalhes, Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]));
            case "GET_ARTISTS_ONE_SONG":
                String[] dadosAnosArtistas = input[1].split(" ");
                return FunctionsArtist.getArtistsOneSong(Integer.parseInt(dadosAnosArtistas[0]), Integer.parseInt(dadosAnosArtistas[1]),Main.listadeSons2,Main.listadeArtistas);
        }
        return switchExecuteFuction2(input);
    }
    public static String switchExecuteFuction2(String[] input) { // switch 2
        switch (input[0]) {
            case "GET_ARTISTS_MOST_TAGS" :
                return FunctionsRecurso.getArtistMostTags(Main.listadeArtistas,Integer.parseInt(input[1]));
            case "GET_ARTISTS_LOUDER_THAN":
                String[] dados = input[1].split(" ");
                return FunctionsRecurso.getArtistsLouderThan(Main.listadeArtistas,Main.listadeSons2,Main.listaDetalhes2,Integer.parseInt(dados[0]),Double.parseDouble(dados[1]));
            case "GET_TOP_ARTISTS_WITH_SONGS_BETWEEN":
                return "d";
            case "MOST_FREQUENT_WORDS_IN_ARTIST_NAME":
                return "e";
            case "GET_UNIQUE_TAGS":
                return FunctionsArtist.getUniqueTags(Main.listadeArtistas);
            case "GET_UNIQUE_TAGS_IN_BETWEEN_YEARS":
                String[] anos = input[1].split(" ");
                return FunctionsArtist.getUniqueTagsBetween(Integer.parseInt(anos[0]),Integer.parseInt(anos[1]),Main.listadeArtistas,Main.listadeSons);
            case "GET_RISING_STARS":
                return "a";
        }
        return switchExecuteFuction3(input);
    }

    public static String switchExecuteFuction3(String[] input) { // switch 3
        switch (input[0]) {
            case "ADD_TAGS":
                ArrayList<String> tagsPedidas = new ArrayList<>();
                String[] tags = input[1].split(";"); // divide pelo ;
                String artistaPedido = tags[0]; // a posicao 0 sera o nome do artista nao contar
                for (int i = 1; i < tags.length; i++) { // comeca no 1 porque o zero é o artista
                    tagsPedidas.add(tags[i].trim().toUpperCase()); // colocar a TAG em maiscula sem espaco
                }
                return FunctionsArtist.tagsAdd(Main.listadeArtistas, artistaPedido, tagsPedidas); // tags[0] sera o artista
            case "REMOVE_TAGS":
                ArrayList<String> tagsPedidas2 = new ArrayList<>();
                String[] tags2 = input[1].split(";"); // divide pelo ;
                for (int i = 1; i < tags2.length; i++) { // comeca no 1 porque o zero é o artista
                    tagsPedidas2.add(tags2[i].trim().toUpperCase()); // colocar a TAG em maiscula sem espaco
                }
                return FunctionsArtist.removeTaggs(Main.listadeArtistas, tags2[0], tagsPedidas2);
            case "GET_MOST_DURATION_YEAR":
                String[] dadosDuracao = input[1].split(" ");
                return FunctionsSongs.getMostDurationThatYear(Main.listadeSons, Main.listadetalhes, Integer.parseInt(dadosDuracao[0]), Integer.parseInt(dadosDuracao[1]));
            case "CLEANUP":
                return "l";
            default:
                return "Illegal command. Try again";
        }
    }
}
