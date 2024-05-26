package axiom;

public class Sur implements PuntoCardinal {
    public PuntoCardinal rotarIzquierda(Dron drone) {
        return new Este();
    }

    public PuntoCardinal rotarDerecha(Dron drone) {
        return new Oeste();
    }

    public String getNombre() {
        return "Sur";
    }
}