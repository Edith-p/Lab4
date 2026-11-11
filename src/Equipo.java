public class Equipo implements Comparable<Equipo> {
    private final String codigo; 
    private final String nombre; 
    private final String empresa; 
    private int puntos = 0; 


    public Equipo (String codigo, String nombre, String empresa){
        this.codigo = codigo; 
        this.nombre =nombre; 
        this.empresa= empresa; 

        
    }

    public String getCodigo(){
        return codigo; 
    }
    
    public String getNombre(){
        return nombre; 

    }

    public String getEmpresa(){
        return empresa; 
    }

    public int getPuntosTabla(){
        return puntos; 
    }

    public void sumarPuntos(int cantidad){
        if (cantidad<0){
            throw new IllegalArgumentException("LOs puntos deben ser siempre positivos -_-"); 

        }
        puntos +=cantidad; 
        
    }

    @Override 
    public int compareTo(Equipo otro){
        int comparacion = Integer.compare(otro.getPuntosTabla(), puntos); 

        if (comparacion !=0){
            return comparacion; 
        }
        return nombre.compareTo(otro.getNombre()); 


    }
}

