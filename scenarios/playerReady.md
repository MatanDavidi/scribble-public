## Scenario 2 - Analysis
This scenario starts when a player has already registered to a server and has pressed to button to signal to the server and to other players that they're ready to start the game.

![Swim lane diagram](playerReady.png)

Once the "ready" button is pressed, the client sends a message to the server signalling that the player is ready. If the player is actually registered in the server, the server then updates its players list, setting the player to "ready", and appends a message to the chat for everyone to see. Afterwards, it sends an acknowledgement message to the client, which is then forwarded to the player.
However, if the player is not registered, it simply sends an error message to the client, which is forwarded to the player.

### Communication protocol

| Message | Direction | Description | Reply
| :------------- | :------------- | :------------- | :------------- |
| readyToPlay() | C --> S | sent by the client to indicate that they are ready to play | 1 = OK; 2 = Error: player not registered (msg) |
