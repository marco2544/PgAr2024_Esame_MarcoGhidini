package utility.player;

import utility.Arma;
import utility.Carta;

import java.util.ArrayList;

public abstract class Giocatore {
    private String nikname;
    private String nomePersonaggio;
    private int ps;
    private String ruolo;
    private Arma arma;
    private ArrayList<Carta> carteInMano;

    public Giocatore(String nomePersonaggio, int ps, String ruolo,String nikname) {
        this.nomePersonaggio = nomePersonaggio;
        this.ps = ps;
        this.ruolo = ruolo;
        this.nikname = nikname;
        this.carteInMano=new ArrayList<Carta>();
        this.arma=new Arma("Colt 45",1);
    }


    public String getNikname() {
        return nikname;
    }

    public ArrayList<Carta> getCarteInMano() {
        return carteInMano;
    }

    public String getNomePersonaggio() {
        return nomePersonaggio;
    }

    public void setNomePersonaggio(String nomePersonaggio) {
        this.nomePersonaggio = nomePersonaggio;
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

    public void setCarteInMano(ArrayList<Carta> carteInMano) {
        this.carteInMano = carteInMano;
    }

    public void setNikname(String nikname) {
        this.nikname = nikname;
    }

    public Arma getArma() {
        return arma;
    }

    @Override
    public String toString() {
        return this.nikname +" personaggio: " +this.nomePersonaggio+"  ruolo: " +this.ruolo;
    }

}
