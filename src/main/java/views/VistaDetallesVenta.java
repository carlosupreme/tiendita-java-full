/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import db.SelectStatementMapper;
import exceptions.ValidationModelException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.DetallesVenta;
import repositories.ProductoRepository;
import repositories.ProveedorRepository;
import views.inventario.VerProductoModal;

/**
 *
 * @author Raul
 */
public class VistaDetallesVenta extends JDialog {

    private int idVenta;

    public VistaDetallesVenta(int idVenta) {
        SelectStatementMapper<DetallesVenta> ventaMap
                = new SelectStatementMapper<>("detalles_venta");

        ventaMap.setSql("SELECT detalles_venta.* FROM ventas INNER JOIN "
                + "detalles_venta ON ventas.id = detalles_venta.id_venta "
                + "WHERE detalles_venta.id_venta = " + idVenta);

        try {
            String[][] datos = ventaMap.selectAllAsArray(DetallesVenta.class, new String[]{});

            String[] columnasTabla = {"ID de venta", "ID del producto", "Cantidad", "Precio Unitario"};

            DefaultTableModel modelo = new DefaultTableModel(datos, columnasTabla) {
                boolean[] canEdit = new boolean[]{
                    false, true, false, false
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

            Action detallesProductoBtn;
            detallesProductoBtn = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable) e.getSource();
                    int modelRow = Integer.parseInt(e.getActionCommand());
                    Object valor = ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 1);
                    String idTexto = valor.toString();

                    long id = Long.valueOf(idTexto);
                    ProductoRepository pr = new ProductoRepository();
                    try {
                        new VerProductoModal(null, pr.findById(id), pr, new ProveedorRepository()).setVisible(true);
                    } catch (SQLException | ValidationModelException ex) {
                        MessageHandler.showErrorMessage(ex.getMessage());
                    }

                }
            };

            new ButtonColumn(tabla, detallesProductoBtn, 1, new ImageIcon(getClass().getResource("/info_icon.png")));

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(800, 500);
            setResizable(false);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JScrollPane scroll = new JScrollPane(tabla);
            panel.add(scroll, BorderLayout.CENTER);
            add(panel);

        } catch (IllegalAccessException | IllegalArgumentException
                | InstantiationException | NoSuchMethodException
                | InvocationTargetException | SQLException ex) {
            MessageHandler.showErrorMessage(ex.getMessage());
        }

    }

}
