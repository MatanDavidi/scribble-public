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
package samt.scribble.server;

import samt.scribble.server.player.PlayerManager;
import samt.scribble.communication.*;
import samt.scribble.server.modules.EchoModule;
import samt.scribble.server.modules.JoinModule;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;

/**
 * Scribble server.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-19)
 */
public class ScribbleServer implements DatagramListener {

    /**
     * Datagram listening thread.
     */
    private ListeningThread listeningThread;

    /**
     * Gestione dei giocatori di scribble.
     */
    private PlayerManager playerManager;

    /**
     * Getsione della connessione con il gruppo multicast.
     */
    private GroupConnection groupConnection;

    /**
     * Crea server scribbe con il l'indirizzo del gruppo multicast.
     *
     * @param groupIp Indirizzo del gruppo multicast.
     * @throws IOException Errore con il gruppo multicast o la recezione di
     * pacchetti.
     */
    public ScribbleServer(InetAddress groupIp) throws IOException {
        this.listeningThread = new ListeningThread(DefaultScribbleParameters.DEFAULT_SERVER_PORT);
        this.listeningThread.addDatagramListener(this);

        this.playerManager = new PlayerManager();

        this.groupConnection = new GroupConnection(groupIp, DefaultScribbleParameters.DEFAULT_GROUP_PORT);
        this.groupConnection.addDatagramListener(this);
    }

    /**
     * Avvia il server multicast.
     */
    public void start() {
        this.listeningThread.start();
        this.groupConnection.start();
    }

    /**
     * Ricevuto messaggio dal gruppo multicast oppure dalla thread di ascolto.
     *
     * @param datagramPacket Messaggio ricevuto.
     */
    @Override
    public void messageReceived(DatagramPacket datagramPacket) {
        byte[] bytes = datagramPacket.getData();
        if (bytes.length > 0) {
            try {
                switch (bytes[0]) {
                    case Commands.ECHO:
                        sendMessage(EchoModule.echo(datagramPacket));
                        break;

                    case Commands.JOIN:
                        sendMessage(JoinModule.join(
                                datagramPacket,
                                this.playerManager,
                                groupConnection));
                        break;
                }
            } catch (IOException ex) {
                if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.ERRORS) {
                    System.out.println("ScribbleServer: " + ex.getMessage());
                }
            }
        }
    }

    /**
     * Invia pacchetto a singolo client.
     *
     * @param packet Pacchetto da inviare.
     * @throws IOException Errore di invio.
     */
    private void sendMessage(DatagramPacket packet) throws IOException {
        new DatagramSocket().send(packet);
    }

    /**
     * Test della classe.
     *
     * @param args Argomenti da line di comando.
     * @throws IOException Errore nel server.
     */
    public static void main(String[] args) throws IOException {
        InetAddress ip = InetAddress.getByName("230.0.0.0");
        ScribbleServer server = new ScribbleServer(ip);
        server.start();
    }
}
