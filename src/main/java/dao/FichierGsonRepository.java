package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;


public class FichierGsonRepository implements FichierRepository {
    private final String filePath;
    private final Gson gson;
    private List<Fichier> data;

    public FichierGsonRepository(String filePath) {
        this.filePath = filePath;

        // 🔑 Déclaration de l’adaptateur polymorphe
        RuntimeTypeAdapterFactory<Fichier> fichierAdapter =
                RuntimeTypeAdapterFactory.of(Fichier.class, "type")
                        .registerSubtype(Texte.class, "texte")
                        .registerSubtype(Image.class, "image")
                        .registerSubtype(Video.class, "video")
                        .registerSubtype(Audio.class, "audio");

        // 🔑 Création du Gson avec l’adaptateur
        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(fichierAdapter)
                .setPrettyPrinting()
                .create();

        // Charger les données existantes
        this.data = chargerDepuisFichier();
    }

    private List<Fichier> chargerDepuisFichier() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Fichier>>() {}.getType();
            List<Fichier> fichiers = gson.fromJson(reader, listType);
            return fichiers != null ? fichiers : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>(); // fichier inexistant → liste vide
        }
    }

    private void sauvegarderDansFichier() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Erreur de sauvegarde JSON : " + e.getMessage());
        }
    }

    @Override
    public void ajouter(Fichier fichier) {
        data.add(fichier);
        sauvegarderDansFichier();
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
        return new ArrayList<>(data);
    }

    @Override
    public boolean mettreAjour(Fichier fichier) {
        Fichier existant = trouverParNom(fichier.getNom());
        if (existant != null) {
            data.remove(existant);
            data.add(fichier);
            sauvegarderDansFichier();
            return true;
        }
        return false;
    }

    @Override
    public boolean supprimer(String nom) {
        boolean removed = data.removeIf(f -> f.getNom().equalsIgnoreCase(nom));
        if (removed) sauvegarderDansFichier();
        return removed;
    }
}
