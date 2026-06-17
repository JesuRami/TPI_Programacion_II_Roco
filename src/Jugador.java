import java.util.ArrayList;

public class Jugador {
    private ArrayList<Carta> mano;
    private ArrayList<Carta> casita;

    public Jugador() {
        this.mano = new ArrayList<>();
        this.casita = new ArrayList<>();
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }
    public ArrayList<Carta> getCasita() {
        return casita;
    }

    public void verMano() {
        System.out.println("--- Mano.");
        if (mano.isEmpty()) {
            System.out.println("No tenés cartas en la mano.");
        } else {
            for (int i = 0; i < mano.size(); i++) {
                System.out.println(i + ". " + mano.get(i));
            }
        }
        System.out.println(" ");
    }

    public void verCasita() {
        System.out.println("--- Casita.");
        if (casita.isEmpty()) {
            System.out.println("La casita está vacía.");
        } else {
            System.out.println("Carta superior: " + casita.getLast() + ".");
            System.out.println("Tamaño: " + casita.size() + " cartas.");
        }
    }

    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }

    public void dejarCartaMesa(int iMano, ArrayList<Carta> mesa) {
        mesa.add(mano.remove(iMano));
    }

    public void moverACasita(int iMano, ArrayList<Carta> cartasMesa) {
        Carta cartaMia = this.mano.get(iMano);
        
        for (int i = cartasMesa.size() - 1; i >= 0; i--) {
            Carta cartaMesa = cartasMesa.get(i);
            if (cartaMia.getNumero() == cartaMesa.getNumero()) {
                this.casita.add(cartaMesa);
                cartasMesa.remove(i); 
            }
        }
        
        this.casita.add(cartaMia);
        this.mano.remove(iMano);
    }

    public void robarCasitaEnemiga(int iMano, Jugador enemigo) {
        Carta cartaMia = this.mano.remove(iMano);
        
        // Pasamos todas las cartas de la casita enemiga a la nuestra
        this.casita.addAll(enemigo.getCasita());
        enemigo.getCasita().clear(); // Dejamos al enemigo en cero
        
        // Ponemos nuestra carta arriba de todo en nuestra nueva casita
        this.casita.add(cartaMia);
    }
}
