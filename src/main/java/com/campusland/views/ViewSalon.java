package com.campusland.views;

import java.util.List;

import com.campusland.repository.models.Edificios;
import com.campusland.repository.models.Salones;

public class ViewSalon extends ViewMain {

    public static void startMenu() {

        int op = 0;

        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearSalon();
                    break;
                case 2:
                    listarSalones();
                    break;
                case 3:
                    // buscarAlumno();
                    break;
                case 4:
                    // modificarAlumno();
                    break;
                case 5:
                    // eliminarAlumno();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (op >= 1 && op < 6);
    }

    private static int mostrarMenu() {
        System.out.println("----Menu--Salones----");
        System.out.println("1. Crear salones.");
        System.out.println("2. Listar salones.");
        System.out.println("3. Buscar salon.");
        System.out.println("4. Modificar salon.");
        System.out.println("5. Eliminar salon.");
        System.out.println("6. Salir ");
        return leer.nextInt();
    }

    public static void crearSalon() {
        leer.nextLine();

        System.out.println("Capacidad de Alumnos: ");
        int capacidadAlumnos = leer.nextInt();

        System.out.println("Numero de Identificacion del salon: ");
        int numeroIdentificacion = leer.nextInt();

        System.out.println("Edificios");
        List<Edificios> edificios = serviceEdificio.listar();
        for (Edificios edificio : edificios) {
            System.out.println(edificio.getIdEdificios() + ". " + edificio.getNumeroEdificio());
        }
        System.out.println((edificios.size() + 1) + ". Agregar nuevo programa");

        System.out.println("Ingrese el número correspondiente al Edificio: ");
        int opcionEdificio = leer.nextInt();

        Edificios edificioSeleccionado = null;
        if (opcionEdificio >= 1 && opcionEdificio <= edificios.size()) {
            edificioSeleccionado = edificios.get(opcionEdificio - 1);
        } else {
            System.out.println("Opción inválida. Volviendo al menú principal.");
            return; 
        }

        int edificioId = edificioSeleccionado.getIdEdificios();

        Salones nuevoSalon = new Salones(capacidadAlumnos, numeroIdentificacion, edificioId);

        serviceSalon.crear(nuevoSalon);
        System.out.println("Salon Asignado Correctamente");
    }

    public static void listarSalones(){
        System.out.println("Lista de Salones");
        for (Salones salon : serviceSalon.listar()) {
            System.out.println("Numero de Identificacion del salon:" + salon.getIdSalones());
            System.out.println("Capacidad de Alumnos: " + salon.getCapacidadAlumnos());
        }
    }

}
