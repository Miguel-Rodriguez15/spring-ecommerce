package com.spring.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/")//le indico que se lanzara desde la ruta raiz
public class HomeController {
	private final Logger log=LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private ProductoService productoService;//lamo mis ervicios

	@GetMapping("")
	public String home(Model model) {//llamo miclase model para poder llamar los atributos
		model.addAttribute("productos", productoService.findAll());//traigo todos los atributos de productos
		return "usuario/home";
	}
	
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id) {
		log.info("Id producto enviado como parametro{}",id);
	return "usuario/productohome";
	}
}