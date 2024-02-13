package com.campusland.views;

import java.util.Arrays;
import java.util.List;

import com.campusland.exceptiones.alumnoexceptions.AlumnoNullException;
import com.campusland.exceptiones.personaexceptions.PersonaNullException;
import com.campusland.repository.enums.TipoDireccion;
import com.campusland.repository.impl.impldireccion.RepositoryDireccionImpl;
import com.campusland.repository.models.Alumnos;
import com.campusland.repository.models.Ciudad;
import com.campusland.repository.models.Direccion;
import com.campusland.repository.models.Personas;
import com.campusland.repository.models.Programas;
import com.campusland.services.ServiceDireccion;
import com.campusland.services.impl.ServiceDireccionImpl;

public class ViewAlumno extends ViewMain {

    public static void startMenu() throws AlumnoNullException {

        int op = 0;

        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearAlumno();
                    break;
                case 2:
                    listarAlumnos();
                    break;
                case 3:
                    buscarAlumno();
                    break;
                case 4:
                    modificarAlumno();
                    break;
                case 5:
                    //eliminarAlumno();
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

    public static void crearAlumno() {
        // Limpiar el buffer de entrada
        leer.nextLine();

        // Mostrar las opciones de tipo de documento
        System.out.println("Tipo de documento: ");
        System.out.println("1. Cedula");
        System.out.println("2. Cedula de extranjeria");
        System.out.println("3. Pasaporte");
        System.out.println("4. Licencia");
        System.out.println("5. Tarjeta de Identidad");
        int tipoDocOption = leer.nextInt();
        String tipoDoc = obtenerTipoDocumento(tipoDocOption);

        // Leer el número de documento
        System.out.print("Número de documento: ");
        String numeroDocumento = leer.next();

        leer.nextLine(); // Limpiar el buffer de entrada

        // Leer el nombre
        System.out.print("Nombre: ");
        String nombre = leer.nextLine();

        // Leer el apellido
        System.out.print("Apellido: ");
        String apellido = leer.nextLine();

        // Leer el número de teléfono
        System.out.print("Número de teléfono: ");
        String numTelefono = leer.nextLine();

        // Leer la fecha de nacimiento
        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        String fechaNacimientoStr = leer.next();
        java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);

        leer.nextLine(); // Limpiar el buffer de entrada

        // Mostrar las opciones de sexo
        System.out.println("Sexo: ");
        System.out.println("1. Masculino");
        System.out.println("2. Femenino");
        int sexoOption = leer.nextInt();
        String sexo = obtenerSexo(sexoOption);

        leer.nextLine(); // Limpiar el buffer de entrada

        // Leer la dirección
        String direccionStr;
        TipoDireccion tipoDireccionEnum = null;
        int numero = 0;
        String barrio = "";

        do {
            System.out.print("Ingrese la dirección (por ejemplo, calle 22): ");
            direccionStr = leer.nextLine();

            // Dividir la dirección en palabras
            String[] direccionParts = direccionStr.split("\\s+");

            if (direccionParts.length >= 2) {
                // El primer elemento es el tipo de dirección
                String tipoDireccionStr = direccionParts[0].trim();

                // Unir los elementos restantes para obtener el número
                String numeroStr = String.join(" ", Arrays.copyOfRange(direccionParts, 1, direccionParts.length))
                        .trim();

                // Convertir el tipo de dirección a Enum
                try {
                    tipoDireccionEnum = TipoDireccion.valueOf(tipoDireccionStr.toUpperCase());
                    numero = Integer.parseInt(numeroStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: Dirección inválida. Por favor, inténtelo de nuevo.");
                }
            } else {
                System.out.println("Error: Dirección inválida. Por favor, inténtelo de nuevo.");
            }

            System.out.print("Ingrese el nombre del barrio: ");
            barrio = leer.nextLine();
        } while (tipoDireccionEnum == null);

        // Crear un objeto de tipo Direccion
        Direccion direccion = new Direccion(tipoDireccionEnum, numero, barrio);

        // Obtener una instancia del servicio de dirección
        ServiceDireccion serviceDireccion = new ServiceDireccionImpl(new RepositoryDireccionImpl());

        try {
            // Crear la dirección
            serviceDireccion.crear(direccion);

            // Obtener la lista de ciudades disponibles
            List<Ciudad> ciudades = serviceCiudad.listar();

            // Mostrar las opciones de ciudad
            System.out.println("Seleccione una ciudad o agregue una nueva:");
            for (int i = 0; i < ciudades.size(); i++) {
                System.out.println((i + 1) + ". " + ciudades.get(i).getNombreCiudad());
            }
            System.out.println((ciudades.size() + 1) + ". Agregar nueva ciudad");

            System.out.print("Ingrese el número correspondiente a la ciudad: ");
            int opcionCiudad = leer.nextInt();

            Ciudad ciudadSeleccionada = null;
            if (opcionCiudad >= 1 && opcionCiudad <= ciudades.size()) {
                // El usuario seleccionó una ciudad existente
                ciudadSeleccionada = ciudades.get(opcionCiudad - 1);
            } else if (opcionCiudad == ciudades.size() + 1) {
                // El usuario quiere agregar una nueva ciudad
                leer.nextLine(); // Limpiar el buffer de entrada
                System.out.print("Ingrese el nombre de la nueva ciudad: ");
                String nuevoNombreCiudad = leer.nextLine();

                // Crear una nueva ciudad y agregarla a la base de datos
                Ciudad nuevaCiudad = new Ciudad(nuevoNombreCiudad);
                serviceCiudad.crear(nuevaCiudad);
                System.out.println("Nueva ciudad agregada correctamente.");
                ciudadSeleccionada = nuevaCiudad;
            } else {
                System.out.println("Opción inválida. Volviendo al menú principal.");
                return; // Salir del método si la opción ingresada no es válida
            }

            // Extraer el ID de la ciudad seleccionada
            int ciudadId = ciudadSeleccionada.getIdCiudad();

            // Crear una nueva persona
            Personas persona = new Personas();
            persona.setTipoDocumento(tipoDoc);
            persona.setNumeroDocumento(numeroDocumento);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setNumeroTelefono(numTelefono);
            persona.setFechaNacimiento(fechaNacimiento);
            persona.setSexo(sexo);
            persona.setDireccionNumero(direccion.getNumeroDireccion());
            persona.setCiudadId(ciudadId); // Establecer el ID de la ciudad en la persona

            // Utilizar el servicio para crear la persona
            servicePersonas.crear(persona);

            // Obtener la persona recién creada para obtener su ID
            Personas personaCreada = servicePersonas.porDocumento(numeroDocumento);
            int idPersona = personaCreada.getIdPersona();

            // Obtener la lista de programas disponibles
            List<Programas> programasDisponibles = servicePrograma.listar();

            // Mostrar las opciones de programas
            System.out.println("Seleccione el programa del alumno:");
            for (int i = 0; i < programasDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + programasDisponibles.get(i).getNombrePrograma());
            }
            System.out.println((programasDisponibles.size() + 1) + ". Agregar nuevo programa");

            System.out.print("Ingrese el número correspondiente al programa: ");
            int opcionPrograma = leer.nextInt();

            Programas programaSeleccionado = null;
            if (opcionPrograma >= 1 && opcionPrograma <= programasDisponibles.size()) {
                // El usuario seleccionó un programa existente
                programaSeleccionado = programasDisponibles.get(opcionPrograma - 1);
            } else if (opcionPrograma == programasDisponibles.size() + 1) {
                // El usuario quiere agregar un nuevo programa
                leer.nextLine(); // Limpiar el buffer de entrada
                ViewPrograma.crearPrograma(); // Llamar al método de creación de programa en la clase ViewPrograma
                return; // Salir del método después de agregar un nuevo programa
            } else {
                System.out.println("Opción inválida. Volviendo al menú principal.");
                return; // Salir del método si la opción ingresada no es válida
            }

            // Extraer el ID del programa seleccionado
            int programaId = programaSeleccionado.getIdPrograma();

            // Crear un nuevo alumno con el ID de la persona
            Alumnos alumno = new Alumnos(idPersona, programaId);

            // Utilizar el servicio para crear el alumno
            serviceAlumno.crear(alumno);
            System.out.println("Alumno Agregado Correctamente");

        } catch (PersonaNullException e) {
            System.out.println("Error: " + e.getMessage());
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

    public static void listarAlumnos() {
        System.out.println("Lista de Alumnos");
        for (Personas alumnos : serviceAlumno.listar()) {
            System.out.println("Tipo de Documento: " + alumnos.getTipoDocumento());
            System.out.println("Numero de Documento: " + alumnos.getNumeroDocumento());
            System.out.println("Nombre: " + alumnos.getNombre());
            System.out.println("Apellido: " + alumnos.getApellido());
            System.out.println("Numero: " + alumnos.getNumeroTelefono());
            System.out.println("Fecha de Nacimiento: " + alumnos.getFechaNacimiento());
            System.out.println("Genero: " + alumnos.getSexo());
            System.out.println("Direccion: " + alumnos.getDireccionNumero());
            System.out.println("Ciudad: " + alumnos.getCiudadId());
            System.out.println();
        }
    }

/*     private static void eliminarAlumno() {
        System.out.println("Eliminar un alumno");
        leer.nextLine();
        System.out.print("\t Documento del alumno: ");
        String numeroDocumento = leer.nextLine();
        
        try {
            // Obtener el alumno por su documento
            Personas alumno = serviceAlumno.porDocumento(numeroDocumento);
            // Verificar si se encontró un alumno con el documento especificado
            if (alumno != null) {
                // Eliminar al alumno utilizando el servicio
                serviceAlumno.eliminar(alumno.getIdPersona());
                System.out.println("Alumno eliminado exitosamente");
            } else {
                System.out.println("No se encontró ningún alumno con el documento especificado");
            }
        } catch (AlumnoNullException e) {
            e.printStackTrace();
        }
    } */
    

    private static void buscarAlumno() throws AlumnoNullException {

        System.out.println("Buscar Alumnos");
        leer.nextLine();
        System.out.print("Numero de Documento: ");
        String numeroDocumento = leer.nextLine();
        Personas alumnos = serviceAlumno.porDocumento(numeroDocumento);
        System.out.println("Alumno Encontrado");
        System.out.println("Tipo de Documento: " + alumnos.getTipoDocumento());
        System.out.println("Numero de Documento: " + alumnos.getNumeroDocumento());
        System.out.println("Nombre: " + alumnos.getNombre());
        System.out.println("Apellido: " + alumnos.getApellido());
        System.out.println("Numero: " + alumnos.getNumeroTelefono());
        System.out.println("Fecha de Nacimiento: " + alumnos.getFechaNacimiento());
        System.out.println("Genero: " + alumnos.getSexo());
        System.out.println("Direccion: " + alumnos.getDireccionNumero());
        System.out.println("Ciudad: " + alumnos.getCiudadId());
        System.out.println();
    }

    private static void modificarAlumno() throws AlumnoNullException {

        System.out.println("Editar un Estudiante");
        leer.nextLine();
        System.out.print("\t Numero de Documento: ");
        String numeroDocumento = leer.nextLine();
        Personas alumnos = serviceAlumno.porDocumento(numeroDocumento);

        System.out.println("Editando Alumno...");

        System.out.println("Modificar nombre: si o no? ");
        String opcion = leer.nextLine();
        if (opcion.equalsIgnoreCase("si")) {
            System.out.println("Nombre: ");
            alumnos.setNombre((leer.nextLine().length() > 0) ? leer.nextLine() : alumnos.getNombre());
        }

        System.out.println("Modificar apellido: si o no? ");
        opcion = leer.nextLine();
        if (opcion.equalsIgnoreCase("si")) {
            System.out.println("Apellido: ");
            alumnos.setApellido((leer.nextLine().length() > 0) ? leer.nextLine() : alumnos.getApellido());
        }

        System.out.println("Modificar numero: si o no? ");
        opcion = leer.nextLine();
        if (opcion.equalsIgnoreCase("si")) {
            System.out.println("Numero: ");
            alumnos.setNumeroTelefono(
                    (leer.nextLine().length() > 0) ? leer.nextLine() : alumnos.getNumeroTelefono());
        }

        System.out.println("Modificar fecha de nacimiento: si o no? ");
        opcion = leer.nextLine();
        if (opcion.equalsIgnoreCase("si")) {
            System.out.println("Fecha de Nacimiento (AAAA-MM-DD): ");
            String fechaNacimientoStr = leer.nextLine();
            // Verificar si se ingresó una fecha no vacía
            if (!fechaNacimientoStr.isEmpty()) {
                try {
                    // Convertir la cadena de texto a java.sql.Date
                    java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);
                    // Establecer la fecha de nacimiento en el objeto alumno
                    alumnos.setFechaNacimiento(fechaNacimiento);
                } catch (IllegalArgumentException e) {
                    System.out.println(
                            "Error: Formato de fecha incorrecto. La fecha debe tener el formato AAAA-MM-DD.");
                }
            } else {
                // Conservar la fecha de nacimiento existente si no se ingresó una nueva
                System.out.println("No se ingresó una nueva fecha de nacimiento. Se mantendrá la fecha actual.");
            }
        }

        System.out.println("Modificar genero: si o no? ");
        opcion = leer.nextLine();
        if (opcion.equalsIgnoreCase("si")) {
            System.out.println("Genero: ");
            alumnos.setSexo((leer.nextLine().length() > 0) ? leer.nextLine() : alumnos.getSexo());
        }

        List<Ciudad> ciudades = serviceCiudad.listar();
        System.out.println("Lista de Ciudades");
        for (int i = 0; i < ciudades.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + ciudades.get(i).getNombreCiudad());
        }
        int op_ciudad = 0;
        do {
            System.out.print("\t Elija una ciudad: ");
            op_ciudad = leer.nextInt();
        } while (op_ciudad < 1 || op_ciudad > ciudades.size());
        int ciudad = ciudades.get(op_ciudad - 1).getIdCiudad();
        alumnos.setCiudadId(ciudad);
        servicePersonas.editar(alumnos);
        List<Programas> programas = servicePrograma.listar();
        int op_programa = 0;
        System.out.println("Lista de programas");
        for (int i = 0; i < programas.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + programas.get(i).getNombrePrograma());
        }
        do {
            System.out.print("\t Elija un programa: ");
            op_programa = leer.nextInt();
        } while (op_programa < 1 || op_programa > programas.size());
        int programa = programas.get(op_programa - 1).getIdPrograma();
        serviceAlumno.editar(new Alumnos(programa, alumnos.getIdPersona()));

    }

}
