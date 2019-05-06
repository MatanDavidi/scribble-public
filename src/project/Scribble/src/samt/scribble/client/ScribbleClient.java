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
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.communication.Connection;
import samt.scribble.communication.GroupConnection;
import samt.scribble.communication.ListeningThread;
import samt.scribble.communication.MessageSender;

/**
 * Scribble client.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author MatanDavidi
 * @version 1.2 (2019-05-04 - 2019-05-06)
 */
public class ScribbleClient extends JFrame implements LoginListener {
    // ---------------------------------------------------------------- Costants
    // -------------------------------------------------------------- Attributes

    /**
     * Pannello scribble, dove verrà disegnato il disegno.
     */
    private ScribblePanel scribblePanel;

    /**
     * Istanza di LoginPanel che contiene i componenti e la logica per
     * effettuare l'accesso presso il server.
     */
    private LoginPanel loginPanel;

    /**
     * L'istanza di Connection che contiene i membri per gestire la connessione
     * con il server.
     */
    private Connection serverConnection;

    // ------------------------------------------------------- Getters & Setters
    // ------------------------------------------------------------ Constructors
    /**
     * Crea scribble client.
     */
    public ScribbleClient() {
        super();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(200, 200));

        // Set Frame Layout.
        this.getContentPane().setLayout(new BorderLayout());
        
        //serverConnection = new Connection(listeningThread, messageSender);

        loginPanel = new LoginPanel();
        loginPanel.setListener(this);
        add(loginPanel);

        pack();
    }

    // ------------------------------------------------------------ Help Methods
    // --------------------------------------------------------- General Methods
    // ------------------------------------------------------- Static Components
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

    @Override
    public void loggedIn(String username, GroupConnection groupConnectionResponse) {

        if (DefaultScribbleParameters.DEBUG_VERBOSITY.equals(DebugVerbosity.Information)) {

            System.out.println(username + ": logged into group " + groupConnectionResponse.getGroupIp().getHostAddress());
        }

        serverConnection = new Connection(groupConnectionResponse, new ListeningThread(DefaultScribbleParameters.DEFAULT_CLIENT_PORT), new MessageSender());
        scribblePanel = new ScribblePanel(serverConnection);
        getContentPane().remove(loginPanel);
        getContentPane().add(scribblePanel);
        getContentPane().validate();
        getContentPane().repaint();
        pack();

    }

}
