import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistrarUsuario registro = new RegistrarUsuario();
        List<Usuarios> listaUsuarios = new ArrayList<>();

        Usuarios usuarios = new Usuarios("admin","istrador","c",4,"a123");
        listaUsuarios.add(usuarios);

        int opcion = 0;

        do {

            try {
                System.out.println("""
                    Bienvenido al registro de avance de obra
                    indique la accion que desea realizar:
                    1. Registrarse
                    2. Ingresar
                    3. Salir""");

                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("Bienvenido al sistema, procederemos a registrarlo");
                        Usuarios nuevoUsuario = registro.RegistrarNuevoUsuario(scanner);
                        listaUsuarios.add(nuevoUsuario);
                        System.out.println("Usuario registrado con exito\n");
                        break;
                    case 2:
                        Ingresar(scanner, listaUsuarios);
                        break;
                    case 3:
                        System.out.println("Gracias por usar el Sistema");
                        break;
                }
            }catch (Exception e) {
                System.out.println("Ingrese una accion valida");
            }
        }while (opcion != 3);
        scanner.close();
    }

    private static void Ingresar(Scanner scanner, List<Usuarios> listaUsuarios) {
        System.out.println("Ingrese su correo");
        String correoIngreso = scanner.nextLine();

        System.out.println("Ingrese su password");
        String passwordIngreso = scanner.nextLine();

        boolean loginExitoso = false;

        for (Usuarios u : listaUsuarios) {
            if (u.getCorreo().equals(correoIngreso) && u.getPassword().equals(passwordIngreso)) {
                System.out.println("Bienvenido " + u.getNombre() + " " + u.getApellido());
                loginExitoso = true;
                break;
            }
        }

        if (!loginExitoso) {
            System.out.println("Correo o contraseña incorrectos. Por favor, intente de nuevo.");
        }
        if (loginExitoso){
            Sesion sesion= new Sesion(scanner);
        }
    }
}
