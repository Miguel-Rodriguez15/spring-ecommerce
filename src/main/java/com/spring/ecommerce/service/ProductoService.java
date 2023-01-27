package com.spring.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.spring.ecommerce.model.Producto;


public interface ProductoService {

    /**
     * Metodo para guardar un producto
     */
    public Producto save(Producto producto);

    /**
     * Metodo para devolver los productos por id
     */
    public Optional<Producto> get(Integer id);

    /**
     * Metodo para actualizar los productos
     */
    public void update(Producto producto);

    /**
     * Metodo para eliminar los productos por id
     */
    public void delete(Integer id);

    /**
     * Metodo para listar los productos
     */

    public List<Producto> findAll();
}
