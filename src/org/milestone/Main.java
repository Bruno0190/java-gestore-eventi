package org.milestone;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
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
        LocalTime ora = null;
        boolean dataValida = false;
        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter oraFormat = DateTimeFormatter.ofPattern("HH:mm");
    /*Prevedendo che il formato data possa non essere quello che vogliamo richiediamo la data all'interno di un ciclo while, la cui condizione di uscita sia che dataValida sia vera. */
        while(!dataValida){

            System.out.println("In quale data si svolgerà l'evento?Si prega di scrivere in formato dd/MM/yyyy");
            String dataInput = input.nextLine();
    
    /*Usiamo un blocco try catch per andare a verificare se effettivamente l'utente ha inserito una data come vogliamo noi e se la data è futura oppure no. Nelle parentesi di try, andiamo letteralmente a provare la prima verifica mentre con un interno blocco if osserviamo la seconda verifica. Nel qual caso si rivelasse giusta, impostiamo dataValida come true con conseguente uscita dal ciclo while*/

            try{

                data = LocalDate.parse(dataInput, formatData);

                
                if (data.isBefore(LocalDate.now())){

                    throw new IllegalArgumentException("Non puoi inserire una data passata");         
        
                } else {

                    dataValida = true;
                }
               
    
    /*Altrimenti catch si occupa di "acchiappare" le due eccezioni (che vanno sempre tipizzata, in questo caso DateTimeParseException e IllegalArgumentException. Queste vanno nominate, solitamente si usa semplicemente "e" ) e quindi lanciare un avviso e riprendere il ciclo*/

            } catch (DateTimeParseException e){

                System.out.println("Formato data non valido, riprovare perfavore.");

            } catch (IllegalArgumentException e) {
    //Il messaggio che viene lanciato in caso di questa seconda eccezione viene recuperato tramite il metodo getMessage() che corrisponde a quello sopra scritto in riferimento a quest'eccezione
                System.out.println(e.getMessage());

            }



        }

        boolean numeroValido = false;
        int postiTotali = 0;
    //Dopo avere inizializzato numeroValido e postiTotali, creiamo un loop while che si cicla fintanto che la variabile numeroValido non sia falsa, ovvero sia vera
        while(!numeroValido){

            System.out.println("Quanti posti saranno disponibili all'evento? Utilizzare un numero");
    //Faccio attentione che si sia inserito effettivamente un numero con il metodo hasNextInt
            if (input.hasNextInt()){

                postiTotali = input.nextInt();
                input.nextLine();

                numeroValido = true;

            } else {

                System.out.println("Devi inserire una cifra a numero e deve essere diverso da 0");
                input.next();
            }

        }

        
    //Creo quindi il mio oggetto evento dalla Classe Evento avendomi procurato sopra i parametri per istanziarlo
        Evento evento = new Evento(titolo, data, postiTotali);
    
    //Inizializzo la variabile postiDaPrenotare e risposta
        int postiDaPrenotare = 0;
        String risposta;
        System.out.println("Desideri prenotare dei posti?si/no");
        risposta = input.nextLine();

    //Fintanto che la risposta, case insensitive sia uguale a si procevo con il loop
        while (risposta.equalsIgnoreCase("si")) {
            System.out.println("Quanti?");
            
            if(input.hasNextInt()) {
                postiDaPrenotare = input.nextInt();
                input.nextLine();
    //Mi assicuro nella seguente condizione che i posti richiesti non siano superiori a quelli ancora disponibili
                if(postiDaPrenotare <= (postiTotali - evento.getPostiPrenotati())) {
                    for(int i = 0; i < postiDaPrenotare; i++) {
                        evento.prenota();
                    }

                    System.out.println("Posti prenotati finora: " + evento.getPostiPrenotati());
                    System.out.println("Posti disponibili rimasti: " + (postiTotali - evento.getPostiPrenotati()));
                } else {
                    System.out.println("Non ci sono abbastanza posti disponibili.");
                }
            } else {
                System.out.println("Devi inserire un numero valido.");
                input.nextLine(); // consuma input errato
            }

            System.out.println("Desideri prenotare altri posti? si/no");
            risposta = input.nextLine();
            
        }

    //La stessa logica di sopra la riutilizzo per disdire i posti
        int postiDaCancellare = 0;
        System.out.println("Desideri disdire dei posti?si/no");
        risposta = input.nextLine();

        while (risposta.equalsIgnoreCase("si")) {
            System.out.println("Quanti?");
            
            if(input.hasNextInt()) {
                postiDaCancellare = input.nextInt();
                input.nextLine();

                if(postiDaCancellare <= evento.getPostiPrenotati()) {
                    for(int i = 0; i < postiDaCancellare; i++) {
                        evento.disdici();
                    }

                    System.out.println("Posti prenotati finora: " + evento.getPostiPrenotati());
                    System.out.println("Posti disponibili rimasti: " + (postiTotali - evento.getPostiPrenotati()));
                } else {
                    System.out.println("Errore stai cercando di disdire più posti di quelli prenotati");
                }
            } else {
                System.out.println("Devi inserire un numero valido.");
                input.nextLine(); // consuma input errato
            }

            System.out.println("Desideri cancellare altri posti? si/no");
            risposta = input.nextLine();
            
        }

        

        System.out.println("Titolo: " + evento.getTitolo());
        System.out.println("Data: " + evento.getData());
        System.out.println("Posti totali: " + evento.getPostiTotali());
        System.out.println("Posti prenotati: " + evento.getPostiPrenotati());

        String titoloProgramma;
        int tipo = 0;
        boolean tipoGiusto = false;

        System.out.println("Creiamo un programma eventi. Qual è il titolo?");
        titoloProgramma = input.nextLine();

        ProgrammaEventi programma = new ProgrammaEventi(titoloProgramma);

        while(!tipoGiusto){

            System.out.println("Si tratta di concerti o altro tipo? Digitare 1 per concerti e 2 per altro tipo");
            if(input.hasNextInt()){
                tipo = input.nextInt();
                input.nextLine();
                if(tipo == 1 || tipo == 2){
                    tipoGiusto = true;
                    risposta = "si";
                    while(risposta.equalsIgnoreCase("si")){

                        if(tipo == 1){

                            System.out.println("Ok titolo concerto?");
                            titolo = input.nextLine();

                            dataValida = false;
                            boolean oraValida = false;
                            while(!dataValida || !oraValida){

                                System.out.println("In quale data si svolgerà il concerto?Si prega di scrivere in formato dd/MM/yyyy");
                                String dataInput = input.nextLine();

                                try{

                                    data = LocalDate.parse(dataInput, formatData);

                                    
                                    if (data.isBefore(LocalDate.now())){

                                        throw new IllegalArgumentException("Non puoi inserire una data passata");         
                            
                                    } else {

                                        dataValida = true;
                                    }

                                } catch (DateTimeParseException e){

                                    System.out.println("Formato data non valido, riprovare perfavore.");

                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());

                                }

                                System.out.println("A che ora si svolgerà il concerto?Si prega di scrivere in formato HH:mm");
                                String oraInput = input.nextLine();

                                try{

                                    ora = LocalTime.parse(oraInput, oraFormat);

                                } catch (DateTimeParseException e){

                                    System.out.println("Formato ora non valido, riprovare perfavore.");

                                }

                            
                            }
                            numeroValido = false;
                            while(!numeroValido){


                                System.out.println("Quanti posti saranno disponibili al concerto? Utilizzare un numero");
                                
                                if (input.hasNextInt()){

                                    postiTotali = input.nextInt();
                                    input.nextLine();

                                    numeroValido = true;

                                } else {

                                    System.out.println("Devi inserire una cifra a numero e deve essere diverso da 0");
                                    input.next();
                                }

                            }

                            boolean prezzoGiusto = false;
                            double prezzo = 0.00;
                            while(!prezzoGiusto){

                                System.out.println("Quanto verrà il biglietto?Inserire cifra come 0.00");
                                if(input.hasNextDouble()){
                                    prezzo = input.nextDouble();
                                    input.nextLine();
                                    

                                    prezzoGiusto = true;
                                } else {
                                    System.out.println("Errore, il prezzo deve essere scritto come 0.00");
                                }
                            }

                            Concerto concerto = new Concerto(titolo, data, postiTotali, ora, prezzo);
                            programma.addEvento(concerto);
                            System.out.println("concerto aggiunto al programma");
                            
                        }else {

                           System.out.println("Ok titolo evento?");
                            titolo = input.nextLine();

                            dataValida = false;
                        
                            while(!dataValida){

                                System.out.println("In quale data si svolgerà l'evento?Si prega di scrivere in formato dd/MM/yyyy");
                                String dataInput = input.nextLine();

                                try{

                                    data = LocalDate.parse(dataInput, formatData);

                                    
                                    if (data.isBefore(LocalDate.now())){

                                        throw new IllegalArgumentException("Non puoi inserire una data passata");         
                            
                                    } else {

                                        dataValida = true;
                                    }

                                } catch (DateTimeParseException e){

                                    System.out.println("Formato data non valido, riprovare perfavore.");

                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());

                                }
                            
                            }

                            numeroValido = false;
                            while(!numeroValido){

                                System.out.println("Quanti posti saranno disponibili all'evento? Utilizzare un numero");
                                
                                if (input.hasNextInt()){

                                    postiTotali = input.nextInt();
                                    input.nextLine();

                                    numeroValido = true;

                                } else {

                                    System.out.println("Devi inserire una cifra a numero e deve essere diverso da 0");
                                    input.next();
                                }

                            }

                            Evento eventox = new Evento(titolo, data, postiTotali);
                            programma.addEvento(eventox);
                            System.out.println("evento aggiunto al programma");


                        }
                        
                        System.out.println("Ne vuoi aggiungere altri?si/no");
                        risposta = input.nextLine();

                    }

                }else{
                    System.out.println("Devi digitare un numero che sia 1 o 2");
                }
            }else{
                System.out.println("Devi digitare un numero che sia 1 o 2");
            }

        } 
        System.out.println("Hai programmato " + programma.numeroEventi() + " eventi nel programma");
        System.out.println(programma.programma());
        LocalDate filtroData;
        String filtro;
        System.out.println("seleziona una data in base alla quale filtrare gli eventi. Sempre formato dd/MM/yyyy");
        filtro = input.nextLine();

        filtroData = LocalDate.parse(filtro, formatData);

        System.out.println(programma.dataEventi(filtroData));


        


        input.close();
    } 

} 

