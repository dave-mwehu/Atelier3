package dao;

import entity.Fichier;
import java.util.ArrayList;
import java.util.List;

public class FichierListRepository implements FichierRepository {
    private final List<Fichier> data = new ArrayList<>();

    @Override
    public void ajouter(Fichier fichier) {
        data.add(fichier);
    }

    @Override
    public Fichier trouverParNom(String nom) {
        return data.stream().filter(f -> f.getNom().equalsIgnoreCase(nom)).findFirst().orElse(null);
    }

    @Override
    public List<Fichier> listerTous(){
        return new ArrayList<>(data);
    }

    @Override
    public boolean mettreAjour(Fichier fichier) {
        Fichier existant=trouverParNom(fichier.getNom());
        if(existant!=null){
            data.remove(existant);
            data.add(fichier);
            return true;
        }
        return false;
    }

    @Override
    public boolean supprimer(String nom) {
        return data.removeIf(f -> f.getNom().equalsIgnoreCase(nom)) ;
    }
}
