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

import samt.scribble.client.game.GamePanel;
import samt.scribble.client.game.WordGuessListener;
import samt.scribble.client.lobby.LobbyPanel;
import samt.scribble.client.login.LoginPanel;
import samt.scribble.client.login.LoginListener;
import javax.swing.*;
import java.awt.*;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.client.game.PlayerRole;
import samt.scribble.client.game.WordGuessListener;
import samt.scribble.client.lobby.LobbyListener;
import samt.scribble.client.login.WelcomePanel;
import samt.scribble.communication.Connection;

/**
 * Scribble client.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author MatanDavidi
 * @version 1.2.1 (2019-05-04 - 2019-05-07)
 */
public class ScribbleClient extends JFrame implements LoginListener, LobbyListener, WordGuessListener {
    
    /**
     * Pannello contenente tutti i pannelli.
     */
    private JPanel cardsPanel;

    /**
     * Istanza di GamePanel che contiene la GUI relativa alla partita.
     */
    private GamePanel gamePanel;

    /**
     * Istanza di LoginPanel che contiene i componenti e la logica per
     * effettuare l'accesso presso il server.
     */
    private LoginPanel loginPanel;

    /**
     * Istanza di LobbyPanel che contiene i componenti e la logica per l'attesa
     * di giocatori nel server.
     */
    private LobbyPanel lobbyPanel;

    /**
     * Pannello di benvenuto.
     */
    private WelcomePanel welcomePanel;

    /**
     * L'istanza di Connection che contiene i membri per gestire la connessione
     * con il server.
     */
    private Connection serverConnection;

    /**
     *  Nome utente del client.
     */
    private String username;

    /**
     * Attributo che rappresenta il titolo della pannello della lobby.
     */
    private final String LOBBY_PANEL_NAME = "lobbyPanel";

    /**
     * Attributo che rappresenta il titolo del pannello di gioco.
     */
    private final String GAME_PANEL_NAME = "gamePanel";

    /**
     * Nome del pannello di benvenuto.
     */
    private final String WELCOME_PANEL_NAME = "welcomePanel";

    /**
     * Crea scribble client.
     */
    public ScribbleClient() {
        super();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(200, 200));

        // Set Frame Layout.
        this.getContentPane().setLayout(new BorderLayout());

        cardsPanel = new JPanel(new CardLayout());

        welcomePanel = new WelcomePanel();
        welcomePanel.setListener(this);
        cardsPanel.add(welcomePanel);
        add(cardsPanel);

        pack();
    }

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
        if (loginPanel == null) {

            loginPanel = new LoginPanel();
            loginPanel.setListener(this);
            cardsPanel.add(LOGIN_PANEL_NAME, loginPanel);

        }
    }

    @Override
    public void loggedIn(String username, Connection serverConnection) {

        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
            this.username = username;
            System.out.println(username + ": accesso al gruppo multicast " + serverConnection.getGroupConnection().getGroupIp().getHostAddress());
        }

        lobbyPanel = new LobbyPanel(serverConnection, username);
        lobbyPanel.setListener(this);
        if (lobbyPanel == null) {

            lobbyPanel = new LobbyPanel(serverConnection, username);
            lobbyPanel.setListener(this);
            cardsPanel.add(LOBBY_PANEL_NAME, lobbyPanel);

        }

        this.serverConnection = serverConnection;

        cardsPanel.add(LOBBY_PANEL_NAME, lobbyPanel);
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, LOBBY_PANEL_NAME);
        pack();
    }

    @Override
    public void gameStartingGuesser() {

        gamePanel = new GamePanel(serverConnection, username, PlayerRole.Guesser);
        if (gamePanel == null) {

            gamePanel = new GamePanel(serverConnection, username, PlayerRole.Guesser);
            gamePanel.setListener(this);
            cardsPanel.add(GAME_PANEL_NAME, gamePanel);

        }

    }

    @Override
    public void gameStartingDrawer(String word) {

        gamePanel = new GamePanel(serverConnection, username, PlayerRole.Drawer);
        if (gamePanel == null) {

            gamePanel = new GamePanel(serverConnection, username, PlayerRole.Drawer);
            gamePanel.setListener(this);
            cardsPanel.add(GAME_PANEL_NAME, gamePanel);

        }


    }

    @Override
    public void wordGuessed(String word) {
        this.welcomePanel = new WelcomePanel();

        cardsPanel.add(WELCOME_PANEL_NAME, welcomePanel);
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, WELCOME_PANEL_NAME);
        pack();
    }
}
