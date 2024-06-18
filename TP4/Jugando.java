package uno;

public class Jugando extends Estado {
    private Jugador jugadorActual;

    public Jugando(Jugador nuevoJugadorActual) {
        this.jugadorActual = nuevoJugadorActual;
    }

    public void jugar(Juego juego, Carta carta) {
        if (!jugadorActual.getMano().contains(carta)) {
            throw new IllegalStateException("El jugador actual no tiene esta carta en su mano");
        }
        if (carta.esCompatible(juego.getCartaEnJuego())) {
            juego.setCartaEnJuego(carta);
            carta.aplicarEfecto(juego, carta.getColor());
            juego.avanzarTurno();
        } else {
            throw new IllegalStateException("La carta no se puede jugar");
        }
    }
}
