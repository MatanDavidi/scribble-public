
### Mattia Ruberto e Gabriele Alessi 19.03.2019

# Scenario 4

## Nello scenario 4 il giocatore si disconnete da una partita in corso.

![gatto](playerDisconnected.png)

### Se il giocatore è nella sessione sarà sempre disponibile un pulsante che permette di lasciare la partita mentre è in corso (disconnessione). 
## Procedura:  
### L'utente preme il pulsante (disconnection request) e il client chiede al server di lasciare la partita (leave()). Quindi se va tutto bene il server rimuove il player (removePlayer()) dal sistema e invia un check al client (removed()). Infine il server aggiorna la chat mostrando che il player ha appunto lasciato la partita (updateChat()) e manda l'OK all'utente. Se invece ci sono problemi con la disconnessione verrà mandato un messaggio di errore al client e quindi all'utente (error()).

## Tabella dei protocolli di comunicazione

| Message                     | Direction     | Description                                                                            |   Reply                 |
|:----------------------------|:--------------|:---------------------------------------------------------------------------------------|:------------------------|
| leave(nickname, ip, port)   | c --> s       | Client fa una richiesta di disconessione al server.                                    | 1. Ok <br> 2. Error(msg)|     
| removePlayer(nickname, ip)  | s --> s       | Il server rimuove il player dalla partita.                                             | -                       |     
| updateChat(LEAVED, nickname)| s --> s       | Il server aggiorna la chat togliendo l'utente.                                         | -                       | 
| removed                     | s --> c       | Il server ritorna al client la conferma che è stato rimosso.                           | -                       | 
| error(msg)                  | s --> c       | Errore ritornato dal server dopo aver ricevuto la richiesta di <br> disconnessione     | -                       |


