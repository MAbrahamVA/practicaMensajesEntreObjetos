package mensajesEntreObjetos.Interface;

import mensajesEntreObjetos.modelos.Cliente;

public interface gestionTienda {
    void agregarCliente();
    void listarClientes();
    void iniciarPedido();
    void procesarPedido(Cliente cliente, String producto, int cantidad);
    void listarPedidos();
    void modificarPedido();
    void eliminarPedido();
}
