package views.inventario;

import exceptions.ValidationModelException;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import models.Producto;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;

public class EditarProductoModal extends javax.swing.JDialog {

    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;
    private final int productoId;

    public EditarProductoModal(java.awt.Frame parent, ProductoRepository productoRepository, ProveedorRepository proveedorRepository, int id) {
        super(parent, true);
        initComponents();
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
        productoId = id;
        try {
            Producto producto = productoRepository.findById(id);
            nombre.setText(producto.getNombre());
            descripcion.setText(producto.getDescripcion());
            precio.setText(String.valueOf(producto.getPrecio()));
            getProveedoresIds(producto.getProveedorId());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error de BBDD");
            System.err.println(ex.getMessage());
        } catch (ValidationModelException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }

    }

    private void getProveedoresIds(int proveedorIdSelected) {
        try {
            proveedorRepository.findAll().forEach(proveedor -> {
                DefaultComboBoxModel<ProveedorItem> model = (DefaultComboBoxModel) proveedorSelect.getModel();
                ProveedorItem item = new ProveedorItem(proveedor.getId(), proveedor.getNombre());
                model.insertElementAt(item, 0);

                if (proveedor.getId() == proveedorIdSelected) {
                    proveedorSelect.setSelectedItem(item);
                }
            });
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "ERROR BBDD");
            System.err.println(ex.getMessage());
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
        jLabel3 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cancelarBtn = new javax.swing.JButton();
        editarBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        proveedorSelect = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editar producto");

        jLabel2.setText("Nombre");

        jLabel3.setText("Descripcion");

        jLabel4.setText("Precio");

        jLabel5.setText("$");

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        editarBtn.setText("Editar");
        editarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Proveedor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelarBtn)
                        .addGap(128, 128, 128)
                        .addComponent(editarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(proveedorSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precio, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                            .addComponent(descripcion)
                            .addComponent(nombre))))
                .addContainerGap(80, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(proveedorSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editarBtn)
                    .addComponent(cancelarBtn))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void editarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBtnActionPerformed
        try {
            ProveedorItem proveedorItem = (ProveedorItem) proveedorSelect.getSelectedItem();

            Producto producto = new Producto();
            producto.setId(productoId);
            producto.setNombre(nombre.getText());
            producto.setDescripcion(descripcion.getText());
            producto.setPrecio(Double.parseDouble(precio.getText()));
            producto.setProveedorId(proveedorItem.getId());

            productoRepository.update(productoId, producto);

            dispose();
            JOptionPane.showMessageDialog(rootPane, "Editado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos, no se editó el producto");
            System.err.println(e.getMessage());
        } catch (ValidationModelException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPane, "El precio debe ser un numero valido mayor a 0");
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(rootPane, "El proveedor es requerido");
        }
    }//GEN-LAST:event_editarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField descripcion;
    private javax.swing.JButton editarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precio;
    private javax.swing.JComboBox<String> proveedorSelect;
    // End of variables declaration//GEN-END:variables
}
