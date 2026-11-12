import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        String texto = "";
        boolean valido = false;

        while (!valido) {
            texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                mostrarMensaje("Este dato no puede estar vacío -_- ingrésalo nuvamente: ");
    
            } else {
                valido = true;
            }
        }
        return texto;
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

    public LocalDate leerFecha() {
        LocalDate fecha = null;
        boolean valida = false;

        while (!valida) {
            mostrarMensaje("Ingrese la fecha (AAAA-MM-DD): ");
            
            try {
                fecha = LocalDate.parse(sc.nextLine().trim());
                valida = true;
            } catch (DateTimeParseException e) {
                mostrarMensaje("Fecha inválida -_- ingresala nuevamente con el formato AAAA-MM-DD y que sea una fecha existente");
            }
        } 
        return fecha;
    }

    public int leerHora() {
        int hora = -1;
        boolean valida = false;

        while (!valida) {
            System.out.print("Ingrese la hora (0 a 23): ");
            hora = leerEntero();

            if (hora >= 0 && hora <= 23) {
                valida = true;
            } else {
                mostrarMensaje("La hora debe estar entre 0 y 23 -_-");
            }
        }

        return hora;
    }
}