public class Audio extends Fichier {
    private int duree;
    private int debitBinaire;

    public Audio(int duree, int debitbinaire) {
        this.duree = duree;
        this.debitBinaire = debitbinaire;
    }

    public Audio() {}

    public void setDuree(int duree) {
        this.duree = duree;
    }
    public void setDebitbinaire(int debitbinaire) {
        this.debitBinaire = debitbinaire;
    }

    public int getDuree() {
        return duree;
    }
    public int getDebitbinaire() {
        return debitBinaire;
    }
}
