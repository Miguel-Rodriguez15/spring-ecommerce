package com.spring.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.spring.ecommerce.model.Producto;
//creo mis metodos crud
public interface ProductoService {
   public Producto save(Producto producto);//guardar
   public  Optional<Producto> get(Integer id);//obtener un solo producto
   public void update(Producto producto);//actualizar 
   public void delete(Integer id);//eliminar
   public List<Producto> findAll();//listar
}
