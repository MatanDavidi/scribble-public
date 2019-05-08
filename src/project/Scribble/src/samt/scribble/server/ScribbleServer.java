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
import samt.scribble.client.game.PlayerRole;
import samt.scribble.communication.messages.DrawerMessage;
import samt.scribble.communication.messages.GuesserMessage;
import samt.scribble.communication.messages.Message;
import samt.scribble.communication.messages.UsersListMessage;
import samt.scribble.communication.messages.WordGuessMessage;
import samt.scribble.server.modules.WordManager;
import samt.scribble.server.player.Player;
import samt.scribble.server.modules.WordModule;

/**
 * Scribble server.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-19)
 */
public class ScribbleServer implements DatagramListener {

    /**
     * Thread di ascolto dei pacchetti.
     */
    private ListeningThread listeningThread;

    /**
     * Gestione dei giocatori di scribble.
     */
    private PlayerManager playerManager;

    private WordManager wordManager;

    /**
     * Getsione della connessione con il gruppo multicast.
     */
    private GroupConnection groupConnection;

    /**
     * Attributo che indica il gestore delle parole.
     */
    private WordManager wManager;

    /**
     * Crea server scribbe con il l'indirizzo del gruppo multicast.
     *
     * @param groupIp Indirizzo del gruppo multicast.
     * @throws IOException Errore con il gruppo multicast o la recezione di
     * pacchetti.
     */
    public ScribbleServer(InetAddress groupIp) throws IOException {
        this.wManager = new WordManager(DefaultScribbleParameters.WORDS_DICTIONARY_PATH);
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

                        int playersNumber = playerManager.getPlayersNumber();
                        if (playersNumber == DefaultScribbleParameters.MINIMUM_PLAYERS_NUMBER) {

                            startGame();

                        }
                        groupConnection.send(new UsersListMessage(playerManager.getPlayers()));
                        break;

                    case Commands.WORD_GUESS:
                        String userWord = WordModule.guessed(datagramPacket);

                        //controllo se il tentativo di indovinare la parola è corretto
                        if (wManager.isGuessedWord(userWord)) {

                            //ricavo lo username del player che ha indovinato
                            InetAddress ip = datagramPacket.getAddress();
                            int port = datagramPacket.getPort();
                            String username = playerManager.getUsernameByAddress(ip, port);

                            groupConnection.send(new WordGuessMessage(username));
                        }
                }
            } catch (IOException ex) {
                if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.ERRORS) {
                    System.out.println("ScribbleServer: " + ex.getMessage());
                }
            }
        }
    }

    private void startGame() throws IOException {

        int playersNumber = playerManager.getPlayersNumber();
        int drawerIndex = (int) (Math.random() * playersNumber);

        //ottengo la parola da indovinare
        String wordToGuess = wManager.getUniqueNewWord();

        for (int i = 0; i < playersNumber; ++i) {

            Player player = playerManager.getPlayers().get(i);

            Message msgToSend;

            if (i == drawerIndex) {

                msgToSend = new DrawerMessage(wordToGuess);

            } else {

                msgToSend = new GuesserMessage();

            }

            MessageSender.sendMessage(player.getIp(), player.getPort(), msgToSend);

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
     */
    public static void main(String[] args){
        try{
            InetAddress ip = InetAddress.getByName(DefaultScribbleParameters.GROUP_ADDRESS);
            ScribbleServer server = new ScribbleServer(ip);
            server.start();
        }catch(IOException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
