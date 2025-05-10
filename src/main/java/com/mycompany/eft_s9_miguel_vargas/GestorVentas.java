package com.mycompany.eft_s9_miguel_vargas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorVentas {
    private List<Entrada> entradasVendidas;
    private int contadorVentas; // Generador de ID único
    private List<Cliente> listaClientes = new ArrayList<>(); // Lista de clientes registrados

    public GestorVentas() {
        this.entradasVendidas = new ArrayList<>();
        this.contadorVentas = 1;
    }

    public GestorVentas(List<Entrada> entradasVendidas) {
        this.entradasVendidas = entradasVendidas;
    }
    
    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    
    // Método para agregar una entrada a la lista de entradas vendidas.
    public void agregarEntrada(Entrada entrada) {
        entradasVendidas.add(entrada);
    }

    // Generar un ID único para cada venta
    public int generarIdVenta() {
        return contadorVentas++;
    }

    // Método para eliminar una entrada de un cliente y liberar el asiento.
    public void eliminarEntradaDeCliente(Cliente cliente, Scanner scanner, Teatro teatro) {
        System.out.println("\n--- Eliminar Entrada ---");
        List<Entrada> entradasCliente = cliente.getEntradasCompradas();
    
        if (entradasCliente.isEmpty()) {
            System.out.println("No tienes entradas pendientes de pago.");
            return;
        }

        System.out.println("\n--- Entradas disponibles para eliminar ---");
        for (Entrada entrada : entradasCliente) {
            System.out.println("ID: " + entrada.getIdVenta() +
                                " | Zona: " + entrada.getZona() +
                                " | Asiento: " + entrada.getFilaChar() + (entrada.getColumna() + 1) +
                                " | Precio: $" + entrada.getPrecioBase());
        }
    
        System.out.print("Ingrese el ID de la entrada a eliminar: ");
        int idVenta = scanner.nextInt();
        scanner.nextLine();
        
        Entrada entradaAEliminar = null;
        for (Entrada entrada : entradasCliente) {
            if (entrada.getIdVenta() == idVenta) {
                entradaAEliminar = entrada;
                break;
            }
        }

        if (entradaAEliminar == null) {
            System.out.println("No se encontró la entrada con ID: " + idVenta);
            return;
        }
    
        boolean liberado = teatro.liberarAsiento(entradaAEliminar.getZona(), 
                                                  entradaAEliminar.getFila(), 
                                                  entradaAEliminar.getColumna());
    
        if (liberado) {
            entradasCliente.remove(entradaAEliminar);
            System.out.println("Entrada eliminada y asiento liberado correctamente.");
        } else {
            System.out.println("Hubo un problema al liberar el asiento. No se eliminó la entrada.");
        }
    }

    // Método para procesar el pago de las entradas pendientes.
    public void finalizarCompra() {
        if (!entradasVendidas.isEmpty()) {
            System.out.println("\n--- Procesando pago ---");
            for (Entrada entrada : entradasVendidas) {
                System.out.println(entrada);
            }
            System.out.println("Pago completado. ¡Gracias por su compra!");
            entradasVendidas.clear();
        } else {
            System.out.println("No hay entradas pendientes de pago.");
        }
    }

    // Método para cancelar una compra.
    public void cancelarCompra() {
        System.out.println("\nCancelando compra...");
        entradasVendidas.clear();
        System.out.println("Compra cancelada. Todos los asientos han sido liberados.");
    }

    // Verifica si hay entradas pendientes de pago.
    public boolean tieneEntradasPendientes() {
        return !entradasVendidas.isEmpty();
    }

    // Calcula el precio de la entrada según la zona.
    public double calcularPrecioPorZona(String zona) {
        return switch (zona.toLowerCase()) {
            case "vip" -> 25000.0;
            case "platea baja" -> 17000.0;
            case "platea alta" -> 20000.0;
            case "palco" -> 15000.0;
            case "galeria" -> 9000.0;
            default -> throw new IllegalArgumentException("Zona inválida: " + zona);
        };
    }

    // Obtiene un descuento basado en el tipo de cliente.
    public double obtenerDescuento(String tipoCliente) {
        return switch (tipoCliente.toLowerCase()) {
            case "niño" -> 0.10;
            case "mujer" -> 0.20;
            case "estudiante" -> 0.15;
            case "adulto mayor" -> 0.25;
            default -> 0.00;
        };
    }

    // Método para modificar el asiento de una entrada de un cliente.
    public void modificarAsiento(int idVenta, String nuevaZona, char nuevaFilaChar, int nuevaColumna, Cliente cliente) {
        boolean encontro = false;
        for (Entrada entrada : cliente.getEntradasCompradas()) {
            if (entrada.getIdVenta() == idVenta) {
                entrada.setZona(nuevaZona);
                entrada.setFila(nuevaFilaChar - 'A');
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


    // Método para generar una boleta de compra para el cliente.
    public void generarBoleta(Cliente cliente) {
        if (cliente.getEntradasCompradas().isEmpty()) {
            System.out.println("No hay entradas compradas para generar la boleta.");
            return;
        }

        System.out.println("\n--------------- BOLETA ---------------");
        // Uso de String.format para forzar la impresión con ceros a la izquierda (por ejemplo, 0001).
        System.out.println(String.format("                Nº %04d", cliente.getIdCompraActual()));
        System.out.println("             TEATRO MORO");
        System.out.println(" SHOW: De vuelta a clases con el GOTH");
        System.out.println("--------------------------------------");

        double totalNeto = 0;    // Suma de los montos netos (sin IVA) extraídos del precio final.
        double totalIVA = 0;     // Suma de los IVAs calculados de cada entrada.
        double totalFinal = 0;   // Suma de los precios finales de cada entrada (descuento aplicado).

        for (Entrada entrada : cliente.getEntradasCompradas()) {
            String zona = entrada.getZona();
            double precioBase = entrada.getPrecioBase();

            // Suponemos que entrada.getDescuentoAplicado() retorna el descuento en pesos (ej. 3000)
            double descuentoPesos = entrada.getDescuentoAplicado();
            // Precio final luego de aplicar el descuento
            double precioConDescuento = precioBase - descuentoPesos;

            double netoSinIVA = precioConDescuento / 1.19;
            double ivaPorEntrada = precioConDescuento - netoSinIVA;

            // Acumula totales.
            totalNeto += netoSinIVA;
            totalIVA += ivaPorEntrada;
            totalFinal += precioConDescuento;

            // Detalle de la entrada.
            System.out.println("Entrada: Zona " + zona +
                                ", Asiento: " + entrada.getFilaChar() + (entrada.getColumna() + 1));
            System.out.printf("Precio base: $%.2f%n", precioBase);
            System.out.printf("Descuento aplicado: $%.2f%n", descuentoPesos);
            System.out.println("--------------------------------------");
        }

        // Resumen de la compra.
        System.out.println("-------- RESUMEN DE LA COMPRA --------");
        System.out.printf("Subtotal (Neto)     : $%.2f%n", totalNeto);
        System.out.printf("IVA (19%%)           : $%.2f%n", totalIVA);
        System.out.printf("TOTAL FINAL A PAGAR : $%.2f%n", totalFinal);
        System.out.println("--------------------------------------");
        System.out.println("¡Gracias por tu compra!");

        // Incrementa el número de boleta para la próxima compra.
        cliente.setIdCompraActual(cliente.getIdCompraActual() + 1);
    }

    // Retorna las entradas de un cliente dado su ID.
    public List<Entrada> getEntradasPorCliente(int idCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente.getEntradasCompradas();
            }
        }
        return new ArrayList<>();
    }

    public Cliente buscarClientePorID(int idCliente) {
        for (Cliente cliente : listaClientes) { 
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }
}