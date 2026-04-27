package mensajesEntreObjetos.modelos;

public class Cliente {
    private String nombre;
    private String apellido;
    private String direccion;
    private String id;


    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String direccion, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public void realizarPedido(Tienda tienda, String producto, int cantidad) {
        System.out.println("El Cliente" + nombre + " está enviando un pedido a la tienda");
        tienda.procesarPedido(this, producto, cantidad);
    }
}