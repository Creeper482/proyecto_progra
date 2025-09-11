import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;

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

    private static Proyectos proyecto;
    List<Proyectos> listaProyectos = new ArrayList<>();
    Proyectos proyectos= new Proyectos("Red Tower", "ConstructoraUPB", "proy1","aqui","Andrew", 4,"cuce123");

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

            // Estilos
            CellStyle negrita = workbook.createCellStyle();
            Font fontBold = workbook.createFont();
            fontBold.setBold(true);
            negrita.setFont(fontBold);
            negrita.setAlignment(HorizontalAlignment.LEFT);

            CellStyle fechaStyle = workbook.createCellStyle();
            CreationHelper helper = workbook.getCreationHelper();
            fechaStyle.setDataFormat(helper.createDataFormat().getFormat("dd/MM/yyyy"));

            CellStyle encabezadoStyle = workbook.createCellStyle();
            Font encabezadoFont = workbook.createFont();
            encabezadoFont.setBold(true);
            encabezadoFont.setFontHeightInPoints((short) 11);
            encabezadoStyle.setFont(encabezadoFont);
            encabezadoStyle.setAlignment(HorizontalAlignment.CENTER);
            encabezadoStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            encabezadoStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            encabezadoStyle.setBorderBottom(BorderStyle.THIN);
            encabezadoStyle.setBorderTop(BorderStyle.THIN);
            encabezadoStyle.setBorderLeft(BorderStyle.THIN);
            encabezadoStyle.setBorderRight(BorderStyle.THIN);

            CellStyle dineroStyle = workbook.createCellStyle();
            dineroStyle.setDataFormat(helper.createDataFormat().getFormat("#,##0.00"));

            // B2: Nombre de la empresa
            Row row2 = sheet.createRow(1);
            row2.createCell(1).setCellValue("Nombre de la empresa");

            // B3: Nombre del proyecto
            Row row3 = sheet.createRow(2);
            row3.createCell(1).setCellValue("Nombre del proyecto");

            // B5: "cod:" en negrita
            Row row5 = sheet.createRow(4);
            Cell codCell = row5.createCell(1);
            codCell.setCellValue("cod:");
            codCell.setCellStyle(negrita);

            // C4: Código del proyecto
            Row row4 = sheet.getRow(3);
            if (row4 == null) row4 = sheet.createRow(3);
            row4.createCell(2).setCellValue("Codigo del proyecto");

            // B6 y C6: Fecha de inicio
            Row row6 = sheet.createRow(5);
            Cell fiLabel = row6.createCell(1);
            fiLabel.setCellValue("Fecha de Inicio:");
            fiLabel.setCellStyle(negrita);
            Cell fiValue = row6.createCell(2);
            fiValue.setCellValue(fechaInicio);
            fiValue.setCellStyle(fechaStyle);

            // B7 y C7: Fecha final
            Row row7 = sheet.createRow(6);
            Cell ffLabel = row7.createCell(1);
            ffLabel.setCellValue("Fecha Final:");
            ffLabel.setCellStyle(negrita);
            Cell ffValue = row7.createCell(2);
            ffValue.setCellValue(fechaFin);
            ffValue.setCellStyle(fechaStyle);

            // Fila 9 (índice 8): Encabezados desde columna B (índice 1)
            Row headerRow = sheet.createRow(8);
            String[] headers = {"N°", "Item", "und", "Cantidad", "Precio Unitario", "Costo"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i + 1);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(encabezadoStyle);
            }

            // Fila 10 en adelante: Datos
            int rowIndex = 9;
            int contador = 1;
            double totalCosto = 0.0;

            for (Avance avance : avancesFiltrados) {
                Row row = sheet.createRow(rowIndex++);
                double cantidad = avance.getCantidad();
                double precio = avance.getItemAvanzado().getPU();
                double costo = cantidad * precio;
                totalCosto += costo;

                row.createCell(1).setCellValue(contador++);
                row.createCell(2).setCellValue(avance.getItemAvanzado().getNombreItem());
                row.createCell(3).setCellValue(avance.getItemAvanzado().getUnidades());
                row.createCell(4).setCellValue(cantidad);

                Cell precioCell = row.createCell(5);
                precioCell.setCellValue(precio);
                precioCell.setCellStyle(dineroStyle);

                Cell costoCell = row.createCell(6);
                costoCell.setCellValue(costo);
                costoCell.setCellStyle(dineroStyle);
            }

            // Fila vacía
            rowIndex++;

            // Fila Total
            Row totalRow = sheet.createRow(rowIndex++);
            Cell labelTotal = totalRow.createCell(1);
            labelTotal.setCellValue("Costo Total del Periodo");
            labelTotal.setCellStyle(negrita);

            Cell totalValor = totalRow.createCell(6);
            totalValor.setCellValue(totalCosto);
            totalValor.setCellStyle(dineroStyle);

            Cell moneda = totalRow.createCell(7);
            moneda.setCellValue("Bs.");

            // Autoajuste de columnas
            for (int i = 1; i <= 7; i++) {
                sheet.autoSizeColumn(i);
            }

            // Guardar archivo
            String fileName = "Reporte_Avances_" + System.currentTimeMillis() + ".xlsx";
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }
            workbook.close();

            System.out.println("📁 Reporte generado exitosamente: " + fileName);

        } catch (Exception e) {
            System.out.println("❌ Error al generar el reporte: " + e.getMessage());
        }
    }
}

}
