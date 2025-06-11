package org.milestone;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {

    private LocalTime ora;
    private float prezzo;

    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, float prezzo )  {        
        super(titolo, data, postiTotali);

        this.ora = ora;
        this.prezzo = prezzo;
   
    }
    public String dataOra(LocalDate data, LocalTime ora) {

        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Non puoi inserire una data passata");
        }

        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter oraFormat = DateTimeFormatter.ofPattern("HH:mm");

        return data.format(dataFormat) + " " + ora.format(oraFormat);
    }
    public String prezzoFormattato{

  

    }


    

}
