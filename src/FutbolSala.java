

public class FutbolSala extends Encuentro{
    public FutbolSala(Equipo equipoA, Equipo equipoB){
        super(equipoA, equipoB); 
    }
//No se coloca nada aqui por las validaciones que se hacen en encuentro 
//y poorque este deporte permite que haya empates
    @Override 
    protected void validarMarcadorDeporte(int marcadorA, int marcadorB){

    }

    @Override 
    protected int calcularPuntos(int propio, int rival){
        if (propio > rival){
            return 3; 
        }
        if (propio == rival){
            return 1; 
        }
        return 0; 
    }
    
    @Override

    public String getDescripcion(){
        Equipo equipoA = getEquipoA(); 
        Equipo equipoB = getEquipoB(); 
        return "Futbol sala: " + equipoA.getNombre() + "VS" + equipoB.getNombre(); 
    }
}
