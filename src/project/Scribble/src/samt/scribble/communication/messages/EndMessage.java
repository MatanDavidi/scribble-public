/*
 * The MIT License
 *
 * Copyright 2019 Nemanja Stojanovic.
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
 * La classe EndMessage � una sottoclasse di Message dedicata a segnalare
 * la fine di una partita.
 *
 * @author Nemanja Stojanovic
 * @version 1.1 (08.05.2019)
 */
public class EndMessage extends Message {
    /**
     * Istanzia un nuovo oggetto di tipo EndMessage utilizzando come byte di
     * comando {@link samt.scribble.communication.Commands#END END} e un array di byte vuoto.
     * 
     *
     */
    public EndMessage() {
        super(Commands.END, new byte[0]);
    }

}
