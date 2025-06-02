package org.milestone;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {

    /*Il Main è il metodo principale da cui parte l'esecuzione del programma Java.
    public: perchè il metodo è accessibile da qualsiasi parte del programma o da librerie esterne,
    requisito necessario perché la JVM possa richiamarlo dall'esterno.
    static: il metodo appartiene alla classe e non a una singola istanza, quindi può essere eseguito senza creare un oggetto della classe.
    void: il metodo non restituisce alcun valore.
    main: nome fisso del metodo di ingresso del programma, sempre riconosciuto dalla JVM.
    String[] args: array di stringhe che può contenere parametri passati al programma da linea di comando.
    
    Questo metodo è obbligatorio in ogni programma Java che si voglia eseguire come applicazione autonoma,
    perché è il punto di ingresso standard riconosciuto dalla Java Virtual Machine (JVM).
    */
    public static void main(String[] args){

    /*Usiamo la classe Scanner dal package java.util.Scanner per leggere dati da input. 
      Nel nostro caso, usiamo Scanner(System.in) per leggere l'input inserito a tastiera.

      Metodi generali utili di Scanner:
      - nextLine(): legge una linea intera di testo (fino al carattere di invio).
      - next(): legge una singola parola (fino al primo spazio).
      - nextInt(): legge un intero.
      - nextDouble(): legge un numero decimale.
      
     Dopo aver creato un oggetto Scanner, lo si usa per leggere i dati inseriti dall'utente
     e assegnarli a variabili, in modo da poterli elaborare nel programma.
     
     Si necessita chiudere lo Scanner alla fine del suo utilizzo con .close(),
     per liberare risorse.
     */
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci un nuovo evento, comincia con lo scrivere il titolo");
        String titolo = input.nextLine();

    /*Dopo aver chiesto il titolo ed averlo registrato come una semplice stringa, andiamo a chiedere la data. La data verrà sempre presa come una stringa ma dobbiamo convertirla in data. Per fare questo inizializziamo il valore data come nullo, tipizzato come una variabile LocalDate. Creiamo anche un pattern tramite DateTimeFormatter che vogliamo venga utilizzato per la data. Allora inizializziamo una variabile booleana dataValida a false.*/

        LocalDate data = null;
        boolean dataValida = false;
        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /*Prevedendo che il formato data possa non essere quello che vogliamo richiediamo la data all'interno di un ciclo while, la cui condizione di uscita sia che dataValida sia vera. */
        while(!dataValida){

            System.out.println("In quale data si svolgerà l'evento?Si prega di scrivere in formato dd/MM/yyyy");
            String dataInput = input.nextLine();
    
    /*Usiamo un blocco try catch per andare a verificare se effettivamente l'utente ha inserito una data come vogliamo noi oppure no. Nelle parentesi di try, andiamo letteralmente a provare questa verifica che, nel qual caso si rivelasse giusta, impostiamo dataValida come true con conseguente uscita dal ciclo while*/

            try{

                data = LocalDate.parse(dataInput, formatData);
                dataValida = true;
    
    /*Altrimenti catch si occupa di "acchiappare" l'eccezione (che va sempre tipizzata, in questo caso DateTime... e nominata, solitamente si usa semplicemente "e" ) e quindi lanciare un avviso e riprendere il ciclo*/

            } catch (DateTimeParseException e){

                System.out.println("Formato data non valido, riprovare perfavore.");

            }

        }

        boolean numeroValido = false;
        int postiTotali = 0;

        while(!numeroValido){

            System.out.println("Quanti posti saranno disponibili all'evento? Utilizzare un numero");

            if (input.hasNextInt()){

                postiTotali = input.nextInt();
                numeroValido = true;

            } else {

                System.out.println("Devi inserire una cifra per il numero");
                input.next();
            }

        }
        

        Evento evento = new Evento(titolo, data, postiTotali);


    
        






        input.close();
    }

    
}
