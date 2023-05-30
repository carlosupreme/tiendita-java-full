package views.inventario;

import exceptions.ValidationModelException;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import repositories.InventarioRepository;
import repositories.ProductoCriteria;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import services.InventarioService;
import views.ErrorHandler;
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
        loadEntries(false);
        setTableButtons();

        showDeleted.addItemListener((ItemEvent e) -> {
            loadEntries(e.getStateChange() == ItemEvent.SELECTED);
        });

        quitarFiltrosBtn.setVisible(false);
    }

    public void setCriteria(ProductoCriteria pc) {
        this.criteria = pc;
        loadEntries(showDeleted.isSelected());
        filterByLbl.setText("Filtro aplicados");
        quitarFiltrosBtn.setVisible(true);
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
                    ErrorHandler.showErrorMessage(ex.getMessage());
                }

            }

            @Override
            public void onShow(int row) {
                long id = (long) model.getValueAt(row, 0);
                try {
                    new VerProductoModal(InventarioFrame.this, productoRepository.findById(id), productoRepository, proveedorRepository).setVisible(true);
                } catch (SQLException | ValidationModelException ex) {
                    ErrorHandler.showErrorMessage(ex.getMessage());
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
        try {
            productoRepository.findByCriteria(showDeleted, criteria).forEach(producto -> {
                Object[] row = new Object[6];
                row[0] = producto.getId();
                row[1] = producto.getNombre();
                row[2] = "$ " + producto.getPrecioPublico();
                row[5] = "";
                try {
                    row[3] = inventarioRepository.getProductStock(producto.getId());
                    row[4] = proveedorRepository.findById(producto.getIdProveedor()).getNombre();
                } catch (ValidationModelException | SQLException ex) {
                    ErrorHandler.showErrorMessage(ex.getMessage());
                }
                model.addRow(row);
            });
        } catch (ValidationModelException | SQLException ex) {
            ErrorHandler.showErrorMessage(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        crearBtn = new javax.swing.JButton();
        showDeleted = new javax.swing.JCheckBox();
        filterBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        filterByLbl = new javax.swing.JLabel();
        quitarFiltrosBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inventario");
        setIconImage(null);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(1300, 720));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 135, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 1300, 590));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INVENTARIO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 50));

        jPanel2.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 1, 2);
        jPanel2.add(crearBtn, gridBagConstraints);

        showDeleted.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        showDeleted.setText("Mostrar eliminados");
        showDeleted.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(showDeleted, new java.awt.GridBagConstraints());

        filterBtn.setBackground(new java.awt.Color(56, 189, 248));
        filterBtn.setFont(new java.awt.Font("Nimbus Sans", 1, 14)); // NOI18N
        filterBtn.setForeground(new java.awt.Color(255, 255, 255));
        filterBtn.setText("Filtrar");
        filterBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(filterBtn, gridBagConstraints);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 530, 50));

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5);
        flowLayout1.setAlignOnBaseline(true);
        jPanel3.setLayout(flowLayout1);

        filterByLbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        filterByLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterByLbl.setText(" ");
        jPanel3.add(filterByLbl);

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
        jPanel3.add(quitarFiltrosBtn);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 390, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void crearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearBtnActionPerformed
        new CrearProductoModal(InventarioFrame.this, productoRepository, proveedorRepository, showDeleted.isSelected()).setVisible(true);
    }//GEN-LAST:event_crearBtnActionPerformed

    private void filterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBtnActionPerformed
        filterProductoModal.setVisible(true);
    }//GEN-LAST:event_filterBtnActionPerformed

    private void quitarFiltrosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarFiltrosBtnActionPerformed
        this.criteria = new ProductoCriteria();
        filterProductoModal.setCriteria(criteria);
        loadEntries(showDeleted.isSelected());
        quitarFiltrosBtn.setVisible(false);
        filterByLbl.setText("");
    }//GEN-LAST:event_quitarFiltrosBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearBtn;
    private javax.swing.JButton filterBtn;
    private javax.swing.JLabel filterByLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton quitarFiltrosBtn;
    private javax.swing.JCheckBox showDeleted;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
