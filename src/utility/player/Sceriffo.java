package utility.player;
import logic.GestioneGioco;
import utility.Personaggio;

import static costant.Costanti.SCERIFFO;

public class Sceriffo extends Giocatore{

    public Sceriffo(Personaggio p,String nik) {
        super(p.getNome(),p.getPS()+1, GestioneGioco.getRuoli(SCERIFFO),nik);
    }
}
