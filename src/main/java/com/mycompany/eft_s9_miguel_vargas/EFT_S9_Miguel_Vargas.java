package com.mycompany.eft_s9_miguel_vargas;

import java.util.List;

public class EFT_S9_Miguel_Vargas {
    public static void main(String[] args) {
        // Creamos las instancias necesarias para la ejecución del código
        GestorVentas gestorVentas = new GestorVentas();
        Teatro teatro = new Teatro();
        InterfazUsuario interfaz = new InterfazUsuario(gestorVentas, teatro);

        // Cargamos los clientes registrados desde el archivo clientes.csv
        List<Cliente> clientesGuardados = PersistenciaClientes.leerClientes("clientes.csv");
        if (!clientesGuardados.isEmpty()) {
            System.out.println("Clientes registrados cargados correctamente.");
            gestorVentas.setListaClientes(clientesGuardados);
        } else {
            System.out.println("No se encontraron clientes registrados.");
        }

        // Cargamos las entradas compradas previamente desde el archivo entradas.csv
        List<Entrada> entradasGuardadas = PersistenciaEntradas.leerEntradas("entradas.csv");
        if (!entradasGuardadas.isEmpty()) {
            System.out.println("Cargando entradas compradas...");
            for (Entrada entrada : entradasGuardadas) {
                // Marcamos el asiento como ocupado en el plano
                teatro.asignarAsiento(entrada.getZona(), entrada.getFila(), entrada.getColumna());
                // Registramos la entrada en el gestor de ventas
                gestorVentas.agregarEntrada(entrada);
            }
        } else {
            System.out.println("No se encontraron entradas.");
        }

        // Iniciamos la interfaz de usuario, es decir el menu principal
        interfaz.mostrarMenu();
    }
}