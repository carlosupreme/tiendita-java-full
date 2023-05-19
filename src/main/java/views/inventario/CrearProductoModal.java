package views.inventario;

import exceptions.ValidationModelException;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import models.Producto;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;

@SuppressWarnings("serial")
public class CrearProductoModal extends javax.swing.JDialog {

    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;
    InventarioFrame parent;

    public CrearProductoModal(java.awt.Frame parent, ProductoRepository productoRepository, ProveedorRepository proveedorRepository) {
        super(parent, true);
        initComponents();
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
        this.parent = (InventarioFrame) parent;

        getProveedoresIds();
    }

    private void getProveedoresIds() {
        try {
            proveedorRepository.findAll().forEach(proveedor -> {
                @SuppressWarnings("unchecked")
                DefaultComboBoxModel<ProveedorItem> model = (DefaultComboBoxModel) proveedorSelect.getModel();
                ProveedorItem item = new ProveedorItem(proveedor.getId(), proveedor.getNombre());
                model.insertElementAt(item, 0);
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos al obtener los proveedores");
            System.err.println(e.getMessage());
        } catch (ValidationModelException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cancelarBtn = new javax.swing.JButton();
        agregarBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        proveedorSelect = new javax.swing.JComboBox<>();
        codigoBarras = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        marca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        categoria = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        costo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar producto");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1124, 32));
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 63, 136, -1));

        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 66, -1, -1));

        jLabel4.setText("Precio");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 319, -1, -1));
        getContentPane().add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 316, 73, -1));

        jLabel5.setText("$");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 319, 12, -1));

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });
        getContentPane().add(cancelarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 504, -1, -1));

        agregarBtn.setText("Agregar");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });
        getContentPane().add(agregarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 504, -1, -1));

        jLabel6.setText("Proveedor");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 423, -1, -1));

        getContentPane().add(proveedorSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 417, 138, -1));
        getContentPane().add(codigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 118, 136, -1));

        jLabel7.setText("Codigo de barras");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 121, -1, -1));
        getContentPane().add(marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 253, 136, -1));

        jLabel8.setText("Marca");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 256, -1, -1));
        getContentPane().add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 356, 144, -1));

        jLabel9.setText("Categoria");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 359, -1, -1));

        jLabel11.setText("Costo");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 479, -1, -1));
        getContentPane().add(costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 476, 76, -1));

        jLabel12.setText("$");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 479, 12, -1));

        jLabel3.setText("Descripcion");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, -1));
        getContentPane().add(descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 215, 131));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        try {

            ProveedorItem proveedorItem = (ProveedorItem) proveedorSelect.getSelectedItem();

            Producto producto = new Producto();
            producto.setProveedorId(proveedorItem.getId());
            producto.setNombre(nombre.getText());
            producto.setDescripcion(descripcion.getText());
            producto.setCodigoBarras(codigoBarras.getText());
            producto.setPrecioPublico(Double.parseDouble(precio.getText()));
            producto.setCosto(Double.parseDouble(costo.getText()));
            producto.setCategoria(categoria.getText());
            producto.setMarca(marca.getText());

            productoRepository.save(producto);

            dispose();
            JOptionPane.showMessageDialog(rootPane, "Agregado correctamente");
            parent.loadEntries();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos, no se agergó el producto");
            System.err.println(e.getMessage());
        } catch (ValidationModelException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, "El precio debe ser un numero valido mayor a 0");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "El proveedor es requerido");
        }
    }//GEN-LAST:event_agregarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField categoria;
    private javax.swing.JTextField codigoBarras;
    private javax.swing.JTextField costo;
    private javax.swing.JTextField descripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precio;
    private javax.swing.JComboBox<String> proveedorSelect;
    // End of variables declaration//GEN-END:variables
}
