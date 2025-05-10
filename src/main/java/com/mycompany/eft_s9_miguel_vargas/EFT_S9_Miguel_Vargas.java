
package com.mycompany.eft_s9_miguel_vargas;

import java.util.List;

public class EFT_S9_Miguel_Vargas {
    public static void main(String[] args) {
        // Instanciar los componentes clave del sistema
        GestorVentas gestorVentas = new GestorVentas();
        Teatro teatro = new Teatro();
        InterfazUsuario interfaz = new InterfazUsuario(gestorVentas, teatro);

        //Funcion experimental, no utilizar aun
        // Cargar entradas persistidas desde el archivo al iniciar la aplicación
        //List<Entrada> entradasGuardadas = PersistenciaEntradas.leerEntradas("entradas.csv");
        //if (!entradasGuardadas.isEmpty()) {
        //    System.out.println("Cargando entradas persistidas...");
        //    for (Entrada entrada : entradasGuardadas) {
        //        // Marca el asiento como ocupado según la información guardada
        //        teatro.asignarAsiento(entrada.getZona(), entrada.getFila(), entrada.getColumna());
        //        // Si es necesario, registra estas entradas en el gestor de ventas
        //        gestorVentas.agregarEntrada(entrada);
        //    }
        //} else {
        //    System.out.println("No se encontraron entradas persistidas.");
        //}

        // Iniciar la interfaz del usuario
        interfaz.mostrarMenu();
    }
}
