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
        System.out.println("---Mano.");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + ". " + mano.get(i));
        }
        System.out.println(" ");
    }

    public void verCasita() {
        System.out.println("---Casita jugador.");
        System.out.println("Carta superior: " + casita.getLast() + ".");
        System.out.println("Tamaño: " + casita.size() + ".");
    }

    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }

    public void dejarCartaMesa(int iMano, ArrayList<Carta> mesa) {
        mesa.add(mano.remove(iMano));
    }

    public void moverACasita(int iMano, ArrayList<Carta> mesa){
        int i = 0;
        for (Carta cartaMesa : mesa){
            if (cartaMesa.getNumero() == mano.get(iMano).getNumero()) {
                casita.add(mesa.remove(i));
                casita.add(mano.remove(iMano));
            }
            i += 1;
        }
    }
}
