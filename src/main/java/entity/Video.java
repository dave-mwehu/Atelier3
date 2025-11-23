package entity;

import java.time.LocalDate;

public class Video extends Fichier implements Jouable, Compressible {
    private int duree;
    private String codec;

    public Video(String nom, long taille, String chemin, String extension, int duree, String codec) {
        super(nom, taille, chemin, extension); // obligatoire en premier
        this.duree = Math.max(0, duree);
        this.codec = codec;
    }


    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = Math.max(0, duree);
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    @Override
    public void jouer() {
        System.out.println("Lecture video (" + duree + " sec) codec " + codec);
    }

    @Override
    public void mettreSurPause() {
        System.out.println("Video mise sur pause");
    }

    @Override
    public void compresser() {
        System.out.println("Compression de la Video "+ getNom());
    }
    @Override
    public void decompresser() {
        System.out.println("Decompression de la Video "+ getNom());
    }
}
