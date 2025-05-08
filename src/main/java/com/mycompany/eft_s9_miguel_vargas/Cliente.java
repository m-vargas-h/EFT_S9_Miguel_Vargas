package com.mycompany.eft_s9_miguel_vargas;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String correo;
    private int edad;
    private char genero;
    private String tipoCliente;
    private int idCliente;
    private List<Entrada> entradasCompradas;

    public Cliente(String nombre, String correo, int edad, char genero, int idCliente) {
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.genero = Character.toUpperCase(genero);
        this.idCliente = idCliente; // Agregamos el ID del cliente
        this.tipoCliente = determinarTipoCliente();
        this.entradasCompradas = new ArrayList<>();
    }

    // Método para determinar el tipo de cliente basado en edad y género
    private String determinarTipoCliente() {
        if (edad <= 12) return "niño";
        if (edad >= 18 && edad <= 25) return "estudiante";
        if (edad >= 60) return "adulto mayor";
        if (genero == 'F') return "mujer";
        return "general";
    }

    // Métodos para obtener información del cliente
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getEdad() {
        return edad;
    }

    public char getGenero() {
        return genero;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public List<Entrada> getEntradasCompradas() {
        return entradasCompradas;
    }

    // Método para agregar una entrada al cliente
    public void agregarEntrada(Entrada entrada) {
        if (entradasCompradas.size() < 5) {
            entradasCompradas.add(entrada);
            System.out.println("✔ Entrada agregada para " + nombre);
        } else {
            System.out.println("Límite de 5 entradas alcanzado para este cliente.");
        }
    }

    // Método para eliminar una entrada
    public void eliminarEntrada(int idVenta) {
        entradasCompradas.removeIf(entrada -> entrada.getIdVenta() == idVenta);
        System.out.println("✔ Entrada eliminada correctamente.");
    }

    // Método para mostrar las entradas compradas
    public void mostrarEntradas() {
        System.out.println("\n--- Entradas de " + nombre + " ---");
        if (entradasCompradas.isEmpty()) {
            System.out.println("Este cliente no ha comprado entradas aún.");
        } else {
            for (Entrada entrada : entradasCompradas) {
                System.out.println(entrada);
            }
        }
    }
}
