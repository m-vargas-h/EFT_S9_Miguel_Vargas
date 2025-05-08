/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eft_s9_miguel_vargas;

public class Teatro {
    // Distribución de asientos: true = ocupado, false = libre
    private static boolean[][] zonaVip = new boolean[2][6];       // 2 filas x 6 columnas
    private static boolean[][] zonaPalco = new boolean[3][6];     // 3 filas x 6 columnas
    private static boolean[][] zonaPlateaBaja = new boolean[4][7];// 4 filas x 7 columnas
    private static boolean[][] zonaPlateaAlta = new boolean[5][8];// 5 filas x 8 columnas
    private static boolean[][] zonaGaleria = new boolean[6][10];  // 6 filas x 10 columnas

    // Método para obtener la matriz correspondiente a la zona
    public boolean[][] obtenerZona(String zona) {
        return switch (zona.toLowerCase()) {
            case "vip" -> zonaVip;
            case "palco" -> zonaPalco;
            case "platea baja" -> zonaPlateaBaja;
            case "platea alta" -> zonaPlateaAlta;
            case "galería" -> zonaGaleria;
            default -> new boolean[0][0]; // Retorna una matriz vacía en caso de zona inválida
        };
    }

    public Teatro() {
        inicializarAsientos();
    }

    // Inicializa todos los asientos como libres
    private void inicializarAsientos() {
        for (boolean[] fila : zonaVip) {
            java.util.Arrays.fill(fila, false);
        }
        for (boolean[] fila : zonaPalco) {
            java.util.Arrays.fill(fila, false);
        }
        for (boolean[] fila : zonaPlateaBaja) {
            java.util.Arrays.fill(fila, false);
        }
        for (boolean[] fila : zonaPlateaAlta) {
            java.util.Arrays.fill(fila, false);
        }
        for (boolean[] fila : zonaGaleria) {
            java.util.Arrays.fill(fila, false);
        }
    }

    // Método para mostrar los asientos disponibles y ocupados en todas las zonas
    public void mostrarPlanoGeneral() {
        System.out.println("\n--- Plano de Asientos ---");
        mostrarZona("VIP");
        mostrarZona("Palco");
        mostrarZona("Platea Baja");
        mostrarZona("Platea Alta");
        mostrarZona("Galería");
    }

    public void mostrarZona(String zona) {
        boolean[][] matrizZona = obtenerZona(zona);
        if (matrizZona == null) {
            System.out.println("❌ Zona no válida.");
            return;
        }
    
        System.out.println("\nZona " + zona + ":");
    
        // Imprimir números de columna alineados correctamente
        System.out.print("  ");
        for (int i = 0; i < matrizZona[0].length; i++) {
            System.out.print((i + 1) + " "); 
        }
        System.out.println();
    
        // Imprimir filas con estado de los asientos
        char filaLetra = 'A';
        for (boolean[] fila : matrizZona) {
            System.out.print(filaLetra + " ");
            for (boolean asiento : fila) {
                System.out.print((asiento ? "X " : "O ")); // Representación clara sin corchetes
            }
            System.out.println();
            filaLetra++;
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



    // Método auxiliar para validar si una ubicación es correcta
    private boolean validarUbicacion(boolean[][] zona, int fila, int asiento) {
        return fila >= 0 && fila < zona.length && asiento >= 0 && asiento < zona[fila].length;
    }
}