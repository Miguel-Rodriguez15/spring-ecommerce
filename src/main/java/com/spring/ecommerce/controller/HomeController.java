package com.spring.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/")//le indico que se lanzara desde la ruta raiz
public class HomeController {
	@Autowired
	private ProductoService productoService;//lamo mis ervicios

	@GetMapping("")
	public String home(Model model) {//llamo miclase model para poder llamar los atributos
		model.addAttribute("productos", productoService.findAll());//traigo todos los atributos de productos
		return "usuario/home";
	}
}
