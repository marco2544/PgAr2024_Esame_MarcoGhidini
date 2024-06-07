package utility.player;
import utility.Ruolo;
import static costant.Costanti.*;

public class Sceriffo extends Giocatore{
    public Sceriffo(String nome) {
        super(nome,PS_SCERIFFO, Ruolo.SCERIFFO);
    }
}
