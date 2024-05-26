package axiom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControladorTest {
    private Dron dron = newDron();
    private Controlador controlador = newControlador();

    private Dron newDron() {
        Dron dron = new Dron();
        dron.setDireccion(new Norte());
        return dron;
    }

    private Controlador newControlador() {
        return new Controlador(dron);
    }
    @Test
    public void testEnviarComandoIncrementarVelocidad() {
        controlador.enviarComando('i');
        assertEquals(1, dron.getVelocidad());
    }

    @Test
    public void testEnviarComandoRotarIzquierda() {
        controlador.enviarComando('l');
        assertEquals("Oeste", dron.getDireccion().getNombre());
    }

    @Test
    public void testEnviarComandoInvalidoConSondaDesplegada() {
        controlador.enviarComando('d');
        Exception exception = assertThrows(IllegalStateException.class, () -> controlador.enviarComando('i')); // Try to increment the speed
        assertEquals("no se puede modificar la velocidad o la direccion de la sonda mientras esta desplegada",
                exception.getMessage());
    }

    @Test
    public void testEnviarComandoDisminuirVelocidad() {
        controlador.enviarComando('i'); // First increase the speed
        controlador.enviarComando('s'); // Then decrease it
        assertEquals(0, dron.getVelocidad());
    }

    @Test
    public void testEnviarComandoRotarDerecha() {
        controlador.enviarComando('r');
        assertEquals("Este", dron.getDireccion().getNombre());
    }

    @Test
    public void testEnviarComandoDesconocido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> controlador.enviarComando('x'));
        assertEquals("Comando desconocido: x", exception.getMessage());
    }

}
