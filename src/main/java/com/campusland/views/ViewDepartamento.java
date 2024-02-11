package com.campusland.views;

import com.campusland.exceptiones.departamentosexceptions.DepartamentoNullException;
import com.campusland.respository.models.Departamentos;

public class ViewDepartamento extends ViewMain {

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearDepartamento();
                    break;
                case 2:
                    listarDepartamentos();
                    break;
                case 3:
                    buscarDepartamento();
                    break;
                case 4:
                    modificarDepartamento();
                    break;
                case 5:
                    eliminarDepartamento();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 6);

    }

    public static void buscarDepartamento() {
        System.out.println("Busqueda de departamento ");
        leer.nextLine();
        System.out.print("Nombre: ");
        String nombreDepartamento = leer.nextLine();

        try {
            Departamentos departamentos = serviceDepartamento.porNombre(nombreDepartamento);
            System.out.println();
            departamentos.imprimir();
        } catch (DepartamentoNullException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Departamentos buscarGetDepartamento() {
        System.out.println("Busqueda de departamento ");
        leer.nextLine();
        System.out.print("Nombre: ");
        String nombreDepartamento = leer.nextLine();

        try {
            return serviceDepartamento.porNombre(nombreDepartamento);

        } catch (DepartamentoNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void crearDepartamento() {
        leer.nextLine();
        System.out.print("Nombre: ");
        String nombre = leer.nextLine();
        Departamentos departamentos = new Departamentos(nombre);
        serviceDepartamento.crear(departamentos);

    }

    public static void listarDepartamentos() {
        System.out.println("Lista de Departamentos");
        for (Departamentos departamentos : serviceDepartamento.listar()) {
            departamentos.imprimir();
            System.out.println();
        }
    }

    public static void modificarDepartamento() {

        Departamentos departamentoActual = buscarGetDepartamento();

        if (departamentoActual != null) {
            System.out.println();
            departamentoActual.imprimir();

            System.out.println("Modificar nombre: si o no? ");
            String opcion = leer.nextLine();
            if (opcion.equalsIgnoreCase("si")) {
                System.out.println("Nombre: ");
                String nuevoNombre = leer.nextLine();
                departamentoActual.setNombreDepartamento(nuevoNombre);
            }
            serviceDepartamento.editar(departamentoActual);
        }

    }

    public static void eliminarDepartamento() {
        Departamentos departamentos = buscarGetDepartamento();
        if (departamentos != null) {
            serviceDepartamento.eliminar(departamentos);
            System.out.println("Elmininado el departamento con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el departamento");
        }

    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Departamentos----");
        System.out.println("1. Crear departamento.");
        System.out.println("2. Listar departamento.");
        System.out.println("3. Buscar departamento.");
        System.out.println("4. Modificar departamento.");
        System.out.println("5. Eliminar departamento.");
        System.out.println("6. Salir ");
        return leer.nextInt();
    }
}
