package views.inventario;

import app.ConexionDB;
import controllers.AutenticacionController;
import helpers.Confirmation;
import helpers.ConfirmationModal;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import repositories.ProductoRepository;

public class InventarioFrame extends javax.swing.JFrame {

    private AutenticacionController authController;
    private ProductoRepository productoRepository;

    public InventarioFrame(AutenticacionController authController) {
        initComponents();
        fullScreen();
        InventarioFrame frame = this;

        this.authController = authController;
        this.productoRepository = new ProductoRepository(ConexionDB.getInstance().getConnection());

        TableActionEvent actionEvent = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("edit: " + row);
            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                ConfirmationModal modal = new ConfirmationModal(frame, "¿Estás seguro de que desea eliminar la fila #" + row + "?", new Confirmation() {
                    @Override
                    public void onConfirm(JDialog component, ActionEvent evt) {
                        component.dispose();
                        model.removeRow(row);
                    }

                    @Override
                    public void onCancel(JDialog component, ActionEvent evt) {
                        component.dispose();
                    }
                });
                modal.setVisible(true);

            }

            @Override
            public void onShow(int row) {
                System.out.println("show: " + row);
            }
        };

        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(actionEvent));

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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Precio", "Proveedor", "Acciones"
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(277, 277, 277)
                        .addComponent(crearBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(crearBtn))
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
        CrearProductoModal crearProductoModal = new CrearProductoModal(this, productoRepository);
        crearProductoModal.setVisible(true);
    }//GEN-LAST:event_crearBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
