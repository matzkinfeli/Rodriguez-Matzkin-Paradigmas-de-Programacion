package uno;

import java.util.*;

public class Jugador {
    private String nombre;
    private ArrayList<Carta> mano;

    public Jugador(String nombre, ArrayList<Carta> mano) {
        this.nombre = nombre;
        this.mano = mano;
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Carta> getMano() {
        return this.mano;
    }

    public void drawCard(Carta carta) {
        this.mano.add(carta);
    }

    public void playCard(Carta carta, Juego juego) {
        if (juego.getJugadorActual().equals(this) && mano.contains(carta) && carta.esCompatible(juego.getCartaEnJuego())) {
            juego.jugar(carta);
            mano.remove(carta);
        } else {
            throw new IllegalStateException("La carta no se puede jugar");
        }
    }

}
