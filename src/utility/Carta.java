package utility;

public  class Carta extends Mazzo{
    private String nome;
    private String descrizione;
    private boolean equipaggiabile;
    private String valore;
    private String seme;

    public Carta(String nome, String descrizione, boolean equipaggiabile, String valore, String seme) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.equipaggiabile = equipaggiabile;
        this.valore = valore;
        this.seme = seme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isEquipaggiabile() {
        return equipaggiabile;
    }

    public void setEquipaggiabile(boolean equipaggiabile) {
        this.equipaggiabile = equipaggiabile;
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
