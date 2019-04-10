
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
 * La classe LoginClient permette di inviare pacchetti UDP a un determinato
 * indirizzo IP e porta in modo da eseguire la registrazione.
 *
 * @author Matan Davidi
 * @version 2019.04.10
 */
public class LoginClient {

    /**
     * Permette di inviare un nome utente ad un indirizzo e una porta.
     *
     * @param username Il nome con il quale registrarsi.
     * @param ipAddress L'indirizzo IP del server presso il quale registrarsi.
     * @param port La porta sulla quale il server presso il quale registrarsi
     * sta ascoltando.
     * @throws SocketException Se non è possibile creare il socket per l'invio
     * del pacchetto.
     * @throws UnknownHostException Se non viene riconosciuto l'indirizzo IP o
     * la porta del server.
     * @throws IOException Se c'è un errore di I/O.
     * @throws NumberFormatException Se non è possibile convertire la porta
     * passata in un numero.
     */
    public void sendLogin(String username, String ipAddress, String port) throws SocketException, UnknownHostException, IOException, NumberFormatException {

        int portInt = Integer.parseInt(port);
        sendLogin(username, ipAddress, portInt);

    }

    /**
     * Permette di inviare un nome utente ad un indirizzo e una porta.
     *
     * @param username Il nome con il quale registrarsi.
     * @param ipAddress L'indirizzo IP del server presso il quale registrarsi.
     * @param port La porta sulla quale il server presso il quale registrarsi
     * sta ascoltando.
     * @throws SocketException Se non è possibile creare il socket per l'invio
     * del pacchetto.
     * @throws UnknownHostException Se non viene riconosciuto l'indirizzo IP o
     * la porta del server.
     * @throws IOException Se c'è un errore di I/O.
     */
    public void sendLogin(String username, String ipAddress, int port) throws SocketException, UnknownHostException, IOException {

        byte[] usernameBytes = username.getBytes();
        DatagramPacket packet = new DatagramPacket(usernameBytes, usernameBytes.length, InetAddress.getByName(ipAddress), port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);

    }

}
