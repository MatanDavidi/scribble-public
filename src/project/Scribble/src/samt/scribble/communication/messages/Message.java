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
 * La classe astratta Message permette di stabilire un byte di comando e un
 * messaggio fisso da inviare come pacchetto attraverso una connessione UDP.
 *
 * @author MatanDavidi
 * @author gabrialessi
 * @version 1.0.2 (2019-04-16 - 2019-05-12)
 */
public abstract class Message {

    /**
     * Il byte di comando che specifica che tipo di messaggio sarà inviato.
     */
    protected byte command;

    /**
     * Il messaggio da inviare sotto forma di array di byte.
     */
    protected byte[] message;

    /**
     * Istanzia nuovi oggetti di tipo Message, permettendo di assegnare un
     * valore ai campi command e message.
     *
     * @param command Il byte di comando che specifica che tipo di messaggio
     * sarà inviato.
     * @param message Il messaggio da inviare sotto forma di array di byte.
     */
    public Message(byte command, byte[] message) {
        setCommand(command);
        setMessage(message);
    }

    /**
     * Imposta il valore del campo command.
     *
     * @param command Il byte di comando che specifica che tipo di messaggio
     * sarà inviato.
     */
    private void setCommand(byte command) {
        if (isCommandValid(command)) {
            this.command = command;
        }
    }

    /**
     * Imposta il valore del campo message.
     *
     * @param message Il messaggio da inviare sotto forma di array di byte.
     */
    private void setMessage(byte[] message) {
        if (isMessageValid(message)) {
            this.message = message;
        }
    }

    /**
     * Ritorna il byte di comando.
     *
     * @return Byte di comando.
     */
    public byte getCommand() {
        return this.command;
    }

    /**
     * Ritorna il messaggio da inviare.
     *
     * @return Il messaggio.
     */
    public byte[] getMessage() {
        return this.message;
    }

    /**
     * Ritorna l'intero messaggio.
     *
     * @return Un array di byte contenente il messaggio, il cui primo byte è
     * quello di comando e quelli successivi sono il messaggio.
     */
    public byte[] getWholeMessage() {
        int length = getMessage().length;
        byte[] bytes = new byte[length + 1];
        bytes[0] = getCommand();
        for (int i = 0; i < length; i++) {
            bytes[i + 1] = getMessage()[i];
        }
        return bytes;
    }

    /**
     * Controlla se un byte di commando è valido e all'interno dei byte di
     * comando disponibili.
     *
     * @see samt.scribble.communication.Commands
     * @param command Il byte di comando che specifica che tipo di messaggio
     * sarà inviato.
     * @return true se il byte di comando è valido, false se non lo è.
     */
    private boolean isCommandValid(byte command) {
        return command > -1 && command <= Commands.COMMANDS_NUMBER;
    }

    /**
     * Controllo della validità del messaggio.
     *
     * @param message Messaggio da inviare.
     * @return Se il messaggio è valido o meno.
     */
    private boolean isMessageValid(byte[] message) {
        return message.length > -1;
    }

}
