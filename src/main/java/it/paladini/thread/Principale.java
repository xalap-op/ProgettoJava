package it.paladini.thread;


public class Principale {

    public static void main(String[] args) {

        // Creiamo un unico oggetto Contatore con valore massimo 10.

        Contatore contatoreCondiviso = new Contatore(10);

        // Creiamo il primo oggetto Lavoratore.
        // Gli passiamo il contatore condiviso e il suo nome.
        Lavoratore lavoratore1 =
                new Lavoratore(contatoreCondiviso, "Thread-1");

        // Creiamo il secondo oggetto Lavoratore.
        Lavoratore lavoratore2 =
                new Lavoratore(contatoreCondiviso, "Thread-2");

        // Creiamo un oggetto Thread usando il primo lavoratore.
        Thread thread1 = new Thread(lavoratore1);

       
        Thread thread2 = new Thread(lavoratore2);

        // Avviamo il primo thread.
        // Da questo momento Java eseguirà il metodo run() di lavoratore1.
        thread1.start();

        thread2.start();

        try {

            // Il metodo join() fa attendere il programma principale
            // fino alla conclusione del primo thread.
            thread1.join();

            thread2.join();

        } catch (InterruptedException e) {

            // Ripristiniamo lo stato di interruzione del thread principale.
            Thread.currentThread().interrupt();

            // Stampiamo un messaggio informativo.
            System.out.println(
                "Il thread principale è stato interrotto."
            );
        }

        // Questo messaggio viene stampato soltanto dopo che entrambi
        // i thread hanno terminato il loro lavoro.
        System.out.println(
            "Raggiunto il valore massimo del contatore!"
        );
    }
}
