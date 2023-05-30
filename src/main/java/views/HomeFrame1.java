/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import app.Sesion;
import controllers.AutenticacionController;
import db.SelectStatementMapper;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Venta;
import views.inventario.InventarioFrame;
import views.proveedor.ProveedorFrame;

/**
 *
 * @author Raul
 */
@SuppressWarnings("serial")
public class HomeFrame1 extends javax.swing.JFrame {

    private final AutenticacionController authController;

    public HomeFrame1(AutenticacionController authController) {
        this.authController = authController;
        initComponents();
        setSize(630, 700);
        setLocationRelativeTo(null);
        setTitle("Página de inicio");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelIzquierdo = new javax.swing.JPanel();
        ventasBtn = new javax.swing.JButton();
        cobrarBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        inventarioBtn = new javax.swing.JButton();
        proveedoresBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-100), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100));

        panelIzquierdo.setBackground(new java.awt.Color(204, 255, 255));
        panelIzquierdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ventasBtn.setText("VENTAS");
        ventasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(ventasBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 220, 30));

        cobrarBtn.setText("COBRAR");
        cobrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobrarBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(cobrarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 214, 30));

        logoutBtn.setText("Cerrar sesión");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 590, -1, 24));

        inventarioBtn.setText("INVENTARIO");
        inventarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(inventarioBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 230, 30));

        proveedoresBtn.setText("PROVEEDORES");
        proveedoresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedoresBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(proveedoresBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 200, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario_icono (1).png"))); // NOI18N
        panelIzquierdo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventas_icono (1).png"))); // NOI18N
        panelIzquierdo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 150, 180));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/productos_icono (1).png"))); // NOI18N
        panelIzquierdo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proveedores_icono (1).png"))); // NOI18N
        panelIzquierdo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Bienvenido, " + Sesion.instance().getUsuario().getNombre());
        panelIzquierdo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        getContentPane().add(panelIzquierdo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void proveedoresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedoresBtnActionPerformed
        new ProveedorFrame().setVisible(true);
    }//GEN-LAST:event_proveedoresBtnActionPerformed

    private void inventarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioBtnActionPerformed
        // TODO add your handling code here:
        InventarioFrame f = new InventarioFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_inventarioBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed

        int option = JOptionPane.showConfirmDialog(rootPane, "¿Estás seguro de que desea cerrar la sesión?");

        if (option == JOptionPane.YES_OPTION) {
            dispose();
            authController.logout();
            new LoginFrame().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void cobrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobrarBtnActionPerformed
        // TODO add your handling code here:

        JFrame f = new JFrame();
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 500);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setTitle("Realizar una venta");
        
        f.add(new CobrarPanel());

        f.setVisible(true);
    }//GEN-LAST:event_cobrarBtnActionPerformed

    private void ventasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasBtnActionPerformed

        SelectStatementMapper<Venta> ventaMap
        = new SelectStatementMapper<>("ventas");
        
        ventaMap.buscandoUsuario = true;

        try {
            String[][] datos = ventaMap.selectAllAsArray(Venta.class, new String[]{"Detalles"});

            String[] columnasTabla = {"ID de Venta", "Total", "Fecha", "Nombre de usuario", "Forma pago", ""};

            DefaultTableModel modelo = new DefaultTableModel(datos, columnasTabla);
            modelo.setDataVector(datos, columnasTabla);

            JTable tabla = new JTable(modelo);

            /*Action usuarioIDAction;
            usuarioIDAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JTable table = (JTable) e.getSource();
                        int modelRow = Integer.parseInt(e.getActionCommand());
                        Object valor = ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 3);

                        SelectStatementMapper<Usuario> selectMap = new SelectStatementMapper<>();

                        selectMap.setSql("SELECT * FROM usuarios WHERE id = ?");

                        Usuario u = selectMap.findById(Usuario.class, valor.toString());

                        JOptionPane.showMessageDialog(null, "Nombre: " + u.getNombre());

                        //JOptionPane.showMessageDialog(null, "Detalles del usuario con id = "
                            //        + valor.toString());
                    } catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            };*/

            //new ButtonColumn(tabla, usuarioIDAction, 3,
                //new ImageIcon(getClass().getResource("/info_icon.png")));

            Action detallesVentaAction;
            detallesVentaAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable) e.getSource();
                    int modelRow = Integer.parseInt(e.getActionCommand());
                    Object valor = ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 0);
                    int id = Integer.parseInt(valor.toString());

                    VistaDetallesVenta detalleVenta = new VistaDetallesVenta(id);
                    detalleVenta.setVisible(true);
                    detalleVenta.setTitle("Detallles de la venta [" + id + "]");

                }
            };

            new ButtonColumn(tabla, detallesVentaAction, 5,
                new ImageIcon(getClass().getResource("/info_icon.png")));

            tabla.setRowHeight(30);

            JFrame f = new JFrame();
            f.setSize(800, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JScrollPane scroll = new JScrollPane(tabla);
            panel.add(scroll, BorderLayout.CENTER);
            f.add(panel);
            
            f.setTitle("Información de todas las venta");

            f.setVisible(true);

        } catch (IllegalAccessException | IllegalArgumentException
            | InstantiationException | NoSuchMethodException
            | InvocationTargetException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_ventasBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cobrarBtn;
    private javax.swing.JButton inventarioBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel panelIzquierdo;
    private javax.swing.JButton proveedoresBtn;
    private javax.swing.JButton ventasBtn;
    // End of variables declaration//GEN-END:variables
}
