import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorArena {
    private final Copa copa;
    private final VistaArena vista;

    public ControladorArena(Copa copa, VistaArena vista) {
        this.copa = copa;
        this.vista = vista;
    }

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            vista.mostrarMenu();
            int opcion = vista.leerEntero();

            try {
                switch (opcion) {
                    case 1:
                        registrarEquipo();
                        break;
                    case 2:
                        crearEncuentro();
                        break;
                    case 3:
                        crearExhibicion();
                        break;
                    case 4:
                        programarEvento();
                        break;
                    case 5:
                        registrarResultado();
                        break;
                    case 6:
                        mostrarTabla();
                        break;
                    case 7:
                        mostrarCalendario();
                        break;
                    case 0:
                        continuar = false;
                        vista.mostrarMensaje("Programa finalizado.");
                        break;
                    default:
                        vista.mostrarMensaje("Opción inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                vista.mostrarMensaje(e.getMessage());
            }
        }
    }

    private void registrarEquipo() {
        String codigo = "";
        boolean valido = false;

        while (!valido) {
            vista.mostrarMensaje("Ingrese el codigo del equipo:");
            codigo = vista.leerTexto();

            if (copa.buscarEquipo(codigo) != null) {
                vista.mostrarMensaje("Ese codigo ya está registrado :(");
            } else {
                valido = true;
            }
        }

        vista.mostrarMensaje("Ingrese el nombre del equipo:");
        String nombre = vista.leerTexto();
        vista.mostrarMensaje("Ingrese la empresa:");
        String empresa = vista.leerTexto();

        copa.registrarEquipo(new Equipo(codigo, nombre, empresa));
        vista.mostrarMensaje("Equipo registrado correctamente :)");
    }

    private void crearEncuentro() {
        if (copa.obtenerTabla().size() < 2) {
            vista.mostrarMensaje("Debe registrar al menos dos equipos.");
            return;
        }

        int deporte = 0;
        boolean valido = false;

        while (!valido) {
            vista.mostrarMensaje("Seleccione el deporte: 1. Fútbol sala | 2. Baloncesto");
            deporte = vista.leerEntero();
            valido = deporte == 1 || deporte == 2;

            if (!valido) {
                vista.mostrarMensaje("Seleccione 1 o 2.");
            }
        }

        mostrarEquipos();
        Equipo equipoA = solicitarEquipo("Ingrese el código del primer equipo:");
        Equipo equipoB = null;
        boolean distintos = false;

        while (!distintos) {
            equipoB = solicitarEquipo("Ingrese el código del segundo equipo:");
            distintos = !equipoA.getCodigo().trim().equalsIgnoreCase(equipoB.getCodigo().trim());

            if (!distintos) {
                vista.mostrarMensaje("Un equipo no puede enfrentarse a sí mismo.");
            }
        }

        Programable encuentro;
        if (deporte == 1) {
            encuentro = new FutbolSala(equipoA, equipoB);
        } else {
            encuentro = new Baloncesto(equipoA, equipoB);
        }

        copa.agregarEvento(encuentro);
        vista.mostrarMensaje("Encuentro creado correctamente.");
    }

    private Equipo solicitarEquipo(String mensaje) {
        Equipo equipo = null;

        while (equipo == null) {
            vista.mostrarMensaje(mensaje);
            equipo = copa.buscarEquipo(vista.leerTexto());

            if (equipo == null) {
                vista.mostrarMensaje("No existe un equipo con ese código.");
            }
        }

        return equipo;
    }

    private void mostrarEquipos() {
        for (Equipo equipo : copa.obtenerTabla()) {
            vista.mostrarMensaje(equipo.getCodigo() + " - " + equipo.getNombre());
        }
    }

    private void crearExhibicion() {
        vista.mostrarMensaje("Ingrese el título de la exhibición:");
        String titulo = vista.leerTexto();
        copa.agregarEvento(new Exhibicion(titulo));
        vista.mostrarMensaje("Exhibición creada correctamente.");
    }

    private void programarEvento() {
        List<Programable> disponibles = new ArrayList<>();

        for (Programable evento : copa.obtenerEventos()) {
            if (evento.getEstado() != EstadoEvento.FINALIZADO) {
                disponibles.add(evento);
            }
        }

        if (disponibles.isEmpty()) {
            vista.mostrarMensaje("No hay eventos disponibles para programar.");
            return;
        }

        Programable evento = seleccionarEvento(disponibles);
        boolean programado = false;

        while (!programado) {
            LocalDate fecha = vista.leerFecha();
            int hora = vista.leerHora();
            vista.mostrarMensaje("Ingrese la sede:");
            String sede = vista.leerTexto();

            try {
                copa.programarEvento(evento, fecha, hora, sede);
                programado = true;
                vista.mostrarMensaje("Evento programado correctamente.");
            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje(e.getMessage());
                vista.mostrarMensaje("Ingrese nuevamente la fecha, hora y sede.");
            }
        }
    }

    private Programable seleccionarEvento(List<Programable> disponibles) {
        for (int i = 0; i < disponibles.size(); i++) {
            Programable evento = disponibles.get(i);
            vista.mostrarMensaje((i + 1) + ". " + evento.getDescripcion() + " | " + evento.getEstado());
        }

        int opcion = 0;
        boolean valida = false;

        while (!valida) {
            vista.mostrarMensaje("Seleccione el número del evento:");
            opcion = vista.leerEntero();
            valida = opcion >= 1 && opcion <= disponibles.size();

            if (!valida) {
                vista.mostrarMensaje("Seleccione un número de la lista.");
            }
        }

        return disponibles.get(opcion - 1);
    }

    private void registrarResultado() {
        List<Programable> disponibles = new ArrayList<>();

        for (Programable evento : copa.obtenerEventos()) {
            if (evento instanceof Puntuable && evento.getEstado() == EstadoEvento.PROGRAMADO) {
                disponibles.add(evento);
            }
        }

        if (disponibles.isEmpty()) {
            vista.mostrarMensaje("No hay encuentros programados pendientes de resultado.");
            return;
        }

        Programable evento = seleccionarEvento(disponibles);
        Puntuable encuentro = (Puntuable) evento;
        boolean registrado = false;

        while (!registrado) {
            int marcadorA = leerMarcador("Ingrese el marcador del equipo A:");
            int marcadorB = leerMarcador("Ingrese el marcador del equipo B:");

            try {
                encuentro.registrarResultado(marcadorA, marcadorB);
                registrado = true;
                vista.mostrarMensaje("Resultado registrado correctamente.");
            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje(e.getMessage());
                vista.mostrarMensaje("Ingrese nuevamente los marcadores.");
            }
        }
    }

    private int leerMarcador(String mensaje) {
        int marcador = -1;

        while (marcador < 0) {
            vista.mostrarMensaje(mensaje);
            marcador = vista.leerEntero();

            if (marcador < 0) {
                vista.mostrarMensaje("El marcador no puede ser negativo.");
            }
        }

        return marcador;
    }

    private void mostrarTabla() {
        List<Equipo> tabla = copa.obtenerTabla();

        if (tabla.isEmpty()) {
            vista.mostrarMensaje("No hay equipos registrados.");
            return;
        }

        vista.mostrarMensaje("""
            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                    TABLA DE POSICIONES
            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
            """);
        for (int i = 0; i < tabla.size(); i++) {
            Equipo equipo = tabla.get(i);
            vista.mostrarMensaje((i + 1) + ". " + equipo.getNombre() + " | Puntos: " + equipo.getPuntosTabla());
        }
    }

    private void mostrarCalendario() {
        boolean hayEventos = false;
        vista.mostrarMensaje("""
            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                       CALENDARIO
            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
            """);

        for (Programable evento : copa) {
            vista.mostrarMensaje(evento.getFecha() + " | Hora: " + evento.getHora() + " | Sede: " + evento.getSede() + " | " + evento.getDescripcion());
            hayEventos = true;
        }

        if (!hayEventos) {
            vista.mostrarMensaje("No hay eventos con fecha asignada.");
        }
    }
}
