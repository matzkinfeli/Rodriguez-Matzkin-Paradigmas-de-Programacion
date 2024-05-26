package axiom;

public class SondaDesplegada extends Sonda {
    SondaDesplegada(Dron drone) {
        super(drone);
    }

    public void incrementarVelocidad() {
        throw new IllegalStateException("no se puede modificar la velocidad o la direccion de la sonda mientras esta desplegada");
    }

    public void disminuirVelocidad() {
        throw new IllegalStateException("no se puede modificar la velocidad o la direccion de la sonda mientras esta desplegada");
    }

    public void rotarIzquierda() {
        throw new IllegalStateException("no se puede modificar la velocidad o la direccion de la sonda mientras esta desplegada");
    }

    public void rotarDerecha() {
        throw new IllegalStateException("no se puede modificar la velocidad o la direccion de la sonda mientras esta desplegada");
    }

    public void desplegarSonda() {
    }

    public void recuperarSonda() {
        this.drone.setSonda(new SondaRecuperada(this.drone));
    }
}
