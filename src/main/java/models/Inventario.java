package models;

/**
 *
 * @author carlos
 */
public class Inventario {

    private long idProducto;
    private long stock;

    public Inventario(long idProducto, long stock) {
        this.idProducto = idProducto;
        this.stock = stock;
    }

    public Inventario() {
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

}
