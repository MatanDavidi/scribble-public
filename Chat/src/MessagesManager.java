
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * MessagesManager Classe che si occupa di gestire la ricezione di pacchetti
 * UDP.
 *
 * @author Nemanja Stojanovic
 * @version 14.02.2019
 */
public class MessagesManager extends Thread {

    /**
     * La grandezza del buffer per i pacchetti.
     */
    private final int BUFFER_SIZE = 1024;

    /**
     * Oggetto da avvisare quando viene ricevuto un nuovo messaggio.
     */
    private MessagesManagerListener listener;

    /**
     * Il socket.
     */
    private DatagramSocket socket;

    /**
     * Metodo costruttore con due parametri.
     *
     * @param socket Il socket.
     * @param listener Oggetto da avvisare quando viene ricevuto un nuovo
     * messaggio.
     */
    public MessagesManager(DatagramSocket socket, MessagesManagerListener listener) {
        this.socket = socket;
        this.listener = listener;
    }

    /**
     * Metodo sendMessage utilizzato per inviare del testo.
     *
     * @param message Il messaggio da inviare .
     * @param address L'inidirzzo IP del destinatario.
     * @param port La porta del destinatario.
     * @throws IOException Sollevata in caso di errore di invio del pacchetto.
     */
    public void sendMessage(String message, InetAddress address, int port) throws IOException {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }

    /**
     * Metodo che viene eseguito dalla thread. Controlla se sono arrivati nuovi
     * pacchetti e lo nota al listener.
     */
    @Override
    public void run() {
        try {
            while (true) {
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                listener.receivedMessage(packet, this);
            }
        } catch (IOException ie) {
            System.out.println("Errore nella lettura del socket!");
        }
        socket.close();
    }
}
