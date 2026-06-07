import java.util.ArrayList;

public class Jugador {
    private ArrayList<Carta> mano;

    public Jugador() {
        this.mano = new ArrayList<Carta>();
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }
    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public void verMano(){
        System.out.println("---Mano.");
        for (Carta carta : mano) {
            System.out.println(carta);
        }
        System.out.println(" ");
    }
}
