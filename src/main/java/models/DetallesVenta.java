/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Raul
 */
public class DetallesVenta {
    
    private long idVenta;
    private long idProducto;
    private int cantidad;
    private double precioUnitario;

    public DetallesVenta() {
    }

    public DetallesVenta(long id_venta, long id_producto, int cantidad, double precio_unitario) {
        this.idVenta = id_venta;
        this.idProducto = id_producto;
        this.cantidad = cantidad;
        this.precioUnitario = precio_unitario;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }    
    
}
