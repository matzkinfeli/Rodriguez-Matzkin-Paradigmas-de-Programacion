package axiom;

public interface Comando {
    void ejecutar(Dron dron);
}

class ComandoIncrementarVelocidad implements Comando {
    @Override
    public void ejecutar(Dron dron) {
        dron.incrementarVelocidad();
    }
}

class ComandoDisminuirVelocidad implements Comando {
    @Override
    public void ejecutar(Dron dron) {
        dron.disminuirVelocidad();
    }
}

class ComandoRotarIzquierda implements Comando {
    @Override
    public void ejecutar(Dron dron) {
        dron.rotarIzquierda();
    }
}

class ComandoRotarDerecha implements Comando {
    @Override
    public void ejecutar(Dron dron) {
        dron.rotarDerecha();
    }
}

class ComandoDesplegarSonda implements Comando {
    @Override
    public void ejecutar(Dron dron) {
        dron.desplegarSonda();
    }
}

class ComandoRecuperarSonda implements Comando {
    @Override
    public void ejecutar(Dron dron) {
        dron.recuperarSonda();
    }
}
