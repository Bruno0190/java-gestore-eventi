package org.milestone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

    private static final DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Affinchè gli attributi della classe non siano accessibili direttamente è buona regola scriverli come private, in modo da potervi poi accedere tramite getter e setter, quest'ultimo consente di filtrare le modifiche del valore con opportuni controlli. Certamente, se sapessimo che la classe deve essere ereditata si può usare protected, ma spesso ciò è più utile qualora la classe fosse astratta.
    private String titolo;
    //Per l'attributo data mi sono avvalso della classe LocalDate del package java.time (che infatti è stato importato in alto.)
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    //Nel costruttore passiamo come parametri gli attributi necessari per istanziare un oggetto. La traccia dice di inizializzare postiPrenotati a 0, ergo non è necessario includere quest'attributo tra i parametri.
    public Evento(String titolo, LocalDate data, int postiTotali){

        //Ricordiamo che "this" occorre perchè titolo, data, postiTotali e postiPrenotati si riferiscano proprio ai rispettivi attributi sopra elencati della classe. A destra dell'uguale infatti titolo, data e postiTotali si riferiscono ai parametri del costruttore. "this" non viene usato solo se il parametro del costruttore non corrisponde all'attributo della classe.
        setTitolo(titolo);
        this.postiPrenotati = 0;

        /*Di seguito abbiamo dei controlli che come secondo traccia verificano che la data immessa come parametro non sia antecedente a quella odierna e che i posti totali del posto ove avverrà luogo l'evento non siano negativi, ma nemmeno nulli.
        I controlli funzionano grazie a "IllegalArgumentException" che di fatto è una sottoclsse della classe Exception. Infatti per essere istanziata si "lancia" con throw e quindi creata con new*/       
        setData(data);

        if (postiTotali<=0){

            throw new IllegalArgumentException("Devi inserire numeri maggiori di zero");

        }
        this.postiTotali = postiTotali;
    }

    //Si passa ai metodi getter e setter come da traccia. Questi metodi sono public ovviamente perchè sono i metodi tramite i quali nel Main possiamo accedere e modificare gli attributi. getTitolo consente di leggere il titolo, infatti "ritorna" la stringa titolo. setTitolo invece consente di reimpostare il titolo che è una stringa, non tornando nulla tra le graffe, è un metodo void. Stesso discorso per gli altri metodi
    public String getTitolo(){

        return titolo;

    }

    public void setTitolo(String titolo){

        this.titolo = titolo;

    }

    public LocalDate getData(){

        return data;

    }
    //Il controllo della data corretta deve assolutamente essere inserito anche nel setter
    public void setData(LocalDate data){

        if (data.isEqual(LocalDate.now()) || data.isAfter(LocalDate.now())){

            this.data = data;

        } else {

            throw new IllegalArgumentException("Non puoi inserire una data passata");

        }

    }

    public int getPostiTotali(){

        return postiTotali;

    }

    public int getPostiPrenotati(){

        return postiPrenotati;

    }
    //Adesso creiamo i tre metodi finali prenota, disdici e toString indicati nello Step 1 della traccia. Il primo, soddisfatta una delle due  condizioni, aggiunge 1 posto aggiornando il valore di postiPrenotati
    public void prenota(){
        prenota(1);
    }
    public void prenota(int numeroPosti){

        if (postiTotali > postiPrenotati && !data.isBefore(LocalDate.now())){

            postiPrenotati += numeroPosti;

        } else {

            throw new IllegalArgumentException("Errore: posti esauriti o evento già passato.");

        }

    }

    public void disdici(){

        if (postiPrenotati > 0 && !data.isBefore(LocalDate.now())){

            postiPrenotati --;

        } else {

            throw new IllegalArgumentException("Errore: nessuna prenotazione da cancellare o evento già passato.");

        }

    }

    //Con @Override andiamo a riscrivere il metodo toString() nell'occasione di utilizzo della nostra classe Evento. All'interno del metodo così rifatto, metto in uso la classe DateTimeFormatter dopo aver richiamato il package java.time.format. Quindi creo l'oggetto formaData su cui uso il metodo ofPattern per dare lo "stampino" in cui voglio riversare giorno, mese e anno e quindi creo la nuova variabile stringa dataFormattata letteralmente formattando data con formatData. Infine ritorno il messaggio come da traccia.
    @Override
    public String toString(){

        
        String dataFormattata = data.format(formatData);

        return dataFormattata + " - " + titolo;

    }


}
