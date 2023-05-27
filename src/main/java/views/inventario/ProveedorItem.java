package views.inventario;

/**
 *
 * @author Carlos Esta clase sirve para agregar items en un ComboBox, con el
 * nombre del proveedor como texto que se muestra en la UI Y su id como valor de
 * mismo, esto para facilitar el acceso a la llave foranea que necesita el
 * Producto en su creación
 */
public class ProveedorItem {

    private int id;
    private String nombre;

    public ProveedorItem(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
