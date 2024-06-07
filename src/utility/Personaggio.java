package utility;

public class Personaggio {
    private int ps;
    private String nome;
    private String descrizione;

    public Personaggio(int ps, String nome, String descrizione) {
        this.ps = ps;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public int getPS() {
        return ps;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
