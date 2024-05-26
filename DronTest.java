package axiom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DronTest {

private Dron newDron() {return new Dron();}

    @Test
    public void testIncrementarVelocidad() {
        assertEquals(1, newDron().procesarComandos( "i").getVelocidad());
    }

    @Test
    public void testDisminuirVelocidad() {
        assertEquals(1, newDron().procesarComandos( "iis").getVelocidad());
    }

    @Test
    public void testDisminuirVelocidadNoMenorACero() {
    assertEquals(0, newDron().procesarComandos( "d").getVelocidad());
    }

    @Test
    public void testRotarIzquierda() {
        assertEquals("Oeste", newDron().procesarComandos("l").getDireccion().getNombre());
    }

    @Test
    public void testRotarDerecha() {
        assertEquals("Este", newDron().procesarComandos("r").getDireccion().getNombre());
    }

    @Test
    public void testDesplegarSonda() {
        assertEquals(false, newDron().procesarComandos("d").isSondaRecuperada());
    }

    @Test
    public void testRecuperarSonda() {
        assertEquals(true, newDron().procesarComandos("d").procesarComandos("f").isSondaRecuperada());
    }

}

