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
 * La classe WordGuessMessage è una sottoclasse di Message che contiene le
 * informazioni relative a un tentativo di indovinare la parola che sta
 * disegnando il disegnatore.
 *
 * Esempio di sottoclasse di Message.
 *
 * @author MatanDavidi
 * @version 1.0 (2019-04-16 - 2019-05-06)
 */
public class WordGuessMessage extends Message {

    /**
     * Istanzia nuovi oggetti di tipo WordGuessMessage con un byte di comando
     * fisso e permettendo di specificare un valore per il campo message.
     *
     * @param message Il messaggio da inviare sotto forma di array di byte.
     */
    public WordGuessMessage(String message) {
        super(Commands.WORD_GUESS, message.getBytes());
    }

}
