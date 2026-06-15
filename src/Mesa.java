import java.util.ArrayList;

public class Mesa {
    private Mazo mazo;
    private ArrayList<Carta> cartasMesa;

    public Mesa() {
        this.mazo = new Mazo();
        this.cartasMesa = new ArrayList<>();
    }

    public Mazo getMazo() {
        return mazo;
    }
    public ArrayList<Carta> getCartasMesa() {
        return cartasMesa;
    }


    public void repartirMesa(ArrayList<Carta> mesa) {
        for (int i = 0; i < 4; i++) {
            if (!mazo.getMazo().isEmpty()) {
                mesa.add(mazo.getMazo().removeLast());
            } else {
                System.out.println("El mazo está vacío.");
                break;
            }
        }
    }

    public void repartirManos(Jugador jugador, CPU cpu) {
        for (int i = 0; i < 3; i++) {
            if (!mazo.getMazo().isEmpty()){
                jugador.recibirCarta(mazo.getMazo().removeLast());
                cpu.recibirCarta(mazo.getMazo().removeLast());
            } else{
                System.out.println("El mazo está vaciío.");
            }
        }
    }

    public void verMesa() {
        System.out.println("---Cartas en la Mesa.");
        for (int i = 0; i < cartasMesa.size(); i++) {
            System.out.println(i + ". " + cartasMesa.get(i));
        }
        System.out.println(" ");
    }
}