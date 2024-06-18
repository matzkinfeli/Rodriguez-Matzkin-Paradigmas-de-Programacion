package uno;

public class Finalizado extends Estado {
    public void jugar(Juego juego, Carta carta) {
        throw new IllegalStateException("Ya hay un ganador.");
    }
}