CREATE TABLE proveedores (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  telefono VARCHAR(20) NOT NULL,
  email VARCHAR(40) NOT NULL
); 

CREATE TABLE productos (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  codigo_barras VARCHAR(13) NOT NULL UNIQUE,
  precio_publico DECIMAL(10,2) NOT NULL,
  precio_coste DECIMAL(10,2) NOT NULL,
  id_proveedor INT,
  fecha_caducidad TIMESTAMP NOT NULL,
  categoria VARCHAR(50) NOT NULL,
  marca VARCHAR(50) NOT NULL,
  edicion VARCHAR(50) NOT NULL,
  FOREIGN KEY (id_proveedor) REFERENCES proveedores(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE inventario (
  id_producto INT NOT NULL PRIMARY KEY,
  stock INT NOT NULL,
  stock_minimo INT NOT NULL,
  FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE usuarios (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  telefono VARCHAR(15) NOT NULL,
  password VARCHAR(255) NOT NULL,
  rol ENUM('administrador', 'cajero') NOT NULL
);

CREATE TABLE ventas (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fecha TIMESTAMP NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  usuario_id INT,
  forma_pago VARCHAR(50),
  FOREIGN KEY(usuario_id) REFERENCES usuarios(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE detalles_venta (
  id_venta INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  precio_unitario DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (id_venta) REFERENCES ventas(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE compras (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fecha TIMESTAMP NOT NULL,
  id_proveedor INT NOT NULL,
  forma_pago VARCHAR(50),
  total DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (id_proveedor) REFERENCES proveedores(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE detalles_compra (
  id_compra INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  precio_coste DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (id_compra) REFERENCES compras(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE RESTRICT ON UPDATE CASCADE
);
