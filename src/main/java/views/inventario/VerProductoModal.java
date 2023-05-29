package views.inventario;

import exceptions.ValidationModelException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Producto;
import repositories.ProveedorRepository;

/**
 *
 * @author Carlos
 */
@SuppressWarnings("serial")
public class VerProductoModal extends javax.swing.JDialog {

    public VerProductoModal(java.awt.Frame parent, Producto producto, ProveedorRepository proveedorRepository) {
        super(parent, true);
        initComponents();

        id.setText(String.valueOf(producto.getId()));
        nombre.setText(producto.getNombre());
        codigoBarras.setText(producto.getCodigoBarras());
        precio.setText(String.valueOf(producto.getPrecioPublico()));
        costo.setText(String.valueOf(producto.getCosto()));
        categoria.setText(producto.getCategoria());

        try {
            proveedor.setText(proveedorRepository.findById(producto.getIdProveedor()).getNombre());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(parent, "ERROR DB");
            System.err.println(ex.getMessage());
        } catch (ValidationModelException ex) {
            JOptionPane.showMessageDialog(parent, ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        precio = new javax.swing.JLabel();
        proveedor = new javax.swing.JLabel();
        categoria = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        codigoBarras = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        costo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 96, 175, 26));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("ID");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 278, 56, 26));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Precio");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 181, 78, 31));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Proveedor");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 234, 91, 26));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Nombre");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 81, 26));

        id.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 278, 169, 26));

        precio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 184, 169, 26));

        proveedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 234, 169, 26));

        categoria.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 140, 169, 26));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Categoria");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, 26));

        codigoBarras.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(codigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 96, 175, 26));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Codigo de barras");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 96, 129, 26));

        costo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 187, 169, 26));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Costo");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 184, 78, 31));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRODUCTO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categoria;
    private javax.swing.JLabel codigoBarras;
    private javax.swing.JLabel costo;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel precio;
    private javax.swing.JLabel proveedor;
    // End of variables declaration//GEN-END:variables
}
