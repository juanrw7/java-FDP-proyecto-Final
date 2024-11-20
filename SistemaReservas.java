import java.util.ArrayList;
import java.util.Scanner;

public class SistemaReservas {
    private ArrayList<Atraccion> atracciones;
    private ArrayList<Visitante> visitantes;
    private ArrayList<Pedido> pedidos;

    public SistemaReservas() {
        atracciones = new ArrayList<>();
        visitantes = new ArrayList<>();
        pedidos = new ArrayList<>();
        inicializarDatos();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Bienvenido al Parque de Atracciones");
            System.out.println("1. Reservar atracción");
            System.out.println("2. Ver horarios de atracciones");
            System.out.println("3. Comprar alimentos");
            System.out.println("4. Registrarse como visitante");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

        if (opcion == 1) {
            reservarAtraccion(scanner);
        } else if (opcion == 2) {
            mostrarHorarios();
        } else if (opcion == 3) {
            comprarAlimentos(scanner);
        } else if (opcion == 4) {
            registrarVisitante(scanner);
        } else if (opcion == 5) {
            System.out.println("Gracias por usar el sistema.");
        } else {
            System.out.println("Opción inválida.");
        }
        
        
        } while (opcion != 5);
    }

    private void inicializarDatos() {
        atracciones.add(new Atraccion("Montaña Rusa", "10:00 - 18:00", 50));
        atracciones.add(new Atraccion("Casa del Terror", "11:00 - 20:00", 30));
        atracciones.add(new Atraccion("Rueda de la Fortuna", "09:00 - 22:00", 100));
    }

    private void reservarAtraccion(Scanner scanner) {
        System.out.println("Lista de atracciones:");
        for (int i = 0; i < atracciones.size(); i++) {
            System.out.println((i + 1) + ". " + atracciones.get(i).getNombre());
        }
        System.out.print("Seleccione la atracción: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();
        if (indice >= 0 && indice < atracciones.size()) {
            Atraccion atraccion = atracciones.get(indice);
            System.out.print("Ingrese la fecha de la reserva (dd/mm/yyyy): ");
            String fecha = scanner.nextLine();
            System.out.print("Ingrese la hora de la reserva (hh:mm): ");
            String hora = scanner.nextLine();

            if (atraccion.reservar(fecha, hora)) {
                System.out.println("Reserva realizada con éxito.");
            } else {
                System.out.println("No hay disponibilidad para esa fecha y hora.");
            }
        } else {
            System.out.println("Opción inválida.");
        }
    }

    private void mostrarHorarios() {
        System.out.println("Horarios de las atracciones:");
        for (Atraccion atraccion : atracciones) {
            System.out.println(atraccion.getNombre() + ": " + atraccion.getHorario());
        }
    }

    private void comprarAlimentos(Scanner scanner) {
        System.out.print("Ingrese el nombre del alimento o bebida: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = new Pedido(nombre, cantidad);
        pedidos.add(pedido);
        System.out.println("Pedido registrado con éxito. Recójalo en el punto designado.");
    }

    private void registrarVisitante(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();

        Visitante visitante = new Visitante(nombre, correo);
        visitantes.add(visitante);
        System.out.println("Registro exitoso. Bienvenido al sistema.");
    }
}
