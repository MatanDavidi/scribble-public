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
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-05-04 - 2019-05-04)
 */
public class ScribblePanel extends JPanel implements DatagramListener, MouseMotionListener, MouseListener {

    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    private List<Point> drawedPoints;

    private List<Point> receivedPoints;

    private boolean drawer;

    private boolean clear;

    // --------------------------------------------------------------------------- Getters & Setters

    public boolean isDrawer() {
        return this.drawer;
    }

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

    // -------------------------------------------------------------------------------- Constructors

    public ScribblePanel(Connection connection) {
        connection.addDatagramListener(this);
        setDrawer(false);

        this.drawedPoints = new ArrayList<>();
        this.receivedPoints = new ArrayList<>();
        this.clear = true;
    }

    // -------------------------------------------------------------------------------- Help Methods

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

        if (bytes.length > 1 && bytes[0] == Commands.DRAWING) {
            int x = bytes[1] & 0x000000FF;
            int y = bytes[1] & 0x000000FF;

            this.receivedPoints.add(new Point(x, y));
        }
    }

    // ----------------------------------------------------------------------------- General Methods

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


    // --------------------------------------------------------------------------- Static Components

}
