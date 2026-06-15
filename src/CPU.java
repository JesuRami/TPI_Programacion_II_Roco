import java.util.ArrayList;

public class CPU {
    private ArrayList<Carta> mano;
    private ArrayList<Carta> casita;

    public CPU() {
        this.mano = new ArrayList<>();
        this.casita = new ArrayList<>();
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }
    public ArrayList<Carta> getCasita() {
        return casita;
    }

    public void recibirCarta(Carta carta) {
        mano.add(carta);
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

    public void verCasita() {
        System.out.println("---Casita CPU.");
        System.out.println("Carta superior: " + casita.getLast() + ".");
        System.out.println("Tamaño: " + casita.size() + ".");
    }
}
