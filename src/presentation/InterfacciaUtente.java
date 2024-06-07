package presentation;

import it.kibo.fp.lib.InputData;
import utility.player.Giocatore;

import java.util.ArrayList;

import static costant.Costanti.*;
public class InterfacciaUtente {
    public static void presentazione(){
        System.out.println(PRESENTAZIONE);
    }

    public static void regole(){
        System.out.println(REGOLE);
    }

    public static int nGiocatori(){
        return InputData.readInteger(N_GIOCATORI);
    }

    public static void infoGiocatori(ArrayList<Giocatore> g,int i){
         System.out.println(g.get(i).toString() + "\n\n");

    }

    public static ArrayList<String> chiedoNomi(int n){
        ArrayList<String> nomi=new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            nomi.add(InputData.readString("inserisci il tuo nome \n",true));
        }
        return nomi;
    }

    public static void menu(){
        System.out.println(""" 
                1)vedere chi è lo Sceriffo
                2) conoscere le proprie carte, sia in mano che equipaggiate
                3) distanza altro giocatore
                4) PF attuali
                5) attacca
                6) scarta carte
                7) passa
                """);
    }

    public static void stampoMano(Giocatore g){
        System.out.println("carte in mano:");
        for (int i = 0; i < g.getCarteInMano().size(); i++) {

            System.out.println(g.getCarteInMano().get(i).getNome()+"\n");
        }
        System.out.println("arma equipaggiata:");
        System.out.println(g.getArma().getNome()+"\n");
    }

    public static Giocatore sceltaGiocatori(ArrayList<Giocatore> giocatori,int i){
        int s=0;
        do {
            for (int j = 0; j < giocatori.size(); j++) {
                if (j != i) {
                    System.out.println(j + "  " + giocatori.get(j).getNikname() + "   :" + giocatori.get(j).getNomePersonaggio());

                }
            }
            s=InputData.readInteger("scegli il giocatore ");
        } while (!(s>=0 && s< giocatori.size() && s!=i));
        return giocatori.get(s);
    }

    public static void stampoSalute(Giocatore g){
        System.out.println(" la tua via \212 di "+g.getPs());
    }
}
