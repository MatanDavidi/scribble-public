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

Dopo fine partita abilito tutti player connessi, server dice a tutti client se deve indovinare o disegnare. Messaggio che dice quanto tempo manca dopo la fine della partita e l&#39;inizio di quella nuova: mancano 3, 2, 1, … Se parola prima è stata indovinata devi disegnare, sennò indovinare.

Mano a mano che disegnatore disegna, ognuno riceve tratti. Chi indovina può scrivere solo un tentativo ogni tot secondi (configurabile).

Fine partita: server valuta parole tentate, distribuisce punti.

In una partita vengono sommati i tempi di tutti i round tra disegno e indovino, chi non indovina becca il tempo massimo. Alla fine della partita (n round, 1?), vince chi ha il tempo minore.

POLITICA DEI PUNTI ALLA FINE DEL ROUND DA DEFINIRE