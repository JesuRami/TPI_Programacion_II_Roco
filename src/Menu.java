import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public boolean mostrarMenuUsuario(Juego juego, Jugador jugadorActual, Mesa mesa, Jugador jugadorRival) {
        boolean turnoFinalizado = false;
        while (!turnoFinalizado) {
            System.out.println("\n1. Ver mesa y mano.");
            System.out.println("2. Mover a casita.");
            System.out.println("3. Dejar en la mesa.");
            System.out.println("4. Robar casita.");
            System.out.println("5. Ver casitas.");
            System.out.println("6. Ver mazo.");
            System.out.println("7. Cerrar programa.");
            System.out.print("Opción: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Debe ser un entero. Ingresa el índice: ");
                scanner.next();
            }
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mesa.verMesa();
                    jugadorActual.verMano();
                    break;
                case 2:
                    if (juego.mesaACasita(jugadorActual, mesa, scanner)){
                        turnoFinalizado = true;
                    }
                    break;
                case 3:
                    if (juego.manoAMesa(jugadorActual, mesa, scanner)){
                        turnoFinalizado = true;
                    }
                    break;
                case 4:
                    if (juego.robarCasita(jugadorActual, jugadorRival, scanner)) {
                        turnoFinalizado = true;
                    }
                    break;
                case 5:
                    System.out.print("[MIA] ");
                    jugadorActual.verCasita();
                    System.out.print("[RIVAL] ");
                    jugadorRival.verCasita();
                    break;
                case 6:
                    mesa.getMazo().verMazo();
                    mesa.getMazo().cartasRestantesMazo();
                    break;
                case 7:
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
