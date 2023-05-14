/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import app.Sesion;
import db.ConexionDB;
import db.InstruccionDML;
import db.PreparedStatementMapper;
import db.SelectStatementMapper;
import db.TransactionManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.DetallesVenta;
import models.ProductoVenta;
import models.Venta;

/**
 *
 * @author Raul
 */
public class CobrarPanel extends JPanel {

    private static final String SELECT_BY_CODIGO_BARRAS_QUERY
            = "SELECT productos.*, inventario.stock FROM productos "
            + "INNER JOIN inventario ON productos.id = inventario.id_producto "
            + "WHERE productos.codigo_barras = ?";

    private static ProductoVenta selectByCodigoBarras(String codigoDeBarras) throws SQLException {

        SelectStatementMapper<ProductoVenta> stm = new SelectStatementMapper<>();

        ProductoVenta producto = null;

        try {
            producto = stm.selectOne(ProductoVenta.class, SELECT_BY_CODIGO_BARRAS_QUERY, codigoDeBarras);
        } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchMethodException | InstantiationException | InvocationTargetException ex) {
            producto = null;
        }

        return producto;
    }

    private JTextField txtCodigoBarras;
    private JLabel lblTotal;
    private JPanel pnlProductos;
    private Map<Integer, PanelProducto> mapProductos;
    private double total;
    private JButton cobrarBtn;

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

        JPanel panelAbajo = new JPanel();
        panelAbajo.add(lblTotal);
        cobrarBtn = new JButton("Cobrar");

        panelAbajo.add(cobrarBtn);

        String country[] = {"Efectivo", "Tarjeta de crédito", "Tarjeta de débito"};
        JComboBox cb = new JComboBox(country);

        cobrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(mapProductos.values().size());

                InstruccionDML dml = new InstruccionDML() {
                    @Override
                    public void ejecutar() throws SQLException {

                        // Insertar venta
                        PreparedStatementMapper<Venta> ventaStm = new PreparedStatementMapper<>("ventas");

                        Instant fecha = Instant.now().with(ChronoField.NANO_OF_SECOND, 0);
                        /*ZoneId z = ZoneId.of("GMT-6");
                        ZonedDateTime zdt = current.atZone(z);
                        Instant fecha = zdt.toInstant();*/

                        Venta venta = new Venta(total, fecha,
                                Sesion.instance().getUsuario().getId(), "forma de pago");

                        long idVenta;
                        try {
                            long[] valoresV = ventaStm.insertar(venta);
                            idVenta = valoresV[0];
                        } catch (SQLException | IllegalAccessException ex) {
                            throw new SQLException(ex.getMessage());
                        }

                        for (PanelProducto pn : mapProductos.values()) {

                            PreparedStatementMapper<DetallesVenta> detallesVentaStm
                                    = new PreparedStatementMapper<>("detalles_venta");

                            try {
                                DetallesVenta detalleV = new DetallesVenta(idVenta, pn.producto.getId(),
                                        pn.cantidadStock, pn.producto.getPrecioPublico());

                                System.out.println(detallesVentaStm.getSqlString(detalleV));

                                detallesVentaStm.setIdGeneradoIgnorado(true);
                                detallesVentaStm.insertar(detalleV);

                                String sql = "UPDATE inventario SET stock = stock - "
                                        + pn.cantidadStock + " WHERE id_producto = "
                                        + pn.producto.getId();

                                Connection conexion = ConexionDB.getInstance().getConnection();
                                Statement statement = conexion.createStatement();

                                int filasAfectadas = statement.executeUpdate(sql);

                                if (filasAfectadas > 0) {
                                } else {
                                    throw new SQLException("Error al actualizar el stock del "
                                            + "producto con id = " + pn.producto.getId());
                                }

                            } catch (SQLException | IllegalAccessException ex) {
                                throw new SQLException(ex.getMessage());
                            }
                        }
                    }
                };

                try {
                    TransactionManager.ejecutarTransaccion(dml);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Fallo al ejecutar la transacción");
                }

                JOptionPane.showMessageDialog(null, "Venta exitosa");

                mapProductos = new HashMap<>();
                
                CobrarPanel.this.repaint();

            }
        });

        add(panelAbajo, BorderLayout.SOUTH);

        // Mapa para almacenar los paneles de productos agregados
        mapProductos = new HashMap<>();

        setVisible(true);
    }

    private class PanelProducto extends JPanel {

        private ProductoVenta producto;
        private int cantidadStock;

        public int getCantidadStock() {
            return cantidadStock;
        }

        public void setCantidadStock(int cantidadStock) {
            this.cantidadStock = cantidadStock;
        }

        public ProductoVenta getProducto() {
            return producto;
        }

        public void setProducto(ProductoVenta producto) {
            this.producto = producto;
        }
    }

    private void agregarProducto() {
        String codigoBarras = txtCodigoBarras.getText();

        // Consulta SQL para obtener el producto
        try {
            ProductoVenta productoEncontrado = selectByCodigoBarras(codigoBarras);

            if (productoEncontrado != null) {
                int id = productoEncontrado.getId();
                String nombre = productoEncontrado.getNombre();
                String codigoBarrasEncontrado = productoEncontrado.getCodigoBarras();
                double precio = productoEncontrado.getPrecioPublico();
                int stock = productoEncontrado.getStock();

                //System.out.println(id);
                // Si ya existe un panel para este producto, actualiza la cantidad
                if (mapProductos.containsKey(id)) {

                    PanelProducto pnlProducto = mapProductos.get(id);

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

                        pnlProducto.setCantidadStock(cantidad + 1);
                        //actualizarSubtotal(pnlProducto, precio);
                    } else {
                        JOptionPane.showMessageDialog(
                                this, "No hay suficiente stock para agregar otro producto");
                    }
                } else {
                    // Crea un nuevo panel para el producto
                    PanelProducto pnlProducto = new PanelProducto();

                    pnlProducto.setProducto(productoEncontrado);

                    pnlProducto.setCantidadStock(1);

                    pnlProducto.setLayout(new GridLayout(1, 7, 25, 25));

                    pnlProducto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

                    pnlProducto.add(new JLabel(nombre));

                    pnlProducto.add(new JLabel(codigoBarrasEncontrado));

                    pnlProducto.add(new JLabel("$" + precio));
                    JSpinner spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, stock, 1));
                    spnCantidad.addChangeListener(new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent e) {
                            JSpinner spnCantidad = (JSpinner) e.getSource();
                            int cantidad = (int) spnCantidad.getValue();
                            pnlProducto.setCantidadStock(cantidad);
                            actualizarSubtotal(pnlProducto, precio, stock);
                        }
                    });
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
            ex.printStackTrace();
        }
        txtCodigoBarras.setText("");
    }

    private void actualizarSubtotal(PanelProducto pnlProducto, double precio, int stock) {

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

        double subtotalPrevio = Double.parseDouble(lblSubtotal.getText().substring(1));
        total -= subtotalPrevio;

        double subtotal = precio * cantidad;

        lblSubtotal.setText("$" + String.format("%.2f", subtotal));

        sumarAlTotal(subtotal);
    }

    private void eliminarProducto(PanelProducto pnlProducto, double precio) {
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
