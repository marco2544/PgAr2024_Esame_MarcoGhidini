package logic;

import it.kibo.fp.lib.InputData;
import presentation.InterfacciaUtente;
import utility.*;
import utility.player.*;

import java.util.*;

import static costant.Costanti.*;

public class GestioneGioco {
    private static int conta=0;
    private static ArrayList<Mazzo> mazzoPescata;
    private static ArrayList<Mazzo> mazzoScarti= new ArrayList<>();
    private static Random random = new Random();
    private static ArrayList<Giocatore> giocatori=new ArrayList<>();
    private static ArrayList<String> ruoli =LeggoXML.leggoruoli();
    private static ArrayList<Arma> armi;
    private static ArrayList<Carta> carte;
    private static ArrayList<String> nomi;
    private static Map<String,Integer> classifica=new HashMap<>();
    private static boolean ultimoRinnegato=false;




    public static void start()  {
        InterfacciaUtente.presentazione();
        regole();
        boolean fine = false;
        int scelt=InputData.readInteger("volete giocare: 1-una singola partita o 2-un torneo?");
        if (scelt==1){
            fine=true;
        }
        giocatori();
        if (scelt==2){

            for (String s : nomi) {
                classifica.put(s, 500);
            }
        }
        do {

            for (String s : nomi) {
                classifica.put(s, classifica.get(s));
            }
            ultimoRinnegato=false;
            creoArmi();
            creoCarte();
            Collections.shuffle(carte);
            creomazzo();
            assegnoGiocatori();
            int i = 0;
            boolean controllo = false;
            do {
                conta++;
                InterfacciaUtente.menu();
                switch (InputData.readInteger("")) {
                    case 1:
                        InterfacciaUtente.infoGiocatori(giocatori, 0);
                        break;
                    case 2:
                        InterfacciaUtente.stampoMano(giocatori.get(i));
                        break;
                    case 3:
                        int d = calcoloDist(i, InterfacciaUtente.sceltaGiocatori(giocatori, i));
                        System.out.println("distanza= " + d);
                        break;
                    case 4:
                        InterfacciaUtente.stampoSalute(giocatori.get(i));
                        break;
                    case 5:
                        if (!controllo) {
                            for (int j = 0; j < giocatori.get(i).getCarteInMano().size(); j++) {
                                if (giocatori.get(i).getCarteInMano().get(j).getNome().equals(TipoCarta.BANG)) {
                                    attacca(i, j);
                                    j = giocatori.get(i).getCarteInMano().size();
                                }
                            }
                            controllo = true;
                        }
                        else {
                            System.out.println("non puoi più attaccare");
                        }
                        break;
                    case 6:
                        int n=0;
                        int nScarti;
                        do {
                            nScarti = InputData.readInteger("quante carte vuoi scartare?");
                        } while (nScarti > giocatori.get(i).getCarteInMano().size());
                        for (int k = 0; k < nScarti; k++) {
                            do {
                                for (int j = 0; j < giocatori.get(i).getCarteInMano().size(); j++) {
                                    System.out.println(i + "  " + giocatori.get(i).getCarteInMano().get(j).getNome() + "\n");
                                }
                                n = InputData.readInteger("quale cara vuoi scartare?");
                            }while (n>=giocatori.get(i).getCarteInMano().size());
                            mazzoScarti.add(giocatori.get(i).getCarteInMano().remove(n));
                        }
                        if (giocatori.get(i).getNomePersonaggio().equalsIgnoreCase("Sid Ketchum")) {
                            if (giocatori.get(i).getPs() < 4) {
                                giocatori.get(i).setPs(giocatori.get(i).getPs() + 1);
                                System.out.println("recupero vita");
                            }
                        }
                        break;
                    case 7:
                        if (i<giocatori.size()-1){
                            i++;
                        }else {
                            i=0;
                        }

                        controllo = false;
                        break;
                    default:
                        System.out.println(VNC);
                }
                if (giocatori.get(i).getNomePersonaggio().equalsIgnoreCase("Suzy Lafayette")) {
                    if (giocatori.get(i).getCarteInMano().isEmpty()) {
                        giocatori.get(i).pesca(mazzoPescata.getFirst());
                    }
                }
                if (mazzoPescata.isEmpty()) {
                    Collections.shuffle(mazzoScarti);
                    mazzoPescata.addAll(mazzoScarti);
                }
            } while (!ControlloVincite());
            if (!fine){
                if (InputData.readInteger("volete giocare: 1-rifioca o 2- basta")==2){
                    fine=true;
                }
            }
        }while (!fine);
        Scrivoxml.scrivoTabella(classifica,conta);
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
        }while (!(n >= MIN && n <=MAX));
        nomi=InterfacciaUtente.chiedoNomi(n);

    }

    private static void assegnoGiocatori(){
        int n=nomi.size();
        ArrayList<Personaggio> personaggi =LeggoXML.leggoPersonaggi();
        giocatori.add(new Rinnegato(personaggi.remove(random.nextInt(personaggi.size())),nomi.remove(random.nextInt(nomi.size()-1)+1)));
        for (int i = 1; i < n-1; i++) {
            if (i<3 || i==4){
                giocatori.add(new FuoriLegge(personaggi.remove(random.nextInt(personaggi.size())),nomi.remove(random.nextInt(nomi.size()-1)+1)));
            }
            if (i==3 || i==5){
                giocatori.add(new Vice(personaggi.remove(random.nextInt(personaggi.size())),nomi.remove(random.nextInt(nomi.size()-1)+1)));
            }

        }
        Collections.shuffle(giocatori);
        giocatori.addFirst(new Sceriffo(personaggi.remove(random.nextInt(personaggi.size())),nomi.getFirst()));
    }
    public static String getRuoli(int n) {
        return ruoli.get(n);
    }

    private static void creomazzo(){

        mazzoPescata=new ArrayList<Mazzo>();
        mazzoPescata.addAll(armi);
        mazzoPescata.addAll(carte);
        Collections.shuffle(mazzoPescata);
    }

    private static void creoArmi(){
        armi=new ArrayList<Arma>();
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



    private static void attacca(int i,int k){
        boolean schivato=false;
        Giocatore attaccato=InterfacciaUtente.sceltaGiocatori(giocatori,i);
        if (giocatori.get(i).getArma().getDistanza()>=calcoloDist(i,attaccato));{
            Mazzo m=giocatori.get(i).getCarteInMano().remove(k);
            mazzoScarti.add(m);
            if (InputData.readString(attaccato.getNikname() + "vuoi usare una carta Mancato? (y/n)", true).equalsIgnoreCase("y")) {
                for (int j = 0; j < attaccato.getCarteInMano().size(); j++) {
                    if (attaccato.getCarteInMano().get(i).getNome().equals(TipoCarta.MANCATO)) {
                        attaccato.getCarteInMano().remove(attaccato.getCarteInMano().get(j));
                        j = giocatori.get(i).getCarteInMano().size();
                        schivato = true;
                    }
                }
            }
            if (!schivato) {
                System.out.println(attaccato.getNikname() + " sei stato colpito");
                attaccato.setPs(attaccato.getPs() - 1);
                if (attaccato.getPs()==0){
                    giocatori.remove(attaccato);
                    if (attaccato.getRuolo().equalsIgnoreCase(GestioneGioco.getRuoli(VICE))){
                        if (giocatori.get(i).getRuolo().equalsIgnoreCase(GestioneGioco.getRuoli(SCERIFFO))){
                            System.out.println("hai eliminato il tuo vice scarta tutte le carte");
                            for (int j = 0; j < giocatori.get(i).getCarteInMano().size(); j++) {
                                mazzoScarti.add(giocatori.get(i).getCarteInMano().removeFirst());
                            }
                        }
                    }
                    if (attaccato.getRuolo().equalsIgnoreCase(GestioneGioco.getRuoli(FUORILEGGE))){
                        for (int j = 0; j < 3; j++) {
                            giocatori.get(i).pesca(mazzoPescata.getFirst());
                        }
                    }
                }
                else if (attaccato.getNomePersonaggio().equalsIgnoreCase("Bart Cassidy")) {
                    attaccato.pesca(mazzoPescata.getFirst());
                }
                else if (attaccato.getNomePersonaggio().equalsIgnoreCase("El Gringo")) {
                    attaccato.pesca(giocatori.get(i).getCarteInMano().get(random.nextInt(giocatori.get(i).getCarteInMano().size())));
                }
            }
        }
    }


    private static boolean ControlloVincite(){

        if (!ceSceriffo()){
            if (ceRinnegato()){
                for (Giocatore giocatore : giocatori) {
                    if (giocatore.getRuolo().equals(GestioneGioco.getRuoli(RINNEGATO))) {
                        if (nomi.size() == 4) {
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 22000);
                        }
                        else if (nomi.size() == 5) {
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 24000);
                        }
                        else if (nomi.size() == 6) {
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 26000);
                        }
                        else if (nomi.size() == 7) {
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 28000);
                        }
                    }
                }
                System.out.println("ha vinto il rinnegato");
            }
            else if (ceFuorilegge()){
                for (Giocatore giocatore : giocatori) {
                    if (giocatore.getRuolo().equals(GestioneGioco.getRuoli(FUORILEGGE))) {
                        if (nomi.size() == 4) {
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 1600);
                        }
                        else if (nomi.size() == 5) {
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 1800);
                        }
                        else if (nomi.size() == 6) {
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 1500);
                        }
                        else if (nomi.size() == 7) {
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 1700);
                        }
                    }
                }
                System.out.println("hanno vinto i fuorilegge");
            }
            return true;
        }
        else if (!ceFuorilegge()){
            if (!ceRinnegato()){
                for (Giocatore giocatore : giocatori) {
                    if (giocatore.getRuolo().equals(GestioneGioco.getRuoli(SCERIFFO)) || giocatore.getRuolo().equals(GestioneGioco.getRuoli(VICE))) {
                        if (nomi.size() == 4) {
                            if (ultimoRinnegato) {
                                classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 250);
                            }
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 1400);
                        }
                        else if (nomi.size() == 5) {
                            if (ultimoRinnegato) {
                                classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 300);
                            }
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 1200);
                        }
                        else if (nomi.size() == 6) {
                            if (ultimoRinnegato) {
                                classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 350);
                            }
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 1600);
                        }
                        else if (nomi.size() == 7) {
                            if (ultimoRinnegato) {
                                classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 450);
                            }
                            classifica.put(giocatore.getNikname(), classifica.get(giocatore.getNikname()) + 1200);
                        }
                    }
                }
                System.out.println("ha vinto la legge");
                return true;
            }
            else if(ceRinnegato()){
                ultimoRinnegato=true;
            }
        }
        return false;
    }
    private static boolean ceSceriffo(){
        for (Giocatore giocatore : giocatori) {
            if (giocatore.getRuolo().equals(GestioneGioco.getRuoli(SCERIFFO))) {
                return true;
            }
        }
        return false;
    }
    private static boolean ceRinnegato(){
        for (Giocatore giocatore : giocatori) {
            if (giocatore.getRuolo().equals(GestioneGioco.getRuoli(RINNEGATO))) {
                return true;
            }
        }
        return false;
    }

    private static boolean ceFuorilegge(){
        for (Giocatore giocatore : giocatori) {
            if (giocatore.getRuolo().equals(GestioneGioco.getRuoli(FUORILEGGE))) {
                return true;
            }
        }
        return false;
    }

    private static int calcoloDist(int i,Giocatore g){
        int dist=0;
        for (int j = i+1; j <giocatori.size(); j++) {
            dist++;
            if (j==i){
                break;
            }
            else if (j==giocatori.size()-1){
                j=0;
            }
        }
        if (dist>giocatori.size()/2){
            dist= giocatori.size()-dist;
        }
        if (g.getNomePersonaggio().equalsIgnoreCase("Paul Regret")){
            dist++;
        }
        if (giocatori.get(i).getNomePersonaggio().equalsIgnoreCase("Rose Doolan")){
            dist--;
        }
        return dist;

    }
    public static ArrayList<Mazzo> getMazzoScarti() {
        return mazzoScarti;
    }
    public static ArrayList<Carta> getCarte() {
        return carte;
    }


    public static ArrayList<String> getNomi() {
        return nomi;
    }
}
