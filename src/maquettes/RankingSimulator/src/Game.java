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
    private List<Player> players = new ArrayList<>();

    /**
     * Ranking of the game.
     */
    private Ranking ranking;

    /**
     * Constructor method where players and ranking of the game are defined.
     *
     * @param players List of players of the game.
     * @param ranking Ranking of the game.
     */
    public Game(List<Player> players, Ranking ranking) {
        this.players = players;
        this.ranking = ranking;
        this.ranking.rankPlayers(this.players);
    }

    /**
     * Get the list of players.
     *
     * @return List of players of the game.
     */
    public List<Player> getPlayers() {
        return this.players;
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
    public void addPlayer(Player player) {
        getPlayers().add(player);
        getRanking().insertionSort(getPlayers());
    }

    /**
     * Remove player to the list.
     *
     * @param player attribute representing the player to be removed.
     */
    public void removePlayer(Player player) {
        getPlayers().remove(player);
    }

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            // Creation of players, ranking and game.
            List<Player> players = new ArrayList<>();
            Path path = Paths.get("data", "ranking.csv");
            Ranking ranking = new Ranking(path);
            Game game = new Game(players, ranking);
            // Adding players to the game with insertion sort.
            game.addPlayer(new Player("Giuseppe", 100));
            game.addPlayer(new Player("Mario", 220));
            game.addPlayer(new Player("Francesco", 50));
            game.addPlayer(new Player("Giovanni", 1110));
            game.addPlayer(new Player("Luigi", 500));
            // Writing the ranking.
            game.getRanking().writeRanking(game.getPlayers());
            // Reading and printing the generated ranking.
            System.out.println(ranking.readRanking());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
