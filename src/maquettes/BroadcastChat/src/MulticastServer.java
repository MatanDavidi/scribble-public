
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JFrame;

/**
 *
 * @author Thor Düblin & Nemanja Stojanovic
 * @version 2019.04.18
 */
public class MulticastServer extends Thread {
    /**
     * Il socket di dati.
     */
    MulticastSocket socket;
    
    /**
     * Il pacchetto di dati.
     */
    DatagramPacket packet;
    
    /**
     * Array di byte. Il buffer dei dati da mandare.
     */
    byte[] sendBuf = new byte[1024];
    
    /**
     * La porta corrente.
     */
    private int port;
    
    /**
     * La porta di destinazione.
     */
    private int destinationPort = 0;
    
    /**
     * L'ip di destinazione.
     */
    private InetAddress group;
    
    /**
     * Il messaggio da inviare.
     */
    private String messageToSend = "";
    
    /**
     * Il messaggio ricevuto.
     */
    private String messageReceived = new String();
    
    /**
     * Istanza del frame.
     */
    private ChatForm messageListener;
    
    /**
     * Metodo costruttore personalizzato con 1 parametro.
     * @param jf Il frame in cui avviene la comunicazione.
     */
    public MulticastServer(ChatForm jf) {
        try {
            socket = new MulticastSocket(5555);
            
            group = InetAddress.getByName("224.12.12.12");
            
            socket.joinGroup(group);
            
        } catch (IOException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
        
        port = socket.getLocalPort();
    }
    
    /**
     * Ritorna il messaggio ricevuto.
     * @return Il messaggio ricevuto.
     */
    public String getMessageReceived() {
        return messageReceived;
    }
    
    /**
     * Metodo che viene chiamato dalle thread e serve per ricevere i messaggi.
     */
    public void run(){
        try{
            while(true){
                DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length);
                socket.receive(packet);
                messageReceived = new String(packet.getData(), 0, packet.getLength());
                
                messageListener.appendToChat(messageReceived);
            }
        }
        catch (IOException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
        try{
            socket.leaveGroup(group);
            socket.close();
        }
        catch(IOException ex){
            System.out.println("ERRORE: " + ex.getMessage());
        }
    }
    
    /**
     * Metodo che serve a mandare i messaggi.
     */
    public void sendMessage(){
        try {
            byte[] data = messageToSend.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, group, destinationPort);
            socket.send(packet);
        }
        catch (SocketException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
    }
}
