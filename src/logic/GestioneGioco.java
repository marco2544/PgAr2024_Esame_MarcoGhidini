package logic;

import it.kibo.fp.lib.InputData;
import presentation.InterfacciaUtente;
import utility.*;
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
    private static ArrayList<Arma> armi;
    private static ArrayList<Carta> carte;


    public static void start()  {
        InterfacciaUtente.presentazione();
        regole();
        giocatori();
        creomazzo();
        System.out.println(mazzoPescata.size());
        int i = 0;
        do {
            InterfacciaUtente.menu();
            switch (InputData.readInteger("")){
                case 1:
                    InterfacciaUtente.infoGiocatori(giocatori,0);
                    break;
                case 2:
                    InterfacciaUtente.stampoMano(giocatori.get(i));
                    break;
                case 3:
                    Giocatore g=InterfacciaUtente.sceltaGiocatori(giocatori,i);
                    int dist=0;
                    for (int j = i; j <giocatori.size(); j++) {
                        dist++;
                        if (giocatori.get(j).equals(g)){
                            j=giocatori.size()*10;
                        }
                        if (j==giocatori.size()-1){
                            j=0;
                        }
                    }
                    System.out.println("distanza= "+dist);
                    break;
                case 4:
                    InterfacciaUtente.stampoSalute(giocatori.get(i));
                    break;
                case 5:
                    break;
                case 6:
                    i++;
                    break;
                default:
                    System.out.println(VNC);
            }
        }while (!ControlloVincite());
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
        creoArmi();
        creoCarte();
        mazzoPescata=new ArrayList<Mazzo>();
        mazzoPescata.addAll(armi);
        mazzoPescata.addAll(carte);
        Collections.shuffle(mazzoPescata);
    }

    private static void creoArmi(){
        for (int i=0; i<3;i++){
            armi.add(new Arma("Schofield",2));
        }
        for (int i=0; i<1;i++){
            armi.add(new Arma("Remington",3));
        }
        for (int i=0; i<1;i++){
            armi.add(new Arma("Rev. Carabine",4));
        }
        for (int i=0; i<1;i++){
            armi.add(new Arma("Winchester",5));
        }
    }
    private static void creoCarte(){
        carte=new ArrayList<Carta>();
        for(int i=0; i<50;i++){
            carte.add(new Carta(TipoCarta.BANG));
        }
        for (int i=0; i<24;i++){
            carte.add(new Carta(TipoCarta.MANCATO));
        }
    }

    private static boolean ControlloVincite(){
        return false;
    }
}
