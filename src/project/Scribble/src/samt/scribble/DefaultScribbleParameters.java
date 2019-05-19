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
 * @version 1.0.4 (2019-04-19 - 2019-05-06)
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
    public static final int MINIMUM_PLAYERS_NUMBER = 2;

    /**
     * Il livello di verbosità delle informazioni di debug durante l'esecuzione
     * del programma.
     */
    public static final int DEBUG_VERBOSITY = DebugVerbosity.RELEASE;

    /**
     * Attributo che rappresenta il percorso del file csv su cui verrà salvata
     * la lista rappresentante la classifica.
     */
    public static final Path RANKING_CSV_PATH = Paths.get("data", "ranking.csv");

    /**
     * Costante che definisce il separatore tra username e punteggio nel file.
     */
    public static final String RANKING_CSV_SEP = ",";

    /**
     * Attributo che rappresenta il percorso del file di testo in cui sono
     * salvate le parole che vengono estratte per il disegnatore.
     */
    public static final Path WORDS_DICTIONARY_PATH = Paths.get("data", "words.txt");

    /**
     * L'indirizzo IP del server che gestisce il gioco.
     */
    public static final String SERVER_ADDRESS = "127.0.0.1";

    /**
     * L'indirizzo IP del gruppo multicast al quale sono connessi tutti gli
     * utenti.
     */
    public static final String GROUP_ADDRESS = "230.0.0.0";

    /**
     * Il separatore tra un argomento passato all'interno di un messaggio e
     * l'altro (vedi
     * {@link samt.scribble.communication.messages.Message Message} e le sue
     * sottoclassi).
     */
    public static final byte COMMAND_MESSAGE_SEPARATOR = Byte.MAX_VALUE;

    /**
     * L'espressione regolare da usare per controllare se un nome utente è
     * valido o meno.
     */
    public static final String USERNAME_REGEX = "^(?=.{1,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

    /**
     * Percorso del file contenente le parole da utilizzare nel gioco.
     */
    public static final Path WORDS_CSV_PATH = Paths.get("data", "words.csv");

    /**
     * La larghezza massima in pixel della parte disegnabile dello
     * ScribblePanel.
     */
    public static final int SCRIBBLE_HEIGHT = 200;

    /**
     * L'altezza massima in pixel della parte disegnabile dello ScribblePanel.
     */
    public static final int SCRIBBLE_WIDTH = 200;

    /**
     * Separatore del contenuto di un messaggio.
     */
    public static final String MESSAGE_SEPARATOR = ";";

    /**
     * Il numero di secondi che passa da quando hanno fatto l'accesso abbastanza
     * giocatori (vedi
     * {@link samt.scribble.DefaultScribbleParameters#MINIMUM_PLAYERS_NUMBER MINIMUM_PLAYERS_NUMBER})
     * all'inizio vero e proprio della partita.
     */
    public static final int SECONDS_PRE_MATCH = 3;
}
