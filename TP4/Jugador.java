package uno;

import java.lang.reflect.Array;
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
        if (mano.contains(carta)) {
            juego.jugar(carta);
            mano.remove(carta);
        } else {
            System.out.println("No puedes jugar una carta que no tienes en tu mano.");
        }
    }

}
