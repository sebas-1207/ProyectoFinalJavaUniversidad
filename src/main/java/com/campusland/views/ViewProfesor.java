package com.campusland.views;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.campusland.exceptiones.personaexceptions.PersonaNullException;
import com.campusland.respository.RepositoryProfesor;
import com.campusland.respository.models.Ciudad;
import com.campusland.respository.models.Direccion;
import com.campusland.respository.models.Personas;
import com.campusland.respository.models.Profesores;
import com.campusland.services.ServiceProfesor;
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


        System.out.println("Profesor creado exitosamente.");
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
