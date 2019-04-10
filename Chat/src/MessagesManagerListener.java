
import java.net.DatagramPacket;

/**
 * MessagesManagerListener Interfaccia della classe MessagesManager.
 *
 * @author Nemanja Stojanovic
 */
public interface MessagesManagerListener {

    /**
     * Metodo utilizzato per avvisare quando viene ricevuto un nuovo pacchetto.
     *
     * @param packet Il pacchetto ricevuto.
     * @param source L'oggetto che ha ricevuto il pacchetto.
     */
    public void receivedMessage(DatagramPacket packet, MessagesManager source);
}
