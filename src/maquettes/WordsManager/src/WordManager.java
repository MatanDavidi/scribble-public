
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bryan Beffa
 */
public class WordManager {
    /**
     * List that contains the word without duplicates.
     */
    private List<String> words = new ArrayList<>();

    /**
     * Method that returns a random line from the list.
     * 
     * @param list list of the words.
     * @return a random line of the list.
     */
    private String getRandomWord(List<String> list) {
        //random number
        int rnd = (int) (Math.random() * list.size());

        return list.get(rnd);
    }
    
    /**
     * Method that sorts alphabetically the list of the words.
     * 
     * @param list list of the words.
     */
    public void sortList(List<String> list){
        Collections.sort(list);
        
        for(String line : list){
            System.out.println("Word: " + line);
        }
    }
    
    /**
     * Methods that returns a word that it didn't already get previously.
     * 
     * @param list list of the words.
     * @return the unique new word.
     */
    public String getUniqueNewWord(List<String> list){
        String word = "";
        boolean duplicateWord = true;
        
        while(duplicateWord){
            word = getRandomWord(list);
            //check if the word is unique
            if(!words.contains(word)){
                words.add(word);
                duplicateWord = false;
            }
        }
        
        return word;
    }

    public static void main(String[] args) {

        //create the file path
        Path path = Paths.get("data", "words.txt");
        FileManager fManager;
        WordManager wManager;

        try {
            //create a new FileManager
            fManager = new FileManager(path);
            List<String> wordsList = fManager.getWords();
            
            //create a new WordManager
            wManager = new WordManager();
            
            for (int i = 0; i < 200; i++) {
                //get a word never picked
                System.out.println("Parola scelta: " + 
                        wManager.getUniqueNewWord(wordsList));                
            }
            
            //sort list
            wManager.sortList(wordsList);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}
