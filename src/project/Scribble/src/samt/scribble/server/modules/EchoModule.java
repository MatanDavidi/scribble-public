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

package samt.scribble.server.modules;

import samt.scribble.communication.messages.InformationMessage;

import java.net.DatagramPacket;
import samt.scribble.DefaultScribbleParameters;

/**
 * Ritorna al mittente il messaggio che riceve.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-19)
 */
public class EchoModule {

    /**
     * Ritorna al mittene il messaggio che riceve.
     *
     * @param datagramPacket Messaggio ricevuto.
     * @return Messaggio di tipo informazione da ritornare al mittente.
     */
    public static DatagramPacket echo(DatagramPacket datagramPacket) {
        byte[] bytes = new byte[datagramPacket.getData().length - 1];
        for (int i = 1; i < datagramPacket.getData().length; i++) {
            bytes[i - 1] = datagramPacket.getData()[i];
        }
        InformationMessage message = new InformationMessage(bytes);
        return new DatagramPacket(message.getWholeMessage(), message.getWholeMessage().length, datagramPacket.getAddress(), DefaultScribbleParameters.DEFAULT_CLIENT_PORT);
    }

}
