package com.spring.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.ecommerce.model.Producto;
//indico la tabla va a usarser el repositorio 
@Repository //inidicamos para que esta despues se pueda inyectar en nuestro servicio
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
