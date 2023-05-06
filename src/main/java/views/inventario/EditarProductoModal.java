package views.inventario;

import exceptions.ValidationModelException;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import models.Producto;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;

@SuppressWarnings("serial")
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
            codigoBarras.setText(producto.getCodigoBarras());
            fechaCaducidad.setText(producto.getFechaCaducidad().toString());
            precio.setText(String.valueOf(producto.getPrecioPublico()));
            costo.setText(String.valueOf(producto.getCosto()));
            categoria.setText(producto.getCategoria());
            marca.setText(producto.getMarca());
            edicion.setText(producto.getEdicion());

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
                @SuppressWarnings("unchecked")
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
        fechaCaducidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        cancelarBtn = new javax.swing.JButton();
        editarBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        proveedorSelect = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        costo = new javax.swing.JTextField();
        codigoBarras = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        categoria = new javax.swing.JTextField();
        marca = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        edicion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editar producto");

        jLabel2.setText("Nombre");

        jLabel3.setText("Fecha de caducidad");

        jLabel4.setText("Precio $");

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

        jLabel8.setText("Costo $");

        jLabel9.setText("Codigo de barras");

        jLabel10.setText("Categoria");

        jLabel11.setText("Marca");

        jLabel12.setText("Edicion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(422, Short.MAX_VALUE)
                .addComponent(cancelarBtn)
                .addGap(128, 128, 128)
                .addComponent(editarBtn)
                .addContainerGap(421, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(40, 40, 40)
                .addComponent(proveedorSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(411, 411, 411))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30)
                        .addComponent(precio))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fechaCaducidad)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(categoria)
                    .addComponent(codigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8)
                        .addGap(24, 24, 24)
                        .addComponent(costo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edicion)
                            .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(183, 183, 183))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(fechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(edicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(proveedorSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editarBtn)
                    .addComponent(cancelarBtn))
                .addContainerGap(104, Short.MAX_VALUE))
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
            producto.setProveedorId(proveedorItem.getId());
            producto.setNombre(nombre.getText());
            producto.setCodigoBarras(codigoBarras.getText());
            producto.setFechaCaducidad(LocalDate.parse(fechaCaducidad.getText()));
            producto.setPrecioPublico(Double.parseDouble(precio.getText()));
            producto.setCosto(Double.parseDouble(costo.getText()));
            producto.setCategoria(categoria.getText());
            producto.setMarca(marca.getText());
            producto.setEdicion(edicion.getText());
            
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
    private javax.swing.JTextField categoria;
    private javax.swing.JTextField codigoBarras;
    private javax.swing.JTextField costo;
    private javax.swing.JTextField edicion;
    private javax.swing.JButton editarBtn;
    private javax.swing.JTextField fechaCaducidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precio;
    private javax.swing.JComboBox<String> proveedorSelect;
    // End of variables declaration//GEN-END:variables
}
