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

import samt.scribble.DefaultScribbleParameters;
import samt.scribble.communication.Commands;
import samt.scribble.communication.Connection;
import samt.scribble.communication.DatagramListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import samt.scribble.client.game.PlayerRole;
import samt.scribble.communication.MessageSender;
import samt.scribble.communication.messages.DrawMessage;

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
 * @version 1.1.4 (2019-05-06 - 2019-05-09)
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
     * Connessione al server scribble.
     */
    private Connection connection;

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
     * @param playerRole Il ruolo di questo giocatore nella prossima partita.
     */
    public ScribblePanel(Connection connection, PlayerRole playerRole) {
        super();
        connection.addDatagramListener(this);
        setDrawer(playerRole.equals(PlayerRole.Drawer));

        this.drawedPoints = new ArrayList<>();
        this.receivedPoints = new ArrayList<>();
        this.clear = true;
        this.connection = connection;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.drawedPoints.add(e.getPoint());
        try {
            this.sendPoint(e.getPoint());
        } catch (IOException ignored) {
        }
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
        try {
            this.sendPoint(e.getPoint());
        } catch (IOException ignored) {
        }
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    /**
     * Metodo che il punto da disegnare al server.
     * @param p attributro che rappresenta il punto.
     * @throws IOException eccezione che gestisce i messaggi sbagliati.
     */
    public void sendPoint(Point p) throws IOException {
        DrawMessage message = new DrawMessage(p);

        MessageSender.sendMessage(
                InetAddress.getByName(DefaultScribbleParameters.SERVER_ADDRESS),
                DefaultScribbleParameters.DEFAULT_SERVER_PORT,
                message);
    }

    @Override
    public void messageReceived(DatagramPacket datagramPacket) {

        byte[] bytes = datagramPacket.getData();

        switch (bytes[0]) {

            case Commands.DRAWING:
                if (bytes.length > 2) {
                    int x = bytes[1] & 0x000000FF;
                    int y = bytes[2] & 0x000000FF;

                    this.receivedPoints.add(new Point(x, y));
                    this.repaint();
                }
                break;
        }
        
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (this.clear) {
            g.setColor(Color.white);
            g.drawRect(0, 0, getWidth(), getHeight());
            this.clear = false;
        }

        g.setColor(Color.gray);
        for (int i = 0; i < this.drawedPoints.size(); i++) {
            g.drawRect(this.drawedPoints.get(i).x, this.drawedPoints.get(i).y, 1, 1);
        }

        g.setColor(Color.black);
        for (int i = 0; i < this.receivedPoints.size(); i++) {
            g.drawRect(this.receivedPoints.get(i).x, this.receivedPoints.get(i).y, 1, 1);
        }
    }
}
