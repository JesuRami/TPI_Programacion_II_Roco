import java.util.ArrayList;

public class Jugador {
    private ArrayList<Carta> mano;

    public Jugador() {
        this.mano = new ArrayList<>();
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }
    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public void verMano() {
        System.out.println("---Mano.");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + ". " + mano.get(i));
        }
        System.out.println(" ");
    }

    public Carta jugarCarta(int index) {
        if (index >= 0 && index < mano.size()) {
            return mano.remove(index);
        }
        return null;
    }

    public void recibirCarta(Carta c) {
        if (c != null) {
            mano.add(c);
        }
    }
}