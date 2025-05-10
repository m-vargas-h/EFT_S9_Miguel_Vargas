# Sistema de Venta de Entradas - Teatro Moro

Este proyecto es un sistema de venta de entradas para el **Teatro Moro** desarrollado en Java. El programa permite procesar la compra de entradas, aplicar descuentos en pesos, calcular impuestos y generar una boleta (recibo) detallada de cada compra.

## Características

- **Compra de Entradas:**  
  Permite la selección de asientos en diferentes zonas del teatro (ej. platea alta, palco, VIP, etc.) mediante una interfaz de usuario sencilla.

- **Aplicación de Descuentos:**  
  Los descuentos se aplican como un valor absoluto en pesos al precio base de cada entrada, en lugar de porcentajes.

- **Cálculo de Impuestos:**  
  Una vez aplicado el descuento, el sistema extrae el monto neto y calcula el IVA correspondiente (19%), manteniendo claridad en el desglose.

- **Generación de Boletas Detalladas:**  
  Se genera un recibo con el siguiente desglose:
  - Número de boleta (formateado con ceros a la izquierda).
  - Detalle de cada entrada (zona, asiento, precio base y descuento aplicado en pesos).
  - Resumen de la compra: Subtotal (neto sin IVA), IVA calculado y total final a pagar.

- **Modularidad y Mantenimiento:**  
  El sistema está estructurado en clases como `GestorVentas`, `Teatro`, `InterfazUsuario`, `Cliente` y `Entrada`, manteniendo el código organizado y facilitando futuras mejoras o ampliaciones.

## Cómo Funciona

1. **Llamada de Componentes Clave:**  

   En el método principal del programa se crean las instancias de las clases encargadas de atender las ventas y la gestión del teatro:
   ```java
   GestorVentas gestorVentas = new GestorVentas();
   Teatro teatro = new Teatro();
   InterfazUsuario interfaz = new InterfazUsuario(gestorVentas, teatro);

Esto configura el entorno en el que se realizará la venta de entradas.

2. **Proceso de Compra:**

    El usuario ingresa la fila y el número del asiento desde la interfaz. Se generan instancias de la clase Entrada que contienen la zona, el precio base y el descuento aplicado (en pesos). Estas entradas se agregan al objeto Cliente.

3. **Generación de la Boleta:**

    Al finalizar la compra, se llama al método generarBoleta(Cliente cliente), el cual:
    - Imprime la cabecera de la boleta con el número de compra.
    - Recorre las entradas adquiridas, mostrando para cada una el precio base y el valor del descuento (en pesos).
    - Calcula el precio final de cada entrada (precio base menos descuento), extrae el monto neto sin IVA y calcula el IVA (19%).
    - Muestra un resumen final con el subtotal neto, total del IVA y monto total a pagar.
    - Incrementa el número de boleta para la próxima compra.

4. **El proceso de cálculos se basa en la siguiente lógica:**
    - Precio final con descuento:

        precioConDescuento = precioBase - descuentoPesos
    - Extracción del neto y cálculo del IVA (asumiendo que el precio final incluye IVA):

        netoSinIVA = precioConDescuento / 1.19
ivaPorEntrada = precioConDescuento - netoSinIVA

5. **Formato de Salida:**

    Se utiliza la función System.out.printf para asegurar que los precios se presenten correctamente con dos decimales, evitando notaciones científicas.

## Estructura del proyecto
- **Gestor de ventas:** Encargado de la lógica de venta de entradas y gestión de operaciones.

- **Teatro:** Administra la distribución y configuración de asientos y gestión de operaciones.

- **Interfaz Usuario:** Facilita la interacción con el usuario, integrando funciones de venta y selección de asientos.

- **Cliente y Entrada:** Clases que registran información relevante de clientes registrados y entradas vendidas.

- **Clases persistentes:** Almacenan la información captura por la clase Entrada y Cliente para guardarlos en ficheros de formato .scv y asi facilitar la lectura posterior de datos.

## Requisitos
- Java 8 o superior.
- Cualquier IDE de Java (como Eclipse, IntelliJ IDEA o NetBeans) o uso de la línea de comandos.
