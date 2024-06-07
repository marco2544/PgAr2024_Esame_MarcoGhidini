package logic;

import it.kibo.fp.lib.InputData;
import presentation.InterfacciaUtente;
import utility.Arma;
import utility.Carta;
import utility.Mazzo;
import utility.Personaggio;
import utility.player.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static costant.Costanti.*;

public class GestioneGioco {
    private static ArrayList<Mazzo> mazzoPescata;
    private static ArrayList<Mazzo> mazzoScarti=new ArrayList<Mazzo>();
    private static Random random = new Random();
    private static ArrayList<Giocatore> giocatori=new ArrayList<Giocatore>();
    private static ArrayList<String> ruoli =LeggoXML.leggoruoli();


    public static void start()  {
        InterfacciaUtente.presentazione();
        regole();
        giocatori();
        creomazzo();
        System.out.println(mazzoPescata.get(10));
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
        ArrayList<String> nomi;
        int n=0;
        do {
            n=InterfacciaUtente.nGiocatori();
        }while (!(n >= MIN && n <=MAX));
        nomi=InterfacciaUtente.chiedoNomi(n);
        giocatori.add(new Rinnegato(personaggii.remove(random.nextInt(personaggii.size())),nomi.remove(random.nextInt(nomi.size()-1)+1)));
        for (int i = 1; i < n-1; i++) {
            if (i<3 || i==4){
                giocatori.add(new FuoriLegge(personaggii.remove(random.nextInt(personaggii.size())),nomi.remove(random.nextInt(nomi.size()-1)+1)));
            }
            if (i==3 || i==5){
                giocatori.add(new Vice(personaggii.remove(random.nextInt(personaggii.size())),nomi.remove(random.nextInt(nomi.size()-1)+1)));
            }

        }
        Collections.shuffle(giocatori);
        giocatori.addFirst(new Sceriffo(personaggii.remove(random.nextInt(personaggii.size())),nomi.getFirst()));
    }

    public static String getRuoli(int n) {
        return ruoli.get(n);
    }

    private static void creomazzo(){
        ArrayList<Arma> armi=LeggoXML.leggoArmi();
        ArrayList<Carta> carte=LeggoXML.leggoCarte();
        mazzoPescata=new ArrayList<Mazzo>();
        mazzoPescata.addAll(armi);
        mazzoPescata.addAll(carte);
        Collections.shuffle(mazzoPescata);
    }
}
