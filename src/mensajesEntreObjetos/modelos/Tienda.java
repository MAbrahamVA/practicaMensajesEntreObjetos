package mensajesEntreObjetos.modelos;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Tienda {
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    public Tienda() {
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void agregarCliente() {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String apellido = JOptionPane.showInputDialog("Apellido:");
        String dir = JOptionPane.showInputDialog("Dirección:");
        String id = JOptionPane.showInputDialog("ID:");

        clientes.add(new Cliente(nombre, apellido, dir, id));
        JOptionPane.showMessageDialog(null, "Cliente registrado en la tienda.");
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes en la base de datos.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- CLIENTES REGISTRADOS ---\n");
        for (int i = 0; i < clientes.size(); i++) {
            sb.append(i).append(". ").append(clientes.get(i).getNombreCompleto()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void iniciarPedido() {
        int index = seleccionarIndiceCliente();
        if (index != -1) {
            String producto = JOptionPane.showInputDialog("Producto a comprar:");
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
            clientes.get(index).realizarPedido(this, producto, cantidad);
        }
    }

    public void procesarPedido(Cliente cliente, String producto, int cantidad) {
        Pedido nuevoPedido = new Pedido(cliente, producto, cantidad);
        pedidos.add(nuevoPedido); // Guardamos el pedido en la lista
        JOptionPane.showMessageDialog(null, "Pedido Guardado:\n" + nuevoPedido);
    }

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

    public void modificarPedido() {
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pedidos para editar.");
            return;
        }
        listarPedidos();
        int index = Integer.parseInt(JOptionPane.showInputDialog("Índice del pedido a modificar:"));

        if (index >= 0 && index < pedidos.size()) {
            Pedido p = pedidos.get(index);
            p.setProducto(JOptionPane.showInputDialog("Nuevo producto:", p.getProducto()));
            p.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Nueva cantidad:", p.getCantidad())));
            JOptionPane.showMessageDialog(null, "Pedido actualizado.");
        }
    }

    public void eliminarPedido() {
        if (pedidos.isEmpty()) return;
        listarPedidos();
        int index = Integer.parseInt(JOptionPane.showInputDialog("Índice del pedido a eliminar:"));

        if (index >= 0 && index < pedidos.size()) {
            pedidos.remove(index);
            JOptionPane.showMessageDialog(null, "Pedido borrado con éxito.");
        }
    }

    private int seleccionarIndiceCliente() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
            return -1;
        }
        listarClientes();
        try {
            return Integer.parseInt(JOptionPane.showInputDialog("Seleccione el índice del cliente:"));
        } catch (Exception e) { return -1; }
    }
}
