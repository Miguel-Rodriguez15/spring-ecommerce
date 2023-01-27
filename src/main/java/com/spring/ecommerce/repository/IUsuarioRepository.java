package com.spring.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.ecommerce.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    /**
     * Metodo para tarer el email del usuario
     */
    Optional<Usuario> findByEmail(String email);
}
