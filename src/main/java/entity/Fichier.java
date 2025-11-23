package entity;

import java.util.Date;
import java.util.Objects;

public class Fichier {
    protected String nom;
    protected long taille;
    protected Date dateCreation;
    protected String chemin;
    protected String extension;

    // constructeur attendu par les sous-classes
    public Fichier(String nom, long taille, String chemin, String extension) {
        this.nom = Objects.requireNonNull(nom);
        if (nom.isBlank()) throw new IllegalArgumentException("Nom vide");
        if (taille < 0) throw new IllegalArgumentException("Taille négative");
        this.taille = taille;
        this.dateCreation = new Date();          // initialisation automatique
        this.chemin = Objects.requireNonNull(chemin);
        this.extension = Objects.requireNonNull(extension);
    }

    // getters / setters et méthodes existantes...
    public String getNom() { return nom; }
    public void setNom(String nom) {
        if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Nom vide");
        this.nom = nom;
    }

    public long getTaille() { return taille; }
    public void setTaille(long taille) {
        if (taille < 0) throw new IllegalArgumentException("Taille négative");
        this.taille = taille;
    }

    public Date getDateCreation() { return dateCreation; }
    public String getChemin() { return chemin; }
    public void setChemin(String chemin) { this.chemin = Objects.requireNonNull(chemin); }

    public String getExtension() { return extension; }
    public void setExtension(String extension) { this.extension = Objects.requireNonNull(extension); }

    public String lire() { return "Lecture du fichier " + nom; }
    public boolean supprimer() { return true; }
    public boolean mettreAJour() { return true; }
    public void creerNouveau() { System.out.println("Nouveau fichier créé : " + nom); }
    public void ouvrir() { System.out.println("Ouverture du fichier : " + nom); }
    public void fermer() { System.out.println("Fermeture du fichier : " + nom); }

    @Override
    public String toString() {
        return "Fichier{" +
                "nom='" + nom + '\'' +
                ", taille=" + taille +
                ", dateCreation=" + dateCreation +
                ", chemin='" + chemin + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
