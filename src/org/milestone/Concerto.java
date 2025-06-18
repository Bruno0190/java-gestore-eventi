package org.milestone;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/*La traccia richiede di creare una classe che estende Evento. In Java quando una classe estende un altra classe si dice che eredita gli attributi e i metodi di quella classe, potendo in più aggiungere nuovi attributi e metodi */
public class Concerto extends Evento {

    private LocalTime ora;
    private double prezzo;

    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, double prezzo )  {       
/*super si chiama per inizializzare gli attributi ereditati. Il costruttore della superclasse (cioè la classe da cui si eredita) deve sempre essere chiamato per potere inizializzare attributi che di norma sono privati e quindi non accessibili altrimenti. */ 
        super(titolo, data, postiTotali);

        this.ora = ora;
        this.prezzo = prezzo;
   
    }

    public LocalTime getOra(){

        return ora;

    }

    public void setOra(LocalTime ora){

        this.ora = ora;

    }

    public double getPrezzo(){

        return prezzo;

    }

    public void setPrezzo(double prezzo){

        this.prezzo = prezzo;

    }

    /*il primo nuovo metodo serve a ritornare la data e ora formattata */
    public String dataOra(LocalDate data, LocalTime ora) {
    /*Come per Evento ci assicuriamo la data sia presente o futura */
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Non puoi inserire una data passata");
        }
    /*Come per Evento utilizziamo gli oggetti dataFormat e oraFormat ottenuti istanziando la classe DateTimeFormatter e poi metodo ofPattern per definire qual è l'aspetto che voglio per la mia data e la mia ora */
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter oraFormat = DateTimeFormatter.ofPattern("HH:mm");
    /*Concatento i valori ottenuti */
        return data.format(dataFormat) + " " + ora.format(oraFormat);
    }

    /*Il secondo metodo serve a ritornare il prezzo formattato */
    public String prezzoFormattato(double prezzo){

    /*Usiamo la classe DecimalFormatSymbols che rappresenta un set di simboli (separatori e cose così) per creare l'oggetto semantica. Locale è un oggetto che rappresenta una specifica regione geografica, nel nostro caso l'ITALIA perchè volevamo numeri decimali a due cifre con separatore dei decimali la virgola*/
        DecimalFormatSymbols semantica = new DecimalFormatSymbols(Locale.ITALY);
    /*Il pattern del modello creato da un istanza della classe DecimalFormat invece si basa su uno dei pattern più comuni per i numeri decimali dove internazionalmente il "." rappresenta il separatore dei decimali. l' hashtag significa che può come può non esserci un numero mentre lo zero indica che qualcosa non ci sia un numero bisogna metterci lo zero.*/
        DecimalFormat modello = new DecimalFormat("#0.00€", semantica);
        String prezzoFormattato =  modello.format(prezzo);

        return prezzoFormattato;

    }

    /*L'ultimo metodo è un ulteriore override di toString. Si noti che nella stringa finale tra le variabili richiamate abbiamo il metodo getTitolo() proprio dalla superclasse Evento e questo perchè così richiamiamo poi l'ipotetico titolo che viene scelto, se avessimo scritto semplicemente titolo avremmo avuto proprio la stringa titolo */

    @Override
    public String toString(){

        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter oraFormat = DateTimeFormatter.ofPattern("HH:mm");
        DecimalFormatSymbols semantica = new DecimalFormatSymbols(Locale.ITALY);
        DecimalFormat modello = new DecimalFormat("#0.00€", semantica);
        
        String dataFormattata = getData().format(dataFormat);
        String oraFormattata = ora.format(oraFormat);
        String prezzoFormattato =  modello.format(prezzo);

        return super.toString() + " " + dataFormattata + " " + oraFormattata + " - " + getTitolo() + " " + prezzoFormattato;
    } 

}
