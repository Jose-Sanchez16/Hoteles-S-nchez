drop database if exists hotelesSánchez;
create database hotelesSánchez;
use hotelesSánchez;

-- Tabla TARIFA
CREATE TABLE TARIFA (
    tarifa_id INT PRIMARY KEY AUTO_INCREMENT,
    temporada VARCHAR(50) NOT NULL,
    precio_noche DECIMAL(10,2) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    descripcion TEXT
);

-- Tabla HUESPED
CREATE TABLE HUESPED (
    huesped_id INT PRIMARY KEY AUTO_INCREMENT,
    dni_pasaporte VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(15),
    pais VARCHAR(50),
    fecha_registro DATE NOT NULL,
    tipo_huesped VARCHAR(20)
);

-- Tabla HABITACION
CREATE TABLE HABITACION (
    habitacion_id INT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(10) UNIQUE NOT NULL,
    piso INT NOT NULL,
    estado VARCHAR(20) DEFAULT 'Disponible',
    caracteristicas TEXT,
    tarifa_id INT NOT NULL,
    FOREIGN KEY (tarifa_id) REFERENCES TARIFA(tarifa_id)
);

-- Tabla RESERVA
CREATE TABLE RESERVA (
    reserva_id INT PRIMARY KEY AUTO_INCREMENT,
    fecha_entrada DATE NOT NULL,
    fecha_salida DATE NOT NULL,
    estado VARCHAR(20) DEFAULT 'Confirmada',
    total_reserva DECIMAL(10,2) NOT NULL,
    cantidad_adultos INT NOT NULL,
    cantidad_ninos INT NOT NULL,
    huesped_id INT NOT NULL,
    habitacion_id INT NOT NULL,
    FOREIGN KEY (huesped_id) REFERENCES HUESPED(huesped_id),
    FOREIGN KEY (habitacion_id) REFERENCES HABITACION(habitacion_id)
);

-- Tabla EMPLEADO
CREATE TABLE EMPLEADO (
    empleado_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    rol VARCHAR(30) NOT NULL,
    usuario VARCHAR(30) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    telefono VARCHAR(15),
    email VARCHAR(100)
);

-- Tabla MANTENIMIENTO
CREATE TABLE MANTENIMIENTO (
    mantenimiento_id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    descripcion TEXT NOT NULL,
    estado VARCHAR(20) DEFAULT 'Pendiente',
    habitacion_id INT NOT NULL,
    empleado_id INT NOT NULL,
    FOREIGN KEY (habitacion_id) REFERENCES HABITACION(habitacion_id),
    FOREIGN KEY (empleado_id) REFERENCES EMPLEADO(empleado_id)
);

-- Tabla SERVICIO
CREATE TABLE SERVICIO (
    servicio_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    categoria_servicio VARCHAR(30)
);

-- Tabla CHECKIN_CHECK
CREATE TABLE CHECKIN_CHECK (
    check_id INT PRIMARY KEY AUTO_INCREMENT,
    tipo ENUM('Check-In', 'Check-Out') NOT NULL,
    fecha_hora DATETIME NOT NULL,
    reserva_id INT NOT NULL,
    empleado_id INT NOT NULL,
    FOREIGN KEY (reserva_id) REFERENCES RESERVA(reserva_id),
    FOREIGN KEY (empleado_id) REFERENCES EMPLEADO(empleado_id)
);

-- Tabla FACTURA
CREATE TABLE FACTURA (
    factura_id INT PRIMARY KEY AUTO_INCREMENT,
    fecha_emision DATE NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    impuestos DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    estado VARCHAR(20) DEFAULT 'Pendiente',
    reserva_id INT UNIQUE NOT NULL,
    FOREIGN KEY (reserva_id) REFERENCES RESERVA(reserva_id)
);

-- Tabla intermedia para relación N:N entre RESERVA y SERVICIO
CREATE TABLE RESERVA_SERVICIO (
    reserva_id INT,
    servicio_id INT,
    cantidad INT DEFAULT 1,
    precio_unitario DECIMAL(10,2),
    PRIMARY KEY (reserva_id, servicio_id),
    FOREIGN KEY (reserva_id) REFERENCES RESERVA(reserva_id),
    FOREIGN KEY (servicio_id) REFERENCES SERVICIO(servicio_id)
);


-- Insertar datos en TARIFA
INSERT INTO TARIFA (temporada, precio_noche, fecha_inicio, fecha_fin, descripcion) VALUES
('Baja', 80.00, '2024-01-01', '2024-03-31', 'Temporada baja - Precios económicos'),
('Media', 120.00, '2024-04-01', '2024-06-30', 'Temporada media - Precios moderados'),
('Alta', 180.00, '2024-07-01', '2024-08-31', 'Temporada alta - Precios elevados'),
('Media', 130.00, '2024-09-01', '2024-11-30', 'Temporada media - Precios moderados'),
('Baja', 90.00, '2024-12-01', '2024-12-31', 'Temporada baja - Precios económicos');

-- Insertar datos en HUESPED
INSERT INTO HUESPED (dni_pasaporte, nombre, apellido, email, telefono, pais, fecha_registro, tipo_huesped) VALUES
('12345678A', 'María', 'García', 'maria.garcia@email.com', '+34123456789', 'España', '2024-01-15', 'Regular'),
('87654321B', 'Carlos', 'Rodríguez', 'carlos.rod@email.com', '+34987654321', 'España', '2024-02-20', 'Frecuente'),
('AB123456C', 'John', 'Smith', 'john.smith@email.com', '+44123456789', 'Reino Unido', '2024-03-10', 'Regular'),
('XYZ78901D', 'Sophie', 'Martin', 'sophie.martin@email.com', '+33123456789', 'Francia', '2024-01-25', 'VIP'),
('98765432E', 'Ana', 'López', 'ana.lopez@email.com', '+34666777888', 'España', '2024-02-28', 'Frecuente');

-- Insertar datos en HABITACION
INSERT INTO HABITACION (numero, piso, estado, caracteristicas, tarifa_id) VALUES
('101', 1, 'Disponible', 'Habitación estándar con cama doble, TV y baño privado', 1),
('102', 1, 'Ocupada', 'Habitación estándar con dos camas individuales', 1),
('201', 2, 'Disponible', 'Suite junior con vista al mar, minibar y jacuzzi', 3),
('202', 2, 'Mantenimiento', 'Habitación familiar con capacidad para 4 personas', 2),
('301', 3, 'Disponible', 'Suite presidencial con terraza privada y sala de estar', 4);

-- Insertar datos en EMPLEADO
INSERT INTO EMPLEADO (nombre, apellido, rol, usuario, contrasena, telefono, email) VALUES
('Laura', 'Martínez', 'Recepcionista', 'lmartinez', 'password123', '+34611223344', 'laura.martinez@hotel.com'),
('Pedro', 'Sánchez', 'Gerente', 'psanchez', 'admin123', '+34655443322', 'pedro.sanchez@hotel.com'),
('Elena', 'Gómez', 'Limpieza', 'egomez', 'clean456', '+34699887766', 'elena.gomez@hotel.com'),
('Miguel', 'Fernández', 'Mantenimiento', 'mfernandez', 'maintain789', '+34677665544', 'miguel.fernandez@hotel.com'),
('Sara', 'Díaz', 'Recepcionista', 'sdiaz', 'recep123', '+34622334455', 'sara.diaz@hotel.com');

-- Insertar datos en RESERVA
INSERT INTO RESERVA (fecha_entrada, fecha_salida, estado, total_reserva, cantidad_adultos, cantidad_ninos, huesped_id, habitacion_id) VALUES
('2024-06-01', '2024-06-05', 'Confirmada', 480.00, 2, 0, 1, 1),
('2024-06-10', '2024-06-15', 'Activa', 900.00, 2, 1, 2, 3),
('2024-07-20', '2024-07-25', 'Confirmada', 900.00, 4, 2, 3, 4),
('2024-08-01', '2024-08-07', 'Pendiente', 1080.00, 2, 0, 4, 5),
('2024-05-15', '2024-05-17', 'Completada', 240.00, 1, 0, 5, 2);

-- Insertar datos en SERVICIO
INSERT INTO SERVICIO (nombre, descripcion, precio, categoria_servicio) VALUES
('Desayuno buffet', 'Desayuno continental con variedad de productos', 15.00, 'Alimentación'),
('Spa', 'Acceso a zona spa con sauna y jacuzzi', 25.00, 'Bienestar'),
('Parking', 'Estacionamiento seguro cubierto', 12.00, 'Transporte'),
('Lavandería', 'Servicio de lavado y planchado de ropa', 8.00, 'Limpieza'),
('Room Service', 'Servicio de comida a la habitación 24h', 5.00, 'Alimentación');

-- Insertar datos en MANTENIMIENTO
INSERT INTO MANTENIMIENTO (fecha, descripcion, estado, habitacion_id, empleado_id) VALUES
('2024-05-20', 'Reparación de aire acondicionado', 'Completado', 2, 4),
('2024-05-25', 'Pintura y renovación de paredes', 'En progreso', 4, 4),
('2024-06-02', 'Cambio de cortinas y alfombras', 'Pendiente', 1, 4),
('2024-05-28', 'Revisión general de instalaciones', 'Completado', 3, 4),
('2024-06-01', 'Sustitución de mobiliario', 'Pendiente', 5, 4);

-- Insertar datos en CHECKIN_CHECK
INSERT INTO CHECKIN_CHECK (tipo, fecha_hora, reserva_id, empleado_id) VALUES
('Check-In', '2024-05-15 14:30:00', 5, 1),
('Check-Out', '2024-05-17 11:00:00', 5, 2),
('Check-In', '2024-06-01 15:45:00', 1, 1),
('Check-In', '2024-06-10 16:20:00', 2, 5),
('Check-Out', '2024-06-05 10:15:00', 1, 2);

-- Insertar datos en FACTURA
INSERT INTO FACTURA (fecha_emision, subtotal, impuestos, total, estado, reserva_id) VALUES
('2024-05-17', 240.00, 50.40, 290.40, 'Pagada', 5),
('2024-06-05', 480.00, 100.80, 580.80, 'Pagada', 1),
('2024-06-15', 900.00, 189.00, 1089.00, 'Pendiente', 2),
('2024-07-25', 900.00, 189.00, 1089.00, 'Pendiente', 3),
('2024-08-07', 1080.00, 226.80, 1306.80, 'Pendiente', 4);

-- Insertar datos en RESERVA_SERVICIO
INSERT INTO RESERVA_SERVICIO (reserva_id, servicio_id, cantidad, precio_unitario) VALUES
(1, 1, 4, 15.00),  -- 4 desayunos para reserva 1
(1, 3, 5, 12.00),  -- 5 días de parking para reserva 1
(2, 1, 5, 15.00),  -- 5 desayunos para reserva 2
(2, 2, 2, 25.00),  -- 2 sesiones de spa para reserva 2
(2, 4, 1, 8.00),   -- 1 servicio de lavandería para reserva 2
(3, 1, 18, 15.00), -- 18 desayunos para reserva 3 (6 personas × 3 días)
(3, 5, 3, 5.00),   -- 3 room services para reserva 3
(4, 1, 7, 15.00),  -- 7 desayunos para reserva 4
(4, 2, 3, 25.00),  -- 3 sesiones de spa para reserva 4
(5, 1, 2, 15.00);  -- 2 desayunos para reserva 5 