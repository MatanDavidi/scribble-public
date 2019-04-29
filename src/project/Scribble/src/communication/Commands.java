/*
 * The MIT License
 *
 * Copyright 2019 Matan Davidi.
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
package communication;

/**
 * La classe Commands contiene tutti i byte di comando che è possibile inviare
 * assieme a dei pacchetti per specificare il tipo di messaggio che si sta
 * inviando.
 *
 * @author Matan Davidi
 * @version 2019-04-16
 */
public class Commands {

    /**
     * Il numero totale di byte comando definiti.
     */
    public final static short COMMANDS_NUMBER = 7;

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
     * richiesta di registrarsi al server.
     */
    public final static byte LOGIN = 2;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene un
     * riconoscimento che un utente si è registrato.
     */
    public final static byte LOGIN_ACKNOWLEDGEMENT = 3;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene una
     * richiesta di ottenere la lista di utenti attualmente connessi.
     */
    public final static byte USERS_LIST_REQUEST = 4;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene la lista
     * di utenti.
     */
    public final static byte USERS_LIST = 4;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene il
     * disegno fatto dal disegnatore.
     */
    public final static byte DRAWING = 5;

    /**
     * Un byte di comando che segnala che il messaggio inviato contiene un
     * tentativo di indovinare la parola che sta disegnando il disegnatore.
     */
    public final static byte WORD_GUESS = 6;

}
