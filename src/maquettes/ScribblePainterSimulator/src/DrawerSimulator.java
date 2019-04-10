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
     * Port to use for send pixels.
     */
    private final int PORT = 9876;
    
    /**
     * Size in pixels of the window x and y axis.
     */
    private final int SIZE = 256;

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
    private Point pixelToSend;
    
    /**
     * Boolean matrix that represents the status of each pixel.
     * true = On
     * false = Off
     */
    private boolean[][] pixelsStatus;

    /**
     * Create the drawer simulator with the window title.
     *
     * @param title Window title.
     * @throws IOException Error on the multicast socket.
     */
    public DrawerSimulator(String title) throws IOException {
        super(title);
        this.setSize(this.SIZE, this.SIZE);
        this.setPreferredSize(new Dimension(this.SIZE, this.SIZE));
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.pixelsStatus = new boolean[256][256];
        this.group = InetAddress.getByName("224.0.0.1");
        this.socket = new MulticastSocket(PORT);
        socket.joinGroup(group);
    }
    
    /**
     * Getter method for the pixelStatus array.
     * @return pixelStatus array containing the state of all pixels.
     */
    public boolean[][] getPixelStatus(){
        return this.pixelsStatus;
    }

    /**
     * Paint the point and send the pixel.
     *
     * @param g Graphics of the window.
     */
    @Override
    public void paint(Graphics g){
        if(pixelToSend != null){
            if(pixelToSend.x > -1 && pixelToSend.x < 256 && pixelToSend.y > -1 && pixelToSend.y < 256){
                g.fillRect(pixelToSend.x, pixelToSend.y, 1, 1);
                if(!pixelsStatus[pixelToSend.x][pixelToSend.y]){
                    pixelsStatus[pixelToSend.x][pixelToSend.y] = true;
                    sendPacket(pixelToSend);
                }
            }
        }
    }

    /**
     * Send the pixel packet.
     * If using all pixel mode, sends all the pixels, other ways just the single pixel.
     *
     * @param pixel Last pixel to send.
     */
    private void sendPacket(Point pixel) {

        try {
            byte[] message = new byte[]{
                (byte)pixel.x,
                (byte)pixel.y
            };
       
            DatagramPacket packet = new DatagramPacket(
                    message,
                    message.length,
                    group,
                    PORT
            );

            socket.send(packet);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
    
    
    

    /**
     * Mouse clicked event.
     * Set the pixelToSend and repaint the frame.
     *
     * @param e Mouse clicked event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        pixelToSend = e.getPoint();
        repaint();
    }

    /**
     * Mouse pressed event.
     * Set the pixelToSend and repaint the frame.
     *
     * @param e Mouse pressed event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        pixelToSend = e.getPoint();
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
     * Set the pixelToSend and repaint the frame.
     *
     * @param e Mouse dragged event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        pixelToSend = e.getPoint();
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
