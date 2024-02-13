package com.campusland.views;

import java.time.DateTimeException;
import java.time.Year;
import java.util.List;

import com.campusland.exceptiones.alumnoexceptions.AlumnoNullException;
import com.campusland.repository.models.Asignaturas;
import com.campusland.repository.models.Cursos;
import com.campusland.repository.models.Periodo;
import com.campusland.repository.models.Personas;
import com.campusland.repository.models.Programas;

public class ViewAsignatura extends ViewMain {

    public static void startMenu() throws AlumnoNullException {

        int op = 0;

        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearAsignatura();
                    break;
                case 2:
                    listarAsignatura();
                    break;
                case 3:
                    // buscarAsignatura();
                    break;
                case 4:
                    // modificarAsignatura();
                    break;
                case 5:
                    // eliminarAsignatura();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (op >= 1 && op < 6);
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Asignatura----");
        System.out.println("1. Crear Asignatura.");
        System.out.println("2. Listar Asignatura.");
        System.out.println("3. Buscar Asignatura.");
        System.out.println("4. Modificar Asignatura.");
        System.out.println("5. Eliminar Asignatura.");
        System.out.println("6. Salir ");
        return leer.nextInt();
    }

    public static void crearAsignatura() {
        leer.nextLine(); // Limpiar el buffer de entrada

        // Leer el nombre de la asignatura
        System.out.print("Nombre de la asignatura: ");
        String nombreAsignatura = leer.nextLine();

        // Leer la cantidad de créditos
        System.out.print("Cantidad de créditos: ");
        int cantidadCreditos = leer.nextInt();

        // Listar los programas existentes
        System.out.println("Programas existentes:");
        List<Programas> programas = servicePrograma.listar();
        for (Programas programa : programas) {
            System.out.println(programa.getIdPrograma() + ". " + programa.getNombrePrograma());
        }
        System.out.println((programas.size() + 1) + ". Agregar nuevo programa");

        System.out.println("Ingrese el número correspondiente al programa: ");
        int opcionPrograma = leer.nextInt();

        Programas programaSeleccionado = null;
        if (opcionPrograma >= 1 && opcionPrograma <= programas.size()) {
            // El usuario seleccionó un programa existente
            programaSeleccionado = programas.get(opcionPrograma - 1);
        } else if (opcionPrograma == programas.size() + 1) {
            // El usuario quiere agregar un nuevo programa
            leer.nextLine(); // Limpiar el buffer de entrada
            System.out.print("Ingrese el nombre del nuevo programa: ");
            String nuevoNombrePrograma = leer.nextLine();

            System.out.println("Ingrese el nivel: ");
            String nuevoNivel = leer.nextLine();

            // Crear una nuevo programa y agregarlo a la base de datos
            Programas nuevaPrograma = new Programas(nuevoNombrePrograma, nuevoNivel);
            servicePrograma.crear(nuevaPrograma);
            System.out.println("Nuevo programa agregado correctamente.");
            programaSeleccionado = nuevaPrograma;
        } else {
            System.out.println("Opción inválida. Volviendo al menú principal.");
            return; // Salir del método si la opción ingresada no es válida
        }

        // Listar los periodos existentes
        System.out.println("Periodos existentes:");
        List<Periodo> periodos = servicePeriodo.listar();
        for (Periodo periodo : periodos) {
            System.out.println(periodo.getSemestre() + ". " + periodo.getCodigoEspecifico() + ". " + periodo.getAño());
        }
        System.out.println((periodos.size() + 1) + ". Agregar nuevo periodo");

        System.out.println("Ingrese el número correspondiente al periodo: ");
        int opcionPeriodo = leer.nextInt();

        Periodo periodoSeleccionado = null;
        if (opcionPeriodo >= 1 && opcionPeriodo <= periodos.size()) {
            // El usuario seleccionó un periodo existente
            periodoSeleccionado = periodos.get(opcionPeriodo - 1);
        } else if (opcionPeriodo == periodos.size() + 1) {
            // El usuario quiere agregar un nuevo periodo
            leer.nextLine(); // Limpiar el buffer de entrada
            System.out.print("Ingrese el codigo especifico del nuevo periodo: ");
            int nuevoCodigo = leer.nextInt();

            Year nuevoAño = null;

            // Leer el año del nuevo periodo
            do {
                System.out.print("Ingrese el año del nuevo periodo: ");
                int año = leer.nextInt();

                try {
                    nuevoAño = Year.of(año); // Intenta crear un objeto Year con el año ingresado
                } catch (DateTimeException e) {
                    System.out.println("El año ingresado no es válido. Por favor, ingrese un año válido.");
                }
            } while (nuevoAño == null);

            System.out.print("Ingrese el semestre del nuevo periodo: ");
            int nuevoSemestre = leer.nextInt();

            // Crear un nuevo periodo y agregarlo a la base de datos
            Periodo nuevoPeriodo = new Periodo(nuevoCodigo, nuevoAño, nuevoSemestre);
            servicePeriodo.crear(nuevoPeriodo);
            System.out.println("Nuevo periodo agregado correctamente.");
            periodoSeleccionado = nuevoPeriodo;
        } else {
            System.out.println("Opción inválida. Volviendo al menú principal.");
            return; // Salir del método si la opción ingresada no es válida
        }

        System.out.println("Cursos existentes:");
        List<Cursos> cursos = serviceCurso.listar();
        for (Cursos curso : cursos) {
            System.out.println(curso.getIdCurso() + ". " + curso.getNombreCurso());
        }
        System.out.println((cursos.size() + 1) + ". Agregar nuevo Curso");

        System.out.println("Ingrese el número correspondiente al curso: ");
        int opcionCurso = leer.nextInt();

        Cursos cursoSeleccionado = null;
        if (opcionCurso >= 1 && opcionCurso <= cursos.size()) {
            cursoSeleccionado = cursos.get(opcionCurso - 1);
        } else if (opcionPeriodo == periodos.size() + 1) {
            leer.nextLine(); 
            System.out.print("Ingrese el nombre del nuevo curso: ");
            String nuevoNombre = leer.nextLine();

            System.out.print("Ingrese la guia Catedra del nuevo curso: ");
            String nuevaGuia = leer.nextLine();

            Cursos nuevoCurso = new Cursos(nuevoNombre, nuevaGuia);
            serviceCurso.crear(nuevoCurso);
            System.out.println("Nuevo Curso agregado correctamente.");
            cursoSeleccionado = nuevoCurso;
        } else {
            System.out.println("Opción inválida. Volviendo al menú principal.");
            return; // Salir del método si la opción ingresada no es válida
        }

        System.out.println("Profesores existentes:");
        List<Personas> profesores = serviceProfesor.listar();
        for (Personas profesor : profesores) {
            System.out.println(profesor.getNombre());
        }

        System.out.println("Ingrese el número correspondiente al profesor: ");
        int opcionProfesor = leer.nextInt();

        Personas profesorSeleccionado = null;
        if (opcionProfesor >= 1 && opcionProfesor <= profesores.size()) {
            profesorSeleccionado = profesores.get(opcionProfesor - 1);
        } else {
            System.out.println("Opción inválida. Volviendo al menú principal.");
            return; // Salir del método si la opción ingresada no es válida
        }

        // Extraer el ID del programa seleccionado
        int programaId = programaSeleccionado.getIdPrograma();
        int periodoId = periodoSeleccionado.getIdPeriodo();
        int cursoId = cursoSeleccionado.getIdCurso();
        int profesorId = profesorSeleccionado.getIdPersona();

        Asignaturas nuevaAsignaturas = new Asignaturas(nombreAsignatura, cantidadCreditos, programaId, periodoId,
                cursoId, profesorId);

        serviceAsignatura.crear(nuevaAsignaturas);
        System.out.println("Asignatura Agregada correctamente");
    }

    public static void listarAsignatura(){
        System.out.println("Lista de Asignaturas");
        for (Asignaturas asignatura : serviceAsignatura.listar()) {
            System.out.println("Nombre: " + asignatura.getNombreAsignatura());
            System.out.println("Cantidad de Creditos: " + asignatura.getCantidadCreditos());
            System.out.println();

        }
    }

}
