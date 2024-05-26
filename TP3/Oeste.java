package axiom;

public class Oeste implements PuntoCardinal {
    public PuntoCardinal rotarIzquierda(Dron drone) {
        return new Sur();
    }

    public PuntoCardinal rotarDerecha(Dron drone) {
        return new Norte();
    }

    public String getNombre() {
        return "Oeste";
    }
}