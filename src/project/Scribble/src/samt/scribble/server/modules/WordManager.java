/*
 * The MIT License
 *
 * Copyright 2019 SAMT.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package samt.scribble.server.modules;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import samt.scribble.DebugVerbosity;
import samt.scribble.DefaultScribbleParameters;

/**
 * Gestione del dizionario di scribble.
 *
 * @author bryanbeffa
 * @author matteoforni
 * @author gabrialessi
 * @version 1.1 (2019-05-12)
 */
public class WordManager {

    /**
     * Attributo che rappresenta il gestore del file.
     */
    private FileManager fileManager;

    /**
     * Lista che contiene tutte le parole del file.
     */
    private List<String> words;

    /**
     * Lista che contiene le parole già estratte.
     */
    private List<String> pickedWords;

    /**
     * Stringa che contiene la parola corrente.
     */
    private String currentWord;

    /**
     * Metodo costruttore che richiede la path del file e consente di utilizzare
     * la modalità di debug.
     *
     * @param filePath Il percorso del file.
     * @throws java.io.IOException Se si verifica un'eccezione di input o di
     * output.
     */
    public WordManager(Path filePath) throws IOException {
        this.fileManager = new FileManager(filePath);
        this.words = fileManager.readFile();
        this.pickedWords = new ArrayList<>();
        this.currentWord = "";
    }

    /**
     * Metodo che ritorna la lista delle parole.
     *
     * @return La lista completa delle parole.
     */
    public List<String> getWords() {
        return this.words;
    }

    /**
     * Metodo che ritorna la lista delle parole già estratte.
     *
     * @return La lista delle parole estratte.
     */
    public List<String> getPickedWords() {
        return this.pickedWords;
    }

    /**
     * Metodo getter dell'attributo currentWord.
     *
     * @return La parola corrente.
     */
    public String getCurrentWord() {
        return this.currentWord;
    }

    /**
     * Metodo che imposta la parola corrente.
     *
     * @param word La parola corrente.
     */
    private void setCurrentWord(String word) {
        this.currentWord = word;
    }

    /**
     * Metodo che imposta la lista delle parole.
     *
     * @param word La lista da impostare.
     */
    private void setWords(List<String> words) {
        this.words = words;
    }

    /**
     * Metodo che imposta la lista delle parole già estratte.
     *
     * @param word La lista da impostare.
     */
    private void setPickedWords(List<String> words) {
        this.pickedWords = words;
    }

    /**
     * Metodo che ritorna una parola casuale dalla lista delle parole non ancora
     * estratte.
     *
     * @return Una parola casuale.
     */
    private String getRandomWord() {
        int rand = new Random().nextInt(getWords().size());
        return getWords().get(rand);
    }

    /**
     * Metodo che determina se la parola passata è uguale a quella corrente.
     *
     * @param userWord La parola da confrontare.
     * @return Se le parole corrispondono (True = le parole sono uguali).
     */
    public boolean isGuessedWord(String userWord) {
        userWord = userWord.trim();
        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
            System.out.println("Parola corrente: '" + getCurrentWord() + "', " + getCurrentWord().trim().length());
            System.out.println("Parola dell'utente: '" + userWord + "', " + userWord.length());
            System.out.println("Statement: " + userWord.equalsIgnoreCase(getCurrentWord()));
        }

        if (getCurrentWord().equalsIgnoreCase(userWord)) {

            if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {

                System.out.println("Parola indovinata");

            }

            return true;

        } else {

            if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {

                System.out.println("Parola sbagliata");

            }

            return false;
        }
    }

    /**
     * Metodo che ordina la lista di parole estratte in ordine alfabetico.
     */
    public void sortList() {
        Collections.sort(getPickedWords());
        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
            for (String pickedWord : getPickedWords()) {
                System.out.println("Word: " + pickedWord);
            }
        }
    }

    /**
     * Metodo che ritorna una parola casuale non ancora estratta.
     *
     * @return Parola casuale non ancora estratta.
     */
    public String getUniqueNewWord() {
        // Controllo se la lista di parole non estratte è vuota
        if (getWords().size() > 0) {
            String randWord = getRandomWord();
            getWords().remove(randWord);
            getPickedWords().add(randWord);
            setCurrentWord(randWord.trim());
            return getCurrentWord();
        }
        setWords(getPickedWords());
        setPickedWords(new ArrayList<>());
        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
            System.out.println("Parole terminate: ricarico la lista");
        }
        // Chiamata ricursiva
        return getUniqueNewWord();
    }

}
