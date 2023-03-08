/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.proyecto.administradortiendita.view;

import edu.proyecto.administradortiendita.controller.login.AutenticacionController;
import helpers.Confirmation;
import helpers.ConfirmationModal;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

/**
 *
 * @author carlos
 */
public class HomeFrame extends javax.swing.JFrame {

    private AutenticacionController authController;

    /**
     * Creates new form HomeFrame
     */
    public HomeFrame(AutenticacionController authController) {
        initComponents();
        setLocationRelativeTo(null);
        this.authController = authController;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logoutBtn.setText("Cerrar sesión");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(261, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(logoutBtn)
                .addContainerGap(259, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        ConfirmationModal modal = new ConfirmationModal(this, "¿Estás seguro de que desea cerrar la sesión?", new Confirmation() {
            @Override
            public void onConfirm(JDialog component, ActionEvent evt) {
                component.dispose();
                dispose();
                authController.logout();
                new LoginFrame().setVisible(true);
            }

            @Override
            public void onCancel(JDialog component, ActionEvent evt) {
                component.dispose();
            }
        });
        modal.setVisible(true);
    }//GEN-LAST:event_logoutBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton logoutBtn;
    // End of variables declaration//GEN-END:variables
}
