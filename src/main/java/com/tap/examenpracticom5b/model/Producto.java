/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tap.examenpracticom5b.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author jhudy
 */
@Data
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int id;

    @Size(min = 3, max = 100, message = "La descripción tiene como máximo 100 caracteres")
    @NotBlank(message = "La descripción no puede estar vacía")
    @Column(name = "descripcion_prod")
    private String descripccion;

    @NotNull(message = "El precio no puede estar vacío")
    @DecimalMin(value = "1.0", message = "El valor debe ser mayor o igual a 1")
    @Pattern(regexp = "\\d+(\\.\\d{1,2})?", message = "El valor debe ser un double con 2 decimales")
    @Column(name = "precio_prod")
    private double precio;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "El valor debe ser mayor o igual a 1")
    @Column(name = "cantidad_prod")
    private int cantidad;

    @NotNull(message = "El subtotal no puede estar vacío")
    @Column(name = "subTotal_prod")
    private double subTotal;

    @NotNull(message = "El descuento no puede estar vacío")
    @Column(name = "descuento_prod")
    private double descuento;

    @NotNull(message = "El iva no puede estar vacío")
    @Column(name = "iva_prod")
    private double iva;

    @NotNull(message = "El pvp no puede estar vacío")
    @Column(name = "pvp_prod")
    private double pvp;

}
