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
import javax.swing.JFrame;

/**
 * Client drawer simulator for scribble.
 *
 * @author jarinaser
 * @author giuliobosco
 * @version 1.2 (2019-04-03)
 */
public class ClientSimulator extends JFrame implements PointListener{
    
    /**
     * Size in pixels of the window x and y axis.
     */
    private final int SIZE = 256;
    
    /**
     * Received pixel to draw.
     */
    private Point pixelToDraw;

    /**
     * Point listener.
     */
    private ListeningThread listeningThread;
    
    /**
     * Array containing the status of all pixels.
     */
    private boolean[][] pixelStatus;

    /**
     * Create ClientSimulator.
     *
     * @param title Frame title.
     * @throws IOException Error getting the inet address.
     */
    public ClientSimulator(String title) throws IOException {
        super(title);
        this.setSize(this.SIZE, this.SIZE);
        this.setPreferredSize(new Dimension(this.SIZE, this.SIZE));
        this.setBackground(Color.lightGray);
        this.listeningThread = new ListeningThread(this);
        this.pixelStatus = new boolean[256][256];
    }

    /**
     * Point received from drawer.
     *
     * @param pixels Received Point.
     */
    @Override
    public void pointReceived(byte[] pixels) {
        System.out.println("ClientSimulator: Pixels packet size:" + pixels.length);
        if(pixels.length == 2){
            this.pixelToDraw = new Point(getInt(pixels[0]), getInt(pixels[1]));
            if(!this.pixelStatus[this.pixelToDraw.x][this.pixelToDraw.y]){
                this.pixelStatus[this.pixelToDraw.x][this.pixelToDraw.y] = true;
                this.repaint();
            }
        }
    }

    /**
     * Paint the last point.
     *
     * @param g Graphics of the frame.
     */
    @Override
    public void paint(Graphics g) {
        
        int cols = SIZE;
        int rows = SIZE;   
        int w = getWidth();
        int h = getHeight();
        double widthInPixels;
        
        if(w/cols > h/rows){
            widthInPixels = h/rows;
        }else{
            widthInPixels = w/cols;
        }
        
        double left = (w - widthInPixels * cols)/2;
        double top = (h - widthInPixels * rows)/2;
        
        g.clearRect(0, 0, w, h);
        g.setColor(Color.white);
        g.fillRect((int)left, (int)top, (int)widthInPixels * SIZE, (int)widthInPixels * SIZE);
        g.setColor(Color.black);
        
        for(int i = 0; i < pixelStatus.length; i++){
            for(int j = 0; j < pixelStatus[i].length; j++){
                if(pixelStatus[i][j]){
                    g.fillRect(
                        (int)left + i * (int)widthInPixels, 
                        (int)top + j * (int)widthInPixels, 
                        (int)widthInPixels, 
                        (int)widthInPixels
                    );
                }
            }
        }
        
        if(this.pixelToDraw != null){
            g.fillRect(
                    (int)left + this.pixelToDraw.x * (int)widthInPixels, 
                    (int)top + this.pixelToDraw.y * (int)widthInPixels, 
                    (int)widthInPixels, 
                    (int)widthInPixels
            );
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
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ClientSimulator("Client Simulator").setVisible(true);
                }catch(IOException ex){
                    System.err.println("IOException: " + ex.getMessage());
                }
            }
        });
    }
    
}
