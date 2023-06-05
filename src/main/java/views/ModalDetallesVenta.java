package views;

import db.SelectStatementMapper;
import exceptions.ValidationModelException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.DetallesVenta;
import models.Producto;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import views.inventario.VerProductoModal;

/**
 *
 * @author Carlos
 */
@SuppressWarnings("serial")
public class ModalDetallesVenta extends javax.swing.JDialog {

    private long idVenta;
    private final HashMap<String, Long> productos;

    public ModalDetallesVenta(long idVenta, String total, String fecha) {
        super((Frame) null, true);
        initComponents();
        productos = new HashMap<>();

        totalLbl.setText("$ " + total);
        ProductoRepository productoRepository = new ProductoRepository();

        try {

            SelectStatementMapper<DetallesVenta> ventaMap
                    = new SelectStatementMapper<>("detalles_venta");

            ventaMap.setSql("SELECT detalles_venta.* FROM ventas INNER JOIN "
                    + "detalles_venta ON ventas.id = detalles_venta.id_venta "
                    + "WHERE detalles_venta.id_venta = " + idVenta);

            List<DetallesVenta> data = ventaMap.selectAll(DetallesVenta.class);
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            data.forEach(venta -> {
                Object[] row = new Object[4];
                try {
                    Producto p = productoRepository.findById(venta.getIdProducto());
                    row[0] = p.getNombre();
                    productos.put((String) row[0], p.getId());
                } catch (SQLException | ValidationModelException ex) {
                    MessageHandler.showErrorMessage(ex.getMessage());
                }
                row[1] = "";
                row[2] = venta.getCantidad();
                row[3] = "$ " + venta.getPrecioUnitario();
                model.addRow(row);
            });

            table.setModel(model);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            table.setDefaultRenderer(Object.class, centerRenderer);

            JTableHeader header = table.getTableHeader();
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(204, 204, 204)));
                    setHorizontalAlignment(SwingConstants.LEFT); // Alinear el texto al centro
                    setOpaque(true); // Hacer que las celdas sean opacas para que el color de fondo sea visible

                    return component;
                }
            };
            header.setDefaultRenderer(headerRenderer);
            header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));

            Action detallesProductoBtn;
            detallesProductoBtn = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable) e.getSource();
                    int modelRow = Integer.parseInt(e.getActionCommand());
                    Object valor = table.getModel().getValueAt(modelRow, 0);

                    long id = productos.get(String.valueOf(valor));

                    try {
                        new VerProductoModal(null, productoRepository.findById(id), productoRepository, new ProveedorRepository()).setVisible(true);
                    } catch (SQLException | ValidationModelException ex) {
                        MessageHandler.showErrorMessage(ex.getMessage());
                    }

                }
            };

            new ButtonColumn(table, detallesProductoBtn, 1, new ImageIcon(getClass().getResource("/info_icon.png")));

        } catch (SQLException ex) {
            Logger.getLogger(ModalDetallesVenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ModalDetallesVenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ModalDetallesVenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ModalDetallesVenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ModalDetallesVenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ModalDetallesVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        totalLbl1 = new javax.swing.JTextField();
        totalLbl = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.CardLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        table.setBorder(new javax.swing.border.MatteBorder(null));
        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Info", "Cantidad", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setAutoscrolls(false);
        table.setGridColor(new java.awt.Color(204, 204, 204));
        table.setRowHeight(35);
        table.setRowSelectionAllowed(false);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(350);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(32);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel2.add(jScrollPane1, "card2");

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 370));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        totalLbl1.setEditable(false);
        totalLbl1.setBackground(new java.awt.Color(255, 255, 255));
        totalLbl1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalLbl1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalLbl1.setText("Total: ");
        totalLbl1.setBorder(null);
        totalLbl1.setFocusable(false);
        totalLbl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalLbl1ActionPerformed(evt);
            }
        });

        totalLbl.setEditable(false);
        totalLbl.setBackground(new java.awt.Color(255, 255, 255));
        totalLbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalLbl.setBorder(null);
        totalLbl.setFocusable(false);
        totalLbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalLblActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(396, 396, 396)
                .addComponent(totalLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(totalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 50, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {totalLbl, totalLbl1});

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 600, 90));

        setSize(new java.awt.Dimension(615, 472));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void totalLblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalLblActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalLblActionPerformed

    private void totalLbl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalLbl1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalLbl1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField totalLbl;
    private javax.swing.JTextField totalLbl1;
    // End of variables declaration//GEN-END:variables
}
