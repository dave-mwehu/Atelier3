import java.util.*;
import java.time.LocalDate;

public class Fichier {
    private String nom;
    private long taille;
    private LocalDate dateCreation;
    private String extension;
    private String chemin;

    public Fichier(String nom, long taille, LocalDate dateCreation, String extension, String chemin) {
        this.nom = nom;
        this.taille = taille;
        this.dateCreation = dateCreation;
        this.extension = extension;
        this.chemin = chemin;
    }
    public Fichier() {}
    public void  setNom(String nom) {
        this.nom = nom;
    }
    public void  setTaille(long taille) {
        this.taille = taille;
    }
    public void  setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
    public void  setExtension(String extension) {
        this.extension = extension;
    }
    public void  setChemin(String chemin) {
        this.chemin = chemin;
    }
    public String getNom() {
        return nom;
    }
    public long getTaille() {
        return taille;
    }
    public LocalDate getDateCreation() {
        return dateCreation;
    }
    public String getExtension() {
        return extension;
    }
    public String getChemin() {
        return chemin;
    }
}
