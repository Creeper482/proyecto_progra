import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RegistroAvance {
    private static List<Items> listaItems = new ArrayList<>();
    private static List<Avance> listaAvances = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public RegistroAvance() {

        listaItems.add(new Items("Muro", 150.0, "m2"));
        listaItems.add(new Items("Revoque", 50.0, "m2"));
        listaItems.add(new Items("Pintura", 30.0, "m2"));
    }

    public void menuAvance(){
        int opcion;

        do {
            System.out.println("\n--- MENÚ DE REGISTRO DE AVANCE ---");
            System.out.println("1. Agregar Item");
            System.out.println("2. Avanzar Item");
            System.out.println("3. Ver avance");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarItem();
                    break;
                case 2:
                    avanzarItem();
                    break;
                case 3:
                    verAvance();
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    private static void mostrarItems() {
        System.out.println("\n--- Lista de Items ---");
        for (Items item : listaItems) {
            System.out.println(item);
        }
    }

    private static void agregarItem() {
        mostrarItems();
        System.out.println("\n--- Agregar Nuevo Item ---");
        System.out.print("Nombre del Item: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio Unitario: ");
        double precio = scanner.nextDouble();
        System.out.print("Unidades: ");
        String unidades = scanner.nextLine();
        scanner.nextLine();

        Items nuevoItem = new Items(nombre, precio, unidades);
        listaItems.add(nuevoItem);
        System.out.println("✔️ Item agregado con éxito.");
    }

    private static void avanzarItem() {
        mostrarItems();
        System.out.print("\nIngrese el ID del item a avanzar: ");
        int idBuscado = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Items itemSeleccionado = null;
        for (Items item : listaItems) {
            if (item.getId() == idBuscado) {
                itemSeleccionado = item;
                break;
            }
        }

        if (itemSeleccionado == null) {
            System.out.println("❌ Item no encontrado.");
            return;
        }

        System.out.println("✔️ Item seleccionado: " + itemSeleccionado.getNombreItem());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicio = null;
        Date fechaFin = null;

        try {
            System.out.print("Ingrese fecha de inicio (dd/MM/yyyy): ");
            String fechaInicioStr = scanner.nextLine();
            fechaInicio = sdf.parse(fechaInicioStr);

            System.out.print("Ingrese fecha de fin (dd/MM/yyyy): ");
            String fechaFinStr = scanner.nextLine();
            fechaFin = sdf.parse(fechaFinStr);
        } catch (ParseException e) {
            System.out.println("❌ Formato de fecha incorrecto. Debe ser dd/MM/yyyy");
            return;
        }

        System.out.print("Ingrese cantidad de avance: ");
        double cantidad = scanner.nextDouble();
        scanner.nextLine();

        Avance avance = new Avance(fechaInicio, fechaFin, itemSeleccionado, cantidad);
        listaAvances.add(avance);

        System.out.println("✔️ Avance registrado:");
        System.out.println(avance);
    }

    private static void verAvance() {
        if (listaItems.isEmpty()) {
            System.out.println("⚠️ No hay ítems registrados.");
            return;
        }

        mostrarItems();
        System.out.print("\nIngrese el ID del item para ver su avance: ");
        int idBuscado = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Items itemSeleccionado = null;
        for (Items item : listaItems) {
            if (item.getId() == idBuscado) {
                itemSeleccionado = item;
                break;
            }
        }

        if (itemSeleccionado == null) {
            System.out.println("❌ Item no encontrado.");
            return;
        }

        System.out.println("📋 Avances del item: " + itemSeleccionado.getNombreItem());
        boolean encontrado = false;

        for (Avance avance : listaAvances) {
            if (avance.getItemAvanzado().getId() == idBuscado) {
                System.out.println(avance);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("⚠️ No hay avances registrados para este ítem.");
        }
    }
}


