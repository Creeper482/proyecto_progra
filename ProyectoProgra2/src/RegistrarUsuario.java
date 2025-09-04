import java.util.Scanner;

public class RegistrarUsuario {

    public Usuarios RegistrarNuevoUsuario(Scanner scanner) {

        System.out.println("Ingrese su nombre");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su apellido");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese su correo");
        String correo = scanner.nextLine();

        System.out.println("Ingrese su RNI");
        int rni = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Establezca una contraseña para su cuenta");
        String password = scanner.nextLine();

        return new Usuarios(nombre, apellido, correo, rni, password);
    }

}

