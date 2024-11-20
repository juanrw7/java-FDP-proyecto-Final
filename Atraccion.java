import java.util.HashMap;

public class Atraccion {
    private String nombre;
    private String horario;
    private int capacidad;
    private HashMap<String, Integer> reservas;

    public Atraccion(String nombre, String horario, int capacidad) {
        this.nombre = nombre;
        this.horario = horario;
        this.capacidad = capacidad;
        this.reservas = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public boolean reservar(String fecha, String hora) {
        String clave = fecha + " " + hora;
        int reservasActuales = reservas.getOrDefault(clave, 0);
        if (reservasActuales < capacidad) {
            reservas.put(clave, reservasActuales + 1);
            return true;
        }
        return false;
    }
}
