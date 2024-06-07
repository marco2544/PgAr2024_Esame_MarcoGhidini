package logic;

import it.kibo.fp.lib.InputData;
import presentation.InterfacciaUtente;
import utility.Personaggio;
import utility.player.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import static costant.Costanti.*;

public class GestioneGioco {
    private static Random random = new Random();
    private static ArrayList<Giocatore> giocatori=new ArrayList<Giocatore>();
    private static ArrayList<String> ruoli =LeggoXML.leggoruoli();
    public static void start()  {
        InterfacciaUtente.presentazione();
        regole();
        giocatori();
        System.out.println(giocatori.get(0).getName());
        System.out.println(giocatori.get(2).getName());
        System.out.println(ruoli.get(2));
        InterfacciaUtente.presentoGiocatori(giocatori);
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
        ArrayList<Personaggio> personaggii =LeggoXML.leggoPersonaggi();
        int n=0;
        do {
            n=InterfacciaUtente.nGiocatori();
        }while (!(n >= MIN && n <=MAX));
        giocatori.add(new Rinnegato(personaggii.remove(random.nextInt(personaggii.size()))));
        for (int i = 1; i < n-1; i++) {
            if (i<3 || i==4){
                giocatori.add(new FuoriLegge(personaggii.remove(random.nextInt(personaggii.size()))));
            }
            if (i==3 || i==5){
                giocatori.add(new Vice(personaggii.remove(random.nextInt(personaggii.size()))));
            }

        }
        Collections.shuffle(giocatori);
        giocatori.addFirst(new Sceriffo(personaggii.remove(random.nextInt(personaggii.size()))));
    }

    public static String getRuoli(int n) {
        return ruoli.get(n);
    }
}
