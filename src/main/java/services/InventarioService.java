package services;

import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import models.Inventario;
import models.Producto;
import repositories.InventarioRepository;
import repositories.ProductoCriteria;
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

    public void actualizarProducto(Producto producto, long cantidad) throws SQLException {
        productoRepository.update(producto.getId(), producto);
        inventarioRepository.updateStock(producto.getId(), cantidad);
    }

    public void fillProveedoresCombobox(DefaultComboBoxModel<ProveedorItem> model) throws SQLException {
        proveedorRepository.findAll(false).forEach(proveedor -> {
            ProveedorItem item = new ProveedorItem(proveedor.getId(), proveedor.getNombre());
            model.insertElementAt(item, 0);
        });
    }

    public void fillProveedoresCombobox(DefaultComboBoxModel<ProveedorItem> model, long selectedId) throws SQLException {
        proveedorRepository.findAll(false).forEach(proveedor -> {
            ProveedorItem item = new ProveedorItem(proveedor.getId(), proveedor.getNombre());
            model.insertElementAt(item, 0);

            if (proveedor.getId() == selectedId) {
                model.setSelectedItem(item);
            }
        });
    }

    public void query() throws SQLException {
        ProductoCriteria p = new ProductoCriteria();
        p.nombre = "Carlos";
        p.categoria = "hola";

        productoRepository.findByCriteria(true, p);

    }

}
