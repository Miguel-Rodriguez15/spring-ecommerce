package com.spring.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ecommerce.model.Orden;
import com.spring.ecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService {

	@Autowired
	private IOrdenRepository ordenRepository;

	@Override
	public Orden save(Orden orden) {

		return ordenRepository.save(orden);// obtenemos todas las ordenes
	}

	@Override
	public List<Orden> findAll() {

		return ordenRepository.findAll();
	}

	public String generarNumeroOrden() {
		int numero = 0;// con este vamos incrementando el numero de la ordes para despues pasarlo a un
						// string
		String numeroConcatenado = "";// este nos va a devolver el numero en String
		List<Orden> ordenes = findAll();// retornamos todas las ordenes
		List<Integer> numeros = new ArrayList<Integer>();// iniciamos una lista como entero
		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));// hacemops las convercion de
																					// cadenas de la base de datos a
																					// numeros
		if (ordenes.isEmpty()) {
			numero = 1;
		} else {// en caso de que no este vacia es uqe ya hay ordenes guardadas
			numero = numeros.stream().max(Integer::compare).get();// pasamos el valor mayor de el objeto numero
			numero++;// y que este objeto numero se vaya incrementando
		}

		if (numero < 10) { // 0000001000
			numeroConcatenado = "000000000" + String.valueOf(numero);
		} else if (numero < 100) {
			numeroConcatenado = "00000000" + String.valueOf(numero);
		} else if (numero < 1000) {
			numeroConcatenado = "0000000" + String.valueOf(numero);
		} else if (numero < 10000) {
			numeroConcatenado = "0000000" + String.valueOf(numero);
		}

		return numeroConcatenado;
	}

}
