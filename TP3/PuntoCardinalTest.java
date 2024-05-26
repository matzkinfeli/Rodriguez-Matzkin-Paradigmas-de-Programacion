package axiom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntoCardinalTest {
    private Dron dron;

    private void setup() {
        dron = new Dron();
    }

    private void testRotacion(PuntoCardinal puntoCardinal, String expectedDirectionAfterRightRotation, String expectedDirectionAfterLeftRotation) {
        setup();
        dron.setDireccion(puntoCardinal.rotarDerecha(dron));
        assertEquals(expectedDirectionAfterRightRotation, dron.getDireccion().getNombre());

        setup();
        dron.setDireccion(puntoCardinal.rotarIzquierda(dron));
        assertEquals(expectedDirectionAfterLeftRotation, dron.getDireccion().getNombre());
    }

    @Test
    public void testNorteRotar() {
        testRotacion(new Norte(), "Este", "Oeste");
    }

    @Test
    public void testSurRotar() {
        testRotacion(new Sur(), "Oeste", "Este");
    }

    @Test
    public void testEsteRotar() {
        testRotacion(new Este(), "Sur", "Norte");
    }

    @Test
    public void testOesteRotar() {
        testRotacion(new Oeste(), "Norte", "Sur");
    }

    private void testDoubleRotation(PuntoCardinal puntoCardinal, String expectedDirectionAfterDoubleRightRotation, String expectedDirectionAfterDoubleLeftRotation) {
        setup();
        dron.setDireccion(puntoCardinal.rotarDerecha(dron));
        dron.setDireccion(dron.getDireccion().rotarDerecha(dron));
        assertEquals(expectedDirectionAfterDoubleRightRotation, dron.getDireccion().getNombre());

        setup();
        dron.setDireccion(puntoCardinal.rotarIzquierda(dron));
        dron.setDireccion(dron.getDireccion().rotarIzquierda(dron));
        assertEquals(expectedDirectionAfterDoubleLeftRotation, dron.getDireccion().getNombre());
    }

    @Test
    public void testNorteDoubleRotar() {
        testDoubleRotation(new Norte(), "Sur", "Sur");
    }

    @Test
    public void testSurDoubleRotar() {
        testDoubleRotation(new Sur(), "Norte", "Norte");
    }

    @Test
    public void testEsteDoubleRotar() {
        testDoubleRotation(new Este(), "Oeste", "Oeste");
    }

    @Test
    public void testOesteDoubleRotar() {
        testDoubleRotation(new Oeste(), "Este", "Este");
    }


}