
package com.mycompany.eft_s9_miguel_vargas;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private int edad;
    private String tipoCliente; // Niño, Estudiante, Adulto Mayor, General
    private List<Entrada> entradasCompradas;

    public Cliente(String nombre, int edad, String tipoCliente) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipoCliente = tipoCliente;
        this.entradasCompradas = new ArrayList<>();
    }

    // Métodos para obtener información del cliente
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
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
            System.out.println("❌ Límite de 5 entradas alcanzado para este cliente.");
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
