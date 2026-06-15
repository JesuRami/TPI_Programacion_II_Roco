import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    private Jugador jugador;
    private CPU cpu;
    private Mesa mesa;

    public Juego() {
        this.jugador = new Jugador();
        this.cpu = new CPU();
        this.mesa = new Mesa();
    }

    public void jugarCasitaRobada() {
        mesa.getMazo().definirMazo();
        mesa.getMazo().barajarMazo();
        mesa.repartirMesa(mesa.getCartasMesa());

        Scanner scanner = new Scanner(System.in);
        boolean juegoActivo = true;
        int turno = 0; // 0 = Usuario, 1 = Computadora

        while (juegoActivo) {
            // Lógica de repartición condicional (ej. cuando se quedan sin cartas)
            if (jugador.getMano().isEmpty() && cpu.getMano().isEmpty()) {
                mesa.repartirManos(jugador, cpu);
            }

            System.out.println("\n   Turno " + (turno == 0 ? "del usuario" : "de la CPU"));

            if (turno == 0) {
                juegoActivo = turnoUsuario(jugador, scanner);
            } else {
                juegoActivo = turnoComputadora(cpu);
            }

            if(jugador.getMano().isEmpty() && cpu.getMano().isEmpty() && mesa.getCartasMesa().isEmpty()){
                finalizarJuego(jugador.getCasita(), cpu.getCasita());
                juegoActivo = false;
            }
            // Cambiar al siguiente jugador si el juego sigue activo
            if (juegoActivo) {
                turno = (turno + 1) % 2;
            }
        }
        scanner.close();
    }

    private boolean turnoUsuario(Jugador jugadorActual, Scanner scanner) {
        boolean turnoFinalizado = false;

        while (!turnoFinalizado) {
            System.out.println("1. Ver mesa.");
            System.out.println("2. Ver mano.");
            System.out.println("3. Mover a la casita.");
            System.out.println("4. Dejar carta en la mesa.");
            System.out.println("5. Robar casita.");
            System.out.println("6. Ver casita.");
            System.out.println("7. Ver mazo.");
            System.out.println("8. Cerrar programa.");
            System.out.print("Op: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mesa.verMesa();
                    break;
                case 2:
                    jugador.verMano();
                    break;
                case 3:
                    if (mesaACasita(jugador, mesa, scanner)){
                        turnoFinalizado = true;
                    }
                    break;
                case 4:
                    if (manoAMesa(jugador, mesa, scanner)){
                        turnoFinalizado = true;
                    }
                    break;
                case 5:

                    break;
                case 6:
                    jugador.verCasita();
                    cpu.verCasita();
                    break;
                case 7:
                    mesa.getMazo().verMazo();
                    mesa.getMazo().cartasRestantesMazo();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    return false;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        return true; // Mantiene el juego activo para el siguiente turno
    }

    private boolean turnoComputadora(CPU cpu) {
        // Lógica automatizada para la computadora
        System.out.println("La computadora está realizando su jugada...");

        // Simulación de jugada que consume el turno
        return true; // Mantiene el juego activo
    }

    private boolean mesaACasita(Jugador jugador, Mesa mesa, Scanner scanner){
        boolean realizado = false;
        if(buscarCoincidencias(jugador.getMano(), mesa.getCartasMesa())){
            System.out.print("¿Qué carta sacas de tu mano para mover a la casa?");
            int iMano = scanner.nextInt();

            jugador.moverACasita(iMano, mesa.getCartasMesa());
            realizado = true;
        } else {
            System.out.println("\nNo coinciden las cartas de tus manos con las de la mesa.");
        }
        return realizado;
    }

    private boolean manoAMesa(Jugador jugador, Mesa mesa, Scanner scanner){
        boolean realizado = false;
        if(!buscarCoincidencias(jugador.getMano(), mesa.getCartasMesa())){
            jugador.verMano();
            System.out.print("Carta: ");
            int iMano = scanner.nextInt();
            jugador.dejarCartaMesa(iMano, mesa.getCartasMesa());
            realizado = true;
        } else {
            System.out.println("\nHay cartas que debes llevar a la casita.");
        }
        return realizado;
    }

    private boolean buscarCoincidencias(ArrayList<Carta> mano, ArrayList<Carta> mesa) {
        boolean hayCoincidecia = false;
        for (Carta cartaMano : mano){
            for (Carta cartaMesa : mesa){
                if (cartaMano.getNumero() == cartaMesa.getNumero()){
                    System.out.println("Mano: " + cartaMano.toString() + "; mesa: " + cartaMesa.toString() + ".");
                    hayCoincidecia = true;
                }
            }
        }
        return hayCoincidecia;
    }

    private void finalizarJuego(ArrayList<Carta> manoJugador, ArrayList<Carta> manoCPU){
        System.out.println("Se agotaron las cartas en juego. Ahora, determinamos el ganador.");

        if (manoJugador.size() == manoCPU.size()){
            System.out.println("Casita del jugador: "+manoJugador.size()+" cartas.");
            System.out.println("  Casita de la CPU: "+manoCPU.size()+" cartas.");
            System.out.println("------------EMPATE------------");
        }
        if (manoJugador.size() < manoCPU.size()){
            System.out.println("Casita del jugador: "+manoJugador.size()+" cartas.");
            System.out.println("  Casita de la CPU: "+manoCPU.size()+" cartas.");
            System.out.println("------------PERDISTE------------");
        } else {
            System.out.println("Casita del jugador: "+manoJugador.size()+" cartas.");
            System.out.println("  Casita de la CPU: "+manoCPU.size()+" cartas.");
            System.out.println("------------GANASTE------------");
        }
    }
}
