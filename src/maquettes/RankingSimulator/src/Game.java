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
 * Simulation of a game.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 2.0 (17.04.2019)
 */
public class Game {

    /**
     * List of players.
     */
    private List<Record> records = new ArrayList<>();

    /**
     * Ranking of the game.
     */
    private Ranking ranking;

    /**
     * Constructor method where players and ranking of the game are defined.
     *
     * @param records List of players of the game.
     * @param ranking Ranking of the game.
     */
    public Game(List<Record> records, Ranking ranking) {
        this.records = records;
        this.ranking = ranking;
        this.ranking.rankPlayers(this.records);
    }

    /**
     * Get the list of players.
     *
     * @return List of players of the game.
     */
    public List<Record> getPlayers() {
        return this.records;
    }

    /**
     * Get the ranking of the game.
     *
     * @return The ranking of the game.
     */
    public Ranking getRanking() {
        return this.ranking;
    }

    /**
     * Add player to the List.
     *
     * @param player attribute representing the player to be added.
     */
    public void addPlayer(Record records) {
        getPlayers().add(records);
        getRanking().insertionSort(getPlayers());
    }

    /**
     * Remove player to the list.
     *
     * @param player attribute representing the player to be removed.
     */
    public void removePlayer(Record records) {
        getPlayers().remove(records);
    }

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            // Creation of players, ranking and game.
            List<Record> records = new ArrayList<>();
            Path path = Paths.get("data", "ranking.csv");
            Ranking ranking = new Ranking(path);
            Game game = new Game(records, ranking);
            
            // Adding players to the game with insertion sort.
            game.addPlayer(new Record("Giuseppe", 100));
            game.addPlayer(new Record("Mario", 220));
            game.addPlayer(new Record("Francesco", 50));
            game.addPlayer(new Record("Giovanni", 1110));
            game.addPlayer(new Record("Luigi", 500));
            
            // Writing the ranking.
            game.getRanking().writeRanking(game.getPlayers());
            
            // Reading the generated ranking.
            List<Record> rankingRecords = ranking.readRanking();
            
            // Printing the ranking
            for(Record playerRanking : rankingRecords){
                System.out.println(playerRanking.getUsername()+" "+playerRanking.getScore());
            }        
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
