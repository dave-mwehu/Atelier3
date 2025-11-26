package entity;

public class Audio extends Fichier implements Jouable, Compressible {
    private int duree;
    private int debitBinaire;

    public Audio(String nom, long taille, String chemin, String extension,
                 int duree, int debitBinaire) {
        super(nom, taille, chemin, extension); // appel au constructeur de Fichier
        this.duree = Math.max(0, duree);
        this.debitBinaire = Math.max(0, debitBinaire);
    }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = Math.max(0, duree); }

    public int getDebitBinaire() { return debitBinaire; }
    public void setDebitBinaire(int debitBinaire) { this.debitBinaire = Math.max(0, debitBinaire); }

    @Override
    public void jouer() {
        System.out.println("Lecture audio (" + duree + " sec) à " + debitBinaire + " kbps");
    }

    @Override
    public void mettreSurPause() {
        System.out.println("Audio mis sur pause");
    }

    @Override
    public void compresser() {
        System.out.println("Compression de l'audio " + getNom());
    }

    @Override
    public void decompresser() {
        System.out.println("Décompression de l'audio " + getNom());
    }

    // Implémentation obligatoire de la méthode abstraite de Fichier
    @Override
    public void afficherInfos() {
        System.out.println("Audio : " + getNom() +
                " | Taille=" + getTaille() +
                " | Durée=" + duree + " sec" +
                " | Débit=" + debitBinaire + " kbps");
    }

    @Override
    public String toString() {
        return "Audio{" +
                "nom=" + getNom() +
                ", taille=" + getTaille() +
                ", duree=" + duree +
                ", debitBinaire=" + debitBinaire +
                "}";
    }
}
