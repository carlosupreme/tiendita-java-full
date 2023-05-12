package views.inventario;

import app.ConexionDB;
import controllers.AutenticacionController;
import exceptions.ValidationModelException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import views.tabla.TableActionCellEditor;
import views.tabla.TableActionCellRender;
import views.tabla.TableActionEvent;

@SuppressWarnings("serial")
public class InventarioFrame extends javax.swing.JFrame {

    private final AutenticacionController authController;
    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;

    private final DefaultTableModel model;

    public InventarioFrame(AutenticacionController authController) {
        initComponents();
        fullScreen();
        this.authController = authController;
        this.productoRepository = new ProductoRepository(ConexionDB.getInstance().getConnection());
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

    }

    private void loadEntries() {
        model.setRowCount(0);
        try {
            productoRepository.findAll().forEach(producto -> {
                try {
                    Object[] row = new Object[6];
                    row[0] = producto.getId();
                    row[1] = producto.getNombre();
                    row[2] = producto.getEdicion();
                    row[3] = producto.getPrecioPublico();
                    row[4] = proveedorRepository.findById(producto.getProveedorId()).getNombre();
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

    private void fullScreen() {
        setLocationRelativeTo(null);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(0, 0);
        this.setSize(dimension.width, dimension.height);
        this.setResizable(false);
        this.setTitle("Inventario");
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        crearBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        refreshBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Noto Sans CJK HK", 1, 24)); // NOI18N
        jLabel1.setText("INVENTARIO");

        crearBtn.setText("Agregar producto");
        crearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearBtnActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Edicion", "Precio", "Proveedor", "Acciones"
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

        refreshBtn.setText("Actualizar");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(refreshBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(277, 277, 277)
                        .addComponent(crearBtn)
                        .addContainerGap(375, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(31, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(crearBtn)
                    .addComponent(refreshBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearBtnActionPerformed
        CrearProductoModal crearProductoModal = new CrearProductoModal(InventarioFrame.this, productoRepository, proveedorRepository);
        crearProductoModal.setVisible(true);
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
