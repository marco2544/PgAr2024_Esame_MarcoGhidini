package utility.player;

public abstract class Giocatore {
    private String name;
    private int ps;
    private String ruolo;

    public Giocatore(String name, int ps, String ruolo) {
        this.name = name;
        this.ps = ps;
        this.ruolo = ruolo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
