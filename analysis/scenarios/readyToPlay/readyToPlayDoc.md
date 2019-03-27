@author Jari Näser <br>
@author Giulio Bosco <br>
@version 18.3.2019

# Scenario Ready To Play

In questa seconda parte l’utente clicca il bottone sulla sua interfaccia per mandare il comando readyToPlay() al server che a sua volta lo metterà nella lista dei player pronti e lo aggiungerà alla chat dei giocatori presenti nella lista.

|Command|Description|Possible Response|
|-|-|-|
|readyToPlay()|Client tells the server that he’s ready to play.|Ack() Error(message)|
|Ack()|Confirms that the client is ready to play.|-|
|Error(message)|Returns an error with it’s relative message.|-|
