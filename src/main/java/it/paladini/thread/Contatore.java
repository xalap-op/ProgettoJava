package it.paladini.thread;

// La classe Contatore rappresenta la risorsa condivisa
// utilizzata contemporaneamente dai due thread.
public class Contatore {

    // Variabile che contiene il valore attuale del contatore.
    private int valore;

    // Valore massimo che il contatore può raggiungere.
    private final int valoreMassimo;

    // Costruttore della classe Contatore.
    // Riceve il valore massimo come parametro.
    public Contatore(int valoreMassimo) {

        // Il contatore parte sempre da 0.
        this.valore = 0;

        // Salviamo il valore massimo ricevuto.
        this.valoreMassimo = valoreMassimo;
    }

    /*
     * Il metodo incrementa aumenta il contatore di 1.
     *
     * La parola synchronized è fondamentale:
     * garantisce che un solo thread alla volta possa eseguire
     * questo metodo, evitando che entrambi leggano lo stesso valore
     * e producano risultati errati.
     *
     * Il metodo restituisce:
     * - true se l'incremento è stato eseguito;
     * - false se il contatore ha già raggiunto il massimo.
     */
    public synchronized boolean incrementa(String nomeThread) {

        // Controlliamo se il valore attuale è ancora inferiore al massimo.
        if (valore < valoreMassimo) {

            // Incrementiamo il contatore di 1.
            valore++;

            // Stampiamo il nome del thread e il nuovo valore.
            System.out.println(
                nomeThread + " ha incrementato il contatore a: " + valore
            );

            // Comunichiamo al chiamante che l'incremento è avvenuto.
            return true;
        }

        // Se arriviamo qui, il valore massimo è già stato raggiunto.
        return false;
    }
}

