import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();

        mesa.definirMazo();
        mesa.verMazo();

        System.out.println("Cantidad de cartas: " + mesa.getMazo().size());
    }
}