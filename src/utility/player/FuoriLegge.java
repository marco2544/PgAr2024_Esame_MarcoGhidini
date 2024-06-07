package utility.player;

import logic.GestioneGioco;
import utility.Personaggio;
import static costant.Costanti.*;
public class FuoriLegge extends Giocatore{
    public FuoriLegge(Personaggio p,String nik) {
        super(p.getNome(), p.getPS(), GestioneGioco.getRuoli(FUORILEGGE), nik);
    }
}
