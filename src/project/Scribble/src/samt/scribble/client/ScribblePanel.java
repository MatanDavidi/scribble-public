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
package samt.scribble.client;

import samt.scribble.communication.Commands;
import samt.scribble.communication.Connection;
import samt.scribble.communication.DatagramListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ScribblePanel che si occupa di fornire un pannello funzionante sia per
 * il disegnatore che per il client che lo usa solamente in modalità di
 * visualizzazione. Inoltre allo stesso tempo se in modalità disegnatore la
 * classe manda ogni pixel al server che poi a sua volta verrà inoltrato al
 * gruppo.
 *
 * @author giuliobosco
 * @author jarinaeser
 * @author MatanDavidi
 * @version 1.1 (2019-05-06 - 2019-05-07)
 */
public class ScribblePanel extends JPanel implements DatagramListener, MouseMotionListener, MouseListener {

    /**
     * Attributo drawedPoints che contiene i pixel temporanei colorati da
     * mandare al server.
     */
    private List<Point> drawedPoints;

    /**
     * Attributo receivedPoints che contiene i pixel colorati ricevuti dal
     * server.
     */
    private List<Point> receivedPoints;

    /**
     * Attributo drawer che definisce se il client è disegnatore oppure no.
     */
    private boolean drawer;

    /**
     * Attributo clear che definisce se pulire l'area di disegno oppure no.
     */
    private boolean clear;

    /**
     * Metodo getter per l'attributo drawer.
     *
     * @return Valore dell'attributo drawer.
     */
    public boolean isDrawer() {
        return this.drawer;
    }

    /**
     * Metodo setter per l'attributo drawer.
     *
     * @param drawer Nuovo valore da assegnare all'attributo drawer.
     */
    public void setDrawer(boolean drawer) {
        this.drawer = drawer;

        if (this.drawer) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        } else {
            this.removeMouseListener(this);
            this.removeMouseMotionListener(this);
        }
    }

    /**
     * Costruttore della classe ScribblePanel.
     *
     * @param connection Connessione con il server.
     */
    public ScribblePanel(Connection connection) {
        connection.addDatagramListener(this);
        setDrawer(false);

        this.drawedPoints = new ArrayList<>();
        this.receivedPoints = new ArrayList<>();
        this.clear = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.drawedPoints.add(e.getPoint());
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.drawedPoints.add(e.getPoint());
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void messageReceived(DatagramPacket datagramPacket) {

        byte[] bytes = datagramPacket.getData();

        switch (bytes[0]) {

            case Commands.START:
                break;
            
            case Commands.DRAWING:
                if (bytes.length > 1) {
                    int x = bytes[1] & 0x000000FF;
                    int y = bytes[1] & 0x000000FF;

                    this.receivedPoints.add(new Point(x, y));
                }
                break;

        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (this.clear) {
            g.setColor(Color.white);
            g.drawRect(0, 0, getWidth(), getHeight());
        }

        g.setColor(Color.gray);
        for (Point point : this.drawedPoints) {
            g.drawRect(point.x, point.y, 1, 1);
            this.drawedPoints.remove(point);
        }

        g.setColor(Color.black);
        for (Point point : this.receivedPoints) {
            g.drawRect(point.x, point.y, 1, 1);
            this.receivedPoints.remove(point);
        }
    }
}
