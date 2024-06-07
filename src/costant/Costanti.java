package costant;

public class Costanti {
    //costanti logiche
    public final static int PS=4;
    public final static int PS_SCERIFFO=PS+1;
    public final static int MIN=4;
    public final static int MAX=7;

    //costanti di interfaccia
    public final static String PRESENTAZIONE= """
            benvenuti giocatori......
            
            
            conoscete le regole? (y/n)""";// da finire non so cosa scrivere
    public final static String REGOLE= """
            Ogni giocatore parte da 4 punti ferita (PF), fatta eccezione per lo Sceriffo che ne ha\s
            sempre 1 in più. Essi indicheranno sia le vite del giocatore ma anche quante carte esso
            potrà tenere in mano alla fine del suo turno. Pe cominciare comincerete avendo in mano
            tante carte quanti sono i vostri PF.
            Il primo a cominciare sarà lo Sceriffo e si proseguirà poi in senso orario con i turni
            degli altri giocatori. Un turno si svolge in 3 fasi:
            1. Pescare due carte
            Pesca le prime due carte dalla cima del mazzo. Quando il mazzo finisce, rimescola
            gli scarti per formare un nuovo mazzo da cui pescare.
            2. Giocare carte
            Puoi giocare carte a tuo vantaggio, o contro gli altri, cercando di eliminare gli
            avversari. Puoi giocare carte solo durante il tuo turno (eccezione: Mancato!, vedi
            oltre). Non sei obbligato a giocare carte in questa fase o puoi giocare quante carte
            vuoi; hai solo 2 limiti:
            • puoi giocare solo 1 carta BANG! per turno;
            • puoi avere in gioco solo 1 arma.
            (quando giochi una nuova arma, scarta sempre la precedente)
            3. Scartare le carte in eccesso
            Finita la seconda fase, scarta dalla tua mano eventuali carte in eccesso. Ricorda che
            il numero massimo di carte che puoi avere in mano, alla fine del tuo turno,
            è pari ai tuoi punti ferita correnti (cioè alle tue pallottole). A questo punto il
            turno è finito e il gioco prosegue in senso orario.
            All’interno del gioco troverete due tipi di carte, che sono:
            • Le carte a bordo marrone, dette “Gioca e Scarta”, che vengono scartate dalla
            propria mano e messe nella pila degli scarti, applicando i rispettivi effetti.
            • Le carte a bordo blu, dette “Equipaggiabili”, che verranno posizionate davanti a
            se stessi per poter beneficiare dei loro effetti.

            -BANG!: La carta BANG! è il modo principale per togliere un PF
            ad un giocatore.

            -Mancato!: Quando sei colpito da un BANG! puoi giocare subito,
            quindi fuori dal tuo turno, un Mancato! per annullare il colpo; se non
            lo fai, perdi un PF. Quando finisci i PF sei eliminato dal gioco. Puoi
            annullare solo i BANG! diretti a te, non quelli diretti agli altri. La
            carta BANG! è comunque scartata, anche se annullata.

            Inoltre all’interno del mazzo avremmo 4 armi, che consentiranno di sparare un BANG!
            ad una distanza maggiore, esse sono:
            3 Schofield,
            1 Remington,
            1 Rev. Carabine
            1 Winchester.
            74 “Gioca e Scarta”: 50 BANG! e 24 Mancato!.""";
    public final static String VNC="comando non valido reinseriscilo\n";
    public final static String N_GIOCATORI="in quanti giocatori siete (max "+MAX+" - min "+MIN+")\n";

}
