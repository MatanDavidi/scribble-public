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
package samt.scribble.communication.messages;

import samt.scribble.DefaultScribbleParameters;
import samt.scribble.communication.Commands;

/**
 * La classe WordGuessMessage è una sottoclasse di Message che contiene le
 * informazioni relative a un tentativo di indovinare la parola che sta
 * disegnando il disegnatore.
 *
 * @author MatanDavidi
 * @version 1.2 (2019-04-16 - 2019-05-19)
 */
public class WordGuessMessage extends Message {

    /**
     * Istanzia nuovi oggetti di tipo WordGuessMessage con un byte di comando
     * fisso e permettendo di specificare la parola che si pensa stia essendo
     * disegnata.
     *
     * @param username Il nome dell'utente che sta tentando di indovinare la
     * parola.
     * @param word Il tentativo di indovinare la parola, ovvero quello che si
     * pensa che il disegnatore stia disegnando in questo momento.
     */
    public WordGuessMessage(String username, String word) {
        super(Commands.WORD_GUESS, WordGuessMessage.buildMessage(username, word));
    }

    /**
     * Costruisce il messaggio da associare a quest'istanza di WordGuessMessage.
     *
     * @param username Il nome dell'utente che sta tentando di indovinare la
     * parola.
     * @param word Il tentativo dell'utente di indovinare la parola.
     * @return Un array di byte contenente in ordine il nome utente, il
     * separatore e la parola.
     */
    private static byte[] buildMessage(String username, String word) {

        return (username + DefaultScribbleParameters.MESSAGE_SEPARATOR + word).getBytes();

    }

}
