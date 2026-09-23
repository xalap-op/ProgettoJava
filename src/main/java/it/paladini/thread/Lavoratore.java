package it.paladini.thread;

// Importiamo ThreadLocalRandom per generare pause casuali.
import java.util.concurrent.ThreadLocalRandom;

// La classe Lavoratore implementa Runnable.
// Questo significa che contiene il lavoro che un thread dovrà eseguire.
public class Lavoratore implements Runnable {

    // Riferimento al contatore condiviso tra i thread.
    private final Contatore contatore;

    // Nome assegnato al lavoratore.
    private final String nome;

    // Costruttore della classe Lavoratore.
    // Riceve il contatore condiviso e il nome del lavoratore.
    public Lavoratore(Contatore contatore, String nome) {

        // Salviamo il riferimento al contatore.
        this.contatore = contatore;

        // Salviamo il nome del lavoratore.
        this.nome = nome;
    }

    /*
     * Il metodo run contiene le istruzioni eseguite dal thread
     * quando viene chiamato il metodo start().
     */
    @Override
    public void run() {

        // Il ciclo continua finché il contatore può essere incrementato.
        while (contatore.incrementa(nome)) {

            // Generiamo un numero casuale compreso tra 100 e 500.
            //
            // Il limite superiore è 501 perché il secondo parametro
            // di nextInt è escluso.
            int pausa = ThreadLocalRandom.current().nextInt(100, 501);

            try {

                // Mettiamo in pausa il thread per il numero di millisecondi
                // generato casualmente.
                Thread.sleep(pausa);

            } catch (InterruptedException e) {

                // Ripristiniamo lo stato di interruzione del thread.
                Thread.currentThread().interrupt();

                // Interrompiamo il ciclo perché il thread è stato fermato.
                break;
            }
        }

        // Questo messaggio indica che il lavoratore non può più incrementare
        // il contatore oppure è stato interrotto.
        System.out.println(nome + " ha terminato il lavoro.");
    }
}

