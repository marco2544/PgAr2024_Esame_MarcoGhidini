package utility.player;

import logic.GestioneGioco;
import utility.Personaggio;

import static costant.Costanti.*;

public class Vice extends Giocatore{
    public Vice(Personaggio p, String nik) {
        super(p.getNome(),p.getPS(), GestioneGioco.getRuoli(VICE),nik);
    }
}
