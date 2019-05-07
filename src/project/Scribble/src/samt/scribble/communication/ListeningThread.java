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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;

/**
 * Thread di ascolto
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author MatanDavidi
 * @version 1.1 (2019-04-19 - 2019-05-06)
 */
public class ListeningThread extends Thread {

    /**
     * Porta logica del socket di ascolto.
     */
    private int port;

    /**
     * Lista dei datagram listeners, a cui viene inviato il pacchetto ricevuto.
     */
    private List<DatagramListener> datagramListeners;

    /**
     * Getter per la porta logica del socket di ascolto.
     *
     * @return Porta logica del socket di ascolto.
     */
    public int getPort() {
        return this.port;
    }

    private DatagramSocket datagramSocket;

    /**
     * Aggiunge un datagram listener, alla lista dei datagram listeners, a cui
     * viene inviato il pacchetto ricevuto.
     *
     * @param datagramListener Datagram listener da aggiungere alla lista.
     */
    public void addDatagramListener(DatagramListener datagramListener) {
        this.datagramListeners.add(datagramListener);
    }

    /**
     * Crea la thread di ascolto con la porta logica di ascolto.
     *
     * @param port Porta logica del socket di ascolto.
     */
    public ListeningThread(int port) throws IllegalArgumentException {
        if (port > 0 && port < 65536) {
            this.port = port;
            this.datagramListeners = new ArrayList<>();
        } else {
            String message = "Port " + port + " out of valid range (valid range: 1-65535).";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Crea il socket di ascolto ed invia ogni pacchetto ricevuto a tutta la
     * lista dei listeners.
     */
    @Override
    public void run() {
        try {
            datagramSocket = new DatagramSocket(this.getPort());

            while (!isInterrupted()) {
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                datagramSocket.receive(packet);

                for (int i = 0; i < datagramListeners.size(); ++i) {
                    datagramListeners.get(i).messageReceived(packet);
                }
            }

            if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {

                System.out.println("ListeningThread " + getId() + " interrotta con successo.");

            }

        } catch (IOException ioe) {

            if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.ERRORS) {

                System.out.println("ListeningThread " + getId() + ": " + ioe.getMessage());

            }

        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        datagramSocket.close();
    }
}
