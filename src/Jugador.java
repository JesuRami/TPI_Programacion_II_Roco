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

    public void verMano(){
        System.out.println("---Mano.");
        for (Carta carta : mano) {
            System.out.println(carta);
        }
        System.out.println(" ");
    }

    public void jugarCarta(){
        //.
    }
    public void recibirCarta(Carta c){
        mano.add(c);
    }
}
