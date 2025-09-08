import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sesion {
    public Sesion (Scanner scanner){
        CrearProyecto creado = new CrearProyecto();
        RegistroAvance registrado = new RegistroAvance();
        List<Proyectos> listaProyectos = new ArrayList<>();

        Proyectos proyectos= new Proyectos("Red Tower", "ConstructoraUPB", "proy1","aqui","Andrew", 4,"cuce123");
        listaProyectos.add(proyectos);

        int opcion=0;

        do {
            try{
                System.out.println("""
                        Indique la accion que desea realizar:
                        1. Crear Nuevo Proyecto
                        2. Registrar avance de un Proyecto
                        3. Eliminar un Proyecto
                        4. Salir""");
                opcion=Integer.parseInt(scanner.nextLine());

                switch (opcion){
                    case 1:
                        System.out.println("Vamos a crear un nuevo proyecto");
                        Proyectos nuevoProyecto = creado.CrearProyecto(scanner);
                        listaProyectos.add(nuevoProyecto);
                        System.out.println("Proyecto creado con exito\n");
                        break;
                    case 2:
                        System.out.println("Vamos a registrar el avance de un proyecto");
                        registrado.menuAvance();
                        break;
                    case 3:
                        System.out.println("Vamos a eliminar un proyecto"); break;
                    case 4:
                        System.out.println("Gracias por venir"); break;
                }
            }catch (Exception e) {
                System.out.println("Ingrese una accion valida");
            }
        }while (opcion!=4);
    }
}


