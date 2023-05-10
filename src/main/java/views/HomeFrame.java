/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.AutenticacionController;
import db.SelectStatementMapper;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import models.Proveedor;
import views.inventario.InventarioFrame;

import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Raul
 */
public class HomeFrame extends javax.swing.JFrame {

    private final AutenticacionController authController;

    /**
     * Creates new form HomeFrame2
     *
     * @param authController
     */
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
        ventasBtn1 = new javax.swing.JButton();
        proveedoresBtn1 = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        panelDerecho = new javax.swing.JPanel();
        tituloSeccion = new javax.swing.JTextField();
        panelContenido = new javax.swing.JPanel();
        contenidoParteArriba = new javax.swing.JPanel();
        panelScroll = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-100), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100));

        panelIzquierdo.setBackground(new java.awt.Color(0, 102, 255));

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

        ventasBtn1.setText("COMPRAS A PROVEEDORES");
        ventasBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasBtn1ActionPerformed(evt);
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
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addGap(67, 67, 67))
            .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelIzquierdoLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(proveedoresBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ventasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ventasBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                        .addComponent(proveedoresBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inventarioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(10, 10, 10)))
        );
        panelIzquierdoLayout.setVerticalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIzquierdoLayout.createSequentialGroup()
                .addContainerGap(446, Short.MAX_VALUE)
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
                    .addGap(34, 34, 34)
                    .addComponent(ventasBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addComponent(proveedoresBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(119, Short.MAX_VALUE)))
        );

        getContentPane().add(panelIzquierdo, java.awt.BorderLayout.LINE_START);

        panelDerecho.setLayout(new java.awt.BorderLayout());

        tituloSeccion.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        tituloSeccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tituloSeccion.setText("Selecciona una sección");
        tituloSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tituloSeccionActionPerformed(evt);
            }
        });
        panelDerecho.add(tituloSeccion, java.awt.BorderLayout.PAGE_START);

        panelContenido.setLayout(new java.awt.BorderLayout());

        contenidoParteArriba.setBackground(new java.awt.Color(102, 204, 255));

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

        //ProveedorFrame prov = new ProveedorFrame(authController);
        //prov.setVisible(true);
        SelectStatementMapper<Proveedor> mapper = new SelectStatementMapper<>("proveedores");

        List<Proveedor> datos;
        try {
            datos = mapper.selectAll(Proveedor.class);
            String[] columnasTabla = {"id", "nombre", "direccion", "email", "telefono"};
            ModeloTabla<Proveedor> tableModel = new ModeloTabla<>(datos, columnasTabla);
            JTable table = new JTable(tableModel);
            panelScroll.setViewportView(table);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_proveedoresBtnActionPerformed

    private void inventarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioBtnActionPerformed

        new InventarioFrame(authController).setVisible(true);
    }//GEN-LAST:event_inventarioBtnActionPerformed

    private void ventasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ventasBtnActionPerformed

    private void ventasBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ventasBtn1ActionPerformed

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

    private void tituloSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloSeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tituloSeccionActionPerformed


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
    private javax.swing.JTextField tituloSeccion;
    private javax.swing.JButton ventasBtn;
    private javax.swing.JButton ventasBtn1;
    // End of variables declaration//GEN-END:variables
}
