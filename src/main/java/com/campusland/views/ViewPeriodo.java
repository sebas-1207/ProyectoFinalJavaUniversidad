package com.campusland.views;

import java.time.Year;

import com.campusland.exceptiones.periodoexceptions.PeriodoNullException;
import com.campusland.repository.models.Periodo;

public class ViewPeriodo extends ViewMain {

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearPeriodo();
                    break;
                case 2:
                    listarPeriodo();
                    break;
                case 3:
                    buscarPeriodo();
                    break;
                case 4:
                    modificarPeriodo();
                    break;
                case 5:
                    eliminarPeriodo();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 6);

    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Periodo----");
        System.out.println("1. Crear Periodo.");
        System.out.println("2. Listar Periodo.");
        System.out.println("3. Buscar Periodo.");
        System.out.println("4. Modificar Periodo.");
        System.out.println("5. Eliminar Periodo.");
        System.out.println("6. Salir ");
        return leer.nextInt();
    }

    public static void buscarPeriodo() {
        System.out.println("Busqueda de Periodo ");
        leer.nextLine();
        System.out.print("Codigo Especifico: ");
        int codigoEspecifico = leer.nextInt();

        try {
            Periodo periodo = servicePeriodo.porCodigo(codigoEspecifico);
            System.out.println();
            periodo.imprimir();
        } catch (PeriodoNullException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Periodo buscarGetPeriodo() {
        System.out.println("Busqueda de Periodo ");
        leer.nextLine();
        System.out.print("Codigo Especifico: ");
        int codigoEspecifico = leer.nextInt();

        try {
            return servicePeriodo.porCodigo(codigoEspecifico);

        } catch (PeriodoNullException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void crearPeriodo() {
        leer.nextLine();
        System.out.print("Codigo Especifico: ");
        int codigoEspecifico = leer.nextInt();
        System.out.print("Año: ");
        int año = leer.nextInt();
        Year year = Year.of(año);
        System.out.print("Semestre: ");
        int semestre = leer.nextInt();
        Periodo periodo = new Periodo(codigoEspecifico, year, semestre);
        servicePeriodo.crear(periodo);
    }

    public static void listarPeriodo() {
        System.out.println("Lista de Periodos");
        for (Periodo periodo : servicePeriodo.listar()) {
            periodo.imprimir();
            System.out.println();
        }
    }

    public static void modificarPeriodo() {

        Periodo periodoActual = buscarGetPeriodo();

        if (periodoActual != null) {
            System.out.println();
            periodoActual.imprimir();

            System.out.println("Modificar codigo: si o no? ");
            String opcion = leer.nextLine();
            if (opcion.equalsIgnoreCase("si")) {
                System.out.println("Codigo Especifico: ");
                int nuevoCodigo = leer.nextInt();
                leer.nextLine(); 
                periodoActual.setCodigoEspecifico(nuevoCodigo);
            }

            System.out.println("Modificar año: si o no? ");
            opcion = leer.nextLine(); 
            if (opcion.equalsIgnoreCase("si")) {
                System.out.println("Año: ");
                int nuevoAño = leer.nextInt();
                leer.nextLine(); 
                Year year = Year.of(nuevoAño);
                periodoActual.setAño(year);
            }

            System.out.println("Modificar semestre: si o no? ");
            opcion = leer.nextLine();

            if (opcion.equalsIgnoreCase("si")) {
                System.out.println("Semestre: ");
                int nuevoSemestre = leer.nextInt();
                periodoActual.setSemestre(nuevoSemestre);
            }
            servicePeriodo.editar(periodoActual);

        }

    }

    public static void eliminarPeriodo() {
        Periodo periodo = buscarGetPeriodo();
        if (periodo != null) {
            servicePeriodo.eliminar(periodo);
            System.out.println("Elmininado el periodo con exito");
        } else {
            System.out.println("Se presentó un problema y no se puedo eliminar el periodo");
        }

    }
}
