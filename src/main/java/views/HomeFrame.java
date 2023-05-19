/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.AutenticacionController;
import db.SelectStatementMapper;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;
import models.DetallesVenta;

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
        ventasBtn = new javax.swing.JButton();
        cobrarBtn = new javax.swing.JButton();
        proveedoresBtn1 = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        inventarioBtn1 = new javax.swing.JButton();
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

        ventasBtn.setText("VENTAS");
        ventasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasBtnActionPerformed(evt);
            }
        });

        cobrarBtn.setText("COBRAR");
        cobrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobrarBtnActionPerformed(evt);
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

        inventarioBtn1.setText("INVENTARIO");
        inventarioBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelIzquierdoLayout = new javax.swing.GroupLayout(panelIzquierdo);
        panelIzquierdo.setLayout(panelIzquierdoLayout);
        panelIzquierdoLayout.setHorizontalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdoLayout.createSequentialGroup()
                .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdoLayout.createSequentialGroup()
                        .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelIzquierdoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(proveedoresBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelIzquierdoLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(logoutBtn))
                            .addGroup(panelIzquierdoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cobrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIzquierdoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ventasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzquierdoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(inventarioBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelIzquierdoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(proveedoresBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelIzquierdoLayout.setVerticalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIzquierdoLayout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addComponent(inventarioBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ventasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cobrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(proveedoresBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelIzquierdoLayout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(proveedoresBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(384, Short.MAX_VALUE)))
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

    private void ventasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasBtnActionPerformed

        SelectStatementMapper<DetallesVenta> mapper
                = new SelectStatementMapper<>("detalles_venta");

        HashMap<String, String> mapeosAttr = new HashMap<>();
        mapeosAttr.put("idProducto", "Ver detalles");

        mapper.setMapeoAtributos(mapeosAttr);

        try {
            String[][] datos = mapper.selectAllAsArray(DetallesVenta.class);

            String[] columnasTabla = {"ID Venta", "Producto", "Cantidad", "Precio Unitario"};

            DefaultTableModel modelo = new DefaultTableModel(datos, columnasTabla);
            modelo.setDataVector(datos, columnasTabla);
            
            JTable tabla = new JTable(modelo);
            
            Action detallesProductoBtn;
            detallesProductoBtn = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable) e.getSource();
                    int modelRow = Integer.parseInt(e.getActionCommand());
                    Object valor = ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 0);
                    JOptionPane.showMessageDialog(null, valor.toString());
                    
                }
            };
            
            ButtonColumn btnDetalles1 =  new ButtonColumn(tabla, detallesProductoBtn, 1);
            
            tabla.setFont(new Font("Arial", Font.PLAIN, 15));
            
            JFrame f = new JFrame();
            f.setSize(800, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JScrollPane scroll = new JScrollPane(tabla);
            panel.add(scroll, BorderLayout.CENTER);
            f.add(panel); 
            
            f.setVisible(true);

        } catch (IllegalAccessException | IllegalArgumentException
                | InstantiationException | NoSuchMethodException
                | InvocationTargetException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_ventasBtnActionPerformed

    private void cobrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobrarBtnActionPerformed
        // TODO add your handling code here:

        JFrame f = new JFrame();
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 500);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.add(new CobrarPanel());

        f.setVisible(true);

    }//GEN-LAST:event_cobrarBtnActionPerformed

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

    private void inventarioBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inventarioBtn1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cobrarBtn;
    private javax.swing.JPanel contenidoParteArriba;
    private javax.swing.JButton inventarioBtn1;
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
