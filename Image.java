public class Image extends Fichier {
    private String resolution;
    private String format;

    public Image(String resolution, String format) {
        this.resolution = resolution;
        this.format = format;
    }
    public Image() {}

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public String getResolution() {
        return resolution;
    }
    public String getFormat() {
        return format;
    }
}
