package uno;

public class CartaRainbow extends Carta  {
    public CartaRainbow() {
    }


    @Override
    public void aplicarEfecto(Juego juego, String color) {
        color = "rojo";
        juego.cambiarColor(color);
        juego.avanzarTurno();
    }

    @Override
    public boolean colorCompatible(String color) {
        return true;
    }

    @Override
    public boolean numeroCompatible(int numero) {
        return true;
    }

    @Override
    public boolean esCompatible(Carta carta) {
        return true;
    }
}