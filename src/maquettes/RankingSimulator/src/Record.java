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

/**
 * Classe che rappresenta il nome utente e il punteggio di un giocatore.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 1.1 (10.04.2019)
 */
public class Record {

    /**
     * Attributo che rappresenta il nome utente. Valore di default: "UNKNOWN".
     */
    private String username = "UNKNOWN";

    /**
     * Attributo che rappresenta il punteggio. Valore di default: 0.
     */
    private int score = 0;

    /**
     * Metodo costruttore che definisce la classe con il nome utente.
     *
     * @param username Nome utente del giocatore.
     */
    public Record(String username) {
        setUsername(username);
    }

    /**
     * Metodo costruttore che definisce la classe con il nome utente e il punteggio.
     *
     * @param username Nome utente del giocatore.
     * @param score Punteggio del giocatore.
     */
    public Record(String username, int score) {
        setUsername(username);
        setScore(score);
    }

    /**
     * Metodo che ritorna il nome utente.
     *
     * @return Nome utente del giocatore.
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
     * Metodo che setta il nome utente.
     *
     * @param username Nome utente del giocatore.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo che setta il punteggio.
     *
     * @param score Punteggio del giocatore.
     */
    public void setScore(int score) {
        if (score < 0) {
            score = 0;
        }
        this.score = score;
    }

}
