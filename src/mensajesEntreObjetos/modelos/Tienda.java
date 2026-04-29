package mensajesEntreObjetos.modelos;

import mensajesEntreObjetos.Interface.gestionTienda;

import javax.swing.JOptionPane;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Tienda implements gestionTienda {

    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    public Tienda() {
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    @Override
    public void agregarCliente() {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String nombre = JOptionPane.showInputDialog("Nombre:");
            if (nombre == null) return; // Si cancela, sale del método

            String apellido = JOptionPane.showInputDialog("Apellido:");
            String dir = JOptionPane.showInputDialog("Dirección:");
            String id = JOptionPane.showInputDialog("ID:");
            String email = JOptionPane.showInputDialog("Email:");

            String fechaStr = JOptionPane.showInputDialog("Fecha de Nacimiento (dd/mm/aaaa):");
            if (fechaStr == null) return; // Validación extra si cancela en la fecha

            LocalDate fechaConvertida = LocalDate.parse(fechaStr, formato);
            Cliente nuevoCliente = new Cliente(nombre, apellido, dir, id, email, fechaConvertida);

            clientes.add(nuevoCliente);

            JOptionPane.showMessageDialog(null,
                    "Cliente registrado con éxito.\nEdad calculada: " + nuevoCliente.calcularEdad() + " años.");

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: El formato de fecha no es válido. Use dd/mm/aaaa",
                    "Error de entrada",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
        }
    }

    @Override
    public void listarClientes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes en la base de datos.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- CLIENTES REGISTRADOS ---\n");
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            // CORRECCIÓN: Ahora mostramos la edad en la lista de clientes
            sb.append(i).append(". ").append(c.getNombreCompleto())
                    .append(" (Edad: ").append(c.calcularEdad()).append(" años)\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    @Override
    public void iniciarPedido() {
        int index = seleccionarIndiceCliente();
        if (index != -1) {
            try {
                String producto = JOptionPane.showInputDialog("Producto a comprar:");
                if (producto == null) return;

                String inputCantidad = JOptionPane.showInputDialog("Cantidad:");
                if (inputCantidad == null) return;

                int cantidad = Integer.parseInt(inputCantidad);
                clientes.get(index).realizarPedido(this, producto, cantidad);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: La cantidad debe ser un número entero.");
            }
        }
    }

    @Override
    public void procesarPedido(Cliente cliente, String producto, int cantidad) {
        Pedido nuevoPedido = new Pedido(cliente, producto, cantidad);
        pedidos.add(nuevoPedido);
        JOptionPane.showMessageDialog(null, "Pedido Guardado:\n" + nuevoPedido);
    }

    @Override
    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pedidos registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- LISTADO DE PEDIDOS ---\n");
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            sb.append(i).append(". ").append(p.getProducto())
                    .append(" (").append(p.getCantidad()).append(" ud) - ")
                    .append(p.getCliente().getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    @Override
    public void modificarPedido() {
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pedidos para editar.");
            return;
        }
        listarPedidos();
        try {
            int index = Integer.parseInt(JOptionPane.showInputDialog("Índice del pedido a modificar:"));

            if (index >= 0 && index < pedidos.size()) {
                Pedido p = pedidos.get(index);
                p.setProducto(JOptionPane.showInputDialog("Nuevo producto:", p.getProducto()));
                p.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Nueva cantidad:", p.getCantidad())));
                JOptionPane.showMessageDialog(null, "Pedido actualizado.");
            } else {
                JOptionPane.showMessageDialog(null, "Índice fuera de rango.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.");
        }
    }

    @Override
    public void eliminarPedido() {
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pedidos para eliminar.");
            return;
        }
        listarPedidos();
        try {
            int index = Integer.parseInt(JOptionPane.showInputDialog("Índice del pedido a eliminar:"));

            if (index >= 0 && index < pedidos.size()) {
                pedidos.remove(index);
                JOptionPane.showMessageDialog(null, "Pedido borrado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Índice fuera de rango.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.");
        }
    }

    private int seleccionarIndiceCliente() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
            return -1;
        }
        listarClientes();
        try {
            String input = JOptionPane.showInputDialog("Seleccione el índice del cliente:");
            if (input == null) return -1;
            int index = Integer.parseInt(input);
            if (index >= 0 && index < clientes.size()) {
                return index;
            } else {
                JOptionPane.showMessageDialog(null, "Índice de cliente no válido.");
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}