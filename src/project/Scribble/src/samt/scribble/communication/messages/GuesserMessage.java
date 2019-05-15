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

import samt.scribble.communication.Commands;

/**
 * La classe GuesserMessage è una sottoclasse di Message che definisce che,
 * nella partita che si sta per avviare, l'utente a cui è diretto questo
 * messaggio sarà un indovinatore.
 *
 * @author MatanDavidi
 * @version 1.0.1 (2019-05-08 - 2019-05-16)
 */
public class GuesserMessage extends Message {

    /**
     * Istanzia nuovi oggetti di tipo GuesserMessage definendo un byte di
     * comando di tipo
     * {@link samt.scribble.communication.Commands#START_GUESSER START_GUESSER}
     * e privo di messaggio che segnala a un giocatore che, nella prossima
     * partita, avrà il ruolo di indovinatore.
     */
    public GuesserMessage() {
        super(Commands.START_GUESSER, new byte[0]);
    }

}
