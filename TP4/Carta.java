package uno;

public abstract class Carta {
    protected String color;
    protected Integer numero;

    public String getColor() {
        return this.color;
    }

    public Integer getNumero () {
        return this.numero;
    }

    public abstract void aplicarEfecto(Juego juego, String color);

    public abstract boolean colorCompatible(String color);

    public abstract boolean numeroCompatible(int numero);

    public abstract boolean esCompatible(Carta carta);

}
