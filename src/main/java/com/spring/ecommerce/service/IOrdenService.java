package com.spring.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.spring.ecommerce.model.Orden;
import com.spring.ecommerce.model.Usuario;

public interface IOrdenService {

    /**
     * Metodo para mandar por parametro el id por paramatreo de la orden
     */
    Optional<Orden> findById(Integer id);//pasamo el id de la orden que queremos obtener

    /**
     * Metodo para listar todos los datos de la orden
     */
    List<Orden> findAll();//obtenemos todas las ordenes

    /**
     * metodo para guardar la orden
     */
    Orden save(Orden orden);

    /**
     * metodo para generar el numero de la orden
     */
    String generarNumeroOrden();

    /**
     * Metodo para enviar los datos del usuario por parametro y enviarlos a la orde
     */
    List<Orden> findByUsuario(Usuario usuario);


}
