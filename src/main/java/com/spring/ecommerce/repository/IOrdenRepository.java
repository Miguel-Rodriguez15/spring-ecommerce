package com.spring.ecommerce.repository;

import com.spring.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.ecommerce.model.Orden;

import java.util.List;

/**
 * repositorio de las ordenes
 */
@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {
    /**
     * Metodo para traer el usaurio a la orden
     */
    List<Orden> findByUsuario(Usuario usuario);
}
