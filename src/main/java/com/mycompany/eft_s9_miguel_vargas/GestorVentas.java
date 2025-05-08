
package com.mycompany.eft_s9_miguel_vargas;

/**
 * EFT - 
 * author Miguel Vargas
 */

import java.util.ArrayList;
import java.util.List;

public class GestorVentas {
    private List<Entrada> entradasVendidas;
    private int contadorVentas; // Generador de ID único

    public GestorVentas() {
        this.entradasVendidas = new ArrayList<>();
        this.contadorVentas = 1;
    }

    // Generar un ID único para cada venta
    public int generarIdVenta() {
        return contadorVentas++;
    }

    // Método para agregar una entrada
    public void agregarEntrada(Entrada entrada) {
        entradasVendidas.add(entrada);
        System.out.println("✔ Entrada registrada: " + entrada.getEntradaActualizada());
    }

    // Método para eliminar una entrada
    public void eliminarEntrada(int idVenta) {
        boolean eliminada = entradasVendidas.removeIf(entrada -> entrada.getIdVenta() == idVenta);
        if (eliminada) {
            System.out.println("✔ Entrada eliminada correctamente.");
        } else {
            System.out.println("❌ No se encontró la entrada con ID: " + idVenta);
        }
    }

    // Método para procesar el pago de las entradas
    public void finalizarCompra() {
        if (!entradasVendidas.isEmpty()) {
            System.out.println("\n--- Procesando pago ---");
            for (Entrada entrada : entradasVendidas) {
                System.out.println(entrada);
            }
            System.out.println("✔ Pago completado. ¡Gracias por su compra!");
            entradasVendidas.clear(); // Vaciar lista tras pago exitoso
        } else {
            System.out.println("❌ No hay entradas pendientes de pago.");
        }
    }

    // Método para cancelar una compra y liberar los asientos
    public void cancelarCompra() {
        System.out.println("\n❌ Cancelando compra...");
        entradasVendidas.clear();
        System.out.println("✔ Compra cancelada. Todos los asientos han sido liberados.");
    }

    // Verifica si hay entradas pendientes de pago
    public boolean tieneEntradasPendientes() {
        return !entradasVendidas.isEmpty();
    }

    public double calcularPrecioPorZona(String zona) {
        return switch (zona.toLowerCase()) {
            case "vip" -> 20000.0; // Precio para zona VIP
            case "normal" -> 15000.0; // Precio para zona Normal
            case "palco" -> 18000.0; // Precio para zona Palco
            default -> throw new IllegalArgumentException("Zona inválida: " + zona);
        };
    }

    public double obtenerDescuento(String tipoCliente) {
        return switch (tipoCliente.toLowerCase()) {
            case "niño" -> 0.50;  // 50% de descuento
            case "estudiante" -> 0.30; // 30% de descuento
            case "adulto mayor" -> 0.40; // 40% de descuento
            case "general" -> 0.00; // Sin descuento
            default -> throw new IllegalArgumentException("Tipo de cliente inválido: " + tipoCliente);
        };
    }

    public void modificarAsiento(int idVenta, String nuevaZona, char nuevaFilaChar, int nuevaColumna) {
        for (Entrada entrada : entradasVendidas) {
            if (entrada.getIdVenta() == idVenta) {
                entrada.setZona(nuevaZona);
                entrada.setFila(nuevaFilaChar - 'A');
                entrada.setFilaChar(nuevaFilaChar);
                entrada.setColumna(nuevaColumna);
                System.out.println("✔ Asiento modificado correctamente.");
                return;
            }
        }
        System.out.println("❌ No se encontró la entrada con ID: " + idVenta);
    }
}