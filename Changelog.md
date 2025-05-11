# Historial de cambios

## 11/05/2025 - v1.1.1 Correcciones menores (last commit)
- Se corrige falla en el código que causaba que la asignación de ID para clientes iniciara en 1 en cada ejecución.

- Se corrige el sistema de GestorVentas para que lea correctamente la lista de clientes existentes.

---

### 10/05/2025 - V1.1 Sistema de ventas Teatro Moro (commit ccc12b3)
- El programa ahora cuenta con la capacidad de guardar la información de entradas vendidas y clientes registrados
    en ficheros .scv para recuperar la información en ejecuciones posteriores. Ahora cuando un cliente se registre
    solo deberá completar su información 1 vez, posterior a eso podrá comprar utilizando solo su ID.

- Se corrige la generación de la boleta para que muestre la información de forma correcta

- Se corrigen textos de diversos métodos para mejorar la experiencia de usuario.

---

### 10/05/2025 - Corrección opciones modificación de entradas (commit ba78019)
- Se corrige una serie de errores que impedían que el menu de modificación de compra funcionara correctamente
    - La opción de agregar entradas ya no pide la información duplicada y agrega las entradas extras al plano 
        de asientos.
    - La opción de eliminar ahora libera un asiento cuando es eliminado de la transacción en curso.
    - La opción de modificación ahora permite cambiar un asiento de zona mostrando el plano de entradas.

- Se crea la clase PersistenciaEntradas para implementar una base simple que guarde el historial de entradas
compradas, de esta forma no se perderá la información de entradas compradas (en desarrollo).

---

### 10/05/2025 - V1.0 Sistema de ventas Teatro Moro (commit bcc2856)
- Primera version funcional del sistema, actualmente el sistema posee las siguientes características:  
    - Registro de Clientes:
        Permite a los usuarios registrarse ingresando su nombre, correo, edad y género, asignándoles un ID único y determinando su tipo de cliente para aplicar descuentos.
    - Compra de Entradas:
        El sistema guía al usuario para seleccionar la zona y el asiento deseado dentro del teatro. Las zonas incluyen VIP, Platea Baja, Platea Alta, Palco y Galería, cada una con su propia configuración de asientos. Se asigna un identificador único a cada entrada y se aplican descuentos según el tipo de cliente.
    - Gestión de Ventas y Boleta:
        Las entradas compradas se asocian al cliente, se registran en el gestor de ventas y, al procesar el pago, se genera una boleta con el número formateado (iniciando en 0001) y el detalle de cada entrada, incluyendo el precio final y el descuento aplicado.
    - Interfaz de Usuario y Funcionalidades Adicionales:
        La aplicación cuenta con un menú interactivo que permite al usuario:
        - Registrar clientes.
        - Visualizar asientos disponibles en el teatro.
        - Consultar promociones.
        - Modificar compras (agregar, eliminar o cambiar asientos).
        - Procesar el pago mediante débito o crédito, utilizando la información registrada del cliente.

---

### 08/05/2025 - Implementación medios de pago (commit ad99686)
- Se implementan las opciones de pago débito y crédito para el menu de compra, ademas de una opción para
    cancelar la transacción.

---

### 08/05/2025 - Corrección interfaz usuario (commit c823335)
- Se corrigen las promociones vigentes con valores actualizados según requerimientos.

- Se actualiza la matriz de asientos para incluir las opciones solicitadas, ademas de los métodos y clases
    que hacen referencia a dichas matrices como la clase Teatro.

- Se actualiza la clase cliente para almacenar información  como el nombre, correo y edad, ademas se añaden 
    métodos que hagan uso de esa información para que el cliente solo deba proporcionar sus datos en la primera compra, luego de eso solo deberá indicar su ID.

- Ahora la clase InterfazUsuario permite registrar usuarios y recuperar su información para que deba ser ingresada
    en cada ocasión.

- Se actualiza el menu con las siguientes funciones:
    - permite registrar un usuario
    - Las zonas se muestran ahora como una lista
    - la opción de compra muestra los asientos disponibles antes de solicitar los datos
    - se muestra un detalle de las entradas seleccionadas antes de volver al menu principal
    - la opción de pago admite pagos con débito y crédito, ademas de la opción de cancelar la compra
    
---

### 08/05/2025 - Implementación clase GestorVentas y main (commit 5094fd2)
- Se implemento la clase GestorVentas para la administración de la compra y gestión de entradas en el sistema,
    dentro de las funciones principales de esta clase encontramos:
    - Generación de ID para las ventas.
    - Administración de la compra mediante los métodos agregarEntradas y eliminarEntradas.
    - Procesamiento de los pagos según las opciones disponibles.
    - Calculo de precios.

- También se implementa la clase principal (con el nombre de la evaluación), que tiene por función principal unir
    y llamar a las demás clases según sea necesario para la correcta ejecución del código.

---

### 08/05/2025 - Implementación clases Entrada y Cliente (commit efd4e5d)
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

---

### 07/05/2025 - Desarrollo clases auxiliares (commit 103bf9d)
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

---

### 05/05/2025 - Carga inicial (commit e99e7fc)
- Creación repositorio Evaluación Final Transversal con las siguientes clases:
    - Cliente           : para manejar los datos del comprador
    - Entrada           : maneja los datos de la entrada
    - GestorVentas      : gestionara la lógica del negocio
    - InterfazUsuario   : manejara la interacción del usuario con el programa
    - Teatro            : gestión de asientos y mapa del teatro

- Creación archivo Changelog para registrar cambios