package servidor.servicio;

import servidor.modelo.Estudiante;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class ServidorUDP {
    private List<Estudiante> estudiantes = new ArrayList<>();

    public ServidorUDP() {
        cargarEstudiantesIniciales();
    }
    private void cargarEstudiantesIniciales() {
        estudiantes.add(new Estudiante("1", "Richard Soria", "0998470954", "Desarrollo en Software", 4, true));
        estudiantes.add(new Estudiante("2", "Christian Marquez", "0980865549", "Desarrollo en Software", 4, true));
        estudiantes.add(new Estudiante("3", "Angel Villamil", "098470954", "Desarrollo en Software", 4, false));
        estudiantes.add(new Estudiante("4", "Richard Robalino", "0985646546", "Desarrollo en Software", 4, false));
        estudiantes.add(new Estudiante("5", "Washington Villares", "78456452", "Desarrollo en Software", 4, true));
        estudiantes.add(new Estudiante("6", "Pedro Torres", "098470954", "Desarrollo en Software", 4, false));
        estudiantes.add(new Estudiante("7", "Paul Torres", "098470954", "Desarrollo en Software", 4, true));
        estudiantes.add(new Estudiante("8", "Gustavo Linabanda", "098470954", "Desarrollo en Software", 4, false));
        estudiantes.add(new Estudiante("9", "David Yela", "098470954", "Desarrollo en Software", 4, false));
        estudiantes.add(new Estudiante("10", "Yasmine Muñoz", "098470954", "Desarrollo en Software", 4, false));
    }

    public void iniciar() {
        int puerto = 5000;

        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP escuchando en el puerto " + puerto + "...");

            byte[] bufferEntrada = new byte[1024];

            while (true) {
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(paqueteEntrada);

                String cedulaString = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("Consulta recibida para cédula: " + cedulaString);

                // La cédula se maneja como String, no es necesario convertirla
                String cedula = cedulaString;

                String respuesta = buscarEstudiante(cedula);

                byte[] bufferSalida = respuesta.getBytes();
                DatagramPacket paqueteSalida = new DatagramPacket(
                        bufferSalida,
                        bufferSalida.length,
                        paqueteEntrada.getAddress(),
                        paqueteEntrada.getPort()
                );

                socket.send(paqueteSalida);
                System.out.println("Respuesta enviada: " + respuesta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buscarEstudiante(String id) {
        for (Estudiante est : estudiantes) {
            if (est.getId().equals(id)) {
                return est.getDatos();
            }
        }
        return "Estudiante no encontrado.";
    }
}