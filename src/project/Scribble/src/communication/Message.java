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
    protected byte command;

    /**
     * Il messaggio da inviare sotto forma di array di byte.
     */
    protected byte[] message;
    
    protected byte[] buffer;
    
    protected DatagramSocket socket;
    
    protected MulticastSocket multicastSocket;

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
        buffer = new byte[256];

    }

    /**
     * Imposta il valore del campo command.
     *
     * @param command Il byte di comando che specifica che tipo di messaggio
     * sarà inviato.
     */
    private void setCommand(byte command) {
        //Aggiungere controlli sulla validità

        if (command > -1 && command <= Commands.COMMANDS_NUMBER) {

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

    public void sendPacket(InetAddress address, int port) throws IOException {
        
        DatagramPacket packet = new DatagramPacket(buffer, command, address, port);
        socket.send(packet);
        
    }
}
