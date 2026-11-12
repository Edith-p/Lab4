public class Baloncesto extends Encuentro{

    public Baloncesto(Equipo equipoA, Equipo equipoB){
        super(equipoA, equipoB);
    }
    @Override 
    protected void validarMarcadorDeporte(int marcadorA, int marcadorB){
        if (marcadorA == marcadorB){
            throw new IllegalArgumentException("En baloncesto no se permiten los empates 0.="); 
        }


    }

    @Override
    protected int calcularPuntos(int propio, int rival) {
        if (propio > rival){
            return 2; 
        }
    
            return 1; 
        }

    @Override 
    public String getDescripcion(){

        Equipo equipoA = getEquipoA(); 
        Equipo equipoB = getEquipoB(); 
        return "Baloncesto: " + equipoA.getNombre() + "---VS---" + equipoB.getNombre(); 
    }

}
