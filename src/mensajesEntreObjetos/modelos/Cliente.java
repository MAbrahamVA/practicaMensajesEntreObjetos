package mensajesEntreObjetos.modelos;

import java.time.LocalDate;
import java.time.Period;

public class Cliente {
    private String nombre;
    private String apellido;
    private String direccion;
    private String id;
    private String email;
    private LocalDate fechaNacimiento;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String direccion, String id, String email, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.id = id;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public void realizarPedido(Tienda tienda, String producto, int cantidad) {
        // CORRECCIÓN: Se añadió un espacio después de "Cliente "
        System.out.println("El Cliente " + nombre + " está enviando un pedido a la tienda");
        tienda.procesarPedido(this, producto, cantidad);
    }

    public int calcularEdad() {
        if (this.fechaNacimiento == null) {
            return 0;
        }
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }
}