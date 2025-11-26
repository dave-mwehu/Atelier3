package entity;

public class Video extends Fichier implements Jouable, Compressible {
    private int duree;
    private String codec;

    public Video(String nom, long taille, String chemin, String extension,
                 int duree, String codec) {
        super(nom, taille, chemin, extension); // appel au constructeur de Fichier
        this.duree = Math.max(0, duree);
        this.codec = codec;
    }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = Math.max(0, duree); }

    public String getCodec() { return codec; }
    public void setCodec(String codec) { this.codec = codec; }

    @Override
    public void jouer() {
        System.out.println("Lecture vidéo (" + duree + " sec) codec " + codec);
    }

    @Override
    public void mettreSurPause() {
        System.out.println("Vidéo mise sur pause");
    }

    @Override
    public void compresser() {
        System.out.println("Compression de la vidéo " + getNom());
    }

    @Override
    public void decompresser() {
        System.out.println("Décompression de la vidéo " + getNom());
    }

    // Implémentation obligatoire de la méthode abstraite de Fichier
    @Override
    public void afficherInfos() {
        System.out.println("Vidéo : " + getNom() +
                " | Taille=" + getTaille() +
                " | Durée=" + duree + " sec" +
                " | Codec=" + codec);
    }

    @Override
    public String toString() {
        return "Video{" +
                "nom=" + getNom() +
                ", taille=" + getTaille() +
                ", duree=" + duree +
                ", codec=" + codec +
                "}";
    }
}
