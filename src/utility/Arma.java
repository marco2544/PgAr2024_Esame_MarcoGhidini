package utility;

public class Arma extends Mazzo{
    private String nome;
    private int distanza;

    public String getNome() {
        return nome;
    }

    public int getDistanza() {
        return distanza;
    }

    public Arma(String nome, int distanza) {
        this.nome = nome;
        this.distanza = distanza;
    }
}
