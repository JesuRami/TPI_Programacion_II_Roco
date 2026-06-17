import java.util.ArrayList;

public class Juego {
    private Jugador jugador1;
    private Jugador jugador2; 
    private Mesa mesa;
    private Menu menu; 

    public Juego() {
        this.jugador1 = new Jugador();
        this.jugador2 = new Jugador();
        this.mesa = new Mesa();
        this.menu = new Menu(); 
    }

    public void jugarCasitaRobada() {
        mesa.getMazo().definirMazo();
        mesa.getMazo().barajarMazo();
        mesa.repartirMesa(); 

        boolean juegoActivo = true;
        int turno = 0; 

        while (juegoActivo) {
            // Si ambos se quedan sin cartas, se vuelven a repartir manos
            if (jugador1.getMano().isEmpty() && jugador2.getMano().isEmpty()) {
                // Verificación de fin de juego: si no hay cartas en manos ni en el mazo
                if (mesa.getMazo().getMazo().isEmpty()) {
                    finalizarJuego(jugador1.getCasita(), jugador2.getCasita());
                    juegoActivo = false;
                    break;
                }
                mesa.repartirManos(jugador1, jugador2);
            }

            System.out.println("\n   Turno del JUGADOR " + (turno == 0 ? "1" : "2"));

            if (turno == 0) {
                juegoActivo = menu.mostrarMenuUsuario(this, jugador1, mesa, jugador2);
            } else {
                juegoActivo = menu.mostrarMenuUsuario(this, jugador2, mesa, jugador1);
            }
            
            // Si el juego sigue activo tras la jugada, cambia el turno
            if (juegoActivo) {
                turno = (turno + 1) % 2;
            }
        }
        menu.cerrarScanner(); 
    }

    public boolean mesaACasita(Jugador jugador, Mesa mesa, java.util.Scanner scanner) {
        ArrayList<Carta> mano = jugador.getMano();
        ArrayList<Carta> cartasMesa = mesa.getCartasMesa();

        if (mano.isEmpty()) {
            System.out.println("\nNo tenés cartas en la mano.");
            return false;
        }

        boolean tieneJuego = false;
        for (Carta cartaMano : mano) {
            for (Carta cartaMesa : cartasMesa) {
                if (cartaMano.getNumero() == cartaMesa.getNumero()) {
                    tieneJuego = true;
                    break; 
                }
            }
            if (tieneJuego) break; 
        }

        if (!tieneJuego) {
            System.out.println("\nNo coinciden las cartas de tus manos con las de la mesa.");
            return false;
        }

        System.out.println("\n--- TUS COINCIDENCIAS DISPONIBLES ---");
        System.out.println("Tu mano actual:");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + ". " + mano.get(i).toString());
        }

        System.out.print("¿Qué carta sacas de tu mano para mover a la casa? (Ingresa el número de índice): ");
        int iMano = scanner.nextInt(); 

        if (iMano < 0 || iMano >= mano.size()) {
            System.out.println("Opción inválida. No tenés esa carta.");
            return false;
        }

        Carta cartaSeleccionada = mano.get(iMano);

        boolean cartaElegidaEsValida = false;
        for (Carta cartaMesa : cartasMesa) {
            if (cartaSeleccionada.getNumero() == cartaMesa.getNumero()) {
                System.out.println("Coincidencia levantada -> Mano: " + cartaSeleccionada.toString() + " ; Mesa: " + cartaMesa.toString() + ".");
                cartaElegidaEsValida = true;
            }
        }

        if (cartaElegidaEsValida) {
            jugador.moverACasita(iMano, cartasMesa);
            System.out.println("¡Movimiento realizado con éxito!");
            return true;
        } else {
            System.out.println("\n¡Error! Elegiste el " + cartaSeleccionada.toString() + " pero esa carta no coincide con la mesa.");
            return false;
        }
    }

    public boolean manoAMesa(Jugador jugador, Mesa mesa, java.util.Scanner scanner){
        ArrayList<Carta> mano = jugador.getMano();
        ArrayList<Carta> cartasMesa = mesa.getCartasMesa();

        if (mano.isEmpty()) return false;

        System.out.println("\nTu mano actual:");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + ". " + mano.get(i).toString());
        }

        System.out.print("¿Qué carta querés dejar en la mesa? (Ingresa el número de índice): ");
        int iMano = scanner.nextInt();

        if (iMano < 0 || iMano >= mano.size()) {
            System.out.println("Opción inválida.");
            return false;
        }

        Carta cartaSeleccionada = mano.get(iMano);

        if (buscarCoincidenciaDeCarta(cartaSeleccionada, cartasMesa)) {
            System.out.println("\n¡Esa carta coincide con la mesa! Debes mandarla a la casita (Opción 3).");
            return false;
        }

        jugador.dejarCartaMesa(iMano, cartasMesa);
        System.out.println("Dejaste el " + cartaSeleccionada + " en la mesa.");
        return true;
    }

    public boolean robarCasita(Jugador jugadorActual, Jugador jugadorRival, java.util.Scanner scanner) {
        ArrayList<Carta> mano = jugadorActual.getMano();
        ArrayList<Carta> casitaRival = jugadorRival.getCasita();

        if (casitaRival.isEmpty()) {
            System.out.println("\nEl rival no tiene cartas en su casita para robar.");
            return false;
        }

        Carta cartaTopeRival = casitaRival.getLast();
        boolean tieneRobo = false;

        for (Carta c : mano) {
            if (c.getNumero() == cartaTopeRival.getNumero()) {
                tieneRobo = true;
                break;
            }
        }

        if (!tieneRobo) {
            System.out.println("\nNo tenés ninguna carta en la mano que coincida con el tope de la casita rival (" + cartaTopeRival + ").");
            return false;
        }

        System.out.println("\n--- ROBOS DISPONIBLES ---");
        System.out.println("Tope de la casita rival: " + cartaTopeRival);
        System.out.println("Tu mano:");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + ". " + mano.get(i));
        }

        System.out.print("¿Con qué carta querés robar la casita? (Ingresa el índice): ");
        int iMano = scanner.nextInt();

        if (iMano < 0 || iMano >= mano.size()) {
            System.out.println("Índice inválido.");
            return false;
        }

        Carta cartaSeleccionada = mano.get(iMano);

        if (cartaSeleccionada.getNumero() == cartaTopeRival.getNumero()) {
            jugadorActual.robarCasitaEnemiga(iMano, jugadorRival);
            System.out.println("\n¡Casa robada!");
            return true;
        } else {
            System.out.println("\nEsa carta no coincide con el tope de la casita rival.");
            return false;
        }
    }

    private boolean buscarCoincidenciaDeCarta(Carta cartaMano, ArrayList<Carta> mesa) {
        boolean hayCoincidencia = false;
        for (Carta cartaMesa : mesa) {
            if (cartaMano.getNumero() == cartaMesa.getNumero()) {
                hayCoincidencia = true;
            }
        }
        return hayCoincidencia;
    }

    private void finalizarJuego(ArrayList<Carta> casitaJ1, ArrayList<Carta> casitaJ2){
        System.out.println("\n========================================");
        System.out.println("Se terminaron las cartas. Fin de la partida.");
        System.out.println("Casita Jugador 1: " + casitaJ1.size() + " cartas.");
        System.out.println("Casita Jugador 2: " + casitaJ2.size() + " cartas.");
        System.out.println("========================================");

        if (casitaJ1.size() == casitaJ2.size()){
            System.out.println("------------ ¡EMPATE! ------------");
        } else if (casitaJ1.size() > casitaJ2.size()){
            System.out.println("------------ ¡GANÓ EL JUGADOR 1! ------------");
        } else {
            System.out.println("------------ ¡GANÓ EL JUGADOR 2! ------------");
        }
    }
}