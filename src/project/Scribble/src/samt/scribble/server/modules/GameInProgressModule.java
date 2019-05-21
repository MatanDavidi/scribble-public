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
package samt.scribble.server.modules;

import java.net.DatagramPacket;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.communication.GroupConnection;
import samt.scribble.communication.messages.JoinGameInProgressMessage;
import samt.scribble.communication.messages.JoinMessage;

/**
 * La classe GameInProgressModule è un modulo utilizzato dal server quando una
 * partita è già in corso.
 *
 * @author MatanDavidi
 * @version 1.0 (2019-05-20 - 2019-05-21)
 */
public class GameInProgressModule {

    public static DatagramPacket addToGameInProgress(DatagramPacket packetSent, GroupConnection groupConnection, boolean[][] matrix) {

        byte[] data = packetSent.getData();
        boolean isUsernamePart = true;
        int port = -1;

        for (int i = 0; i < data.length; i++) {

            if (data[i] != DefaultScribbleParameters.COMMAND_MESSAGE_SEPARATOR) {

                if (!isUsernamePart) {

                    byte[] portBytes = new byte[]{
                        data[i],
                        data[i + 1],
                        data[i + 2],
                        data[i + 3]};
                    port = JoinMessage.byteArrayToInt(portBytes);
                    break;

                }

            } else {

                isUsernamePart = false;

            }

        }

        if (port != -1) {

            JoinGameInProgressMessage guesserMessage = new JoinGameInProgressMessage(groupConnection.getGroupIp(), matrix);
            return new DatagramPacket(
                    guesserMessage.getWholeMessage(),
                    guesserMessage.getWholeMessage().length,
                    packetSent.getAddress(),
                    port);
        } else {
            return null;
        }

    }

}
