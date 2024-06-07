package utility.player;

import utility.Ruolo;

public abstract class Giocatore {
    private String name;
    private int ps;
    private Ruolo ruolo;

    public Giocatore(String name, int ps, Ruolo ruolo) {
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

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }
}
