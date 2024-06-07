package utility;

public  class Carta extends Mazzo{
    private TipoCarta nome;

    public Carta(TipoCarta nome) {
        this.nome = nome;
    }

    public TipoCarta getNome() {
        return nome;
    }

}
