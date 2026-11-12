import java.time.LocalDate;

public class Exhibicion implements Programable{

    private final String titulo;
    private EstadoEvento estado;
    private LocalDate fecha;
    private int hora;
    private String sede;

    public Exhibicion(String titulo) {
        if (titulo == null) {
            throw new IllegalArgumentException("El título no puede estar vacío :/");
        }

        this.titulo = titulo.trim();
        this.estado = EstadoEvento.SIN_PROGRAMAR;
        this.fecha = null;
        this.hora = -1;
        this.sede = null;
    }
    
    public String getTitulo() {
        return titulo;
    }

    @Override
    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public int getHora() {
        return hora;
    }

    @Override
    public String getSede() {
        return sede;
    }

    @Override
    public EstadoEvento getEstado() {
        return estado;
    }

    @Override 
    public void programar(LocalDate fecha, int hora, String sede) {
        if (fecha == null) {
            throw new IllegalArgumentException("Debe indicar una fecha.");
        }

        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException("La hora debe estar entre 0 y 23.");
        }

        if (sede == null || sede.trim().isEmpty()) {
            throw new IllegalArgumentException("La sede no puede estar vacía.");
        }
    }

    @Override
    public String getDescripcion() {
        return "Exhibición: " + titulo;
    }
}
