public class Main {
    public static void main(String[] args) {
        Copa copa = new Copa();
        VistaArena vista = new VistaArena();
        ControladorArena controlador = new ControladorArena(copa, vista);

        controlador.iniciar();
    }
}
