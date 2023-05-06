package views.tabla;

/**
 *
 * @author carlos
 */
public interface TableActionEvent {

    public void onEdit(int row);

    public void onDelete(int row);

    public void onShow(int row);
}
