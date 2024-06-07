package utility.player;

import utility.Ruolo;

import static costant.Costanti.PS;

public class FuoriLegge extends Giocatore{
    public FuoriLegge(String name) {
        super(name, PS, Ruolo.FUORILEGGE);
    }
}
