package utility.player;

import it.kibo.fp.lib.InputData;
import logic.GestioneGioco;
import utility.Arma;
import utility.Carta;
import utility.Mazzo;

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
        this.carteInMano=preparocarte(ps);
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


    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getRuolo() {
        return ruolo;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void pesca(Mazzo carta){
        if (carta.getClass().equals(Arma.class)){
            if (InputData.readString("è un arma vuoi equipaggiarla? (y/n) ",false).equalsIgnoreCase("y")){
                GestioneGioco.getMazzoScarti().add(getArma());
                setArma((Arma) carta);
            }
            else {
                GestioneGioco.getMazzoScarti().add(carta);
            }
        }
        else if (carta.getClass().equals(Carta.class)){
            getCarteInMano().add((Carta) carta);
        }

    }
    @Override
    public String toString() {
        return this.nikname +" personaggio: " +this.nomePersonaggio+"  ruolo: " +this.ruolo;
    }

    private static ArrayList<Carta> preparocarte(int ps){
        ArrayList<Carta> carte = new ArrayList<Carta>();
        for (int i = 0; i < ps; i++) {
            carte.add(GestioneGioco.getCarte().getFirst());
            GestioneGioco.getCarte().remove(carte.getLast());
        }
        return carte;
    }

}
