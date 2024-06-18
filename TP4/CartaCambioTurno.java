package uno;

public class CartaCambioTurno extends Carta {
    public CartaCambioTurno(String color) {
        this.color = color;
    }

    @Override
    public void aplicarEfecto(Juego juego, String color) {
        juego.cambioTurno();
        juego.avanzarTurno();
    }

    @Override
    public boolean colorCompatible(String color) {
        return this.color.equals(color);
    }

    @Override
    public boolean numeroCompatible(int numero) {
        return true;
    }

    @Override
    public boolean esCompatible(Carta carta) {
        return this.colorCompatible(carta.getColor());
    }
}