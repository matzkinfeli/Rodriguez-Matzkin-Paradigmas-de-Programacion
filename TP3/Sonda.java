package axiom;

abstract public class Sonda implements ProcesadorComandos {
    Dron drone;

    Sonda(Dron drone) {
        this.drone = drone;
    }

}
