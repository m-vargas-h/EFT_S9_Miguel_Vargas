
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
            System.out.println("Entrada eliminada correctamente.");
        } else {
            System.out.println("No se encontró la entrada con ID: " + idVenta);
        }
    }

    // Método para procesar el pago de las entradas
    public void finalizarCompra() {
        if (!entradasVendidas.isEmpty()) {
            System.out.println("\n--- Procesando pago ---");
            for (Entrada entrada : entradasVendidas) {
                System.out.println(entrada);
            }
            System.out.println("Pago completado. ¡Gracias por su compra!");
            entradasVendidas.clear(); // Vaciar lista tras pago exitoso
        } else {
            System.out.println("No hay entradas pendientes de pago.");
        }
    }

    // Método para cancelar una compra y liberar los asientos
    public void cancelarCompra() {
        System.out.println("\nCancelando compra...");
        entradasVendidas.clear();
        System.out.println("Compra cancelada. Todos los asientos han sido liberados.");
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
            case "niño" -> 0.10;
            case "mujer" -> 0.20;
            case "estudiante" -> 0.15;
            case "adulto mayor" -> 0.25;
            default -> 0.00;
        };
    }

    public void modificarAsiento(int idVenta, String nuevaZona, char nuevaFilaChar, int nuevaColumna) {
        for (Entrada entrada : entradasVendidas) {
            if (entrada.getIdVenta() == idVenta) {
                entrada.setZona(nuevaZona);
                entrada.setFila(nuevaFilaChar - 'A');
                entrada.setFilaChar(nuevaFilaChar);
                entrada.setColumna(nuevaColumna);
                System.out.println("Asiento modificado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró la entrada con ID: " + idVenta);
    }

    public void generarBoleta(Cliente cliente) {
    if (cliente.getEntradasCompradas().isEmpty()) {
        System.out.println("❌ No hay entradas compradas para generar la boleta.");
        return;
    }

    System.out.println("\n--------------- BOLETA ---------------");
    System.out.println("                Nº 000" + cliente.getIdCompraActual());
    System.out.println("             TEATRO MORO");
    System.out.println(" SHOW: De vuelta a clases con el GOTH");
    System.out.println("--------------------------------------");

    double totalNeto = 0;
    double totalIVA = 0;
    double totalFinal = 0;

    for (Entrada entrada : cliente.getEntradasCompradas()) {
        String zona = switch (entrada.getZonaSeleccionada()) {
            case 1 -> "VIP";
            case 2 -> "Normal";
            case 3 -> "Palco";
            default -> "Desconocida";
        };

        double precioConDescuento = entrada.getPrecioBase() * (1 - entrada.getDescuentoAplicado());
        double ivaPorEntrada = precioConDescuento * 0.19;
        double precioNetoEntrada = precioConDescuento - ivaPorEntrada;

        totalNeto += precioNetoEntrada;
        totalIVA += ivaPorEntrada;
        totalFinal += precioConDescuento;

        System.out.println("Entrada: Zona " + zona + ", Asiento: " + entrada.getFilaChar() + (entrada.getColumna() + 1));
        System.out.println("Precio base: $" + entrada.getPrecioBase());
        System.out.println("Descuento aplicado: " + (entrada.getDescuentoAplicado() * 100) + "%");
        System.out.println("--------------------------------------");
    }

    System.out.println("-------- RESUMEN DE LA COMPRA --------");
    System.out.println("Subtotal (Neto)     : $" + totalNeto);
    System.out.println("IVA (19%)           : $" + totalIVA);
    System.out.println("TOTAL FINAL A PAGAR : $" + totalFinal);
    System.out.println("--------------------------------------");
    System.out.println("¡Gracias por tu compra!");
}
}