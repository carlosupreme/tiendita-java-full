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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
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
        stm.setSql(SELECT_BY_CODIGO_BARRAS_QUERY);

        ProductoVenta producto;

        try {
            producto = stm.findById(ProductoVenta.class, codigoDeBarras);
        } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchMethodException | InstantiationException | InvocationTargetException ex) {
            ex.printStackTrace();
            producto = null;
        }
        
        System.out.println(producto);

        return producto;
    }

    private JTextField txtCodigoBarras;
    private JLabel lblTotal;
    private JPanel pnlProductos;
    private Map<Long, PanelProducto> mapProductos;
    private double total;
    private JButton cobrarBtn;
    
    private JPanel crearPanelTitulos() {
        //Crear columnas
        JPanel pnlColumnas = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        pnlColumnas.setLayout(layout);

        pnlColumnas.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        // Definición de las restricciones para cada celda
        GridBagConstraints constraints = new GridBagConstraints();

        // Restricciones para la primera columna
        constraints.gridx = 0;  // Columna 0
        constraints.gridy = 1;  // Fila 0
        constraints.gridwidth = 1;  // Ocupa una sola columna
        constraints.gridheight = 1; // Ocupa una sola fila 
        constraints.weightx = 0.0;  // No se ajusta horizontalmente
        constraints.fill = GridBagConstraints.NONE;  // No se estira
        constraints.anchor = GridBagConstraints.WEST;  // Alineado a la izquierda
        constraints.insets = new Insets(0, 0, 0, 15);  // Margen de 15px a la derecha

        JTextField labelNombre = new JTextField("Nombre", 35);
        labelNombre.setEditable(false);

        layout.setConstraints(labelNombre, constraints);
        pnlColumnas.add(labelNombre);

        constraints.weightx = 1.0 / 5.0;
        
        // Componente para la segunda celda
        JLabel labelCodigoBarras = new JLabel("Código de barras");
        constraints.gridx = 1;  // Columna 2
        constraints.ipadx = 0;
        
        constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 15, 0, 15);  // Margen de 15px a la izquierda y derecha
        layout.setConstraints(labelCodigoBarras, constraints);
        pnlColumnas.add(labelCodigoBarras);

        // Componente para la tercera celda
        JLabel labelPrecio = new JLabel("Precio");
        constraints.gridx = 2;  // Columna 3
        constraints.ipadx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 15, 0, 15);  // Margen de 15px a la izquierda y derecha
        layout.setConstraints(labelPrecio, constraints);
        pnlColumnas.add(labelPrecio);

        JLabel labelCantidad = new JLabel("Cantidad");
        // Componente para la cuarta celda
        constraints.gridx = 3;  // Columna 4
        constraints.ipadx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 15, 0, 15);  // Margen de 15px a la izquierda y derecha
        layout.setConstraints(labelCantidad, constraints);
        pnlColumnas.add(labelCantidad);

        // Componente para la quinta celda
        JLabel lblSubtotal = new JLabel("Subtotal");
        constraints.gridx = 4;  // Columna 5
        constraints.ipadx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 15, 0, 15);  // Margen de 15px a la izquierda y derecha
        layout.setConstraints(lblSubtotal, constraints);
        pnlColumnas.add(lblSubtotal);
        
        // Componente para la sexta celda
        JLabel eliminarLabel = new JLabel("Acción");
        constraints.gridx = 5;  // Columna 6
        constraints.ipadx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 0, 0, 0);  // Margen de 15px a la izquierda y derecha
        layout.setConstraints(eliminarLabel, constraints);
        pnlColumnas.add(eliminarLabel);
        
        return pnlColumnas;
    }

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

        // Panel de productos agregados
        pnlProductos = new JPanel();
        pnlProductos.setLayout(new BoxLayout(pnlProductos, BoxLayout.Y_AXIS));

        add(new JScrollPane(pnlProductos), BorderLayout.CENTER);
        
        JPanel pnlColumnas = crearPanelTitulos();

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

        panelAbajo.add(cb);

        cobrarBtn.addActionListener((ActionEvent e) -> {
            System.out.println(mapProductos.values().size());
            InstruccionDML dml = () -> {
                // Insertar venta
                PreparedStatementMapper<Venta> ventaStm =
                        new PreparedStatementMapper<>("ventas");
                
                Instant fecha = Instant.now().with(ChronoField.NANO_OF_SECOND, 0);
                /*ZoneId z = ZoneId.of("GMT-6");
                ZonedDateTime zdt = current.atZone(z);
                Instant fecha = zdt.toInstant();*/
                
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
                        ex.printStackTrace();
                        throw new SQLException(ex.getMessage());
                    }
                }
            };
            try {
                TransactionManager.ejecutarTransaccion(dml);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Fallo al ejecutar la transacción");
            }
            JOptionPane.showMessageDialog(null, "Venta exitosa");
            mapProductos = new HashMap<>();
            pnlProductos.removeAll();
            JPanel pnlColumnas1 = crearPanelTitulos();
            pnlProductos.add(pnlColumnas1);
            pnlColumnas1.setMaximumSize(new Dimension(Integer.MAX_VALUE, pnlColumnas1.getMinimumSize().height));
            CobrarPanel.this.revalidate();
            CobrarPanel.this.repaint();
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
                long id = productoEncontrado.getId();
                String nombre = productoEncontrado.getNombre();
                String codigoBarrasEncontrado = productoEncontrado.getCodigoBarras();
                double precio = productoEncontrado.getPrecioPublico();
                long stock = productoEncontrado.getStock();

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
                    } else {
                        JOptionPane.showMessageDialog(
                                this, "No hay suficiente stock para agregar otro producto");
                    }
                } else {
                    
                    // Crea un nuevo panel para el producto
                    PanelProducto pnlProducto = new PanelProducto();
                    
                    GridBagLayout layout = new GridBagLayout();
                    pnlProducto.setLayout(layout);
                    
                    pnlProducto.setProducto(productoEncontrado);
                    pnlProducto.setCantidadStock(1);
                    pnlProducto.setBorder(
                            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

                    // Definición de las restricciones para cada celda
                    GridBagConstraints constraints = new GridBagConstraints();

                    // Restricciones para la primera columna
                    constraints.gridx = 0;  // Columna 0
                    constraints.gridy = 0;  // Fila 0
                    constraints.gridwidth = 1;  // Ocupa una sola celda
                    constraints.gridheight = 1;
                    constraints.weightx = 0.0;  // No se ajusta horizontalmente
                    constraints.fill = GridBagConstraints.NONE;  // No se estira
                    constraints.anchor = GridBagConstraints.WEST;  // Alineado a la izquierda
                    constraints.insets = new Insets(0, 0, 0, 15);  // Margen de 15px a la derecha

                    JTextField labelNombre = new JTextField(nombre, 35);
                    labelNombre.setEditable(false);

                    layout.setConstraints(labelNombre, constraints);
                    pnlProducto.add(labelNombre);
                    
                    constraints.weightx = 1.0 / 5.0;

                    // Componente para la segunda celda
                    JLabel labelCodigoBarras = new JLabel(codigoBarrasEncontrado);
                    constraints.gridx = 1;  // Columna 2
                    constraints.ipadx = 0;
                    constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
                    constraints.anchor = GridBagConstraints.WEST;
                    constraints.insets = new Insets(0, 15, 0, 15);  // Margen de 15px a la izquierda y derecha
                    layout.setConstraints(labelCodigoBarras, constraints);
                    pnlProducto.add(labelCodigoBarras);

                    // Componente para la tercera celda
                    JLabel labelPrecio = new JLabel("$" + precio);
                    constraints.gridx = 2;  // Columna 3
                    constraints.ipadx = 0;
                    constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
                    constraints.anchor = GridBagConstraints.WEST;
                    constraints.insets = new Insets(0, 15, 0, 15);  // Margen de 15px a la izquierda y derecha
                    layout.setConstraints(labelPrecio, constraints);
                    pnlProducto.add(labelPrecio);

                    JSpinner spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, stock, 1));
                    spnCantidad.addChangeListener((ChangeEvent e) -> {
                        JSpinner spnCantidad1 = (JSpinner) e.getSource();
                        int cantidad = (int) spnCantidad1.getValue();
                        pnlProducto.setCantidadStock(cantidad);
                        actualizarSubtotal(pnlProducto, precio, stock);
                    });

                    // Componente para la cuarta celda
                    constraints.gridx = 3;  // Columna 4
                    constraints.ipadx = 0;
                    constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
                    constraints.anchor = GridBagConstraints.WEST;
                    constraints.insets = new Insets(0, 15, 0, 15);  // Margen de 15px a la izquierda y derecha
                    layout.setConstraints(spnCantidad, constraints);
                    pnlProducto.add(spnCantidad);

                    // Componente para la quinta celda
                    JLabel lblSubtotal = new JLabel("$" + precio);
                    constraints.gridx = 4;  // Columna 5
                    constraints.ipadx = 0;
                    constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
                    constraints.anchor = GridBagConstraints.WEST;
                    constraints.insets = new Insets(0, 15, 0, 15);  // Margen de 15px a la izquierda y derecha
                    layout.setConstraints(lblSubtotal, constraints);
                    pnlProducto.add(lblSubtotal);

                    JButton btnEliminar = new JButton("Eliminar");
                    btnEliminar.addActionListener(e -> eliminarProducto(pnlProducto, precio));
                    constraints.gridx = 5;  // Columna 6
                    constraints.ipadx = 0;
                    constraints.fill = GridBagConstraints.HORIZONTAL;  // Se estira horizontalmente
                    constraints.anchor = GridBagConstraints.WEST;
                    constraints.insets = new Insets(0, 0, 0, 0);  // Margen de 15px a la izquierda y derecha
                    layout.setConstraints(btnEliminar, constraints);
                    pnlProducto.add(btnEliminar);

                    pnlProductos.add(pnlProducto);
                    mapProductos.put(id, pnlProducto);

                    sumarAlTotal(precio);

                    pnlProducto.setMaximumSize(new Dimension(
                            Integer.MAX_VALUE, pnlProducto.getMinimumSize().height));
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                        "No se encontró ningún producto con ese código de barras");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar el producto en la base de datos");
        }
        txtCodigoBarras.setText("");
        
    }

    private void actualizarSubtotal(PanelProducto pnlProducto, double precio, long stock) {

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
        
        txtCodigoBarras.requestFocus();
    }

    private void eliminarProducto(PanelProducto pnlProducto, double precio) {
        pnlProductos.remove(pnlProducto);
        pnlProductos.revalidate();
        pnlProductos.repaint();
        long id = mapProductos.entrySet().stream()
                .filter(e -> e.getValue() == pnlProducto)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(Long.valueOf(-1));
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
