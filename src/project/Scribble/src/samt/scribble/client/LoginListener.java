/*
 * The MIT License
 *
 * Copyright 2019 Matan Davidi.
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
package samt.scribble.client;

import samt.scribble.communication.Connection;

/**
 * L'interfaccia LoginListener contiene gli eventi relativi all'accesso e la
 * registrazione al server.
 *
 * @author MatanDavidi
 * @version 1.1 (2019-05-04 - 2019-05-06)
 */
public interface LoginListener {

    /**
     * Evento che segnala che un utente ha eseguito l'accesso al server.
     *
     * @param username Il nome dell'utente che ha eseguito l'accesso.
     * @param serverConnection La connessione al server, composta da connessione
     * al gruppo, thread di ascolto e classe di invio dei pacchetti.
     */
    public void loggedIn(String username, Connection serverConnection);

}
