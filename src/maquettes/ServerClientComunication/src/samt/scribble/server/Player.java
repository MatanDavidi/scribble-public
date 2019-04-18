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

/**
 * Giocatore di scribble, rappresenta un giocatore del gioco scribble.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-18)
 */
public class Player {

    /**
     * Nick name del giocatore.
     */
    private String nickname;

    /**
     * Indirizzo IP del giocatore.
     */
    private InetAddress ip;

    /**
     * Porta logica del client del giocatore.
     */
    private int port;

    /**
     * Punteggio del giocatore.
     */
    private int score;

    /**
     * Ritorna il nick name del giocatoe.
     *
     * @return Nick name del giocatore.
     */
    public String getNickname() {
        return this.nickname;
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

    /**
     * Ritorna il punteggio del giocatore.
     *
     * @return Punteggio del giocatore.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Setta il punteggio del giocatore, che deve essere maggiore o uguale a 0, se non lo &egrave;
     * viene settato a zero.
     *
     * @param score Punteggio del giocatore.
     */
    public void setScore(int score) {
        if (score < 0) {
            score = 0;
        }

        this.score = score;
    }

    /**
     * Crea un giocatore con il suo nickname, il suo ip ed la porta logica del suo client.
     * Setta il punteggio a 0.
     *
     * @param nickname Nick name del giocatore.
     * @param ip       Indirizzo IP del giocatore.
     * @param port     Porta logica del client del giocatore.
     */
    public Player(String nickname, InetAddress ip, int port) {
        this.nickname = nickname;
        this.ip = ip;
        this.port = port;
        this.score = 0;
    }

}
