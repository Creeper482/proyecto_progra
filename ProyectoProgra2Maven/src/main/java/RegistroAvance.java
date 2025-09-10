import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;

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
            System.out.println("4. Generar");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion=Integer.parseInt(scanner.nextLine());

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
                    generar();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private static void mostrarItems() {
        System.out.println("\n--- Lista de main.java.Items ---");
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
        scanner.nextLine();

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

        System.out.println("✔️ main.java.Avance registrado:");
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

    private static void generar() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\n--- Generar Reporte Excel de Avances ---");

        try {
            System.out.print("Ingrese fecha de inicio (dd/MM/yyyy): ");
            Date fechaInicio = sdf.parse(scanner.nextLine());

            System.out.print("Ingrese fecha de fin (dd/MM/yyyy): ");
            Date fechaFin = sdf.parse(scanner.nextLine());

            List<Avance> avancesFiltrados = listaAvances.stream()
                    .filter(a -> !a.getFechaInicio().before(fechaInicio) && !a.getFechaFin().after(fechaFin))
                    .collect(Collectors.toList());

            if (avancesFiltrados.isEmpty()) {
                System.out.println("⚠️ No hay avances en ese periodo.");
                return;
            }

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Avances");

            // Encabezados
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Item");
            header.createCell(1).setCellValue("Cantidad");
            header.createCell(2).setCellValue("Unidad");
            header.createCell(3).setCellValue("Fecha Inicio");
            header.createCell(4).setCellValue("Fecha Fin");

            int rowNum = 1;
            for (Avance avance : avancesFiltrados) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(avance.getItemAvanzado().getNombreItem());
                row.createCell(1).setCellValue(avance.getCantidad());
                row.createCell(2).setCellValue(avance.getItemAvanzado().getUnidades());
                row.createCell(3).setCellValue(sdf.format(avance.getFechaInicio()));
                row.createCell(4).setCellValue(sdf.format(avance.getFechaFin()));
            }

            // Autoajuste de columnas
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            String fileName = "Reporte_Avances_" + System.currentTimeMillis() + ".xlsx";
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            System.out.println("📁 Reporte generado exitosamente: " + fileName);

        } catch (Exception e) {
            System.out.println("❌ Error al generar el reporte: " + e.getMessage());
        }
    }
}