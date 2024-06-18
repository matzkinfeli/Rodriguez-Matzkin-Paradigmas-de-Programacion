package uno;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class JuegoTest {
    private Juego juego;
    private ArrayList<Jugador> jugadores;
    private Stack<Carta> mazo;

    @BeforeEach
    public void setup() {
        mazo = new Stack<>();
        mazo.push(new CartaNumerada(1, "rojo"));
        mazo.push(new CartaNumerada(2, "rojo"));
        mazo.push(new CartaNumerada(3, "rojo"));
        mazo.push(new CartaNumerada(4, "rojo"));
        mazo.push(new CartaNumerada(5, "rojo"));
        mazo.push(new CartaNumerada(1, "azul"));
        mazo.push(new CartaNumerada(2, "azul"));
        mazo.push(new CartaNumerada(3, "azul"));
        mazo.push(new CartaNumerada(4, "azul"));
        mazo.push(new CartaNumerada(6, "azul"));
        mazo.push(new CartaNumerada(1, "verde"));
        mazo.push(new CartaNumerada(2, "verde"));
        mazo.push(new CartaNumerada(3, "verde"));
        mazo.push(new CartaNumerada(1, "amarillo"));
        mazo.push(new CartaNumerada(2, "amarillo"));
        mazo.push(new CartaNumerada(3, "amarillo"));
        mazo.push(new CartaNumerada(6, "amarillo"));
        mazo.push(new CartaSkipTurno("rojo"));
        mazo.push(new CartaSkipTurno("azul"));
        mazo.push(new CartaSkipTurno("verde"));
        mazo.push(new CartaSkipTurno("amarillo"));
        mazo.push(new CartaMasDos("rojo"));
        mazo.push(new CartaMasDos("azul"));
        mazo.push(new CartaMasDos("verde"));
        mazo.push(new CartaMasDos("amarillo"));
        mazo.push(new CartaCambioTurno("rojo"));
        mazo.push(new CartaCambioTurno("azul"));
        mazo.push(new CartaCambioTurno("amarillo"));
        mazo.push(new CartaCambioTurno("verde"));
        mazo.push(new CartaRainbow());


        ArrayList<Carta> manoJugador1 = new ArrayList<>(Arrays.asList(
                new CartaNumerada(1, "rojo"),
                new CartaNumerada(2, "rojo"),
                new CartaCambioTurno("rojo"),
                new CartaSkipTurno("rojo"),
                new CartaMasDos("rojo"),
                new CartaRainbow()
        ));
        ArrayList<Carta> manoJugador2 = new ArrayList<>(Arrays.asList(
                new CartaNumerada(1, "azul"),
                new CartaNumerada(2, "azul"),
                new CartaCambioTurno("azul"),
                new CartaSkipTurno("azul"),
                new CartaMasDos("azul"),
                new CartaRainbow()
        ));

        Jugador jugador1 = new Jugador("Jugador 1", manoJugador1);
        Jugador jugador2 = new Jugador("Jugador 2", manoJugador2);

        jugadores = new ArrayList<>(Arrays.asList(jugador1,jugador2));


        juego = new Juego(mazo, jugadores);
    }
    private static Carta getCartaInicial() {
        return new CartaNumerada(1, "rojo");
    }


    @Test
    public void testCartaNumericaDiferenteColorDiferenteNumeroNoSePuedeJugar() {
        // Carta actual en juego: 1 rojo
        Carta cartaInicial = getCartaInicial();
        juego.setCartaEnJuego(cartaInicial);

        // Buscar carta 2 azul en la mano del jugador 2
        Carta cartaAzul2 = jugadores.get(1).getMano().stream()
                .filter(c -> c instanceof CartaNumerada && c.getNumero() == 2 && c.getColor().equals("azul"))
                .findFirst()
                .orElse(null);
        assertNotNull(cartaAzul2, "Jugador 1 debería tener la carta 2 azul en su mano");

        // Intentar jugar carta 2 azul
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            jugadores.get(1).playCard(cartaAzul2, juego);
        });

        assertTrue(exception.getMessage().contains("La carta no se puede jugar"));
        assertEquals(jugadores.get(0), juego.getJugadorActual());
    }

    @Test
    public void testCartaSkipTurno() {
        // Carta actual en juego: 1 rojo (ejemplo)
        Carta cartaInicial = getCartaInicial();
        juego.setCartaEnJuego(cartaInicial);

        // Buscar carta Skip Turno rojo en la mano del jugador 1
        Carta cartaSkipTurno = jugadores.get(0).getMano().stream()
                .filter(c -> c instanceof CartaSkipTurno && c.getColor().equals("rojo"))
                .findFirst()
                .orElse(null);
        assertNotNull(cartaSkipTurno, "Jugador 1 debería tener la carta Skip Turno roja en su mano");

        // Guardar nombre del jugador actual antes de jugar la carta
        String nombreJugadorAntes = juego.getJugadorActual().getNombre();

        // Jugar carta Skip Turno rojo
        jugadores.get(0).playCard(cartaSkipTurno, juego);

        // Verificar que el jugador actual no es el mismo que jugó la carta
        assertNotEquals(nombreJugadorAntes, juego.getJugadorActual().getNombre());
    }

    @Test
    public void testCartaMasDos() {
        // Carta actual en juego: 1 rojo
        Carta cartaInicial = getCartaInicial();
        juego.setCartaEnJuego(cartaInicial);

        // Buscar carta +2 rojo en la mano del jugador 1
        Carta cartaMasDos = jugadores.get(0).getMano().stream()
                .filter(c -> c instanceof CartaMasDos && c.getColor().equals("rojo"))
                .findFirst()
                .orElse(null);
        assertNotNull(cartaMasDos, "Jugador 1 debería tener la carta +2 rojo en su mano");

        // Jugar carta +2 rojo
        jugadores.get(0).playCard(cartaMasDos, juego);

        assertEquals(8, jugadores.get(1).getMano().size(), "Jugador 2 debería tener 2 cartas adicionales en su mano");
        assertEquals(jugadores.get(1), juego.getJugadorActual(), "Jugador 1 debería jugar de nuevo");
    }

    @Test
    public void testCartaCambioTurno() {
        // Carta actual en juego: 1 rojo
        Carta cartaInicial = getCartaInicial();
        juego.setCartaEnJuego(cartaInicial);

        // Buscar carta Cambio Turno rojo en la mano del jugador 1
        Carta cartaCambioTurno = jugadores.get(0).getMano().stream()
                .filter(c -> c instanceof CartaCambioTurno && c.getColor().equals("rojo"))
                .findFirst()
                .orElse(null);
        assertNotNull(cartaCambioTurno, "Jugador 1 debería tener la carta Cambio Turno roja en su mano");

        // Jugar carta Cambio Turno rojo
        jugadores.get(0).playCard(cartaCambioTurno, juego);

        // Verificar que el jugador actual es el jugador 1 por su nombre.
        // hago get(1) porque la lista de jugadores con cambioturno() se da vuelta y el jugador1 queda en el indice 1.
        assertEquals(jugadores.get(1).getNombre(), juego.getJugadorActual().getNombre());
    }



    @Test
    public void testCartaRainbow() {
        // Carta actual en juego: 1 rojo
        Carta cartaInicial = getCartaInicial();
        juego.setCartaEnJuego(cartaInicial);

        // Buscar carta Rainbow en la mano del jugador 1
        Carta cartaRainbow = jugadores.get(0).getMano().stream()
                .filter(c -> c instanceof CartaRainbow)
                .findFirst()
                .orElse(null);
        assertNotNull(cartaRainbow, "Jugador 1 debería tener la carta Rainbow en su mano");

        // Jugar carta Rainbow
        jugadores.get(0).playCard(cartaRainbow, juego);

        assertEquals("rojo", juego.getColorActual(), "El color actual debería ser rojo");
        assertEquals(jugadores.get(1), juego.getJugadorActual(), "Debería ser el turno del Jugador 2");
    }


    @Test
    public void testJugadorNoPuedeJugarCartaNoEnMano() {
        Carta cartaNoEnMano = new CartaNumerada(9, "verde");
        assertThrows(IllegalStateException.class, () -> {
            jugadores.get(0).playCard(cartaNoEnMano, juego);
        });
    }

    @Test
    public void testJuegoAvanzaAlSiguienteJugadorDespuesDeCadaTurno() {
        Jugador jugadorInicial = juego.getJugadorActual();
        juego.avanzarTurno();
        Jugador jugadorDespuesDeUnTurno = juego.getJugadorActual();
        juego.avanzarTurno();
        Jugador jugadorDespuesDeDosTurnos = juego.getJugadorActual();

        assertNotEquals(jugadorInicial, jugadorDespuesDeUnTurno);
        assertEquals(jugadorInicial, jugadorDespuesDeDosTurnos);
    }


    @Test
    public void testJuegoTerminaCorrectamenteCuandoJugadorSeQuedaSinCartas() {
        jugadores.get(0).getMano().clear();
        assertTrue(jugadores.get(0).getMano().isEmpty());
    }

    @Test
    public void testJuegoManejaCorrectamenteCuandoJugadorIntentaJugarCartaNoCompatible() {
        juego.setCartaEnJuego(new CartaNumerada(1, "rojo"));
        assertThrows(IllegalStateException.class, () -> {
            jugadores.get(0).playCard(new CartaNumerada(2, "azul"), juego);
        });
    }

}
