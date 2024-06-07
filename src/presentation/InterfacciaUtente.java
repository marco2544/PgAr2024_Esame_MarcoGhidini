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
}
