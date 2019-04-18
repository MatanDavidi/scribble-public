
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan Beffa
 * @author Matteo Forni
 * @version 18.04.2019
 */
public class FileManager {

    /**
     * Percorso del file.
     */
    private final Path WORDS_FILE_PATH = Paths.get("data", "list.txt");

    private Path wordsFilePath;

    /**
     * Metodo costruttore vuoto.
     *
     * @throws java.io.IOException
     */
    public FileManager() throws IOException {
        //controllo se il file esiste
        if (Files.exists(WORDS_FILE_PATH) && !Files.notExists(WORDS_FILE_PATH)) {

            //conntrollo se il file è leggibile
            if (!Files.isReadable(WORDS_FILE_PATH)) {
                throw new IOException("Il file non è accessibile in lettura");
            }
        } else {
            throw new IOException("Il file non esiste");
        }
        wordsFilePath = WORDS_FILE_PATH;
    }

    /**
     * Metodo costruttore che richiede il percorso del file.
     *
     * @param path percorso del file.
     * @throws java.io.IOException
     */
    public FileManager(Path path) throws IOException {
        //controllo se il file esiste
        if (Files.exists(path) && !Files.notExists(path)) {

            //conntrollo se il file è leggibile
            if (!Files.isReadable(path)) {
                throw new IOException("Il file non è accessibile in lettura");
            }
        } else {
            throw new IOException("Il file non esiste");
        }
        wordsFilePath = path;
    }

    /**
     * Metodo che ritorna la lista di parole contenute nel file.
     *
     * @return la lista di parole contenute nel file.
     * @throws java.io.IOException
     */
    public List<String> getWords() throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(wordsFilePath);
        } catch (IOException ex) {
            throw new IOException("Errore: " + ex.getMessage());
        }
        return lines;
    }
}
