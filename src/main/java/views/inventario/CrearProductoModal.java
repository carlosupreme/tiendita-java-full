package views.inventario;

import exceptions.ValidationModelException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import models.Producto;
import repositories.InventarioRepository;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import services.InventarioService;
import views.MessageHandler;
import views.RealTimeValidator;
import views.ValidationRule;

@SuppressWarnings("serial")
public class CrearProductoModal extends javax.swing.JDialog {

    private final InventarioFrame parent;
    private final InventarioService inventarioService;

    public CrearProductoModal(java.awt.Frame parent, ProductoRepository productoRepository, ProveedorRepository proveedorRepository) {
        super(parent, true);
        initComponents();
        this.parent = (InventarioFrame) parent;
        this.inventarioService = new InventarioService(productoRepository, proveedorRepository, new InventarioRepository());
        getProveedoresIds();
        addRealTimeValidation();
    }

    private void getProveedoresIds() {
        @SuppressWarnings("unchecked")
        DefaultComboBoxModel<ProveedorItem> model = (DefaultComboBoxModel) proveedorSelect.getModel();
        try {
            inventarioService.fillProveedoresCombobox(model);
        } catch (SQLException ex) {
            MessageHandler.showErrorMessage(ex.getMessage());
        }
    }

    private void addRealTimeValidation() {
        RealTimeValidator.addValidation(nombre, new ValidationRule(Producto::esNombreValido, nombreError));
        RealTimeValidator.addValidation(codigoBarras, new ValidationRule(Producto::esCodigoValido, codigoBarrasError));
        RealTimeValidator.addValidation(categoria, new ValidationRule(Producto::esCategotiaValido, categoriaError));
        RealTimeValidator.addValidation(costo, new ValidationRule(this::validarCosto, costoError));
        RealTimeValidator.addValidation(precio, new ValidationRule(this::validarPrecio, precioError));
        RealTimeValidator.addValidation(cantidad, new ValidationRule(this::validarCantidad, cantidadError));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
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
        nombrePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombreError = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cancelarBtn = new javax.swing.JButton();
        agregarBtn = new javax.swing.JButton();
        tituloLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar producto");
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setFont(getFont().deriveFont(getFont().getSize()+2f));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(254, 254, 254));
        jPanel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(254, 254, 254));
        jPanel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        proveedorlbl1.setFont(proveedorlbl1.getFont());
        proveedorlbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proveedorlbl1.setText("Cantidad");
        proveedorlbl1.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel5.add(proveedorlbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        cantidadError.setBackground(new java.awt.Color(254, 254, 254));
        cantidadError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cantidadError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        cantidadError.setOpaque(true);
        jPanel5.add(cantidadError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        cantidad.setToolTipText("");
        cantidad.setActionCommand("<Not Set>");
        cantidad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        cantidad.setMargin(new java.awt.Insets(2, 10, 2, 6));
        cantidad.setOpaque(true);
        jPanel5.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 700, 70));

        jPanel3.setBackground(new java.awt.Color(254, 254, 254));
        jPanel3.setFont(jPanel3.getFont());
        jPanel3.setInheritsPopupMenu(true);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        proveedorSelect.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        proveedorSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        proveedorSelect.setOpaque(true);
        jPanel3.add(proveedorSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 400, 40));

        proveedorlbl.setFont(proveedorlbl.getFont());
        proveedorlbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proveedorlbl.setText("Proveedor");
        proveedorlbl.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel3.add(proveedorlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 60));

        jPanel6.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 700, 60));

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));
        jPanel2.setFont(jPanel2.getFont());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(jLabel10.getFont());
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Costo ");
        jLabel10.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        costo.setToolTipText("");
        costo.setActionCommand("<Not Set>");
        costo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        costo.setMargin(new java.awt.Insets(2, 10, 2, 6));
        costo.setOpaque(true);
        jPanel2.add(costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 200, 40));

        costoError.setBackground(new java.awt.Color(254, 254, 254));
        costoError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        costoError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        costoError.setOpaque(true);
        jPanel2.add(costoError, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 250, 20));

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
        jPanel2.add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 260, 40));

        precioError.setBackground(new java.awt.Color(254, 254, 254));
        precioError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        precioError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        precioError.setOpaque(true);
        jPanel2.add(precioError, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 260, 20));

        jLabel12.setFont(jLabel12.getFont());
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Precio");
        jLabel12.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 80, 40));

        jPanel6.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 700, 60));

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setFont(jPanel1.getFont());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(jLabel9.getFont());
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Categoria");
        jLabel9.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        categoriaError.setBackground(new java.awt.Color(254, 254, 254));
        categoriaError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        categoriaError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        categoriaError.setOpaque(true);
        jPanel1.add(categoriaError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        categoria.setToolTipText("");
        categoria.setActionCommand("<Not Set>");
        categoria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        categoria.setMargin(new java.awt.Insets(2, 10, 2, 6));
        categoria.setOpaque(true);
        jPanel1.add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        jPanel6.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 700, 60));

        jPanel.setBackground(new java.awt.Color(254, 254, 254));
        jPanel.setFont(jPanel.getFont());
        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(jLabel8.getFont());
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Código de barras");
        jLabel8.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        codigoBarrasError.setBackground(new java.awt.Color(254, 254, 254));
        codigoBarrasError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        codigoBarrasError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        codigoBarrasError.setOpaque(true);
        jPanel.add(codigoBarrasError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        codigoBarras.setToolTipText("");
        codigoBarras.setActionCommand("<Not Set>");
        codigoBarras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        codigoBarras.setMargin(new java.awt.Insets(2, 10, 2, 6));
        codigoBarras.setOpaque(true);
        jPanel.add(codigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        jPanel6.add(jPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 700, 60));

        nombrePanel.setBackground(new java.awt.Color(254, 254, 254));
        nombrePanel.setFont(nombrePanel.getFont());
        nombrePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(jLabel2.getFont());
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre");
        jLabel2.setPreferredSize(new java.awt.Dimension(250, 16));
        nombrePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        nombreError.setBackground(new java.awt.Color(254, 254, 254));
        nombreError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        nombreError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        nombreError.setOpaque(true);
        nombrePanel.add(nombreError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        nombre.setToolTipText("");
        nombre.setActionCommand("<Not Set>");
        nombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        nombre.setMargin(new java.awt.Insets(2, 10, 2, 6));
        nombre.setOpaque(true);
        nombrePanel.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        jPanel6.add(nombrePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 700, 60));

        jPanel4.setBackground(new java.awt.Color(254, 254, 254));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancelarBtn.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        cancelarBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancelarBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelarBtn.setText("Cancelar");
        cancelarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });
        jPanel4.add(cancelarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 120, 30));

        agregarBtn.setBackground(new java.awt.Color(129, 140, 248));
        agregarBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        agregarBtn.setForeground(new java.awt.Color(255, 255, 255));
        agregarBtn.setText("Agregar");
        agregarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });
        jPanel4.add(agregarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 140, 30));

        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 700, 80));

        tituloLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tituloLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLbl.setText("Agregar producto");
        jPanel6.add(tituloLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 40));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private boolean validarPrecio(String precio) throws ValidationModelException {
        try {
            Double p = Double.parseDouble(precio);
            Producto.esPrecioValido(p);
        } catch (Exception ex) {
            throw new ValidationModelException("El precio debe ser un numero valido mayor a 0");
        }

        return true;
    }

    private boolean validarCosto(String costo) throws ValidationModelException {
        try {
            Double p = Double.parseDouble(costo);
            Producto.esCostoValido(p);
        } catch (Exception ex) {
            throw new ValidationModelException("El costo debe ser un numero valido mayor a 0");
        }

        return true;
    }

    private boolean validarCantidad(String cantidad) throws ValidationModelException {
        try {
            long p = Long.parseLong(cantidad);
            if (p <= 0 || p >= Long.MAX_VALUE) {
                throw new Exception();
            }
        } catch (Exception ex) {
            throw new ValidationModelException("La cantidad debe ser un numero entero valido mayor a 0");
        }

        return true;
    }

    private void focusInput(String message) {
        try {
            Class<?> clazz = this.getClass();
            for (Field field : clazz.getDeclaredFields()) {

                if (!field.getType().equals(JTextField.class) || !message.contains(field.getName())) {
                    continue;
                }

                JTextField textField = (JTextField) field.get(this);
                textField.requestFocus();
                textField.selectAll();
            }
        } catch (IllegalAccessException e) {
            System.err.println("No se pudo acceder a los campos.");
        }
    }

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        Producto producto = new Producto();

        try {
            producto.setNombre(nombre.getText());
            producto.setCodigoBarras(codigoBarras.getText());
            producto.setCategoria(categoria.getText());

            validarCosto(costo.getText());
            producto.setCosto(Double.parseDouble(costo.getText()));

            validarPrecio(precio.getText());
            producto.setPrecioPublico(Double.parseDouble(precio.getText()));
            ProveedorItem proveedorItem = (ProveedorItem) proveedorSelect.getSelectedItem();
            producto.setIdProveedor(proveedorItem.getId());

            validarCantidad(cantidad.getText());

            inventarioService.agregarProducto(producto, Long.parseLong(cantidad.getText()));

            dispose();
            parent.loadEntries();
            MessageHandler.showSuccessMessage("Producto agregado correctamente", null);
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Duplicate entry '" + codigoBarras.getText() + "' for key 'productos.codigo_barras'")) {
                MessageHandler.showErrorMessage("El código de barras ya fue registrado, ingresa uno diferente");
                codigoBarras.requestFocus();
                codigoBarras.selectAll();
            } else {
                MessageHandler.showErrorMessage(ex.getMessage());
            }
        } catch (ValidationModelException ex) {
            MessageHandler.showErrorMessage(ex.getMessage());
            focusInput(ex.getMessage().contains("codigo de barras") ? "codigoBarras" : ex.getMessage());
        } catch (Exception e) {
            MessageHandler.showErrorMessage("El proveedor es requerido");
            proveedorSelect.requestFocus();
        }
    }//GEN-LAST:event_agregarBtnActionPerformed

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
    private javax.swing.JPanel jPanel6;
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
