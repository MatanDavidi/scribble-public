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

/**
 * Parametri di default di scribble.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @author Matan Davidi
 * @version 1.0.2 (2019-04-19 - 2019-05-04)
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
    public static final DebugVerbosity DEBUG_VERBOSITY = DebugVerbosity.Debug;

}
