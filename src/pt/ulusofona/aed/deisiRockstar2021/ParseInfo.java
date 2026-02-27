package pt.ulusofona.aed.deisiRockstar2021;

public class ParseInfo { // linhas ok e ignoradas
    int numLinhasOk;
    int numLinhasIgnored;

    public ParseInfo(int NUM_LINHAS_OK, int NUM_LINHAS_IGNORED) {
        this.numLinhasOk = NUM_LINHAS_OK;
        this.numLinhasIgnored = NUM_LINHAS_IGNORED;
    }
    public String toString() {
        return "OK: " + numLinhasOk +", Ignored: " + numLinhasIgnored;
    }
}
