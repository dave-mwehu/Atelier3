import java.time.LocalDate;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Fichier Texte = new Fichier(
                "document",
                15617,
                LocalDate.of(2024, 4, 19), ".txt",
                "C:\\Users\\Kasongo Ilunga\\Documents"

        );
        System.out.println("le nom du fichier word est "+Texte.getNom());
    }
}