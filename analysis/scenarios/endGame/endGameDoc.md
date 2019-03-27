# Scenario: fine partita
Matteo Forni & Bryan Beffa

- Swim Lane

![swim lane](endGame.jpg)

Se l'utente vorrà giocare un'altra partita dovrà attendere per l'inizio di essa, altrimenti lascierà il gioco e questo verrà riportato nella chat.

- Tabella del protocollo

| Message     | Direction | Description | Reply |
|----------|:-------------:|--------|------------|
| endGame() | S -> C       | Il server comunica al client che la partita è terminata | Partita terminata |
| playAgain()| C -> C | Il client decide se vuole giocare ancora o meno| 1. Il client vuole rigiocare|
||||2. Il client non vuole più giocare|
|newGame()| S -> C | Il server attende l'inizio di una nuova partita | -|
| quit() | C -> S | Il client desidera disconettersi | Il client si è disconnesso |
