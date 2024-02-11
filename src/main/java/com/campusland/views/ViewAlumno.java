package com.campusland.views;

import com.campusland.exceptiones.alumnoexceptions.AlumnoNullException;
import com.campusland.exceptiones.personaexceptions.PersonaNullException;
import com.campusland.respository.models.Alumnos;
import com.campusland.respository.models.Direccion;
import com.campusland.respository.models.Personas;

public class ViewAlumno extends ViewMain {

    public static void startMenu() {

        int op = 0;

        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearAlumno();
                    break;
                case 2:
                    // listarAlumno();
                    break;
                case 3:
                    buscarAlumno();
                    break;
                case 4:
                    // modificarAlumno();
                    break;
                case 5:
                    // eliminarAlumno();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (op >= 1 && op < 6);
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Alumno----");
        System.out.println("1. Crear alumno.");
        System.out.println("2. Listar alumno.");
        System.out.println("3. Buscar alumno.");
        System.out.println("4. Modificar alumno.");
        System.out.println("5. Eliminar alumno.");
        System.out.println("6. Salir ");
        return leer.nextInt();
    }

    public static void buscarAlumno() {
        System.out.println("Busqueda del Alumno ");
        leer.nextLine();
        System.out.print("Documento: ");
        String numeroDocumento = leer.nextLine();

        try {
            Alumnos alumno = serviceAlumno.porDocumento(numeroDocumento);
            System.out.println();
            alumno.imprimir();
        } catch (AlumnoNullException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Alumnos buscarGetAlumno() {
        System.out.println("Busqueda del alumno ");
        leer.nextLine();
        System.out.print("Documento: ");
        String numeroDocumento = leer.nextLine();

        try {
            return serviceAlumno.porDocumento(numeroDocumento);
        } catch (AlumnoNullException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void crearAlumno() {
        leer.nextLine();

        System.out.println("Tipo de documento: ");
        System.out.println("1. Cedula");
        System.out.println("2. Cedula de extranjeria");
        System.out.println("3. Pasaporte");
        System.out.println("4. Licencia");
        System.out.println("5. Tarjeta de Identidad");
        int tipoDocOption = leer.nextInt();
        String tipoDoc = obtenerTipoDocumento(tipoDocOption);

        System.out.print("Número de documento: ");
        String numDoc = leer.nextLine();

        leer.nextLine();

        System.out.print("Nombre: ");
        String nombre = leer.nextLine();

        System.out.print("Apellido: ");
        String apellido = leer.nextLine();

        System.out.print("Número de teléfono: ");
        String numTelefono = leer.nextLine();

        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        String fechaNacimientoStr = leer.next();
        java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);

        leer.nextLine();

        System.out.println("Sexo: ");
        System.out.println("1. Masculino");
        System.out.println("2. Femenino");
        int sexoOption = leer.nextInt();
        String sexo = obtenerSexo(sexoOption);

        leer.nextLine();

        System.out.print("Ingrese la dirección (por ejemplo, calle 22 # 20 -66): ");
        String direccionStr = leer.nextLine();

        String[] direccionParts = direccionStr.split("#");
        String calle = direccionParts[0].trim();
        String numeroStr = direccionParts[1].trim();
        String[] numeroParts = numeroStr.split("-");
        int numero = Integer.parseInt(numeroParts[0].trim());

        Direccion direccion = new Direccion(calle, numero);

        System.out.print("Ingrese el nombre de la ciudad: ");
        String nombreCiudad = leer.nextLine();

        try {
            // Crear una nueva persona
            Personas persona = new Personas();
            persona.setTipoDocumento(tipoDoc);
            persona.setNumeroDocumento(numDoc);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setNumeroTelefono(numTelefono);
            persona.setFechaNacimiento(fechaNacimiento);
            persona.setSexo(sexo);
            persona.setDireccionNumero(direccionStr);
            persona.setCiudadId(numero);

            // Guardar la persona en la base de datos
            servicePersonas.crear(persona);

            // Obtener el ID de la persona recién creada
            Personas personaCreada = servicePersonas.porDocumento(numDoc);
            int idPersona = personaCreada.getIdPersona();

            // Crear el alumno asociado a la persona
            Alumnos alumnos = new Alumnos(idPersona);
            serviceAlumno.crear(alumnos);
        } catch (PersonaNullException e) {
            // Manejar la excepción PersonaNullException
            System.out.println("Error: No se encontró la persona con el documento proporcionado.");
            e.printStackTrace(); // Imprimir detalles de la excepción (opcional)
        }

    }

    public static String obtenerTipoDocumento(int opcion) {
        switch (opcion) {
            case 1:
                return "Cedula";
            case 2:
                return "Cedula de extranjeria";
            case 3:
                return "Pasaporte";
            case 4:
                return "Licencia";
            case 5:
                return "Tarjeta de Identidad";
            default:
                return "Tipo de documento inválido";
        }
    }

    public static String obtenerSexo(int opcion) {
        switch (opcion) {
            case 1:
                return "Masculino";
            case 2:
                return "Femenino";
            default:
                return "Sexo inválido";
        }
    }
}
