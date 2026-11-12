import java.time.LocalDate; 

public abstract class Encuentro implements Programable, Puntuable{
    private final Equipo equipoA; 
    private final Equipo equipoB; 
    private Integer marcadorA; 
    private Integer marcadorB; 
    private EstadoEvento estado; 
    private LocalDate fecha;
    private int hora; 
    private String sede; 


    public Encuentro(Equipo equipoA, Equipo equipoB){
        if (equipoA == null || equipoB == null){
            throw new IllegalArgumentException("Debes ingresar los equipos 0_0");
        }
        if (equipoA.getCodigo().equals(equipoB.getCodigo())) {
            throw new IllegalArgumentException("No se puede enfrentar a sí mismo 0_0");
        }
        this.equipoA=equipoA; 
        this.equipoB = equipoB; 
        this.estado = EstadoEvento.SIN_PROGRAMAR; 
    }
    
    public Equipo getEquipoA(){
        return equipoA; 
    }

    public Equipo getEquipoB(){
        return equipoB; 
    }

    public Integer getMarcadorA(){
        return marcadorA; 
    }
    public Integer getMarcadorB(){
        return marcadorB; 

    }
    public LocalDate getFecha(){
        return fecha; 
    }

    public int getHora(){
        return hora; 
    }
    public String getSede(){
        return sede; 
    }
    public EstadoEvento getEstado(){
        return estado ;
    }

    @Override

    public void  programar(LocalDate fecha, int hora, String sede ){
        if (estado == EstadoEvento.FINALIZADO){
            throw new IllegalStateException("Si un encuentro finalizo, no lo puedes reprogramar 0__O"); 
        }
        if (fecha == null){
            throw new IllegalArgumentException("Debes ingresar un fecha para programar 0_="); 

        }
        if(hora < 0 || hora >23){
            throw new IllegalArgumentException("Para contar como hora, debe estar entre 0 ay 23 0_0"); 
        }
        if (sede == null || sede.isEmpty()){
            throw new IllegalArgumentException("Debes disponer de una sede para poder programar el encuentro 0.0"); 

        }
        this.fecha = fecha;
        this.hora = hora;
        this.sede = sede; 
        estado= EstadoEvento.PROGRAMADO; 
    }
    
    protected abstract void validarMarcadorDeporte(int marcadorA, int marcadorB); 
    protected abstract int calcularPuntos(int propio, int rival); 
    
    @Override 
    public final void registrarResultado(int marcadorA, int marcadorB){
        if(estado != EstadoEvento.PROGRAMADO){
            throw new IllegalStateException("Si no has programado el encuentro, no puedes registrar resultados =-="); 

        }
        if (marcadorA<0 || marcadorB<0){
            throw new IllegalArgumentException("EL marcador no acepta valores negativos o-0"); 
        }
        
        validarMarcadorDeporte(marcadorA, marcadorB);
        int puntosA = calcularPuntos(marcadorA, marcadorB); 
        int puntosB = calcularPuntos(marcadorB, marcadorA); 
        this.marcadorA = marcadorA;
        this.marcadorB = marcadorB;
        equipoA.sumarPuntos(puntosA);
        equipoB.sumarPuntos(puntosB);
        estado = EstadoEvento.FINALIZADO;
                
    }
    @Override 

    public boolean tieneEquipo(Equipo equipo){
        if (equipo == null){
            return false; 
        }


         if (equipoA.getCodigo().equals(equipo.getCodigo())) {
        return true;
        }

        if (equipoB.getCodigo().equals(equipo.getCodigo())) {
            return true;
        }
        return false; 
        }

    @Override 
    public abstract String getDescripcion(); 


}
