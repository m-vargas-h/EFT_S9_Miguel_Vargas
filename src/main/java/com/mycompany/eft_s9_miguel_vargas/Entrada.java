
package com.mycompany.eft_s9_miguel_vargas;

public class Entrada {
    private int idVenta;
    private String zona; // VIP, Normal, Palco
    private int fila;
    private int columna;
    private double precioBase;
    private char filaChar;
    private boolean esReserva;
    private double descuentoAplicado;
    private int zonaSeleccionada;

    public Entrada(int idVenta, String zona, int fila, int columna, double precioBase, char filaChar, boolean esReserva, double descuentoAplicado) {
        this.idVenta = idVenta;
        this.zona = zona;
        this.fila = fila;
        this.columna = columna;
        this.precioBase = precioBase;
        this.filaChar = filaChar;
        this.esReserva = esReserva;
        this.descuentoAplicado = descuentoAplicado;
    }

    // Métodos para obtener información clave de la entrada
    public boolean isReserva() {
        return esReserva;
    }

    public String getEntradaActualizada() {
        return "Venta ID: " + idVenta + " | Zona: " + zona + " | Asiento: " + filaChar + (columna + 1) +
               " | Precio final: $" + calcularPrecioFinal();
    }

    public int getZonaSeleccionada() { // 🔹 Método getter
        return zonaSeleccionada;
    }

    public String getZona() {
        return zona;
    }

    public char getFilaChar() {
        return filaChar;
    }

    public int getFila() {
        return fila;
    }
    
    public int getColumna() {
        return columna;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public int getIdVenta() {
        return idVenta;
    }

    // Métodos para modificar atributos de la entrada
    public void setZona(String nuevaZona) {
        this.zona = nuevaZona;
    }
    
    public void setFila(int nuevaFila) {
        this.fila = nuevaFila;
    }
    
    public void setColumna(int nuevaColumna) {
        this.columna = nuevaColumna;
    }
    
    public void setPrecioBase(double nuevoPrecioBase) {
        this.precioBase = nuevoPrecioBase;
    }
    
    public void setFilaChar(char nuevaFilaChar) {
        this.filaChar = nuevaFilaChar;
    }

    public void setReserva(boolean esReserva) {
        this.esReserva = esReserva;
    }

    public void setDescuentoAplicado(double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    // Calcula el precio final aplicando el descuento
    public double calcularPrecioFinal() {
        return precioBase * (1 - descuentoAplicado);
    }

    // Muestra la información de la entrada de manera clara
    @Override
    public String toString() {
        return "Venta ID: " + idVenta + " | Zona: " + zona + " | Asiento: " + filaChar + (columna + 1) +
               " | Precio final: $" + calcularPrecioFinal() + " | Descuento aplicado: " + (descuentoAplicado * 100) + "%";
    }
}