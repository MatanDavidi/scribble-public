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
package samt.scribble.server.modules;

/**
 * Classe che rappresenta il nome utente e il punteggio di un giocatore.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 2019-05-01
 */
public class Record {

    /**
     * Attributo che rappresenta il nome di un giocatore. Valore di default:
     * "UNKNOWN".
     */
    protected String username = "UNKNOWN";

    /**
     * Attributo che rappresenta il punteggio di un giocatore. Valore di
     * default: 0.
     */
    protected int score = 0;

    /**
     * Metodo costruttore vuoto.
     */
    public Record() {

    }

    /**
     * Metodo costruttore dove si definisce lo username e si imposta il
     * punteggio a 0.
     *
     * @param username Username del giocatore.
     */
    public Record(String username) {
        setUsername(username);
        setScore(0);
    }

    /**
     * Metodo costruttore dove si definiscono username e punteggio..
     *
     * @param username Username del giocatore.
     * @param score Puntegio del giocatore.
     */
    public Record(String username, int score) {
        setUsername(username);
        setScore(score);
    }

    /**
     * Metodo che ritorna lo username.
     *
     * @return Username del giocatore.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Metodo che ritorna il punteggio.
     *
     * @return Punteggio del giocatore.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Metodo che imposta lo username.
     *
     * @param username Username del giocatore.
     */
    protected void setUsername(String username) {
        String regex = "^(?=.{1,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
        if (username.matches(regex)) {
            this.username = username;
        }
    }

    /**
     * Metodo che imposta il punteggio.
     *
     * @param score Punteggio del giocatore.
     */
    protected void setScore(int score) {
        if (score < 0) {
            score = 0;
        }
        this.score = score;
    }

}
