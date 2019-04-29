/*
 * The MIT License
 *
 * Copyright 2019 Matan Davidi.
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
package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

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
    /*
    Add sendPacket
    and receivePacket
     */
}
