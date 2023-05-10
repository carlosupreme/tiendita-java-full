/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Raul
 */
public class ModeloTabla<T> extends AbstractTableModel {
    
    private final List<T> datos;
    private final String[] nombresColumnas;

    public ModeloTabla(List<T> data, String[] columnNames) {
        this.datos = data;
        this.nombresColumnas = columnNames;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T row = datos.get(rowIndex);
        try {
            return row.getClass().getField(nombresColumnas[columnIndex]).get(row);
        } catch (IllegalAccessException | IllegalArgumentException | 
                NoSuchFieldException | SecurityException e) {
            JOptionPane.showMessageDialog(null, "Error al leer campo de clase");
            return null;
        }
    }
    
    @Override
    public String getColumnName(int index) {
        return nombresColumnas[index];
    }
    
}
