package com.mycompany.eft_s9_miguel_vargas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaClientes {

    /**
     * Guarda un cliente en el archivo CSV.
     * Estructura: idCliente,nombre,correo,edad,genero,tipoCliente,idCompraActual
     */
    public static void guardarCliente(Cliente cliente, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            String linea = cliente.getIdCliente() + "," +
                           cliente.getNombre() + "," +
                           cliente.getCorreo() + "," +
                           cliente.getEdad() + "," +
                           cliente.getGenero() + "," +
                           cliente.getTipoCliente() + "," +
                           cliente.getIdCompraActual();
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el cliente: " + e.getMessage());
        }
    }

    /**
     * Lee el archivo CSV y retorna una lista de clientes.
     * Se usa trim() para cada campo y se remueve el BOM si aparece en el primer campo.
     */
    public static List<Cliente> leerClientes(String rutaArchivo) {
        List<Cliente> clientes = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            // Si el archivo no existe, retorna una lista vacía.
            return clientes;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue; // Saltar líneas vacías
                String[] campos = linea.split(",");
                if (campos.length < 7) continue; // Se requieren 7 campos

                // Remover BOM si existe en el primer campo
                String idCampo = campos[0].trim();
                if (idCampo.startsWith("\uFEFF")) {
                    idCampo = idCampo.substring(1).trim();
                }
                
                int idCliente = Integer.parseInt(idCampo);
                String nombre = campos[1].trim();
                String correo = campos[2].trim();
                int edad = Integer.parseInt(campos[3].trim());
                char genero = campos[4].trim().charAt(0);
                String tipoCliente = campos[5].trim();
                int idCompraActual = Integer.parseInt(campos[6].trim());
                
                Cliente cliente = new Cliente(nombre, correo, edad, genero, idCliente);
                cliente.setIdCompraActual(idCompraActual);
                
                // Agregamos el cliente a la lista y mostramos el ID para depurar
                clientes.add(cliente);
                System.out.println("Cliente cargado: ID " + idCliente + ", Nombre: " + nombre);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato en el archivo de clientes: " + e.getMessage());
        }
        return clientes;
    }

    /**
     * Sobrescribe el archivo con la lista completa de clientes.
     */
    public static void escribirClientes(List<Cliente> clientes, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Cliente cliente : clientes) {
                String linea = cliente.getIdCliente() + "," +
                               cliente.getNombre() + "," +
                               cliente.getCorreo() + "," +
                               cliente.getEdad() + "," +
                               cliente.getGenero() + "," +
                               cliente.getTipoCliente() + "," +
                               cliente.getIdCompraActual();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir los clientes: " + e.getMessage());
        }
    }
}