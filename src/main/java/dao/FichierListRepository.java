package dao;

import entity.Fichier;
import java.util.ArrayList;
import java.util.List;

public class FichierListRepository implements FichierRepository {
    private final List<Fichier> data = new ArrayList<>();

    @Override
    public void ajouter(Fichier fichier) {
        data.add(fichier);
        System.out.println("✅ Fichier ajouté : " + fichier.getNom());
    }

    @Override
    public Fichier trouverParNom(String nom) {
        return data.stream()
                .filter(f -> f.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Fichier> listerTous() {
        if (data.isEmpty()) {
            System.out.println("⚠️ Aucun fichier enregistré.");
        } else {
            System.out.println("📂 Liste des fichiers :");
            data.forEach(Fichier::afficherInfos); // polymorphisme : chaque type affiche ses infos
        }
        return new ArrayList<>(data);
    }

    @Override
    public boolean mettreAjour(Fichier fichier) {
        Fichier existant = trouverParNom(fichier.getNom());
        if (existant != null) {
            data.remove(existant);
            data.add(fichier);
            System.out.println("🔄 Mise à jour réussie pour : " + fichier.getNom());
            return true;
        }
        System.out.println(" Fichier introuvable : " + fichier.getNom());
        return false;
    }

    @Override
    public boolean supprimer(String nom) {
        boolean removed = data.removeIf(f -> f.getNom().equalsIgnoreCase(nom));
        if (removed) {
            System.out.println("🗑️ Fichier supprimé : " + nom);
        } else {
            System.out.println(" Aucun fichier trouvé avec le nom : " + nom);
        }
        return removed;
    }
}
