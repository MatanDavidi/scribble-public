/**
 *
 * @author Thor Düblin & Nemanja Stojanovic
 */
public interface ChatListener {
    /**
     * Metodo che indica quando un messaggio viene ricevuto.
     */
    public void appendToChat(String newLine);
}
