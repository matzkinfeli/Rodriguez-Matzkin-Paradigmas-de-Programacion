package axiom;

public class Dron implements ProcesadorComandos {
    private int velocidad;
    private PuntoCardinal direccion;
    private Sonda sonda;

    public Dron() {
        this.velocidad = 0;
        this.direccion = new Norte();
        this.sonda = new SondaRecuperada(this);
    }

    public void incrementarVelocidad() {
        this.sonda.incrementarVelocidad();
    }

    public void disminuirVelocidad() {
        this.sonda.disminuirVelocidad();
    }

    public void rotarIzquierda() {
        this.direccion = this.direccion.rotarIzquierda(this);
    }

    public void rotarDerecha() {
        this.direccion = this.direccion.rotarDerecha(this);
    }

    public void desplegarSonda() {
        this.sonda.desplegarSonda();
    }

    public void recuperarSonda() {
        this.sonda.recuperarSonda();
    }

    public void setSonda(Sonda sonda) {
        this.sonda = sonda;
    }

    public void setVelocidad(int nuevaVelocidad) {
        this.velocidad = nuevaVelocidad;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public PuntoCardinal getDireccion() {
        return direccion;
    }

    public void setDireccion(PuntoCardinal direccion) {
        this.direccion = direccion;
    }
    public boolean isSondaRecuperada() {
        return this.sonda instanceof SondaRecuperada;
    }
    public Dron procesarComandos(String comandos) {
        Controlador controlador = new Controlador(this);
        comandos.chars().mapToObj(c -> (char) c).forEachOrdered(controlador::enviarComando);
        return this;
    }
}