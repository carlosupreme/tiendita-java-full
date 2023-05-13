/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import db.ConexionDB;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Raul
 */
public class CobrarPanel extends JPanel {

    private class ProductoVenta {

        private int id;
        private String nombre;
        private String codigoDeBarras;
        private double precio;
        private int cantidadEnStock;

        public ProductoVenta(int id, String nombre, String codigoDeBarras, double precio, int cantidadEnStock) {
            this.id = id;
            this.nombre = nombre;
            this.codigoDeBarras = codigoDeBarras;
            this.precio = precio;
            this.cantidadEnStock = cantidadEnStock;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCodigoDeBarras() {
            return codigoDeBarras;
        }

        public double getPrecio() {
            return precio;
        }

        public int getStock() {
            return cantidadEnStock;
        }
    }

    private class ProductoDAO {

        private static final String SELECT_BY_ID_QUERY
                = "SELECT productos.id, productos.nombre, productos.codigo_barras, "
                + "productos.precio_publico, inventario.stock FROM productos "
                + "INNER JOIN inventario ON productos.id = inventario.id_producto "
                + "WHERE productos.codigo_barras = ?";

        private Connection connection = ConexionDB.getInstance().getConnection();

        public ProductoVenta selectById(String codigoDeBarras) throws SQLException {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setString(1, codigoDeBarras);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String codBarras = resultSet.getString("codigo_barras");
                double precio = resultSet.getDouble("precio_publico");
                int cantidadEnStock = resultSet.getInt("stock");

                return new ProductoVenta(id, nombre, codBarras, precio, cantidadEnStock);
            } else {
                return null;
            }
        }
    }

    private final Connection connection = ConexionDB.getInstance().getConnection();

    private final JTextField txtCodigoBarras;
    private final JLabel lblTotal;
    private final JPanel pnlProductos;
    private final Map<Integer, JPanel> mapProductos;
    private double total;

    public CobrarPanel() {

        setLayout(new BorderLayout());

        setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        // Panel de búsqueda de productos
        JPanel pnlBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCodigoBarras = new JTextField(20);
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarProducto());
        pnlBusqueda.add(new JLabel("Código de barras:"));
        pnlBusqueda.add(txtCodigoBarras);
        pnlBusqueda.add(btnAgregar);
        add(pnlBusqueda, BorderLayout.NORTH);

        //Crear columnas
        JPanel pnlColumnas = new JPanel(new FlowLayout(FlowLayout.LEFT));

        pnlColumnas.setLayout(new GridLayout(1, 7, 25, 25));
        pnlColumnas.add(new JLabel("Nombre"));
        pnlColumnas.add(new JLabel("Código de barras"));
        pnlColumnas.add(new JLabel("Precio"));
        pnlColumnas.add(new JLabel("Cantidad"));
        pnlColumnas.add(new JLabel("Subtotal"));
        pnlColumnas.add(new JLabel("Acción"));

        pnlColumnas.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        // Panel de productos agregados
        pnlProductos = new JPanel();
        pnlProductos.setLayout(new BoxLayout(pnlProductos, BoxLayout.Y_AXIS));

        add(new JScrollPane(pnlProductos), BorderLayout.CENTER);

        pnlProductos.add(pnlColumnas);
        pnlColumnas.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, pnlColumnas.getMinimumSize().height));

        // Label de total
        lblTotal = new JLabel("Total: $0.00");
        add(lblTotal, BorderLayout.SOUTH);

        // Mapa para almacenar los paneles de productos agregados
        mapProductos = new HashMap<>();

        setVisible(true);
    }

    private void agregarProducto() {
        String codigoBarras = txtCodigoBarras.getText();

        // Consulta SQL para obtener el producto
        final String query
                = "SELECT productos.id, productos.nombre, productos.codigo_barras, "
                + "productos.precio_publico, inventario.stock FROM productos "
                + "INNER JOIN inventario ON productos.id = inventario.id_producto "
                + "WHERE productos.codigo_barras = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, codigoBarras);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String codigoBarrasEncontrado = rs.getString("codigo_barras");
                double precio = rs.getDouble("precio_publico");
                int stock = rs.getInt("stock");

                // Si ya existe un panel para este producto, actualiza la cantidad
                if (mapProductos.containsKey(id)) {

                    JPanel pnlProducto = mapProductos.get(id);

                    /*int i = 0;
                    for (Component componente : pnlProducto.getComponents()) {
                        Component componenteIndice = pnlProducto.getComponent(i);
                        System.out.print("i = " + i + ", " + componente.getClass() + ", " + componenteIndice.getClass()
                                + ", " + componente.equals(componenteIndice));
                        if(componente instanceof JLabel) {
                            JLabel label = (JLabel) componente;
                            System.out.print(", label text = " + label.getText());
                        } 
                        System.out.println("");
                        i++;
                    }*/
                    JSpinner spnCantidad = (JSpinner) pnlProducto.getComponent(3);
                    int cantidad = (int) spnCantidad.getValue();
                    //System.out.println("cantidad = " + cantidad + ", stock = " + stock
                    //+ ", " + cantidad + " < " + stock + " = " + (cantidad < stock));
                    if (cantidad < stock) {
                        spnCantidad.setValue(cantidad + 1);
                        //actualizarSubtotal(pnlProducto, precio);
                    } else {
                        JOptionPane.showMessageDialog(this, "No hay suficiente stock para agregar otro producto");
                    }
                } else {
                    // Crea un nuevo panel para el producto
                    JPanel pnlProducto = new JPanel(new FlowLayout(FlowLayout.LEFT));

                    pnlProducto.setLayout(new GridLayout(1, 7, 25, 25));

                    pnlProducto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

                    pnlProducto.add(new JLabel(nombre));

                    pnlProducto.add(new JLabel(codigoBarrasEncontrado));

                    pnlProducto.add(new JLabel("$" + precio));
                    JSpinner spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, stock, 1));
                    spnCantidad.addChangeListener(e -> actualizarSubtotal(pnlProducto, precio, stock));
                    pnlProducto.add(spnCantidad);

                    JLabel lblSubtotal = new JLabel("$" + precio);
                    pnlProducto.add(lblSubtotal);
                    JButton btnEliminar = new JButton("Eliminar");
                    btnEliminar.addActionListener(e -> eliminarProducto(pnlProducto, precio));
                    pnlProducto.add(btnEliminar);

                    //pnlProducto.add(Box.createHorizontalStrut(20));
                    pnlProductos.add(pnlProducto);
                    mapProductos.put(id, pnlProducto);
                    sumarAlTotal(precio);

                    pnlProducto.setMaximumSize(new Dimension(Integer.MAX_VALUE, pnlProducto.getMinimumSize().height));
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún producto con ese código de barras");
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar el producto en la base de datos");
        }
        txtCodigoBarras.setText("");
    }

    private void actualizarSubtotal(JPanel pnlProducto, double precio, int stock) {

        JSpinner spnCantidad = (JSpinner) pnlProducto.getComponent(3);
        int cantidad = (int) spnCantidad.getValue();

        if (cantidad == stock) {
            Runnable myThread = () -> {
                Thread.currentThread().setName("myThread");
                JOptionPane.showMessageDialog(null, "Se ha alcanzado el límite del stock");

            };
            Thread run = new Thread(myThread);
            run.start();
        }

        JLabel lblSubtotal = (JLabel) pnlProducto.getComponent(4);

        System.out.println(cantidad);

        double subtotalPrevio = Double.parseDouble(lblSubtotal.getText().substring(1));
        total -= subtotalPrevio;

        double subtotal = precio * cantidad;

        lblSubtotal.setText("$" + String.format("%.2f", subtotal));

        sumarAlTotal(subtotal);
    }

    private void eliminarProducto(JPanel pnlProducto, double precio) {
        pnlProductos.remove(pnlProducto);
        pnlProductos.revalidate();
        pnlProductos.repaint();
        int id = mapProductos.entrySet().stream()
                .filter(e -> e.getValue() == pnlProducto)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(-1);
        mapProductos.remove(id);
        JSpinner spnCantidad = (JSpinner) pnlProducto.getComponent(3);
        double subtotal = (int) spnCantidad.getValue() * precio;
        sumarAlTotal(-subtotal);
    }

    private void sumarAlTotal(double cantidad) {
        total += cantidad;
        lblTotal.setText("Total: $" + String.format("%.2f", total));
    }

}
