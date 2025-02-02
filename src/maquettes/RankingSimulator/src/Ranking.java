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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che gestisce la classifica dei giocatori.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 2019-05-01
 */
public class Ranking {
    /**
     * Attributoc che rappresenta la lista dei giocatori.
     */
    private List<Record> records = new ArrayList<>();

    /**
     * Attributo che rappresenta il percorso di default del file csv.
     */
    public static final Path CSV_PATH = Paths.get("data", "ranking.csv");

    /**
     * Attributo che rappresenta il percorso del file csv che contiene la
     * classifica.
     */
    private Path csvPath = CSV_PATH;

    /**
     * Metodo costruttore dove si definisce il percorso del file.
     *
     * @param csvPath Percorso del file csv.
     * @throws java.io.IOException Se si verifica un'eccezione di input o di
     * output.
     */
    public Ranking(Path csvPath) throws IOException {
        setCsvPath(csvPath);
    }

    /**
     * Ottenimento della lista dei giocatori.
     *
     * @return Lista dei giocatori.
     */
    public List<Record> getRecords() {
        return this.records;
    }
    
    /**
     * Inserimento di un giocatore nella lista.
     *
     * @param record Giocatore da aggiungere.
     */
    public void addPlayer(Record record) {
        insertRecord(record);
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
     * Metodo che ritrona il percorso del file csv.
     *
     * @return Percorso del file csv.
     */
    public Path getCsvPath() {
        return this.csvPath;
    }

    /**
     * Metodo che imposta il percorso del file csv.
     *
     * @param csvPath Percorso del file csv.
     */
    private void setCsvPath(Path csvPath) throws IOException {
        if (Files.exists(csvPath) && !Files.notExists(csvPath)) {
            if (Files.isReadable(csvPath)) {
                this.csvPath = csvPath;
            } else {
                throw new IOException("File non leggibile!");
            }
        } else {
            throw new IOException("File non accessibile!");
        }
    }

    /**
     * Metodo che ordina la classica per il punteggio.
     *
     * @param records Lista dei giocatori.
     */
    public void rankPlayers(List<Record> records) {
        boolean isDone;
        Record temp;
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
     * Metodo che ordina il giocatore appena inserito nella posizione giusta
     * della classifica.
     *
     * @param records Lista dei giocatori.
     * @param newRecord Giocatore appena inserito.
     */
    public void insertRecord(Record newRecord) {
        boolean isDone = false;
        int index = 0;
        for (int i = 0; i < records.size() && !isDone; i++) {
            if (newRecord.getScore() >= records.get(i).getScore()) {
                index = i;
                isDone = true;
            } else if (i == records.size() - 1) {
                index = i + 1;
            }
        }
        records.add(index, newRecord);
    }

    /**
     * Lettura della classifica dal file csv.
     *
     * @return La lista della classifica.
     */
    public List<Record> readRanking() {
        List<Record> rankingPlayers = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(getCsvPath());
            for (String line : lines) {
                String[] arguments = line.split(",");
                Record player = new Record(arguments[0], Integer.parseInt(arguments[1]));
                rankingPlayers.add(player);
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return rankingPlayers;
    }

    /**
     * Metodo che scrive la lista dei giocatori nel file csv.
     *
     * @param records Lista dei giocatori.
     */
    public void writeRanking() {
        try {
            try {
                List<String> lines = new ArrayList<>();
                for (Record player : records) {
                    lines.add((player.getUsername() + "," + player.getScore()));
                }
                Files.write(getCsvPath(), lines);
            } catch (FileNotFoundException fn) {
                System.out.println("Error: " + fn.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
