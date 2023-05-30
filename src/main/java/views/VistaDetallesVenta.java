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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

            DefaultTableModel modelo = new DefaultTableModel(datos, columnasTabla);
            modelo.setDataVector(datos, columnasTabla);

            JTable tabla = new JTable(modelo);

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
                        ErrorHandler.showErrorMessage(ex.getMessage());
                    }

                }
            };

            new ButtonColumn(tabla, detallesProductoBtn, 1,
                    new ImageIcon(getClass().getResource("/info_icon.png")));

            tabla.setRowHeight(30);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(800, 500);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JScrollPane scroll = new JScrollPane(tabla);
            panel.add(scroll, BorderLayout.CENTER);
            add(panel);

        } catch (IllegalAccessException | IllegalArgumentException
                | InstantiationException | NoSuchMethodException
                | InvocationTargetException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

}
