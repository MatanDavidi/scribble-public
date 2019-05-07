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
package samt.scribble;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Parametri di default di scribble.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author MatanDavidi
 * @version 1.0.3 (2019-04-19 - 2019-05-06)
 */
public class DefaultScribbleParameters {

    /**
     * Porta del gruppo multicast di default.
     */
    public static final int DEFAULT_GROUP_PORT = 5000;

    /**
     * Porta di ascolto di default del client scribble.
     */
    public static final int DEFAULT_CLIENT_PORT = 5001;

    /**
     * Porta di ascolto di default del server scribble.
     */
    public static final int DEFAULT_SERVER_PORT = 5002;

    /**
     * Il numero minimo di giocatori che devono essere registrati per poter
     * avviare la partita.
     */
    public static final int MINIMUM_PLAYERS_NUMBER = 3;

    /**
     * Il livello di verbosità delle informazioni di debug durante l'esecuzione
     * del programma.
     */
    public static final int DEBUG_VERBOSITY = DebugVerbosity.INFORMATION;

    /**
     * Attributo che rappresenta il percorso del file csv su cui verrà salvata
     * la lista rappresentante la classifica.
     */
    public static final Path RANKING_CSV_PATH = Paths.get("data", "ranking.csv");

    /**
     * L'indirizzo IP del server che gestisce il gioco.
     */
    public static final String SERVER_ADDRESS = "127.0.0.1";

    /**
     * L'indirizzo IP del gruppo multicast al quale sono connessi tutti gli
     * utenti.
     */
    public static final String GROUP_ADDRESS = "230.0.0.0";

}
