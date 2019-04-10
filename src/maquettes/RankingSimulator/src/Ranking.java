
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
/**
 * Players ranking management.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 1.1 (10.04.2019)
 */
public class Ranking {

    /**
     * Default path of the ranking csv file.
     */
    private Path csvPath = null;

    /**
     * List of players.
     */
    public List<Player> players = new ArrayList();

    /**
     * Constructor method where the path of the csv file is defined.
     *
     * @param csvPath Path of the csv file.
     */
    public Ranking(Path csvPath) throws IOException {
        if (Files.exists(csvPath)) {
            if (Files.isReadable(csvPath)) {
                setCsvPath(csvPath);
            } else {
                throw new IOException("File non leggibile!");
            }
        } else {
            throw new IOException("File non accessibile!");
        }
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
    private void setCsvPath(Path csvPath) {
        this.csvPath = csvPath;
    }

    /**
     * Add player to the List.
     *
     * @param player attribute representing the player to be added.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Remove player to the list.
     *
     * @param player attribute representing the player to be removed.
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Method that orders players by their score.
     */
    public void sortPlayers() {
        int n = players.size();
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (players.get(j - 1).getScore() > players.get(j).getScore()) {
                    //swap elements  
                    temp = players.get(j - 1).getScore();
                    players.get(j - 1).setScore(players.get(j).getScore());
                    players.get(j).setScore(temp);
                }

            }
        }
    }

    /**
     * Method that takes the players and saves a ranking in the text file.
     */
    public String readingRankings() {
        try {
            byte[] bytes = Files.readAllBytes(csvPath);
            String ranking = new String(bytes);
            return ranking;
        } catch (IOException ie) {
            System.out.println("Error: " + ie.getMessage());
        }
        return "";
    }

    /**
     * Method that takes the players and saves a ranking in the text file.
     */
    public void writingRankings() {
        try {
            FileWriter w = new FileWriter(csvPath.toString());
            BufferedWriter b = new BufferedWriter(w);
            b.write("Username, Score\n");
            for (Player player : players) {
                b.write(player.getUsername() + "," + player.getScore());
            }
            b.flush();
        } catch (IOException ie) {
            System.out.println("Error: " + ie.getMessage());
        }
    }

    /**
     * Creation of the csv file.
     */
    private void createCsv() throws IOException {
        // Get the file.
        File csvFile = new File(getCsvPath().toString());
        // Create the file if not exists.
        if (csvFile.createNewFile()) {
            System.out.println("File created.");
        } else {
            System.out.println("File not created: already exists.");
        }
        // Write ranking csv header.
        FileWriter writer = new FileWriter(csvFile);
        writer.write("username,score");
        writer.close();
    }
}
