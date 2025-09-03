import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistrarNuevoUsuario {
    private List<Usuarios> usuarios = new ArrayList<>();

    public static void RegistrarNuevoUsuario() {

        Scanner scanner =new Scanner(System.in);
        System.out.println("Ingrese su nombre");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su apellido");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese su correo");
        String correo = scanner.nextLine();

        System.out.println("Ingrese su RNI");
        int rni = scanner.nextInt();

        System.out.println("Establezca una contraseña para su cuenta");
        String password = scanner.nextLine();
    }
}
