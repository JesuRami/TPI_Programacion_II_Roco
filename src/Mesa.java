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

    public void repartirMesa() {
        for (int i = 0; i < 4; i++) {
            if (!mazo.getMazo().isEmpty()) {
                this.cartasMesa.add(mazo.getMazo().removeLast());
            } else {
                System.out.println("El mazo está vacío al armar la mesa.");
                break;
            }
        }
    }

    public void repartirManos(Jugador j1, Jugador j2) {
        for (int i = 0; i < 3; i++) { 
            if (!mazo.getMazo().isEmpty()){
                j1.recibirCarta(mazo.getMazo().removeLast());
            }
            if (!mazo.getMazo().isEmpty()){
                j2.recibirCarta(mazo.getMazo().removeLast());
            }
        }
        System.out.println("Se han repartido cartas.");
    }

    public void verMesa() {
        System.out.println("--- Cartas en la Mesa.");
        if (cartasMesa.isEmpty()) {
            System.out.println("No hay cartas en la mesa.");
        } else {
            for (int i = 0; i < cartasMesa.size(); i++) {
                System.out.println(i + ". " + cartasMesa.get(i));
            }
        }
        System.out.println(" ");
    }
}