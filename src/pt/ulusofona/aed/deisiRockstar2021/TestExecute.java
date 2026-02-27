package pt.ulusofona.aed.deisiRockstar2021;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TestExecute {
    @Test
    public void testCountSongsYear() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("COUNT_SONGS_YEAR 2020");
        String resultadoEsperado = "7";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testCountDuplicateSongs() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("COUNT_DUPLICATE_SONGS_YEAR 2019");
        String resultadoEsperado = "0";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeGetArtistForTagNaoAdicionada() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("GET_ARTISTS_FOR_TAG funk");
        String resultadoEsperado = "No results";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeGetMostDanceable() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("GET_MOST_DANCEABLE 2017 2020 3");
        String resultadoEsperado = "Best Friend : 2018 : 0.4970000000000001\n" +
                "Viver : 2020 : 0.49\n" +
                "Tranquilo : 2020 : 0.45299999999999996";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal);
    }
    @Test
    public void testeGetAddTags() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("ADD_TAGS Plutonio;RAP");
        String resultadoEsperado = "Plutonio | RAP";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeGetAddTagsInexistente() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("ADD_TAGS Desconhecido;Kizomba");
        String resultadoEsperado = "Inexistent artist";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeGetArtistForTagContida() throws IOException {
        LeituraFilesAlunos.loadFiles();
        ArrayList<String> tagsPedidas = new ArrayList<>();
        tagsPedidas.add("RAP");
        tagsPedidas.add("TECNO");
        FunctionsArtist.tagsAdd(LeituraFilesAlunos.listadeArtistas,"Bispo",tagsPedidas);
        String resultadoReal = LeituraFilesAlunos.execute("GET_ARTISTS_FOR_TAG RAP");
        String resultadoEsperado = "Bispo";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeRemoveTagsInexistente() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("REMOVE_TAGS Desconhecido;Kizomba");
        String resultadoEsperado = "Inexistent artist";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeRemoveTags() throws IOException {
        LeituraFilesAlunos.loadFiles();
        ArrayList<String> tagsPedidas = new ArrayList<>();
        tagsPedidas.add("RAP");
        tagsPedidas.add("TECNO");
        FunctionsArtist.tagsAdd(LeituraFilesAlunos.listadeArtistas,"Bispo",tagsPedidas);
        String resultadoReal = LeituraFilesAlunos.execute("REMOVE_TAGS Bispo;RAP");
        String resultadoEsperado = "Bispo | TECNO";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeRemoveTagsVazio() throws IOException {
        LeituraFilesAlunos.loadFiles();
        ArrayList<String> tagsPedidas = new ArrayList<>();
        tagsPedidas.add("RAP");
        tagsPedidas.add("TECNO");
        FunctionsArtist.tagsAdd(LeituraFilesAlunos.listadeArtistas,"Bispo",tagsPedidas);
        String resultadoReal = LeituraFilesAlunos.execute("REMOVE_TAGS Bispo;RAP;TECNO");
        String resultadoEsperado = "Bispo | No tags";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeGetArtistsOneSong() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("GET_ARTISTS_ONE_SONG 2017 2020");
        String resultadoEsperado = "Bluray | Louco | 2020\n" +
                "Jvst Fly | Cura | 2020\n" +
                "Phoenix | Vencedor | 2019\n" +
                "Richie | Best Friend | 2018\n" +
                "RonnyFuego | Bem-Disposto | 2020";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeGetArtistsOneSong2() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("GET_ARTISTS_ONE_SONG 2030 2040");
        String resultadoEsperado = ""; // tem de dar vazio
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeUniqueTags() throws IOException {
        LeituraFilesAlunos.loadFiles();
        LeituraFilesAlunos.execute("ADD_TAGS Plutonio;Kimzomba;funk");
        LeituraFilesAlunos.execute("ADD_TAGS Bispo;funk");
        String resultadoReal = LeituraFilesAlunos.execute("GET_UNIQUE_TAGS");
        String resultadoEsperado = "KIMZOMBA 1\n" +
                "FUNK 2"; // tem de dar vazio
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeUniqueTags2() throws IOException {
        LeituraFilesAlunos.loadFiles();
        LeituraFilesAlunos.execute("ADD_TAGS Pedro Abrunhosa;funk");
        String resultadoReal = LeituraFilesAlunos.execute("GET_UNIQUE_TAGS");
        String resultadoEsperado = "No results"; // tem de dar vazio
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeUniqueTagsBetween() throws IOException {
        LeituraFilesAlunos.loadFiles();
        LeituraFilesAlunos.execute("ADD_TAGS Bispo;rap;pop;");
        LeituraFilesAlunos.execute("ADD_TAGS Richie;rap");
        String resultadoReal = LeituraFilesAlunos.execute("GET_UNIQUE_TAGS_IN_BETWEEN_YEARS 2015 2020");
        String resultadoEsperado = "RAP 2\n" +
                "POP 1"; // tem de dar vazio
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testeUniqueTagsBetween2() throws IOException {
        LeituraFilesAlunos.loadFiles();
        LeituraFilesAlunos.execute("ADD_TAGS Pedro Alves;rap;pop;");
        String resultadoReal = LeituraFilesAlunos.execute("GET_UNIQUE_TAGS_IN_BETWEEN_YEARS 2015 2020");
        String resultadoEsperado = "No results";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void recurso1() throws IOException {
        LeituraFilesAlunos.loadFiles();
        String resultadoReal = LeituraFilesAlunos.execute("GET_ARTISTS_LOUDER_THAN 2020 -100");
        String resultadoEsperado = "Aragão";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
}
