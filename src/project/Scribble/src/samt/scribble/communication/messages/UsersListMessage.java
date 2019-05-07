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
package samt.scribble.communication.messages;

import java.io.IOException;
import java.util.List;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.communication.Commands;
import samt.scribble.server.player.Player;

/**
 *
 * @author Matan Davidi
 * @version 1.0 (2019-05-07 - 2019-05-07)
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

            playersArray[playersArrayIndex] = DefaultScribbleParameters.USERS_LIST_SEPARATOR;
            ++playersArrayIndex;
            ++currentPlayerIndex;

        }

        return playersArray;
        
    }

}
