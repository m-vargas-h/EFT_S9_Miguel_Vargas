
package com.mycompany.eft_s9_miguel_vargas;

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
            case 1 -> comprarEntradas();
            case 2 -> verAsientosDisponibles();
            case 3 -> verPromociones();
            case 4 -> modificarCompra();
            case 5 -> procesarPago();
            case 6 -> {
                salirMenu();
                return false;
            }
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
        return true;
    }

    private void comprarEntradas() {
        System.out.println("\n--- Compra de Entradas ---");
        System.out.println("Máximo 5 entradas por transacción.");

        for (int i = 0; i < 5; i++) {
            System.out.print("Ingrese tipo de cliente (Niño, Estudiante, Adulto Mayor, General): ");
            String tipoCliente = scanner.nextLine();
            System.out.print("Ingrese zona (VIP, Normal, Palco): ");
            String zona = scanner.nextLine().toLowerCase();
            System.out.print("Ingrese fila (A, B, C...): ");
            char filaChar = scanner.next().toUpperCase().charAt(0);
            System.out.print("Ingrese número de asiento: ");
            int asiento = scanner.nextInt() - 1;
            scanner.nextLine(); // Limpiar buffer

            int fila = filaChar - 'A';

            if (teatro.asignarAsiento(zona, fila, asiento)) {
                Entrada entrada = new Entrada(tipoCliente, zona + "-" + filaChar + "-" + (asiento + 1));
                gestorVentas.agregarEntrada(entrada);
                System.out.println("✔ Entrada asignada correctamente.");
            } else {
                System.out.println("❌ Asiento no disponible, elija otro.");
                i--; // Permitir que el usuario vuelva a ingresar un asiento válido
            }

            System.out.print("¿Desea comprar otra entrada? (S/N): ");
            if (!scanner.nextLine().equalsIgnoreCase("S")) break;
        }
    }

    private void verAsientosDisponibles() {
        System.out.println("\n--- Plano Completo del Teatro ---");
        teatro.mostrarPlanoGeneral();
    }

    private void verPromociones() {
        System.out.println("\n--- Promociones Vigentes ---");
        System.out.println("1. Descuento del 50% para niños.");
        System.out.println("2. Descuento del 30% para estudiantes.");
        System.out.println("3. Descuento del 40% para adultos mayores.");
    }

    private void modificarCompra() {
        System.out.println("\n--- Modificar Compra ---");
        System.out.println("1. Agregar entrada.");
        System.out.println("2. Eliminar entrada.");
        System.out.println("3. Modificar asiento.");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> comprarEntradas();
            case 2 -> gestorVentas.eliminarEntrada(scanner);
            case 3 -> gestorVentas.modificarAsiento(scanner);
            default -> System.out.println("Opción inválida.");
        }
    }

    private void procesarPago() {
        System.out.println("\n--- Opciones de Pago ---");
        System.out.println("1. Débito.");
        System.out.println("2. Crédito.");
        System.out.println("3. Transferencia.");
        System.out.println("4. Cancelar compra.");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (opcion == 4) {
            System.out.println("❌ Compra cancelada. Los asientos han sido liberados.");
            gestorVentas.cancelarCompra();
        } else {
            System.out.println("✔ Pago procesado correctamente.");
            gestorVentas.finalizarCompra();
        }
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
        System.out.println("1. Comprar entradas (Máximo 5 por transacción)");
        System.out.println("2. Ver asientos disponibles");
        System.out.println("3. Promociones disponibles");
        System.out.println("4. Modificar compra (agregar, eliminar, modificar)");
        System.out.println("5. Pagar (Débito, crédito, transferencia o cancelar)");
        System.out.println("6. Salir (redirige al pago si hay compras pendientes)");
        System.out.print("Seleccione una opción: ");
    }
}