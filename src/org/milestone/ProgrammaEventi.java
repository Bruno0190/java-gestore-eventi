package org.milestone;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Comparator;

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
        List<Evento> eventiFiltrati = new ArrayList<>();
        for (Evento evento : this.eventi) {
            if (evento.getData().equals(data)){
                eventiFiltrati.add(evento);
            }
        }    
        return eventiFiltrati;
    }
    // algo di min, max, avg (media), filtro (trovo elementi in base a una char); reduce (es: riduci una lista di eventi ad una stringa)



    /*Il terzo metodo ci richiede semplicemente il numero di eventi del programma che si ottiene con un semplice metodo tipico di ArrayList */

    public int numeroEventi(){

        return eventi.size();

    }

    /*Per eliminare gli oggetti dalla lista eventi ho provato prima tramite un ciclo for e metodo remove. Inizialmente partendo dal primo elemento non funzionava in quanto iniziando da zero mi saltava un elemento: tolto il primo elemento, il successivo ne prendeva il posto e non veniva chiamato correttamente. Allora ho invertito il ciclo partendo dall'ultimo e così non abbiamo saltato nulla. Credo potessi usare clear() ma ho preferito testarmi così*/
    public void svuotaEventi(){

        for(int i = eventi.size(); i >= 0; i--){

            eventi.remove(i);

        }
        for (int i=0;i<eventi.size();i++) {

            eventi.remove(0);
        }

    }

    public String programma() {

        // Creo un oggetto StringBuilder per costruire pezzo per pezzo la stringa finale. StringBuilder è una classe che consente di creare una dinamica sequenza di caratteri
        StringBuilder forma = new StringBuilder();

        //.append() è il metodo con il quale letteralmente appendo nuovi parti all'oggetto StringBuilder
        forma.append(titolo + "\n");

        // Ordino la lista di eventi in base alla data, utilizzando il metodo .sort()
        // Comparator è un oggetto che letteralmente compare le cose. Si usa il metodo.comparing() in cui si inserisce come parametri quali oggetti comparare e in base a quale filtro.
        // Evento::getData è una scorciatoia per dire, usa .getData() per prendere le date di ogni oggetto Evento. Ovviamente l'oggetto evento è inteso appartenente alla lista eventi.
        eventi.sort(Comparator.comparing(Evento::getData));

        // Per ogni evento ordinato, aggiungo una riga con data e titolo
        for (Evento evento : eventi) {
            forma.append(evento.getData() + " - " + evento.getTitolo() + "\n");
        }

        // Ritorno la stringa completa (titolo + elenco degli eventi)
        return forma.toString();
    }

}
