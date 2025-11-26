import dao.FichierListRepository;
import dao.FichierRepository;
import dao.FichierGsonRepository;
import entity.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner entree = new Scanner(System.in);
    private static FichierRepository repo = new FichierGsonRepository("fichiers.json");

    public static void main(String[] args) {
        boolean demarrer = true;
        while (demarrer) {
            System.out.println("""
                    === Gestion des Fichiers ===
                    1. Ajouter un fichier
                    2. Lister tous les Fichiers
                    3. Rechercher par nom
                    4. Mettre à jour un fichier
                    5. Supprimer un fichier
                    6. Tester actions polymorphes
                    0. Quitter
                    Choix: """);
            String choix = entree.nextLine().trim();
            try {
                switch (choix) {
                    case "1" -> ajouterFichier();
                    case "2" -> lister();
                    case "3" -> rechercher();
                    case "4" -> mettreAJour();
                    case "5" -> supprimer();
                    case "6" -> testerActions();
                    case "0" -> demarrer = false;
                    default -> System.out.println("❌ Choix invalide.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Erreur : " + e.getMessage());
            }
        }
        System.out.println("👋 Au revoir.");
    }

    private static void ajouterFichier() {
        System.out.println("Type de fichier (texte/image/video/audio): ");
        String type = entree.nextLine().trim().toLowerCase();

        System.out.print("Nom: "); String nom = entree.nextLine();
        System.out.print("Taille (long): "); long taille = Long.parseLong(entree.nextLine());
        System.out.print("Chemin: "); String chemin = entree.nextLine();
        System.out.print("Extension (ex: .txt, .jpg, ...): "); String extension = entree.nextLine();

        Fichier f = null;
        switch (type) {
            case "texte" -> {
                System.out.print("Encodage: "); String enc = entree.nextLine();
                System.out.print("Nombre de lignes: "); int n = Integer.parseInt(entree.nextLine());
                f = new Texte(nom, taille, chemin, extension, enc, n);
            }
            case "image" -> {
                System.out.print("Résolution (ex: 1920x1080): "); String res = entree.nextLine();
                System.out.print("Format (ex: JPEG, PNG): "); String format = entree.nextLine();
                f = new Image(nom, taille, chemin, extension, res, format);
            }
            case "video" -> {
                System.out.print("Durée (sec): "); int duree = Integer.parseInt(entree.nextLine());
                System.out.print("Codec (ex: H264): "); String codec = entree.nextLine();
                f = new Video(nom, taille, chemin, extension, duree, codec);
            }
            case "audio" -> {
                System.out.print("Durée (sec): "); int duree = Integer.parseInt(entree.nextLine());
                System.out.print("Débit binaire (kbps): "); int kbps = Integer.parseInt(entree.nextLine());
                f = new Audio(nom, taille, chemin, extension, duree, kbps);
            }
            default -> System.out.println("❌ Type inconnu.");
        }
        if (f != null) {
            repo.ajouter(f);
            System.out.println("✅ Ajout réussi: " + f.getNom());
        }
    }

    private static void lister() {
        List<Fichier> fichiers = repo.listerTous();
        if (fichiers.isEmpty()) {
            System.out.println("⚠️ Aucun fichier.");
        } else {
            System.out.println("📂 Liste des fichiers :");
            fichiers.forEach(Fichier::afficherInfos); // polymorphisme
        }
    }

    private static void rechercher() {
        System.out.print("Nom à rechercher: ");
        String nom = entree.nextLine();
        Fichier f = repo.trouverParNom(nom);
        System.out.println(f != null ? "🔎 Résultat : " + f : "❌ Introuvable");
    }

    private static void mettreAJour() {
        System.out.print("Nom du fichier à mettre à jour: ");
        String nom = entree.nextLine();
        Fichier existant = repo.trouverParNom(nom);
        if (existant == null) { System.out.println("❌ Introuvable"); return; }

        System.out.print("Nouvelle taille (long): "); long taille = Long.parseLong(entree.nextLine());
        System.out.print("Nouveau chemin: "); String chemin = entree.nextLine();
        System.out.print("Nouvelle extension: "); String ext = entree.nextLine();

        Fichier maj = null;
        if (existant instanceof Texte) {
            System.out.print("Encodage: "); String enc = entree.nextLine();
            System.out.print("Nombre de lignes: "); int n = Integer.parseInt(entree.nextLine());
            maj = new Texte(nom, taille, chemin, ext, enc, n);
        } else if (existant instanceof Image) {
            System.out.print("Résolution: "); String res = entree.nextLine();
            System.out.print("Format: "); String format = entree.nextLine();
            maj = new Image(nom, taille, chemin, ext, res, format);
        } else if (existant instanceof Video) {
            System.out.print("Durée (sec): "); int duree = Integer.parseInt(entree.nextLine());
            System.out.print("Codec: "); String codec = entree.nextLine();
            maj = new Video(nom, taille, chemin, ext, duree, codec);
        } else if (existant instanceof Audio) {
            System.out.print("Durée (sec): "); int duree = Integer.parseInt(entree.nextLine());
            System.out.print("Débit binaire (kbps): "); int kbps = Integer.parseInt(entree.nextLine());
            maj = new Audio(nom, taille, chemin, ext, duree, kbps);
        }

        boolean ok = maj != null && repo.mettreAjour(maj);
        System.out.println(ok ? "🔄 Mise à jour réussie" : "❌ Échec de mise à jour");
    }

    private static void supprimer() {
        System.out.print("Nom à supprimer: ");
        String nom = entree.nextLine();
        boolean ok = repo.supprimer(nom);
        System.out.println(ok ? "🗑️ Supprimé" : "❌ Introuvable");
    }

    private static void testerActions() {
        System.out.print("Nom du fichier pour tester: ");
        String nom = entree.nextLine();
        Fichier f = repo.trouverParNom(nom);
        if (f == null) { System.out.println("❌ Introuvable"); return; }

        System.out.println("=== Test polymorphe ===");
        f.afficherInfos(); // polymorphisme
        System.out.println(f.lire());

        if (f instanceof Lisible l) {
            System.out.println(l.lireContenu());
            if (f instanceof Texte ft) System.out.println("📝 Mots (approx): " + ft.compterMots());
        }
        if (f instanceof Affichable a) {
            a.afficher();
            a.redimensionner();
        }
        if (f instanceof Jouable j) {
            j.jouer();
            j.mettreSurPause();
        }
        if (f instanceof Compressible c) {
            c.compresser();
            c.decompresser();
        }
    }
}
