package samt.scribble.communication;

/**
 * La classe Commands contiene tutti i byte di comando che è possibile inviare
 * assieme a dei pacchetti per specificare il tipo di messaggio che si sta
 * inviando.
 *
 * @author MatanDavidi
 * @author giuliobosco
 * @author bryanbeffa
 * @version 1.0.3 (2019-04-29 - 2019-05-08)
 */
public class Commands {

    /**
     * Il numero totale di byte comando definiti.
     */
    public final static short COMMANDS_NUMBER = 14;

    /**
     * Un byte di comando che segnala che il messaggio inviato è di tipo
     * informativo.
     */
    public final static byte INFORMATION = 0;

    /**
     * Un byte di comando che segnala che il messaggio inviato è un errore
     * generale.
     */
    public final static byte GENERAL_ERROR = 1;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene una
     * richiesta di ottenere la lista di utenti attualmente connessi.
     */
    public final static byte USERS_LIST_REQUEST = 2;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene la lista
     * di utenti.
     */
    public final static byte USERS_LIST = 3;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene il
     * disegno fatto dal disegnatore.
     */
    public final static byte DRAWING = 4;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene un
     * tentativo di indovinare la parola che sta disegnando il disegnatore.
     */
    public final static byte WORD_GUESS = 5;

    /**
     * Un byte di comando che segnala che i byte seguenti devono essere
     * rimandati al mittente indietro come sono stati ricevuti.
     */
    public final static byte ECHO = 6;

    /**
     * Un byte di comando che segnala la richiesta di entrare nel gioco
     * scribble. I byte seguenti sono il nickname del giocatore.
     */
    public final static byte JOIN = 7;

    /**
     * Un byte di comando che segnala l'indirizzo del gruppo multicast.
     */
    public final static byte GROUP_ADDRESS_MESSAGE = 8;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene l'ordine
     * di inizio del gioco e che il giocatore che riceve questo messaggio sarà
     * uno degli indovinatori.
     */
    public final static byte START_GUESSER = 9;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene l'ordine
     * di inizio del gioco e che il giocatore che riceve questo messaggio sarà
     * uno dei disegnatori.
     */
    public final static byte START_DRAWER = 10;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene l'ordine
     * di fine del gioco.
     */
    public final static byte END = 11;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene che la
     * parola è stata indovinata.
     */
    public final static byte GUESSED_WORD = 12;

    /**
     * Un byte di comando che segnala che il messagio e una parola indovinata.
     */
    public final static byte WORD_GUESSED = 13;
}
