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

package samt.scribble.communication;

import samt.scribble.communication.messages.Message;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Connessione al gruppo multicast di scribble.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-19)
 */
public class GroupConnection extends Thread {

    /**
     * Indirizzo del gruppo multicast.
     */
    private InetAddress groupIp;

    /**
     * Porta del gruppo multicast.
     */
    private int port;

    /**
     * Gruppo multicast.
     */
    private MulticastSocket socket;

    /**
     * Lista di listeners dei pacchetti provenienti dal gruppo.
     */
    private List<DatagramListener> datagramListeners;

    /**
     * Getter per l'indirizzo IP del gruppo multicast.
     *
     * @return Indirizzo IP del gruppo mulicast.
     */
    public InetAddress getGroupIp() {
        return this.groupIp;
    }

    /**
     * Crea la connessione al gruppo, con l'indirizzo IP del gruppo, e la porta.
     *
     * @param groupIp Indirizzo ip del gruppo.
     * @param port    Porta del gruppo.
     * @throws IOException Errore nel multicast socket.
     */
    public GroupConnection(InetAddress groupIp, int port) throws IOException {
        if (!groupIp.isMulticastAddress()) {
            throw new IllegalArgumentException("Indirizzo del gruppo non valido.");
        }

        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("La porta " + port + " non è valida");
        }

        this.groupIp = groupIp;
        this.port = port;
        this.socket = new MulticastSocket(this.port);
        socket.joinGroup(this.groupIp);
        this.datagramListeners = new ArrayList<>();
    }

    /**
     * Aggiungere un listener alla lista dei listeners.
     *
     * @param datagramListener Listener da aggiungere alla lista.
     */
    public void addDatagramListener(DatagramListener datagramListener) {
        this.datagramListeners.add(datagramListener);
    }

    /**
     * Rimuovere un listener dalla lista dei listeners.
     *
     * @param datagramListener Listeners da rimuovere dalla lista.
     */
    public void removeDatagramListener(DatagramListener datagramListener) {
        this.datagramListeners.remove(datagramListener);
    }

    /**
     * Invia a tutti listeners i pacchetti che arrivano sul gruppo.
     */
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                DatagramPacket packet = receive();

                for (DatagramListener datagramListener : this.datagramListeners) {
                    datagramListener.messageReceived(packet);
                }
            }
        } catch (IOException ioe) {
        }
    }

    /**
     * Chiudere la connessione con il gruppo.
     *
     * @throws IOException Errore con il gruppo.
     */
    public void close() throws IOException {
        this.socket.leaveGroup(this.groupIp);
        this.socket.close();
    }

    /**
     * Ricevi un pacchetto dal gruppo multicast.
     *
     * @return Pacchetto ricevuto dal gruppo.
     * @throws IOException Errore durante la recezione di un pacchetto.
     */
    public DatagramPacket receive() throws IOException {
        byte[] buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        this.socket.receive(packet);
        return packet;
    }

    /**
     * Invia un pacchetto al gruppo multicast.
     *
     * @param message Pacchetto da invialre al gruppo.
     * @throws IOException Errore durante l'invio del pacchetto.
     */
    public void send(Message message) throws IOException {
        DatagramPacket packet = new DatagramPacket(message.getWholeMessage(), message.getWholeMessage().length, groupIp, port);
        this.socket.send(packet);
    }

}
