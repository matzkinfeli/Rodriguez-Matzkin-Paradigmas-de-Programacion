package uno;

import java.util.*;

public class Juego {
    private Stack<Carta> mazo;
    private Carta cartaEnJuego;
    private String colorActual;
    private Jugador jugadorActual;
    private Estado estado;
    private ArrayList<Jugador> jugadores;

    public Juego(Stack<Carta> mazo, ArrayList<Jugador> jugadores) {
        this.mazo = mazo;
        cartaEnJuego = mazo.pop();
        this.jugadores = jugadores;
        jugadorActual = jugadores.get(0);
        this.estado = new Jugando(jugadorActual);
    }

    public void cambioTurno() {
        Collections.reverse(jugadores);
        int currentIndex = jugadores.indexOf(jugadorActual);
        jugadorActual = jugadores.get((currentIndex + 1) % jugadores.size());
    }

    public void avanzarTurno() {
        int currentIndex = jugadores.indexOf(jugadorActual);
        jugadorActual = jugadores.get((currentIndex + 1) % jugadores.size());
    }

    public void skipTurno() {
        int currentIndex = jugadores.indexOf(jugadorActual);
        jugadorActual = jugadores.get((currentIndex + 2) % jugadores.size());
    }

    public void cambiarColor(String color) {
        this.colorActual = color;
    }

    public void jugar(Carta carta) {
        estado.jugar(this, carta);
        carta.aplicarEfecto(this, colorActual);
        setEstado(new Jugando(jugadorActual));
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }

    public Jugador getJugadorActual() {
        return this.jugadorActual;
    }

    public Stack<Carta> getMazo() {
        return this.mazo;
    }

    public Carta getCartaEnJuego() {
        return this.cartaEnJuego;
    }

    public void setCartaEnJuego(Carta carta) {
        this.cartaEnJuego = carta;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getColorActual() {
        return this.colorActual;
    }

    public void setColorActual(String color) {
        this.colorActual = color;
    }
}