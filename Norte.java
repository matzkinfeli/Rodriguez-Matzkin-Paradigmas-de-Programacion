package axiom;

public class Norte implements PuntoCardinal {
    public PuntoCardinal rotarIzquierda(Dron drone) {
        return new Oeste();
    }

    public PuntoCardinal rotarDerecha(Dron drone) {
        return new Este();
    }

    public String getNombre() {
        return "Norte";
    }
}
