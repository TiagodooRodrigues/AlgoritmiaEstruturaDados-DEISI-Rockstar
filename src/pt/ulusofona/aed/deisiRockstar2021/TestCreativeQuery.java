package pt.ulusofona.aed.deisiRockstar2021;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class TestCreativeQuery {
    @Test
    public void testVazioSCreativeQuery() throws IOException {
        Main.loadFiles();
        String resultadoReal = Main.execute("GET_MOST_DURATION_YEAR 3000 4000");
        String resultadoEsperado = "";
        assertEquals("Deveria dar: ",resultadoEsperado,resultadoReal.toString());
    }
    @Test
    public void testNormalCreativeQuery() throws IOException {
        Main.loadFiles();
        String resultadoReal = Main.execute("GET_MOST_DURATION_YEAR 2020 2021");
        String resultadoEsperado = Main.execute("GET_MOST_DURATION_YEAR 2020 2021");
        assertEquals("Deveria dar:",resultadoEsperado,resultadoReal);
    }
}

