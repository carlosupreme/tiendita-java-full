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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.Venta;
import views.inventario.InventarioFrame;
import views.proveedor.ProveedorFrame;

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
        setSize(1300, 700);
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
        setResizable(false);
        setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-100), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelIzquierdo.setBackground(new java.awt.Color(254, 254, 254));
        panelIzquierdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ventasBtn.setText("VENTAS");
        ventasBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ventasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(ventasBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 520, 220, 30));

        cobrarBtn.setText("COBRAR");
        cobrarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cobrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobrarBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(cobrarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 520, 214, 30));

        logoutBtn.setBackground(java.awt.Color.decode("#FE3F5A"));
        logoutBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setText("Cerrar sesión");
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 30, -1, 30));

        inventarioBtn.setText("INVENTARIO");
        inventarioBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inventarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(inventarioBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 230, 30));

        proveedoresBtn.setText("PROVEEDORES");
        proveedoresBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        proveedoresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedoresBtnActionPerformed(evt);
            }
        });
        panelIzquierdo.add(proveedoresBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 200, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventario_icono (1).png"))); // NOI18N
        panelIzquierdo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventas_icono (1).png"))); // NOI18N
        panelIzquierdo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 310, 150, 180));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/productos_icono (1).png"))); // NOI18N
        panelIzquierdo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proveedores_icono (1).png"))); // NOI18N
        panelIzquierdo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Bienvenido, " + Sesion.instance().getUsuario().getNombre());
        panelIzquierdo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        getContentPane().add(panelIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 700));
    }// </editor-fold>//GEN-END:initComponents

    private void proveedoresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedoresBtnActionPerformed
        new ProveedorFrame().setVisible(true);
    }//GEN-LAST:event_proveedoresBtnActionPerformed

    private void inventarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioBtnActionPerformed
        new InventarioFrame().setVisible(true);
    }//GEN-LAST:event_inventarioBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        boolean confirmed = MessageHandler.showConfirmMessage("¿Estás seguro de que desea cerrar la sesión?", "Salir");

        if (confirmed) {
            dispose();
            authController.logout();
            new LoginFrame().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void cobrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobrarBtnActionPerformed
        JFrame f = new JFrame();
        f.setSize(1300, 720);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setTitle("Realizar una venta");
        f.setLocationRelativeTo(null);
        f.setResizable(false);

        f.add(new CobrarPanel());

        f.setVisible(true);
    }//GEN-LAST:event_cobrarBtnActionPerformed

    private void ventasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasBtnActionPerformed

        SelectStatementMapper<Venta> ventaMap
                = new SelectStatementMapper<>("ventas");

        ventaMap.buscandoUsuario = true;

        JFrame f = new JFrame();

        try {
            String[][] datos = ventaMap.selectAllAsArray(Venta.class, new String[]{""});

            String[] columnasTabla = {"ID", "Total $", "Fecha", "Nombre de usuario", "Forma de pago", "Detalles"};

            DefaultTableModel modelo = new DefaultTableModel(datos, columnasTabla) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, true
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };
            modelo.setDataVector(datos, columnasTabla);

            JTable tabla = new JTable(modelo);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tabla.setDefaultRenderer(Object.class, centerRenderer);
            tabla.setShowGrid(true);
            tabla.getTableHeader().setResizingAllowed(false);
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setFont(new java.awt.Font("Segoe UI", 0, 14));
            tabla.setRowHeight(30);

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
                    Object valor = table.getModel().getValueAt(modelRow, 0);
                    int id = Integer.parseInt(valor.toString());
                    String total = (String) table.getModel().getValueAt(modelRow, 1);
                    String fecha = (String) table.getModel().getValueAt(modelRow, 2);

                    ModalDetallesVenta modalVentas = new ModalDetallesVenta(id, total, fecha);
                    modalVentas.setTitle("Detallles de la venta [" + id + "]");
                    modalVentas.setLocationRelativeTo(f);
                    modalVentas.setVisible(true);

//                    VistaDetallesVenta detalleVenta = new VistaDetallesVenta(id);
//                    detalleVenta.setTitle("Detallles de la venta [" + id + "]");
//                    detalleVenta.setLocationRelativeTo(f);
//                    detalleVenta.setModal(true);
//                    detalleVenta.setVisible(true);
                }
            };

            ButtonColumn buttonColumn = new ButtonColumn(tabla, detallesVentaAction, 5,
                    new ImageIcon(getClass().getResource("/eye.png")));

            f.setSize(1300, 720);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setResizable(false);
            f.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JScrollPane scroll = new JScrollPane();
            scroll.setViewportView(tabla);
            panel.add(scroll, BorderLayout.CENTER);
            f.add(panel);

            f.setTitle("Información de todas las ventas");

            f.setVisible(true);

        } catch (IllegalAccessException | IllegalArgumentException
                | InstantiationException | NoSuchMethodException
                | InvocationTargetException | SQLException ex) {
            MessageHandler.showErrorMessage(ex.getMessage());
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
