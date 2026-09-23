package it.paladini.thread;


public class Principale {

    public static void main(String[] args) {

        Contatore contatoreCondiviso = new Contatore(10);

        Lavoratore lavoratore1 = new Lavoratore(contatoreCondiviso, "Thread-1");

        Lavoratore lavoratore2 = new Lavoratore(contatoreCondiviso, "Thread-2");

        // Creiamo un oggetto Thread usando il primo e secondo lavoratore.
        Thread thread1 = new Thread(lavoratore1);
        Thread thread2 = new Thread(lavoratore2);

        thread1.start();
        thread2.start();

        try {

            // Il metodo join() fa attendere il programma principale
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {

            // Ripristiniamo lo stato di interruzione del thread principale.
            Thread.currentThread().interrupt();

            System.out.println(
                "Il thread principale è stato interrotto."
            );
        }
        System.out.println(
            "Raggiunto il valore massimo del contatore!"
        );
    }
}
