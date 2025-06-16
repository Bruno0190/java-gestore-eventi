package org.milestone;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ProgrammaEventi {

/*Nel creare la classe ProgrammaEventi ci viene richiesto di inserire tra gli attributi eventi come un List. List è un interfaccia. Un interfaccia è come una classe completamente astratta. Una classe astratta non può essere usata per creare oggetti*/
    private String titolo;
    private List<Evento> eventi;

    public ProgrammaEventi(String titolo){

    /*Quando inizializziamo eventi creiamo un oggetto ArraList che implementa List */
        this.titolo = titolo;
        this.eventi = new ArrayList<>();

    }

    public String getTitolo() {
        return titolo;
    }

    /*Il primo metodo da scrivere è facile, perchè c'è solo da aggiungere tramite il metodo degli ArrayList .add() un nuovo oggetto Evento passato come parametro */
    public void addEvento(Evento evento){

        eventi.add(evento);

    }

    /*Il secondo metodo invece ci porta a creare un nuovo oggettoArrayList fatto apposto per includere solo gli oggetti Evento dell ArrayList precedente eventi filtrati per data, che infatti passiamo come parametro */

    public List<Evento> dataEventi(LocalDate data){

        List<Evento> eventi = new ArrayList<>();

        /*Il filtro è ottenuto tramite un ciclo for each e un blocco if */
        for (Evento evento : eventi) {

            if (evento.getData().equals(data)){

                eventi.add(evento);

            }

        }    
        return eventi;

    }

    /*Il terzo metodo ci richiede semplicemente il numero di eventi del programma che si ottiene con un semplice metodo tipico di ArrayList */

    public int numeroEventi(){

        return eventi.size();

    }

    /*Per eliminare gli oggetti dalla lista eventi ho provato prima tramite un ciclo for e metodo remove. Inizialmente partendo dal primo elemento non funzionava in quanto iniziando da zero mi saltava un elemento: tolto il primo elemento, il successivo ne prendeva il posto e non veniva chiamato correttamente. Allora ho invertito il ciclo partendo dall'ultimo e così non abbiamo saltato nulla. Credo potessi usare clear() ma ho preferito testarmi così*/
    public void svuotaEventi(){

        for(int i = eventi.size(); i >= 0; i--){

            eventi.remove(i);

        }

    }

    public String programma(){

        for(Evento evento : eventi){

            String forma = evento.getData() + " - " + evento.getTitolo();

        }
        return 
    }




}
