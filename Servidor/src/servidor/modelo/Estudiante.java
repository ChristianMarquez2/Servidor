package servidor.modelo;

public class Estudiante {
    private String id;  // Cambié el tipo de 'id' a String
    private String nombre;
    private String telefono;
    private String carrera;
    private int semestre;
    private boolean gratuidad;

    // Constructor con parámetros
    public Estudiante(String id, String nombre, String telefono, String carrera, int semestre, boolean gratuidad) {
        this.id = id;  // Ahora 'id' es un String
        this.nombre = nombre;
        this.telefono = telefono;
        this.carrera = carrera;
        this.semestre = semestre;
        this.gratuidad = gratuidad;
    }

    public String getId() {
        return id;  // Cambié el retorno de 'int' a 'String'
    }

    public String getDatos() {
        return "ID: " + id +
                ", Nombre: " + nombre +
                ", Teléfono: " + telefono +
                ", Carrera: " + carrera +
                ", Semestre: " + semestre +
                ", Gratuidad: " + (gratuidad ? "Sí" : "No");
    }
}
