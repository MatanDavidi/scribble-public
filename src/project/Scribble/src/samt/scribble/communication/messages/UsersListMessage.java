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

import java.util.ArrayList;
import java.util.List;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.communication.Commands;
import samt.scribble.server.player.Player;

/**
 * La classe UsersListMessage è una sottoclasse di Message che contiene la lista
 * di utenti connessi attualmente al server.
 *
 * @author MatanDavidi
 * @version 1.0.2 (2019-05-07 - 2019-05-16)
 */
public class UsersListMessage extends Message {

    /**
     * Istanzia nuovi oggetti di tipo UsersListMessage con un byte di comando di
     * tipo {@link samt.scribble.communication.Commands#USERS_LIST USERS_LIST} e
     * una lista di nomi utente dei giocatori come messaggio.
     *
     * @param players La lista dei giocatori attualmente connessi al server.
     */
    public UsersListMessage(List<Player> players) {
        super(Commands.USERS_LIST, UsersListMessage.playersListToByteArray(players));
    }

    /**
     * Converte una lista di oggetti di tipo
     * {@link samt.scribble.server.player.Player Player} in un array di byte.
     *
     * @param players La lista di Player da convertire.
     * @return Un array di byte contenente i nomi utente dei giocatori contenuti
     * nella lista.
     */
    public static byte[] playersListToByteArray(List<Player> players) {
        int currentPlayerIndex = 0;
        int playersArrayIndex = 0;
        byte[] playersArray = new byte[256];
        while (currentPlayerIndex < players.size()) {
            Player player = players.get(currentPlayerIndex);
            for (byte usernameByte : player.getUsername().getBytes()) {
                playersArray[playersArrayIndex] = usernameByte;
                ++playersArrayIndex;
            }
            playersArray[playersArrayIndex] = DefaultScribbleParameters.COMMAND_MESSAGE_SEPARATOR;
            ++playersArrayIndex;
            ++currentPlayerIndex;
        }
        return playersArray;
    }

    /**
     * Verifica il corretto funzionamento della classe UsersListMessage.
     *
     * @param args Gli argomenti passati da linea di comando.
     */
    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();
        players.add(new Player("paolo", null, 0));
        players.add(new Player("gabriele", null, 0));
        players.add(new Player("giulio", null, 0));
        players.add(new Player("franco", null, 0));

        byte[] playersArray = playersListToByteArray(players);

        List<String> usernames = rebuildUsernames(playersArray);

    }

    /**
     * Converte un array di byte in una lista di stringhe, rappresentanti i nomi
     * utente degli oggetti di tipo
     * {@link samt.scribble.server.player.Player Player} contenuti in una lista
     * convertita in array di byte tramite il metodo
     * {@link samt.scribble.communication.messages.UsersListMessage#playersListToByteArray(java.util.List) playersListToByteArray}.
     *
     * @param packetData L'array di byte da convertire.
     * @return Una lista di stringhe, ognuna il nome utente di un giocatore.
     */
    private static List<String> rebuildUsernames(byte[] packetData) {

        List<String> usernames = new ArrayList<>();

        StringBuilder username = new StringBuilder();

        for (int i = 0; i < packetData.length; ++i) {

            if (packetData[i] == DefaultScribbleParameters.COMMAND_MESSAGE_SEPARATOR) {

                usernames.add(username.toString());
                username.setLength(0);

            } else {

                username.append((char) packetData[i]);

            }

        }

        return usernames;

    }

}
