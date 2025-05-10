
package com.mycompany.eft_s9_miguel_vargas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaEntradas {

    /**
     * Guarda una entrada en el archivo CSV.
     * Cada entrada se escribe como una línea con campos separados por comas.
     *
     * @param entrada    Objeto Entrada a guardar.
     * @param rutaArchivo Ruta del archivo CSV (por ejemplo, "entradas.csv").
     */
    public static void guardarEntrada(Entrada entrada, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            // Estructura de la línea: idVenta,zona,fila,columna,precioBase,filaChar,esReserva,descuentoAplicado
            String linea = entrada.getIdVenta() + "," +
                           entrada.getZona() + "," +
                           entrada.getFila() + "," +
                           entrada.getColumna() + "," +
                           entrada.getPrecioBase() + "," +
                           entrada.getFilaChar() + "," +
                           entrada.isReserva() + "," +
                           entrada.getDescuentoAplicado();
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la entrada: " + e.getMessage());
        }
    }

    /**
     * Lee el archivo CSV y retorna una lista de entradas.
     *
     * @param rutaArchivo Ruta del archivo CSV.
     * @return Lista de objetos Entrada leídos del archivo.
     */
    public static List<Entrada> leerEntradas(String rutaArchivo) {
        List<Entrada> entradas = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            // Si el archivo no existe, retorna una lista vacía.
            return entradas;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Saltar líneas vacías
                if (linea.trim().isEmpty()) continue;
                
                String[] campos = linea.split(",");
                if (campos.length < 8) continue; // Validar que se encuentren todos los campos
                
                int idVenta = Integer.parseInt(campos[0]);
                String zona = campos[1];
                int fila = Integer.parseInt(campos[2]);
                int columna = Integer.parseInt(campos[3]);
                double precioBase = Double.parseDouble(campos[4]);
                char filaChar = campos[5].charAt(0);
                boolean esReserva = Boolean.parseBoolean(campos[6]);
                double descuentoAplicado = Double.parseDouble(campos[7]);
                
                Entrada entrada = new Entrada(idVenta, zona, fila, columna, precioBase, filaChar, esReserva, descuentoAplicado);
                entradas.add(entrada);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato en el archivo: " + e.getMessage());
        }
        return entradas;
    }

    /**
     * Escribe una lista completa de entradas en el archivo CSV, sobrescribiendo su contenido.
     * Esta opción puede ser útil si necesitas actualizar o eliminar registros.
     *
     * @param entradas    Lista de entradas que se desean escribir en el archivo.
     * @param rutaArchivo Ruta del archivo CSV.
     */
    public static void escribirEntradas(List<Entrada> entradas, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Entrada entrada : entradas) {
                String linea = entrada.getIdVenta() + "," +
                               entrada.getZona() + "," +
                               entrada.getFila() + "," +
                               entrada.getColumna() + "," +
                               entrada.getPrecioBase() + "," +
                               entrada.getFilaChar() + "," +
                               entrada.isReserva() + "," +
                               entrada.getDescuentoAplicado();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir las entradas: " + e.getMessage());
        }
    }
}
