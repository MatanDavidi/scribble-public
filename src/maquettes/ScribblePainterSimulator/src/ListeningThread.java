/*
 * The MIT License
 *
 * Copyright 2019 jarinaeser and giuliobosco.
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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

/**
 * Listening thread of client drawer simulator, for scribble.
 *
 * @author giuliobosco
 * @version 1.1 (2019-04-03)
 */
public class ListeningThread extends Thread {

    /**
     * Socket port.
     */
    private final static int PORT = 9876;

    /**
     * Multicast socket.
     */
    private static MulticastSocket socket;

    /**
     * Inet group address.
     */
    private InetAddress group;

    /**
     * Point listener.
     * Object to send received points.
     */
    private PointListener pointLister;

    /**
     * Create listening thread of client drawer simulator.
     *
     * @param pointListener Point listener.
     * @throws IOException Error with the multicast socket.
     */
    public ListeningThread(PointListener pointListener) throws IOException {
        this.pointLister = pointListener;
        this.group = InetAddress.getByName("224.0.0.1");
        this.socket = new MulticastSocket(PORT);
        this.socket.joinGroup(group);
        this.start();
    }

    /**
     * Listen for points.
     */
    @Override
    public void run() {
        byte[] buffer = new byte[2];

        DatagramPacket recievedPacket = new DatagramPacket(buffer, buffer.length);
        System.out.println("Started listener on " + socket.getLocalSocketAddress());

        while (!isInterrupted()) {
            try{
                recievedPacket.setData(new byte[buffer.length]);
                socket.receive(recievedPacket);
                System.out.println("Listening Thread: Recieved packet containing: " + Arrays.toString(recievedPacket.getData()));
                if(recievedPacket.getData().length == 2){
                    this.pointLister.pointReceived(recievedPacket.getData());
                }
            }catch(IOException ioe){
                System.out.println("IOException: " + ioe.getMessage());
            }
        }
    }

}
