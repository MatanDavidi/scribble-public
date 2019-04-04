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

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * Drawer simulator for scribble.
 *
 * @author jarinaser
 * @author giuliobosco
 * @version 1.3 (2019-04-03)
 */
public class DrawerSimulator extends JFrame implements MouseListener, MouseMotionListener {

    /**
     * Send all pixels mode.
     */
    public static final char SEND_ALL_PIXEL_MODE = 'a';

    /**
     * Send single pixel mode
     */
    public static final char SEND_SINGLE_PIXEL_MODE = 'p';

    /**
     * Send pixels mode.
     * Can be 'a' for send all pixels, or 'p' for send single pixel.
     */
    private char mode = 'p';

    /**
     * Port to use for send pixels.
     */
    private final int PORT = 9876;

    /**
     * Multicast socket to send pixels.
     */
    private MulticastSocket socket;

    /**
     * InetAddress for destination of the pixels (MulticastGroup).
     */
    private InetAddress group;

    /**
     * Pixel to send.
     */
    private Point position;


    /**
     * List of the pixels to send when using send all pixels mode.
     */
    private ArrayList<Point> pixelsToDraw;

    /**
     * Create the drawer simulator with the window title.
     *
     * @param title Window title.
     * @throws IOException Error on the multicast socket.
     */
    public DrawerSimulator(String title) throws IOException {
        super(title);
        this.setSize(200, 200);
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.group = InetAddress.getByName("224.0.0.1");
        this.socket = new MulticastSocket(PORT);
        socket.joinGroup(group);
        this.position = new Point(10, 10);
        pixelsToDraw = new ArrayList<Point>();
    }

    /**
     * Paint the point and send the pixel.
     *
     * @param g Graphics of the window.
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(position.x, position.y, 1, 1);
        sendPacket(position);
    }

    /**
     * Send the pixel packet.
     * If using all pixel mode, sends all the pixels, other ways just the single pixel.
     *
     * @param pixel Last pixel to send.
     */
    private void sendPacket(Point pixel) {

        byte[] message;

        if (this.mode == SEND_ALL_PIXEL_MODE) {
            if (!pixelsToDraw.contains(pixel)) {
                pixelsToDraw.add(pixel);
            }

            message = new byte[pixelsToDraw.size() * 2 + 1];

            for (int i = 0; i < message.length; i++) {
                if (i % 2 == 0) {
                    message[i] = (byte) pixelsToDraw.get((int) (i - 1) / 2).x;
                } else {
                    message[i] = (byte) pixelsToDraw.get((int) (i - 1) / 2).y;
                }
            }
            message[message.length - 1] = (byte) 'a';
        } else if (this.mode == SEND_SINGLE_PIXEL_MODE) {
            message = new byte[]{
                    (byte) position.x,
                    (byte) position.y,
                    (byte) 'p'
            };
        } else {
            return;
        }

        try {
            DatagramPacket packet = new DatagramPacket(
                    message,
                    message.length,
                    group,
                    PORT
            );

            socket.send(packet);

        } catch (SocketException se) {
            System.err.println("SocketException: " + se.getMessage());
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    /**
     * Mouse clicked event.
     * Set the position and repaint the frame.
     *
     * @param e Mouse clicked event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        position = e.getPoint();
        repaint();
    }

    /**
     * Mouse pressed event.
     * Set the position and repaint the frame.
     *
     * @param e Mouse pressed event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        position = e.getPoint();
        repaint();
    }

    /**
     * Mouse pressed event (not implemented).
     *
     * @param e Mouse released event.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Mouse entered event (not implemented).
     *
     * @param e Mouse entered event.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Mouse entered event (not implemented).
     *
     * @param e Mouse entered event.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Mouse dragged event.
     * Set the position and repaint the frame.
     *
     * @param e Mouse dragged event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        position = e.getPoint();
        repaint();
    }

    /**
     * Mouse moved event (not implemented).
     *
     * @param e Mouse moved event.
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Main method of the class, create the frame.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DrawerSimulator("My Drawer Simulator").setVisible(true);
                } catch (IOException ex) {
                    System.err.println("IOException: " + ex.getMessage());
                }
            }
        });
    }
}
