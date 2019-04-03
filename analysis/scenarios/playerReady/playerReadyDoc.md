##### [Scenario precedente](../joinRoom/joinRoomDoc.md)

Questo scenario inizia dopo la fine dello scenario precedente, ovvero [joinRoom](../joinRoom/joinRoomDoc.md), quando un giocatore si è già registrato su un server e ha premuto il pulsante per segnalare al server e agli altri giocatori che è pronto per iniziare il gioco.

# Scenario 2: playerReady

Autori: Matan Davidi, Paolo Gübeli

## Swim Lane

![PlayerReady swim lane diagram](playerReady.png)

## Descrizione

Una volta premuto il pulsante "pronto", il client invia un messaggio al server segnalando che il giocatore è pronto. 

## Se il giocatore è registrato:
Se il giocatore è effettivamente registrato nel server, il server aggiorna la sua lista di giocatori, impostandolo su "pronto", e aggiunge un messaggio alla chat per permettere a tutti di vederlo. 

Successivamente, invia un messaggio di conferma al client, che viene poi inoltrato al giocatore.

## Se il giocatore non è registrato:
Tuttavia, se il giocatore non è registrato, invia semplicemente un messaggio di errore al client, che viene inoltrato al giocatore.

### Protocollo di comunicazione

| Messaggio | Direzione | Descrizione | Risposta
| :------------- | :------------- | :------------- | :------------- |
| readyToPlay() | C --> S | sent by the client to indicate that they are ready to play | 1 = OK; 2 = Error: player not registered (msg) |
