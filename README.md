# TicTacToe
Gioco del tris, implementato con l'algoritmo MinMax


Funzionamento della funzione minmax

Il metodo viene invocato ogni volta che deve eseguire una mossa il computer
Il metodo prende due parametri in ingresso: il grado di profondità (0 ad ogni chiamata) e il giocatore per il quale si vuole calcolare il risultato ottimo con la minmax(PLAYER_X appunto).
L'algoritmo è ricorsivo, e pertanto presenta 3 casi base:
1. Situazione di vittoria per il PLAYER_X
2. Situazione di vittoria per il PLAYER_O
3. Situazione di pareggio tra le parti

Il suo funzionamento è il seguente:
Dopo aver assegnato il valore più grande possibile alla variabile min (quindi ciò che equivale a +infinito) e dopo aver assegnato il valore più piccolo possibile alla variabile max (quindi ciò che equivale a -infinito), viene eseguito un ciclo.
Il ciclo continua la sua esecuzione fintanto che ci sono celle disponibili in cui eseguire una mossa.
La mossa viene scelta ogni volta in base alla prima cella libera: dopodichè viene eseguita la logica vera e propria del metodo, a seconda del turno (PLAYER_X o PLAYER_O)

Di fatto l'algoritmo va occupando tutte le caselle disponibili nella scacchiera, cercando di capire se la soluzione porta ad una situazione di vantaggio (vittoria PLAYER_X), di svantaggio (vittoria PLAYER_O) o di pareggio.
Scelta una mossa, tramite la funzione placeMove(point, player) vengono eseguite (solo a livello teorico) le seguenti, fino a che non ci sono più celle disponibili. Si osserva dunque il risultato ottenuto (0, 1 o -1) e si seleziona il punteggio meno basso possibile, ovvero quello che più avvicina il PLAYER_X alla vittoria.
ATTENZIONE! Non viene selezionato il punteggio uguale a 1, perchè l'algoritmo minmax prevede che anche il giocatore avversario esegua una mossa, che sia la più vantaggiosa per lui, e di conseguenza la più svantaggiosa per noi. Per questo motivo viene scelta sempre la mossa che porta al valore meno piccolo possibile.
Il ciclo continua la sua iterazione cercando di ottenere una combinazione di soli 1, così il valore più piccolo possibile è appunto 1. Quello che accade è che, ad ogni iterazione, si tentano le varie combinazioni possibili, data la mossa scelta da PLAYER_X, per vedere quali sono vincenti e quali perdenti.
L'algoritmo continua la sua esecuzione fintanto che non trova una combinazione di mosse che lo porti alla vittoria (ottenimento del valore di ritorno pari a 1) oppure che non lo porti alla sconfitta (ottenimento valore di ritorno pari a 0). Qualora tutte le combinazioni dell'algoritmo portino come risultato -1, l'algoritmo sarà obbligato nella scelta.
