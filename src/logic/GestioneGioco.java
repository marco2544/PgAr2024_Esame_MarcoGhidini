package logic;

import it.kibo.fp.lib.InputData;
import presentation.InterfacciaUtente;
import utility.player.Giocatore;

import java.util.ArrayList;

import static costant.Costanti.*;

public class GestioneGioco {
    ArrayList<Giocatore> giocatori=new ArrayList<Giocatore>();
    public static void start()  {
        InterfacciaUtente.presentazione();
        regole();
    }

    private static void regole()    {
        String scelta= InputData.readString("", false);
        while (!scelta.equalsIgnoreCase("y")&&!scelta.equalsIgnoreCase("n"))    {
            scelta=InputData.readString(VNC, false);
        }
        if (scelta.equalsIgnoreCase("n"))   {
            InterfacciaUtente.regole();
        }
    }

    private static void giocatori(){
        int n=0;
        do {
            n=InterfacciaUtente.nGiocatori();
        }while (n >= MIN && n <=MAX);


    }


}
