import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JFrame;

/**
 * 
 * @author Nemanja e Thor
 * @version 01.05.2019
 */
public class MulticastClient extends Thread{
    
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

    void setInfo(String message) {
        try {
            group = InetAddress.getByName("224.12.12.12");
            if(port >= 1024 && port <= 65535){
                destinationPort = 5555;
            }
            messageToSend = message;
        }catch (UnknownHostException | NullPointerException ex) {
            System.err.println("ERRORE: IP inserito non valido!");
        }
    }
}
