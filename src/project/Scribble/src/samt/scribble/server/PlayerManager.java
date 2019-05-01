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

package samt.scribble.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
     * Controlla se il nickname &egrave; registrato nella lista dei giocatori di scribble.
     *
     * @param nickname Nickname da controllare se &egrave; nella lista.
     * @return True se il giocatore &egrave; registrato.
     */
    public boolean isRegisteredPlayer(String nickname) {
        for (Player player : this.players) {
            if (player.getNickname().equals(nickname)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Controlla se l'ip è già stato registrato nella lista dei giocatori di scribble.
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
     * Controlla se il nickname e l'IP sono registrati nella lista dei giocatori di scribble.
     *
     * @param nickname Nickname da controllare se &egrave; nella lista.
     * @param ip       IP da controlla se &egrave; nella lista.
     * @return True se il nickname e l'IP sono nella lista dei giocatori di scribble.
     */
    public boolean isRegisteredPlayer(String nickname, InetAddress ip) {
        for (Player player : this.players) {
            if (player.getNickname().equals(nickname) && player.getIp().equals(ip)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Registra il giocatore nella lista dei giocatori di scribble.
     *
     * @param player Giocatore di scribble.
     * @throws PlayerAlreadyRegisteredException Il nickname del giocatore è già in uso.
     */
    public void registerPlayer(Player player) throws PlayerAlreadyRegisteredException {
        for (Player playerInList : this.players) {
            if (playerInList.getNickname().equals(player.getNickname())) {
                String message = "Il nickname \"" + player.getNickname() + "\" è già in uso!";
                throw new PlayerAlreadyRegisteredException(message);
            }
        }

        this.players.add(player);
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
            System.out.println(p0.getNickname() + " registrato!");
            playerManager.registerPlayer(p1);
            System.out.println(p1.getNickname() + " registrato!");
            playerManager.registerPlayer(p2);
            System.out.println(p2.getNickname() + " registrato!");
            playerManager.registerPlayer(p3);
            System.out.println(p3.getNickname() + " registrato!");
        } catch (PlayerAlreadyRegisteredException | UnknownHostException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
