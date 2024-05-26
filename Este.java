package axiom;

public class Este implements PuntoCardinal {
    public PuntoCardinal rotarIzquierda(Dron drone) {
        return new Norte();
    }

    public PuntoCardinal rotarDerecha(Dron drone) {
        return new Sur();
    }

    public String getNombre() {
        return "Este";
    }
}