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
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che gestisce la classifica dei giocatori.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 2.0 (17.04.2019)
 */
public class Ranking {

    /**
     * Attributo che rappresenta il percorso di default del file csv.
     */
    public static final Path CSV_PATH = Paths.get("data", "ranking.csv");

    /**
     * Attributo che rappresenta il percorso del file csv che rappresenta la classifica.
     */
    private Path csvPath = CSV_PATH;

    /**
     * Metodo costruttore che inizializza la classe con il percorso del file csv.
     *
     * @param csvPath Percorso del file csv.
     * @throws java.io.IOException Se si verifica un'eccezione di input o di output.
     */
    public Ranking(Path csvPath) throws IOException {
        setCsvPath(csvPath);
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
     * Metodo che setta il percorso del file csv.
     *
     * @param csvPath Percorso del file csv.
     */
    private void setCsvPath(Path csvPath) throws IOException {
        if (Files.exists(csvPath) && !Files.notExists(csvPath)) {
            if (Files.isReadable(csvPath)) {
                this.csvPath = csvPath;
            } else {
                throw new IOException("File not readable!");
            }
        } else {
            throw new IOException("File not accessible!");
        }
    }

    /**
     * Metodo che ordina la classica per il punteggio.
     *
     * @param records Lista dei giocatori.
     */
    public void rankPlayers(List<Record> records) {
        boolean thereIsBubbling;
        Record temp;
        do {
            thereIsBubbling = false;
            for (int j = 1; j < records.size(); j++) {
                if (records.get(j - 1).getScore() < records.get(j).getScore()) {
                    temp = records.get(j - 1);
                    records.set(j - 1, records.get(j));
                    records.set(j, temp);
                    thereIsBubbling=true;
                }
            }
        }while(thereIsBubbling);
    }

    /**
     * Metodo che inserisce il giocatore nella lista nell'ordine giusto.
     *
     * @param records Lista dei giocatori.
     */
    public void insertionSort(List<Record> records) {
        boolean flag;
        for (int i = 1; i < records.size(); i++) {
            flag = true;
            Record key = records.get(i);
            for (int j = i - 1; j >= 0 && flag; j--) {
                if (key.getScore() > records.get(j).getScore()) {
                    records.set(j + 1, records.get(j));
                    if (j == 0) {
                        records.set(0, key);
                    }
                } else {
                    records.set(j + 1, key);
                    flag = false;
                }
            }
        }
    }

    /**
     * Lettura della classifica dal file csv.
     *
     * @return La classifica in una stringa.
     */
    public List<Record> readRanking() {
        List<Record> rankingPlayers = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(getCsvPath());
            for(String line : lines){
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
    public void writeRanking(List<Record> records) {
        try {
            try{
                List<String> lines = new ArrayList<>();
                for (Record player : records) {
                    lines.add((player.getUsername() + "," + player.getScore()));
                }
                Files.write(getCsvPath(), lines);
            }catch(FileNotFoundException fn){
                System.out.println("Error: " + fn.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
