package com.chema.tienda.relojes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tabla_relojes")
public class Reloj {

    @Size(min = 3, max = 40, message = "La marca es obligatoria y debe tener entre 3 y 40 caracteres")
    @Pattern(regexp = "[a-záéíóúñA-ZÁÉÍÓÚÑ 0-9]*",
            message = "Marca solo puede tener letras, números y espacios")
    private String marca;

    @Size(min = 3, max = 40, message = "El modelo es obligatorio y debe tener entre 3 y 40 caracteres")
    @Pattern(regexp = "[a-záéíóúñA-ZÁÉÍÓÚÑ 0-9]*",
            message = "Modelo solo puede tener letras, números y espacios")
    private String modelo;

    @Min(value = 1, message = "Precio mínimo 1 euro")
    @Max(value = 99999, message = "El precio máximo es 99999 euros")
    private double precio;

    @Min(value = 1, message = "Stock mínimo 1 unidad")
    @Max(value = 99999, message = "El stock máximo es 999 unidades")
    private int stock;

    @Size(min = 3, max = 40, message = "El tipo de movimiento es obligatorio y debe tener entre 3 y 40 caracteres")
    @Pattern(regexp = "[a-záéíóúñA-ZÁÉÍÓÚÑ 0-9]*",
            message = "Tipo de movimiento solo puede tener letras, números y espacios")
    private String tipoMovimiento;

    @Min(value = 1, message = "Resistencia agua mínimo 1 atm")
    @Max(value = 99999, message = "La resistencia al agua máxima es de 1000 atm")
    private int resistenciaAgua;

    @Size(min = 3, max = 40, message = "El género es obligatorio y debe tener entre 3 y 40 caracteres")
    @Pattern(regexp = "[a-záéíóúñA-ZÁÉÍÓÚÑ ]*",
            message = "Género solo puede tener letras y espacios")
    private String genero;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Este campo va a ser solo para gestionar
    // la subida de archivo, es lo que va a
    // ser el input tipe="file"
    // esto es solo para recibir facilmente la imagen del form
    // @Transient es para decir que el campo sea ignorado de cara a la base de datos


    @Transient
    private MultipartFile fotoSubida;

    // El siguiente campo es para
    // almacenar la imagen en BBDD
    // @Lob es para tipos de columna,
    // p. ej blob o clob, para guardar imagenes/archivos/arrays de bytes
    @Lob
    private byte[] imagenPortada;


    public Reloj() {
    }

    public Reloj(String marca, String modelo, double precio, int stock, String tipoMovimiento, int resistenciaAgua, String genero) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.tipoMovimiento = tipoMovimiento;
        this.resistenciaAgua = resistenciaAgua;
        this.genero = genero;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getResistenciaAgua() {
        return resistenciaAgua;
    }

    public void setResistenciaAgua(int resistenciaAgua) {
        this.resistenciaAgua = resistenciaAgua;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MultipartFile getFotoSubida() {
        return fotoSubida;
    }

    public void setFotoSubida(MultipartFile fotoSubida) {
        this.fotoSubida = fotoSubida;
    }

    public byte[] getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(byte[] imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    @Override
    public String toString() {
        return "Reloj{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", resistenciaAgua=" + resistenciaAgua +
                ", genero='" + genero + '\'' +
                ", id=" + id +
                '}';
    }
}
