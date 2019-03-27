# Scenario 4: Disconnessione utente

**Mattia Ruberto e Gabriele Alessi, 27.03.2019**

Nello scenario 4 il giocatore si disconnette da una partita in corso.

![playerDisconnected](playerDisconnected.png)
 
## Procedura:  
 L'utente preme il pulsante (disconnection request) e il client chiede al server di lasciare la partita (leave()). Quindi se va tutto bene il server rimuove il player (removePlayer()) dal sistema e invia un check al client (removed()). Infine il server aggiorna la chat mostrando che il player ha appunto lasciato la partita (updateChat()) e manda l'OK all'utente.

## Tabella dei protocolli di comunicazione

| Message | Direction | Description | Reply |
| --- | --- | --- | --- |
| leave(nickname, ip, port) | c &rarr; s | Client fa una richiesta di disconessione al server. | - |        
| removed() | s &rarr; c | Il server ritorna al client la conferma che è stato rimosso. | - |