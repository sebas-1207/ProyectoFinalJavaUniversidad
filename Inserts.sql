-- Insertar datos en la tabla Departamentos
INSERT INTO Departamentos (nombreDepartamento) VALUES
    ('Departamento de Ingeniería'),
    ('Departamento de Finanzas'),
    ('Departamento de Psicología'),
    ('Departamento de Ciencias de la Computación'),
    ('Departamento de Administración');

-- Insertar datos en la tabla Ciudad
INSERT INTO Ciudad (nombreCiudad) VALUES
    ('Bogotá'),
    ('Medellín'),
    ('Cali'),
    ('Barranquilla'),
    ('Cartagena');

-- Insertar datos en la tabla Direccion
INSERT INTO Direccion (tipoDireccion, numero) VALUES
    ('CALLE', 123),
    ('CARRERA', 45),
    ('AVENIDA', 67),
    ('TRANSVERSAL', 89),
    ('DIAGONAL', 101);

-- Insertar datos en la tabla Edificios
INSERT INTO Edificios (numeroEdificio, numeroPiso, capacidadMax_salones) VALUES
    (1, 1, 50),
    (2, 2, 60),
    (3, 1, 55),
    (4, 3, 70),
    (5, 2, 65);    

-- Insertar datos en la tabla Programas
INSERT INTO Programas (nombrePrograma, nivel) VALUES
    ('Ingeniería de Sistemas', 'Pregrado'),
    ('Administración de Empresas', 'Pregrado'),
    ('Psicología', 'Pregrado'),
    ('Maestría en Ciencias de la Computación', 'Posgrado'),
    ('Maestría en Administración de Empresas', 'Posgrado');

-- Insertar datos en la tabla Cursos
INSERT INTO Cursos (nombreCurso, guiaCatedra) VALUES
    ('Programación I', 'Juan Pérez'),
    ('Contabilidad Financiera', 'María Rodríguez'),
    ('Psicología Infantil', 'Ana Gómez'),
    ('Inteligencia Artificial', 'Luis Martínez'),
    ('Gestión Estratégica', 'Carlos Sánchez');

-- Insertar datos en la tabla Periodo
INSERT INTO Periodo (codigoEspecifico, año, semestre) VALUES
    (20241, 2024, 1),
    (20242, 2024, 2),
    (20251, 2025, 1),
    (20252, 2025, 2),
    (20261, 2026, 1);


-- Insertar datos en la tabla Tarifas
INSERT INTO Tarifas (costoCreditos, valorCredito, programaId, periodoId) VALUES
    (300, 50000, 1, 1),
    (280, 60000, 2, 1),
    (320, 55000, 3, 1),
    (400, 70000, 4, 1),
    (350, 65000, 5, 1);

-- Insertar datos en la tabla Salones
INSERT INTO Salones (capacidadAlumnos, numeroIdentificacion, edificioId) VALUES
    (50, 101, 1),
    (60, 201, 2),
    (55, 102, 3),
    (70, 301, 4),
    (65, 202, 5);    


-- Insertar datos en la tabla Personas
INSERT INTO Personas (tipoDocumento, numeroDocumento, nombre, apellido, numeroTelefono, fechaNacimiento, sexo, direccionNumero, ciudadID) VALUES
    ('CEDULA', '123456789', 'Juan', 'García', '3214567890', '1990-05-15', 'MASCULINO', 1, 1),
    ('CEDULA', '987654321', 'María', 'López', '3217894560', '1992-09-20', 'FEMENINO', 2, 2),
    ('CEDULA', '456789123', 'Pedro', 'Martínez', '3156789450', '1991-11-12', 'MASCULINO', 3, 3),
    ('CEDULA', '654123789', 'Ana', 'Sánchez', '3109876543', '1993-03-25', 'FEMENINO', 4, 4),
    ('CEDULA', '789456123', 'Carlos', 'Gómez', '3157894561', '1994-07-30', 'MASCULINO', 5, 5),
    ('CEDULA_EXTRANJERIA', '98765432', 'Laura', 'Pérez', '3001234567', '1995-01-10', 'FEMENINO', 1, 1),
    ('TARJETA_IDENTIDAD', '1234567890', 'Luis', 'González', '3112345678', '1996-06-18', 'MASCULINO', 2, 2),
    ('PASAPORTE', 'AB123456', 'Diana', 'Hernández', '3123456789', '1997-12-05', 'FEMENINO', 3, 3),
    ('LICENCIA', '987654', 'Andrés', 'Díaz', '3145678901', '1998-10-03', 'MASCULINO', 4, 4),
    ('CEDULA', '123987456', 'Paola', 'Rojas', '3106789452', '1999-04-28', 'FEMENINO', 5, 5);    


-- Insertar datos en la tabla Profesores
INSERT INTO Profesores (especialidad, personaId, departamentoId) VALUES
    ('Desarrollo Web', 1, 1),
    ('Finanzas Corporativas', 2, 2),
    ('Psicología Clínica', 3, 3),
    ('Machine Learning', 4, 4),
    ('Gestión de Proyectos', 5, 5);    


-- Insertar datos en la tabla Alumnos
INSERT INTO Alumnos (personaId) VALUES
    (6),
    (7),
    (8),
    (9),
    (10);    


-- Insertar datos en la tabla Asignaturas
INSERT INTO Asignaturas (nombreAsignatura, cantidadCreditos, programaId, periodoId, cursoId, profesorId) VALUES
    ('Programación I', 4, 1, 1, 1, 1),
    ('Contabilidad Financiera', 3, 2, 1, 2, 2),
    ('Psicología Infantil', 3, 3, 1, 3, 3),
    ('Inteligencia Artificial', 4, 4, 1, 4, 4),
    ('Gestión Estratégica', 3, 5, 1, 5, 5);    


-- Insertar datos en la tabla Matriculas
INSERT INTO Matriculas (alumnoId, asignaturaId) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);    

-- Insertar datos en la tabla Programacion
INSERT INTO Programacion (dia, horaInicio, horaFin, asignaturaId, matriculaId, salonId) VALUES
    ('2024-02-10', '08:00:00', '10:00:00', 1, 1, 1),
    ('2024-02-10', '10:30:00', '12:30:00', 2, 2, 2),
    ('2024-02-11', '08:00:00', '10:00:00', 3, 3, 3),
    ('2024-02-11', '10:30:00', '12:30:00', 4, 4, 4),
    ('2024-02-12', '08:00:00', '10:00:00', 5, 5, 5);





















