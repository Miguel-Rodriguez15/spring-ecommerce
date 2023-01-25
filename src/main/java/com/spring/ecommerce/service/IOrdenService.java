package com.spring.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.spring.ecommerce.model.Orden;
import com.spring.ecommerce.model.Usuario;

public interface IOrdenService {
    Optional<Orden> findById(Integer id);//pasamo el id de la orden que queremos obtener

    List<Orden> findAll();//obtenemos todas las ordenes

    Orden save(Orden orden);

    String generarNumeroOrden();

    List<Orden> findByUsuario(Usuario usuario);


}
