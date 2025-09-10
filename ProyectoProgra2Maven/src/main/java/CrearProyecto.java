import java.util.Scanner;

public class CrearProyecto {

    public Proyectos CrearProyecto(Scanner scanner){

        System.out.println("Ingrese el nombre del Proyecto");
        String nombreProyecto= scanner.nextLine();

        System.out.println("Ingrese el nombre de la Empresa");
        String empresa= scanner.nextLine();

        System.out.println("Ingrese el codigo del Proyecto");
        String codProyecto= scanner.nextLine();

        System.out.println("Ingrese la geolocalizacion del Proyecto");
        String geolocalizacion= scanner.nextLine();

        System.out.println("Indique el nombre del Director de Proyecto");
        String directorProyecto= scanner.nextLine();

        System.out.println("Ingrese el RNI del Director");
        int rniDirector= Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el Cuce del Proyecto");
        String cuce = scanner.nextLine();

        return new Proyectos(nombreProyecto, empresa, codProyecto, geolocalizacion, directorProyecto, rniDirector, cuce);
    }
}