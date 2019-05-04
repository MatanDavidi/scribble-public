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
package samt.scribble.server;

import samt.scribble.server.modules.Record;
import java.net.InetAddress;

/**
 * Giocatore di scribble, rappresenta un giocatore del gioco scribble.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author gabrialessi
 * @version 2019-05-04
 */
public class Player extends Record {

    /**
     * Indirizzo IP del giocatore.
     */
    private InetAddress ip;

    /**
     * Porta logica del client del giocatore.
     */
    private int port;

    /**
     * Crea un giocatore con il suo username, il suo ip e la porta logica del
     * suo client.
     *
     * @param username Username del giocatore.
     * @param ip Indirizzo IP del giocatore.
     * @param port Porta logica del client del giocatore.
     */
    public Player(String username, InetAddress ip, int port) {
        super(username);
        this.ip = ip;
        this.port = port;
    }

    /**
     * Crea un giocatore con il suo username, punteggio, ip e la porta logica
     * del suo client.
     *
     * @param username Username del giocatore.
     * @param score Punteggio del giocatore.
     * @param ip Indirizzo IP del giocatore.
     * @param port Porta logica del client del giocatore.
     */
    public Player(String username, int score, InetAddress ip, int port) {
        super(username, score);
        this.ip = ip;
        this.port = port;
    }

    /**
     * Ritorna l'indirizzo IP del giocatore.
     *
     * @return Indirizzo IP del giocatore.
     */
    public InetAddress getIp() {
        return this.ip;
    }

    /**
     * Ritorna la porta logica del client del giocatore.
     *
     * @return Porta logica del client del giocatore.
     */
    public int getPort() {
        return this.port;
    }

}
