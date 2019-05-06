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

import samt.scribble.DefaultScribbleParameters;
import samt.scribble.communication.messages.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Send scribble message as datagram.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.1 (2019-04-29 - 2019-05-04)
 */
public class MessageSender {

    /**
     * Send scribble message as datagram.
     *
     * @param receiver Address of the receiver of the message.
     * @param port Port of the of the receiver of the message.
     * @param message Message to send.
     * @throws IOException Error while sending the datagram.
     */
    public static void sendMessage(InetAddress receiver, int port, Message message) throws IOException {
        byte[] bytes = message.getWholeMessage();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, receiver, port);
        new DatagramSocket().send(packet);
    }
    
}