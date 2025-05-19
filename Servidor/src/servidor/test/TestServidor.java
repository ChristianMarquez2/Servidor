package servidor.test;

import servidor.servicio.ServidorUDP;

public class TestServidor {
    public static void main(String[] args) {
        ServidorUDP servidor = new ServidorUDP();
        servidor.iniciar();
    }
}
