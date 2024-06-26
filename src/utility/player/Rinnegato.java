package utility.player;

import logic.GestioneGioco;
import utility.Personaggio;

import static costant.Costanti.RINNEGATO;


public class Rinnegato extends Giocatore{
    public Rinnegato(Personaggio p,String nik) {
        super(p.getNome(),
                p.getPS(),
                GestioneGioco.getRuoli(RINNEGATO),
                nik);
    }
}
