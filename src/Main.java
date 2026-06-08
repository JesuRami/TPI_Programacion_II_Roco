import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        iniciarJuego();

    }

    public static void iniciarJuego() {
        Mesa mesa = new Mesa();
        mesa.definirJugadores(2);
        mesa.definirMazo();

        mesa.verMazo();
        mesa.barajarMazo();
        mesa.verMazo();
        mesa.cartasRestantesMazo();

        mesa.repartirManos(3);
        mesa.getJugadores().get(1).verMano();
        mesa.cartasRestantesMazo();
    }
    public static void empezarJuego(){
        int turno = 0;
    }
}