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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Players ranking management.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 1.3 (17.04.2019)
 */
public class Ranking {

    /**
     * Default path of the ranking csv file.
     */
    public static final Path CSV_PATH = Paths.get("data", "ranking.csv");

    /**
     * Path of the ranking csv file.
     */
    private Path csvPath = CSV_PATH;

    /**
     * Constructor method where the path of the csv file is defined.
     *
     * @param csvPath Path of the csv file.
     * @throws java.io.IOException If an input or output exception is occurred.
     */
    public Ranking(Path csvPath) throws IOException {
        setCsvPath(csvPath);
    }

    /**
     * Get the path of the csv file.
     *
     * @return Path of the csv file.
     */
    public Path getCsvPath() {
        return this.csvPath;
    }

    /**
     * Set the path of the csv file.
     *
     * @param csvPath Path of the csv file.
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
     * Rank players by their score.
     *
     * @param players List of players.
     */
    public void rankPlayers(List<Player> players) {
        int listSize = players.size();
        int temp = 0;
        for (int i = 0; i < listSize; i++) {
            for (int j = 1; j < (listSize - i); j++) {
                if (players.get(j - 1).getScore() < players.get(j).getScore()) {
                    temp = players.get(j - 1).getScore();
                    players.get(j - 1).setScore(players.get(j).getScore());
                    players.get(j).setScore(temp);
                }
            }
        }
    }

    /**
     * Read the ranking in the file.
     *
     * @return The ranking in the file.
     */
    public String readRanking() {
        String ranking = "";
        try {
            byte[] bytes = Files.readAllBytes(getCsvPath());
            ranking = new String(bytes);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return ranking;
    }

    /**
     * Write the ranking in the file.
     *
     * @param players List of players.
     */
    public void writeRanking(List<Player> players) {
        try {
            for (Player player : players) {
                Files.write(getCsvPath(), (player.getUsername() + ", " + player.getScore() + "\n\r").getBytes());
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
