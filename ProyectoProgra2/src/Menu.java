import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("""
            Bienvenido al registro de avance de obra:
            indique la accion que desea realizar
    
            1. Registrarse
            2. Ingresar
            3. Salir
        """);

    int opcion = scanner.nextInt();
        try {
            switch (opcion){
                case 1: Registrarse(); break;
                case 2: Ingresar(); break;
                case 3: Salir(); break;
            }
        } catch (Exception e) {
            System.out.println("Ingrese una accion valida");
        }
    }

    private static void Registrarse(){
        System.out.println("Bienvenido al sistema, procederemos a registrarlo");
        RegistrarNuevoUsuario.RegistrarNuevoUsuario();
    }

    private static void Ingresar(){
        System.out.println("Ingrese su nombre de usuario");
        String nUsuario = scanner.nextLine();
        System.out.println("Ingrese su password");
        String password = scanner.nextLine();




    }

    private static void Salir(){
        System.out.println("Gracias por usar el sistema de registro de avance de obra," +
                "\nvuelva pronto.");
    }
}
