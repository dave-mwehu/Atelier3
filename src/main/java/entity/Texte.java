package entity;

public class Texte extends Fichier implements Lisible, Compressible {
    private String encodage;
    private int nombreLignes;

    public Texte(String nom, long taille, String chemin, String extension,
                 String encodage, int nombreLignes) {
        super(nom, taille, chemin, extension); // super doit être la première instruction
        this.encodage = encodage;
        this.nombreLignes = Math.max(0, nombreLignes);
    }

    public String getEncodage() { return encodage; }
    public void setEncodage(String encodage) { this.encodage = encodage; }

    public int getNombreLignes() { return nombreLignes; }
    public void setNombreLignes(int nombreLignes) { this.nombreLignes = Math.max(0, nombreLignes); }

    @Override
    public String lireContenu() {
        // Retour simple pour tests ; remplace par la logique réelle si tu charges du contenu
        return "Contenu de " + getNom() + " (encodage=" + encodage + ", lignes=" + nombreLignes + ")";
    }

    public int compterMots() {
        return nombreLignes * 10; // approximation ; adapte si besoin
    }

    @Override
    public void compresser() {
        System.out.println("Compression du fichier texte " + getNom());
    }

    @Override
    public void decompresser() {
        System.out.println("Décompression du fichier texte " + getNom());
    }

    @Override
    public String toString() {
        return "Texte{" + "nom=" + getNom() + ", taille=" + getTaille() +
                ", encodage=" + encodage + ", lignes=" + nombreLignes + "}";
    }
}
