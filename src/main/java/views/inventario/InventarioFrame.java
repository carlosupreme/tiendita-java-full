package views.inventario;

import exceptions.ValidationModelException;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import repositories.InventarioRepository;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import views.MessageHandler;
import views.tabla.TableActionCellEditor;
import views.tabla.TableActionCellRender;
import views.tabla.TableActionEvent;

@SuppressWarnings("serial")
public final class InventarioFrame extends javax.swing.JFrame {

    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;
    private final InventarioRepository inventarioRepository;
    private final DefaultTableModel model;

    public InventarioFrame() {
        initComponents();
        this.productoRepository = new ProductoRepository();
        this.proveedorRepository = new ProveedorRepository();
        this.inventarioRepository = new InventarioRepository();
        model = (DefaultTableModel) table.getModel();
        loadEntries(false);
        setTableButtons();

        showDeleted.addItemListener((ItemEvent e) -> {
            loadEntries(e.getStateChange() == ItemEvent.SELECTED);
        });
    }

    private void setTableButtons() {
        TableActionEvent actionEvent = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                long id = (long) model.getValueAt(row, 0);
                new EditarProductoModal(InventarioFrame.this, productoRepository, proveedorRepository, id, showDeleted.isSelected()).setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                long id = (long) model.getValueAt(row, 0);
                int option = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que desea eliminar el producto con ID '" + id + "' ?",
                        "Eliminar permanentemente",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (option != JOptionPane.YES_OPTION) {
                    return;
                }

                try {
                    productoRepository.delete(id);
                    loadEntries(showDeleted.isSelected());
                    JOptionPane.showMessageDialog(null, "Eliminado correctamente");
                } catch (SQLException ex) {
                    MessageHandler.showErrorMessage(ex.getMessage());
                }

            }

            @Override
            public void onShow(int row) {
                long id = (long) model.getValueAt(row, 0);
                try {
                    new VerProductoModal(InventarioFrame.this, productoRepository.findById(id), proveedorRepository).setVisible(true);
                } catch (SQLException | ValidationModelException ex) {
                    MessageHandler.showErrorMessage(ex.getMessage());
                }
            }
        };

        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(actionEvent));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    public void loadEntries(boolean showDeleted) {
        model.setRowCount(0);

        //para la busqueda
        //ProductoCriteria pc = new ProductoCriteria();
        //pc.nombre = "arroz";
        //pc.categoria = "perros";
        try {
            productoRepository.findAll(showDeleted).forEach(producto -> {
                Object[] row = new Object[6];
                row[0] = producto.getId();
                row[1] = producto.getNombre();
                row[2] = "$ " + producto.getPrecioPublico();
                row[5] = "";
                try {
                    row[3] = inventarioRepository.getProductStock(producto.getId());
                    row[4] = proveedorRepository.findById(producto.getIdProveedor()).getNombre();
                } catch (Exception ex) {
                    MessageHandler.showErrorMessage(ex.getMessage());
                }
                model.addRow(row);
            });
        } catch (Exception ex) {
            MessageHandler.showErrorMessage(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        crearBtn = new javax.swing.JButton();
        showDeleted = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(1300, 720));
        jPanel1.setLayout(new java.awt.CardLayout());

        table.setAutoCreateRowSorter(true);
        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Precio", "Cantidad", "Proveedor", "Acciones"
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
        table.setShowGrid(true);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(10);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(400);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(50);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(20);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(200);
            table.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.add(jScrollPane1, "card2");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1300, 580));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INVENTARIO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 50));

        crearBtn.setBackground(new java.awt.Color(129, 140, 248));
        crearBtn.setFont(new java.awt.Font("Nimbus Sans", 1, 14)); // NOI18N
        crearBtn.setForeground(new java.awt.Color(255, 255, 255));
        crearBtn.setText("Agregar producto");
        crearBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        crearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearBtnActionPerformed(evt);
            }
        });
        jPanel2.add(crearBtn);

        showDeleted.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        showDeleted.setText("Mostrar eliminados");
        jPanel2.add(showDeleted);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 60, 400, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void crearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearBtnActionPerformed
        new CrearProductoModal(InventarioFrame.this, productoRepository, proveedorRepository, showDeleted.isSelected()).setVisible(true);
    }//GEN-LAST:event_crearBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox showDeleted;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
