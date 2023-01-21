package com.spring.ecommerce.service;

import java.util.Optional;

import com.spring.ecommerce.model.Usuario;

public interface IUsuarioService {
  Optional<Usuario> findById(Integer id);
}
