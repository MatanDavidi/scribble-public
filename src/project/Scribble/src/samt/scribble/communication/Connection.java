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

package samt.scribble.communication;

/**
 * Controllo della comunicazione.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-05-04 - 2019-05-04)
 */
public class Connection {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Connessione al gruppo.
     */
    private GroupConnection groupConnection;

    /**
     * Thread di ascolto messaggi in single cast.
     */
    private ListeningThread listeningThread;

    /**
     * Invio messaggi single cast.
     */
    private MessageSender messageSender;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Getter per la connessione al gruppo.
     *
     * @return Connessione al gruppo.
     */
    public GroupConnection getGroupConnection() {
        return this.groupConnection;
    }

    /**
     * Getter per la thread di ascolto messaggi in single cast.
     *
     * @return Thread di ascolto messaggi in single cast.
     */
    public ListeningThread getListeningThread() {
        return this.listeningThread;
    }

    /**
     * Getter per l'invio messaggi single cast.
     *
     * @return Invio messaggi single cast.
     */
    public MessageSender getMessageSender() {
        return this.messageSender;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Costruttore.
     */
    public Connection(GroupConnection groupConnection, ListeningThread listeningThread, MessageSender messageSender) {
        this.groupConnection = groupConnection;
        this.listeningThread = listeningThread;
        this.messageSender = messageSender;
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
