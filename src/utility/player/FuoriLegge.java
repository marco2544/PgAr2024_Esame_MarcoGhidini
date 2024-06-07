package utility.player;

import logic.GestioneGioco;
import utility.Personaggio;
import static costant.Costanti.*;
public class FuoriLegge extends Giocatore{
    public FuoriLegge(Personaggio p) {
        super(p.getNome(),p.getPS(), GestioneGioco.getRuoli(FUORILEGGE));
    }
}
