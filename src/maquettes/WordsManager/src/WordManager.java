
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
    
    private FileManager fManager;

    /**
     * Lista che contiene tutte le parole del file.
     */
    private List<String> words = new ArrayList<>();

    /**
     * Lista che contiene le parole già estratte.
     */
    private List<String> pickedWords = new ArrayList<>();

    private String currentWord = "";
    
    /**
     * Attributo booleano che indica se la modalità debug è attiva. Valore di
     * default = false.
     */
    private boolean debug = false;

    public WordManager(Path filePath) throws IOException{
        fManager = new FileManager(filePath);
        this.words = fManager.getWords();
    }
    
    public WordManager(Path filePath, boolean debug) throws IOException{
        fManager = new FileManager(filePath);
        this.words = fManager.getWords();
        this.debug = debug;
    }

    public String getCurrentWord(){
        return currentWord;
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
    
    public boolean isGuessedWord(String userWord){
        
        userWord = userWord.trim();
        
        System.out.println("Parola corrente: '" + currentWord + "' " + currentWord.trim().length());
        System.out.println("Parola dell'utente: '" + userWord + "' " + userWord.length());
        System.out.println("Statement: " + userWord.equalsIgnoreCase(currentWord));
        
        
//        for (int i = 0; i < currentWord.length(); i++) {
//            System.out.println((int)currentWord.charAt(i) + " " + currentWord.charAt(i));
//        }
        
        if(currentWord.equalsIgnoreCase(userWord.trim())){
            System.out.println("Parola indovinata");
            return true;
        } 
        
        System.out.println("Parola sbagliata");
        return false;
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
            currentWord = word.trim();
            
            return word;
        }
        
        words = pickedWords;
        pickedWords = new ArrayList<>();

        if (debug) {
            System.out.println("Parole terminate: ricarico la lista");
        }

        //chiamata ricursiva
        return getUniqueNewWord();
    }
}
