/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views.proveedor;

import exceptions.ValidationModelException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Proveedor;
import repositories.ProveedorRepository;
import views.ErrorHandler;
import views.RealTimeValidator;
import views.ValidationRule;

/**
 *
 * @author ili
 */
public class CrearProveedorModal extends javax.swing.JDialog {

    private final ProveedorRepository proveedorRepository;
    private final ProveedorFrame parent;

    /**
     * Creates new form CrearProveedorModal
     *
     * @param parent
     * @param proveedorRepository
     */
    public CrearProveedorModal(java.awt.Frame parent, ProveedorRepository proveedorRepository) {
        super(parent, true);
        initComponents();
        this.parent = (ProveedorFrame) parent;
        this.proveedorRepository = proveedorRepository;

        RealTimeValidator.addValidation(nombre, new ValidationRule(Proveedor::NombreValido, nombreError));
        RealTimeValidator.addValidation(direccion, new ValidationRule(Proveedor::DireccionValida, direccionError));
        RealTimeValidator.addValidation(telefono, new ValidationRule(Proveedor::TelefonoValido, telefonoError));
        RealTimeValidator.addValidation(email, new ValidationRule(Proveedor::EmailValido, emailError));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        proveedorlbl1 = new javax.swing.JLabel();
        telefonoError = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        emailError = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        direccionError = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        nombrePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombreError = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cancelarBtn = new javax.swing.JButton();
        agregarBtn = new javax.swing.JButton();
        tituloLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        proveedorlbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proveedorlbl1.setText("Teléfono");
        proveedorlbl1.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel5.add(proveedorlbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        telefonoError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        telefonoError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        telefonoError.setOpaque(true);
        jPanel5.add(telefonoError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        telefono.setToolTipText("");
        telefono.setActionCommand("<Not Set>");
        telefono.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        telefono.setMargin(new java.awt.Insets(2, 10, 2, 6));
        telefono.setOpaque(true);
        jPanel5.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Email");
        jLabel9.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        emailError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        emailError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        emailError.setOpaque(true);
        jPanel1.add(emailError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        email.setToolTipText("");
        email.setActionCommand("<Not Set>");
        email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        email.setMargin(new java.awt.Insets(2, 10, 2, 6));
        email.setOpaque(true);
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Dirección ");
        jLabel8.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        direccionError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        direccionError.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        direccionError.setOpaque(true);
        jPanel.add(direccionError, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 400, 20));

        direccion.setToolTipText("");
        direccion.setActionCommand("<Not Set>");
        direccion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        direccion.setMargin(new java.awt.Insets(2, 10, 2, 6));
        direccion.setOpaque(true);
        jPanel.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

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
        nombrePanel.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

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

        tituloLbl.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        tituloLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLbl.setText("Agregar proveedor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombrePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tituloLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addComponent(nombrePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tituloLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(430, 430, 430)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setSize(new java.awt.Dimension(700, 598));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        Proveedor proveedor = new Proveedor();

        try {
            proveedor.setNombre(nombre.getText());
            proveedor.setDireccion(direccion.getText());
            proveedor.setEmail(email.getText());
            proveedor.setTelefono(telefono.getText());
            proveedorRepository.save(proveedor);

            dispose();
            parent.loadEntries(false);
            JOptionPane.showMessageDialog(null, "Proveedor agregado correctamente", "Exito", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException | ValidationModelException ex) {

            ErrorHandler.showErrorMessage(ex.getMessage());

        }
    }//GEN-LAST:event_agregarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField direccion;
    private javax.swing.JLabel direccionError;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailError;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel nombreError;
    private javax.swing.JPanel nombrePanel;
    private javax.swing.JLabel proveedorlbl1;
    private javax.swing.JTextField telefono;
    private javax.swing.JLabel telefonoError;
    private javax.swing.JLabel tituloLbl;
    // End of variables declaration//GEN-END:variables
}
