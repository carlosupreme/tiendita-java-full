package views.inventario;

import controllers.AutenticacionController;
import db.ConexionDB;
import exceptions.ValidationModelException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import views.tabla.TableActionCellEditor;
import views.tabla.TableActionCellRender;
import views.tabla.TableActionEvent;

@SuppressWarnings("serial")
public final class InventarioFrame extends javax.swing.JFrame {

    private final AutenticacionController authController;
    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;

    private final DefaultTableModel model;

    public InventarioFrame(AutenticacionController authController) {
        initComponents();
        this.authController = authController;
        this.productoRepository = new ProductoRepository();
        this.proveedorRepository = new ProveedorRepository();
        model = (DefaultTableModel) table.getModel();
        loadEntries();

        TableActionEvent actionEvent = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int id = (int) model.getValueAt(row, 0);
                new EditarProductoModal(InventarioFrame.this, productoRepository, proveedorRepository, id).setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                int id = (int) model.getValueAt(row, 0);
                int option = JOptionPane.showConfirmDialog(InventarioFrame.this, "¿Estás seguro de que desea eliminar el producto con ID '" + id + "' ?", "Eliminar permanentemente", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    try {
                        productoRepository.delete(id);
                        loadEntries();
                        JOptionPane.showMessageDialog(InventarioFrame.this, "Eliminado correctamente");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(InventarioFrame.this, "No se eliminó");
                    }
                }
            }

            @Override
            public void onShow(int row) {
                int id = (int) model.getValueAt(row, 0);
                try {
                    new VerProductoModal(InventarioFrame.this, productoRepository.findById(id), proveedorRepository).setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(InventarioFrame.this, "Error BBDD");
                    System.err.println(ex.getMessage());
                } catch (ValidationModelException ex) {
                    JOptionPane.showMessageDialog(InventarioFrame.this, ex.getMessage());
                }
            }
        };

        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(actionEvent));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void loadEntries() {
        model.setRowCount(0);
        try {
            productoRepository.findAll().forEach(producto -> {
                try {
                    Object[] row = new Object[6];
                    row[0] = producto.getId();
                    row[1] = producto.getNombre();
                    row[2] = "";
                    row[3] = producto.getPrecioPublico();
                    row[4] = proveedorRepository.findById(producto.getIdProveedor()).getNombre();
                    row[5] = "";
                    model.addRow(row);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "ERROR BBDD");
                    System.err.println(ex.getMessage());
                } catch (ValidationModelException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            });
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "ERROR BBDD");
            System.err.println(ex.getMessage());
        } catch (ValidationModelException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        crearBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        refreshBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(1300, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crearBtn.setText("Agregar producto");
        crearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(crearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripción", "Precio", "Proveedor", "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(50);
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1300, 600));

        refreshBtn.setText("Actualizar");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });
        jPanel1.add(refreshBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INVENTARIO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 400, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-7, 0, 1360, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void crearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearBtnActionPerformed
        new CrearProductoModal(InventarioFrame.this, productoRepository, proveedorRepository).setVisible(true);
    }//GEN-LAST:event_crearBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        loadEntries();
    }//GEN-LAST:event_refreshBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
