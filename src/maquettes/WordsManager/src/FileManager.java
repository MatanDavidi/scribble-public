
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Bryan Beffa
 * @author Matteo Forni
 */
public class FileManager {

    /**
     * Path of the file.
     */
    private Path path;

    /**
     * Constructor that needs the file path.
     *
     * @param path path of the file.
     * @throws java.io.IOException
     */
    public FileManager(Path path) throws IOException {
        //check if the file exists
        if (Files.exists(path) && !Files.notExists(path)) {

            //check if the file is readable
            if (!Files.isReadable(path)) {
                throw new IOException("Il file non è accessibile in lettura");
            }
        } else {
            throw new IOException("Il file non esiste");
        }
        this.path = path;
    }

    /**
     * Method that returns the bytes of the content.
     *
     * @return the bytes of the content.
     */
    public byte[] getContentBytes() {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(path);
            return bytes;
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return bytes;
    }

    /**
     * Methods that returns the a list that contains the content of the file.
     * 
     * @return the list that contains the words (lines of the file).
     */
    public List<String> getWords() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lines;
    }
}
