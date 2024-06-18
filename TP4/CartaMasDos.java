package uno;

public class CartaMasDos extends Carta {
    public CartaMasDos(String color) {
        this.color = color;
    }

    @Override
    public void aplicarEfecto(Juego juego, String color) {
        int currentIndex = juego.getJugadores().indexOf(juego.getJugadorActual());
        Jugador siguienteJugador = juego.getJugadores().get((currentIndex + 1) % juego.getJugadores().size());
        siguienteJugador.drawCard(juego.getMazo().pop());
        siguienteJugador.drawCard(juego.getMazo().pop());
        juego.skipTurno();
    }

    @Override
    public boolean colorCompatible(String color) {
        return this.color.equals(color);
    }

    @Override
    public boolean numeroCompatible(int numero) {
        return false;
    }

    @Override
    public boolean esCompatible(Carta carta) {
        return this.colorCompatible(carta.getColor());
    }
}