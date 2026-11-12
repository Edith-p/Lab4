import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Copa implements Iterable<Programable>{
    private final ArrayList<Equipo> equipos;
    private final ArrayList<Programable> eventos;

    public Copa() {
        equipos = new ArrayList<>();
        eventos = new ArrayList<>();
    }
public void registrarEquipo(Equipo equipo) {
        if (equipo == null) {
            throw new IllegalArgumentException("El equipo no puede ser nulo :(");
        }
        if (equipo.getCodigo() == null || equipo.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío :(");
        }
        if (buscarEquipo(equipo.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe un equipo con ese código :(");
        }
        equipos.add(equipo);
    }

    public Equipo buscarEquipo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Equipo equipo : equipos) {
            if (equipo.getCodigo().trim().equalsIgnoreCase(codigo.trim())) {
                return equipo;
            }
        }
        return null;
    }

    public void agregarEvento(Programable evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo :(");
        }
        if (eventos.contains(evento)) {
            throw new IllegalArgumentException("Este evento ya está registrado :(");
        }
        eventos.add(evento);
    }

    public void programarEvento(Programable evento, LocalDate fecha, int hora, String sede) {
        if (evento == null || !eventos.contains(evento)) {
            throw new IllegalArgumentException("El evento debe estar registrado en la copa :(");
        }
        if (evento.getEstado() == EstadoEvento.FINALIZADO) {
            throw new IllegalStateException("No se puede reprogramar un evento finalizado :(");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("Debe indicar una fecha :(");
        }
        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException("La hora debe estar entre 0 y 23 :(");
        }
        if (sede == null || sede.trim().isEmpty()) {
            throw new IllegalArgumentException("La sede no puede estar vacía :(");
        }

        String sedeLimpia = sede.trim();

        for (Programable otro : eventos) {
            if (otro == evento || otro.getFecha() == null) {
                continue;
            }
            if (fecha.equals(otro.getFecha()) && hora == otro.getHora()) {
                if (sedeLimpia.equalsIgnoreCase(otro.getSede())) {
                    throw new IllegalArgumentException("La sede ya está ocupada en esa fecha y hora :(");
                }
                for (Equipo equipo : equipos) {
                    if (evento.tieneEquipo(equipo) && otro.tieneEquipo(equipo)) {
                        throw new IllegalArgumentException("Un equipo ya tiene otro encuentro en esa fecha y hora.");
                    }
                }
            }
        }

        evento.programar(fecha, hora, sedeLimpia);
    }

    public List<Equipo> obtenerTabla() {
        List<Equipo> tabla = new ArrayList<>(equipos);
        Collections.sort(tabla);
        return tabla;
    }

    @Override
    public Iterator<Programable> iterator() {
        List<Programable> calendario = new ArrayList<>();
        for (Programable evento : eventos) {
            if (evento.getFecha() != null) {
                calendario.add(evento);
            }
        }
        Collections.sort(calendario);
        return calendario.iterator();
    }

    public List<Programable> obtenerEventos() {
        return new ArrayList<>(eventos);
    }
}
