/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views.proveedor;

import repositories.ProveedorCriteria;

/**
 *
 * @author ili
 */
@SuppressWarnings("serial")
public final class FilterProveedorModal extends javax.swing.JDialog {

    private final ProveedorFrame parent;
    private ProveedorCriteria pc;

    public FilterProveedorModal(java.awt.Frame parent, ProveedorCriteria pc) {
        super(parent, true);
        initComponents();
        this.parent = (ProveedorFrame) parent;
        setCriteria(pc);

    }

    public void setCriteria(ProveedorCriteria pc) {
        this.pc = pc;

        nombre.setText(pc.nombre);
        direccion.setText(pc.direccion);
        email.setText(pc.email);
        telefono.setText(pc.telefono);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        proveedorlbl1 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cancelarBtn = new javax.swing.JButton();
        FiltrarBtn = new javax.swing.JButton();
        tituloLbl = new javax.swing.JLabel();
        nombrePanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Filtrar ");
        setModal(true);
        setResizable(false);

        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Dirección ");
        jLabel8.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        direccion.setToolTipText("");
        direccion.setActionCommand("<Not Set>");
        direccion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        direccion.setMargin(new java.awt.Insets(2, 10, 2, 6));
        direccion.setOpaque(true);
        jPanel.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Email");
        jLabel9.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        email.setToolTipText("");
        email.setActionCommand("<Not Set>");
        email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        email.setMargin(new java.awt.Insets(2, 10, 2, 6));
        email.setOpaque(true);
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        proveedorlbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proveedorlbl1.setText("Teléfono");
        proveedorlbl1.setPreferredSize(new java.awt.Dimension(250, 16));
        jPanel5.add(proveedorlbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        telefono.setToolTipText("");
        telefono.setActionCommand("<Not Set>");
        telefono.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        telefono.setMargin(new java.awt.Insets(2, 10, 2, 6));
        telefono.setOpaque(true);
        jPanel5.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

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

        FiltrarBtn.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        FiltrarBtn.setText("Filtrar");
        FiltrarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FiltrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltrarBtnActionPerformed(evt);
            }
        });
        jPanel4.add(FiltrarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 140, 30));

        tituloLbl.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        tituloLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLbl.setText("Filtrar proveedor");

        nombrePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Nimbus Sans", 0, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nombre");
        jLabel3.setPreferredSize(new java.awt.Dimension(250, 16));
        nombrePanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 40));

        nombre.setToolTipText("");
        nombre.setActionCommand("<Not Set>");
        nombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        nombre.setMargin(new java.awt.Insets(2, 10, 2, 6));
        nombre.setOpaque(true);
        nombrePanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 400, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombrePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(nombrePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(712, 493));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void FiltrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltrarBtnActionPerformed
        pc.nombre = nombre.getText().trim();
        pc.direccion = direccion.getText().trim();
        pc.email = email.getText().trim();
        pc.telefono = telefono.getText().trim();

        //
        parent.setCriteria(pc);
        dispose();
    }//GEN-LAST:event_FiltrarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FiltrarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField nombre;
    private javax.swing.JPanel nombrePanel1;
    private javax.swing.JLabel proveedorlbl1;
    private javax.swing.JTextField telefono;
    private javax.swing.JLabel tituloLbl;
    // End of variables declaration//GEN-END:variables
}
