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

    public static void presentoGiocatori(ArrayList<Giocatore> g){
        System.out.println();
    }
}
