import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> mazo;

    public Mazo() {
        this.mazo = new ArrayList<>();
    }

    public ArrayList<Carta> getMazo() {
        return mazo;
    }

    public ArrayList<Carta> definirMazo() {
        for (int i = 1; i <= 12; i++) {
            mazo.add(new Carta(i, "Oro"));
            mazo.add(new Carta(i, "Bastos"));
            mazo.add(new Carta(i, "Copas"));
            mazo.add(new Carta(i, "Espadas"));
        }
        return mazo;
    }

    public void verMazo() {
        System.out.println("---Cartas del Mazo.");
        for (Carta carta : mazo) {
            System.out.println(carta);
        }
        System.out.println("\n");
    }

    public void cartasRestantesMazo(){
        System.out.println("Cartas en el mazo: "+mazo.size()+".\n");
    }

    public void barajarMazo() {
        Collections.shuffle(mazo);
    }
}
