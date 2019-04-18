
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 
 * @author Bryan Beffa
 * @author Matteo Forni
 * @version 18.04.2019
 */
public class WordManager {

    /**
     * Lista che contiene tutte le parole del file.
     */
    private List<String> words = new ArrayList<>();

    /**
     * Lista che contiene le parole già estratte.
     */
    private List<String> pickedWords = new ArrayList<>();

    /**
     * Attributo booleano che indica se la modalità debug è attiva. Valore di
     * default = false.
     */
    private boolean debug = false;

    /**
     * Metodo costruttore che richiede la lista di parole del file.
     *
     * @param words lista di parole del file.
     */
    public WordManager(List<String> words) {
        this.words = words;
    }

    /**
     * Metodo costruttore che richiede la lista di parole del file e se la
     * modalità debug è attiva o meno.
     *
     * @param words lista di parole del file.
     * @param debug parametro che indica se la modalità debug è attiva.
     */
    public WordManager(List<String> words, boolean debug) {
        this.words = words;
        this.debug = debug;
    }

    /**
     * Metodo che ritorna una parola casuale dalla lista delle parole non ancora
     * estratte.
     */
    private String getRandomWord() {
        //numero casuale
        int rnd = (int) (Math.random() * words.size());

        return words.get(rnd);
    }

    /**
     * Metodo che ordina la lista di parole estratte in ordine alfabetico.
     */
    public void sortList() {
        Collections.sort(pickedWords);

        if (debug) {
            for (String word : pickedWords) {
                System.out.println("Word: " + word);
            }
        }
    }

    /**
     * Metodo che ritorna una parola casuale non ancora estratta.
     * 
     * @return parola casuale non ancora estratta.
     */
    public String getUniqueNewWord() {
        //controllo se la lista di parole non estratte è vuota
        if (words.size() > 0) {
            String word = getRandomWord();

            words.remove(word);
            pickedWords.add(word);

            return word;
        }
        
        words = pickedWords;
        pickedWords = new ArrayList<>();

        if (debug) {
            System.out.println("Parole terminate: ricarico la lista");
        }

        //chiamata recursiva
        return getUniqueNewWord();
    }

    public static void main(String[] args) {

        //creo il percorso del file
        Path path = Paths.get("data", "list.txt");
        FileManager fManager;
        WordManager wManager;

        try {
            //creo il file manager
            fManager = new FileManager(path);
            List<String> wordsList = fManager.getWords();

            //creo il WordManager
            wManager = new WordManager(wordsList, true);

            System.out.println("Lista parole casuali");
            for (int i = 0; i < 1000; i++) {
                //ottengo una parola non ancora estratta
                System.out.println(i + ". Parola scelta: "
                        + wManager.getUniqueNewWord());
            }

            //ordino la lista
            wManager.sortList();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}
