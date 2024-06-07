package utility;

public class Arma extends Mazzo{
    private String nome;
    private int distanza;
    private String valore;
    private String seme;

    public Arma(String nome, int distanza, String valore, String seme) {
        this.nome = nome;
        this.distanza = distanza;
        this.valore = valore;
        this.seme = seme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDistanza() {
        return distanza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }

    public String getValore() {
        return valore;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }

    public String getSeme() {
        return seme;
    }

    public void setSeme(String seme) {
        this.seme = seme;
    }
}
