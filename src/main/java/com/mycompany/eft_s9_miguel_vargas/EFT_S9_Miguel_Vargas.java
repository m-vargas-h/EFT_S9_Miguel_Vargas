
package com.mycompany.eft_s9_miguel_vargas;

public class EFT_S9_Miguel_Vargas {
    public static void main(String[] args) {
        // Instanciar los componentes clave del sistema
        GestorVentas gestorVentas = new GestorVentas();
        Teatro teatro = new Teatro();
        InterfazUsuario interfaz = new InterfazUsuario(gestorVentas, teatro);

        // Iniciar la interfaz del usuario
        interfaz.mostrarMenu();
    }
}
