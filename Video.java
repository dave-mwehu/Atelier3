public class Video extends Fichier {
    private int duree;
    private String codec;

    public Video(int duree, String codec) {
        this.duree = duree;
        this.codec = codec;
    }

    public Video() {}

    public void setDuree(int duree) {
        this.duree = duree;
    }
    public void setCodec(String codec) {
        this.codec = codec;
    }

    public int getDuree() {
        return duree;
    }
    public String getCodec() {
        return codec;
    }
}
