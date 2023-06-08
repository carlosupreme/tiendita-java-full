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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
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
            + "WHERE productos.activo = 1 AND productos.codigo_barras = ?";

    private static ProductoVenta selectByCodigoBarras(String codigoDeBarras) throws SQLException {

        SelectStatementMapper<ProductoVenta> stm = new SelectStatementMapper<>();
        stm.setSql(SELECT_BY_CODIGO_BARRAS_QUERY);

        ProductoVenta producto;

        try {
            producto = stm.findById(ProductoVenta.class, codigoDeBarras);
        } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchMethodException | InstantiationException | InvocationTargetException ex) {
            producto = null;
            MessageHandler.showErrorMessage(ex.getMessage());
        }

        return producto;
    }

    private void crearFilaProductos(JPanel pnlFila, ArrayList<JComponent> componentes) {

        GridBagLayout layout = new GridBagLayout();
        pnlFila.setLayout(layout);
        pnlFila.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        // Definición de las restricciones para cada celda
        GridBagConstraints constraints = new GridBagConstraints();

        // Restricciones para la primera columna
        constraints.gridx = 0;  // Columna 1
        constraints.gridy = 0;  // Fila 1
        constraints.gridwidth = 1;  // Ocupa una sola columna
        constraints.gridheight = 1; // Ocupa una sola fila 
        constraints.ipadx = 0; // Sin relleno adicional
        constraints.weightx = 0.0;  // No se ajusta horizontalmente
        constraints.fill = GridBagConstraints.HORIZONTAL;  // No se estira
        constraints.anchor = GridBagConstraints.WEST;  // Alineado a la izquierda
        constraints.insets = new Insets(4, 0, 4, 0);

        // Inserción de primera columna
        layout.setConstraints(componentes.get(0), constraints);
        pnlFila.add(componentes.get(0));

        // Restricciones para las demás columnas
        constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
        constraints.anchor = GridBagConstraints.WEST; // Alineado a la izquierda
        constraints.insets = new Insets(4, 0, 4, 0);
        constraints.weightx = 1.0;  // No se ajusta horizontalmente

        //constraints.weightx = 1.0 / (componentes.size() - 1);
        for (int i = 1; i < componentes.size(); i++) {

            constraints.gridx = i;  // Columna i
            layout.setConstraints(componentes.get(i), constraints);

            pnlFila.add(componentes.get(i));
        }

    }

    private JTextField txtCodigoBarras;
    private JLabel lblTotal;
    private JPanel pnlProductos;
    private Map<Long, PanelProducto> mapProductos;
    private double total;
    private JButton cobrarBtn;

    private ArrayList<JComponent> getComponentesTitutlo() {

        ArrayList<JComponent> lista = new ArrayList<>();

        JTextField labelNombre = new JTextField("Nombre");
        labelNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        labelNombre.setEditable(false);
        labelNombre.setPreferredSize(new Dimension(400, labelNombre.getPreferredSize().height));
        lista.add(labelNombre);

        JTextField labelCodigoBarras = new JTextField("Código de barras");
        labelCodigoBarras.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        labelCodigoBarras.setEditable(false);
        labelCodigoBarras.setPreferredSize(new Dimension(100, labelCodigoBarras.getPreferredSize().height));
        lista.add(labelCodigoBarras);
        //lista.add(new JLabel("Código de barras"));

        JTextField labelPrecio = new JTextField("Precio");
        labelPrecio.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        labelPrecio.setEditable(false);
        labelPrecio.setPreferredSize(new Dimension(50, labelPrecio.getPreferredSize().height));
        lista.add(labelPrecio);
        //lista.add(new JLabel("Precio"));

        JTextField labelCantidad = new JTextField("Cantidad");
        labelCantidad.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        labelCantidad.setEditable(false);
        labelCantidad.setPreferredSize(new Dimension(100, labelCantidad.getPreferredSize().height));
        lista.add(labelCantidad);
        //lista.add(new JLabel("Cantidad"));

        JTextField labelSubtotal = new JTextField("Subtotal");
        labelSubtotal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 30, 0, 0),
                BorderFactory.createEmptyBorder())
        );
        labelSubtotal.setEditable(false);
        labelSubtotal.setPreferredSize(new Dimension(150, labelSubtotal.getPreferredSize().height));
        lista.add(labelSubtotal);
        //lista.add(new JLabel("Subtotal"));

        lista.add(new JLabel("Eliminar"));

        return lista;

    }

    public CobrarPanel() {

        setBackground(Color.decode("#FEFEFE"));
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        // Panel de búsqueda de productos
        JPanel pnlBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBusqueda.setBackground(Color.decode("#FEFEFE"));
        txtCodigoBarras = new JTextField(20);
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(e -> agregarProducto());
        pnlBusqueda.add(new JLabel("Código de barras:"));
        pnlBusqueda.add(txtCodigoBarras);
        pnlBusqueda.add(btnAgregar);
        add(pnlBusqueda, BorderLayout.NORTH);

        // Panel de productos agregados
        pnlProductos = new JPanel();
        pnlProductos.setBackground(Color.decode("#FFFFFF"));
        pnlProductos.setLayout(new BoxLayout(pnlProductos, BoxLayout.Y_AXIS));

        add(new JScrollPane(pnlProductos), BorderLayout.CENTER);

        // Label de total
        lblTotal = new JLabel("Total: $0.00");

        JPanel panelAbajo = new JPanel();
        panelAbajo.setBackground(Color.decode("#FEFEFE"));
        panelAbajo.add(lblTotal);
        cobrarBtn = new JButton("Cobrar");
        cobrarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelAbajo.add(cobrarBtn);

        String country[] = {"Efectivo", "Tarjeta de crédito", "Tarjeta de débito"};
        JComboBox cb = new JComboBox(country);
        cb.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelAbajo.add(cb);

        cobrarBtn.addActionListener((ActionEvent e) -> {

            if (mapProductos.isEmpty()) {
                MessageHandler.showErrorMessage("No hay productos para cobrar");
                return;
            }

            InstruccionDML dml = () -> {
                // Insertar venta
                PreparedStatementMapper<Venta> ventaStm
                        = new PreparedStatementMapper<>("ventas");

                Instant fecha = Instant.now().with(ChronoField.NANO_OF_SECOND, 0);

                Venta venta = new Venta(total, fecha,
                        Sesion.instance().getUsuario().getId(), cb.getSelectedItem().toString());

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
            };
            try {
                TransactionManager.ejecutarTransaccion(dml);

                MessageHandler.showSuccessMessage("Se ha realizado la venta de forma exitosa", null);
                mapProductos = new HashMap<>();
                pnlProductos.removeAll();

                JPanel pnlColumnas = new JPanel();
                crearFilaProductos(pnlColumnas, getComponentesTitutlo());

                total = 0;
                lblTotal.setText("Total: $" + String.format("%.2f", total));

                pnlProductos.add(pnlColumnas);
                pnlColumnas.setMaximumSize(new Dimension(
                        Integer.MAX_VALUE, pnlColumnas.getMinimumSize().height));

                CobrarPanel.this.revalidate();
                CobrarPanel.this.repaint();

            } catch (SQLException ex) {
                MessageHandler.showErrorMessage("Fallo al ejecutar la transacción");
            }

        });

        add(panelAbajo, BorderLayout.SOUTH);

        JPanel pnlColumnas = new JPanel();
        crearFilaProductos(pnlColumnas, getComponentesTitutlo());

        pnlProductos.add(pnlColumnas);
        pnlColumnas.setMaximumSize(new Dimension(
                Integer.MAX_VALUE, pnlColumnas.getMinimumSize().height));

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
        String codigoBarras = txtCodigoBarras.getText().trim();

        // Consulta SQL para obtener el producto
        try {
            ProductoVenta productoEncontrado = selectByCodigoBarras(codigoBarras);

            if (productoEncontrado != null) {
                long id = productoEncontrado.getId();
                String nombre = productoEncontrado.getNombre();
                String codigoBarrasEncontrado = productoEncontrado.getCodigoBarras();
                double precio = productoEncontrado.getPrecioPublico();
                long stock = productoEncontrado.getStock();

                if (stock == 0) {
                    MessageHandler.showSuccessMessage("No hay más cantidad de dicho productos disponible en el inventario", "Inventario vacío");

                } else {

                    // Si ya existe un panel para este producto, actualiza la cantidad
                    if (mapProductos.containsKey(id)) {

                        PanelProducto pnlProducto = mapProductos.get(id);

                        JSpinner spnCantidad = (JSpinner) pnlProducto.getComponent(3);
                        int cantidad = Double.valueOf(spnCantidad.getValue().toString()).intValue();

                        if (cantidad < stock) {
                            spnCantidad.setValue(cantidad + 1);

                            pnlProducto.setCantidadStock(cantidad + 1);
                        } else {
                            MessageHandler.showSuccessMessage("No hay suficiente stock para agregar otro producto para cobrar", "Inventario vacío");
                        }
                    } else {

                        // Crea un nuevo panel para el producto
                        PanelProducto pnlProducto = new PanelProducto();
                        pnlProducto.setBackground(Color.WHITE);
                        pnlProducto.setProducto(productoEncontrado);
                        pnlProducto.setCantidadStock(1);

                        ArrayList<JComponent> componentes = new ArrayList<>();

                        JTextField textNombre = new JTextField(nombre);
                        textNombre.setBackground(Color.WHITE);
                        textNombre.setBorder(BorderFactory.createEmptyBorder());
                        textNombre.setEditable(false);
                        textNombre.setPreferredSize(new Dimension(400, textNombre.getPreferredSize().height));
                        componentes.add(textNombre);

                        JTextField textCodigoBarras = new JTextField(codigoBarrasEncontrado);
                        textCodigoBarras.setBackground(Color.WHITE);
                        textCodigoBarras.setBorder(BorderFactory.createEmptyBorder());
                        textCodigoBarras.setEditable(false);
                        textCodigoBarras.setPreferredSize(new Dimension(100, textCodigoBarras.getPreferredSize().height));
                        //componentes.add(new JLabel(codigoBarrasEncontrado));
                        componentes.add(textCodigoBarras);

                        JTextField textPrecio = new JTextField("$" + String.format("%.2f", precio));
                        textPrecio.setBackground(Color.WHITE);
                        textPrecio.setBorder(BorderFactory.createEmptyBorder());
                        textPrecio.setEditable(false);
                        textPrecio.setPreferredSize(new Dimension(50, textPrecio.getPreferredSize().height));
                        //componentes.add(new JLabel("$" + String.format("%.2f", precio)));
                        componentes.add(textPrecio);

                        JSpinner spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, stock, 1));
                        spnCantidad.setPreferredSize(new Dimension(100, spnCantidad.getPreferredSize().height));

                        spnCantidad.addChangeListener((ChangeEvent e) -> {
                            JSpinner spnCantidad1 = (JSpinner) e.getSource();
                            Double cantidad = Double.valueOf(spnCantidad1.getValue().toString());
                            pnlProducto.setCantidadStock(cantidad.intValue());
                            actualizarSubtotal(pnlProducto, precio, stock);
                        });

                        componentes.add(spnCantidad);

                        JTextField textSubtotal = new JTextField("$" + String.format("%.2f", precio));
                        textSubtotal.setBackground(Color.WHITE);
                        textSubtotal.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createEmptyBorder(0, 30, 0, 0),
                                BorderFactory.createEmptyBorder())
                        );
                        textSubtotal.setEditable(false);
                        textSubtotal.setPreferredSize(new Dimension(150, textSubtotal.getPreferredSize().height));
                        //componentes.add(new JLabel("$" + String.format("%.2f", precio)));
                        componentes.add(textSubtotal);

//                        JButton btnEliminar = new JButton("Eliminar");
//                        btnEliminar.setBackground(Color.decode("#FE3F5A"));
//                        btnEliminar.setForeground(Color.WHITE);
//                        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                        btnEliminar.addActionListener(e -> eliminarProducto(pnlProducto));
//                        componentes.add(btnEliminar);
                        JLabel deleteBtn = new JLabel();
                        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        deleteBtn.setIcon(new ImageIcon(getClass().getResource("/trash-bin.png")));
                        deleteBtn.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                eliminarProducto(pnlProducto);
                            }
                        });

                        componentes.add(deleteBtn);

                        crearFilaProductos(pnlProducto, componentes);
                        pnlProductos.add(pnlProducto);
                        pnlProducto.setMaximumSize(new Dimension(
                                Integer.MAX_VALUE, pnlProducto.getMinimumSize().height));

                        mapProductos.put(id, pnlProducto);
                        sumarAlTotal(precio);
                    }

                }
            } else {
                MessageHandler.showErrorMessage("No se encontró ningún producto con ese código de barras");
            }
        } catch (SQLException ex) {
            MessageHandler.showErrorMessage("Error al buscar el producto en la base de datos");
        }

        txtCodigoBarras.selectAll();
        txtCodigoBarras.requestFocus();
    }

    private void actualizarSubtotal(PanelProducto pnlProducto, double precio, long stock) {

        JSpinner spnCantidad = (JSpinner) pnlProducto.getComponent(3);
        int cantidad = Double.valueOf(spnCantidad.getValue().toString()).intValue();

        if (cantidad == stock) {
            Runnable myThread = () -> {
                Thread.currentThread().setName("myThread");
                MessageHandler.showSuccessMessage("Se ha alcanzado el límite del stock", "Límite alcanzado");
            };
            Thread run = new Thread(myThread);
            run.start();
        }

        JTextField lblSubtotal = (JTextField) pnlProducto.getComponent(4);
        //JLabel lblSubtotal = (JLabel) pnlProducto.getComponent(4);

        double subtotalPrevio = Double.parseDouble(lblSubtotal.getText().substring(1));
        total -= subtotalPrevio;

        double subtotal = precio * cantidad;

        lblSubtotal.setText("$" + String.format("%.2f", subtotal));

        sumarAlTotal(subtotal);

        txtCodigoBarras.requestFocus();
    }

    private void eliminarProducto(PanelProducto pnlProducto) {
        pnlProductos.remove(pnlProducto);
        pnlProductos.revalidate();
        pnlProductos.repaint();
        long id = mapProductos.entrySet().stream()
                .filter(e -> e.getValue() == pnlProducto)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(Long.valueOf(-1));
        mapProductos.remove(id);

        JTextField lblCantidad = (JTextField) pnlProducto.getComponent(4);
        double subtotal = Double.parseDouble(lblCantidad.getText().substring(1));
        sumarAlTotal(-subtotal);
    }

    private void sumarAlTotal(double cantidad) {
        total += cantidad;
        lblTotal.setText("Total: $" + String.format("%.2f", total));
    }

}
