/*
 * The MIT License
 *
 * Copyright 2019 giuliobosco.
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
package samt.scribble.server.player;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;

/**
 * Gestione della lista dei giocatori di scribble.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-18)
 */
public class PlayerManager {

    /**
     * Lista dei giocatori di scribble.
     */
    private List<Player> players;

    /**
     * Crea la lista dei giocatori di scribble vuota.
     */
    public PlayerManager() {
        this.players = new ArrayList<>();
    }

    /**
     * Ritorna il numero totale di giocatori registrati.
     *
     * @return Il numero totale di giocatori registrati.
     */
    public int getPlayersNumber() {

        return players.size();

    }

    /**
     * Controlla se il username &egrave; registrato nella lista dei giocatori di
     * scribble.
     *
     * @param username Username da controllare se &egrave; nella lista.
     * @return True se il giocatore &egrave; registrato.
     */
    public boolean isRegisteredPlayer(String username) {
        for (Player player : this.players) {
            if (player.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Controlla se l'ip è già stato registrato nella lista dei giocatori di
     * scribble.
     *
     * @param ip IP da controllare se &egrave; nella lista.
     * @return True se l'IP &egrave; registrato.
     */
    public boolean isRegisteredPlayer(InetAddress ip) {
        for (Player player : this.players) {
            if (player.getIp().equals(ip)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Controlla se il username e l'IP sono registrati nella lista dei giocatori
     * di scribble.
     *
     * @param username Username da controllare se &egrave; nella lista.
     * @param ip IP da controlla se &egrave; nella lista.
     * @return True se il username e l'IP sono nella lista dei giocatori di
     * scribble.
     */
    public boolean isRegisteredPlayer(String username, InetAddress ip) {
        for (Player player : this.players) {
            if (player.getUsername().equals(username) && player.getIp().equals(ip)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Registra il giocatore nella lista dei giocatori di scribble.
     *
     * @param player Giocatore di scribble.
     * @throws PlayerAlreadyRegisteredException Il username del giocatore è già
     * in uso.
     */
    public void registerPlayer(Player player) throws PlayerAlreadyRegisteredException {
        for (Player playerInList : this.players) {
            if (playerInList.getUsername().equals(player.getUsername())) {
                String message = "Il username \"" + player.getUsername() + "\" è già in uso!";
                throw new PlayerAlreadyRegisteredException(message);
            }
        }

        this.players.add(player);

        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {

            System.out.println("Il giocatore " + player.getUsername() + " è stato registrato con successo.");

        }

    }

    /**
     * Metodo main della classe, utilizzato per testare PlayerManager e player.
     *
     * @param args Argomenti da linea di comando.
     */
    public static void main(String[] args) {
        try {
            PlayerManager playerManager = new PlayerManager();

            Player p0 = new Player("player0", InetAddress.getByName("localhost"), 2000);
            Player p1 = new Player("player1", InetAddress.getByName("localhost"), 2001);
            Player p2 = new Player("player2", InetAddress.getByName("localhost"), 2002);
            Player p3 = new Player("player2", InetAddress.getByName("localhost"), 2003);

            playerManager.registerPlayer(p0);
            System.out.println(p0.getUsername() + " registrato!");
            playerManager.registerPlayer(p1);
            System.out.println(p1.getUsername() + " registrato!");
            playerManager.registerPlayer(p2);
            System.out.println(p2.getUsername() + " registrato!");
            playerManager.registerPlayer(p3);
            System.out.println(p3.getUsername() + " registrato!");
        } catch (PlayerAlreadyRegisteredException | UnknownHostException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
