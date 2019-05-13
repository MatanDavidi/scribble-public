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
package samt.scribble.communication;

import samt.scribble.communication.messages.Message;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;

/**
 * Connessione al gruppo multicast di scribble.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author gabrialessi
 * @version 1.0.1 (2019-05-13)
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
     * Connessione del gruppo multicast.
     */
    private MulticastSocket socket;

    /**
     * Lista di listeners dei pacchetti provenienti dal gruppo.
     */
    private List<DatagramListener> listeners;

    /**
     * Crea la connessione al gruppo, con l'indirizzo IP e la porta.
     *
     * @param groupIp Indirizzo IP del gruppo.
     * @param port Porta del gruppo.
     * @throws IOException Errore nel multicast socket.
     */
    public GroupConnection(InetAddress groupIp, int port) throws IOException {
        setGroupIp(groupIp);
        setPort(port);
        this.socket = new MulticastSocket(this.port);
        this.socket.joinGroup(getGroupIp());
        this.listeners = new ArrayList<>();
    }

    /**
     * Getter per l'indirizzo IP del gruppo multicast.
     *
     * @return Indirizzo IP del gruppo mulicast.
     */
    public InetAddress getGroupIp() {
        return this.groupIp;
    }

    /**
     * Imposta l'indirizzo del gruppo.
     *
     * @param groupIp Indirizzo da impostare.
     * @throws IllegalArgumentException Se l'indirizzo non è valido.
     */
    private void setGroupIp(InetAddress groupIp) throws IllegalArgumentException {
        if (groupIp.isMulticastAddress()) {
            this.groupIp = groupIp;
        } else {
            throw new IllegalArgumentException("Indirizzo del gruppo non valido.");
        }
    }

    /**
     * Metodo che imposta la porta di ascolto.
     *
     * @param port La porta da impostare alla connessione.
     * @throws IllegalArgumentException Porta passata non valida.
     */
    private void setPort(int port) throws IllegalArgumentException {
        if (port > -1 && port < 65536) {
            this.port = port;
        } else {
            String message = "Porta '" + port + "' fuori dal range valido (0-65535).";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Aggiungere un listener alla lista dei listeners.
     *
     * @param listener Listener da aggiungere alla lista.
     */
    public void addDatagramListener(DatagramListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Rimuovere un listener dalla lista dei listeners.
     *
     * @param listener Listeners da rimuovere dalla lista.
     */
    public void removeDatagramListener(DatagramListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Invia a tutti listeners i pacchetti che arrivano sul gruppo.
     */
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                DatagramPacket packet = receive();
                for (DatagramListener listener : this.listeners) {
                    listener.messageReceived(packet);
                }
            }
            if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
                System.out.println("ListeningThread '" + getId() + "' interrotta con successo.");
            }
        } catch (IOException ex) {
            if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.ERRORS) {
                System.out.println("ListeningThread '" + getId() + "': " + ex.getMessage());
            }
        }
    }

    /**
     * Chiudere la connessione con il gruppo.
     *
     * @throws IOException Errore con il gruppo.
     */
    public void close() throws IOException {
        this.socket.leaveGroup(getGroupIp());
        this.socket.close();
    }

    /**
     * Ricezione di un pacchetto dal gruppo multicast.
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
     * Invio di un pacchetto al gruppo multicast.
     *
     * @param message Pacchetto da inviare al gruppo.
     * @throws IOException Errore durante l'invio del pacchetto.
     */
    public void send(Message message) throws IOException {
        DatagramPacket packet = new DatagramPacket(message.getWholeMessage(), message.getWholeMessage().length, getGroupIp(), this.port);
        this.socket.send(packet);
    }

}
