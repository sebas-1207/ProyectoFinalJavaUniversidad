package com.campusland.views;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.campusland.exceptiones.personaexceptions.PersonaNullException;
import com.campusland.repository.RepositoryProfesor;
import com.campusland.repository.enums.TipoDireccion;
import com.campusland.repository.impl.impldireccion.RepositoryDireccionImpl;
import com.campusland.repository.models.Alumnos;
import com.campusland.repository.models.Ciudad;
import com.campusland.repository.models.Departamentos;
import com.campusland.repository.models.Direccion;
import com.campusland.repository.models.Personas;
import com.campusland.repository.models.Profesores;
import com.campusland.repository.models.Programas;
import com.campusland.services.ServiceDireccion;
import com.campusland.services.ServiceProfesor;
import com.campusland.services.impl.ServiceDireccionImpl;
import com.campusland.services.impl.ServiceProfesorImpl;

public class ViewProfesor extends ViewMain {
    private static final Scanner leer = new Scanner(System.in);

    public static void startMenu() {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearProfesor();
                    break;
                case 2:
                    listarProfesor();
                    break;
                case 3:
                    buscarProfesor();
                    break;
                case 4:
                    modificarProfesor();
                    break;
                case 5:
                    eliminarProfesor();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (op >= 1 && op < 6);
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Profesor----");
        System.out.println("1. Crear Profesor.");
        System.out.println("2. Listar Profesor.");
        System.out.println("3. Buscar Profesor.");
        System.out.println("4. Modificar Profesor.");
        System.out.println("5. Eliminar Profesor.");
        System.out.println("6. Salir ");
        return leer.nextInt();
    }

    public static void crearProfesor() {
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

        System.out.print("Especialidad: ");
        String especialidad = leer.nextLine();

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

            // Obtener la lista de departamentos disponibles
            List<Departamentos> departamentosDisponibles = serviceDepartamento.listar();

            // Mostrar las opciones de departamentos
            System.out.println("Seleccione el departamento del profesor:");
            for (int i = 0; i < departamentosDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + departamentosDisponibles.get(i).getNombreDepartamento());
            }
            System.out.println((departamentosDisponibles.size() + 1) + ". Agregar nuevo departamento");

            System.out.print("Ingrese el número correspondiente al departamento: ");
            int opcionDepartamento = leer.nextInt();

            Departamentos departamentoSeleccionado = null;
            if (opcionDepartamento >= 1 && opcionDepartamento <= departamentosDisponibles.size()) {
                // El usuario seleccionó un departamento existente
                departamentoSeleccionado = departamentosDisponibles.get(opcionDepartamento - 1);
            } else if (opcionDepartamento == departamentosDisponibles.size() + 1) {
                // El usuario quiere agregar un nuevo departamento
                leer.nextLine(); // Limpiar el buffer de entrada
                ViewDepartamento.crearDepartamento();
                return; 
            } else {
                System.out.println("Opción inválida. Volviendo al menú principal.");
                return; 
            }

            // Extraer el ID del programa seleccionado
            int departamentoId = departamentoSeleccionado.getIdDepartamento();

            // Crear un nuevo alumno con el ID de la persona
            Profesores profesores = new Profesores(especialidad, idPersona, departamentoId);

            // Utilizar el servicio para crear el alumno
            serviceProfesor.crear(profesores);
            System.out.println("Profesor Agregado Correctamente");

        } catch (PersonaNullException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarProfesor() {
        System.out.println("Lista de Personas");
        for (Personas persona : servicePersonas.listar()) {
            System.out.println(persona.toString());
            System.out.println();
        }
    }

    public static void buscarProfesor() {
        System.out.println("Búsqueda de persona ");
        System.out.print("ID: ");
        int idPersona = leer.nextInt();
        try {
            Personas persona = servicePersonas.porCodigo(idPersona);
            System.out.println();
            System.out.println(persona.toString());
        } catch (PersonaNullException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Personas buscarGetPersona() {
        System.out.println("Búsqueda de persona ");
        System.out.print("ID: ");
        int idPersona = leer.nextInt();
        try {
            return servicePersonas.porCodigo(idPersona);
        } catch (PersonaNullException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void modificarProfesor() {
        Personas personaActual = buscarGetPersona();
        leer.nextLine();
        if (personaActual != null) {
            System.out.println();
            System.out.println(personaActual.toString());

            modificarAtributoPersona("nombre", personaActual);
            modificarAtributoPersona("apellido", personaActual);
            modificarAtributoPersona("número de teléfono", personaActual);
            modificarAtributoFechaNacimiento(personaActual);
            modificarAtributoDireccion(personaActual);
            modificarAtributoCiudad(personaActual);

            servicePersonas.editar(personaActual);
        }
    }

    public static void modificarAtributoPersona(String atributo, Personas persona) {
        System.out.println("¿Modificar " + atributo + "? (si o no): ");
        String opcion = leer.nextLine();
        if (opcion.equalsIgnoreCase("si")) {
            System.out.println("Nuevo " + atributo + ": ");
            String nuevoValor = leer.nextLine();
            switch (atributo) {
                case "nombre":
                    persona.setNombre(nuevoValor);
                    break;
                case "apellido":
                    persona.setApellido(nuevoValor);
                    break;
                case "número de teléfono":
                    persona.setNumeroTelefono(nuevoValor);
                    break;
                default:
                    System.out.println("Atributo no válido");
                    break;
            }
        }
    }

    public static void modificarAtributoFechaNacimiento(Personas persona) {
        System.out.println("¿Modificar fecha de nacimiento? (si o no): ");
        String opcion = leer.nextLine();
        if (opcion.equalsIgnoreCase("si")) {
            System.out.println("Nueva fecha de nacimiento (en formato yyyy-MM-dd): ");
            String nuevaFechaNacimientoStr = leer.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = dateFormat.parse(nuevaFechaNacimientoStr);
                Date nuevaFechaNacimiento = new Date(parsedDate.getTime());
                persona.setFechaNacimiento(nuevaFechaNacimiento);
            } catch (ParseException e) {
                System.out.println("Formato de fecha inválido.");
            }
        }
    }

    public static void modificarAtributoCiudad(Personas persona) {
        System.out.println("¿Modificar ciudad? (si o no): ");
        String opcion = leer.nextLine();
        if (opcion.equalsIgnoreCase("si")) {
            System.out.print("Ingrese el nuevo nombre de la ciudad: ");
            String nuevoNombreCiudad = leer.nextLine();
            // Actualizar el nombre de la ciudad de la persona...
            persona.getCiudad().setNombreCiudad(nuevoNombreCiudad);
        }
    }

    public static void modificarAtributoDireccion(Personas persona) {
        System.out.println("¿Modificar dirección? (si o no): ");
        String opcion = leer.nextLine();
        if (opcion.equalsIgnoreCase("si")) {
            System.out.print("Ingrese el nuevo tipo de dirección (CALLE, CARRERA, TRANSVERSAL, DIAGONAL, AVENIDA): ");
            String nuevoTipoDireccionStr = leer.nextLine();
            // Actualizar el tipo de dirección de la persona...
            persona.getDireccion().setTipoDireccion(nuevoTipoDireccionStr);

            System.out.print("Ingrese el nuevo número de la dirección: ");
            int nuevoNumeroDireccion = leer.nextInt();
            // Actualizar el número de la dirección de la persona...
            persona.getDireccion().setNumero(nuevoNumeroDireccion);
        }
    }

    public static void eliminarProfesor() {
        Personas persona = buscarGetPersona();
        if (persona != null) {
            servicePersonas.eliminar(persona);
            System.out.println("La persona se eliminó correctamente ");
        } else {
            System.out.println("Se presentó un problema y no se pudo eliminar la persona ");
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
