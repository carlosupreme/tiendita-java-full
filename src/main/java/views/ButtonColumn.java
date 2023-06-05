/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

/**
 *
 * @author Raul
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class ButtonColumn extends AbstractCellEditor
        implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener {

    private final JTable tabla;
    private final Action action;
    private int mnemonic;
    private Border originalBorder;
    private Border focusBorder;

    private JButton renderButton;
    private JButton editButton;
    private Object editorValue;
    private boolean isButtonColumnEditor;

    Color hoverBackgroundColor = new Color(255, 0, 0); // Por ejemplo, rojo

    public ButtonColumn(JTable table, Action action, int columnIndex, Icon iconoBtn) {
        this.tabla = table;
        this.action = action;

        renderButton = new JButton(iconoBtn);
        editButton = new JButton(iconoBtn);

        editButton.setFocusPainted(false);
        editButton.addActionListener(this);
        originalBorder = editButton.getBorder();
        setFocusBorder(new LineBorder(Color.BLUE));
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        renderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(columnIndex).setCellRenderer(this);
        columnModel.getColumn(columnIndex).setCellEditor(this);
        table.addMouseListener(this);
    }

    public Border getFocusBorder() {
        return focusBorder;
    }

    public void setFocusBorder(Border focusBorder) {
        this.focusBorder = focusBorder;
        editButton.setBorder(focusBorder);
    }

    public int getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(int mnemonic) {
        this.mnemonic = mnemonic;
        renderButton.setMnemonic(mnemonic);
        editButton.setMnemonic(mnemonic);
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        if (value == null) {
            editButton.setText("");
            //editButton.setIcon(null);
        } else if (value instanceof Icon) {
            editButton.setText("");
            editButton.setIcon((Icon) value);
        } else {
            editButton.setText(value.toString());
            //editButton.setIcon(null);
        }

        this.editorValue = value;
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {
        return editorValue;
    }

    Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        if (hasFocus) {
            renderButton.setBorder(focusBorder);
        } else {
            renderButton.setBorder(originalBorder);
        }

        //renderButton.setText( (value == null) ? "" : value.toString() );
        if (value == null) {
            renderButton.setText("");
            //renderButton.setIcon(null);
        } else if (value instanceof Icon) {
            renderButton.setText("");
            renderButton.setIcon((Icon) value);
        } else {
            renderButton.setText(value.toString());
            //renderButton.setIcon(null);
        }

        return renderButton;
    }

    public void actionPerformed(ActionEvent e) {
        int row = tabla.convertRowIndexToModel(tabla.getEditingRow());
        fireEditingStopped();

        //  Invoke the Action
        ActionEvent event = new ActionEvent(
                tabla,
                ActionEvent.ACTION_PERFORMED,
                "" + row);
        action.actionPerformed(event);
    }

    public void mousePressed(MouseEvent e) {
        if (tabla.isEditing()
                && tabla.getCellEditor() == this) {
            isButtonColumnEditor = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isButtonColumnEditor
                && tabla.isEditing()) {
            tabla.getCellEditor().stopCellEditing();
        }

        isButtonColumnEditor = false;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        editButton.setBackground(new Color(204, 204, 204));

    }

    public void mouseExited(MouseEvent e) {
        editButton.setBackground(editButton.getBackground());
    }
}
