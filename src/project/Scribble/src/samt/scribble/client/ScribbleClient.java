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
import samt.scribble.client.lobby.LobbyPanel;
import samt.scribble.client.login.LoginPanel;
import samt.scribble.client.login.LoginListener;
import javax.swing.*;
import java.awt.*;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.client.endGame.EndGameListener;
import samt.scribble.client.endGame.EndPanel;
import samt.scribble.client.game.PlayerRole;
import samt.scribble.client.game.WordGuessListener;
import samt.scribble.client.lobby.LobbyListener;
import samt.scribble.client.welcome.WelcomeListener;
import samt.scribble.client.welcome.WelcomePanel;
import samt.scribble.communication.Connection;

/**
 * Finestra contenente tutti i pannelli.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author MatanDavidi
 * @author JariNaeser
 * @version 1.2.2 (2019-05-04 - 2019-05-09)
 */
public class ScribbleClient extends JFrame implements WelcomeListener, LoginListener, LobbyListener, WordGuessListener, EndGameListener {

    // ---------------------------------------------------------------- Costants
    /**
     * Attributo che rappresenta il titolo della pannello di benvenuto.
     */
    private final String WELCOME_PANEL_NAME = "welcomePanel";
    /**
     * Attributo che rappresenta il titolo della pannello di login.
     */
    private final String LOGIN_PANEL_NAME = "loginPanel";
    /**
     * Attributo che rappresenta il titolo della pannello della lobby.
     */
    private final String LOBBY_PANEL_NAME = "lobbyPanel";

    /**
     * Attributo che rappresenta il titolo del pannello di gioco.
     */
    private final String GAME_PANEL_NAME = "gamePanel";

    private final String ENDGAME_PANEL_NAME = "endPanel";

    // -------------------------------------------------------------- Attributes
    /**
     * Istanza di JPanel usata per la gestione della visualizzazione o del
     * nascondimento di un pannello quando esso entra o esce di scena.
     */
    private JPanel cardsPanel;

    /**
     * Il pannello di benvenuto mostrato all'inizio, prima di mostrare il
     * pannello di login.
     */
    private WelcomePanel welcomePanel;

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

    private EndPanel endPanel;

    /**
     * L'istanza di Connection che contiene i membri per gestire la connessione
     * con il server.
     */
    private Connection serverConnection;

    /**
     * Nome utente del client.
     */
    private String username;

    /**
     * Crea scribble client.
     */
    public ScribbleClient() {
        super("Scribble:");

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

    // ------------------------------------------------------------ Help Methods
    /**
     * Rimpiazza il pannello corrente con un altro pannello, che deve già essere
     * stato aggiunto al JPanel
     * {@link samt.scribble.client.ScribbleClient#cardsPanel cardsPanel}.
     *
     * @param panelName Il nome del pannello da visualizzare al posto di quello
     * corrente.
     */
    private void replacePanel(String panelName) {

        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, panelName);
        pack();

    }

    // --------------------------------------------------------- General Methods
    @Override
    public void welcomeClicked() {

        if (loginPanel == null) {

            loginPanel = new LoginPanel();
            loginPanel.setListener(this);
            cardsPanel.add(LOGIN_PANEL_NAME, loginPanel);

        }

        replacePanel(LOGIN_PANEL_NAME);

    }

    @Override
    public void loggedIn(String username, Connection serverConnection) {

        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
            System.out.println(username + ": accesso al gruppo multicast " + serverConnection.getGroupConnection().getGroupIp().getHostAddress());
        }
        this.username = username;
        if (lobbyPanel == null) {

            lobbyPanel = new LobbyPanel(serverConnection, username);
            lobbyPanel.setListener(this);
            cardsPanel.add(LOBBY_PANEL_NAME, lobbyPanel);

        }

        //set new JFrame name
        this.setTitle("Scribble: " + username);

        this.serverConnection = serverConnection;

        replacePanel(LOBBY_PANEL_NAME);
    }

    @Override
    public void gameStartingGuesser() {

        this.lobbyPanel = null;
        if (gamePanel == null) {

            gamePanel = new GamePanel(serverConnection, PlayerRole.Guesser, username);
            gamePanel.setListener(this);
            cardsPanel.add(GAME_PANEL_NAME, gamePanel);

            //setto il testo del jField nel gamePanel
            gamePanel.setWordToGuess("Indovina la parola per primo");
        }

        replacePanel(GAME_PANEL_NAME);

    }

    @Override
    public void gameStartingDrawer(String word) {
        this.lobbyPanel = null;
        if (gamePanel == null) {

            gamePanel = new GamePanel(serverConnection, PlayerRole.Drawer, username);
            gamePanel.setListener(this);
            cardsPanel.add(GAME_PANEL_NAME, gamePanel);

            //setto la parola da indovinare nel gamePanel
            gamePanel.setWordToGuess("Parola: " + word);
        }

        replacePanel(GAME_PANEL_NAME);

    }

    @Override
    public void wordGuessed(String word) {
        //resetto il game panel
        this.gamePanel = null;

        endPanel = new EndPanel();
        //setto la stringa contentete il vincitore
        endPanel.setGuesserLabelText(word);
        endPanel.setListener(this);
        cardsPanel.add(ENDGAME_PANEL_NAME, endPanel);

        replacePanel(ENDGAME_PANEL_NAME);
    }

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
    public void EndPanelClicked() {
        this.loginPanel = new LoginPanel();
        this.loginPanel.setListener(this);
        cardsPanel.add(LOGIN_PANEL_NAME, loginPanel);
        replacePanel(LOGIN_PANEL_NAME);
    }
}
