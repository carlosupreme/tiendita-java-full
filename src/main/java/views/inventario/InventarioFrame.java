package views.inventario;

import exceptions.ValidationModelException;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import repositories.InventarioRepository;
import repositories.ProductoCriteria;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import services.InventarioService;
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
    private final FilterProductoModal filterProductoModal;
    private ProductoCriteria criteria;

    public InventarioFrame() {
        initComponents();
        this.productoRepository = new ProductoRepository();
        this.proveedorRepository = new ProveedorRepository();
        this.inventarioRepository = new InventarioRepository();
        this.criteria = new ProductoCriteria();
        this.filterProductoModal = new FilterProductoModal(InventarioFrame.this, new InventarioService(productoRepository, proveedorRepository, inventarioRepository), criteria);
        model = (DefaultTableModel) table.getModel();
        loadEntries();
        setTableButtons();
        quitarFiltrosBtn.setEnabled(false);
    }

    public void setCriteria(ProductoCriteria pc) {
        this.criteria = pc;
        loadEntries();
        quitarFiltrosBtn.setEnabled(true);
    }

    private void setTableButtons() {
        TableActionEvent actionEvent = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                long id = (long) model.getValueAt(row, 0);
                new EditarProductoModal(InventarioFrame.this, productoRepository, proveedorRepository, id).setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }

                long id = (long) model.getValueAt(row, 0);
                boolean confirmed = MessageHandler.showConfirmMessage("¿Estás seguro de que desea eliminar el producto con ID '" + id + "' ?", "Eliminar permanentemente");

                if (!confirmed) {
                    return;
                }

                try {
                    productoRepository.delete(id);
                    loadEntries();
                    MessageHandler.showSuccessMessage("Eliminado correctamente", null);
                } catch (SQLException ex) {
                    MessageHandler.showErrorMessage(ex.getMessage());
                }

            }

            @Override
            public void onShow(int row) {
                long id = (long) model.getValueAt(row, 0);
                try {
                    new VerProductoModal(InventarioFrame.this, productoRepository.findById(id), productoRepository, proveedorRepository).setVisible(true);
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

    public void loadEntries() {
        model.setRowCount(0);
        try {
            productoRepository.findByCriteria(criteria).forEach(producto -> {
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        quitarFiltrosBtn = new javax.swing.JButton();
        filterBtn1 = new javax.swing.JButton();
        crearBtn1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inventario");
        setIconImage(null);
        setPreferredSize(new java.awt.Dimension(1300, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(254, 254, 254));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        quitarFiltrosBtn.setBackground(new java.awt.Color(251, 113, 133));
        quitarFiltrosBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        quitarFiltrosBtn.setForeground(new java.awt.Color(255, 255, 255));
        quitarFiltrosBtn.setText("Quitar filtros");
        quitarFiltrosBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quitarFiltrosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarFiltrosBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(quitarFiltrosBtn, gridBagConstraints);

        filterBtn1.setBackground(new java.awt.Color(56, 189, 248));
        filterBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        filterBtn1.setForeground(new java.awt.Color(255, 255, 255));
        filterBtn1.setText("Filtrar");
        filterBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filterBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBtn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(filterBtn1, gridBagConstraints);

        crearBtn1.setBackground(new java.awt.Color(129, 140, 248));
        crearBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        crearBtn1.setForeground(new java.awt.Color(255, 255, 255));
        crearBtn1.setText("Agregar");
        crearBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        crearBtn1.setMaximumSize(new java.awt.Dimension(158, 27));
        crearBtn1.setMinimumSize(new java.awt.Dimension(158, 27));
        crearBtn1.setPreferredSize(new java.awt.Dimension(158, 27));
        crearBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearBtn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 10;
        jPanel4.add(crearBtn1, gridBagConstraints);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 570, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 60));

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));
        jPanel2.setLayout(new java.awt.CardLayout());

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

        jPanel2.add(jScrollPane1, "card2");

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1300, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void quitarFiltrosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarFiltrosBtnActionPerformed
        this.criteria = new ProductoCriteria();
        filterProductoModal.setCriteria(criteria);
        loadEntries();
        quitarFiltrosBtn.setEnabled(false);
    }//GEN-LAST:event_quitarFiltrosBtnActionPerformed

    private void filterBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBtn1ActionPerformed
       filterProductoModal.setVisible(true);
    }//GEN-LAST:event_filterBtn1ActionPerformed

    private void crearBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearBtn1ActionPerformed
        new CrearProductoModal(InventarioFrame.this, productoRepository, proveedorRepository).setVisible(true);
    }//GEN-LAST:event_crearBtn1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearBtn1;
    private javax.swing.JButton filterBtn1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton quitarFiltrosBtn;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
