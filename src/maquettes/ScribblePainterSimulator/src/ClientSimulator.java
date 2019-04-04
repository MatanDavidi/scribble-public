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
import java.io.*;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 * Client drawer simulator for scribble.
 *
 * @author jarinaser
 * @author giuliobosco
 * @version 1.2 (2019-04-03)
 */
public class ClientSimulator extends JFrame implements PointListener {

    /**
     * New pixel.
     * Pixel to design.
     */
    private Point[] pixels;

    /**
     * Transmission method.
     */
    private char method;

    /**
     * Point listener.
     */
    private ListeningThread listeningThread;

    /**
     * Create ClientSimulator.
     *
     * @param title Frame title.
     * @throws IOException Error getting the inet address.
     */
    public ClientSimulator(String title) throws IOException {
        super(title);
        this.setSize(200, 200);
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        listeningThread = new ListeningThread(this);
    }

    /**
     * Point received from drawer.
     *
     * @param pixels Received Point.
     */
    @Override
    public void pointReceived(byte[] pixels) {
        System.out.println("size:" + pixels.length);
        this.method = (char) pixels[pixels.length - 1];
        this.pixels = new Point[(pixels.length - 1) / 2];
        if ((this.pixels.length - 1) % 2 == 0) {
            for (int i = 0; i < pixels.length - 2; i += 2) {
                this.pixels[(int) (i / 2)] = new Point(getInt(pixels[i]), getInt(pixels[i + 1]));
            }
        }
        this.repaint();
    }

    /**
     * Paint the last point.
     *
     * @param g Graphics fo the frame.
     */
    @Override
    public void paint(Graphics g) {
        if (this.pixels != null && this.pixels.length > 0 && this.pixels[0] != null) {
            if (this.method == DrawerSimulator.SEND_ALL_PIXEL_MODE) {
                g.clearRect(0, 0, getWidth(), getHeight());
                System.out.println(Arrays.toString(pixels));
                for (Point p : this.pixels) {
                    g.fillRect(p.x, p.y, 1, 1);
                }
            } else if (this.method == DrawerSimulator.SEND_SINGLE_PIXEL_MODE) {
                g.fillRect(this.pixels[0].x, this.pixels[0].y, 1, 1);
            }
        }
    }

    /**
     * Get int from unsigned byte.
     *
     * @param b Unsigned byte.
     * @return Int value.
     */
    private int getInt(byte b) {
        return 0xFF & b;
    }

    /**
     * Main method of the class, used for test it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ClientSimulator("My Client Simulator").setVisible(true);
                } catch (IOException ex) {
                    System.err.println("IOException: " + ex.getMessage());
                }
            }
        });
    }
}
