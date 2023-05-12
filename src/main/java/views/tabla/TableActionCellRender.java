package views.tabla;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author carlos
 */
public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        PanelAction panelAction = new PanelAction();

        if (!isSelected && row % 2 == 0) {
            panelAction.setBackground(Color.WHITE);
        } else {
            panelAction.setBackground(comp.getBackground());
        }

        return panelAction;
    }

}
