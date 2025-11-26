package entity;

public class Image extends Fichier implements Affichable, Compressible {
    private String resolution;
    private String format;

    public Image(String nom, long taille, String chemin, String extension,
                 String resolution, String format) {
        super(nom, taille, chemin, extension);
        this.resolution = resolution;
        this.format = format;
    }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    @Override
    public void afficher() {
        System.out.println("Affichage de l'image " + getNom() +
                " en " + resolution + " (" + format + ")");
    }

    @Override
    public boolean redimensionner() {
        System.out.println("Image " + getNom() + " redimensionnée");
        return true;
    }

    @Override
    public void compresser() {
        System.out.println("Compression de l'image " + getNom());
    }

    @Override
    public void decompresser() {
        System.out.println("Décompression de l'image " + getNom());
    }

    // Implémentation obligatoire de la méthode abstraite de la class Fichier
    @Override
    public void afficherInfos() {
        System.out.println("Image : " + getNom() +
                " | Taille=" + getTaille() +
                " | Résolution=" + resolution +
                " | Format=" + format);
    }

    @Override
    public String toString() {
        return "Image{" +
                "nom=" + getNom() +
                ", taille=" + getTaille() +
                ", resolution=" + resolution +
                ", format=" + format +
                "}";
    }
}
