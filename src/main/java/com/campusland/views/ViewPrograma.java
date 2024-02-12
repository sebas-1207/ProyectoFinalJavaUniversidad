package com.campusland.views;

import com.campusland.exceptiones.programaexceptions.ProgramaNullException;
import com.campusland.repository.models.Programas;

public class ViewPrograma extends ViewMain {

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearPrograma();
                    break;
                case 2:
                    listarProgramas();
                    break;
                case 3:
                    buscarPrograma();
                    break;
                case 4:
                    modificarPrograma();
                    break;
                case 5:
                    eliminarPrograma();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 6);

    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Programas----");
        System.out.println("1. Crear programa.");
        System.out.println("2. Listar programas.");
        System.out.println("3. Buscar programa.");
        System.out.println("4. Modificar programa.");
        System.out.println("5. Eliminar programa.");
        System.out.println("6. Salir ");
        return leer.nextInt();
    }

    public static void buscarPrograma() {
        System.out.println("Busqueda del programa ");
        leer.nextLine();
        System.out.print("Nombre: ");
        String nombrePrograma = leer.nextLine();

        try {
            Programas programas = servicePrograma.porNombre(nombrePrograma);
            System.out.println();
            programas.imprimir();
        } catch (ProgramaNullException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Programas buscarGetPrograma() {
        System.out.println("Busqueda del programa ");
        leer.nextLine();
        System.out.print("Nombre: ");
        String nombrePrograma = leer.nextLine();

        try {
            return servicePrograma.porNombre(nombrePrograma);

        } catch (ProgramaNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void crearPrograma() {
        leer.nextLine();
        System.out.print("Nombre del Programa: ");
        String nombre = leer.nextLine();
        System.out.print("Nivel: ");
        String nivel = leer.nextLine();
        Programas programas = new Programas(nombre, nivel);
        servicePrograma.crear(programas);

    }

    public static void listarProgramas() {
        System.out.println("Lista de Programas");
        for (Programas programas : servicePrograma.listar()) {
            programas.imprimir();
            System.out.println();
        }
    }

    public static void modificarPrograma() {

        Programas programaActual = buscarGetPrograma();

        if (programaActual != null) {
            System.out.println();
            programaActual.imprimir();

            System.out.println("Modificar nombre: si o no? ");
            String opcion = leer.nextLine();
            if (opcion.equalsIgnoreCase("si")) {
                System.out.println("Nombre: ");
                String nuevoNombre = leer.nextLine();
                programaActual.setNombrePrograma(nuevoNombre);
            }
            System.out.println("Modificar nivel: si o no? ");
            opcion = leer.nextLine();

            if (opcion.equalsIgnoreCase("si")) {
                System.out.println("Nivel: ");
                String nuevoNivel = leer.nextLine();
                programaActual.setNivel(nuevoNivel);
            }
            servicePrograma.editar(programaActual);
        }

    }

    public static void eliminarPrograma() {
        Programas programas = buscarGetPrograma();
        if (programas != null) {
            servicePrograma.eliminar(programas);
            System.out.println("Elmininado el programa con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el programa");
        }

    }
}
