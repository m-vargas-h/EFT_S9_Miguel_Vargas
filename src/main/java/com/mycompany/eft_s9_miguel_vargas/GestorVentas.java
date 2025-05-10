
package com.mycompany.eft_s9_miguel_vargas;

/**
 * EFT - 
 * author Miguel Vargas
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorVentas {
    private List<Entrada> entradasVendidas;
    private int contadorVentas; // Generador de ID único
    private List<Entrada> listaEntradas = new ArrayList<>(); // 🔹 Asegura que la lista existe en la clase
    private List<Cliente> listaClientes = new ArrayList<>(); // 🔹 Debe existir en la clase

    public GestorVentas() {
        this.entradasVendidas = new ArrayList<>();
        this.contadorVentas = 1;
    }

    // Generar un ID único para cada venta
    public int generarIdVenta() {
        return contadorVentas++;
    }

    // Metodo para elminar una entrada
    public void eliminarEntradaDeCliente(Cliente cliente, Scanner scanner, Teatro teatro) {
        System.out.println("\n--- Eliminar Entrada ---");

        // Obtener la lista de entradas compradas del cliente actual
        List<Entrada> entradasCliente = cliente.getEntradasCompradas();
    
        if (entradasCliente.isEmpty()) {
            System.out.println("❌ No tienes entradas pendientes de pago.");
            return;
        }

        // Mostrar las entradas disponibles para eliminar
        System.out.println("\n--- Entradas disponibles para eliminar ---");
        for (Entrada entrada : entradasCliente) {
            System.out.println("ID: " + entrada.getIdVenta() +
                                " | Zona: " + entrada.getZona() +
                                " | Asiento: " + entrada.getFilaChar() + (entrada.getColumna() + 1) +
                                " | Precio: $" + entrada.getPrecioBase());
        }
    
        // Solicitar el ID de la entrada a eliminar
        System.out.print("Ingrese el ID de la entrada a eliminar: ");
        int idVenta = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Buscar la entrada a eliminar
        Entrada entradaAEliminar = null;
        for (Entrada entrada : entradasCliente) {
            if (entrada.getIdVenta() == idVenta) {
                entradaAEliminar = entrada;
                break;
            }
        }

        if (entradaAEliminar == null) {
            System.out.println("❌ No se encontró la entrada con ID: " + idVenta);
            return;
        }
    
        // Intentar liberar el asiento con la información de la entrada
        // Nota: Asegúrate de que getFila() y getColumna() devuelvan índices válidos para la matriz.
        boolean liberado = teatro.liberarAsiento(entradaAEliminar.getZona(), 
                                                entradaAEliminar.getFila(), 
                                                entradaAEliminar.getColumna());
    
        if (liberado) {
            // Solo se elimina la entrada si el asiento se liberó correctamente
            entradasCliente.remove(entradaAEliminar);
            System.out.println("✅ Entrada eliminada y asiento liberado correctamente.");
        } else {
            System.out.println("❌ Hubo un problema al liberar el asiento. No se eliminó la entrada.");
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

    public void modificarAsiento(int idVenta, String nuevaZona, char nuevaFilaChar, int nuevaColumna, Cliente cliente) {
        boolean encontro = false;
        for (Entrada entrada : cliente.getEntradasCompradas()) {
            if (entrada.getIdVenta() == idVenta) {
                entrada.setZona(nuevaZona);
                entrada.setFila(nuevaFilaChar - 'A');  // Suponiendo que conviertes la fila (A=0, B=1, ...)
                entrada.setFilaChar(nuevaFilaChar);
                entrada.setColumna(nuevaColumna);
                System.out.println("Asiento modificado correctamente.");
                encontro = true;
                break;
            }
        }
        if (!encontro) {
            System.out.println("No se encontró la entrada con ID: " + idVenta);
        }
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

        cliente.setIdCompraActual(cliente.getIdCompraActual() + 1); //incrementamos en 1 para asignar un nuevo valor a la proxima boleta

    }

    public List<Entrada> getEntradasPorCliente(int idCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente.getEntradasCompradas(); // 🔹 Las entradas están dentro del cliente
            }
        }
        return new ArrayList<>(); // 🔹 Si el cliente no existe, retorna una lista vacía
    }

}