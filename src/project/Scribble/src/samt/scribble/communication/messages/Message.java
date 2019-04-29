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
package samt.scribble.communication.messages;

import samt.scribble.communication.Commands;

/**
 * La classe astratta Message permette di stabilire un byte di comando e un
 * messaggio fisso da inviare come pacchetto attraverso una connessione UDP.
 *
 * @author Matan Davidi
 * @version 2019-04-16
 */
public abstract class Message {

    /**
     * Il byte di comando che specifica che tipo di messaggio sarà inviato.
     */
    public byte command;

    /**
     * Il messaggio da inviare sotto forma di array di byte.
     */
    public byte[] message;

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
        //Aggiungere controlli sulla validità

        if (isCommandByteValid(command)) {

            this.command = command;

        }

    }

    /**
     * Imposta il valore del campo message.
     *
     * @param message Il messaggio da inviare sotto forma di array di byte.
     */
    private void setMessage(byte[] message) {
        //Aggiungere controlli sulla validità
        this.message = message;
    }

    /**
     * Ritorna l'intero messaggio.
     *
     * @return Un array di byte contenente il messaggio, il cui primo byte è
     * quello di comando e quelli successivi sono il messaggio.
     */
    public byte[] getWholeMessage() {

        byte[] bytes = new byte[this.message.length + 1];
        bytes[0] = this.command;

        for (int i = 0; i < this.message.length; i++) {
            bytes[i + 1] = this.message[i];
        }

        return bytes;

    }

    /**
     * Controlla se un byte di commando è valido e all'interno dei byte di
     * comando disponibili (vedi samt.scribble.communication.Commands).
     *
     * @param command Il byte di comando che specifica che tipo di messaggio
     * sarà inviato.
     * @return true se il byte di comando è valido, false se non lo è.
     */
    private boolean isCommandByteValid(byte command) {

        return command > -1 && command <= Commands.COMMANDS_NUMBER;

    }

}
