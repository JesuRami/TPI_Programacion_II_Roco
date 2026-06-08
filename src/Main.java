import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        mesa.definirJugadores(2);
        mesa.definirMazo();

        mesa.verMazo();
        mesa.barajarMazo();
        mesa.verMazo();
        mesa.cartasRestantesMazo();

        mesa.repartirManos(3);
        mesa.getJugadores().get(1).verMano();
        mesa.cartasRestantesMazo();


        // Esto de acá no es necesariamente el juego entero; es más que
        // nada para tener un modelo base sobre el cual basarnos luego.

        Scanner scanner = new Scanner(System.in);
        boolean juegoActivo = true;
        int turno = 0;

        mesa.repartirMesa(4);

        // Sistema de turnos
        while (juegoActivo) {
            Jugador jugadorActual = mesa.getJugadores().get(turno);
            System.out.println("\n---Turno del Jugador " + (turno + 1) + ".");

            boolean turnoFinalizado = false;

            while (!turnoFinalizado) {
                if (mesa.getCartasMesa().isEmpty()) {
                    mesa.repartirMesa(4);
                }

                System.out.println("1. Ver mesa");
                System.out.println("2. Ver mano");
                System.out.println("3. Robar de la mesa");
                System.out.println("4. Robar del mazo");
                System.out.println("5. Dejar en la mesa");
                System.out.println("6. Ver mazo");
                System.out.println("7. Cerrar programa");
                System.out.print("Op: ");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        mesa.verMesa();
                        break;
                    case 2:
                        jugadorActual.verMano();
                        break;
                    case 3:
                        mesa.verMesa();
                        System.out.print("Índice de la carta a tomar: ");
                        int idxMesa = scanner.nextInt();
                        Carta tomada = mesa.tomarCartaMesa(idxMesa);
                        if (tomada != null) {
                            jugadorActual.recibirCarta(tomada);
                            System.out.println("Tomaste: " + tomada);
                            turnoFinalizado = true; // Termina la acción del turno
                        } else {
                            System.out.println("Índice inválido.");
                        }
                        break;
                    case 4:
                        Carta robada = mesa.robarCarta();
                        if (robada != null) {
                            jugadorActual.recibirCarta(robada);
                            System.out.println("Robaste: " + robada);
                            turnoFinalizado = true;
                        } else {
                            System.out.println("El mazo está vacío.");
                        }
                        break;
                    case 5:
                        jugadorActual.verMano();
                        System.out.print("Índice de la carta a dejar: ");
                        int idxMano = scanner.nextInt();
                        Carta dejada = jugadorActual.jugarCarta(idxMano);
                        if (dejada != null) {
                            mesa.dejarCartaMesa(dejada);
                            System.out.println("Dejaste: " + dejada);
                            turnoFinalizado = true;
                        } else {
                            System.out.println("Índice inválido.");
                        }
                        break;
                    case 6:
                        mesa.verMazo();
                        mesa.cartasRestantesMazo();
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");
                        juegoActivo = false;
                        turnoFinalizado = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }

            // Cambiar al siguiente jugador si el juego sigue activo.
            if (juegoActivo) {
                turno = (turno + 1) % mesa.getJugadores().size();
            }
        }
        scanner.close();
    }
}