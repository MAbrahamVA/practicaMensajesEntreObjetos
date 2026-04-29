package mensajesEntreObjetos.modelos;

public class Pedido {
    private Cliente cliente;
    private String producto;
    private int cantidad;

    public Pedido() {
    }

    public Pedido(Cliente cliente, String producto, int cantidad) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return String.format(
                "==============================\n" +
                        "       DETALLE DEL PEDIDO     \n" +
                        "==============================\n" +
                        "ID Cliente : %s\n" +
                        "Cliente    : %s\n" +
                        "Dirección  : %s\n" +
                        "Producto   : %s\n" +
                        "Cantidad   : %d unidades\n" +
                        "==============================",
                cliente.getId(), cliente.getNombreCompleto(),
                cliente.getDireccion(), producto, cantidad
        );
    }
}