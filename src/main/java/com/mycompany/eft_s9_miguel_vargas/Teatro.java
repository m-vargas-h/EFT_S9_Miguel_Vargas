/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eft_s9_miguel_vargas;

public class Teatro {
    // Distribución de asientos: true = ocupado, false = libre
    private static boolean[][] zonaVip = new boolean[2][6];
    private static boolean[][] zonaNormal = new boolean[4][6];
    private static boolean[][] zonaPalco = new boolean[3][6];

    public Teatro() {
        inicializarAsientos();
    }

    // Inicializa todos los asientos como libres
    private void inicializarAsientos() {
        for (boolean[] fila : zonaVip) {
            java.util.Arrays.fill(fila, false);
        }
        for (boolean[] fila : zonaNormal) {
            java.util.Arrays.fill(fila, false);
        }
        for (boolean[] fila : zonaPalco) {
            java.util.Arrays.fill(fila, false);
        }
    }

    // Método para mostrar los asientos disponibles y ocupados
    public void mostrarPlanoGeneral() {
        System.out.println("\n--- Plano de Asientos ---");
        mostrarZona("VIP", zonaVip);
        mostrarZona("Normal", zonaNormal);
        mostrarZona("Palco", zonaPalco);
    }

    private void mostrarZona(String nombre, boolean[][] zona) {
        System.out.println("\nZona " + nombre + ":");
        System.out.print("  "); // Alineación para etiquetas de columnas
        for (int col = 0; col < zona[0].length; col++) {
            System.out.print((col + 1) + " ");
        }
        System.out.println();

        for (int fila = 0; fila < zona.length; fila++) {
            System.out.print((char) ('A' + fila) + " "); // Etiqueta de filas
            for (int col = 0; col < zona[fila].length; col++) {
                System.out.print(zona[fila][col] ? "[X] " : "[O] ");
            }
            System.out.println();
        }
    }

    // Método para asignar un asiento verificando disponibilidad
    public boolean asignarAsiento(String zona, int fila, int asiento) {
        boolean[][] seleccion = obtenerZona(zona);
        if (seleccion != null && validarUbicacion(seleccion, fila, asiento)) {
            if (!seleccion[fila][asiento]) {
                seleccion[fila][asiento] = true;
                return true;
            } else {
                System.out.println("❌ Asiento ya ocupado.");
                return false;
            }
        }
        System.out.println("❌ Ubicación inválida.");
        return false;
    }

    // Método para liberar un asiento si el usuario cancela la compra
    public boolean liberarAsiento(String zona, int fila, int asiento) {
        boolean[][] seleccion = obtenerZona(zona);
        if (seleccion != null && validarUbicacion(seleccion, fila, asiento)) {
            if (seleccion[fila][asiento]) {
                seleccion[fila][asiento] = false;
                return true;
            } else {
                System.out.println("❌ El asiento ya estaba libre.");
                return false;
            }
        }
        System.out.println("❌ Ubicación inválida.");
        return false;
    }

    // Método auxiliar para obtener la matriz de la zona seleccionada
    private boolean[][] obtenerZona(String zona) {
        return switch (zona.toLowerCase()) {
            case "vip" -> zonaVip;
            case "normal" -> zonaNormal;
            case "palco" -> zonaPalco;
            default -> null;
        };
    }

    // Método auxiliar para validar si una ubicación es correcta
    private boolean validarUbicacion(boolean[][] zona, int fila, int asiento) {
        return fila >= 0 && fila < zona.length && asiento >= 0 && asiento < zona[fila].length;
    }
}