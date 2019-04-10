
import java.util.List;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
/**
 * La classe LoginServer permette a un utente di registrarsi attraverso un invio
 * di pacchetti tramite connessione UDP.
 *
 * @author Matan Davidi
 * @version 2019.04.10
 */
public class LoginServer extends Thread {

    /**
     * Il DatagramSocket utilizzato per ricevere pacchetti UDP.
     */
    private DatagramSocket socket;

    /**
     * La lista di nomi utente degli utenti che si sono registrati.
     */
    private List<String> users;

    /**
     * La lista di tutti i ChatListeners che ascoltano quest'istanza di
     * LoginServer.
     */
    private List<ChatListener> chatListeners;

    /**
     * Istanzia nuovi oggetti di tipo LoginServer.
     *
     * @throws SocketException In caso non fosse possibile istanziare il socket
     * di ricevimento dei pacchetti.
     */
    public LoginServer() throws SocketException {

        users = new ArrayList<>();
        chatListeners = new ArrayList<>();

        int port = -1;

        while (port == -1) {

            try {

                port = Integer.parseInt(JOptionPane.showInputDialog("Enter port on which to listen to:"));
                socket = new DatagramSocket(port);
                System.out.println("Listening on port " + port);

            } catch (NumberFormatException nfe) {

            }

        }

    }

    /**
     * Aggiunge un ChatListener alla lista chatListeners. Esso d'ora in poi
     * riceverà le chiamate ai metodi relativi alla chat richiamati da
     * quest'istanza di LoginServer.
     *
     * @param listener Il listener da aggiungere alla lista.
     * @return true (come specificato da Collection.add)
     */
    public boolean addChatListener(ChatListener listener) {

        return chatListeners.add(listener);

    }

    /**
     * Controlla se un utente è già registrato e, se non lo è, lo aggiunge alla
     * lista users.
     *
     * @param newUser Il nome utente dell'utente da registrare.
     * @return true se è stato possibile aggiungere l'utente alla lista o false
     * se non è stato possibile.
     */
    private boolean updateUsers(String newUser) {

        boolean updated = false;

        if (!users.contains(newUser)) {

            users.add(newUser);
            updated = true;

        }

        return updated;

    }

    @Override
    public void run() {

        while (true) {

            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try {

                socket.receive(packet);

                String newUser = new String(packet.getData());

                if (updateUsers(newUser)) {

                    for (ChatListener chatListener : chatListeners) {

                        chatListener.appendToChat("New user logged in: " + newUser);

                    }

                }

            } catch (IOException ioe) {

                System.out.println(ioe.getMessage());

            }

        }

    }

    /**
     * Istanzia e fa partire un oggetto di tipo LoginServer per testare il
     * corretto funzionamento di questa classe.
     *
     * @param args Gli argomenti passati da linea di comando.
     */
    public static void main(String[] args) {

        try {

            (new LoginServer()).start();

        } catch (SocketException se) {

            System.out.println(se.getMessage());

        }

    }

}
