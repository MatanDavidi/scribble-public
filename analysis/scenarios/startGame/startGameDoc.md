##### [Scenario precedente](../playerReady/playerReadyDoc.md)

Questo scenario inizia dopo la fine dello scenario precedente, ovvero [playerReady](../playerReady/playerReadyDoc.md), quando ci sono abbastanza giocatori per iniziare la partita e i giocatori hanno premuto il pulsante per segnalare al server e agli altri giocatori di essere pronti e che quindi il gioco per iniziare.

## Scenario 3: startGame

Autori: Thor Düblin

## Swim Lane

![startGame swim lane diagram](startGame.png)

## Descrizione

La partita inizia, al client verrà mandato un messaggio, che segnalerà l'inizio della partita. A questo punto la partita sarà iniziata.

### Protocollo di comunicazione

| Messaggio | Direzione | Descrizione | Risposta
| :------------- | :------------- | :------------- | :------------- |
| startGame() | S --> C | Il server avvisa il client che il gioco è iniziato | - |
