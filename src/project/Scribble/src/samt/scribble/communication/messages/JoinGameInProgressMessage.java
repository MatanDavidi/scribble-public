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

import samt.scribble.communication.Commands;

/* 
 *
 * @author MatanDavidi
 * @version 1.0 (2019-05-20 - 2019-05-20)
 */
public class JoinGameInProgressMessage extends Message {

    public JoinGameInProgressMessage(boolean[][] matrix) {
        super(Commands.GAME_IN_PROGRESS, JoinGameInProgressMessage.booleanMatrixToByteArray(matrix));
    }

    public static byte[] booleanMatrixToByteArray(boolean[][] matrix) {

        int messageSize = 0;
        int messageIndex = 0;

        for (boolean[] row : matrix) {

            for (boolean pixel : row) {

                if (pixel) {

                    ++messageSize;

                }

            }

        }

        byte[] message = new byte[messageSize];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                int currentIndex = i * matrix.length + j;

                if (matrix[i][j] && currentIndex <= Byte.MAX_VALUE) {

                    message[messageIndex] = (byte) currentIndex;
                    ++messageIndex;

                }

            }

        }

        return message;

    }


    }

}
