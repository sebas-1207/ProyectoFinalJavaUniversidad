package com.campusland.views;

import java.util.Scanner;

import com.campusland.repository.impl.implalumno.RepositoryAlumnoMysqlImpl;
import com.campusland.repository.impl.implciudad.RepositoryCiudadMysqlImpl;
import com.campusland.repository.impl.implcliente.RepositoryClientMysqlImpl;
import com.campusland.repository.impl.implcurso.RepositoryCursoMysqlImpl;
import com.campusland.repository.impl.impldepartamento.RepositoryDepartamentoMysqlImpl;
import com.campusland.repository.impl.implfactura.RepositoryFacturaJsonImpl;
import com.campusland.repository.impl.implfactura.RepositoryFacturaMysqlImpl;
import com.campusland.repository.impl.implperiodo.RepositoryPeriodoMysqlImpl;
import com.campusland.repository.impl.implpersona.RepositoryPersonasMysqlImpl;
import com.campusland.repository.impl.implproducto.RepositoryProductoMysqlImpl;
import com.campusland.repository.impl.implprofesor.RepositoryProfesorMysqlImpl;
import com.campusland.repository.impl.implprograma.RepositoryProgramaMysqlImpl;
import com.campusland.services.ServiceAlumno;
import com.campusland.services.ServiceCiudad;
import com.campusland.services.ServiceCliente;
import com.campusland.services.ServiceCurso;
import com.campusland.services.ServiceDepartamento;
import com.campusland.services.ServiceFactura;
import com.campusland.services.ServicePeriodo;
import com.campusland.services.ServicePersonas;
import com.campusland.services.ServiceProducto;
import com.campusland.services.ServiceProfesor;
import com.campusland.services.ServicePrograma;
import com.campusland.services.impl.ServiceAlumnoImpl;
import com.campusland.services.impl.ServiceCiudadImpl;
import com.campusland.services.impl.ServiceClienteImpl;
import com.campusland.services.impl.ServiceCursosImpl;
import com.campusland.services.impl.ServiceDepartamentoImpl;
import com.campusland.services.impl.ServiceFacturaImpl;
import com.campusland.services.impl.ServicePeriodoImpl;
import com.campusland.services.impl.ServicePersonasImpl;
import com.campusland.services.impl.ServiceProductoImpl;
import com.campusland.services.impl.ServiceProfesorImpl;
import com.campusland.services.impl.ServiceProgramaImpl;

public class ViewMain {

    public static final ServiceDepartamento serviceDepartamento = new ServiceDepartamentoImpl(
            new RepositoryDepartamentoMysqlImpl());
    public static final ServicePrograma servicePrograma = new ServiceProgramaImpl(new RepositoryProgramaMysqlImpl());
    public static final ServicePeriodo servicePeriodo = new ServicePeriodoImpl(new RepositoryPeriodoMysqlImpl());
    public static final ServiceCurso serviceCurso = new ServiceCursosImpl(new RepositoryCursoMysqlImpl());
    public static final ServiceCiudad serviceCiudad = new ServiceCiudadImpl(new RepositoryCiudadMysqlImpl());
    public static final ServiceCliente serviceCliente = new ServiceClienteImpl(new RepositoryClientMysqlImpl());
    public static final ServiceProducto serviceProducto = new ServiceProductoImpl(new RepositoryProductoMysqlImpl());
    public static final ServiceFactura serviceFactura = new ServiceFacturaImpl(new RepositoryFacturaMysqlImpl(),
            new RepositoryFacturaJsonImpl());
    public static final ServiceProfesor serviceProfesor = new ServiceProfesorImpl(new RepositoryProfesorMysqlImpl());
    public static final ServiceAlumno serviceAlumno = new ServiceAlumnoImpl(new RepositoryAlumnoMysqlImpl());
    public static final ServicePersonas servicePersonas = new ServicePersonasImpl(new RepositoryPersonasMysqlImpl());
    public static final Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {
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
                    // ViewAsignatura.startMenu();
                    break;
                case 6:
                    // ViewSalon.startMenu();
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
                default:
                    System.out.println("Fin");
                    break;
            }

        } while (op >= 1 && op < 10);

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
        System.out.println("11. Salir");
        return leer.nextInt();
    }
}