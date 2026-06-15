public class Carta {
    private int numero;
    private String palo; // Oro, bastos, copas y espadas.

    public Carta(int numero, String palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return numero + " de " + palo;
    }
}
