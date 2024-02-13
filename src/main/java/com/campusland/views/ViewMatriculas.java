package com.campusland.views;

import java.util.List;

import com.campusland.exceptiones.alumnoexceptions.AlumnoNullException;
import com.campusland.repository.models.Asignaturas;
import com.campusland.repository.models.Matriculas;
import com.campusland.repository.models.Personas;

public class ViewMatriculas extends ViewMain {

    public static void startMenu() throws AlumnoNullException {
        int op = 0;

        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearMatricula();
                    break;
                case 2:
                    listarMatricula();
                    break;
                case 3:
                    System.out.println("Saliendo del menú de matrículas.");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (op != 3);
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Matriculas----");
        System.out.println("1. Crear Matricula.");
        System.out.println("2. Listar Matriculas.");
        System.out.println("3. Salir ");
        System.out.print("Ingrese una opción: ");
        return leer.nextInt();
    }

    public static void crearMatricula() {
        leer.nextLine();

        // Mostrar la lista de alumnos disponibles
        List<Personas> alumnos = serviceAlumno.listar();
        System.out.println("Lista de Alumnos:");
        for (int i = 0; i < alumnos.size(); i++) {
            System.out.println((i + 1) + ". " + alumnos.get(i).getNombre());
        }

        // Selección del alumno
        System.out.print("Elija un alumno: ");
        int opcionAlumno = leer.nextInt();
        if (opcionAlumno < 1 || opcionAlumno > alumnos.size()) {
            System.out.println("Opción inválida. Volviendo al menú principal.");
            return;
        }
        Personas alumnoSeleccionado = alumnos.get(opcionAlumno - 1);

        // Mostrar la lista de asignaturas disponibles
        List<Asignaturas> asignaturas = serviceAsignatura.listar();
        System.out.println("Lista de Asignaturas:");
        for (int i = 0; i < asignaturas.size(); i++) {
            System.out.println((i + 1) + ". " + asignaturas.get(i).getNombreAsignatura());
        }

        // Selección de la asignatura
        System.out.print("Elija una asignatura: ");
        int opcionAsignatura = leer.nextInt();
        if (opcionAsignatura < 1 || opcionAsignatura > asignaturas.size()) {
            System.out.println("Opción inválida. Volviendo al menú principal.");
            return;
        }
        Asignaturas asignaturaSeleccionada = asignaturas.get(opcionAsignatura - 1);

        // Realizar la matrícula (implementación pendiente)
        int alumnoId = alumnoSeleccionado.getIdPersona();
        int asignaturaId = asignaturaSeleccionada.getIdAsignatura();

        Matriculas nuevaMatricula = new Matriculas(alumnoId, asignaturaId);
        serviceMatricula.crear(nuevaMatricula);
        System.out.println("Matricula Agregada correctamente");

    }

    public static void listarMatricula() {
        System.out.println("Lista de Matriculas");
        for (Asignaturas matricula : serviceAsignatura.listar()) {
            System.out.println("Nombre del alumno: " );
            System.out.println("Nombre de la Asigantura que tiene:" + matricula.getNombreAsignatura());

            
        }
    }
}
