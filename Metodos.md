# Métodos del Proyecto EFT_S9_Miguel_Vargas

## Clase `EFT_S9_Miguel_Vargas`
### `public static void main(String[] args)`
**Descripción:** Método principal que inicia la ejecución del sistema, estableciendo instancias clave y cargando datos persistentes.  
**Parámetros:** `args` - Argumentos de línea de comandos (no utilizados en este caso).  
**Retorno:** No devuelve valor (`void`).  

---

# Métodos de la clase `Cliente`

## Constructor
### `public Cliente(String nombre, String correo, int edad, char genero, int idCliente)`
**Descripción:** Inicializa un nuevo cliente con sus datos personales, asigna un ID y determina su tipo de cliente.  
**Parámetros:**
- `nombre` - Nombre del cliente.
- `correo` - Correo electrónico del cliente.
- `edad` - Edad del cliente.
- `genero` - Género del cliente (`F` para femenino, `M` para masculino).
- `idCliente` - Identificador único del cliente.

**Retorno:** No devuelve valor (`void`).  

---

## Métodos privados
### `private String determinarTipoCliente()`
**Descripción:** Determina el tipo de cliente según edad y género.  
**Parámetros:** No recibe parámetros.  
**Retorno:** `String` con el tipo de cliente (`niño`, `estudiante`, `adulto mayor`, `mujer`, `general`).  

---

## Métodos públicos (Getters)
### `public int getIdCompraActual()`
**Descripción:** Obtiene el ID de la compra actual del cliente.  
**Retorno:** `int` con el ID de compra.  

### `public String getNombre()`
**Descripción:** Retorna el nombre del cliente.  
**Retorno:** `String` con el nombre.  

### `public String getCorreo()`
**Descripción:** Retorna el correo del cliente.  
**Retorno:** `String` con el correo.  

### `public int getEdad()`
**Descripción:** Retorna la edad del cliente.  
**Retorno:** `int` con la edad.  

### `public char getGenero()`
**Descripción:** Retorna el género del cliente.  
**Retorno:** `char` con el género (`F` o `M`).  

### `public int getIdCliente()`
**Descripción:** Retorna el identificador único del cliente.  
**Retorno:** `int` con el ID del cliente.  

### `public String getTipoCliente()`
**Descripción:** Retorna el tipo de cliente determinado.  
**Retorno:** `String` con la categoría del cliente.  

### `public List<Entrada> getEntradasCompradas()`
**Descripción:** Retorna la lista de entradas compradas por el cliente.  
**Retorno:** `List<Entrada>` con las entradas adquiridas.  

---

## Métodos públicos (Setters)
### `public void setIdCompraActual(int idCompraActual)`
**Descripción:** Establece el ID de la compra actual del cliente.  
**Parámetros:** `idCompraActual` - Nuevo ID de compra.  
**Retorno:** No devuelve valor (`void`).  

---

## Métodos de gestión de entradas
### `public void agregarEntrada(Entrada entrada)`
**Descripción:** Agrega una entrada a la lista del cliente, con un límite de 5 entradas.  
**Parámetros:** `entrada` - Objeto `Entrada` que representa la compra.  
**Retorno:** No devuelve valor (`void`).  

### `public void eliminarEntrada(int idVenta)`
**Descripción:** Elimina una entrada de la lista del cliente según su ID de venta.  
**Parámetros:** `idVenta` - Identificador de la venta asociada a la entrada.  
**Retorno:** No devuelve valor (`void`).  

### `public void mostrarEntradas()`
**Descripción:** Muestra todas las entradas compradas por el cliente en la consola.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

---

# Métodos de la clase `Entrada`

## Constructor
### `public Entrada(int idVenta, String zona, int fila, int columna, double precioBase, char filaChar, boolean esReserva, double descuentoAplicado)`
**Descripción:** Inicializa una nueva entrada con información detallada sobre ubicación, precio, estado de reserva y descuento aplicado.  
**Parámetros:**
- `idVenta` - Identificador único de la venta.
- `zona` - Zona del asiento dentro del teatro.
- `fila` - Número de fila del asiento.
- `columna` - Número de columna del asiento.
- `precioBase` - Precio base de la entrada.
- `filaChar` - Representación en letra de la fila.
- `esReserva` - Indica si la entrada es una reserva (`true` o `false`).
- `descuentoAplicado` - Descuento aplicado sobre el precio base.

**Retorno:** No devuelve valor (`void`).  

---

## Métodos públicos (Getters)
### `public boolean isReserva()`
**Descripción:** Verifica si la entrada corresponde a una reserva.  
**Retorno:** `boolean` - `true` si es una reserva, `false` si no lo es.  

### `public String getEntradaActualizada()`
**Descripción:** Retorna una representación en texto de la entrada con información clave y precio final calculado.  
**Retorno:** `String` con detalles de la venta, zona, asiento y precio final.  

### `public int getZonaSeleccionada()`
**Descripción:** Retorna el identificador de la zona seleccionada.  
**Retorno:** `int` con la zona seleccionada.  

### `public String getZona()`
**Descripción:** Retorna la zona del asiento.  
**Retorno:** `String` con la zona.  

### `public char getFilaChar()`
**Descripción:** Retorna el carácter correspondiente a la fila del asiento.  
**Retorno:** `char` con la letra de fila.  

### `public int getFila()`
**Descripción:** Retorna el número de fila del asiento.  
**Retorno:** `int` con el número de fila.  

### `public int getColumna()`
**Descripción:** Retorna el número de columna del asiento.  
**Retorno:** `int` con el número de columna.  

### `public double getPrecioBase()`
**Descripción:** Retorna el precio base de la entrada sin descuentos.  
**Retorno:** `double` con el precio.  

### `public double getDescuentoAplicado()`
**Descripción:** Retorna el porcentaje de descuento aplicado.  
**Retorno:** `double` con el descuento en formato decimal.  

### `public int getIdVenta()`
**Descripción:** Retorna el identificador único de la venta.  
**Retorno:** `int` con el ID de venta.  

---

## Métodos públicos (Setters)
### `public void setZona(String nuevaZona)`
**Descripción:** Modifica la zona del asiento.  
**Parámetros:** `nuevaZona` - Nueva zona asignada.  
**Retorno:** No devuelve valor (`void`).  

### `public void setFila(int nuevaFila)`
**Descripción:** Modifica el número de fila del asiento.  
**Parámetros:** `nuevaFila` - Nuevo número de fila.  
**Retorno:** No devuelve valor (`void`).  

### `public void setColumna(int nuevaColumna)`
**Descripción:** Modifica el número de columna del asiento.  
**Parámetros:** `nuevaColumna` - Nuevo número de columna.  
**Retorno:** No devuelve valor (`void`).  

### `public void setPrecioBase(double nuevoPrecioBase)`
**Descripción:** Modifica el precio base de la entrada.  
**Parámetros:** `nuevoPrecioBase` - Nuevo valor del precio base.  
**Retorno:** No devuelve valor (`void`).  

### `public void setFilaChar(char nuevaFilaChar)`
**Descripción:** Modifica la representación en letra de la fila del asiento.  
**Parámetros:** `nuevaFilaChar` - Nuevo carácter de fila.  
**Retorno:** No devuelve valor (`void`).  

### `public void setReserva(boolean esReserva)`
**Descripción:** Modifica el estado de reserva de la entrada.  
**Parámetros:** `esReserva` - Nuevo estado (`true` o `false`).  
**Retorno:** No devuelve valor (`void`).  

### `public void setDescuentoAplicado(double descuentoAplicado)`
**Descripción:** Modifica el porcentaje de descuento aplicado.  
**Parámetros:** `descuentoAplicado` - Nuevo porcentaje de descuento.  
**Retorno:** No devuelve valor (`void`).  

---

## Métodos de cálculo y representación
### `public double calcularPrecioFinal()`
**Descripción:** Calcula el precio final de la entrada después de aplicar el descuento.  
**Parámetros:** No recibe parámetros.  
**Retorno:** `double` con el precio final.  

### `public String toString()`
**Descripción:** Retorna una representación detallada de la entrada con información de venta, zona, asiento, precio final y descuento.  
**Parámetros:** No recibe parámetros.  
**Retorno:** `String` con los datos de la entrada.  

---

# Métodos de la clase `GestorVentas`

## Constructor
### `public GestorVentas()`
**Descripción:** Inicializa el gestor de ventas, creando listas para registrar entradas vendidas y clientes.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

### `public GestorVentas(List<Entrada> entradasVendidas)`
**Descripción:** Inicializa el gestor de ventas con una lista predefinida de entradas vendidas.  
**Parámetros:** `entradasVendidas` - Lista de entradas vendidas iniciales.  
**Retorno:** No devuelve valor (`void`).  

---

## Métodos públicos (Gestión de clientes)
### `public void setListaClientes(List<Cliente> listaClientes)`
**Descripción:** Asigna una lista de clientes al gestor de ventas.  
**Parámetros:** `listaClientes` - Nueva lista de clientes.  
**Retorno:** No devuelve valor (`void`).  

### `public List<Cliente> getListaClientes()`
**Descripción:** Retorna la lista de clientes registrados.  
**Retorno:** `List<Cliente>` con los clientes del sistema.  

### `public Cliente buscarClientePorID(int idCliente)`
**Descripción:** Busca un cliente por su identificador único.  
**Parámetros:** `idCliente` - Identificador del cliente a buscar.  
**Retorno:** `Cliente` encontrado o `null` si no existe.  

---

## Métodos públicos (Gestión de entradas)
### `public void agregarEntrada(Entrada entrada)`
**Descripción:** Agrega una entrada a la lista de entradas vendidas.  
**Parámetros:** `entrada` - Entrada adquirida por el cliente.  
**Retorno:** No devuelve valor (`void`).  

### `public void eliminarEntradaDeCliente(Cliente cliente, Scanner scanner, Teatro teatro)`
**Descripción:** Elimina una entrada del cliente y libera su asiento en el teatro.  
**Parámetros:** 
- `cliente` - Cliente que posee la entrada.  
- `scanner` - Objeto `Scanner` para ingresar datos desde la consola.  
- `teatro` - Instancia de teatro para liberar el asiento.  

**Retorno:** No devuelve valor (`void`).  

### `public List<Entrada> getEntradasPorCliente(int idCliente)`
**Descripción:** Retorna la lista de entradas compradas por un cliente específico.  
**Parámetros:** `idCliente` - Identificador del cliente.  
**Retorno:** `List<Entrada>` con las entradas adquiridas.  

---

## Métodos públicos (Procesos de venta)
### `public int generarIdVenta()`
**Descripción:** Genera un identificador único para cada venta.  
**Parámetros:** No recibe parámetros.  
**Retorno:** `int` con el nuevo identificador de venta.  

### `public void finalizarCompra()`
**Descripción:** Procesa el pago de las entradas pendientes y vacía la lista de entradas vendidas.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

### `public void cancelarCompra()`
**Descripción:** Cancela la compra, liberando todos los asientos asociados y eliminando las entradas vendidas.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

### `public boolean tieneEntradasPendientes()`
**Descripción:** Verifica si hay entradas pendientes de pago.  
**Parámetros:** No recibe parámetros.  
**Retorno:** `boolean` (`true` si hay entradas pendientes, `false` si no).  

### `public void modificarAsiento(int idVenta, String nuevaZona, char nuevaFilaChar, int nuevaColumna, Cliente cliente)`
**Descripción:** Modifica la ubicación del asiento de una entrada comprada por un cliente.  
**Parámetros:**  
- `idVenta` - Identificador de la entrada a modificar.  
- `nuevaZona` - Nueva zona asignada.  
- `nuevaFilaChar` - Nueva fila representada en letra.  
- `nuevaColumna` - Nueva columna asignada.  
- `cliente` - Cliente que posee la entrada.  

**Retorno:** No devuelve valor (`void`).  

---

## Métodos públicos (Cálculo de precios)
### `public double calcularPrecioPorZona(String zona)`
**Descripción:** Calcula el precio de una entrada según la zona del teatro.  
**Parámetros:** `zona` - Zona asignada al asiento.  
**Retorno:** `double` con el precio de la entrada en la zona indicada.  

### `public double obtenerDescuento(String tipoCliente)`
**Descripción:** Determina el descuento aplicable según el tipo de cliente.  
**Parámetros:** `tipoCliente` - Categoría del cliente (`niño`, `estudiante`, etc.).  
**Retorno:** `double` con el porcentaje de descuento aplicado.  

---

## Métodos públicos (Generación de boleta)
### `public void generarBoleta(Cliente cliente)`
**Descripción:** Genera una boleta de compra con detalles de las entradas adquiridas, precios y descuento aplicado.  
**Parámetros:** `cliente` - Cliente que realizó la compra.  
**Retorno:** No devuelve valor (`void`).  

---

# Métodos de la clase `InterfazUsuario`

## Constructor
### `public InterfazUsuario(GestorVentas gestorVentas, Teatro teatro)`
**Descripción:** Inicializa la interfaz de usuario con acceso al gestor de ventas, al teatro y al sistema de entrada de datos.  
**Parámetros:**  
- `gestorVentas` - Instancia del gestor de ventas del sistema.  
- `teatro` - Instancia del teatro para gestionar ubicaciones y disponibilidad.  
**Retorno:** No devuelve valor (`void`).  

---

## Métodos privados (Registro y búsqueda de clientes)
### `private void registrarCliente()`
**Descripción:** Permite registrar un nuevo cliente en el sistema, asignando un identificador único y almacenando los datos en un fichero de persistencia.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

### `private Cliente buscarClientePorID(int id)`
**Descripción:** Busca un cliente por su identificador único dentro de la lista de clientes registrados.  
**Parámetros:** `id` - ID del cliente que se busca.  
**Retorno:** `Cliente` encontrado o `null` si no existe.  

---

## Métodos públicos (Gestión de menú)
### `public void mostrarMenu()`
**Descripción:** Muestra el menú principal del sistema, permitiendo al usuario seleccionar opciones para realizar distintas acciones dentro del teatro.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

### `private boolean procesarOpcion(int opcion)`
**Descripción:** Procesa la opción seleccionada en el menú y ejecuta la acción correspondiente.  
**Parámetros:** `opcion` - Número de la opción elegida por el usuario.  
**Retorno:** `boolean` que indica si el sistema debe continuar ejecutándose (`true`) o salir (`false`).  

---

## Métodos privados (Compra de entradas)
### `private void comprarEntradas(int maxEntradas)`
**Descripción:** Permite a un cliente comprar hasta una cantidad máxima de entradas, seleccionando zona y asientos disponibles dentro del teatro.  
**Parámetros:** `maxEntradas` - Máximo de entradas permitidas por transacción.  
**Retorno:** No devuelve valor (`void`).  

### `public void agregarEntrada(Scanner scanner)`
**Descripción:** Permite a un cliente agregar más entradas a su compra dentro de los límites establecidos.  
**Parámetros:** `scanner` - Objeto `Scanner` para la entrada de datos desde la consola.  
**Retorno:** No devuelve valor (`void`).  

---

## Métodos privados (Visualización y modificación de entradas)
### `private void verAsientosDisponibles()`
**Descripción:** Muestra la disposición de asientos disponibles en el teatro.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

### `private void verPromociones()`
**Descripción:** Muestra las promociones y descuentos disponibles según el tipo de cliente.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

### `private void modificarCompra()`
**Descripción:** Permite modificar una compra, ofreciendo opciones para agregar, eliminar o cambiar la ubicación de una entrada adquirida.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

---

## Métodos de procesamiento de pago
### `public void procesarPago(Scanner scanner)`
**Descripción:** Procesa el pago de las entradas adquiridas, permitiendo seleccionar el método de pago y generando la boleta correspondiente.  
**Parámetros:** `scanner` - Objeto `Scanner` para capturar datos de la consola.  
**Retorno:** No devuelve valor (`void`).  

### `private void procesarPagoDebito(Scanner scanner)`
**Descripción:** Realiza el pago mediante tarjeta de débito, confirmando la transacción y enviando la boleta al correo del cliente.  
**Parámetros:** `scanner` - Objeto `Scanner` para la entrada de datos desde la consola.  
**Retorno:** No devuelve valor (`void`).  

### `private void procesarPagoCredito(Scanner scanner)`
**Descripción:** Procesa el pago mediante tarjeta de crédito, permitiendo elegir cuotas y enviando la boleta al cliente.  
**Parámetros:** `scanner` - Objeto `Scanner` para capturar la entrada del usuario.  
**Retorno:** No devuelve valor (`void`).  

### `private void esperarProcesamiento()`
**Descripción:** Simula el tiempo de espera en el proceso de pago.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

---

## Métodos de salida
### `private void salirMenu()`
**Descripción:** Verifica si el cliente tiene compras pendientes de pago y, si es necesario, redirige automáticamente al menú de pago antes de cerrar el sistema.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

### `private void menu()`
**Descripción:** Muestra las opciones del menú principal de manera estructurada para que el usuario pueda seleccionar acciones.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

---

# Métodos de la clase `PersistenciaClientes`

## Métodos de almacenamiento

### `public static void guardarCliente(Cliente cliente, String rutaArchivo)`
**Descripción:** Guarda la información de un cliente en un archivo CSV, agregando una nueva línea con los datos del cliente.  
**Parámetros:**  
- `cliente` - Cliente a guardar.  
- `rutaArchivo` - Ruta del archivo CSV donde se almacenarán los datos.  
**Retorno:** No devuelve valor (`void`).  
**Excepciones manejadas:** `IOException` en caso de error al escribir en el archivo.  

---

### `public static void escribirClientes(List<Cliente> clientes, String rutaArchivo)`
**Descripción:** Sobrescribe el archivo CSV con la lista completa de clientes.  
**Parámetros:**  
- `clientes` - Lista de clientes que se almacenará en el archivo.  
- `rutaArchivo` - Ruta del archivo CSV donde se guardarán los datos.  
**Retorno:** No devuelve valor (`void`).  
**Excepciones manejadas:** `IOException` en caso de error al escribir en el archivo.  

---

## Métodos de lectura

### `public static List<Cliente> leerClientes(String rutaArchivo)`
**Descripción:** Lee el archivo CSV y retorna una lista de clientes con los datos cargados.  
**Parámetros:**  
- `rutaArchivo` - Ruta del archivo CSV que contiene la información de los clientes.  
**Retorno:** `List<Cliente>` con los clientes registrados en el archivo.  
**Excepciones manejadas:**  
- `IOException` en caso de error al leer el archivo.  
- `NumberFormatException` si hay problemas con los datos numéricos.  
**Consideraciones:**  
- Se omiten líneas vacías.  
- Se eliminan posibles caracteres BOM (`\uFEFF`) en el identificador del cliente.  

---

# Métodos de la clase `PersistenciaEntradas`

## Métodos de almacenamiento

### `public static void guardarEntrada(Entrada entrada, String rutaArchivo)`
**Descripción:** Guarda la información de una entrada en un archivo CSV, agregando una nueva línea con los datos de la entrada.  
**Parámetros:**  
- `entrada` - Entrada que se almacenará.  
- `rutaArchivo` - Ruta del archivo CSV donde se guardará la información.  
**Retorno:** No devuelve valor (`void`).  
**Excepciones manejadas:** `IOException` en caso de error al escribir en el archivo.  

---

### `public static void escribirEntradas(List<Entrada> entradas, String rutaArchivo)`
**Descripción:** Sobrescribe el archivo CSV con la lista completa de entradas almacenadas.  
**Parámetros:**  
- `entradas` - Lista de entradas a escribir en el archivo.  
- `rutaArchivo` - Ruta del archivo CSV donde se guardará la información.  
**Retorno:** No devuelve valor (`void`).  
**Excepciones manejadas:** `IOException` en caso de error al escribir en el archivo.  

---

## Métodos de lectura

### `public static List<Entrada> leerEntradas(String rutaArchivo)`
**Descripción:** Lee el archivo CSV y retorna una lista de entradas con los datos cargados.  
**Parámetros:**  
- `rutaArchivo` - Ruta del archivo CSV que contiene la información de las entradas.  
**Retorno:** `List<Entrada>` con las entradas registradas en el archivo.  
**Excepciones manejadas:**  
- `IOException` en caso de error al leer el archivo.  
- `NumberFormatException` si hay problemas con los datos numéricos.  
**Consideraciones:**  
- Se omiten líneas vacías.  
- Se valida que cada línea contenga los campos esperados antes de procesarla.  

---

# Métodos de la clase `Teatro`

## Constructor

### `public Teatro()`
**Descripción:** Inicializa la instancia del teatro y configura todos los asientos como libres.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

---

## Métodos de inicialización

### `private void inicializarAsientos()`
**Descripción:** Establece todos los asientos en estado **libre** (`false`) dentro de cada zona del teatro.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

---

## Métodos de acceso a zonas

### `public boolean[][] obtenerZona(String zona)`
**Descripción:** Retorna la matriz de disponibilidad de asientos para la zona especificada.  
**Parámetros:**  
- `zona` - Nombre de la zona (`vip`, `palco`, `platea baja`, `platea alta`, `galeria`).  
**Retorno:** `boolean[][]` con los asientos disponibles (`false`) y ocupados (`true`).  
**Consideraciones:**  
- Retorna una matriz vacía si la zona ingresada no es válida.  

---

## Métodos de visualización

### `public void mostrarPlanoGeneral()`
**Descripción:** Muestra la distribución completa de los asientos en todas las zonas del teatro.  
**Parámetros:** No recibe parámetros.  
**Retorno:** No devuelve valor (`void`).  

### `public void mostrarZona(String zona)`
**Descripción:** Muestra la disposición de los asientos para la zona indicada, representando asientos ocupados (`X`) y libres (`O`).  
**Parámetros:**  
- `zona` - Nombre de la zona a visualizar.  
**Retorno:** No devuelve valor (`void`).  
**Consideraciones:**  
- Si la zona ingresada no es válida, se muestra un mensaje de error.  

---

## Métodos de asignación y liberación de asientos

### `public boolean asignarAsiento(String zona, int fila, int asiento)`
**Descripción:** Intenta asignar un asiento dentro de la zona especificada, verificando su disponibilidad.  
**Parámetros:**  
- `zona` - Zona del teatro donde se encuentra el asiento.  
- `fila` - Número de fila del asiento.  
- `asiento` - Número de columna del asiento.  
**Retorno:**  
- `true` si la asignación es exitosa.  
- `false` si el asiento ya está ocupado o la ubicación es inválida.  

### `public boolean liberarAsiento(String zona, int fila, int asiento)`
**Descripción:** Libera un asiento previamente asignado en la zona especificada.  
**Parámetros:**  
- `zona` - Zona del teatro donde se encuentra el asiento.  
- `fila` - Número de fila del asiento.  
- `asiento` - Número de columna del asiento.  
**Retorno:**  
- `true` si la liberación es exitosa.  
- `false` si el asiento ya estaba libre o la ubicación es inválida.  

---

## Métodos auxiliares

### `private boolean validarUbicacion(boolean[][] zona, int fila, int asiento)`
**Descripción:** Verifica si la fila y la columna proporcionadas están dentro de los límites de la matriz de la zona.  
**Parámetros:**  
- `zona` - Matriz de asientos de la zona seleccionada.  
- `fila` - Índice de fila del asiento.  
- `asiento` - Índice de columna del asiento.  
**Retorno:**  
- `true` si la ubicación es válida.  
- `false` si los índices están fuera de los límites de la zona.  
