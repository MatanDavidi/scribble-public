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
    public static void main(String[] args) {
        try {
            // Creazione dei giocatori, della classifica e della partita.
            List<Record> records = new ArrayList<>();
            Path path = Paths.get("data", "ranking.csv");
            Ranking ranking = new Ranking(path);

            // Inserimento dei giocatori nella partita.
            ranking.addPlayer(new Record("Giuseppe", 40));
            ranking.addPlayer(new Record("Mario", 10));
            ranking.addPlayer(new Record("Giovanni", 50));
            ranking.addPlayer(new Record("Luigi", 20));
            ranking.addPlayer(new Record("Franco", 30));

            // Scrittura della classifica.
            ranking.writeRanking();

            // Lettura della classifica.
            List<Record> rankingRecords = ranking.readRanking();

            // Stampa della classifica.
            for (Record record : rankingRecords) {
                System.out.println(record.getUsername() + " " + record.getScore());
            }

            System.out.println("");

            // Inserimento di altri giocatori nella partita.
            ranking.addPlayer(new Record("Paolo", 35));
            ranking.addPlayer(new Record("Carlo", 15));

            // Scrittura della classifica.
            ranking.writeRanking();

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
