/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.proveedor;

import controllers.AutenticacionController;
import exceptions.ValidationModelException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repositories.ProveedorRepository;
import views.tabla.TableActionCellEditor;
import views.tabla.TableActionCellRender;
import views.tabla.TableActionEvent;

/**
 *
 * @author ili
 */
public final class ProveedorFrame extends javax.swing.JFrame {

    private final AutenticacionController authController;
    private final ProveedorRepository proveedorRepository;
    private final DefaultTableModel model;

    /**
     * Creates new form ProveedorFrame
     *
     * @param authController
     */
    public ProveedorFrame(AutenticacionController authController) {
        initComponents();
        setSize(1300, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Proveedores");
        this.authController = authController;
        this.proveedorRepository = new ProveedorRepository();
        model = (DefaultTableModel) table.getModel();
        loadEntries();

        TableActionEvent actionEvent = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                long id = (long) model.getValueAt(row, 0);
                new EditarProveedorModal(ProveedorFrame.this, proveedorRepository, id).setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                int id = (int) model.getValueAt(row, 0);
                int option = JOptionPane.showConfirmDialog(ProveedorFrame.this, "¿Estás seguro de que desea eliminar el producto con ID '" + id + "' ?", "Eliminar permanentemente", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    try {
                        proveedorRepository.delete(id);
                        loadEntries();
                        JOptionPane.showMessageDialog(ProveedorFrame.this, "Eliminado correctamente");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(ProveedorFrame.this, "No se eliminó");
                    }
                }
            }

            @Override
            public void onShow(int row) {
                int id = (int) model.getValueAt(row, 0);
                try {
                    new VerProveedorModal(ProveedorFrame.this, proveedorRepository.findById(id), proveedorRepository).setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(ProveedorFrame.this, "Error BBDD");
                    System.err.println(ex.getMessage());
                } catch (ValidationModelException ex) {
                    JOptionPane.showMessageDialog(ProveedorFrame.this, ex.getMessage());
                }
            }
        };

        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(actionEvent));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        crearBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("STIXGeneral", 1, 24)); // NOI18N
        jLabel1.setText("PROVEEDORES ");

        crearBtn.setText("Crear");
        crearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearBtnActionPerformed(evt);
            }
        });

        exitBtn.setFont(new java.awt.Font("STIXIntegralsSm", 1, 18)); // NOI18N
        exitBtn.setText("X");
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitBtnMouseClicked(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre ", "Direccion", "Email ", "Telefono", "Avance "
            }
        ));
        table.setRowHeight(50);
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(515, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addComponent(crearBtn)
                        .addGap(176, 176, 176))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitBtn)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crearBtn)))
                    .addComponent(exitBtn))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1300, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void crearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearBtnActionPerformed
        new CrearProveedorModal(ProveedorFrame.this, proveedorRepository).setVisible(true);
    }//GEN-LAST:event_crearBtnActionPerformed

    private void exitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMouseClicked
        int opt = JOptionPane.showConfirmDialog(rootPane, "¿Estás seguro de que deseas salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_exitBtnMouseClicked

    private void fullScreen() {
        setLocationRelativeTo(null);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(0, 0);
        this.setSize(dimension.width, dimension.height);
        this.setResizable(false);
        this.setTitle("Inventario");
        this.setVisible(true);
    }

    public void loadEntries() {
        model.setRowCount(0);
        try {
            proveedorRepository.findAll().forEach(proveedor -> {
                Object[] row = new Object[6];
                row[0] = proveedor.getId();
                row[1] = proveedor.getNombre();
                row[2] = proveedor.getDireccion();
                row[3] = proveedor.getEmail();
                row[4] = proveedor.getTelefono();
                row[5] = "";
                model.addRow(row);
            });
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "ERROR BBDD");
            System.err.println(ex.getMessage());
        } catch (ValidationModelException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearBtn;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
