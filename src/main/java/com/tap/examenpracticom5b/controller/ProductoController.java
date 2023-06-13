/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tap.examenpracticom5b.controller;

import com.tap.examenpracticom5b.model.Producto;
import com.tap.examenpracticom5b.service.ProductoServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jhudy
 */
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoServiceImpl productoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listaProductos() {
        return new ResponseEntity<>(productoService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody @Valid Producto p) {
        // Calcular el valor de venta
        double valorVenta = p.getCantidad() * p.getPrecio();

        // Aplicar descuento si el valor de venta es superior a $50
        double descuento = 0;
        if (valorVenta > 50) {
            descuento = valorVenta *= 0.1; // Aplicar descuento del 10%
        }

        // Calcular el IVA
        double iva = valorVenta * 0.12;

        // Calcular el total a pagar
        double total = valorVenta - descuento + iva;

        // Asignar los valores calculados al producto
        p.setSubTotal(valorVenta);
        p.setIva(iva);
        p.setPvp(total);
        return new ResponseEntity<>(productoService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody @Valid Producto p) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            try {
                producto.setDescripccion(p.getDescripccion());
                producto.setCantidad(p.getCantidad());
                producto.setPrecio(p.getPrecio());

                double valorVenta = p.getCantidad() * p.getPrecio();

                double descuento = 0;
                if (valorVenta > 50) {
                    descuento = valorVenta *= 0.1;
                }
                double iva = valorVenta * 0.12;
                double total = valorVenta - descuento + iva;

                producto.setSubTotal(valorVenta);
                producto.setIva(iva);
                producto.setPvp(total);

                return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Producto> elimiarUProducto(@PathVariable Integer id) {
        productoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
