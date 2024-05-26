package axiom;

public class Controlador {
    private Dron dron;

    public Controlador(Dron dron) {
        this.dron = dron;
    }

    public void enviarComando(char comandoChar) {
        Comando comando = switch (comandoChar) {
            case 'i' -> new ComandoIncrementarVelocidad();
            case 's' -> new ComandoDisminuirVelocidad();
            case 'l' -> new ComandoRotarIzquierda();
            case 'r' -> new ComandoRotarDerecha();
            case 'd' -> new ComandoDesplegarSonda();
            case 'f' -> new ComandoRecuperarSonda();
            default -> throw new IllegalArgumentException("Comando desconocido: " + comandoChar);
        };
        comando.ejecutar(this.dron);
    }

    public Dron getDron() {
        return dron;
    }
}
