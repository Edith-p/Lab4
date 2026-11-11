import java.util.Scanner;

public class VistaArena {
    private Scanner sc;
    
    public VistaArena() {
        sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("""
            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                        MENÚ ARENA
            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
            1. Registrar equipo
            2. Crear encuentro
            3. Crear exhibición
            4. Programar o reprogramar evento
            5. Registrar resultado
            6. Mostrar tabla de posiciones
            7. Mostrar calendario
            0. Salir
            """);
    }

    public String leerTexto() {
        return sc.nextLine().trim();
    }

    public int leerEntero () {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            try {
                numero = Integer.parseInt(sc.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida :/ Ingresa un número entero");
            }
        }
        return numero;
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}