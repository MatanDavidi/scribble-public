
/**
 * Classe che rappresenta il giocatore.
 * @author Mattia Ruberto
 * @version 09.04.2019
 */
public class Player {
    /**
     * Attributo che rappresenta il nome del giocatore.
     */
    private String username;
    /**
     * Attributo che rappresenta il punteggio totale del giocatore.
     */
    private int score;
    /**
     * Costruttore che inizzializza il giocatore con l'username.
     * @param username attributo che rappresenta il nome utente del giocatore.
     */
    public Player(String username){
        this.username = username;
    }
    /**
     * Costruttore che inizzializza il giocatore con l'username e i punti totali.
     * @param username attributo che rappresenta il nome utente del giocatore.
     * @param score attributo che rappresenta il punteggio totale del giocatore.
     */
    public Player(String username, int score){
        this.username = username;
        this.score = score;
    }
    /**
     * Metodo che setta l'username con il valore che gli viene passato.
     * @param username attributo che rappresenta il nome utente del giocatore.
     */
    public void setUsername(String username){
        this.username = username;
    }
    /**
     * Metodo che ritorna il nome utente del giocatore.
     * @return il nome utente del giocatore.
     */
    public String getUsername(){
        return this.username;
    }
    /**
     * Metodo che setta il punteggio del giocatore con il valore che gli viene passato.
     * @param score attributo che rappresenta il nome utente del giocatore.
     */
    public void setScore(int score){
        this.score = score;
    }
    /**
     * Metodo che ritorna il punteggio totale del giocatore.
     * @return il nome utente del giocatore.
     */
    public int getScore(){
        return this.score;
    }

}
