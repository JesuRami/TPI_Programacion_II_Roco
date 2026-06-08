import java.util.ArrayList;
import java.util.Collections;

public class Mesa {
    private ArrayList<Carta> mazo;
    private ArrayList<Carta> cartasMesa;
    private ArrayList<Jugador> jugadores;

    public Mesa() {
        this.mazo = new ArrayList<>();
        this.cartasMesa = new ArrayList<>();
        this.jugadores = new ArrayList<>();
    }

    public ArrayList<Carta> getMazo() {
        return mazo;
    }
    public void setMazo(ArrayList<Carta> mazo) {
        this.mazo = mazo;
    }
    public ArrayList<Carta> getCartasMesa() {
        return cartasMesa;
    }
    public void setCartasMesa(ArrayList<Carta> cartasMesa) {
        this.cartasMesa = cartasMesa;
    }
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Carta> definirMazo() {
        for (int i = 1; i <= 12; i++) {
            if (i != 8 && i != 9) {
                mazo.add(new Carta(i, "Oro"));
                mazo.add(new Carta(i, "Bastos"));
                mazo.add(new Carta(i, "Copas"));
                mazo.add(new Carta(i, "Espadas"));
            }
        }
        return mazo;
    }

    public void definirJugadores(int cantidadJugadores) {
        jugadores.clear();
        for (int i = 0; i < cantidadJugadores; i++) {
            jugadores.add(new Jugador());
        }
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

    public void barajarMazo(){
        Collections.shuffle(mazo);
    }

    public void repartirManos(int cantidadCartas) {
        for (int i = 0; i < cantidadCartas; i++) {
            for (Jugador jugador : jugadores) {
                if (!mazo.isEmpty()) {
                    jugador.recibirCarta(robarCarta());
                }
            }
        }
    }
    public Carta robarCarta() {
        if (mazo.isEmpty()) {
            return null;
        }
        //Se agarra y remueve la última carta porque, si fuese la primera,
        //tendríamos que estar moviendo los elementos de la lista al principio todo el rato.
        return mazo.removeLast();
    }

}
