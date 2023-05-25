/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import db.SelectStatementMapper;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
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
                    JOptionPane.showMessageDialog(null, "Detalles del producto con id = "
                            + valor.toString());
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
