package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Audio extends Fichier implements Jouable, Compressible {
    private int duree;
    private int debitBinaire;

    public Audio(String nom, long taille, String chemin, String extension, int duree, int debitBinaire) {
        super(nom, taille, chemin, extension); // <--- super doit être la 1ère instruction
        this.duree = Math.max(0, duree);
        this.debitBinaire = Math.max(0, debitBinaire);
    }


    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = Math.max(0, duree);
    }

    public int getDebitBinaire() {
        return debitBinaire;
    }

    public void setDebitBinaire(int debitBinaire) {
        this.debitBinaire = Math.max(0, debitBinaire);
    }

    @Override
    public void jouer() {
        System.out.println("Lecture Audio (" + duree + " sec )" + debitBinaire +" kbps");
    }

    @Override
    public void mettreSurPause(){
        System.out.println("Audio mis sur pause");
    }

    @Override
    public void compresser() {
        System.out.println("Compression de l'audio "+ getNom());
    }

    @Override
    public void decompresser() {
        System.out.println("Decompression de l'audio "+ getNom());
    }
}
