/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.lang.reflect.Field;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Raul
 */
public class ModeloTabla<T> extends AbstractTableModel {

    protected final List<T> data;
    private final String[] nombresColumnas;

    public ModeloTabla(List<T> data, String[] columnNames) {
        this.data = data;
        this.nombresColumnas = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.size(); 
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T row = data.get(rowIndex);
        Field field;
        try {
            field = row.getClass().getDeclaredField(nombresColumnas[columnIndex]);
            field.setAccessible(true);
            return field.get(row);
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }

    @Override
    public String getColumnName(int index) {
        return nombresColumnas[index];
    }

}
