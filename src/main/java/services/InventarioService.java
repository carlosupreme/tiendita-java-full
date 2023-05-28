package services;

import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import models.Inventario;
import models.Producto;
import repositories.InventarioRepository;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import views.inventario.ProveedorItem;

/**
 *
 * @author carlos
 */
public class InventarioService {

    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;
    private final InventarioRepository inventarioRepository;

    public InventarioService(ProductoRepository productoRepository, ProveedorRepository proveedorRepository, InventarioRepository inventarioRepository) {
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
        this.inventarioRepository = inventarioRepository;
    }

    public void agregarProducto(Producto producto, long cantidad) throws SQLException {
        productoRepository.save(producto);
        inventarioRepository.save(new Inventario(producto.getId(), cantidad));
    }

    public void fillProveedoresCombobox(DefaultComboBoxModel<ProveedorItem> model) throws SQLException {
        proveedorRepository.findAll().forEach(proveedor -> {
            ProveedorItem item = new ProveedorItem(proveedor.getId(), proveedor.getNombre());
            model.insertElementAt(item, 0);
        });
    }

}
