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

import samt.scribble.server.player.Record;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che gestisce la classifica dei giocatori.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 2019-05-04
 */
public class RankingModule {

    /**
     * Costante che definisce il separatore tra username e punteggio nel file.
     */
    public static final String SEP = ",";

    /**
     * Attributo che rappresenta la lista dei giocatori nella classifica.
     */
    private List<Record> records = new ArrayList<>();

    /**
     * Attributo che rappresenta il gestore del file.
     */
    private FileManager fileManager;

    /**
     * Metodo costruttore dove si definisce il percorso del file.
     *
     * @param filePath Percorso del file.
     * @throws java.io.IOException Se si verifica un'eccezione di input o di
     * output.
     */
    public RankingModule(Path filePath) throws IOException {
        this.fileManager = new FileManager(filePath);
    }

    /**
     * Metodo costruttore dove si definiscono percorso del file e giocatori.
     *
     * @param filePath Percorso del file.
     * @param records Giocatori della classifica.
     * @throws java.io.IOException Se si verifica un'eccezione di input o di
     * output.
     */
    public RankingModule(Path filePath, List<Record> records) throws IOException {
        this(filePath);
        setRecords(records);
    }

    /**
     * Metodo che ritrona i giocatori della classifica.
     *
     * @return Giocatori della classifica.
     */
    public List<Record> getRecords() {
        return this.records;
    }

    /**
     * Metodo che imposta i giocatori nella classifica.
     *
     * @param csvPath Giocatori della classifica.
     */
    private void setRecords(List<Record> records) {
        this.records = records;
    }

    /**
     * Metodo che ritorna il gestore del file della classifica.
     *
     * @return Gestore del file.
     */
    public FileManager getFileManager() {
        return this.fileManager;
    }

    /**
     * Inserimento di un giocatore nella posizione giusta della classifica.
     *
     * @param record Giocatore da inserire.
     */
    public void addPlayer(Record record) {
        boolean isDone = false;
        for (int i = 0; i < getRecords().size() && !isDone; i++) {
            if (record.getScore() >= getRecords().get(i).getScore()) {
                getRecords().add(i, record);
                isDone = true;
            } else if (i == getRecords().size() - 1) {
                getRecords().add(i + 1, record);
            }
        }
    }

    /**
     * Rimozione di un giocatore dalla lista.
     *
     * @param record Giocatore da rimuovere.
     */
    public void removePlayer(Record record) {
        getRecords().remove(record);
    }

    /**
     * Metodo che ordina la classifica per il punteggio.
     *
     * @param records Lista dei giocatori.
     */
    private void rankPlayers(List<Record> records) {
        boolean isDone = false;
        Record temp = new Record();
        do {
            isDone = true;
            for (int i = 0; i < records.size() - 1; i++) {
                if (records.get(i).getScore() > records.get(i + 1).getScore()) {
                    temp = records.get(i);
                    records.set(i, records.get(i + 1));
                    records.set(i + 1, temp);
                    isDone = false;
                }
            }
        } while (!isDone);
    }

    /**
     * Lettura della classifica dal file.
     *
     * @return La lista della classifica.
     * @throws java.io.IOException Se si verifica un'eccezione di input o di
     * output.
     */
    public List<Record> readRanking() throws IOException {
        List<Record> records = new ArrayList<>();
        List<String> lines = getFileManager().readFile();
        for (String line : lines) {
            String username = line.split(SEP)[0];
            int score = Integer.parseInt(line.split(SEP)[1]);
            Record record = new Record(username, score);
            records.add(record);
        }
        return records;
    }

    /**
     * Metodo che scrive la lista dei giocatori nel file.
     *
     * @param records Lista dei giocatori.
     * @throws java.io.IOException Se si verifica un'eccezione di input o di
     * output.
     */
    public void writeRanking(List<Record> records) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Record player : records) {
            lines.add((player.getUsername() + SEP + player.getScore()));
        }
        getFileManager().writeFile(lines);
    }

}
