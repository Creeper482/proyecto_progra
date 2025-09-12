import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
            System.out.println("2. Registrar Avance de Item");
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

            CreationHelper helper = workbook.getCreationHelper();

            // === ESTILOS ===
            Font fontBold = workbook.createFont();
            fontBold.setBold(true);

            CellStyle negrita = workbook.createCellStyle();
            negrita.setFont(fontBold);
            negrita.setAlignment(HorizontalAlignment.LEFT);

            CellStyle fechaStyle = workbook.createCellStyle();
            fechaStyle.setDataFormat(helper.createDataFormat().getFormat("dd/MM/yyyy"));

            Font encabezadoFont = workbook.createFont();
            encabezadoFont.setBold(true);
            encabezadoFont.setFontHeightInPoints((short) 11);

            CellStyle encabezadoStyle = workbook.createCellStyle();
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

            CellStyle bordeDelgadoCentrado = workbook.createCellStyle();
            Font font14 = workbook.createFont();
            font14.setBold(true);
            font14.setFontHeightInPoints((short) 14);
            bordeDelgadoCentrado.setFont(font14);
            bordeDelgadoCentrado.setAlignment(HorizontalAlignment.CENTER);
            bordeDelgadoCentrado.setVerticalAlignment(VerticalAlignment.CENTER);
            bordeDelgadoCentrado.setBorderTop(BorderStyle.THIN);
            bordeDelgadoCentrado.setBorderBottom(BorderStyle.THIN);
            bordeDelgadoCentrado.setBorderLeft(BorderStyle.THIN);
            bordeDelgadoCentrado.setBorderRight(BorderStyle.THIN);

            CellStyle bordeGruesoCentrado = workbook.createCellStyle();
            Font font16 = workbook.createFont();
            font16.setBold(true);
            font16.setFontHeightInPoints((short) 16);
            bordeGruesoCentrado.setFont(font16);
            bordeGruesoCentrado.setAlignment(HorizontalAlignment.CENTER);
            bordeGruesoCentrado.setVerticalAlignment(VerticalAlignment.CENTER);
            bordeGruesoCentrado.setBorderTop(BorderStyle.MEDIUM);
            bordeGruesoCentrado.setBorderBottom(BorderStyle.MEDIUM);
            bordeGruesoCentrado.setBorderLeft(BorderStyle.MEDIUM);
            bordeGruesoCentrado.setBorderRight(BorderStyle.MEDIUM);

            CellStyle bordeDelgado = workbook.createCellStyle();
            bordeDelgado.setBorderTop(BorderStyle.THIN);
            bordeDelgado.setBorderBottom(BorderStyle.THIN);
            bordeDelgado.setBorderLeft(BorderStyle.THIN);
            bordeDelgado.setBorderRight(BorderStyle.THIN);

            CellStyle bordeGrueso = workbook.createCellStyle();
            bordeGrueso.setBorderTop(BorderStyle.MEDIUM);
            bordeGrueso.setBorderBottom(BorderStyle.MEDIUM);
            bordeGrueso.setBorderLeft(BorderStyle.MEDIUM);
            bordeGrueso.setBorderRight(BorderStyle.MEDIUM);

            // === ESTILOS PERSONALIZADOS POR CELDA ===
            CellStyle bordeSuperiorDelgado = workbook.createCellStyle();
            bordeSuperiorDelgado.setBorderTop(BorderStyle.THIN);

            CellStyle bordeSuperiorIzquierdo = workbook.createCellStyle();
            bordeSuperiorIzquierdo.setBorderTop(BorderStyle.THIN);
            bordeSuperiorIzquierdo.setBorderLeft(BorderStyle.THIN);

            CellStyle bordeSuperiorDerecho = workbook.createCellStyle();
            bordeSuperiorDerecho.setBorderTop(BorderStyle.THIN);
            bordeSuperiorDerecho.setBorderRight(BorderStyle.THIN);

            CellStyle bordeGruesoTopBottom = workbook.createCellStyle();
            bordeGruesoTopBottom.setBorderTop(BorderStyle.MEDIUM);
            bordeGruesoTopBottom.setBorderBottom(BorderStyle.MEDIUM);

            CellStyle bordeGruesoIzquierdo = workbook.createCellStyle();
            bordeGruesoIzquierdo.setBorderTop(BorderStyle.MEDIUM);
            bordeGruesoIzquierdo.setBorderBottom(BorderStyle.MEDIUM);
            bordeGruesoIzquierdo.setBorderLeft(BorderStyle.MEDIUM);

            CellStyle bordeGruesoDerecho = workbook.createCellStyle();
            bordeGruesoDerecho.setBorderTop(BorderStyle.MEDIUM);
            bordeGruesoDerecho.setBorderBottom(BorderStyle.MEDIUM);
            bordeGruesoDerecho.setBorderRight(BorderStyle.MEDIUM);

            // === ENCABEZADOS ===

            // Fila 2: B2:H2 - Bordes superiores delgados
            Row row2 = sheet.createRow(1);
            for (int col = 1; col <= 7; col++) {
                Cell cell = row2.createCell(col);
                if (col == 1) {
                    cell.setCellStyle(bordeSuperiorIzquierdo);
                } else if (col == 7) {
                    cell.setCellStyle(bordeSuperiorDerecho);
                } else {
                    cell.setCellStyle(bordeSuperiorDelgado);
                }
            }
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 7));
            Cell cellEmpresa = row2.getCell(1);
            cellEmpresa.setCellValue("Nombre de la empresa");
            cellEmpresa.setCellStyle(bordeDelgadoCentrado);

            // Fila 3: B3:H3 - Bordes gruesos
            Row row3 = sheet.createRow(2);
            for (int col = 1; col <= 7; col++) {
                Cell cell = row3.createCell(col);
                if (col == 1) {
                    cell.setCellStyle(bordeGruesoIzquierdo);
                } else if (col == 7) {
                    cell.setCellStyle(bordeGruesoDerecho);
                } else {
                    cell.setCellStyle(bordeGruesoTopBottom);
                }
            }
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 7));
            Cell cellProyecto = row3.getCell(1);
            cellProyecto.setCellValue("Nombre del proyecto");
            cellProyecto.setCellStyle(bordeGruesoCentrado);

            // B5: cod
            Row row5 = sheet.createRow(4);
            Cell codCell = row5.createCell(1);
            codCell.setCellValue("cod:");
            codCell.setCellStyle(negrita);
            row5.createCell(2).setCellValue("Codigo del proyecto");

            // Fechas
            Row row6 = sheet.createRow(5);
            row6.createCell(1).setCellValue("Fecha de Inicio:");
            row6.getCell(1).setCellStyle(negrita);
            row6.createCell(2).setCellValue(fechaInicio);
            row6.getCell(2).setCellStyle(fechaStyle);

            Row row7 = sheet.createRow(6);
            row7.createCell(1).setCellValue("Fecha Final:");
            row7.getCell(1).setCellStyle(negrita);
            row7.createCell(2).setCellValue(fechaFin);
            row7.getCell(2).setCellStyle(fechaStyle);

            // Fila encabezado
            Row headerRow = sheet.createRow(8);
            String[] headers = {"N°", "Item", "und", "Cantidad", "Precio Unitario", "Costo", "Moneda"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i + 1);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(encabezadoStyle);
            }

            // === DATOS ===
            int rowIndex = 9;
            int contador = 1;
            double totalCosto = 0.0;

            for (Avance avance : avancesFiltrados) {
                Row row = sheet.createRow(rowIndex++);
                double cantidad = avance.getCantidad();
                double precio = avance.getItemAvanzado().getPU();
                double costo = cantidad * precio;
                totalCosto += costo;

                Cell[] cells = new Cell[7];
                cells[0] = row.createCell(1); cells[0].setCellValue(contador++);
                cells[1] = row.createCell(2); cells[1].setCellValue(avance.getItemAvanzado().getNombreItem());
                cells[2] = row.createCell(3); cells[2].setCellValue(avance.getItemAvanzado().getUnidades());
                cells[3] = row.createCell(4); cells[3].setCellValue(cantidad);
                cells[4] = row.createCell(5); cells[4].setCellValue(precio); cells[4].setCellStyle(dineroStyle);
                cells[5] = row.createCell(6); cells[5].setCellValue(costo); cells[5].setCellStyle(dineroStyle);
                cells[6] = row.createCell(7); cells[6].setCellValue("Bs.");

                for (Cell c : cells) c.setCellStyle(bordeDelgado);
            }

            // Fila vacía
            rowIndex++;

            // === TOTAL ===
            Row totalRow = sheet.createRow(rowIndex);
            for (int col = 1; col <= 7; col++) {
                Cell cell = totalRow.createCell(col);
                CellStyle estilo = workbook.createCellStyle();
                estilo.setBorderTop(BorderStyle.MEDIUM);
                estilo.setBorderBottom(BorderStyle.MEDIUM);
                if (col == 1) estilo.setBorderLeft(BorderStyle.MEDIUM);
                if (col == 7) estilo.setBorderRight(BorderStyle.MEDIUM);
                cell.setCellStyle(estilo);
            }

            totalRow.getCell(2).setCellValue("Costo Total del Periodo");
            totalRow.getCell(6).setCellValue(totalCosto);
            totalRow.getCell(6).setCellStyle(dineroStyle);
            totalRow.getCell(7).setCellValue("Bs.");

            // Ajuste de columnas
            for (int i = 1; i <= 7; i++) {
                sheet.autoSizeColumn(i);
            }

            // Guardar
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
