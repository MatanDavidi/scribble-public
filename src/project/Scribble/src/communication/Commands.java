/*
 * Copyright (C) 2019 Matan Davidi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
