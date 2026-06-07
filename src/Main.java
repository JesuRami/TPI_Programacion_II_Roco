import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        mesa.definirJugadores();
        mesa.definirMazo();
        mesa.verMazo();
        System.out.println("Cantidad de cartas: "+mesa.getMazo().size()+".\n");

        mesa.barajarMazo();
        mesa.verMazo();

    }
}