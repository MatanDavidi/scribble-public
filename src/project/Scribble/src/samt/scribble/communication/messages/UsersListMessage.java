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

import java.io.IOException;
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
 * @version 1.0.1 (2019-05-07 - 2019-05-08)
 */
public class UsersListMessage extends Message {

    public UsersListMessage(List<Player> players) throws IOException {
        super(Commands.USERS_LIST, UsersListMessage.playersListToByteArray(players));
    }

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

    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();
        players.add(new Player("paolo", null, 0));
        players.add(new Player("gabriele", null, 0));
        players.add(new Player("giulio", null, 0));
        players.add(new Player("franco", null, 0));

        byte[] playersArray = playersListToByteArray(players);

        List<String> usernames = rebuildUsernames(playersArray);

    }

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
