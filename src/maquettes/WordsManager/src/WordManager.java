
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bryan Beffa
 * @author Matteo Forni
 */
public class WordManager {

    /**
     * List that contains all the words of the list.
     */
    private List<String> words = new ArrayList<>();

    /**
     * List that contains the picked words from the attribute words.
     */
    private List<String> pickedWords = new ArrayList<>();

    public WordManager(List<String> words) {
        this.words = words;
    }

    /**
     * Method that returns a random line from the list.
     *
     * @return a random line of the list.
     */
    private String getRandomWord() {
        //random number
        int rnd = (int) (Math.random() * words.size());

        return words.get(rnd);
    }

    /**
     * Method that sorts alphabetically the list of the picked words.
     */
    public void sortList() {
        Collections.sort(pickedWords);

        for (String word : pickedWords) {
            System.out.println("Word: " + word);
        }
    }

    public String getUniqueNewWord() {
        if (words.size() > 0) {
            String word = getRandomWord();

            words.remove(word);
            pickedWords.add(word);
            
            return word;
        }
        
        words = pickedWords;
        pickedWords = new ArrayList<>();

        System.out.println("Parole terminate: ricarico la lista");
        
        //recursive call
        return getUniqueNewWord();
    }

    public static void main(String[] args) {

        //create the file path
        Path path = Paths.get("data", "list.txt");
        FileManager fManager;
        WordManager wManager;

        try {
            //create a new FileManager
            fManager = new FileManager(path);
            List<String> wordsList = fManager.getWords();

            //create a new WordManager
            wManager = new WordManager(wordsList);

            System.out.println("Lista parole casuali");
            for (int i = 0; i < 1000; i++) {
                //get a word never picked
                System.out.println(i + ". Parola scelta: "
                        + wManager.getUniqueNewWord());
            }

            //sort list
            wManager.sortList();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}
