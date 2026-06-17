
import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public boolean mostrarMenuUsuario(Juego juego, Jugador jugadorActual, Mesa mesa, Jugador jugadorRival) {
        boolean turnoFinalizado = false;
        //Menú
        while (!turnoFinalizado) {
            System.out.println("\n1. Ver mesa.");
            System.out.println("2. Ver mi mano.");
            System.out.println("3. Mover de la mesa a mi casita.");
            System.out.println("4. Dejar carta en la mesa.");
            System.out.println("5. Robar la casita del rival.");
            System.out.println("6. Ver casitas.");
            System.out.println("7. Ver estado del mazo.");
            System.out.println("8. Cerrar programa.");
            System.out.print("Elige una Opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mesa.verMesa();
                    break;
                case 2:
                    jugadorActual.verMano();
                    break;
                case 3:
                    if (juego.mesaACasita(jugadorActual, mesa, scanner)){
                        turnoFinalizado = true; 
                    }
                    break;
                case 4:
                    if (juego.manoAMesa(jugadorActual, mesa, scanner)){
                        turnoFinalizado = true; 
                    }
                    break;
                case 5:
                    if (juego.robarCasita(jugadorActual, jugadorRival, scanner)) {
                        turnoFinalizado = true; 
                    }
                    break; 
                case 6:
                    System.out.print("[MIA] ");
                    jugadorActual.verCasita();
                    System.out.print("[RIVAL] ");
                    jugadorRival.verCasita();
                    break;
                case 7:
                    mesa.getMazo().verMazo();
                    mesa.getMazo().cartasRestantesMazo();
                    break;
                case 8:
                    System.out.println("Saliendo del juego...");
                    return false; 
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        return true; // Mantiene el bucle del juego activo para el siguiente turno
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
