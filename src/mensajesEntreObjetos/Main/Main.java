package mensajesEntreObjetos.Main;

import mensajesEntreObjetos.Interface.gestionTienda;
import mensajesEntreObjetos.modelos.Tienda;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        gestionTienda miTienda = new Tienda();
        int opcion = 0;

        do {
            try {
                String menu = "--- MENÚ GESTIÓN TOTAL ---\n" +
                        "1. Registrar Cliente\n" +
                        "2. Ver Clientes\n" +
                        "--------------------------\n" +
                        "3. Generar Nuevo Pedido\n" +
                        "4. Ver Todos los Pedidos\n" +
                        "5. Editar un Pedido\n" +
                        "6. Eliminar un Pedido\n" +
                        "7. Salir\n" +
                        "Seleccione una opción:";

                String input = JOptionPane.showInputDialog(menu);
                if (input == null) break; // Cierra si presionan cancelar o la "X"
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1 -> miTienda.agregarCliente();
                    case 2 -> miTienda.listarClientes();
                    case 3 -> miTienda.iniciarPedido();
                    case 4 -> miTienda.listarPedidos();
                    case 5 -> miTienda.modificarPedido();
                    case 6 -> miTienda.eliminarPedido();
                    case 7 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido.");
            }
        } while (opcion != 7);
    }
}