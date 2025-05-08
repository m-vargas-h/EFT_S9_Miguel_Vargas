# Historial de cambios

## 08/05/2025 - Implementación clases Entrada y Cliente (last commit)
- Se implemento la clase Entrada usando como base el proyecto anterior, esta clase gestiona la información
    de cada boleto comprado, dentro de sus funcionalidades se encuentra:
    - Registrar información clave de la entrada, como la zona, el precio, asiento (fila y columna).
    - Permite la impresión en pantalla de estos detalles para tener un resumen claro de la compra.
    - Permite la modificación de una reserva antes de proceder con el pago.

- Se agrego la clase Cliente, que nos permite gestionar la información clave de los clientes, como por ejemplo
    nombre, edad, tipo de cliente y entradas compradas, algunas de sus funciones son:
    - Registrar la información del cliente.
    - Gestionar la compra de entradas, asi como su modificación.
    - Mantener un historial de compras, para recuperar comprar anteriores.

## 07/05/2025 - Desarrollo clases auxiliares (commit 103bf9d)
- Se comienza el trabajo en la clase Teatro que estará encargada de la gestión de asientos:
    - Se crearon matrices booleanas para gestionar los asientos.
    - Se definió el método mostrarPlanoGeneral para mostrar el estado actual de los asientos del teatro.
    - Se definió el método asignarAsiento para verificar la disponibilidad de un asiento al ser elegido.
    - Se definió el método liberarAsiento para modificaciones de asientos antes de proceder al pago.
    - Se plantearon de modo general otras clases para diversos menu, asi como la lógica básica para la elección
        de asientos.

- Se comienza el trabajo en la clase InterfazUsuario: 
    - Es capaz de mostrar el menu principal y registrar la opción elegida por el usuario.
    - Permite al usuario hacer la compra de hasta 5 entradas por transacción.
    - Puede acceder a las promociones vigentes.
    - Permite al usuario entrar en las opciones de modificación de compras.
    - Es capaz de gestionar el pago de las compras, asi como de evitar que el usuario salga del programa
        dejando compras pendientes.

### 05/05/2025 - Carga inicial (commit e99e7fc)
- Creación repositorio Evaluación Final Transversal con las siguientes clases:
    - Cliente           : para manejar los datos del comprador
    - Entrada           : maneja los datos de la entrada
    - GestorVentas      : gestionara la lógica del negocio
    - InterfazUsuario   : manejara la interacción del usuario con el programa
    - Teatro            : gestión de asientos y mapa del teatro

- Creación archivo Changelog para registrar cambios