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

import javax.swing.*;
import java.awt.*;

/**
 * Scribble client.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-05-04 - 2019-05-04)
 */
public class ScribbleClient extends JFrame {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Pannello scribble, dove verrà disegnato il disegno.
     */
    private ScribblePanel scribblePanel;

    // --------------------------------------------------------------------------- Getters & Setters
    // -------------------------------------------------------------------------------- Constructors

    /**
     * Crea scribble client.
     */
    public ScribbleClient() {
        super();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(200, 200));

        // Set Frame Layout.
        this.getContentPane().setLayout(new BorderLayout());

        this.scribblePanel = new ScribblePanel();
        this.getContentPane().add(this.scribblePanel, BorderLayout.CENTER);

        pack();
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

    /**
     * Avvio di scribble (client).
     *
     * @param args Argomenti da linea di comando.
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScribbleClient().setVisible(true);
            }
        });
    }

}
