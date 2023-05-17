/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.AutenticacionController;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.inventario.InventarioFrame;

/**
 *
 * @author Raul
 */
@SuppressWarnings("serial")
public class HomeFrame extends javax.swing.JFrame {

    private final AutenticacionController authController;

    public HomeFrame(AutenticacionController authController) {
        this.authController = authController;
        initComponents();
        setResizable(false);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelIzquierdo = new javax.swing.JPanel();
        proveedoresBtn = new javax.swing.JButton();
        inventarioBtn = new javax.swing.JButton();
        ventasBtn = new javax.swing.JButton();
        proveedoresBtn1 = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        panelDerecho = new javax.swing.JPanel();
        panelContenido = new javax.swing.JPanel();
        contenidoParteArriba = new javax.swing.JPanel();
        panelScroll = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-100), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100));

        panelIzquierdo.setBackground(new java.awt.Color(0, 51, 204));

        proveedoresBtn.setText("PROVEEDORES");
        proveedoresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedoresBtnActionPerformed(evt);
            }
        });

        inventarioBtn.setText("INVENTARIO");
        inventarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioBtnActionPerformed(evt);
            }
        });

        ventasBtn.setText("COBRAR");
        ventasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasBtnActionPerformed(evt);
            }
        });

        proveedoresBtn1.setText("USUARIOS");
        proveedoresBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedoresBtn1ActionPerformed(evt);
            }
        });

        logoutBtn.setText("Cerrar sesión");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelIzquierdoLayout = new javax.swing.GroupLayout(panelIzquierdo);
        panelIzquierdo.setLayout(panelIzquierdoLayout);
        panelIzquierdoLayout.setHorizontalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIzquierdoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addGap(67, 67, 67))
            .addGroup(panelIzquierdoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(proveedoresBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelIzquierdoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ventasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inventarioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(proveedoresBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelIzquierdoLayout.setVerticalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIzquierdoLayout.createSequentialGroup()
                .addContainerGap(281, Short.MAX_VALUE)
                .addComponent(proveedoresBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelIzquierdoLayout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(proveedoresBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addComponent(inventarioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addComponent(ventasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(254, Short.MAX_VALUE)))
        );

        getContentPane().add(panelIzquierdo, java.awt.BorderLayout.LINE_START);

        panelDerecho.setLayout(new java.awt.BorderLayout());

        panelContenido.setLayout(new java.awt.BorderLayout());

        contenidoParteArriba.setBackground(new java.awt.Color(51, 204, 255));

        javax.swing.GroupLayout contenidoParteArribaLayout = new javax.swing.GroupLayout(contenidoParteArriba);
        contenidoParteArriba.setLayout(contenidoParteArribaLayout);
        contenidoParteArribaLayout.setHorizontalGroup(
            contenidoParteArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );
        contenidoParteArribaLayout.setVerticalGroup(
            contenidoParteArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        panelContenido.add(contenidoParteArriba, java.awt.BorderLayout.PAGE_START);
        panelContenido.add(panelScroll, java.awt.BorderLayout.CENTER);

        panelDerecho.add(panelContenido, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelDerecho, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void proveedoresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedoresBtnActionPerformed


    }//GEN-LAST:event_proveedoresBtnActionPerformed

    private void inventarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioBtnActionPerformed
        new InventarioFrame(authController).setVisible(true);
    }//GEN-LAST:event_inventarioBtnActionPerformed

    private void ventasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasBtnActionPerformed
        // TODO add your handling code here:

        JFrame f = new JFrame();
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 500);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.add(new CobrarPanel());

        f.setVisible(true);

    }//GEN-LAST:event_ventasBtnActionPerformed

    private void proveedoresBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedoresBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedoresBtn1ActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed

        int option = JOptionPane.showConfirmDialog(rootPane, "¿Estás seguro de que desea cerrar la sesión?");

        if (option == JOptionPane.YES_OPTION) {
            dispose();
            authController.logout();
            new LoginFrame().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenidoParteArriba;
    private javax.swing.JButton inventarioBtn;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel panelIzquierdo;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JButton proveedoresBtn;
    private javax.swing.JButton proveedoresBtn1;
    private javax.swing.JButton ventasBtn;
    // End of variables declaration//GEN-END:variables
}
