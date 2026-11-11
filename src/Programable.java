import java.time.LocalDate;

public interface Programable extends Comparable<Programable>{
    void programar(LocalDate fecha, int hora, String sede);
    LocalDate getFecha();
    int getHora();
    String getSede();
    EstadoEvento getEstado();
    String getDescripcion();

    default boolean tieneEquipo(Equipo equipo) {
        return false;
    }

    default int compareTo(Programable otro) {
        if (getFecha() == null && otro.getFecha() == null) {
            return 0;
        }

        if (getFecha() == null) {
            return 1;
        }

        if (otro.getFecha() == null) {
            return -1;
        }

        int comparacion = getFecha().compareTo(otro.getFecha());

        if (comparacion == 0) {
            comparacion = Integer.compare(getHora(), otro.getHora());
        }

        return comparacion;
    }
}
