/*
 * The MIT License
 *
 * Copyright 2019 giuliobosco.
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
 * Messaggio di join con il nome del giocatore.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-19)
 */
public class JoinMessage extends Message {

    /**
     * Crea messaggio di join con il nickname del giocatore e la sua porta
     * d'ascolto.
     *
     * @param username Nickname del giocatore.
     * @param port La porta d'ascolto del giocatore.
     */
    public JoinMessage(String username, int port) {
        super(Commands.JOIN, buildMessage(username, port));
    }

    /**
     * Costruisce l'array di byte da mandare assieme a questo messaggio.
     *
     * @param username Il nome dell'utente che si sta registrando al server.
     * @param port La porta di ascolto del client dell'utente che si sta
     * registrando al server.
     * @return Un array di byte contenente il messaggio composto da username
     * convertito, separatore (vedi
     * {@link samt.scribble.DefaultScribbleParameters#COMMAND_MESSAGE_SEPARATOR COMMAND_MESSAGE_SEPARATOR})
     * e un array di 4 byte contenente il valore del parametro port.
     */
    private static byte[] buildMessage(String username, int port) {

        byte[] nickBytes = username.getBytes();
        byte[] portBytes = intToByteArray(port);
        byte[] message = new byte[nickBytes.length + portBytes.length + 1];

        for (int i = 0; i < message.length; ++i) {

            if (i < nickBytes.length) {

                message[i] = nickBytes[i];

            } else if (i == nickBytes.length) {

                message[i] = DefaultScribbleParameters.COMMAND_MESSAGE_SEPARATOR;

            } else {

                message[i] = portBytes[i - nickBytes.length - 1];

            }

        }

        return message;

    }

    /**
     * Converte un il valore di una variabile di tipo intero in un array di 4
     * bytes. Preso da
     * https://stackoverflow.com/questions/2183240/java-integer-to-byte-array
     * Grazie a Grzegorz Oledzki.
     *
     * @param value Il numero da convertire in array di byte.
     * @return Un array di byte che contiene i quattro byte che compongono il
     * numero intero passato come parametro.
     */
    private static byte[] intToByteArray(int value) {

        return new byte[]{
            (byte) (value >>> 24),
            (byte) (value >>> 16),
            (byte) (value >>> 8),
            (byte) value};

    }

    public static void main(String[] args) {

        byte[] testMessage = buildMessage("PAOLONE", Integer.MAX_VALUE);

        for (byte b : testMessage) {

            System.out.println(b);

        }

    }

}
