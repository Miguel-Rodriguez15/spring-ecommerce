package com.spring.ecommerce.service;

import java.util.List;

import com.spring.ecommerce.model.Orden;

public interface IOrdenService {
    List<Orden> findAll();//obtenemos todas las ordenes
	Orden save (Orden orden);
	String generarNumeroOrden();
}
