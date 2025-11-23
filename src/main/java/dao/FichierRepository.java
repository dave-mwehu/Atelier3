package dao;

import entity.Fichier;
import java.util.List;

public interface FichierRepository {
    public void ajouter(Fichier fichier);
    Fichier trouverParNom(String nom);
    List<Fichier> listerTous();
    public boolean mettreAjour(Fichier fichier);
    public boolean supprimer(String nom);
}
