CREATE TABLE `Programas`(
    `idPrograma` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nombrePrograma` VARCHAR(50) NOT NULL,
    `nivel` VARCHAR(50) NOT NULL
);
CREATE TABLE `Cursos`(
    `idCurso` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nombreCurso` VARCHAR(50) NOT NULL,
    `guiaCatedra` VARCHAR(50) NOT NULL
);
CREATE TABLE `Programacion`(
    `dia` DATE NOT NULL,
    `horaInicio` TIME NOT NULL,
    `horaFin` TIME NOT NULL,
    `asignaturaId` INT NOT NULL,
    `matriculaId` INT NOT NULL,
    `salonId` INT NOT NULL
);
CREATE TABLE `Ciudad`(
    `idCiudad` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nombreCiudad` VARCHAR(50) NOT NULL
);
CREATE TABLE `Tarifas`(
    `idTarifas` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `costoCreditos` DOUBLE NOT NULL,
    `valorCredito` DOUBLE NOT NULL,
    `programaId` INT NOT NULL,
    `periodoId` INT NOT NULL
);
CREATE TABLE `Matriculas`(
    `idMatricula` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `alumnoId` INT NOT NULL,
    `asignaturaId` INT NOT NULL
);
CREATE TABLE `Edificios`(
    `idEdificio` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `numeroEdificio` INT NOT NULL,
    `numeroPiso` INT NOT NULL,
    `capacidadMax_salones` INT NOT NULL
);
CREATE TABLE `Profesores`(
    `idProfesor` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `especialidad` VARCHAR(50) NOT NULL,
    `personaId` INT NOT NULL,
    `departamentoId` INT NOT NULL
);
CREATE TABLE `Periodo`(
    `idPeriodo` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `codigoEspecifico` INT NOT NULL,
    `año` YEAR NOT NULL,
    `semestre` INT NOT NULL
);
CREATE TABLE `Direccion`(
    `numeroDireccion` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `tipoDireccion` ENUM('CARRERA', 'CALLE', 'TRANSVERSAL', 'AVENIDA', 'DIAGONAL') NOT NULL,
    `numero` INT NOT NULL
);
CREATE TABLE `Departamentos`(
    `idDepartamento` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nombreDepartamento` VARCHAR(255) NOT NULL
);
CREATE TABLE `Salones`(
    `idSalones` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `capacidadAlumnos` INT NOT NULL,
    `numeroIdentificacion` INT NOT NULL,
    `edificioId` INT NOT NULL
);
CREATE TABLE `Asignaturas`(
    `idAsignatura` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nombreAsignatura` VARCHAR(50) NOT NULL,
    `cantidadCreditos` INT NOT NULL,
    `programaId` INT NOT NULL,
    `periodoId` INT NOT NULL,
    `cursoId` INT NOT NULL,
    `profesorId` INT NOT NULL
);
CREATE TABLE `Alumnos`(
    `idAlumno` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `personaId` INT NOT NULL
);
CREATE TABLE `Personas`(
    `idPersona` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `tipoDocumento` ENUM('CEDULA', 'CEDULA_EXTRANJERIA', 'PASAPORTE', 'TARJETA_IDENTIDAD', 'LICENCIA') NOT NULL,
    `numeroDocumento` VARCHAR(50) NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    `apellido` VARCHAR(50) NOT NULL,
    `numeroTelefono` VARCHAR(50) NOT NULL,
    `fechaNacimiento` DATE NOT NULL,
    `sexo` ENUM('MASCULINO', 'FEMENINO') NOT NULL,
    `direccionNumero` INT NOT NULL,
    `ciudadID` INT NOT NULL
);
ALTER TABLE
    `Asignaturas` ADD CONSTRAINT `asignaturas_programaid_foreign` FOREIGN KEY(`programaId`) REFERENCES `Programas`(`idPrograma`);
ALTER TABLE
    `Matriculas` ADD CONSTRAINT `matriculas_alumnoid_foreign` FOREIGN KEY(`alumnoId`) REFERENCES `Alumnos`(`idAlumno`);
ALTER TABLE
    `Alumnos` ADD CONSTRAINT `alumnos_personaid_foreign` FOREIGN KEY(`personaId`) REFERENCES `Personas`(`idPersona`);
ALTER TABLE
    `Asignaturas` ADD CONSTRAINT `asignaturas_cursoid_foreign` FOREIGN KEY(`cursoId`) REFERENCES `Cursos`(`idCurso`);
ALTER TABLE
    `Matriculas` ADD CONSTRAINT `matriculas_asignaturaid_foreign` FOREIGN KEY(`asignaturaId`) REFERENCES `Asignaturas`(`idAsignatura`);
ALTER TABLE
    `Programacion` ADD CONSTRAINT `programacion_matriculaid_foreign` FOREIGN KEY(`matriculaId`) REFERENCES `Matriculas`(`idMatricula`);
ALTER TABLE
    `Tarifas` ADD CONSTRAINT `tarifas_periodoid_foreign` FOREIGN KEY(`periodoId`) REFERENCES `Periodo`(`idPeriodo`);
ALTER TABLE
    `Programacion` ADD CONSTRAINT `programacion_salonid_foreign` FOREIGN KEY(`salonId`) REFERENCES `Salones`(`idSalones`);
ALTER TABLE
    `Personas` ADD CONSTRAINT `personas_direccionnumero_foreign` FOREIGN KEY(`direccionNumero`) REFERENCES `Direccion`(`numeroDireccion`);
ALTER TABLE
    `Programacion` ADD CONSTRAINT `programacion_asignaturaid_foreign` FOREIGN KEY(`asignaturaId`) REFERENCES `Asignaturas`(`idAsignatura`);
ALTER TABLE
    `Profesores` ADD CONSTRAINT `profesores_personaid_foreign` FOREIGN KEY(`personaId`) REFERENCES `Personas`(`idPersona`);
ALTER TABLE
    `Asignaturas` ADD CONSTRAINT `asignaturas_periodoid_foreign` FOREIGN KEY(`periodoId`) REFERENCES `Periodo`(`idPeriodo`);
ALTER TABLE
    `Salones` ADD CONSTRAINT `salones_edificioid_foreign` FOREIGN KEY(`edificioId`) REFERENCES `Edificios`(`idEdificio`);
ALTER TABLE
    `Personas` ADD CONSTRAINT `personas_ciudadid_foreign` FOREIGN KEY(`ciudadID`) REFERENCES `Ciudad`(`idCiudad`);
ALTER TABLE
    `Profesores` ADD CONSTRAINT `profesores_departamentoid_foreign` FOREIGN KEY(`departamentoId`) REFERENCES `Departamentos`(`idDepartamento`);
ALTER TABLE
    `Tarifas` ADD CONSTRAINT `tarifas_programaid_foreign` FOREIGN KEY(`programaId`) REFERENCES `Programas`(`idPrograma`);
ALTER TABLE
    `Asignaturas` ADD CONSTRAINT `asignaturas_profesorid_foreign` FOREIGN KEY(`profesorId`) REFERENCES `Profesores`(`idProfesor`);