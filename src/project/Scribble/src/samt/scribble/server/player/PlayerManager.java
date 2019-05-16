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
package samt.scribble.server.player;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;

/**
 * Gestione della lista dei giocatori di scribble.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author gabrialessi
 * @version 1.1 (2019-05-12)
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
     * Ritorna la lista di giocatori registrati.
     *
     * @return Una lista contenente un'istanza di Player per ogni giocatore
     * registrato.
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Ritorna il numero totale di giocatori registrati.
     *
     * @return Il numero totale di giocatori registrati.
     */
    public int getPlayersNumber() {
        return this.players.size();
    }

    /**
     * Controlla se lo username è registrato nella lista dei giocatori.
     *
     * @param username Username da controllare se è nella lista.
     * @return Se il giocatore è registrato o meno.
     */
    public boolean isRegisteredPlayer(String username) {
        for (Player player : getPlayers()) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Controlla se l'IP è già stato registrato nella lista dei giocatori.
     *
     * @param ip IP da controllare se è nella lista.
     * @return Se l'IP è registrato o meno.
     */
    public boolean isRegisteredPlayer(InetAddress ip) {
        for (Player player : getPlayers()) {
            if (player.getIp().equals(ip)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Controlla se lo username e l'IP sono registrati nella lista dei giocatori
     * di scribble.
     *
     * @param username Username da controllare se è nella lista.
     * @param ip IP da controllare se è nella lista.
     * @return Se username e IP sono nella lista dei giocatori o meno.
     */
    public boolean isRegisteredPlayer(String username, InetAddress ip) {
        for (Player player : getPlayers()) {
            if (player.getUsername().equalsIgnoreCase(username) && player.getIp().equals(ip)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registra il giocatore nella lista dei giocatori di scribble.
     *
     * @param newPlayer Giocatore da registrare.
     * @throws PlayerAlreadyRegisteredException Se lo username del giocatore è
     * già in uso.
     */
    public void registerPlayer(Player newPlayer) throws PlayerAlreadyRegisteredException {
        if (isRegisteredPlayer(newPlayer.getUsername())) {
            throw new PlayerAlreadyRegisteredException("Lo username \"" + newPlayer.getUsername() + "\" è già in uso!");
        } else {
            getPlayers().add(newPlayer);
            if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
                System.out.println("Il giocatore \"" + newPlayer.getUsername() + "\" è stato registrato con successo.");
            }
        }
    }

//    /**
//     * Metodo che ritorna lo username ricevendo come parametro la porta e l'IP.
//     *
//     * @param ip IP del giocatore.
//     * @param port Porta del giocatore.
//     * @return Username del giocatore.
//     */
//    public String getUsernameByAddress(InetAddress ip, int port) {
//        for (Player player : getPlayers()) {
//            // Controllo se la porta e l'IP corrispondono all'utente corrente
//            if (player.getIp().toString().equals(ip.toString()) && player.getPort() == port) {
//                return player.getUsername();
//            }
//        }
//        return DefaultScribbleParameters.NO_FOUND_USERNAME_BY_ADDRESS;
//    }

    /**
     * Cancella tutti i giocatori registrati.
     */
    public void resetPlayers() {

        players.clear();

    }

}
