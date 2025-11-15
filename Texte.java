public class Texte extends Fichier {
    private String Encodage;
    private int nombrelignes;

    public Texte(String Encode,int nombrelignes){
        this.Encodage=Encode;
        this.nombrelignes=nombrelignes;
    }
    public Texte(){}

    public void setEncodage(String Encode){
        this.Encodage=Encode;
    }
    public void setNombrelignes(int nombrelignes){
        this.nombrelignes=nombrelignes;
    }

    public String getEncodage(){
        return this.Encodage;
    }
    public int getNombrelignes(){
        return this.nombrelignes;
    }
}
