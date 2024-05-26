package axiom;

public class SondaRecuperada extends Sonda {
    SondaRecuperada(Dron drone) {
        super(drone);
    }

    public void incrementarVelocidad() {
        this.drone.setVelocidad(this.drone.getVelocidad() + 1);
    }

    public void disminuirVelocidad() {
        this.drone.setVelocidad(Math.max(0, this.drone.getVelocidad() - 1));
    }

    public void rotarIzquierda() {
        this.drone.getDireccion().rotarIzquierda(this.drone);
    }

    public void rotarDerecha() {
        this.drone.getDireccion().rotarDerecha(this.drone);
    }

    public void desplegarSonda() {
        drone.setSonda(new SondaDesplegada(this.drone));
    }

    public void recuperarSonda() {
    }
}
