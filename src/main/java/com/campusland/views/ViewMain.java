package com.campusland.views;

import java.util.Scanner;

import com.campusland.exceptiones.alumnoexceptions.AlumnoNullException;
import com.campusland.exceptiones.profesorexceptions.ProfesorNullException;
import com.campusland.repository.impl.implEdificio.RepositoryEdificioMysqlImpl;
import com.campusland.repository.impl.implalumno.RepositoryAlumnoMysqlImpl;
import com.campusland.repository.impl.implasignatura.RepositoryAsignaturaMysqlImpl;
import com.campusland.repository.impl.implciudad.RepositoryCiudadMysqlImpl;
import com.campusland.repository.impl.implcurso.RepositoryCursoMysqlImpl;
import com.campusland.repository.impl.impldepartamento.RepositoryDepartamentoMysqlImpl;
import com.campusland.repository.impl.implmatricula.RepositoryMatriculaMysqlImpl;
import com.campusland.repository.impl.implperiodo.RepositoryPeriodoMysqlImpl;
import com.campusland.repository.impl.implpersona.RepositoryPersonasMysqlImpl;
import com.campusland.repository.impl.implprofesor.RepositoryProfesorMysqlImpl;
import com.campusland.repository.impl.implprograma.RepositoryProgramaMysqlImpl;
import com.campusland.repository.impl.implsalon.RepositorySalonMysqlImpl;
import com.campusland.services.ServiceAlumno;
import com.campusland.services.ServiceAsignatura;
import com.campusland.services.ServiceCiudad;
import com.campusland.services.ServiceCurso;
import com.campusland.services.ServiceDepartamento;
import com.campusland.services.ServiceEdificio;
import com.campusland.services.ServiceMatricula;
import com.campusland.services.ServicePeriodo;
import com.campusland.services.ServicePersonas;
import com.campusland.services.ServiceProfesor;
import com.campusland.services.ServicePrograma;
import com.campusland.services.ServiceSalon;
import com.campusland.services.impl.ServiceAlumnoImpl;
import com.campusland.services.impl.ServiceAsignaturaImpl;
import com.campusland.services.impl.ServiceCiudadImpl;
import com.campusland.services.impl.ServiceCursosImpl;
import com.campusland.services.impl.ServiceDepartamentoImpl;
import com.campusland.services.impl.ServiceEdificioImpl;
import com.campusland.services.impl.ServiceMatriculaImpl;
import com.campusland.services.impl.ServicePeriodoImpl;
import com.campusland.services.impl.ServicePersonasImpl;
import com.campusland.services.impl.ServiceProfesorImpl;
import com.campusland.services.impl.ServiceProgramaImpl;
import com.campusland.services.impl.ServiceSalonImpl;

public class ViewMain {

    public static final ServiceDepartamento serviceDepartamento = new ServiceDepartamentoImpl(
            new RepositoryDepartamentoMysqlImpl());
    public static final ServicePrograma servicePrograma = new ServiceProgramaImpl(new RepositoryProgramaMysqlImpl());
    public static final ServicePeriodo servicePeriodo = new ServicePeriodoImpl(new RepositoryPeriodoMysqlImpl());
    public static final ServiceCurso serviceCurso = new ServiceCursosImpl(new RepositoryCursoMysqlImpl());
    public static final ServiceCiudad serviceCiudad = new ServiceCiudadImpl(new RepositoryCiudadMysqlImpl());
    public static final ServiceProfesor serviceProfesor = new ServiceProfesorImpl(new RepositoryProfesorMysqlImpl());
    public static final ServiceAlumno serviceAlumno = new ServiceAlumnoImpl(new RepositoryAlumnoMysqlImpl());
    public static final ServiceAsignatura serviceAsignatura = new ServiceAsignaturaImpl(
            new RepositoryAsignaturaMysqlImpl());
    public static final ServicePersonas servicePersonas = new ServicePersonasImpl(new RepositoryPersonasMysqlImpl());
    public static final ServiceEdificio serviceEdificio = new ServiceEdificioImpl(new RepositoryEdificioMysqlImpl());
    public static final ServiceMatricula serviceMatricula = new ServiceMatriculaImpl(
            new RepositoryMatriculaMysqlImpl());
    public static final ServiceSalon serviceSalon = new ServiceSalonImpl(new RepositorySalonMysqlImpl());
    public static final Scanner leer = new Scanner(System.in);

    public static void main(String[] args) throws AlumnoNullException, ProfesorNullException {
        int op = 0;

        do {

            op = menuMain();
            switch (op) {
                case 1:
                    ViewDepartamento.startMenu();
                    break;
                case 2:
                    ViewPrograma.startMenu();
                    break;
                case 3:
                    ViewCurso.startMenu();
                    break;
                case 4:
                    ViewPeriodo.startMenu();
                    break;
                case 5:
                    ViewAsignatura.startMenu();
                    break;
                case 6:
                    ViewSalon.startMenu();
                    break;
                case 7:
                    // ViewHorario.startMenu();
                    break;
                case 8:
                    // ViewTarifas.startMenu();
                    break;
                case 9:
                    ViewAlumno.startMenu();
                    break;
                case 10:
                    ViewProfesor.startMenu();
                    break;
                case 11:
                    ViewMatriculas.startMenu();
                    break;
                default:
                    System.out.println("Fin");
                    break;
            }

        } while (op >= 1 && op < 12);

    }

    public static int menuMain() {
        System.out.println("---Sistema de Matriculas-----");
        System.out.println("1. Modulo de Departamentos");
        System.out.println("2. Modulo de Programas");
        System.out.println("3. Modulo de Cursos");
        System.out.println("4. Modulo de Periodo");
        System.out.println("5. Modulo de Asignaturas");
        System.out.println("6. Modulo de Salones");
        System.out.println("7. Modulo de Horario");
        System.out.println("8. Modulo de Tarifas");
        System.out.println("9. Modulo de Alumnos:");
        System.out.println("10. Modulo de Profesores");
        System.out.println("11. Matricular");
        System.out.println("12. Salir");
        return leer.nextInt();
    }
}