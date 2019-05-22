/*
 * The MIT License
 *
 * Copyright 2019 SAMT.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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

    public final static byte GAME_IN_PROGRESS = 13;

}
