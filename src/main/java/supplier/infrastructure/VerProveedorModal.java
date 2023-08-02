package supplier.infrastructure;

import supplier.domain.entities.Supplier;
import supplier.domain.entities.SupplierId;

public class VerProveedorModal extends javax.swing.JDialog {

    public VerProveedorModal(java.awt.Frame parent, Long id) {
        super(parent, true);
        initComponents();

        Supplier supplier = app.App.supplierService().findById(new SupplierId(id)).get();

        idLabelValue.setText(supplier.id().toString());
        nameLabelValue.setText(supplier.name().value());
        addressLabelValue.setText(supplier.address().value());
        emailLabelValue.setText(supplier.email().value());
        phoneLabelValue.setText(supplier.phoneNumber().value());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameLabelValue = new javax.swing.JLabel();
        addressLabelValue = new javax.swing.JLabel();
        emailLabelValue = new javax.swing.JLabel();
        phoneLabelValue = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        idLabelValue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles del proveedor");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DETALLES DEL PROVEEDOR ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 48));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(129, 140, 248));
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 140, -1));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(129, 140, 248));
        jLabel3.setText("Dirección");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 150, -1));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(129, 140, 248));
        jLabel4.setText("Correo Electrónico");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 200, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(129, 140, 248));
        jLabel5.setText("Número telefónico ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 200, -1));

        nameLabelValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(nameLabelValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 458, 27));

        addressLabelValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(addressLabelValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 450, 28));

        emailLabelValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(emailLabelValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 450, 27));

        phoneLabelValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(phoneLabelValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, 450, 27));

        jLabel6.setFont(new java.awt.Font("Al Nile", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(129, 140, 248));
        jLabel6.setText("Id");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        idLabelValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(idLabelValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 191, 27));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        setSize(new java.awt.Dimension(816, 508));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabelValue;
    private javax.swing.JLabel emailLabelValue;
    private javax.swing.JLabel idLabelValue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLabelValue;
    private javax.swing.JLabel phoneLabelValue;
    // End of variables declaration//GEN-END:variables
}
