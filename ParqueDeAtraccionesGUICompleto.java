// Esta interfaz grafica fue hecha con la ayuda de chatGPT

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParqueDeAtraccionesGUICompleto {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private ArrayList<Atraccion> atracciones;
    private ArrayList<Visitante> visitantes;
    private ArrayList<Pedido> pedidos;

    public static void main(String[] args) {
        new ParqueDeAtraccionesGUICompleto().crearGUI();
    }

    public ParqueDeAtraccionesGUICompleto() {
        atracciones = new ArrayList<>();
        visitantes = new ArrayList<>();
        pedidos = new ArrayList<>();
        inicializarDatos();
    }

    private void inicializarDatos() {
        atracciones.add(new Atraccion("Montaña Rusa", "10:00 AM - 8:00 PM", 50));
        atracciones.add(new Atraccion("Casa del Terror", "11:00 AM - 7:00 PM", 30));
        atracciones.add(new Atraccion("Rueda de la Fortuna", "9:00 AM - 9:00 PM", 100));
    }

    private void crearGUI() {
        frame = new JFrame("Parque de Atracciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(crearMenuPrincipal(), "MenuPrincipal");
        mainPanel.add(crearPanelReservar(), "Reservar");
        mainPanel.add(crearPanelHorarios(), "Horarios");
        mainPanel.add(crearPanelComprar(), "Comprar");
        mainPanel.add(crearPanelRegistrar(), "Registrar");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel crearMenuPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnReservar = new JButton("Reservar Atracción");
        JButton btnHorarios = new JButton("Ver Horarios de Atracciones");
        JButton btnComprar = new JButton("Comprar Alimentos");
        JButton btnRegistrar = new JButton("Registrarse y Ver Información");
        JButton btnSalir = new JButton("Salir");

        btnReservar.addActionListener(e -> cardLayout.show(mainPanel, "Reservar"));
        btnHorarios.addActionListener(e -> cardLayout.show(mainPanel, "Horarios"));
        btnComprar.addActionListener(e -> cardLayout.show(mainPanel, "Comprar"));
        btnRegistrar.addActionListener(e -> cardLayout.show(mainPanel, "Registrar"));
        btnSalir.addActionListener(e -> System.exit(0));

        panel.add(btnReservar);
        panel.add(btnHorarios);
        panel.add(btnComprar);
        panel.add(btnRegistrar);
        panel.add(btnSalir);

        return panel;
    }

    private JPanel crearPanelReservar() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));

        JLabel lblAtraccion = new JLabel("Atracción:");
        JComboBox<String> cmbAtracciones = new JComboBox<>(atracciones.stream().map(Atraccion::getNombre).toArray(String[]::new));
        JLabel lblFecha = new JLabel("Fecha (dd/mm/yyyy):");
        JTextField txtFecha = new JTextField();
        JLabel lblHora = new JLabel("Hora (hh:mm):");
        JTextField txtHora = new JTextField();

        JButton btnReservar = new JButton("Reservar");
        JLabel lblMensaje = new JLabel("");

        btnReservar.addActionListener(e -> {
            String atraccionNombre = (String) cmbAtracciones.getSelectedItem();
            Atraccion atraccion = atracciones.stream().filter(a -> a.getNombre().equals(atraccionNombre)).findFirst().orElse(null);
            String fecha = txtFecha.getText();
            String hora = txtHora.getText();

            if (atraccion != null && !fecha.isEmpty() && !hora.isEmpty()) {
                if (atraccion.reservar(fecha, hora)) {
                    lblMensaje.setText("Reserva realizada con éxito.");
                } else {
                    lblMensaje.setText("No hay disponibilidad para esa fecha y hora.");
                }
            } else {
                lblMensaje.setText("Por favor, complete todos los campos.");
            }
        });

        panel.add(lblAtraccion);
        panel.add(cmbAtracciones);
        panel.add(lblFecha);
        panel.add(txtFecha);
        panel.add(lblHora);
        panel.add(txtHora);
        panel.add(btnReservar);
        panel.add(lblMensaje);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "MenuPrincipal"));
        panel.add(btnVolver);

        return panel;
    }

    private JPanel crearPanelHorarios() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea txtHorarios = new JTextArea();
        txtHorarios.setEditable(false);
        StringBuilder horariosText = new StringBuilder("Horarios de las atracciones:\n");
        for (Atraccion atraccion : atracciones) {
            horariosText.append(atraccion.getNombre()).append(": ").append(atraccion.getHorario()).append("\n");
        }
        txtHorarios.setText(horariosText.toString());

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "MenuPrincipal"));

        panel.add(new JScrollPane(txtHorarios), BorderLayout.CENTER);
        panel.add(btnVolver, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelComprar() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel lblAlimento = new JLabel("Alimento:");
        JTextField txtAlimento = new JTextField();
        JLabel lblCantidad = new JLabel("Cantidad:");
        JTextField txtCantidad = new JTextField();

        JButton btnComprar = new JButton("Comprar");
        JLabel lblMensaje = new JLabel("");

        btnComprar.addActionListener(e -> {
            String alimento = txtAlimento.getText();
            String cantidad = txtCantidad.getText();

            if (!alimento.isEmpty() && !cantidad.isEmpty()) {
                pedidos.add(new Pedido(alimento, Integer.parseInt(cantidad)));
                lblMensaje.setText("Compra realizada con éxito.");
            } else {
                lblMensaje.setText("Por favor, complete todos los campos.");
            }
        });

        panel.add(lblAlimento);
        panel.add(txtAlimento);
        panel.add(lblCantidad);
        panel.add(txtCantidad);
        panel.add(btnComprar);
        panel.add(lblMensaje);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "MenuPrincipal"));
        panel.add(btnVolver);

        return panel;
    }

    private JPanel crearPanelRegistrar() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea txtRegistros = new JTextArea();
        txtRegistros.setEditable(false);

        JButton btnRegistrar = new JButton("Registrar Visitante");
        btnRegistrar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del visitante:");
            String correo = JOptionPane.showInputDialog("Ingrese el correo electrónico del visitante:");
            if (nombre != null && correo != null && !nombre.trim().isEmpty() && !correo.trim().isEmpty()) {
                visitantes.add(new Visitante(nombre, correo));
                JOptionPane.showMessageDialog(frame, "Visitante registrado con éxito.");
            }
        });

        JButton btnMostrarRegistros = new JButton("Mostrar Registros");
        btnMostrarRegistros.addActionListener(e -> {
            StringBuilder registros = new StringBuilder("Visitantes registrados:\n");
            for (Visitante visitante : visitantes) {
                registros.append("Nombre: ").append(visitante.getNombre())
                        .append(", Correo: ").append(visitante.getCorreo()).append("\n");
            }
            registros.append("\nPedidos realizados:\n");
            for (Pedido pedido : pedidos) {
                registros.append("Alimento: ").append(pedido.getAlimento())
                        .append(", Cantidad: ").append(pedido.getCantidad()).append("\n");
            }
            txtRegistros.setText(registros.toString());
        });

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        topPanel.add(btnRegistrar);
        topPanel.add(btnMostrarRegistros);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "MenuPrincipal"));

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtRegistros), BorderLayout.CENTER);
        panel.add(btnVolver, BorderLayout.SOUTH);

        return panel;
    }
}
