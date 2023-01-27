package com.spring.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.spring.ecommerce.model.Usuario;

public interface IUsuarioService {
    /**
     * Metodo para  los datos del usuario
     */
    List<Usuario> findall();//obtenemos todos los usuarios

    /**
     * Metodo para enviar por parametro el id del usuario
     */
    Optional<Usuario> findById(Integer id);

    /**
     * Metodo para guardar al usuario
     */
    Usuario save(Usuario usuario);

    /**
     * Metodo para enviar por parametro el email del usuario
     */
    Optional<Usuario> findByEmail(String email);
}
