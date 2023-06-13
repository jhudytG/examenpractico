/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tap.examenpracticom5b.repository;

import com.tap.examenpracticom5b.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jhudy
 */

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    @Query( value = "Select * from Producto p where p.descripcion_prod = :descripcion", nativeQuery = true)
    public Producto buscarProducto (String descripcion);
}
