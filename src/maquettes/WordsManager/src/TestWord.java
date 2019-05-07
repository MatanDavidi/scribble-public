
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Bryan
 */
public class TestWord {
    public static void main(String[] args) {

        //creo il percorso del file
        Path path = Paths.get("data", "test.txt");
        WordManager wManager;

        try {

            //creo il WordManager
            wManager = new WordManager(path, true);

            //estraggo la parola
            wManager.getUniqueNewWord();
            wManager.isGuessedWord("albero");

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}
