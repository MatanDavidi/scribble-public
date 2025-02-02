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
package samt.scribble.server;

import java.awt.Point;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ThreadLocalRandom;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.client.game.ScribbleGame;
import samt.scribble.communication.Commands;
import samt.scribble.communication.DatagramListener;
import samt.scribble.communication.GroupConnection;
import samt.scribble.communication.ListeningThread;
import samt.scribble.communication.MessageSender;
import samt.scribble.communication.messages.DrawMessage;
import samt.scribble.communication.messages.DrawerMessage;
import samt.scribble.communication.messages.GuessedWordMessage;
import samt.scribble.communication.messages.GuesserMessage;
import samt.scribble.communication.messages.Message;
import samt.scribble.communication.messages.UsersListMessage;
import samt.scribble.server.modules.DatagramConverter;
import samt.scribble.server.modules.EchoModule;
import samt.scribble.server.modules.GameInProgressModule;
import samt.scribble.server.modules.JoinModule;
import samt.scribble.server.modules.WordManager;
import samt.scribble.server.player.Player;
import samt.scribble.server.player.PlayerManager;

/**
 * Scribble server.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author MatanDavidi
 * @version 1.2.1 (2019-04-19 - 2019-05-21)
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

    /**
     * Gestione della connessione con il gruppo multicast.
     */
    private GroupConnection groupConnection;

    /**
     * Attributo che indica il gestore delle parole.
     */
    private WordManager wordManager;

    /**
     * Classe che definisce i valori dell'interfaccia di gioco (matrice dei
     * punti).
     */
    private ScribbleGame scribbleGame;

    /**
     * Crea server scribble con l'indirizzo del gruppo multicast.
     *
     * @param groupIp Indirizzo del gruppo multicast.
     * @throws IOException Errore con il gruppo multicast o la recezione di
     * pacchetti.
     */
    public ScribbleServer(InetAddress groupIp) throws IOException {
        this.listeningThread = new ListeningThread(DefaultScribbleParameters.DEFAULT_SERVER_PORT);
        this.listeningThread.addDatagramListener(this);
        this.groupConnection = new GroupConnection(groupIp, DefaultScribbleParameters.DEFAULT_GROUP_PORT);
        this.groupConnection.addDatagramListener(this);
        this.playerManager = new PlayerManager();
        this.wordManager = new WordManager(DefaultScribbleParameters.WORDS_DICTIONARY_PATH);
        this.scribbleGame = new ScribbleGame(DefaultScribbleParameters.SCRIBBLE_HEIGHT, DefaultScribbleParameters.SCRIBBLE_WIDTH);
    }

    /**
     * Avvia il server multicast.
     */
    public void start() {
        this.listeningThread.start();
        this.groupConnection.start();
        System.out.println("Server avviato correttamente, attualmente in ascolto sulla porta " + listeningThread.getPort());
    }

    /**
     * Ricezione del messaggio dal gruppo multicast oppure dalla thread di
     * ascolto.
     *
     * @param packet Messaggio ricevuto.
     */
    @Override
    public void messageReceived(DatagramPacket packet) {
        byte[] data = packet.getData();
        if (data.length > 0) {
            try {
                switch (data[0]) {
                    case Commands.ECHO:
                        sendMessage(EchoModule.echo(packet));
                        break;

                    case Commands.JOIN:
                        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
                            System.out.println("Ricevuta richiesta di accesso dall'indirizzo " + packet.getAddress().toString());
                        }
                        DatagramPacket joinPacket = JoinModule.join(
                                packet,
                                this.playerManager,
                                this.groupConnection);
                        if (joinPacket != null) {
                            sendMessage(joinPacket);

                            //Should use GroupConnection here, but I can't manage to do it, so peer-to-peer it is!
//                            this.groupConnection.send(new UsersListMessage(this.playerManager.getPlayers()));
                            UsersListMessage usersListMessage = new UsersListMessage(playerManager.getPlayers());
                            for (Player player : playerManager.getPlayers()) {

                                DatagramPacket usersListPacket = new DatagramPacket(usersListMessage.getWholeMessage(), usersListMessage.getWholeMessage().length, player.getIp(), player.getPort());
                                sendMessage(usersListPacket);

                            }

                            int playersNumber = this.playerManager.getPlayersNumber();
                            if (playersNumber == DefaultScribbleParameters.MINIMUM_PLAYERS_NUMBER) {
                                startGame();
                            } else if (playersNumber > DefaultScribbleParameters.MINIMUM_PLAYERS_NUMBER) {

                                DatagramPacket guesserPacket = GameInProgressModule.addToGameInProgress(packet, groupConnection, scribbleGame.getMatrix());

                                if (guesserPacket != null) {

                                    sendMessage(guesserPacket);

                                }

                            }

                            if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {

                                for (Player player : playerManager.getPlayers()) {

                                    System.out.println(player.getUsername() + " - " + player.getIp().getHostAddress() + ":" + player.getPort());

                                }

                            }
                        }
                        break;

                    case Commands.DRAWING:
                        if (this.playerManager.isRegisteredPlayer(packet.getAddress()) && data.length > 2) {
                            Point point = new Point(data[1], data[2]);
                            this.scribbleGame.setPixel(point);
                            sendMessageToAllPlayers(new DrawMessage(point));
                        }
                        break;

                    case Commands.WORD_GUESS:
                        String attempt = DatagramConverter.dataToString(packet);

                        String[] msgParts = attempt.split(DefaultScribbleParameters.WORD_GUESS_MESSAGE_SEPARATOR);

                        //ricavo le due parti del messaggio
                        String username = msgParts[0].trim();
                        String attemptWord = msgParts[1].trim();

                        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {

                            System.out.println("Ricevuto tentativo di indovinare da parte di " + username + ": " + attemptWord);

                        }

                        // Controllo se il tentativo di indovinare la parola è corretto.
                        if (this.wordManager.isGuessedWord(attemptWord)) {

                            //this.groupConnection.send(new GuessedWordMessage(username, attemptWord));
                            sendMessageToAllPlayers(new GuessedWordMessage(username, attemptWord));
                            playerManager.resetPlayers();
                            if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
                                System.out.println("Giocatori rimossi dalla lista.");
                            }
                        } else {

                            //Informa l'utente che la parola che ha tentato di indovinare non è quella che si sta disegnando.
                            Player sender = playerManager.getPlayerByUsername(username);
                            if (sender != null) {

                                DatagramPacket returnPacket = new DatagramPacket(data, data.length, sender.getIp(), sender.getPort());
                                sendMessage(returnPacket);

                            }

                        }
                }
            } catch (IOException ex) {
                if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.ERRORS) {
                    System.out.println("ScribbleServer: " + ex.getMessage());
                }
            }
        }
    }

    private void sendMessageToAllPlayers(Message message) throws IOException {
        //this.groupConnection.send(new DrawMessage(point));
        for (Player player : playerManager.getPlayers()) {

            MessageSender.sendMessage(player.getIp(), player.getPort(), message);

        }
    }

    /**
     * Metodo che fa partire il gioco occupandosi di scegliere chi disegnerà e
     * la parola da indovinare.
     *
     * @throws IOException eccezione in caso il messaggio non va a buon fine.
     */
    private void startGame() throws IOException {
        int playersAmount = this.playerManager.getPlayersNumber();
        int drawerIndex = ThreadLocalRandom.current().nextInt(playersAmount);
        // Ottengo la parola da indovinare.
        String wordToGuess = this.wordManager.getUniqueNewWord();
        for (int i = 0; i < playersAmount; ++i) {
            Player player = this.playerManager.getPlayers().get(i);
            Message msgToSend;
            if (i == drawerIndex) {
                msgToSend = new DrawerMessage(wordToGuess);
            } else {
                msgToSend = new GuesserMessage();
            }
            MessageSender.sendMessage(player.getIp(), player.getPort(), msgToSend);
        }
        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {

            System.out.println("Iniziato la partita con la parola '" + wordToGuess + "' da indovinare.");

        }
    }

    /**
     * Invio di un pacchetto a un singolo client.
     *
     * @param packet Pacchetto da inviare.
     * @throws IOException Errore di invio.
     */
    private void sendMessage(DatagramPacket packet) throws IOException {
        DatagramSocket sendSocket = new DatagramSocket();
        sendSocket.send(packet);
        sendSocket.close();
    }

    /**
     * Test della classe.
     *
     * @param args Argomenti da linea di comando.
     */
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName(DefaultScribbleParameters.GROUP_ADDRESS);
            new ScribbleServer(ip).start();
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}
