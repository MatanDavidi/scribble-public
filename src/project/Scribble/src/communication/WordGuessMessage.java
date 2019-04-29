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
 * La classe WordGuessMessage è una sottoclasse di Message che contiene le
 * informazioni relative a un tentativo di indovinare la parola che sta
 * disegnando il disegnatore.
 *
 * Esempio di sottoclasse di Message.
 *
 * @author Matan Davidi
 * @version 2019-04-16
 */
public class WordGuessMessage extends Message {

    /**
     * Istanzia nuovi oggetti di tipo WordGuessMessage con un byte di comando
     * fisso e permettendo di specificare un valore per il campo message.
     *
     * @param message Una stringa contenente il tentativo di indovinare
     * (parola).
     */
    public WordGuessMessage(String message) {

        this(message.getBytes());

    }

    /**
     * Istanzia nuovi oggetti di tipo WordGuessMessage con un byte di comando
     * fisso e permettendo di specificare un valore per il campo message.
     *
     * @param message Il messaggio da inviare sotto forma di array di byte.
     */
    private WordGuessMessage(byte[] message) {

        super(Commands.WORD_GUESS, message);

    }

}
