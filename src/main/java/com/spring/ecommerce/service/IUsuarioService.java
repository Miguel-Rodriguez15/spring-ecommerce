package com.spring.ecommerce.service;

import java.util.Optional;

import com.spring.ecommerce.model.Usuario;

public interface IUsuarioService {
  Optional<Usuario> findById(Integer id);
  //metodo save para guardar en la base de datos
  Usuario save(Usuario usuario);
  
  Optional<Usuario> findByEmail(String email);
}
