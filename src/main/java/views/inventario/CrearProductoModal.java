package views.inventario;

import db.PreparedStatementMapper;
import exceptions.ValidationModelException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
        RealTimeValidator realTimeValidator = new RealTimeValidator();
        
        ArrayList<HashMap<JTextField, JLabel>> textInputs = new ArrayList<>();
        HashMap<JTextField, JLabel> nombrehash = new HashMap<>();
        
        nombrehash.put(nombre, nombreError);
        
        textInputs.add(nombrehash);
        
        realTimeValidator.setTextInputs(textInputs);
        realTimeValidator.addTextValidation();
        
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
        categoria = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        costo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        nombreError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar producto");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 40));

        nombre.setToolTipText("");
        nombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 330, 40));

        jLabel2.setText("Nombre");
        jLabel2.setPreferredSize(new java.awt.Dimension(250, 16));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 60, 40));

        jLabel4.setText("Precio");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 60, 30));
        getContentPane().add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 73, 30));

        jLabel5.setText("$");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 12, 30));

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });
        getContentPane().add(cancelarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, -1, -1));

        agregarBtn.setText("Agregar");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });
        getContentPane().add(agregarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, -1, -1));

        jLabel6.setText("Proveedor");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 30));

        getContentPane().add(proveedorSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 138, 30));
        getContentPane().add(codigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 170, 40));

        jLabel7.setLabelFor(codigoBarras);
        jLabel7.setText("Codigo de barras");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 110, 40));
        getContentPane().add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 144, 30));

        jLabel9.setText("Categoria");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 30));

        jLabel11.setText("Costo");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 50, 30));
        getContentPane().add(costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 76, 30));

        jLabel12.setText("$");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 12, 30));

        nombreError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        nombreError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        getContentPane().add(nombreError, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 330, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        try {
            
            PreparedStatementMapper<Producto> mapper = new PreparedStatementMapper<>("productos");
            
            ProveedorItem proveedorItem = (ProveedorItem) proveedorSelect.getSelectedItem();
            
            Producto producto = new Producto();
            producto.setIdProveedor(proveedorItem.getId());
            producto.setNombre(nombre.getText());
            producto.setCodigoBarras(codigoBarras.getText());
            producto.setPrecioPublico(Double.parseDouble(precio.getText()));
            producto.setCosto(Double.parseDouble(costo.getText()));
            producto.setCategoria(categoria.getText());
            
            mapper.insertar(producto);
            
            dispose();
            parent.loadEntries();
            JOptionPane.showMessageDialog(rootPane, "Agregado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos, no se agregó el producto");
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel nombreError;
    private javax.swing.JTextField precio;
    private javax.swing.JComboBox<String> proveedorSelect;
    // End of variables declaration//GEN-END:variables
}
