package views.inventario;

import exceptions.ValidationModelException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import models.Inventario;
import models.Producto;
import repositories.InventarioRepository;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import views.ErrorHandler;

@SuppressWarnings("serial")
public class CrearProductoModal extends javax.swing.JDialog {

    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;
    private final InventarioRepository inventarioRepository;
    private final InventarioFrame parent;

    public CrearProductoModal(java.awt.Frame parent, ProductoRepository productoRepository, ProveedorRepository proveedorRepository) {
        super(parent, true);
        initComponents();

        this.proveedorRepository = proveedorRepository;
        this.productoRepository = productoRepository;
        this.inventarioRepository = new InventarioRepository();
        this.parent = (InventarioFrame) parent;
        RealTimeValidator realTimeValidator = new RealTimeValidator();

        HashMap<JTextField, JLabel> textInputs = new HashMap<>();
        textInputs.put(nombre, nombreError);
        textInputs.put(categoria, categoriaError);

        HashMap<JTextField, JLabel> numericInputs = new HashMap<>();
        numericInputs.put(precio, precioError);
        numericInputs.put(costo, costoError);

        realTimeValidator.setTextInputs(textInputs);
        realTimeValidator.setNumericInputs(numericInputs);
        realTimeValidator.addTextValidation();
        realTimeValidator.addNumericValidation();
        realTimeValidator.addCodigoBarrasValidation(codigoBarras, codigoBarrasError);

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

        } catch (SQLException ex) {
            ErrorHandler.showErrorMessage("Error obteniendo proveedores");
            System.err.println(ex.getMessage());
        } catch (ValidationModelException ex) {
            ErrorHandler.showErrorMessage(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        proveedorlbl1 = new javax.swing.JLabel();
        cantidadError = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        proveedorSelect = new javax.swing.JComboBox<>();
        proveedorlbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        costo = new javax.swing.JTextField();
        costoError = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        precioError = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        categoriaError = new javax.swing.JLabel();
        categoria = new javax.swing.JTextField();
        jPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        codigoBarrasError = new javax.swing.JLabel();
        codigoBarras = new javax.swing.JTextField();
        tituloLbl = new javax.swing.JLabel();
        nombrePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombreError = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cancelarBtn = new javax.swing.JButton();
        agregarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        proveedorlbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proveedorlbl1.setText("Cantidad");
        proveedorlbl1.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel5.add(proveedorlbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        cantidadError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cantidadError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        cantidadError.setOpaque(true);
        jPanel5.add(cantidadError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        cantidad.setToolTipText("");
        cantidad.setActionCommand("<Not Set>");
        cantidad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        cantidad.setMargin(new java.awt.Insets(2, 10, 2, 6));
        cantidad.setOpaque(true);
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });
        jPanel5.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 700, 70));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        proveedorSelect.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        proveedorSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        proveedorSelect.setOpaque(true);
        jPanel3.add(proveedorSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 400, 40));

        proveedorlbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proveedorlbl.setText("Proveedor");
        proveedorlbl.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel3.add(proveedorlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 60));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 700, 60));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Costo ");
        jLabel10.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        costo.setToolTipText("");
        costo.setActionCommand("<Not Set>");
        costo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        costo.setMargin(new java.awt.Insets(2, 10, 2, 6));
        costo.setOpaque(true);
        costo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costoActionPerformed(evt);
            }
        });
        jPanel2.add(costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 200, 40));

        costoError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        costoError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        costoError.setOpaque(true);
        jPanel2.add(costoError, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 200, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("$");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 20, 40));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("$");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 20, 40));

        precio.setToolTipText("");
        precio.setActionCommand("<Not Set>");
        precio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        precio.setMargin(new java.awt.Insets(2, 10, 2, 6));
        precio.setOpaque(true);
        precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioActionPerformed(evt);
            }
        });
        jPanel2.add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 260, 40));

        precioError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        precioError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        precioError.setOpaque(true);
        jPanel2.add(precioError, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 260, 20));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Precio");
        jLabel12.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 80, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 700, 60));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Categoria");
        jLabel9.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        categoriaError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        categoriaError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        categoriaError.setOpaque(true);
        jPanel1.add(categoriaError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        categoria.setToolTipText("");
        categoria.setActionCommand("<Not Set>");
        categoria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        categoria.setMargin(new java.awt.Insets(2, 10, 2, 6));
        categoria.setOpaque(true);
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });
        jPanel1.add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 700, 60));

        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Código de barras");
        jLabel8.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        codigoBarrasError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        codigoBarrasError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        codigoBarrasError.setOpaque(true);
        jPanel.add(codigoBarrasError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        codigoBarras.setToolTipText("");
        codigoBarras.setActionCommand("<Not Set>");
        codigoBarras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        codigoBarras.setMargin(new java.awt.Insets(2, 10, 2, 6));
        codigoBarras.setOpaque(true);
        codigoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoBarrasActionPerformed(evt);
            }
        });
        jPanel.add(codigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        getContentPane().add(jPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 700, 60));

        tituloLbl.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        tituloLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLbl.setText("Agregar producto");
        getContentPane().add(tituloLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 40));

        nombrePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Nimbus Sans", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre");
        jLabel2.setPreferredSize(new java.awt.Dimension(250, 16));
        nombrePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        nombreError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        nombreError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        nombreError.setOpaque(true);
        nombrePanel.add(nombreError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        nombre.setToolTipText("");
        nombre.setActionCommand("<Not Set>");
        nombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        nombre.setMargin(new java.awt.Insets(2, 10, 2, 6));
        nombre.setOpaque(true);
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombrePanel.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        getContentPane().add(nombrePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 700, 60));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancelarBtn.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });
        jPanel4.add(cancelarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 120, 30));

        agregarBtn.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        agregarBtn.setText("Agregar");
        agregarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });
        jPanel4.add(agregarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 140, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 700, 80));

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
            producto.setIdProveedor(proveedorItem.getId());
            producto.setNombre(nombre.getText());
            producto.setCodigoBarras(codigoBarras.getText());
            producto.setPrecioPublico(Double.parseDouble(precio.getText()));
            producto.setCosto(Double.parseDouble(costo.getText()));
            producto.setCategoria(cantidad.getText());

            productoRepository.save(producto);

            Inventario i = new Inventario(producto.getId(), Long.valueOf(cantidad.getText()));
            inventarioRepository.save(i);

            dispose();
            parent.loadEntries(false);
            JOptionPane.showMessageDialog(parent, "Agregado correctamente");
        } catch (SQLException ex) {
            ErrorHandler.showErrorMessage(ex.getMessage());
            System.err.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            ErrorHandler.showErrorMessage(ex.getMessage());

            precio.requestFocusInWindow();
            precio.selectAll();

        } catch (ValidationModelException ex) {
            ErrorHandler.showErrorMessage(ex.getMessage());
        } catch (Exception e) {
            ErrorHandler.showErrorMessage("El proveedor requerido");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_agregarBtnActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void costoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costoActionPerformed

    private void precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioActionPerformed

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaActionPerformed

    private void codigoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoBarrasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cantidad;
    private javax.swing.JLabel cantidadError;
    private javax.swing.JTextField categoria;
    private javax.swing.JLabel categoriaError;
    private javax.swing.JTextField codigoBarras;
    private javax.swing.JLabel codigoBarrasError;
    private javax.swing.JTextField costo;
    private javax.swing.JLabel costoError;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel nombreError;
    private javax.swing.JPanel nombrePanel;
    private javax.swing.JTextField precio;
    private javax.swing.JLabel precioError;
    private javax.swing.JComboBox<String> proveedorSelect;
    private javax.swing.JLabel proveedorlbl;
    private javax.swing.JLabel proveedorlbl1;
    private javax.swing.JLabel tituloLbl;
    // End of variables declaration//GEN-END:variables
}
