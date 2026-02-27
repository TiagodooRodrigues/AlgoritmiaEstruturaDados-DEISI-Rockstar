package pt.ulusofona.aed.deisiRockstar2021;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class TestSong {
   // @Test
   /* public void testSongNoTxt() throws IOException {
        Main.loadFiles();
        String expected = "4zOmmPgqE0P7amaIAkQer8 | Ding Dong! Merrily on High | 2013 | 2:9 | 48.0 | Choir of Clare College / Cambridge / Orchestra of Clare College / John Rutter | (1 / 3 / 1 / 5)";
        Song obtained = new Song("4zOmmPgqE0P7amaIAkQer8","Ding Dong! Merrily on High",2013);
        assertEquals("Expected: ", expected, obtained.toString());
   */
    @Test
    public void testSongForaTxt() throws IOException {
        Main.loadFiles();
        String expected = "ID desconhecido | Nao esta no songs.txt | 0 | 0:0 | 0.0 |  | ()";
        Song obtained = new Song("ID desconhecido","Nao esta no songs.txt",0);
        assertEquals("Expected : ", expected, obtained.toString());
    }
}
