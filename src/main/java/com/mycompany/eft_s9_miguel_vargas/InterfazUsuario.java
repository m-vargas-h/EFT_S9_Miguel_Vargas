
package com.mycompany.eft_s9_miguel_vargas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfazUsuario {

    private Cliente clienteActual; // Cliente accesible en toda la clase

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
        scanner.nextLine();

        System.out.print("Ingrese su género (M/F): ");
        char genero = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();

        // Generar ID único
        int idCliente = contadorID++;

        // Crear nuevo cliente y agregarlo a la lista
        Cliente nuevoCliente = new Cliente(nombre, correo, edad, genero, idCliente);
        clientesRegistrados.add(nuevoCliente);

        System.out.println("\n✔ Cliente registrado correctamente.");
        System.out.println("ID Asignado: " + idCliente);

        // Registramos el cliente en el fichero para tener una bse de datos actualizada
        PersistenciaClientes.guardarCliente(nuevoCliente, "clientes.csv");
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
            scanner.nextLine();
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
    
        clienteActual = gestorVentas.buscarClientePorID(idCliente);
    
        if (clienteActual == null) {
            System.out.println("Cliente no encontrado. Verifique su ID.");
            return;
        }
    
        System.out.println("\nCliente identificado: " + clienteActual.getNombre());
        System.out.println("Correo: " + clienteActual.getCorreo());
        System.out.println("Tipo de Cliente: " + clienteActual.getTipoCliente());
    
        int cantidadEntradas;
        do {
            System.out.print("\n¿Cuántas entradas desea comprar? (1-" + maxEntradas + "): ");
            cantidadEntradas = scanner.nextInt();
            scanner.nextLine(); 
    
            if (cantidadEntradas < 1 || cantidadEntradas > maxEntradas) {
                System.out.println("Número inválido. Debe elegir entre 1 y " + maxEntradas + " entradas.");
            }
        } while (cantidadEntradas < 1 || cantidadEntradas > maxEntradas);
    
        List<Entrada> entradasCompradas = new ArrayList<>();
    
        for (int i = 0; i < cantidadEntradas; i++) {
            System.out.println("\nSeleccionando Entrada #" + (i + 1));
    
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
                case 5 -> "galeria";
                default -> {
                    System.out.println("Opción inválida. Intente nuevamente.");
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
                clienteActual.agregarEntrada(entrada);
                System.out.println("Entrada #" + (i + 1) + " asignada correctamente.");
            } else {
                System.out.println("Asiento no disponible, elija otro.");
                i--;
            }
        }
    
        // Mostrar resumen solo al final
        System.out.println("\n--- Detalle de Compra ---");
        for (int i = 0; i < entradasCompradas.size(); i++) {
            Entrada entrada = entradasCompradas.get(i);
            System.out.println("E" + (i + 1) + ", ID Venta: " + entrada.getIdVenta() +
                               ", Zona: " + entrada.getZona() + 
                               ", Asiento: " + entrada.getFilaChar() + (entrada.getColumna() + 1) + 
                               ", Descuento aplicado: $" + entrada.getDescuentoAplicado() + 
                               ", Precio final: $" + (entrada.getPrecioBase() - entrada.getDescuentoAplicado()));
        }
        System.out.println("-------------------------");
    }

    public void agregarEntrada(Scanner scanner) {
        System.out.println("\n--- Agregar Entrada ---");

        // Se obtiene el número de entradas compradas en la transacción actual.
        int currentCount = clienteActual.getEntradasCompradas().size();
        int maxAllowed = 5; // Límite total permitido por transacción
        int disponibles = maxAllowed - currentCount;
    
        if (disponibles <= 0) {
            System.out.println("No puedes agregar más entradas en esta transacción.");
            return;
        }
    
        System.out.println("Actualmente tienes " + currentCount + " entrada(s).");
        System.out.println("Puedes agregar hasta " + disponibles + " entrada(s) más.");
    
        System.out.print("¿Cuántas entradas deseas agregar? (1-" + disponibles + "): ");
        int cantidadAAgregar = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        if (cantidadAAgregar < 1 || cantidadAAgregar > disponibles) {
            System.out.println("Cantidad inválida. Solo puedes agregar entre 1 y " + disponibles + " entrada(s).");
            return;
        }
    
        comprarEntradas(clienteActual.getIdCliente());
    }

    private void verAsientosDisponibles() {
        System.out.println("\n--- Plano Completo del Teatro ---");
        teatro.mostrarPlanoGeneral();
    }

    private void verPromociones() {
        System.out.println("\n--- Promociones Vigentes ---");
        System.out.println("1. Descuento del 10% para niños.");
        System.out.println("2. Descuento del 20% para mujeres.");
        System.out.println("3. Descuento del 15% para estudiantes.");
        System.out.println("4. Descuento del 25% para personas de tercera edad.");
    }

    private void modificarCompra() {
        System.out.println("\n--- Modificar Compra ---");
        System.out.println("1. Agregar entrada.");
        System.out.println("2. Eliminar entrada.");
        System.out.println("3. Modificar asiento.");
    
        int opcion = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Ingrese su ID de cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
    
        Cliente clienteActual = buscarClientePorID(idCliente);
    
        if (clienteActual == null) {
            System.out.println("Cliente no encontrado. Verifique su ID.");
            return;
        }
    
        int entradasActuales = clienteActual.getEntradasCompradas().size();
        int maxEntradasDisponibles = 5 - entradasActuales;
    
        switch (opcion) {
            case 1 -> {
                if (maxEntradasDisponibles <= 0) {
                    System.out.println("No puede agregar más entradas. Ya tiene el máximo permitido.");
                } else {
                    System.out.println("\nActualmente tiene " + entradasActuales + " entradas.");
                    System.out.println("Puede agregar hasta " + maxEntradasDisponibles + " más.");

                    agregarEntrada(scanner);

                }
            }
            case 2 -> {
                gestorVentas.eliminarEntradaDeCliente(clienteActual, scanner, teatro);
            }

            case 3 -> {
                // Primero verificamos y mostramos las entradas del cliente para modificar
                if (clienteActual.getEntradasCompradas().isEmpty()) {
                    System.out.println("No tiene entradas para modificar.");
                    break;
                }
    
                System.out.println("Sus entradas:");
                for (Entrada e : clienteActual.getEntradasCompradas()) {
                    System.out.println("ID: " + e.getIdVenta() +
                                        " | Zona: " + e.getZona() +
                                        " | Asiento: " + e.getFilaChar() + (e.getColumna() + 1));
                }
    
                System.out.print("Ingrese el ID de la entrada a modificar: ");
                int idVenta = scanner.nextInt();
                scanner.nextLine();

                // Desplegar lista de zonas disponibles
                String[] zonasDisponibles = {"VIP", "Palco", "Platea Baja", "Platea Alta", "Galeria"};  // Puedes ajustar el arreglo según corresponda
                System.out.println("Zonas disponibles:");
                for (int i = 0; i < zonasDisponibles.length; i++) {
                    System.out.println((i + 1) + ". " + zonasDisponibles[i]);
                }
    
                System.out.print("Ingrese el número de la nueva zona: ");
                int opcionZona = scanner.nextInt();
                scanner.nextLine();

                if (opcionZona < 1 || opcionZona > zonasDisponibles.length) {
                    System.out.println("Opción de zona inválida.");
                    break;
                }
                // Se guarda la zona seleccionada (se pasa a minúsculas o se deja como desees)
                String nuevaZona = zonasDisponibles[opcionZona - 1].toLowerCase();

                // Mostrar el mapa de asientos para la zona seleccionada
                System.out.println("Mostrando mapa de asientos para la zona " + nuevaZona + ":");
                // Supongamos que tienes un método en la clase Teatro:
                teatro.mostrarZona(nuevaZona);
                // Si no cuentas con este método, podrías implementar un ejemplo simple aquí

                System.out.print("Ingrese nueva fila (A, B, C...): ");
                char nuevaFilaChar = scanner.next().toUpperCase().charAt(0);

                System.out.print("Ingrese nuevo número de asiento: ");
                int nuevaColumna = scanner.nextInt() - 1;
                scanner.nextLine(); // Limpiar buffer

                // Se llama al método que modifica el asiento buscándolo en las entradas del cliente
                gestorVentas.modificarAsiento(idVenta, nuevaZona, nuevaFilaChar, nuevaColumna, clienteActual);
            }

            default -> System.out.println("Opción inválida.");
        }
    }

    public void procesarPago(Scanner scanner) {  
        List<Entrada> entradas = clienteActual.getEntradasCompradas();
        /*Esta linea es utilizada para verificar que las entradas se estuviesen cargando bien antes de proceder
         * con el pago, incluir en el código solo para pruebas de código
         */
        //System.out.println("🔍 Entradas recuperadas para pago: " + entradas);

        System.out.println("\n--- Procesar Pago ---");

        if (entradas.isEmpty()) { 
            System.out.println("No hay compras realizadas. Por favor, compre sus entradas antes de proceder al pago.");
            return;
        }

        // Mostrar resumen de compra
        double total = 0;
        System.out.println("\n--- Resumen de Compra ---");
        System.out.println("Cantidad de Entradas: " + entradas.size()); 

        for (Entrada entrada : entradas) {
            double precioFinal = entrada.getPrecioBase() - entrada.getDescuentoAplicado();
            total += precioFinal;
        }

        System.out.println("Total a Pagar: $" + total);
        System.out.println("------------------------");

        // Confirmar antes de proceder
        System.out.print("¿Desea continuar con el pago? (S/N): ");
        if (!scanner.nextLine().equalsIgnoreCase("S")) {
            System.out.println("Pago cancelado. Puede modificar su compra si lo desea.");
            return;
        }

        System.out.println("\nSeleccione el medio de pago:");
        System.out.println("1. Débito");
        System.out.println("2. Crédito");
        System.out.println("3. Cancelar compra");
        System.out.print("Ingrese opción: ");

        int opcionPago = scanner.nextInt();
        scanner.nextLine();

        switch (opcionPago) {
            case 1 -> procesarPagoDebito(scanner);
            case 2 -> procesarPagoCredito(scanner);
            case 3 -> {
                System.out.println("Compra cancelada. Vuelve pronto.");
                return;
            }
            default -> {
                System.out.println("Opción inválida.");
                return;
            }
        }

        //Generar boleta solo si el pago fue exitoso
        /* Al igual que la linea del inicio del método, esta es utilizada solo con fines de depuración, para poder
         * hacer las pruebas necesarias sobre como se registra la información, no incluir en el código final
         */
        //System.out.println("🔍 Generando boleta con entradas: " + entradas);
        gestorVentas.generarBoleta(clienteActual);

        // Persistencia: Guarda cada entrada en el archivo CSV
        for (Entrada entrada : entradas) {
            PersistenciaEntradas.guardarEntrada(entrada, "entradas.csv");
        }
        

        entradas.clear(); // Limpia las entradas registradas tras el pago exitoso
        System.out.println("Compra completada correctamente.");

    }

    private void procesarPagoDebito(Scanner scanner) {
        // Utiliza el correo ya registrado para el cliente actual
        String correo = clienteActual.getCorreo();

        System.out.println("Procesando pago con tarjeta de débito...");
        esperarProcesamiento();

        System.out.println("✅ Pago confirmado. Su boleta y entradas serán enviadas al correo " + correo);
    }

    private void procesarPagoCredito(Scanner scanner) {
        // Utiliza el correo ya registrado para el cliente actual
        String correo = clienteActual.getCorreo();

        int cuotas;
        do {
            System.out.print("Seleccione el número de cuotas (1 a 12): ");
            cuotas = scanner.nextInt();
            scanner.nextLine();

            if (cuotas < 1 || cuotas > 12) {
                System.out.println("Número de cuotas inválido. Intente nuevamente.");
            }
        } while (cuotas < 1 || cuotas > 12);

        System.out.println("Procesando pago con tarjeta de crédito en " + cuotas + " cuotas...");
        esperarProcesamiento();

        System.out.println("Pago confirmado en " + cuotas + " cuotas. Su boleta y entradas serán enviadas al correo " + correo);
    }

    // Simulación de procesamiento de pago
    private void esperarProcesamiento() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Error en la simulación de pago.");
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