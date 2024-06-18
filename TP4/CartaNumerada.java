package uno;

public class CartaNumerada extends Carta {
    public CartaNumerada(int numero, String color) {
        this.numero = numero;
        this.color = color;
    }

    @Override
    public void aplicarEfecto(Juego juego, String color) {
        juego.setCartaEnJuego(this);
        juego.setColorActual(this.color);
        juego.avanzarTurno();
    }

    @Override
    public boolean colorCompatible(String color) {
        return this.color.equals(color);
    }

    @Override
    public boolean numeroCompatible(int numero) {
        return this.numero == numero;
    }

    @Override
    public boolean esCompatible(Carta carta) {
        return this.colorCompatible(carta.getColor()) || this.numeroCompatible(carta.getNumero());
    }
}