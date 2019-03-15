# Scribble - Analisi

giovedì, 28 febbraio 2019

09:20

Client si connette a server, [con più stanze], come [Guest], esso aspetta min. 2 giocatori [se 1 giocatore, CPU disegna basandosi su tratti fatti da altri giocatori in altre partite]

I tentativi di indovinare sono scritti pubblicamente

Quando viene indovinata una parola, i punti vengono distribuiti tra chi ha indovinato e chi ha disegnato

Inizio partita: il primo disegnatore è beccato a caso, poi chi indovina diventa disegnatore

[Eventualmente punteggio di chi indovina è inversamente proporzionale al numero di tratti o pixel]

[Stanza da tot player massimi (6?), dopo un tot di giocatori viene creata altra stanza (4?)]

Una sola stanza, chi entra nel gioco entra in quella.

Nickname per entrare mostrato agli altri, deve essere UNIVOCO. Risposta da server: nome già in uso, lunghezza massima, entrata riuscita.

Dopo nick vengo inserito in stanza, dove aspetto che finisca il gioco come spettatore.

Dopo fine partita abilito tutti player connessi, server dice a tutti client se deve indovinare o disegnare. Messaggio che dice quanto tempo manca dopo la fine della partita e l'inizio di quella nuova: mancano 3, 2, 1, … Se parola prima è stata indovinata devi disegnare, sennò indovinare.

Mano a mano che disegnatore disegna, ognuno riceve tratti. Chi indovina può scrivere solo un tentativo ogni tot secondi (configurabile).

Fine partita: server valuta parole tentate, distribuisce punti.

In una partita vengono sommati i tempi di tutti i round tra disegno e indovino, chi non indovina becca il tempo massimo. Alla fine della partita (n round, 1?), vince chi ha il tempo minore.

POLITICA DEI PUNTI ALLA FINE DEL ROUND DA DEFINIRE

## 14 marzo 2019
Due diagrammi: client, server con eventuale zona comune per classi uguali, collegati con protocollo di comunicazione tramite rete

Quello che sta disegnando: disegnatore
Quelli che indovinano: indovinatori

Architettura 1: disegnatore invia a server (no multicast), server invia a gruppo (multicast), ricevono indovinatori

Architettura 2: solo socket multicast (tutti inviano a tutti)

SOLO UDP / SOLO TCP / MIX UDP + TCP

Stato iniziale scenario: giocatore ha computer con applicazione accesa, sa IP e porta server.

Attori scenario: giocatore (persona), P1, computer client, C, computer server S.

Scenario 1:
P1 da inizio avviando applicazione
C richiede un nickname a P1
P1 inserisce nickname
C invia nickname a server: join(nickname, IP, porta)
S controlla se nickname è valido (non già usato, non troppo lungo)
S se nickname è valido aggiunge ad una lista di P (addToList) e scrive messaggio alla chat, poi manda conferma a C: joined
C abilita pulsante readyToPlay e la chat, poi conferma la connessione a P1
S se nickname non è valido manda avviso a C: error
Se riceve error da S, C manda avviso a P1, se P1 vuole provare di nuovo preme ancora pulsante connetti

Scenario 2:
P preme pulsante readyToPlay
C manda a S readyToPlay
S se P è registrato imposta utente a ready (updateList)
S aggiunge messaggio che P è ready a chat
S manda a C che P è ready: ack
C scrive messaggio a P
S se P non è registrato manda errore a C: err(msg)
C manda pop-up di errore a P

Scenario 3:
P1 = player,
C = client,
G = gruppo,
S = server

politica da definire per gestire nel server l'algoritmo che decide se e quando iniziare partita. Dopodiché:
S manda a tutti i C (gruppo multicast) che la partita sta per iniziare con identificativo di chi è disegnatore
C se S gli dice di essere disegnatore switcha in modalità disegnatore

Protocollo comunicazione:

| Message | Direction | Description | Reply
| :------------- | :------------- | :------------- | :------------- |
| join(nickname) | C --> S | C vuole chiedere a S di essere registrato | 1 = OK; 2 = Error già registrato; 3 = Error nome non valido(; 4 = OK client già registrato/connesso) |
| readyToPlay() | C --> S | inviato dal client per indicare che è pronto per giocare | 1 = OK; 2 = Error non registrato (msg) |

#### Per la prossima volta descrivere scenari mancanti con swim lane e protocollo di comunicazione per ogni scenario (quando la partita finisce [parola indovinata, fine tempo, mancano giocatori per continuare], quando utente vuole uscire)

PlantUML
