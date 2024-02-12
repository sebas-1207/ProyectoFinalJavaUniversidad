package com.campusland.views;

import com.campusland.exceptiones.cursoexceptions.CursoNullException;
import com.campusland.repository.models.Cursos;

public class ViewCurso extends ViewMain {

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearCurso();
                    break;
                case 2:
                    listarCursos();
                    break;
                case 3:
                    buscarCurso();
                    break;
                case 4:
                    modificarCurso();
                    break;
                case 5:
                    eliminarCurso();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 6);

    }

    public static void buscarCurso() {
        System.out.println("Busqueda de curso ");
        leer.nextLine();
        System.out.print("Nombre: ");
        String nombreCurso = leer.nextLine();

        try {
            Cursos cursos = serviceCurso.porNombre(nombreCurso);
            System.out.println();
            cursos.imprimir();
        } catch (CursoNullException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Cursos buscarGetCurso() {
        System.out.println("Busqueda de curso ");
        leer.nextLine();
        System.out.print("Nombre: ");
        String nombreCurso = leer.nextLine();

        try {
            return serviceCurso.porNombre(nombreCurso);

        } catch (CursoNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void crearCurso() {
        leer.nextLine();
        System.out.print("Nombre: ");
        String nombre = leer.nextLine();
        System.out.print("Guia Catedra: ");
        String guia = leer.nextLine();
        Cursos cursos = new Cursos(nombre, guia);
        serviceCurso.crear(cursos);
    }

    public static void listarCursos() {
        System.out.println("Lista de Cursos");
        for (Cursos cursos : serviceCurso.listar()) {
            cursos.imprimir();
            System.out.println();
        }
    }

    public static void modificarCurso() {

        Cursos cursoActual = buscarGetCurso();

        if (cursoActual != null) {
            System.out.println();
            cursoActual.imprimir();

            System.out.println("Modificar nombre: si o no? ");
            String opcion = leer.nextLine();
            if (opcion.equalsIgnoreCase("si")) {
                System.out.println("Nombre: ");
                String nuevoNombre = leer.nextLine();
                cursoActual.setNombreCurso(nuevoNombre);
            }
            System.out.println("Modificar guia Catedra: si o no? ");
            opcion = leer.nextLine();

            if (opcion.equalsIgnoreCase("si")) {
                System.out.println("Guia Catedra: ");
                String nuevaGuia = leer.nextLine();
                cursoActual.setGuiaCatedra(nuevaGuia);
            }
            serviceCurso.editar(cursoActual);
        }

    }

    public static void eliminarCurso() {
        Cursos cursos = buscarGetCurso();
        if (cursos != null) {
            serviceCurso.eliminar(cursos);
            System.out.println("Elmininado el curso con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el curso");
        }

    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Curso----");
        System.out.println("1. Crear curso.");
        System.out.println("2. Listar curso.");
        System.out.println("3. Buscar curso.");
        System.out.println("4. Modificar curso.");
        System.out.println("5. Eliminar curso.");
        System.out.println("6. Salir ");
        return leer.nextInt();
    }
}
