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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulazione di una partita con la classifica.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 2019-05-01
 */
public class Game {

    /**
     * Lista dei giocatori.
     */
    private List<Record> records = new ArrayList<>();

    /**
     * Classifica della partita.
     */
    private Ranking ranking;

    /**
     * Metodo costruttore dove si definiscono la lista di giocatori e la
     * classifica.
     *
     * @param records Lista dei giocatori.
     * @param ranking Classifica della partita.
     */
    public Game(List<Record> records, Ranking ranking) {
        this.records = records;
        this.ranking = ranking;
        this.ranking.rankPlayers(this.records);
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
     * Ottenimento della classifica.
     *
     * @return Classifica della partita.
     */
    public Ranking getRanking() {
        return this.ranking;
    }

    /**
     * Inserimento di un giocatore nella lista.
     *
     * @param record Giocatore da aggiungere.
     */
    public void addPlayer(Record record) {
        getRanking().insertRecord(getRecords(), record);
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
     * Metodo in cui si effettua il test di una partita con la classifica.
     *
     * @param args Argomenti a linea di comando.
     */
    public static void main(String[] args) {
        try {
            // Creazione dei giocatori, della classifica e della partita.
            List<Record> records = new ArrayList<>();
            Path path = Paths.get("data", "ranking.csv");
            Ranking ranking = new Ranking(path);
            Game game = new Game(records, ranking);

            // Inserimento dei giocatori nella partita.
            game.addPlayer(new Record("Giuseppe", 40));
            game.addPlayer(new Record("Mario", 10));
            game.addPlayer(new Record("Giovanni", 50));
            game.addPlayer(new Record("Luigi", 20));
            game.addPlayer(new Record("Franco", 30));

            // Scrittura della classifica.
            game.getRanking().writeRanking(game.getRecords());

            // Lettura della classifica.
            List<Record> rankingRecords = ranking.readRanking();

            // Stampa della classifica.
            for (Record record : rankingRecords) {
                System.out.println(record.getUsername() + " " + record.getScore());
            }

            System.out.println("");

            // Inserimento di altri giocatori nella partita.
            game.addPlayer(new Record("Paolo", 35));
            game.addPlayer(new Record("Carlo", 15));

            // Scrittura della classifica.
            game.getRanking().writeRanking(game.getRecords());

            // Lettura della classifica.
            rankingRecords = ranking.readRanking();

            // Stampa della classifica.
            for (Record record : rankingRecords) {
                System.out.println(record.getUsername() + " " + record.getScore());
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
