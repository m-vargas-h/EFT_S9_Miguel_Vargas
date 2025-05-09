
package com.mycompany.eft_s9_miguel_vargas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfazUsuario {

    private List<Entrada> entradasCompradas = new ArrayList<>();
    private Cliente clienteActual; // 🔹 Cliente accesible en toda la clase

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
            case 6 -> procesarPago(scanner);
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
    
        clienteActual = buscarClientePorID(idCliente);
    
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

    public void procesarPago(Scanner scanner) {  
        System.out.println("\n--- Procesar Pago ---");

        if (entradasCompradas.isEmpty()) {
            System.out.println("❌ No hay compras realizadas. Por favor, compre sus entradas antes de proceder al pago.");
            return;
        }

        // 🔹 Mostrar resumen de compra
        double total = 0;
        System.out.println("\n--- Resumen de Compra ---");
        System.out.println("Cantidad de Entradas: " + entradasCompradas.size());

        for (Entrada entrada : entradasCompradas) {
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

        System.out.println("\nSeleccione el medio de pago:");
        System.out.println("1. Débito");
        System.out.println("2. Crédito");
        System.out.println("3. Transferencia");
        System.out.println("4. Cancelar compra");
        System.out.print("Ingrese opción: ");

        int opcionPago = scanner.nextInt();
        scanner.nextLine();

        switch (opcionPago) {
            case 1 -> procesarPagoDebito(scanner);
            case 2 -> procesarPagoCredito(scanner);
            case 3 -> {
                System.out.println("❌ Compra cancelada. Vuelve pronto.");
                return;
            }
            default -> {
                System.out.println("❌ Opción inválida.");
                return;
            }
        }

        // 🔹 Generar boleta solo si el pago fue exitoso
        gestorVentas.generarBoleta(clienteActual);
        entradasCompradas.clear();
        System.out.println("✅ Compra completada correctamente.");
    }

    private void procesarPagoDebito(Scanner scanner) {
        System.out.print("Antes de continuar, ingrese su correo electrónico: ");
        String correo = scanner.nextLine();

        System.out.println("Procesando pago con tarjeta de débito...");
        esperarProcesamiento();

        System.out.println("✅ Pago confirmado. Su boleta y entradas serán enviadas al correo " + correo);
    }

    private void procesarPagoCredito(Scanner scanner) {
        System.out.print("Antes de continuar, ingrese su correo electrónico: ");
        String correo = scanner.nextLine();

        int cuotas;
        do {
            System.out.print("Seleccione el número de cuotas (1 a 12): ");
            cuotas = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (cuotas < 1 || cuotas > 12) {
                System.out.println("❌ Número de cuotas inválido. Intente nuevamente.");
            }
        } while (cuotas < 1 || cuotas > 12);

        System.out.println("Procesando pago con tarjeta de crédito en " + cuotas + " cuotas...");
        esperarProcesamiento();

        System.out.println("✅ Pago confirmado en " + cuotas + " cuotas. Su boleta y entradas serán enviadas al correo " + correo);
    }

    // 🔹 Simulación de procesamiento de pago
    private void esperarProcesamiento() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("❌ Error en la simulación de pago.");
        }
    }

    private void salirMenu() {
        if (gestorVentas.tieneEntradasPendientes()) {
            System.out.println("\nTiene compras pendientes de pago.");
            System.out.println("Se le redirigirá automáticamente al menú de pago.");
            procesarPago(scanner);
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