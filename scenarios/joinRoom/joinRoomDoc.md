@author Jari Näser <br>
@author Giulio Bosco <br>
@version 18.3.2019

# Scenario 1: Join Room

![joinroom scenarios](joinRoom.png)

## procedura

In questa prima parte del progetto l’utente avvia l’applicazione client immettendo un Nickname da lui scelto. Una volta immesso questo viene mandato al server, controllato, e se rispetta tutte le restrizioni e non è ancora registrato viene accettato; Altrimenti il server ritorna un errore con il suo rispettivo messaggio.

## tabella dei protocolli di comunicazione

|Command|Direction|Description|Possible Response|
|-|-|-|
|Join(Nickname)|c&rarr;s|Client makes a request to join the server with the nickname given as a parameter|Joined() error(message)|
|Joined()|c&rarr;s|Confirms that the client joined the server.|-|
|Error(message)|c&rarr;s|Returns an error with it’s relative message.|-|
