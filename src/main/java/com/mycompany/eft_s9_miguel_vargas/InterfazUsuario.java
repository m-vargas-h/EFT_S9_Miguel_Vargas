
package com.mycompany.eft_s9_miguel_vargas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfazUsuario {
    private GestorVentas gestorVentas;
    private Teatro teatro;
    private Scanner scanner;

    public InterfazUsuario(GestorVentas gestorVentas, Teatro teatro) {
        this.gestorVentas = gestorVentas;
        this.teatro = teatro;
        this.scanner = new Scanner(System.in);
    }

    private List<Cliente> clientesRegistrados = new ArrayList<>();
    private int contadorID = 1; // Para generar IDs únicos

    private void registrarCliente() {
        System.out.println("\n--- Registro de Cliente ---");

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine();

        System.out.print("Ingrese su edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese su género (M/F): ");
        char genero = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine(); // Limpiar buffer

        // Generar ID único
        int idCliente = contadorID++;

        // Crear nuevo cliente y agregarlo a la lista
        Cliente nuevoCliente = new Cliente(nombre, correo, edad, genero, idCliente);
        clientesRegistrados.add(nuevoCliente);

        System.out.println("\n✔ Cliente registrado correctamente.");
        System.out.println("ID Asignado: " + idCliente);
    }

    public void mostrarMenu() {
        boolean continuar = true;
        System.out.println("---------------------");
        System.out.println("     TEATRO MORO");
        System.out.println("---------------------");
        System.out.println("Bienvenido a nuestro sistema de compra");

        while (continuar) {
            menu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            continuar = procesarOpcion(opcion);
        }
    }

    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrarCliente();
            case 2 -> comprarEntradas(5);
            case 3 -> verAsientosDisponibles();
            case 4 -> verPromociones();
            case 5 -> modificarCompra();
            case 6 -> procesarPago();
            case 7 -> {
                salirMenu();
                return false;
            }
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
        return true;
    }

    private Cliente buscarClientePorID(int id) {
        for (Cliente cliente : clientesRegistrados) {
            if (cliente.getIdCliente() == id) {
                return cliente;
            }
        }
        return null; // Retorna null si no encuentra el cliente
    }

    private void comprarEntradas(int maxEntradas) {
        System.out.println("\n--- Compra de Entradas ---");
        System.out.println("Máximo permitido en esta transacción: " + maxEntradas);
    
        System.out.print("Ingrese su ID de cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); 
    
        Cliente clienteActual = buscarClientePorID(idCliente);
    
        if (clienteActual == null) {
            System.out.println("❌ Cliente no encontrado. Verifique su ID.");
            return;
        }
    
        System.out.println("\n✔ Cliente identificado: " + clienteActual.getNombre());
        System.out.println("Correo: " + clienteActual.getCorreo());
        System.out.println("Tipo de Cliente: " + clienteActual.getTipoCliente());
    
        int cantidadEntradas;
        do {
            System.out.print("\n¿Cuántas entradas desea comprar? (1-" + maxEntradas + "): ");
            cantidadEntradas = scanner.nextInt();
            scanner.nextLine(); 
    
            if (cantidadEntradas < 1 || cantidadEntradas > maxEntradas) {
                System.out.println("❌ Número inválido. Debe elegir entre 1 y " + maxEntradas + " entradas.");
            }
        } while (cantidadEntradas < 1 || cantidadEntradas > maxEntradas);
    
        List<Entrada> entradasCompradas = new ArrayList<>();
    
        for (int i = 0; i < cantidadEntradas; i++) {
            System.out.println("\n➡ Seleccionando Entrada #" + (i + 1));
    
            System.out.println("\nSeleccione la zona:");
            System.out.println("1. VIP");
            System.out.println("2. Platea Baja");
            System.out.println("3. Platea Alta");
            System.out.println("4. Palco");
            System.out.println("5. Galería");
            System.out.print("Ingrese una opción (1-5): ");
    
            int opcionZona = scanner.nextInt();
            scanner.nextLine();
    
            String zona = switch (opcionZona) {
                case 1 -> "vip";
                case 2 -> "platea baja";
                case 3 -> "platea alta";
                case 4 -> "palco";
                case 5 -> "galería";
                default -> {
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                    i--;
                    yield null;
                }
            };
            if (zona == null) continue;
    
            teatro.mostrarZona(zona);  
    
            System.out.print("\nIngrese fila (A, B, C...): ");
            char filaChar = scanner.next().toUpperCase().charAt(0);
    
            System.out.print("Ingrese número de asiento: ");
            int asiento = scanner.nextInt() - 1;
            scanner.nextLine();
    
            int fila = filaChar - 'A';
    
            if (teatro.asignarAsiento(zona, fila, asiento)) {
                int idVenta = gestorVentas.generarIdVenta();
                double precioBase = gestorVentas.calcularPrecioPorZona(zona);
                boolean esReserva = false;
                double descuentoAplicado = precioBase * gestorVentas.obtenerDescuento(clienteActual.getTipoCliente());
    
                Entrada entrada = new Entrada(idVenta, zona, fila, asiento, precioBase, filaChar, esReserva, descuentoAplicado);
                
                entradasCompradas.add(entrada);
                gestorVentas.agregarEntrada(entrada);
                clienteActual.agregarEntrada(entrada);
                System.out.println("✔ Entrada #" + (i + 1) + " asignada correctamente.");
            } else {
                System.out.println("❌ Asiento no disponible, elija otro.");
                i--;
            }
        }
    
        // 🔹 Mostrar resumen solo al final
        System.out.println("\n--- Detalle de Compra ---");
        for (int i = 0; i < entradasCompradas.size(); i++) {
            Entrada entrada = entradasCompradas.get(i);
            System.out.println("E" + (i + 1) + " → ID Venta: " + entrada.getIdVenta() +
                               ", Zona: " + entrada.getZona() + 
                               ", Asiento: " + entrada.getFilaChar() + (entrada.getColumna() + 1) + 
                               ", Descuento aplicado: $" + entrada.getDescuentoAplicado() + 
                               ", Precio final: $" + (entrada.getPrecioBase() - entrada.getDescuentoAplicado()));
        }
        System.out.println("-------------------------");
    }

    private void verAsientosDisponibles() {
        System.out.println("\n--- Plano Completo del Teatro ---");
        teatro.mostrarPlanoGeneral();
    }

    private void verPromociones() {
        System.out.println("\n--- Promociones Vigentes ---");
        System.out.println("1. Descuento del 10% para niños.");
        System.out.println("2. Descuento del 20% para mujeres.");
        System.out.println("3. Descuento del 15% para estudiantes.*");
        System.out.println("4. Descuento del 25% para personas de tercera edad.");
    }

    private void modificarCompra() {
        System.out.println("\n--- Modificar Compra ---");
        System.out.println("1. Agregar entrada.");
        System.out.println("2. Eliminar entrada.");
        System.out.println("3. Modificar asiento.");
    
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
    
        System.out.print("Ingrese su ID de cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
    
        Cliente clienteActual = buscarClientePorID(idCliente);
    
        if (clienteActual == null) {
            System.out.println("❌ Cliente no encontrado. Verifique su ID.");
            return;
        }
    
        int entradasActuales = clienteActual.getEntradasCompradas().size();
        int maxEntradasDisponibles = 5 - entradasActuales;
    
        switch (opcion) {
            case 1 -> {
                if (maxEntradasDisponibles <= 0) {
                    System.out.println("❌ No puede agregar más entradas. Ya tiene el máximo permitido.");
                } else {
                    System.out.println("\n✔ Actualmente tiene " + entradasActuales + " entradas.");
                    System.out.println("Puede agregar hasta " + maxEntradasDisponibles + " más.");
            
                    int cantidadEntradas;
                    do {
                        System.out.print("\n¿Cuántas entradas desea agregar? (1-" + maxEntradasDisponibles + "): ");
                        cantidadEntradas = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer
            
                        if (cantidadEntradas < 1 || cantidadEntradas > maxEntradasDisponibles) {
                            System.out.println("❌ Número inválido. Debe elegir entre 1 y " + maxEntradasDisponibles + " entradas.");
                        }
                    } while (cantidadEntradas < 1 || cantidadEntradas > maxEntradasDisponibles);
            
                    // 🔹 Aquí está el error: `comprarEntradas();` debe recibir `maxEntradasDisponibles`
                    comprarEntradas(maxEntradasDisponibles); // 🔹 Asegura que se pasa el argumento correcto
                }
            }
            case 2 -> {
                System.out.print("Ingrese el ID de la entrada a eliminar: ");
                int idVenta = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                gestorVentas.eliminarEntrada(idVenta);
            }
            case 3 -> {
                System.out.print("Ingrese el ID de la entrada a modificar: ");
                int idVenta = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
    
                System.out.print("Ingrese nueva zona (VIP, Normal, Palco): ");
                String nuevaZona = scanner.nextLine().toLowerCase();
                System.out.print("Ingrese nueva fila (A, B, C...): ");
                char nuevaFilaChar = scanner.next().toUpperCase().charAt(0);
                System.out.print("Ingrese nuevo número de asiento: ");
                int nuevaColumna = scanner.nextInt() - 1;
                scanner.nextLine(); // Limpiar buffer
    
                gestorVentas.modificarAsiento(idVenta, nuevaZona, nuevaFilaChar, nuevaColumna);
            }
            default -> System.out.println("❌ Opción inválida.");
        }
    }

    private void procesarPago() {
        System.out.println("\n--- Procesar Pago ---");
    
        System.out.print("Ingrese su ID de cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); 
    
        Cliente clienteActual = buscarClientePorID(idCliente);
    
        if (clienteActual == null) {
            System.out.println("❌ Cliente no encontrado. Verifique su ID.");
            return;
        }
    
        List<Entrada> entradas = clienteActual.getEntradasCompradas();
        if (entradas.isEmpty()) {
            System.out.println("❌ No tiene entradas para pagar.");
            return;
        }
    
        // 🔹 Calcular total a pagar considerando descuentos
        double total = 0;
        System.out.println("\n--- Resumen de Compra ---");
        System.out.println("Cantidad de Entradas: " + entradas.size());
    
        for (Entrada entrada : entradas) {
            double precioFinal = entrada.getPrecioBase() - entrada.getDescuentoAplicado();
            total += precioFinal;
        }
    
        System.out.println("Total a Pagar: $" + total);
        System.out.println("------------------------");
    
        // 🔹 Confirmar antes de proceder
        System.out.print("¿Desea continuar con el pago? (S/N): ");
        if (!scanner.nextLine().equalsIgnoreCase("S")) {
            System.out.println("❌ Pago cancelado. Puede modificar su compra si lo desea.");
            return;
        }
    
        System.out.println("\nSeleccione método de pago:");
        System.out.println("1. Débito");
        System.out.println("2. Crédito");
        System.out.println("3. Cancelar compra");
        System.out.print("Ingrese opción: ");
    
        int metodoPago = scanner.nextInt();
        scanner.nextLine(); 
    
        switch (metodoPago) {
            case 1 -> System.out.println("✔ Pago con tarjeta de débito procesado.");
            case 2 -> System.out.println("✔ Pago con tarjeta de crédito procesado.");
            case 3 -> {
                System.out.println("❌ Compra cancelada. Volviendo al menú principal...");
                return;
            }
            default -> {
                System.out.println("❌ Método inválido. Intente nuevamente.");
                return;
            }
        }
    
        System.out.println("\n✅ Pago exitoso. Gracias por su compra.");
    }

    private void salirMenu() {
        if (gestorVentas.tieneEntradasPendientes()) {
            System.out.println("\nTiene compras pendientes de pago.");
            System.out.println("Se le redirigirá automáticamente al menú de pago.");
            procesarPago();
        } else {
            System.out.println("\nGracias por usar nuestro sistema. ¡Hasta luego!");
        }
    }

    private void menu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Registrar nuevo cliente");
        System.out.println("2. Comprar entradas");
        System.out.println("3. Ver asientos disponibles");
        System.out.println("4. Promociones disponibles");
        System.out.println("5. Modificar compra");
        System.out.println("6. Pagar");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }
}